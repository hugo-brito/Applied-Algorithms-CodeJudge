import com.sun.source.tree.Tree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class improvedKruskalMST {

	/**
	 * https://itu.codejudge.net/apalg19f/exercise/9686/view
	 */

	private Set<Edge> mst;

	private long weight;

	public improvedKruskalMST(int n, List<Edge> edges){

		this.mst = new HashSet<>(n-1);
		this.weight = 0;

		Collections.sort(edges);
		Collections.reverse(edges);

		// add all edges to the PQ
//		PriorityQueue<Edge> edgePQ = new PriorityQueue<>(edges);

		// new Union-Find data structure with all vertices on their own set
		UF connectedComponents = new UF(n);

		while (connectedComponents.count() > 1){
			Edge e = edges.remove(edges.size()-1);
			int v = e.either(), w = e.other(v);
			if(!connectedComponents.connected(v,w)){

				connectedComponents.union(v,w);

				mst.add(e);

				this.weight += e.weight;

			}

		}

	}

	public void printMST() {

		TreeSet<Edge> edgesInMST = new TreeSet<>();
		edgesInMST.addAll(this.mst);

		int i = 0;
		for (Edge e : edgesInMST) {
			i++;
			System.out.printf("%2d%1s%6s%2d%6s%2d%11s%6d%n", i,":","v =", e.either(),"w =", e.other(e.either()),"Weight =", e.weight);
		}
		System.out.println("Weight of the MST = " + getWeight());
	}

	public long getWeight() {return weight;}

	public static void main(String[] args) throws IOException {

		try {
			Reader s = new Reader();

			// vertices
			int n = s.nextInt();

			// edges
			int m = s.nextInt();

			List<Edge> edges = new ArrayList<>(m);

			for (int i = 0; i < m; i++) {
				int v = s.nextInt();

				int w = s.nextInt();

				long weight = s.nextLong();

				Edge e = new Edge(v, w, weight);

				edges.add(e);

//				graph.addEdge(e);
			}

			improvedKruskalMST mst = new improvedKruskalMST(n, edges);

			System.out.println(mst.getWeight());

			mst.printMST();

		} catch (IOException e) {
			// reader
			e.printStackTrace();
		}
	}

	// significantly improved the running times!
	// for fast input reading
	static class Reader{
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader()
		{
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException	{
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1)
			{
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException	{
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
			{
				ret = ret * 10 + c - '0';
			}  while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');

			if (c == '.')
			{
				while ((c = read()) >= '0' && c <= '9')
				{
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

	/**
	 *  Took inspiration from "Algorithms, 4th Edition" by Robert Sedgewick and Kevin Wayne
	 */

	static class Edge implements Comparable<Edge>{

		// connected edges
		private final int v, w;

		// weight
		public final long weight;

		public Edge(int v, int w, long weight){
			this.v = v;
			this.w = w;
			this.weight = weight;
		}

		public int either() {return v;}

		public int other(int vertex){
			if (vertex == v) return w;
			else return v;
		}

		@Override
		public int compareTo(Edge that) {
			if      (this.weight < that.weight) return -1;
			else if (this.weight > that.weight) return  1;
			else                                return  0;
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

	class UF {

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
