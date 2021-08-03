public enum Seats {
    North,
    East,
    South,
    West;

    // Static values()
    private static Seats[] values = Seats.values();
    public static Seats fromOrdinal(int ord) { return values[ord]; }

    // Number of seats is last seats + 1
    public static final int NUM_SEATS = Seats.values.length;

}