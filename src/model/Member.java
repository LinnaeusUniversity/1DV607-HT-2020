package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int id;
    private String personalNumber;
    private int boatIndex = 0;
    private List<Boat> boatArrayList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Boat> getBoatArrayList() {
        return boatArrayList;
    }

    public int getCountBoats() {
        return boatArrayList.size();
    }

    public void updateBoatIndex() {
        if (!boatArrayList.isEmpty()) {
            boatIndex = boatArrayList.get(boatArrayList.size() - 1).getId();
        }
    }

    public void addBoat(Boat boat) {
        if (boat.getId() == 0) {
            boat.setId(++boatIndex);
        }
        this.boatArrayList.add(boat);
    }

    public void deleteBoat(Boat boat) {
        this.boatArrayList.remove(boat);
    }

    public Boat getBoatById(int boatId) {
        for (Boat boat : this.boatArrayList) {
            if (boat.getId() == boatId) {
                return boat;
            }
        }
        throw new RuntimeException("boat not found");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkName(name);
        this.name = name;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        checkPersonalNumber(personalNumber);
        this.personalNumber = personalNumber;
    }

    private void checkPersonalNumber(String personalNumber) {
        if (personalNumber.length() != 12) {
            throw new RuntimeException("Invalid personal number");
        }
        String string = personalNumber.substring(0, 8);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid personal number date");
        }
    }

    private void checkName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isAlphabetic(c) && !Character.isWhitespace(c)) {
                throw new RuntimeException("Invalid name");
            }
        }
    }
}
