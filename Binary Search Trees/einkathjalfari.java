import edu.princeton.cs.algs4.RedBlackBST;

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

public class einkathjalfari {
    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> rBst = new RedBlackBST<>();
        Kattio io = new Kattio(System.in, System.out);

        int q = io.getInt();

        for (int i = 0; i < q; i++) {
            String command = io.getWord();

            if (command.equals("A")) {
                int time = io.getInt();
                rBst.put(time, 1);
            } else if (command.equals("D")) {
                int time = io.getInt();
                if (rBst.contains((time))) {
                    rBst.delete(time);
                }
            } else {
                int start = io.getInt();
                int end = io.getInt();
                int total = end - start + 1;
                int booked = rBst.size(start, end);

                io.println(total - booked);
                io.flush();

            }
        }
    }
}
