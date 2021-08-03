public class Player {
    String name;
    Seats seat;
    int score = 0;
    Hand hand;

    public Player(String name, Seats seat, Hand hand){
        this.name = name;
        this.seat = seat;
        this.hand = hand;
    }
}
