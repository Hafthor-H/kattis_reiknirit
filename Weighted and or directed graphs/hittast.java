import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;

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

public class hittast {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();// locations
        int m = io.getInt();// Travel options

        EdgeWeightedGraph Ag = new EdgeWeightedGraph(n);
        EdgeWeightedGraph Bg = new EdgeWeightedGraph(n);
        int[] lodgings = new int[n];
        for (int i = 0; i < n; i++) {
            lodgings[i] = io.getInt();
        }
        for (int i = 0; i < m; i++) {
            int u = io.getInt() - 1;
            int v = io.getInt() - 1;
            int a = io.getInt();
            int b = io.getInt();
            Ag.addEdge(new Edge(u, v, a));
            Bg.addEdge(new Edge(u, v, b));

        }
        DijkstraUndirectedSP Asp = new DijkstraUndirectedSP(Ag, 0);
        DijkstraUndirectedSP Bsp = new DijkstraUndirectedSP(Bg, n - 1);

        double[] cheapest = new double[n];
        for (int i = 0; i < lodgings.length; i++) {
            cheapest[i] = Asp.distTo(i) + Bsp.distTo(i) + lodgings[i];
        }
        double min = Arrays.stream(cheapest).min().getAsDouble();

        io.println((int) min);
        io.flush();
    }
}
