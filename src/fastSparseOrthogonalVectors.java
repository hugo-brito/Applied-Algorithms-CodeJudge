import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class fastSparseOrthogonalVectors {
	/**
	 * https://itu.codejudge.net/apalg19f/exercise/9598/view
	 */

	public static void main(String[] args) throws IOException {

		Reader scanner = new Reader();

		//the size of the vectors
		scanner.nextInt();
		// just realized we never care for this number

		//number of vectors in each set
		int k = scanner.nextInt();

		//number of positions with a 1-entry. All other entries are 0
		int s = scanner.nextInt();

		// create a HashMap that maps vectors with non-zero index to a set containing their respective non-zero index-value
		Set<int[]> v = new HashSet<>();
		// there's no need to parse the index as we never operate with it

		// populate the set
		for (int i = 0; i < k; i++) {
			int[] indices = new int[s];
			for (int j = 0; j < s; j++) {
				indices[j] = scanner.nextInt();
			}
			Arrays.sort(indices);
			v.add(indices);
		}

		for (int i = 0; i < k; i++) {

			int[] w = new int[s];
			for (int j = 0; j < s; j++) {
				w[j] = scanner.nextInt();
			}

			for (int[] vIndices : v) {

				boolean allDiff = true;
				for (int l : w) {
					if (Arrays.binarySearch(vIndices, l) > -1) {
						allDiff = false;
						break;
					}
				}
				if (allDiff) {
					System.out.println("yes");
					return;
				}
			}

		}

		System.out.println("no");

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

	}

}
