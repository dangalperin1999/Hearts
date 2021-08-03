public enum Suits {
    Clubs('\u2663'),
    Diamonds('\u2666'),
    Spades('\u2660'),
    Hearts('\u2665');

    // Class field
    static final int NUMBER_OF_SUITS = Hearts.ordinal() + 1;
    // Instance additional field
    private final char symbol;

    Suits(char unicodeSymbol) {
        this.symbol = unicodeSymbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
