import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().solution();
    }

    int n;
    String P;
    boolean[] vstd;
    int[] num;
    char[] oper;
    int ret = Integer.MIN_VALUE;
    void solution() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        P = sc.next();
        int i;
        num = new int[(n + 1) / 2];
        vstd = new boolean[n / 2];
        oper = new char[n / 2];
        for (i = 0; i < n; i++) {
            if (i % 2 == 0) {
                num[i / 2] = P.charAt(i) - '0';
            } else {
                oper[i / 2] = P.charAt(i);
            }
        }

        cal(0);
        System.out.println(ret);
    }

    void cal(int idx) {
        if (idx >= n / 2 - 1) {
            ret = Math.max(ret, move());
            return;
        }

        vstd[idx] = true;
        cal(idx + 2);
        vstd[idx] = false;
        cal(idx + 1);
    }

    int move() {
        int ret = num[0];
        for (int i = 0; i < oper.length; i++) {
            if (!vstd[i]) {
                ret = cc(ret, oper[i], num[i + 1]);
            } else {
                ret = cc(ret, oper[i], cc(num[i + 1], oper[i + 1], num[i + 2]));
                ++i;
            }
        }
        return ret;
    }

    int cc(int a, char op, int b) {
        if (op == '-') return a - b;
        else if (op == '+') return a + b;
        else return a * b;
    }
}
