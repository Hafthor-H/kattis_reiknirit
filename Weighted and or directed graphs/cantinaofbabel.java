import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.Digraph;

public class cantinaofbabel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Digraph g = new Digraph(n);
        Map<Integer, String[]> nameLang = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            String[] langs = new String[words.length - 1];

            for (int j = 0; j < langs.length; j++) {
                langs[j] = words[j + 1];
            }
            nameLang.put(i, langs);
        }

        for (int i = 0; i < n; i++) {
            String check = nameLang.get(i)[0];

            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                } else if (Arrays.asList(nameLang.get(j)).contains(check)) {
                    g.addEdge(i, j);
                }
            }
        }
        KosarajuSharirSCC scc = new KosarajuSharirSCC(g);

        int count = scc.count();

        Map<Integer, Integer> dict = new HashMap<>();

        for (int i = 0; i < count; i++) {
            dict.put(i, 0);
        }

        for (int i = 0; i < n; i++) {
            int id = scc.id(i);
            dict.put(id, dict.get(id) + 1);
        }

        int max = Collections.max(dict.values());
        System.out.println(n - max);
        scanner.close();
    }
}