import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task1 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int n = 0, k = 0;
    private static final HashMap<String, Pair<Integer, Integer>> teams = new HashMap<>();
    private static final HashMap<String, Double> final_score = new HashMap<>();

    private static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws IOException {
        read_first_line();
        main_function();
        end();
    }

    private static void read_first_line() throws IOException {
        String s = reader.readLine();
        for (String s1 : s.split(" ")) {
            if (n == 0) {
                n = Integer.parseInt(s1);
            } else {
                k = Integer.parseInt(s1);
            }
        }
    }

    private static void main_function() throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String s = reader.readLine();
            strings.addAll(Arrays.asList(s.split(" ")));
            for (int j = 1; j < strings.size(); j++) {
                if (test_score(strings.get(j))) {
                    StringBuilder team1 = new StringBuilder();
                    StringBuilder team2 = new StringBuilder();
                    for (int t1 = 0; t1 < j; t1++) {
                        team1.append(strings.get(t1)).append(" ");
                    }
                    for (int t2 = j + 1; t2 < strings.size(); t2++) {
                        team2.append(strings.get(t2)).append(" ");
                    }
                    team1.deleteCharAt(team1.length() - 1);
                    team2.deleteCharAt(team2.length() - 1);
//                  System.out.println(team1 + " " + team2);
                    write_values(strings, team1.toString(), team2.toString(), j);
                }
            }
            strings.clear();
        }
    }

    private static boolean test_score(String s) {
        if (s.contains(":")) {
            return test_int(s.substring(s.indexOf(":") + 1)) && test_int(s.substring(0, s.indexOf(":")));
        }
        return false;
    }

    private static boolean test_int(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void write_values(ArrayList<String> strings, String team1, String team2, int j) {
        int result = decode_result(strings.get(j));
        int points1, points2;
        if (result == 1) {
            points1 = 3;
            points2 = 0;
        } else if (result == -1) {
            points1 = 0;
            points2 = 3;
        } else {
            points1 = 1;
            points2 = 1;
        }
        if (teams.containsKey(team1)) {
            teams.put(team1,
                    new Pair<>(teams.get(team1).getKey() + points1,
                            teams.get(team1).getValue() + 1));
        } else {
            teams.put(team1, new Pair<>(points1, 1));
        }
        if (teams.containsKey(team2)) {
            teams.put(team2,
                    new Pair<>(teams.get(team2).getKey() + points2,
                            teams.get(team2).getValue() + 1));
        } else {
            teams.put(team2, new Pair<>(points2, 1));
        }
    }

    private static int decode_result(String s) {
        Pair<Integer, Integer> pair = get_result(s);
        return pair.getValue().compareTo(pair.getKey());
    }

    private static Pair<Integer, Integer> get_result(String s) {
        return new Pair<>(Integer.parseInt(s.substring(s.indexOf(":") + 1)),
                Integer.parseInt(s.substring(0, s.indexOf(":"))));
    }

    private static void end() {
        add_other();
        count();
        sort();
    }

    private static void add_other() {
        int i = 0;
        String s = "UnknownTeam_";
        while (teams.size() < n) {
            teams.put((s + (char)('A' +  i)), new Pair<>(0, 0));
            i++;
        }
    }

    private static void count() {
        for (Map.Entry<String, Pair<Integer, Integer>> entry : teams.entrySet()) {
            if (entry.getValue().getValue() == 0) {
                final_score.put(entry.getKey(), (double) -1);
            } else {
                final_score.put(entry.getKey(), (double) entry.getValue().getKey() / (double) entry.getValue().getValue());
            }
        }
    }

    private static void sort() {
        List<Map.Entry<String, Double>> list = new ArrayList(final_score.entrySet());
        list.sort(new Comparator<Map.Entry<String, Double>>() {

            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        list.sort(new Comparator<Map.Entry<String, Double>>() {

            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if(o1.getValue().equals(o2.getValue())){
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return 0;
                }
            }
        });
        output(list);
    }

    private static void output (List<Map.Entry<String, Double>> list) {
        for (Map.Entry entry : list){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}