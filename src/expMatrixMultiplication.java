import java.util.Arrays;

public class expMatrixMultiplication {

	/**
	 * Runs REPS times experiment with given arguments for comparing Matrix Multiplication Algorithm
	 * @param args
	 */

	// 1) algorithm (naive, recursive or strassen)
	// 2) running time
	// 3) size of matrix
	// 4) (optional) standard deviation on the average running time if you make more repetitions 🙂

	public static void main(String[] args) {

		// args[0] = n (size)
		int n = Integer.parseInt(args[0]);

		// args[1] = rangeStart
		int rangeStart = Integer.parseInt(args[1]);

		// args[2] = rangeEnd
		int rangeEnd = Integer.parseInt(args[2]);

		// args[3] = seed
		long seed = Long.parseLong(args[3]);

		// args[4] = REPS
		int REPS = Integer.parseInt(args[4]);

		// args[5] = header?
		boolean addHeader = args[5].toLowerCase().startsWith("y") || args[5].toLowerCase().startsWith("h");

		squareMatrixGenerator matrixGenerator = new squareMatrixGenerator(n, rangeStart, rangeEnd, seed);

		int[][] A = matrixGenerator.getA();
		int[][] B = matrixGenerator.getB();

		if (addHeader) { System.out.println("Algorithm,Running Time (ns),Matrix size (n),Valid?,Seed,Range Start (a),Range End (b)"); }

		for (int i = 0; i < REPS; i++){
			long naiveStart = System.nanoTime();
			int[][] naive = naiveMatrixMultiplication.naiveMultiplication(A,B);
			long naiveEnd = System.nanoTime();

			System.out.println("Naive," + (naiveEnd - naiveStart) + "," + n + ",true," + seed + "," + rangeStart + "," + rangeEnd);

			long recursiveStart = System.nanoTime();
			int[][] recursive = naiveMatrixMultiplication.naiveMultiplication(A,B);
			long recursiveEnd = System.nanoTime();

			System.out.println("Recursive," + (recursiveEnd - recursiveStart) + "," + n + "," + Arrays.deepEquals(naive,recursive) + "," + seed + "," + rangeStart + "," + rangeEnd);

			long strassenStart = System.nanoTime();
			int[][] strassen = naiveMatrixMultiplication.naiveMultiplication(A,B);
			long strassenEnd = System.nanoTime();

			System.out.println("Strassen," + (strassenEnd - strassenStart) + "," + n + "," + Arrays.deepEquals(naive,strassen) + "," + seed + "," + rangeStart + "," + rangeEnd);

		}
	}
}