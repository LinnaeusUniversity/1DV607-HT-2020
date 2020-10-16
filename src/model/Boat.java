package model;

/**
 * The type Boat.
 */
public class Boat {
    private BoatType type;
    private int length;
    private int id;

    /**
     * Instantiates a new Boat.
     */
    public Boat() {
    }

    /**
     * Gets length.
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets length.
     *
     * @param length the length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public BoatType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(BoatType type) {
        this.type = type;
    }

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
}


