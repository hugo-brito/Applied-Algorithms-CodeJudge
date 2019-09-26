import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class sparseInnerProduct {
	/**
	https://itu.codejudge.net/apalg19f/exercise/9596/view
	 **/

	public static void main(String[] args) {
		int res = 0;
		Scanner scanner = new Scanner(System.in);

		String[] nvw = scanner.nextLine().split(" ");

		//the size of the vectors
//		int n = Integer.parseInt(nvw[0]);
		// just realized we never care for this number

		//non-zero entries on the first vector
		int vNonZero = Integer.parseInt(nvw[1]);

		//non-zero entries on the second vector
		int wNonZero = Integer.parseInt(nvw[2]);

		// create a HashMap just to store the non-zero entries of v
		Map<String,Integer> v = new HashMap<>(vNonZero);
		// there's no need to parse the index as we never operate with it

		// populate the array
		for (int i = 0; i < vNonZero; i++) {
			String[] line = scanner.nextLine().split(":");
			v.put(line[0],Integer.parseInt(line[1]));
		}

//		System.out.println(v.toString());

		// skip the next line
		scanner.nextLine();

		for (int i = 0; i < wNonZero; i++) {
			String[] line = scanner.nextLine().split(":");
			if (v.containsKey(line[0])) {
				res = res + v.get(line[0]) * Integer.parseInt(line[1]);
			}
		}

		System.out.println(res);
	}
}
