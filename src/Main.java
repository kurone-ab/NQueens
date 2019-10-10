import java.util.Vector;

public class Main {
    static int N;
    static double cases, count;
    private static int[] chess;
    private static Vector<int[]> answers;

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }

    static Thread start() {
        cases = Math.pow(N, N);
        chess = new int[N];
        answers = new Vector<>();
        Thread thread = new Thread(() -> Main.search(chess, 0));
        thread.start();
        return thread;
    }

    private static void search(int[] chess, int row) {
        if (row == N) {
            answers.add(chess.clone());
            count++;
        } else {
            for (int i = 0; i < N; i++) {
                chess[row] = i;
                if (safe(chess, row)) {
                    search(chess, row + 1);
                } else count += Math.pow(N, N - row - 1);
            }
        }
    }

    private static boolean safe(int[] chess, int row) {
        for (int i = 0; i < row; i++) {
            if (chess[i] == chess[row] || Math.abs(i - row) == Math.abs(chess[i] - chess[row])) return false;
        }
        return true;
    }

    static void showResult(){
        new ResultWindow(answers).setVisible(true);
    }

}
