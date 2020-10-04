import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Task2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList <Integer> integers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        input();
        main_function();
    }
    private static void input() throws IOException {
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++){
            integers.add(Integer.parseInt(reader.readLine()));
        }
    }

    private static void main_function() {
        integers.sort(Collections.reverseOrder());
        int count = 0;
        int count_three = 0;
        while (!integers.isEmpty()){
            int a = integers.get(0);
            integers.remove(0);
            if (!integers.isEmpty()) {
                while (a < 3) {
                    a+= integers.get(0);
                    integers.remove(0);
                    if (integers.isEmpty()){
                        break;
                    }
                }
            }
            if (a == 3){
                count_three++;
            }
            if (a >= 3) {
                count++;
            } else {
                if (a == 1 && count_three > 0){
                    count_three--;
                } else {
                    System.out.print(-1);
                    return;
                }
            }
        }
        output(count);
    }

    private static void output(int count){
        System.out.print(count);
    }
}
