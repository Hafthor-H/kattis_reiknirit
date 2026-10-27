
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Bipartite;
import edu.princeton.cs.algs4.Graph;

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

public class breakingbad {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        HashMap<String, Integer> map = new HashMap<>();

        int item_num = io.getInt();
        String[] items = new String[item_num];
        Graph graph = new Graph(item_num);

        for (int i = 0; i < item_num; i++) {
            String item = io.getWord();

            items[i] = item;
            map.put(item, i);
        }

        int sussy_item_num = io.getInt();

        for (int i = 0; i < sussy_item_num; i++) {
            String sussy_1 = io.getWord();
            String sussy_2 = io.getWord();

            int v = map.get(sussy_1);
            int w = map.get(sussy_2);

            graph.addEdge(v, w);
        }

        Bipartite bp = new Bipartite(graph);

        if (!bp.isBipartite()) {
            io.println("impossible");
            io.close();
            return;
        }
        ArrayList<String> walter = new ArrayList<>();
        ArrayList<String> jesse = new ArrayList<>();

        for (int v = 0; v < item_num; v++) {
            if (bp.color(v)) {
                walter.add(items[v]);
            } else {
                jesse.add(items[v]);
            }
        }

        io.println(String.join(" ", walter));
        io.println(String.join(" ", jesse));
        io.flush();
        io.close();
    }
}