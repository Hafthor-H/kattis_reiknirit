import java.util.Arrays;
import java.util.StringTokenizer;

import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.KruskalMST;
import edu.princeton.cs.algs4.Edge;

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

public class landline {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int buildings = io.getInt();
        int connections = io.getInt();
        int insecure = io.getInt();
        EdgeWeightedGraph g = new EdgeWeightedGraph(buildings);

        boolean[] insecure_vertex = new boolean[buildings];

        for (int i = 0; i < insecure; i++) {
            insecure_vertex[io.getInt() - 1] = true;
        }

        int[] cheapest = new int[buildings];
        Arrays.fill(cheapest, Integer.MAX_VALUE);

        int secureCount = buildings - insecure;
        int insecureEdge = Integer.MAX_VALUE;

        for (int i = 0; i < connections; i++) {
            int x = io.getInt() - 1;
            int y = io.getInt() - 1;
            int weight = io.getInt();

            if (!insecure_vertex[x] && !insecure_vertex[y]) {
                g.addEdge(new Edge(x, y, weight));

            } else if (insecure_vertex[x] && !insecure_vertex[y]) {
                cheapest[x] = Math.min(cheapest[x], weight);

            } else if (!insecure_vertex[x] && insecure_vertex[y]) {
                cheapest[y] = Math.min(cheapest[y], weight);

            } else {
                insecureEdge = Math.min(insecureEdge, weight);
            }
        }

        if (secureCount == 0) {
            if (buildings == 1) {
                io.println(0);
            } else if (buildings == 2 && insecureEdge != Integer.MAX_VALUE) {
                io.println(insecureEdge);
            } else {
                io.println("impossible");
            }

            io.flush();
            return;
        }

        KruskalMST mst = new KruskalMST(g);
        double totalCost = mst.weight();
        int mst_size = 0;

        for (Edge e : mst.edges()) {
            mst_size++;
        }

        if (secureCount - 1 != mst_size) {
            io.println("impossible");
            io.flush();
        } else {
            double insecure_total = 0;
            for (double i : cheapest) {
                if (i != Integer.MAX_VALUE) {
                    insecure_total += i;
                }
            }
            boolean huh = true;

            for (int i = 0; i < buildings; i++) {
                if (insecure_vertex[i]) {
                    if (cheapest[i] == Integer.MAX_VALUE) {
                        huh = false;
                        break;
                    }
                }
            }

            if (huh) {
                double total = insecure_total + totalCost;
                io.println(Math.round(total));
            } else {
                io.println("impossible");
            }

            io.flush();
        }

    }
}