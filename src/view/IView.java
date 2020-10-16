package view;

import model.Boat;
import model.Member;
import model.MenuOption;

import java.util.List;

public interface IView {
    MenuOption readOption();

    Member addMember();

    int deleteMember();

    int retrieveMember();

    void displayMember(Member member);

    void displayVerboseList(List<Member> members);

    void displayCompactList(List<Member> members);

    void editMember(Member member);

    void addBoat(Member member);

    int retrieveBoat();

    void editBoat(Boat boat);

    void displayExit();

    void displayError(String message);

    void showMainMenuInstructionView();

    void welcomeMessage();

    void displayMessage(String message);

    void enterBoatId();

    void memberDeletedSuccessfully();

    void boatDeletedSuccessfully();
}