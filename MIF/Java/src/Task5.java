import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task5 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int x = 0, y = 0;
    private static int[][] first, second;
    public static void main(String[] args) throws IOException {
        main_input();
        main_function();
    }
    private static void main_input() throws IOException {
        read_first_line();
        first = second = new int[x][y];
        for (int i = 0; i < x; i++){
            String s = reader.readLine();
            int j = 0;
            for (String s1 : s.split(" ")){
                first[i][j] = Integer.parseInt(s1);
                j++;
            }
        }
    }

    private static void read_first_line() throws IOException {
        String s = reader.readLine();
        for (String s1 :s.split(" ")){
            if (x == 0){
                x = Integer.parseInt(s1);
            } else {
                y = Integer.parseInt(s1);
            }
        }
    }

    private static void main_function(){
        test();
        count();
    }

    private static void test(){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (first[i][j] == 0){
                    second[i][j] = 0;
                } else if (test_1mushroom(i, j)){
                    second[i][j] = 1;
                } else if (test_3mushroom(i, j)){
                    second[i][j] = 3;
                } else {
                    second[i][j] = 2;
                }
            }
        }
    }

    private static boolean test_1mushroom(int x1, int y1){
        for (int i = x1 -1; i <= x1 + 1; i++){
            for (int j = y1 -1; j <= y1 + 1; j++){
                if ((i == x1 || j == y1) && !(i == x1 && j == y1)) {
                    if (i > 0 && j > 0 && i < x && j < y) {
                        if (first[i][j] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean test_3mushroom(int x1, int y1){
        for (int i = -3; i <= 3; i++){
            for (int j = -3; j <= 3; j++){
                if (Math.abs(i) + Math.abs(j) == 3){
                    if (x1 + i > 0 && y1 + j > 0 && x1 + i < x && y1 + j < y){
                        if (first[x1 + i][y1 + j] == 1){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static void count(){
        int count = 0;
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                count += second[i][j];
            }
        }
        output(count);
    }

    private static void output(int count){
        System.out.print(count);
    }
}
