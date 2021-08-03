import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>(52);

    public Deck(){
        int i=0;
        for (Suits suit : Suits.values()) {
            for (Ranks rank : Ranks.values()) {
                cards.add(i,new Card(suit, rank));
                ++i;
            }
        }
    }

    public void shuffle(){
        Random rand = new Random();
        Card temp;
        int min=0;
        for (int i = 51; i > 0; i--) {
            int rand1 = rand.nextInt(50-min+min);
            temp = cards.get(i);
            cards.set(i, cards.get(rand1));
            cards.set(rand1, temp);
            min++;
        }
    }

    public boolean hasCards(){
        if(cards.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public Card dealCard(){
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
}
