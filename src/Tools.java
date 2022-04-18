import java.util.Arrays;

public class Tools {
    public static boolean int2DArrContains(int[][] arr, int[] val) {
        for (int[] i : arr) {
            if (Arrays.equals(val,i)) {
                return true;
            }
        }
        return false;
    }
    public static boolean pieceArrContains(Piece[] arr, Piece val) {
        for (Piece p : arr) {
            if (p == val) {
                return true;
            }
        }
        return false;
    }
    public static void enterToContinue() {
        System.out.print("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e) {}
    }
}
