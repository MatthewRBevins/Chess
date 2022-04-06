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
                    System.out.print("current Y: ");
                    int pos1 = s.nextInt();
                    System.out.print("current X: ");
                    int pos2 = s.nextInt();
                    System.out.print("new Y: ");
                    int npos1 = s.nextInt();
                    System.out.print("new X: ");
                    int npos2 = s.nextInt();
                    if (contains(checkMoves(pos1,pos2,1),new int[]{npos1,npos2})) {
                        Piece temp = board[pos1][pos2];
                        board[pos1][pos2] = null;
                        board[npos1][npos2] = temp;
                        System.out.println("MOVED!");
                    }
                    else {
                        System.out.println("no");
                    }
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
        if (y < 0 || y > board.length || x < 0 || x > board.length) {
            return false;
        }
        return board[y][x] == null || (board[y][x].player != player && board[y][x].type != 5);
    }
    public int[][] rook(int x,int y,int player,int index,int[][] arr) {
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
        return arr;
    }
    public int[][] bishop(int x,int y,int player,int index, int[][] arr) {
        int j = x+1;
        for (int i = y+1; i < 8; i++) {
            try {
                if (!checkSpace(i,j,player)) {
                    break;
                }
                arr[index] = new int[]{i,j};
                index++;
            }
            catch(ArrayIndexOutOfBoundsException a) {
                break;
            }
            j++;
        }
        j = x-1;
        for (int i = y-1; i > 0; i--) {
            try {
                if (!checkSpace(i,x,player)) {
                    break;
                }
                arr[index] = new int[]{i,j};
                index++;
            }
            catch(ArrayIndexOutOfBoundsException a) {
                break;
            }
        }
        j = x+1;
        for (int i = y-1; i > 0; i--) {
            try {
                if (!checkSpace(i,j,player)) {
                    break;
                }
                arr[index] = new int[]{i,j};
                index++;
            }
            catch(ArrayIndexOutOfBoundsException a) {
                break;
            }
            j++;
        }
        j = x-1;
        for (int i = y+1; i < 8; i++) {
            try {
                if (!checkSpace(i,j,player)) {
                    break;
                }
                arr[index] = new int[]{i,j};
                index++;
            }
            catch(ArrayIndexOutOfBoundsException a) {
                break;
            }
            j++;
        }
        return arr;
    }
    public int[][] checkMoves(int y, int x, int player) {
        if ((y < 0 || y > board.length || x < 0 || x > board.length) || (board[y][x] == null))  {
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
                    arr = rook(x,y,player,index,arr);
                    break;
                //knight
                case 2:
                    int[] poss = new int[]{1,-1,2,-2};
                    for (int j : poss) {
                        for (int k : poss) {
                            try {
                                if (!checkSpace(y + k, x + j, player) || Math.abs(j) == Math.abs(k)) {
                                    continue;
                                }
                                arr[index] = new int[]{y + k, x + j};
                                index++;
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                    break;
                //bishop
                case 3:
                    arr = bishop(x,y,player,index,arr);
                    break;
                //king
                case 4:
                    int[] posss = new int[]{-1,0,1};
                    for (int i : posss) {
                        for (int k : posss) {
                            if ((i == 0 && k == 0) || !checkSpace(y+i,x+k,player)) {
                                continue;
                            }
                            arr[index] = new int[]{y+i,x+k};
                            index++;
                        }
                    }
                    break;
                //queen
                case 5:
                    arr = bishop(x,y,player,index,arr);
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i][0] == -1) {
                            index = i;
                        }
                    }
                    arr = rook(x,y,player,index,arr);
                    break;
                default:
                    System.err.println("No such piece exists.");
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
    public boolean contains(int[][] arr, int[] coord) {
        for (int[] i : arr) {
            if (Arrays.equals(i,coord)) {
                return true;
            }
        }
        return false;
    }
}
