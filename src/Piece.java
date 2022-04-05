public class Piece{
    String[][] key = {{"pawn",""},{"rook","R"},{"knight","N"},{"bishop","B"},{"queen","Q"},{"king","K"}};
    int type;
    int player;
    public Piece(int type, int player) {
        this.type = type;
        this.player = player;
    }
    public String getSymbol() {
        return key[type][0];
    }
}

