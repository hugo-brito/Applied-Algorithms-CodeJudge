import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class sparseOrthogonalVectors2 {

	/**
	 * https://itu.codejudge.net/apalg19f/exercise/9598/view
	 */

	public static boolean areOrthogonal(int[] v, int[] w, int s) {
		boolean hasChanged = false;
		boolean orthogonal = true;

		if (w[0] == 6) System.out.println("found 6!");

		for (int i = 0; i < s; i++) {

			for (int j = 0; j < s; j++) {

				if (v[i] == w[j]) {
					hasChanged = true;
					orthogonal = false;
					break;
				}
			}
			if (hasChanged) break;
		}

//		if (orthogonal) {
//			System.out.println("\nthe vectors:");
//			System.out.print("[ ");
//			for (int i = 0; i < s - 1; i++) System.out.print(v[i] + ", ");
//			System.out.print(v[s - 1] + " ]\n");
//
//			System.out.print("and\n[ ");
//			for (int i = 0; i < s - 1; i++) System.out.print(w[i] + ", ");
//			System.out.print(v[s - 1] + " ]\nare orthogonal\n");
//		}


		return orthogonal;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String[] nks = scanner.nextLine().split(" ");

		//the size of the vectors
//		int n = Integer.parseInt(nks[0]);
		// just realized we never care for this number

		//number of vectors in each set
		int k = Integer.parseInt(nks[1]);

		//number of positions with a 1-entry. All other entries are 0
		int s = Integer.parseInt(nks[2]);

		// create set of arrays with non-zero index to a set containing their respective non-zero index-value
		Set<int[]> v = new HashSet<>(k);

		// populate the 2 dimensional array
		for (int i = 0; i < k; i++) {
			String[] line = scanner.nextLine().split(" ");
			int[] vector = new int[s];
			for (int j = 0; j < s; j++) { vector[j] = Integer.parseInt(line[j]); }
			v.add(vector);
		}

		// skip the next line
		scanner.nextLine();

//		System.out.println();
//		for (int[] i : v) {
//			System.out.print("[ ");
//			for (int j = 0; j < s - 1; j++) System.out.print(i[j] + ", ");
//			System.out.print(i[s-1] + " ]\n");
//		}

//		[ 7, 9, 1, 2 ]
//		[ 8, 9, 7, 3 ]
//		[ 1, 2, 9, 4 ]
		boolean hasChanged = false;
		boolean orthogonal = false;

		for (int[] i : v) {
			String[] line = scanner.nextLine().split(" ");
			int[] w = new int[s];
			for (int j = 0; j < s; j++) {
				w[j] = Integer.parseInt(line[j]);
			}

			if (areOrthogonal(i, w, s)) {
				orthogonal = true;
				System.out.println("yes");
				break;

			}
		}

		if (!orthogonal) System.out.println("no");
	}
}
