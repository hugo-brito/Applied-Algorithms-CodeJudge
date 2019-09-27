import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class sparseOrthogonalVectors3 {

	public static void main(String[] args) {

		try {

			BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

			String[] nks = scanner.readLine().split(" ");

			//the size of the vectors
			int n = Integer.parseInt(nks[0]);
			// just realized we never care for this number

			//number of vectors in each set
			int k = Integer.parseInt(nks[1]);

			//number of positions with a 1-entry. All other entries are 0
			int s = Integer.parseInt(nks[2]);

			Set<Integer>[] v = new Set[s];

			for (int i = 0; i < k; i++) {
				Set<Integer> vi = new HashSet<>(k);
				String[] line = scanner.readLine().split(" ");
				for (int j = 0; j < s; j++) {
					vi.add(Integer.parseInt(line[j]));
				}
				v[k] = vi;
			}

			scanner.readLine();

			for (int i = 0; i < k; i++) { // each of w vectors
				String[] line = scanner.readLine().split(" ");

				for (int j = 0; j < s; j++) { // each number in w
					int l = Integer.parseInt(line[j]);
					for (Set<Integer> vi : v) {
						if (vi.contains(l)) {}
					}
				}

			}



		} catch (IOException e) {e.printStackTrace();}

	}
}
