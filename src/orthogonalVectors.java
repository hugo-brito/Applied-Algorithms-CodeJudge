import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

public class orthogonalVectors {
    /**
     * https://itu.codejudge.net/apalg19f/exercise/9165/view
     **/

    public static boolean ort (int[] u, int[] v) {
        int prod = 0;
        for (int i = 0; i < u.length; i++) {
            if (u[i] != 0 && v[i] != 0) return false;
            prod = prod + u[i] * v[i];
        }
        return prod == 0;
    }

    public static void main(String[] args) throws IOException {
        Reader scanner = new Reader();

        int c = scanner.nextInt();
        int r = scanner.nextInt();
        HashSet<int[]> u = new HashSet<>();
        HashSet<int[]> v = new HashSet<>();

        for (int i = 1; i <= r; i++) {
            int[] vector = new int[c];
            for (int j = 0; j < c; j++){
                vector[j] = scanner.nextInt();
            }
            u.add(vector);
        }
        for (int i = 1; i <= r; i++) {
            int[] vector = new int[c];
            for (int j = 0; j < c; j++){
                vector[j] = scanner.nextInt();
            }
            v.add(vector);
        }
        boolean brk = false;
        for (int[] a : u) {
            for (int[] b : v) {
                if (ort(a,b)) {
                    System.out.println("yes");
                    brk = true;
                    break;
                }
            }
            if (brk) break;
        }
        if (!brk) System.out.println("no");
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

//        7 3
//        0 0 0 0 0 1 1
//        1 0 1 0 0 0 1
//        1 0 1 1 0 1 1
//
//        0 1 0 0 1 0 1
//        0 0 1 1 0 0 1
//        0 1 0 0 0 0 0
//        Expected Output
//        yes
//        There will be 2 sets of vectors. If there is any set vector which inner product with any of the vectors from
//        the other set, then the answer is yes automatically (no need to keep checking)