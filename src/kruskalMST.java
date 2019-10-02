import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class kruskalMST {

	/*
	 * Edge by edge.
	 * 1) Sort edges
	 * 2) Add the lowest edge IF it doesn't add a cycle
	 * 3) Finished when all vertices are connected
	 */

	long weight;

	public void buildMST(int n){
		UF sets = new UF(n);
	}

	public long getWeight() {return weight;}


	public static void main(String[] args) {

		try {

			BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

			String[] nm = scanner.readLine().split(" ");

			int n = Integer.parseInt(nm[0]);
			// vertices

			int m = Integer.parseInt(nm[1]);
			// edges


		} catch (IOException e) {
			// buffered reader exception
			e.printStackTrace();
		}
	}

	/**
	 *  Taken from "Algorithms, 4th Edition" by Robert Sedgewick and Kevin Wayne
	 *
	 *
	 *  This implementation uses weighted quick union by rank with path compression
	 *  by halving.
	 *  Initializing a data structure with <em>n</em> sites takes linear time.
	 *  Afterwards, the <em>union</em>, <em>find</em>, and <em>connected</em>
	 *  operations take logarithmic time (in the worst case) and the
	 *  <em>count</em> operation takes constant time.
	 *  Moreover, the amortized time per <em>union</em>, <em>find</em>,
	 *  and <em>connected</em> operation has inverse Ackermann complexity.
	 *
	 *  <p>
	 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
	 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
	 *
	 *  @author Robert Sedgewick
	 *  @author Kevin Wayne
	 */

	private class UF {

		private int[] parent;  // parent[i] = parent of i
		private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
		private int count;     // number of components

		/**
		 * Initializes an empty union–find data structure with {@code n} sites
		 * {@code 0} through {@code n-1}. Each site is initially in its own
		 * component.
		 *
		 * @param n the number of sites
		 * @throws IllegalArgumentException if {@code n < 0}
		 */
		public UF(int n) {
			if (n < 0) throw new IllegalArgumentException();
			count = n;
			parent = new int[n];
			rank = new byte[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				rank[i] = 0;
			}
		}

		/**
		 * Returns the component identifier for the component containing site {@code p}.
		 *
		 * @param p the integer representing one site
		 * @return the component identifier for the component containing site {@code p}
		 * @throws IllegalArgumentException unless {@code 0 <= p < n}
		 */
		public int find(int p) {
			validate(p);
			while (p != parent[p]) {
				parent[p] = parent[parent[p]];    // path compression by halving
				p = parent[p];
			}
			return p;
		}

		/**
		 * Returns the number of components.
		 *
		 * @return the number of components (between {@code 1} and {@code n})
		 */
		public int count() {
			return count;
		}

		/**
		 * Returns true if the the two sites are in the same component.
		 *
		 * @param p the integer representing one site
		 * @param q the integer representing the other site
		 * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
		 * {@code false} otherwise
		 * @throws IllegalArgumentException unless
		 *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
		 */
		public boolean connected(int p, int q) {
			return find(p) == find(q);
		}

		/**
		 * Merges the component containing site {@code p} with the
		 * the component containing site {@code q}.
		 *
		 * @param p the integer representing one site
		 * @param q the integer representing the other site
		 * @throws IllegalArgumentException unless
		 *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
		 */
		public void union(int p, int q) {
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) return;

			// make root of smaller rank point to root of larger rank
			if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
			else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
			else {
				parent[rootQ] = rootP;
				rank[rootP]++;
			}
			count--;
		}

		// validate that p is a valid index
		private void validate(int p) {
			int n = parent.length;
			if (p < 0 || p >= n) {
				throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
			}
		}
	}

}
