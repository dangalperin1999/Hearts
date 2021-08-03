import java.util.ArrayList;

public class Trick {
    ArrayList<Card> cards = new ArrayList<>();
    Player leader;
    Player winner;
    Suits lead;
    boolean brokenHearts = false;

    public void discard(Deck deck){
        deck.cards.addAll(cards);
        cards.clear();
    }

    public Player getWinner(){ return winner; }

    public Player getLeader(){ return leader; }

    public Suits getLead(){ return lead; }
}
