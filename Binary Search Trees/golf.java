import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;
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

public class golf {
    public static void main(String[] args) {
        Map<String, Long> players = new HashMap<>();
        RedBlackBST<scoreNames, Long> scoreboard = new RedBlackBST<>();

        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int q = io.getInt();

        for (int i = 0; i < n; i++) {
            String player = io.getWord();
            scoreNames score_name = new scoreNames(0, player);

            players.put(player, 0l);
            scoreboard.put(score_name, 0l);
        }

        for (int i = 0; i < q; i++) {
            String command = io.getWord();

            if (command.equals("!")) {
                int k = io.getInt();
                for (int j = 0; j < k; j++) {
                    String player = io.getWord();
                    long score = io.getInt();

                    long old_score = players.get(player);
                    long new_score = old_score + score;

                    scoreNames old_score_name = new scoreNames(old_score, player);
                    scoreNames new_score_name = new scoreNames(new_score, player);

                    players.put(player, new_score);
                    scoreboard.delete(old_score_name);
                    scoreboard.put(new_score_name, 0l);
                }
            } else {
                String player = io.getWord();
                long score = players.get(player);
                scoreNames temp = new scoreNames(score, "");
                int rank = scoreboard.rank(temp);
                int position = rank + 1;
                // System.out.println(position + " " + score);
                io.println(position + " " + score);
                io.flush();
            }
        }
        io.close();
    }

    static class scoreNames implements Comparable<scoreNames> {
        long score;
        String player;

        scoreNames(long score, String player) {
            this.score = score;
            this.player = player;
        }

        @Override
        public int compareTo(scoreNames other) {
            int comparison = Long.compare(this.score, other.score);

            if (comparison != 0) {
                return comparison;
            }
            return this.player.compareTo(other.player);
        }
    }

}
