package model;

import java.util.ArrayList;
import java.util.List;

public class Registry {
    private List<Member> memberList = new ArrayList<>();
    private int newMemberUniqueId;
    private Storage readAndWriteStorageData;

    public Registry() {
        readData();
    }

    public void readData() {
        readAndWriteStorageData = new Storage();
        memberList = readAndWriteStorageData.loadRegistry();
        if (!memberList.isEmpty()) {
            newMemberUniqueId = memberList.get(memberList.size() - 1).getId();
        }
    }

    public void saveData() {
        readAndWriteStorageData.writeDataInFile(memberList);
    }

    public void addMember(Member member) {
        member.setId(++newMemberUniqueId);
        this.memberList.add(member);
    }

    public void deleteMember(Member member) {
        this.memberList.remove(member);
    }

    public Member retrieveMember(int memberId) {
        for (Member member : this.memberList) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        throw new RuntimeException("Member not found");
    }

    public List<Member> getMemberList() {
        return memberList;
    }
}
