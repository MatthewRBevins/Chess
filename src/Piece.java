public class Piece{
    String[][] key = {{"pawn","♟","♙"},{"rook","♜","♖"},{"knight","♞","♘"},{"bishop","♝","♗"},{"king","♚","♔"},{"queen","♛","♕"}};
    int type;
    int player;
    public Piece(int type, int player) {
        this.type = type;
        this.player = player;
    }
    public int[] getPos() {
        for (int i = 0; i < Main.game.board.length; i++) {
            for (int j = 0; j < Main.game.board[i].length; j++) {
                if (Main.game.board[i][j] == this) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1};
    }
    public String getSymbol() {
        return key[type][player];
    }
    public String getName() { return key[type][0]; }
    public String getColor() { if (this.player == 1) {return "W";} else {return "B";} }
}

