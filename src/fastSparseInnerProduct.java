import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class fastSparseInnerProduct {
	/**
	 https://itu.codejudge.net/apalg19f/exercise/9596/view
	 **/

	public static void main(String[] args) throws IOException {

		int res = 0;

		Reader scanner = new Reader();

		//the size of the vectors
		int n = scanner.nextInt();
		// just realized we never care for this number

		//non-zero entries on the first vector
		int vNonZero = scanner.nextInt();

		//non-zero entries on the second vector
		int wNonZero = scanner.nextInt();

		// create a HashMap just to store the non-zero entries of v
		Map<String,Integer> v = new HashMap<>(vNonZero);
		// there's no need to parse the index as we never operate with it

		// populate the array
		for (int i = 0; i < vNonZero; i++) {
			String[] line = scanner.readLine().split(":");
			v.put(line[0],Integer.parseInt(line[1]));
		}

		scanner.readLine();

		for (int i = 0; i < wNonZero; i++) {
			String[] line = scanner.readLine().split(":");
			if (v.containsKey(line[0])) {
				res = res + v.get(line[0]) * Integer.parseInt(line[1]);
			}
		}

		System.out.println(res);
	}

	// significantly improved the running times!
	// for fast input reading
	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

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

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
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
