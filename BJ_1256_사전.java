import java.math.BigInteger;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }
    int n, m;
    long k;
    BigInteger[][][] dp;
    String answer = "";
    BigInteger BK;
    void find(int idx, int a, int z) {
        if (idx == n + m) return;
        if (dp[a][z][0].compareTo(BK) >= 0) {
            answer += 'a';
            if (a > 0) find(idx + 1, a - 1, z);
        } else {
            answer += 'z';
            BK = BK.add(dp[a][z][0].negate());
            if (z > 0) find(idx + 1, a, z - 1);
        }
    }
    void solution() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        dp = new BigInteger[n + 1][m + 1][2];
        int i, j;
        for (i = 1; i <= n; i++) {
            dp[i][0][0] = new BigInteger("1");
            dp[i][0][1] = new BigInteger("0");
        }
        for (i = 1; i <= m; i++) {
            dp[0][i][0] = new BigInteger("0");
            dp[0][i][1] = new BigInteger("1");
        }

        for (i = 1; i <= n; i++) {
            for (j = 1; j <= m; j++) {
                dp[i][j][0] = new BigInteger("0");
                dp[i][j][1] = new BigInteger("0");
                dp[i][j][0] = dp[i][j][0].add(dp[i - 1][j][0]).add(dp[i - 1][j][1]);
                dp[i][j][1] = dp[i][j][1].add(dp[i][j - 1][0]).add(dp[i][j - 1][1]);
            }
        }
        BK = BigInteger.valueOf(k);
        
        if (dp[n][m][0].add(dp[n][m][1]).compareTo(BK) < 0) {
            System.out.println(-1);
            return;
        }
        find(0, n, m);
        System.out.println(answer);
        sc.close();
    }
}