package controller;

import model.Boat;
import model.Member;
import model.Registry;
import view.IView;

import java.util.List;

public class Controller {
    private IView view;
    private Registry registry;

    public Controller(IView view, Registry registry) {
        this.view = view;
        this.registry = registry;
    }

    public void run() {
        view.welcomeMessage();
        boolean running = true;
        while (running) {
            view.showMainMenuInstructionView();
            switch (view.readOption()) {
                case ADD_MEMBER:
                    addMember();
                    break;
                case SHOW_COMPACT_LIST:
                    displayCompactList();
                    break;
                case SHOW_VERBOSE_LIST:
                    displayVerboseList();
                    break;
                case RETRIEVE_MEMBER:
                    retrieveMember();
                    break;
                case EDIT_MEMBER:
                    editMember();
                    break;
                case DELETE_MEMBER:
                    deleteMember();
                    break;
                case ADD_BOAT:
                    addBoat();
                    break;
                case EDIT_BOAT:
                    editBoat();
                    break;
                case DELETE_BOAT:
                    deleteBoat();
                    break;
                case EXIT:
                    exit();
                    running = false;
                    break;
                default:
                    displayError();
                    break;
            }
        }
    }

    public void addMember() {
        Member member = view.addMember();
        registry.addMember(member);
    }

    private void deleteMember() {
        int memberId = view.deleteMember();
        Member member = registry.retrieveMember(memberId);
        registry.deleteMember(member);
        view.memberDeletedSuccessfully();

    }

    public void retrieveMember() {
        int memberId = view.retrieveMember();
        try {
            Member member = registry.retrieveMember(memberId);
            view.displayMember(member);
        } catch (Exception exception) {
            view.displayError(exception.getMessage());
        }
    }

    public void displayVerboseList() {
        List<Member> members = registry.getMemberList();
        view.displayVerboseList(members);
    }

    public void displayCompactList() {
        List<Member> members = registry.getMemberList();
        view.displayCompactList(members);
    }

    public void editMember() {
        int memberId = view.retrieveMember();
        try {
            Member member = registry.retrieveMember(memberId);
            view.editMember(member);
        } catch (Exception exception) {
            view.displayError(exception.getMessage());
        }
    }

    public void addBoat() {
        int memberId = view.retrieveMember();
        try {
            Member member = registry.retrieveMember(memberId);
            view.addBoat(member);
        } catch (Exception exception) {
            view.displayError(exception.getMessage());
        }
    }

    public void editBoat() {
        int memberId = view.retrieveMember();
        try {
            Member member = registry.retrieveMember(memberId);
            view.enterBoatId();
            int boatId = view.retrieveBoat();
            Boat boat = member.getBoatById(boatId);
            view.editBoat(boat);
        } catch (Exception exception) {
            view.displayError(exception.getMessage());
        }
    }

    public void deleteBoat() {
        int memberId = view.retrieveMember();
        try {
            Member member = registry.retrieveMember(memberId);
            view.enterBoatId();
            int boatId = view.retrieveBoat();
            Boat boat = member.getBoatById(boatId);
            member.deleteBoat(boat);
            view.boatDeletedSuccessfully();
        } catch (Exception exception) {
            view.displayError(exception.getMessage());
        }
    }

    public void exit() {
        registry.saveData();
        view.displayExit();
    }

    public void displayError() {
        view.displayError("Invalid choice");
    }
}
