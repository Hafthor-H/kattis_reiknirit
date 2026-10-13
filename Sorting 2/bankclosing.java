import java.util.PriorityQueue;

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

public class bankclosing {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int N = io.getInt();
        int K = io.getInt();
        PriorityQueue<Customer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));
        for (int i = 0; i < K; i++) {
            Customer customer = new Customer(io.getInt(), i + 1);
            minHeap.add(customer);
        }
        for (int i = 0; i < N; i++) {
            Customer longest = minHeap.poll();
            int longest_idx = longest.index;
            io.println(longest_idx);
            io.flush();
            String judge = io.getWord();

            if (!judge.equals("DONE")) {
                int time = Integer.parseInt(judge);
                Customer customer = new Customer(time, longest_idx);
                minHeap.add(customer);
            }
        }
        io.println("DONE");
        io.flush();
    }

    static class Customer {
        int time;
        int index;

        Customer(int time, int index) {
            this.time = time;
            this.index = index;
        }
    }
}
