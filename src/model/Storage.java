package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File memberDataBase = new File("MemberDataBASE.txt");

    public List<Member> loadRegistry() {
        List<Member> memberArrayListData = new ArrayList<>();
        try (Scanner scanner = new Scanner(memberDataBase)) {
            while (scanner.hasNext()) {
                var member = new Member();
                var countOfBoats = parseMember(scanner.nextLine(), member);
                for (var i = 0; i < countOfBoats; ++i) {
                    parseBoat(scanner.nextLine(), member);
                }
                member.updateBoatIndex();
                memberArrayListData.add(member);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return memberArrayListData;
    }

    public void writeDataInFile(List<Member> memberArrayList) {
        try (PrintWriter printWriter = new PrintWriter(memberDataBase.getAbsolutePath())) {
            if (memberDataBase.createNewFile()) {
                //
            }
            for (var member : memberArrayList) {
                printWriter.print(formatMember(member));
                for (var boat : member.getBoatArrayList()) {
                    printWriter.print(formatBoat(boat));
                }
            }
        } catch (IOException e) {
            return;
        }
    }

    private int parseMember(String str, Member member) {
        var list = str.split(";");
        if (list.length != 4) {
            throw new RuntimeException("Invalid member format");
        }
        member.setId(Integer.parseInt(list[0]));
        member.setName(list[1]);
        member.setPersonalNumber(list[2]);
        return Integer.parseInt(list[3]);
    }

    private void parseBoat(String str, Member member) {
        var list = str.split(";");
        if (list.length != 3) {
            throw new RuntimeException("Invalid boat format");
        }
        var boat = new Boat();
        boat.setId(Integer.parseInt(list[0]));
        boat.setLength(Integer.parseInt(list[1]));
        boat.setType(parseBoatType(list[2]));
        member.addBoat(boat);
    }

    private BoatType parseBoatType(String str) {
        switch (str) {
            case "MOTORSAILER":
                return BoatType.MOTORSAILER;
            case "SAILBOAT":
                return BoatType.SAILBOAT;
            case "CANOE":
                return BoatType.CANOE;
            case "OTHER":
                return BoatType.OTHER;
            default:
                throw new RuntimeException("Invalid boat type");
        }
    }

    private String formatMember(Member member) {
        return member.getId() + ";" + member.getName() + ";" + member.getPersonalNumber() + ";" + member.getCountBoats() + "\r\n";
    }

    private String formatBoat(Boat boat) {
        return boat.getId() + ";" + boat.getLength() + ";" + boat.getType() + "\r\n";
    }
}
