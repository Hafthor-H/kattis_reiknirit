import java.util.Arrays;
import java.util.HashMap;
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

public class vacationspace {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        Integer[] street = new Integer[n];
        Map<Integer, Integer> houses = new HashMap<>();

        if (n == 1) {
            System.out.println(1);
            return;
        }
        for (int i = 0; i < n; i++) {
            int house = io.getInt();
            houses.put(house, i);
            street[i] = house;
        }
        int best = -1;
        int bestIndex = Integer.MAX_VALUE;
        Arrays.sort(street);
        for (int i = 0; i < n; i++) {
            int distance;

            if (i == 0) {
                distance = street[i + 1] - street[i];

            } else if (i == n - 1) {
                distance = street[i] - street[i - 1];

            } else {
                int left = street[i] - street[i - 1];
                int right = street[i + 1] - street[i];

                distance = Math.min(left, right);
            }

            int index = houses.get(street[i]) + 1;

            if (distance > best || (distance == best && index < bestIndex)) {
                best = distance;
                bestIndex = index;
            }
        }

        System.out.println(bestIndex);
    }
}
