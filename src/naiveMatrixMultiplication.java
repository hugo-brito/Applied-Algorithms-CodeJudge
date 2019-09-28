import java.util.Scanner;

public class naiveMatrixMultiplication {

	/**
	 * https://itu.codejudge.net/apalg19f/exercise/9663/view
	 */

	private static int n;

	private static int[][] multiply(int[][] a, int[][] b) {
		int[][] c = new int[n][n];

		for (int i = 0; i < n; i++){ // row
			for (int j = 0; j < n; j++){ // column
				c[i][j] = rowXcolumn(getRow(i,a),getColumn(j,b));
				// [row] [column]
				// all combinations of a[1][..] x all combinations
				// of b[..][1]
			}
		}

		return c;
	}

	private static int[] getRow(int r, int[][] m) {
		int[] res = new int[n];
		for (int i = 0; i < n; i++){
			res[i] = m[r][i];
		}
		return res;
	}

	private static int[] getColumn(int c, int[][] m) {
		int[] res = new int[n];
		for (int i = 0; i < n; i++){
			res[i] = m[i][c];
		}
		return res;
	}

	private static int rowXcolumn(int[] r, int[] c) {
		int res = 0;
		for (int i = 0; i < n; i++){
			res = res + r[i]*c[i];
		}
		return res;
	}

	private static void printMatrix(int[][] c) {
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n-1; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.print(c[i][n-1] + "\n");
			// c[row][column]
		}

	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		n = Integer.parseInt(scanner.nextLine());

		int[][] a = new int[n][n];

		for (int i = 0; i < n; i++){
			String[] line = scanner.nextLine().split(" ");
			for (int j = 0; j < n; j++){
				a[i][j] = Integer.parseInt(line[j]);
				// row column
			}
		}

//		System.out.println("\nMatrix A:");
//		printMatrix(a);

		scanner.nextLine();

		int[][] b = new int[n][n];

		for (int i = 0; i < n; i++){
			String[] line = scanner.nextLine().split(" ");
			for (int j = 0; j < n; j++){
				b[i][j] = Integer.parseInt(line[j]);
				// row column
			}
		}

//		System.out.println("\nMatrix B:");
//		printMatrix(b);

		int[][] c = multiply(a,b);

//		System.out.println("\nMatrix C:");

		printMatrix(c);

	}

}
