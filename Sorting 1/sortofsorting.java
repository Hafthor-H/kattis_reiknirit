import java.util.ArrayList;
import java.util.List;

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

public class sortofsorting {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = -1;

        while (true) {
            n = io.getInt();
            if (n == 0) {
                break;
            }
            List<String> names = new ArrayList<>();
            for (int k = 0; k < n; k++) {
                String name = io.getWord();
                names.add(name);
            }

            // Algs4 code expects an array
            String[] a = names.toArray(new String[0]);

            int s = a.length;
            for (int i = 1; i < s; i++) {
                // Insert a[i] among a[i-1], a[i-2]....
                for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                    exch(a, j, j - 1);
                }
            }

            for (int h = 0; h < s; h++) {
                io.println(a[h]);
            }

        }
        io.close();
    }

    static List<String> kindaSort(List<String> l) {
        return l;
    }

    private static void exch(Comparable[] a, int k, int j) {
        Comparable t = a[k];
        a[k] = a[j];
        a[j] = t;
    }

    // Modified less method to return true if the first two characters in String v
    // are less than
    // the first two character in String w.
    private static boolean less(String v, String w) {
        return v.substring(0, 2).compareTo(w.substring(0, 2)) < 0;
    }
}