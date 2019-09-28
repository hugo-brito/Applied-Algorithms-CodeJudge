import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class recursiveMatrixMultiplication {

	/**
	 * https://itu.codejudge.net/apalg19f/exercise/9663/view
	 */

	private static int n;

	private static int[][] multiply(int[][] a, int[][] b) {

		//introducing local variable that forces n to 2
		//the method now only works with matrices 2x2
		int n = 2;

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

	private static int[][] recursiveMultiply(int[][] a, int[][] b) throws Exception {

		int size = a.length;
		if (b.length != size) throw new Exception("Bad recursive call. a.length != b.length");

//		int[][] c = new int[size][size];

		while (size > 2) {
			return matrix(
					add(recursiveMultiply(submatrix(a,1),submatrix(b,1)),recursiveMultiply(submatrix(a,2),submatrix(b,3))),
					add(recursiveMultiply(submatrix(a,1),submatrix(b,2)),recursiveMultiply(submatrix(a,2),submatrix(b,4))),
					add(recursiveMultiply(submatrix(a,3),submatrix(b,1)),recursiveMultiply(submatrix(a,4),submatrix(b,3))),
					add(recursiveMultiply(submatrix(a,3),submatrix(b,2)),recursiveMultiply(submatrix(a,4),submatrix(b,4))));
		}

		if (size == 2) { return multiply(a, b); }

		return new int[size][size];
	}

	private static int[][] add(int[][] a, int[][] b){
		int[][] res = new int[a.length][a.length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				res[i][j] = a[i][j] + b[i][j];
			}
		}

		return res;
	}


	private static int[][] submatrix(int[][] a, int pos) {
		// given a matrix and a position, calculates the submatrix for that given position
		int[][] res = new int[a.length/2][a.length/2];

		int colStart = 0;
		int colEnd = 0;
		int rowStart = 0;
		int rowEnd = 0;

		if (pos < 3) {
			rowStart = 0;
			rowEnd = a.length/2;
		} else {
			rowStart = a.length/2;
			rowEnd = a.length; }

		if (pos % 2 != 0) {
			colStart = 0;
			colEnd = a.length/2;
		} else {
			colStart = a.length/2 + 1;
			colEnd = a.length; }

		for (int i = rowStart; i < rowEnd; i++) {
			for (int j = colStart; j < colEnd; j++) {
				res[i][j] = a[i][j];
			}
		}

		return res;
 	}

 	private static int[][] matrix (int[][] pos1, int[][] pos2, int[][] pos3, int[][] pos4) {
		// given 4 submatrices, rebuilds the matrix
	    int[][] res = new int[pos1.length*2][pos1.length*2];

	    // position 1
	    for (int i = 0; i < pos1.length; i++){
	    	for (int j = 0 ; j < pos1.length; j++){
	    		res[i][j] = pos1[i][j];
		    }
	    }

	    // position 2
	    for (int i = 0; i < pos2.length; i++){
		    for (int j = 0 ; j < pos2.length; j++){
			    res[i][j+pos2.length+1] = pos2[i][j];
		    }
	    }

	    // position 3
	    for (int i = 0; i < pos3.length; i++){
		    for (int j = 0 ; j < pos3.length; j++){
			    res[i+pos3.length+1][j] = pos3[i][j];
		    }
	    }

	    // position 4
	    for (int i = 0; i < pos4.length; i++){
		    for (int j = 0 ; j < pos4.length; j++){
			    res[i+pos4.length+1][j+pos4.length+1] = pos4[i][j];
		    }
	    }

	    return res;
    }

	private static int[] getRow(int r, int[][] m) {
		int[] res = new int[m.length];
		for (int i = 0; i < m.length; i++){
			res[i] = m[r][i];
		}
		return res;
	}

	private static int[] getColumn(int c, int[][] m) {
		int[] res = new int[m.length];
		for (int i = 0; i < m.length; i++){
			res[i] = m[i][c];
		}
		return res;
	}

	private static int rowXcolumn(int[] r, int[] c) {
		int res = 0;
		for (int i = 0; i < r.length; i++){
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

		try {

			BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

			n = Integer.parseInt(scanner.readLine());

			// get matrix A
			int[][] a = new int[n][n];

			for (int i = 0; i < n; i++) {
				String[] line = scanner.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					a[i][j] = Integer.parseInt(line[j]);
					// row column
				}
			}

//		System.out.println("\nMatrix A:");
//		printMatrix(a);

			scanner.readLine();

			// get matrix B
			int[][] b = new int[n][n];

			for (int i = 0; i < n; i++) {
				String[] line = scanner.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					b[i][j] = Integer.parseInt(line[j]);
					// row column
				}
			}

//		System.out.println("\nMatrix B:");
//		printMatrix(b);

			int[][] c = recursiveMultiply(a, b);

//		System.out.println("\nMatrix C:");

			printMatrix(c);

		} catch (IOException e) {
			// buffered reader exception
			e.printStackTrace();
		} catch (Exception e) {
			// recursive call exception
			e.printStackTrace();
		}
	}

}
