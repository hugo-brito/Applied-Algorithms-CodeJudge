import java.util.Scanner;

public class vectorProduct {
    /**
     * https://itu.codejudge.net/apalg19f/exercise/9156/view
     **/
        public static void main(String[] args) {
            int res = 0;
            Scanner scanner = new Scanner(System.in);
            int n = Integer.parseInt(scanner.nextLine());
            String[] u = scanner.nextLine().split(" ");
            String[] v = scanner.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                res = res + Integer.parseInt(u[i]) * Integer.parseInt(v[i]);
            }
            System.out.println(res);
        }
}