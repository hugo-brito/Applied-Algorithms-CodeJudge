import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.*;

public class primMST {
	/**
	 * https://itu.codejudge.net/apalg19f/exercise/9686/view
	 *
	 * The MST starts at vertex index 0 and it is always connected.
	 * - Add a vertex to the tree. The edges are put in a separate data structure -- a min oriented PQ.
	 * - Add the edge with min weight to the MST by deleting min from the PQ and adding it to the MST
	 * - This adds another vertex -- the vertex in the other end of the newly added vertex
	 * -
	 * @param args
	 */

	private Map<Integer,Set<Edge>> graph;

	private int vertices;
	private int edges;

	private boolean[] inTheMST;
	private PriorityQueue<Edge> edgesInConsideration;

	private Set<Edge> mst;
	private long weight;

	public primMST(Map<Integer,Set<Edge>> edgeWeightedGraph, int m){

		this.graph = edgeWeightedGraph;

		this.vertices = this.graph.keySet().size();
		this.edges = m;

		this.mst = new HashSet<>(this.vertices-1);
		this.weight = 0;

		inTheMST = new boolean[this.vertices];

		edgesInConsideration = new PriorityQueue<>(m);

		addVertexToMST(0);

//		while (visited.size() < vertices.keySet().size()) {
		while (mst.size() < vertices - 1) {

			Edge shortestEdge = edgesInConsideration.remove();

			int v = shortestEdge.either(), w = shortestEdge.other(v);

			if (isInTheMST(v) && isInTheMST(w)) continue;
			else if (isInTheMST(v) && !isInTheMST(w)) addVertexToMST(w);
			else if (isInTheMST(w) && !isInTheMST(v)) addVertexToMST(v);

			mst.add(shortestEdge);

			this.weight += shortestEdge.weight;

		}
	}

	private void addVertexToMST(int vertex){
		if (isInTheMST(vertex)) System.err.println("Vertex " + vertex + " is already in the MST");

		edgesInConsideration.addAll(graph.get(vertex));
		inTheMST[vertex] = true;

	}

	private boolean isInTheMST(int vertex){return inTheMST[vertex];}

	public long getWeight() {return weight;}

	public void printMST() {

		TreeSet<Edge> edgesInMST = new TreeSet<>();
		edgesInMST.addAll(this.mst);

		long accWeight = 0;
		int i = 0;
		for (Edge e : edgesInMST) {
			i++;
			accWeight = accWeight + e.weight;
			System.err.printf("%2d%1s%6s%3d%6s%3d%11s%7d%23s%8d%n", i, ":", "v=", e.either(), "w=", e.other(e.either()), "Weight=", e.weight, "Accumulated Weight=",accWeight);
		}
		System.err.println("Weight of the MST = " + accWeight);
	}


	public static void main(String[] args) throws IOException {

		Reader s = new Reader();

		// vertices
		int n = s.nextInt();

		// edges
		int m = s.nextInt();

		Map<Integer, Set<Edge>> vertices = new TreeMap<>();

		// had to do this nasty trick because there are vertices without outgoing vertices
		// despite the fact that this is an undirected graph
		for (int i = 0; i < n; i++) { vertices.put(i,new HashSet<>()); }

		for (int i = 0; i < m; i++) {
			int v = s.nextInt();
			// vertex

			int w = s.nextInt();
			// vertex

			long weight = s.nextLong();

			Edge e = new Edge(v, w, weight);

//			if (!vertices.containsKey(v)) {
//				vertices.put(v, new HashSet<>());
//			}

			vertices.get(v).add(e);


		}

		primMST mst = new primMST(vertices, m);

		mst.printMST();

		System.out.println(mst.getWeight());

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

	// for extremely fast input reading
	/*
	 * https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
	 */
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
}


