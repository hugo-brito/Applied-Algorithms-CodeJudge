import java.util.*;

public class sparseOrthogonalVectors {
	/**
	 * https://itu.codejudge.net/apalg19f/exercise/9598/view
	 */

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

		// create a HashMap that maps vectors with non-zero index to a set containing their respective non-zero index-value
		Set<int[]> v = new HashSet<>();
		// there's no need to parse the index as we never operate with it

		// populate the set
		for (int i = 0; i < k; i++) {
			String[] line = scanner.nextLine().split(" ");
			int[] indices = new int[s];
			for (int j = 0; j < s; j++) {
				indices[j] = Integer.parseInt(line[j]);
			}
			Arrays.sort(indices);
			v.add(indices);
		}

		// skip the next line
		scanner.nextLine();

		Map<String, Set<Integer>> w = new HashMap<>();

		boolean brk = false;

		for (int i = 0; i < k; i++) {
			String[] line = scanner.nextLine().split(" ");
			Set<Integer> wIndices = new TreeSet<>();
			for (int j = 0; j < s; j++) {
				wIndices.add(Integer.parseInt(line[j]));
			}
			for (int[] vIndices : v) {
				boolean allDiff = true; // works but exceeds time limit
				for (int l : vIndices) {
					if (wIndices.contains(l)) {
						allDiff = false;
					}
				}
				if (allDiff) {
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
