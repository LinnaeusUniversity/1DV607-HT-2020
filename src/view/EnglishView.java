package view;


import model.Boat;
import model.BoatType;
import model.Member;
import model.MenuOption;

import java.util.List;
import java.util.Scanner;

public class EnglishView implements IView {
    private Scanner scanner = new Scanner(System.in);

    public void welcomeMessage() {
        System.out.println("|==============================|");
        System.out.println("| Welcome to 1DV607 Workshop 2 |");
        System.out.println("|==============================|");
    }

    public void showMainMenuInstructionView() {
        System.out.println("|======= Home page ======|");
        System.out.println("| 1. " + MenuOption.ADD_MEMBER + "          |");
        System.out.println("| 2. " + MenuOption.SHOW_COMPACT_LIST + "   |");
        System.out.println("| 3. " + MenuOption.SHOW_VERBOSE_LIST + "   |");
        System.out.println("| 4. " + MenuOption.RETRIEVE_MEMBER + "     |");
        System.out.println("| 5. " + MenuOption.EDIT_MEMBER + "         |");
        System.out.println("| 6. " + MenuOption.DELETE_MEMBER + "       |");
        System.out.println("| 7. " + MenuOption.ADD_BOAT + "            |");
        System.out.println("| 8. " + MenuOption.EDIT_BOAT + "           |");
        System.out.println("| 9. " + MenuOption.DELETE_BOAT + "         |");
        System.out.println("| 10. " + MenuOption.EXIT + "               |");
        System.out.println("| ====================== |");
        System.out.print("| " + MenuOption.MAKE_A_CHOICE + ":");
    }

    @Override
    public MenuOption readOption() {
        int choice = readInteger();
        switch (choice) {
            case 1:
                return MenuOption.ADD_MEMBER;
            case 2:
                return MenuOption.SHOW_COMPACT_LIST;
            case 3:
                return MenuOption.SHOW_VERBOSE_LIST;
            case 4:
                return MenuOption.RETRIEVE_MEMBER;
            case 5:
                return MenuOption.EDIT_MEMBER;
            case 6:
                return MenuOption.DELETE_MEMBER;
            case 7:
                return MenuOption.ADD_BOAT;
            case 8:
                return MenuOption.EDIT_BOAT;
            case 9:
                return MenuOption.DELETE_BOAT;
            case 10:
                return MenuOption.EXIT;
            default:
                System.err.print("Invalid choice");
                return readOption();
        }
    }

    @Override
    public Member addMember() {
        Member member = new Member();
        readNameAndPersonalNumber(member);
        memberAddedSuccessfully();
        return member;
    }

    @Override
    public int retrieveMember() {
        displayMessage("Please enter member id: ");
        return readInteger();
    }

    @Override
    public void displayMember(Member member) {
        System.out.println("Name:" + member.getName() +
                " Security NO: " + member.getPersonalNumber() +
                " Unique Id: " + member.getId());
    }

    @Override
    public int deleteMember() {
        return retrieveMember();
    }

    @Override
    public void displayVerboseList(List<Member> members) {
        displayMessage("Verbose list\r\n-----------------");
        for (Member member : members) {
            displayMessage("\r\n*******************");
            displayMessage("Name:" + member.getName() + " Personal number: " + member.getPersonalNumber() + " Unique Id: " + member.getId());
            if (member.getCountBoats() > 0) {
                for (var boat : member.getBoatArrayList()) {
                    System.out.println("\nBoat Id: " + boat.getId() + "\n" +
                            "Boat Type: " + boat.getType() +
                            "\nBoat Length: " + boat.getLength());
                }
            } else {
                displayMessage("Member has no boat");
            }
        }
        if (members.isEmpty()) {
            displayMessage("List is empty");
        }
    }

    @Override
    public void displayCompactList(List<Member> members) {
        System.out.println("Compact list");
        for (Member member : members) {
            displayMessage("--------------------");
            System.out.println("Name:" + member.getName() +
                    " Member Id: " + "'" + member.getId() + "'" +
                    " number of boats: " + member.getCountBoats());
        }
        if (members.isEmpty()) {
           displayMessage("List is empty");
        }
    }

    @Override
    public void editMember(Member member) {
        readNameAndPersonalNumber(member);
        memberEditedSuccessfully();
    }

    @Override
    public void addBoat(Member member) {
        Boat boat = new Boat();
        readLengthAndType(boat);
        member.addBoat(boat);
        boatAddedSuccessfully();
    }

    @Override
    public int retrieveBoat() {
        return readInteger();
    }


    @Override
    public void editBoat(Boat boat) {
        readLengthAndType(boat);
        boatEditedSuccessfully();
    }

    private void readLengthAndType(Boat boat) {
        displayMessage("Please enter boat length");
        boat.setLength(scanner.nextInt());
        boat.setType(readBoatType());
    }

    private void readNameAndPersonalNumber(Member member) {
        while (true) {
            try {
                displayMessage("\n----------\nEnter name: ");
                member.setName(scanner.nextLine());
                break;
            } catch (Exception exception) {
                displayError(exception.getMessage());
            }
        }
        while (true) {
            try {
                displayMessage("\n----------\nEnter personal number\r\n" +
                        "12-Digits(YYYYMMDDXXXX ");
                member.setPersonalNumber(scanner.nextLine());
                break;
            } catch (Exception exception) {
                displayError(exception.getMessage());
            }
        }
    }

    private BoatType readBoatType() {
        displayMessage("Please enter boat type");
        displayBoatType();
        while (true) {
            int value = readInteger();
            switch (value) {
                case 1:
                    return BoatType.MOTORSAILER;
                case 2:
                    return BoatType.SAILBOAT;
                case 3:
                    return BoatType.CANOE;
                case 4:
                    return BoatType.OTHER;
                default:
                    System.err.println("invalid type");
            }
        }
    }

    private void displayBoatType() {
        displayMessage("1. " + BoatType.MOTORSAILER +
                "\r\n2. " + BoatType.SAILBOAT +
                "\n3. " + BoatType.CANOE +
                "\n4. " + BoatType.OTHER);
    }

    private int readInteger() {
        while (true) {
            int value;
            try {
                value = scanner.nextInt();
            } catch (Exception exception) {
                scanner.nextLine();
                System.out.println("Invalid input");
                continue;
            }
            scanner.nextLine();
            return value;
        }
    }

    public void boatAddedSuccessfully() {
        System.out.println("Boat added successfully");
    }

    public void memberAddedSuccessfully() {
        System.out.println("Member Added successfully");

    }


    public void memberEditedSuccessfully() {
        System.out.println("Member edited successfully");

    }

    public void boatEditedSuccessfully() {
        System.out.println("boat edited successfully ");

    }

    @Override
    public void displayExit() {
        displayMessage("Thank you and GoodBye");
    }

    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void memberDeletedSuccessfully() {
        System.out.println("Member has been deleted successfully ");
    }

    @Override
    public void enterBoatId() {
        System.out.print("Enter boat ID |:: ");
    }

    @Override
    public void boatDeletedSuccessfully() {
        System.out.println("Boat deleted successfully ");

    }


}
