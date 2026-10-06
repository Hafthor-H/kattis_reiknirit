import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}

public class planetaris {
    private static Comparable[] aux;

    public static void main(String[] args) {
        int counter = 0;
        Kattio io = new Kattio(System.in, System.out);
        int solarsystems = io.getInt();
        int atliships = io.getInt();

        Integer[] finniships = new Integer[solarsystems];

        for (int i = 0; i < solarsystems; i++) {
            finniships[i] = io.getInt();
        }

        // insertion(finniships);
        mergeSort(finniships);
        for (int i = 0; i < solarsystems; i++) {
            if (atliships > finniships[i]) {
                atliships -= finniships[i] + 1;
                counter += 1;
            }
        }
        io.println(counter);
        io.close();
    }

    private static void mergeSort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }

        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

}

// private static void insertion(int[] a) {
// int N = a.length;
// for (int i = 1; i < N; i++) {
// for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
// exch(a, j, j - 1);
// }
// }
// }

// private static void exch(int[] a, int i, int j) {
// int t = a[i];
// a[i] = a[j];
// a[j] = t;
// }
