import java.util.Arrays;

public class Tools {
    public static boolean intArrContains(int[][] arr, int[] val) {
        for (int[] i : arr) {
            if (Arrays.equals(val,i)) {
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
