package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Registry.
 */
public class Registry {
    private List<Member> memberList = new ArrayList<>();
    private int newMemberUniqueId;
    private Storage readAndWriteStorageData;

    /**
     * Instantiates a new Registry.
     */
    public Registry() {
        readData();
    }

    /**
     * Read data.
     */
    public void readData() {
        readAndWriteStorageData = new Storage();
        memberList = readAndWriteStorageData.loadRegistry();
        if (!memberList.isEmpty()) {
            newMemberUniqueId = memberList.get(memberList.size() - 1).getId();
        }
    }

    /**
     * Save data.
     */
    public void saveData() {
        readAndWriteStorageData.writeDataInFile(memberList);
    }

    /**
     * Add member.
     *
     * @param member the member
     */
    public void addMember(Member member) {
        member.setId(++newMemberUniqueId);
        this.memberList.add(member);
    }

    /**
     * Delete member.
     *
     * @param member the member
     */
    public void deleteMember(Member member) {
        this.memberList.remove(member);
    }

    /**
     * Retrieve member member.
     *
     * @param memberId the member id
     * @return the member
     */
    public Member retrieveMember(int memberId) {
        for (Member member : this.memberList) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        throw new RuntimeException("Member not found");
    }

    /**
     * Gets member list.
     *
     * @return the member list
     */
    public List<Member> getMemberList() {
        return memberList;
    }
}
