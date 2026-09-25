import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

public class kreditkort {
    public static void main(String[] args) {
        Map<Integer, Integer> prices = Map.of(
                500, 500,
                1000, 1000,
                2000, 2000,
                5250, 5000,
                11000, 10000,
                24000, 20000);
        List<Integer> sortedKeys = new ArrayList<>(prices.keySet());
        Collections.sort(sortedKeys);

        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();

        if (prices.containsKey(n)) {
            io.println(prices.get(n));
        }

        if (n < 500) {
            io.println(500);
        }

        for (int i = 1; i < sortedKeys.size(); i++) {
            int prev = sortedKeys.get(i - 1);
            int key = sortedKeys.get(i);
            if (n > prev & n < key) {
                io.println(prices.get(key));
            }
        }
        io.close();
    }
}

// io.println("Prev: " + prev + " Key: " + key);