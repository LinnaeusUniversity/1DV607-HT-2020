package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Member.
 */
public class Member {
    private String name;
    private int id;
    private String personalNumber;
    private int boatIndex = 0;
    private List<Boat> boatArrayList = new ArrayList<>();

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets boat array list.
     *
     * @return the boat array list
     */
    public List<Boat> getBoatArrayList() {
        return boatArrayList;
    }

    /**
     * Gets count boats.
     *
     * @return the count boats
     */
    public int getCountBoats() {
        return boatArrayList.size();
    }

    /**
     * Update boat index.
     */
    public void updateBoatIndex() {
        if (!boatArrayList.isEmpty()) {
            boatIndex = boatArrayList.get(boatArrayList.size() - 1).getId();
        }
    }

    /**
     * Add boat.
     *
     * @param boat the boat
     */
    public void addBoat(Boat boat) {
        if (boat.getId() == 0) {
            boat.setId(++boatIndex);
        }
        this.boatArrayList.add(boat);
    }

    /**
     * Delete boat.
     *
     * @param boat the boat
     */
    public void deleteBoat(Boat boat) {
        this.boatArrayList.remove(boat);
    }

    /**
     * Gets boat by id.
     *
     * @param boatId the boat id
     * @return the boat by id
     */
    public Boat getBoatById(int boatId) {
        for (Boat boat : this.boatArrayList) {
            if (boat.getId() == boatId) {
                return boat;
            }
        }
        throw new RuntimeException("boat not found");
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        checkName(name);
        this.name = name;
    }

    /**
     * Gets personal number.
     *
     * @return the personal number
     */
    public String getPersonalNumber() {
        return personalNumber;
    }

    /**
     * Sets personal number.
     *
     * @param personalNumber the personal number
     */
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
