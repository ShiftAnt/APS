import java.util.*;

public class Main {
	public static void main(String[] args) {
		new Main().solution();
	}
	int[][] dp;
	int n;
	int dfs(int num, int ord) {
		if (num > n) return 0;
		if (dp[num][ord] != 0) return dp[num][ord];

		int dirtn = (int)Math.sqrt(num);
		int ret = 2 - ord;
		for (int i = 1; i <= dirtn; i++) {
			if (num % i == 0) {
				if (dfs(num + i, 1 - ord) == ord + 1) ret = ord + 1;
				if (dfs(num + num / i, 1 - ord) == ord + 1) ret = ord + 1;
			}
		}
		return dp[num][ord] = ret;
	}
	void solution() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		dp = new int[n + 1][2];
		dp[n][0] = 2;
		dp[n][1] = 1;
		dfs(1, 0);
		System.out.println(dp[1][0] == 1 ? "Kali" : "Ringo");

		sc.close();
	}
}