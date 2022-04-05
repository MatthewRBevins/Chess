import java.util.Arrays;
import java.util.Scanner;
public class Game {
    public Scanner s = new Scanner(System.in);
    //TODO:y AND x ARE REVERSED
    //TODO:CAPTURE PIECES
    public Piece[][] board = {
            {new Piece(1,1),new Piece(2,1),new Piece(3,1),new Piece(4,1),new Piece(5,1),new Piece(3,1),new Piece(2,1),new Piece(1,1)},
            {new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1)},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2)},
            {new Piece(1,2),new Piece(2,2),new Piece(3,2),new Piece(4,2),new Piece(5,2),new Piece(3,2),new Piece(2,2),new Piece(1,2)}
    };
    public Game() {
        while (true) {
            System.out.print("Choice: ");
            String in = s.next();
            switch(in) {
                case "move":
                    System.out.print("pos 1: ");
                    int pos1 = s.nextInt();
                    System.out.print("pos 2: ");
                    int pos2 = s.nextInt();
                    System.out.print("Y: ");
                    int npos1 = s.nextInt();
                    System.out.print("X: ");
                    int npos2 = s.nextInt();
                    Piece temp = board[pos1][pos2];
                    board[pos1][pos2] = null;
                    board[npos1][npos2] = temp;
                    break;
                case "checkMoves":
                    System.out.print("pos 1: ");
                    int pos11 = s.nextInt();
                    System.out.print("pos 2: ");
                    int pos22 = s.nextInt();
                    System.out.println(Arrays.deepToString(checkMoves(pos11,pos22,1)));
                    break;
                case "stop":
                    System.exit(69);
                default:
                    break;
            }
        }
    }
    public boolean checkSpace(int y, int x, int player) {
        return board[y][x] == null || (board[y][x].player != player && board[y][x].type != 5);
    }
    public int[][] checkMoves(int y, int x, int player) {
        if (board[y][x] == null) {
            return new int[][]{{-1}};
        }
        else {
            int[][] arr = new int[32][2];
            for (int[] i : arr) {
                Arrays.fill(i,-1);
            }
            int index = 0;
            switch(board[y][x].type) {
                //pawn
                case 0:
                    break;
                //rook
                case 1:
                    for (int i = y+1; i < 8; i++) {
                        try {
                            if (!checkSpace(i,x,player)) {
                                break;
                            }
                            arr[index] = new int[]{i,x};
                            index++;
                        }
                        catch(ArrayIndexOutOfBoundsException a) {
                            break;
                        }
                    }
                    System.out.println(1);
                    for (int i = y-1; i > 0; i--) {
                        try {
                            if (!checkSpace(i,x,player)) {
                                break;
                            }
                            arr[index] = new int[]{i,x};
                            index++;
                        }
                        catch(ArrayIndexOutOfBoundsException a) {
                            break;
                        }
                    }
                    System.out.println(2);
                    for (int i = x+1; i < 8; i++) {
                        try {
                            if (!checkSpace(y,i,player)) {
                                break;
                            }
                            arr[index] = new int[]{y,i};
                            index++;
                        }
                        catch(ArrayIndexOutOfBoundsException a) {
                            break;
                        }
                    }
                    System.out.println(3);
                    for (int i = x-1; i > 0; i--) {
                        try {
                            if (!checkSpace(y,i,player)) {
                                break;
                            }
                            arr[index] = new int[]{y,i};
                            index++;
                        }
                        catch(ArrayIndexOutOfBoundsException a) {
                            break;
                        }
                    }
                    break;
                //knight
                case 2:
                    int[] poss = new int[]{1,-1,2,-2};
                    for (int i = 0; i < poss.length; i++) {
                        for (int j = 0; j < poss.length; j++) {
                            try {
                                if (!checkSpace(y+j,x+i,player)) {
                                    break;
                                }
                                arr[index] = new int[]{y+j,x+i};
                            }
                            catch(ArrayIndexOutOfBoundsException a) {
                                break;
                            }
                        }
                    }
                    break;
                //bishop
                case 3:
                    break;
                //yueen
                case 4:
                    break;
                //king
                case 5:
                    break;
                default:
                    System.err.println("No such piece eyists.");
                    break;
            }
            return arr;
        }
    }
    public boolean canTake() {
        return false;
    }
    public void changePos(Piece p) {
        
    }
    public int[] getPos(Piece p) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == p) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }
}
