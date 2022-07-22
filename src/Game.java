import java.util.Arrays;
import java.util.Scanner;
public class Game{
    public static Piece[][] board = {
            {new Piece(1,1),new Piece(2,1),new Piece(3,1),new Piece(4,1),new Piece(5,1),new Piece(3,1),new Piece(2,1),new Piece(1,1)},
            {new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1),new Piece(0,1)},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2),new Piece(0,2)},
            {new Piece(1,2),new Piece(2,2),new Piece(3,2),new Piece(4,2),new Piece(5,2),new Piece(3,2),new Piece(2,2),new Piece(1,2)}
    };
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int currentPlayer = 1;
        while (true) {
            int ri = 0;
            System.out.print(" ");
            for (int i = 0; i < board.length; i++) {
                System.out.print(" | " + i);
            }
            System.out.print(" |");
            System.out.println();
            for (Piece[] r : board) {
                System.out.print(ri+"| ");
                ri++;
                for (Piece p : r) {
                    if (p == null) {
                        System.out.print("··");
                    }
                    else {
                        System.out.print(p.getSymbol());
                    }
                    System.out.print(" | ");
                }
                System.out.println();
            }
            System.out.print("PLAYER " + currentPlayer + " CHOICE (type 'choices' to see options): ");


            /*
             * COMPUTER PLAYER
             */





            String in = s.next();
            switch(in) {
                case "choices":
                    System.out.println("'move': move a given piece to a new space.");
                    System.out.println("'checkMoves': shows you which places you can move a given piece.");
                    System.out.println("'stop': stops the game.");
                    System.out.println("'checkAttacking': shows you a list of the pieces a given piece is attacking.");
                    System.out.println("'checkAttackers': shows you a list of what pieces are attacking a given piece.");
                    Tools.enterToContinue();
                    break;
                case "move":
                    System.out.print("CURRENT ROW: ");
                    int row1 = s.nextInt();
                    System.out.print("CURRENT COLUMN: ");
                    int col1 = s.nextInt();
                    System.out.print("NEW ROW: ");
                    int newRow1 = s.nextInt();
                    System.out.print("NEW COLUMN: ");
                    int newCol1 = s.nextInt();
                    if (Tools.int2DArrContains(checkMoves(row1,col1,currentPlayer),new int[]{newRow1,newCol1})) {
                        Piece temp = board[row1][col1];
                        board[row1][col1] = null;
                        board[newRow1][newCol1] = temp;
                        System.out.println("MOVED!");
                        if (currentPlayer == 1) {
                            currentPlayer = 2;
                        }
                        else {
                            currentPlayer = 1;
                        }
                    }
                    else {
                        System.out.println("***ILLEGAL MOVE***");
                    }
                    Tools.enterToContinue();
                    break;
                case "checkMoves":
                    System.out.print("ROW: ");
                    int row2 = s.nextInt();
                    System.out.print("COLUMN: ");
                    int col2 = s.nextInt();
                    System.out.println(Arrays.deepToString(checkMoves(row2,col2,currentPlayer)));
                    Tools.enterToContinue();
                    break;
                case "checkAttacking":
                    System.out.print("ROW: ");
                    int row3 = s.nextInt();
                    System.out.print("COLUMN: ");
                    int col3 = s.nextInt();
                    System.out.println(attacking(row3,col3,board[row3][col3].player).length);
                    for (Piece p : attacking(row3,col3,board[row3][col3].player)) {
                        System.out.print(Arrays.toString(p.getPos()));
                    }
                    System.out.println();
                    break;
                case "checkAttackers":
                    break;
                case "stop":
                    System.exit(69);
                default:
                    break;
            }
        }
    }
    //PLAYER BEING ATTACKED
    public static boolean spaceUnderAttack(int y, int x, int player) {
        if (board[y][x] == null || board[y][x].player != player) {
            return false;
        }
        for (Piece[] pa : board) {
            for (Piece p : pa) {
                if (p.player != player && Tools.pieceArrContains(attacking(p.getPos()[0],p.getPos()[1],p.player),board[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }
    public static Piece[] attacking(int y, int x, int player) {
        Piece[] f = new Piece[10];
        int[][] cm = checkMoves(y,x,player);
        int index = 0;
        for (int[] i : cm) {
            if (board[i[0]][i[1]] != null) {
                f[index] = board[i[0]][i[1]];
                index++;
            }
        }
        Piece[] finalArr = new Piece[index];
        System.arraycopy(f, 0, finalArr, 0, finalArr.length);
        return finalArr;
    }
    public static boolean checkSpace(int y, int x, int player) {
        if (y < 0 || y > board.length || x < 0 || x > board.length) {
            return false;
        }
        return board[y][x] == null || (board[y][x].player != player && board[y][x].type != 5);
    }
    public static int[][] rook(int x,int y,int player,int index,int[][] arr) {
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
    public static int[][] bishop(int x,int y,int player,int index, int[][] arr) {
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
    public static int[][] checkMoves(int y, int x, int player) {
        if ((y < 0 || y > board.length || x < 0 || x > board.length) || (board[y][x] == null))  {
            return new int[][]{{-1}};
        }
        if (board[y][x].player != player) {
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
                    //TODO:EN PASSANT LATER
                    //TODO:PROMOTION LATER
                    //TODO:FIX NULL POINTER WHEN TOO FAR ACROSS BOARD
                    if (board[y][x].player == 1) {
                        if (checkSpace(y+1,x,player)) {
                            arr[index] = new int[]{y+1,x};
                            index++;
                        }
                        if (y == 1 && checkSpace(y+2,x,player)) {
                            arr[index] = new int[]{y+2,x};
                            index++;
                        }
                    }
                    else {
                        if (checkSpace(y-1,x,player)) {
                            arr[index] = new int[]{y-1,x};
                            index++;
                        }
                        if (y == 6 && checkSpace(y-2,x,player)) {
                            arr[index] = new int[]{y-2,x};
                            index++;
                        }
                    }
                    break;
                //rook
                case 1:
                    System.out.println("*****ROOK****");
                    System.out.println(Arrays.deepToString(rook(x, y, player, index, arr)));
                    arr = rook(x,y,player,index,arr).clone();
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i][0] == -1) {
                            index = i;
                            break;
                        }
                    }
                    System.out.println(Arrays.deepToString(arr));
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
            int[][] finalArr = new int[index][2];
            for (int i = 0; i < finalArr.length; i++) {
                finalArr[i] = arr[i];
            }
            System.out.println(Arrays.deepToString(finalArr));
            return finalArr;
        }
    }
}