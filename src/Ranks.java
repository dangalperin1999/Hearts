/* Printout with Look-Up-Table
enum Ranks {
	Deuce, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;

	static final int NUMBER_OF_RANKS = Ranks.values().length;
	static final String RANK_SYMBOLS = "23456789TJQKA";

	@Override
	public String toString() {
		return String.valueOf(RANK_SYMBOLS.charAt(this.ordinal()));
	}

}
*/
enum Ranks {
    Deuce('2'),
    Three('3'),
    Four('4'),
    Five('5'),
    Six('6'),
    Seven('7'),
    Eight('8'),
    Nine('9'),
    Ten('T'),
    Jack('J'),
    Queen('Q'),
    King('K'),
    Ace('A');

    static final int NUMBER_OF_RANKS = Ranks.values().length;
    private final String symbol;

    Ranks(char oneChar) {
        this.symbol = String.valueOf(oneChar);
    }

    public String getSymbol() {	return symbol; }
}
