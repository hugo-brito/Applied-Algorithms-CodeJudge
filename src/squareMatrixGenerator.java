import java.util.Random;
import java.util.Scanner;

public class squareMatrixGenerator {
	/**
	 https://itu.codejudge.net/apalg19f/exercise/9663/view

	 Produces 2 square matrices, A and B with size n. Optional seed can be used.
	 */

	private Random random;

	private int n;
	private int rangeStart;
	private int rangeEnd;

	private int[][] A;

	private int[][] B;

	/*
	 * getter methods
	 */
	public int[][] getA() {return A;}
	public int[][] getB() {return B;}
	public int getN() {return n;}

	public squareMatrixGenerator(int n, int rangeStart, int rangeEnd) {
		this.n = n;
		if (!isPowerOfTwo(n)) {
			System.err.println(n + " is not a power of 2. Therefore, the generated matrices cannot be with a recursive multiplication method.");
		}
		if (n % 2 != 0 || n < 0) {
			System.err.println("N must be even. Using " + Math.abs(n + 1) + " instead.");
			this.n = Math.abs(n + 1);
		}

		if (rangeEnd < rangeStart) {
			this.rangeStart = rangeEnd;
			this.rangeEnd = rangeStart;
		} else {
			this.rangeStart = rangeStart;
			this.rangeEnd = rangeEnd;
		}



		this.random = new Random();

		generate();

	}

	public squareMatrixGenerator(int n, int rangeStart, int rangeEnd, long seed) {
		this(n, rangeStart, rangeEnd);
		this.random.setSeed(seed);

		generate();

	}

	/* Function to check if x is power of 2*/
	private static boolean isPowerOfTwo(int n)
	{
		if(n==0)
			return false;

		return (int)(Math.ceil((Math.log(n) / Math.log(2)))) ==
				(int)(Math.floor(((Math.log(n) / Math.log(2)))));
	}

	private void generate() {
		this.A = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = this.next();
			}
		}

		this.B = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				B[i][j] = this.next();
			}
		}

	}

	private void printMatrix(int[][] c) {
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n-1; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.print(c[i][n-1] + "\n");
			// c[row][column]
		}

	}

	public void print() {

		System.out.println(n);

		printMatrix(A);

		System.out.println();

		printMatrix(B);

	}

	private int next(){
		return rangeStart + random.nextInt(-rangeStart + rangeEnd + 1);

	}

	public static void main(String[] args) {

		// args: n a b seed(optional)

		Scanner scanner = new Scanner(System.in);

		String[] line = scanner.nextLine().split(" ");

		squareMatrixGenerator matrixes = new squareMatrixGenerator(
				Integer.parseInt(line[0]),
				Integer.parseInt(line[1]),
				Integer.parseInt(line[2]));

		matrixes.print();

	}
}
