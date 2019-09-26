import java.util.HashSet;
import java.util.Scanner;

public class orthogonalVectors {
    /**
     * https://itu.codejudge.net/apalg19f/exercise/9165/view
     **/

    public static boolean ort (int[] u, int[] v) {
        int prod = 0;
        for (int i = 0; i < u.length; i++) {
            if (u[i] != 0 && v[i] != 0) return false;
            prod = prod + u[i] * v[i];
        }
        return prod == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] aux = scanner.nextLine().split(" ");
        int c = Integer.parseInt(aux[0]);
        int r = Integer.parseInt(aux[1]);
        HashSet<int[]> u = new HashSet<>();
        HashSet<int[]> v = new HashSet<>();

        for (int i = 1; i <= r; i++) {
            int[] vector = new int[c];
            for (int j = 0; j < c; j++){
                vector[j] = scanner.nextInt();
            }
            u.add(vector);
        }
        for (int i = 1; i <= r; i++) {
            int[] vector = new int[c];
            for (int j = 0; j < c; j++){
                vector[j] = scanner.nextInt();
            }
            v.add(vector);
        }
        boolean brk = false;
        for (int[] a : u) {
            for (int[] b : v) {
                if (ort(a,b)) {
                    System.out.println("yes");
                    brk = true;
                    break;
                }
            }
            if (brk) break;
        }
        if (!brk) System.out.println("no");
    }
}

//        7 3
//        0 0 0 0 0 1 1
//        1 0 1 0 0 0 1
//        1 0 1 1 0 1 1
//
//        0 1 0 0 1 0 1
//        0 0 1 1 0 0 1
//        0 1 0 0 0 0 0
//        Expected Output
//        yes
//        There will be 2 sets of vectors. If there is any set vector which inner product with any of the vectors from
//        the other set, then the answer is yes automatically (no need to keep checking)