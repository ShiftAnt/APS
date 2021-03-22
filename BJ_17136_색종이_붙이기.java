import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		new Main().solution();
	}

	int[] five = {5, 5, 5, 5, 5};
	final int M = 10;
	int[][] P = new int[M][M];
	int ret = Integer.MAX_VALUE;
	void solution() {
		Scanner sc = new Scanner(System.in);

		int i, j, tot = 0;

		for (i = 0; i < M; i++) {
			for (j = 0; j < M; j++) {
				P[i][j] = sc.nextInt();
				if (P[i][j] == 1) ++tot;
			}
		}
		move(0, 0, tot);

		ret = ret == Integer.MAX_VALUE ? -1 : ret;

		System.out.println(ret);
	}

	void move(int idx, int sum, int tot) {
		int y = idx / 10;
		int x = idx % 10;

		if (idx > 99) {
			if (tot == 0) {
				ret = Math.min(ret, sum);
			}
			return;
		}
		if (P[y][x] == 0) {
			move(idx + 1, sum, tot);
			return;
		}


		int i;
		for (i = 0; i < 5; i++) {
			if (isFill(y, x, i) && five[i] > 0) {
				--five[i];
				fill(y, x, i, 0);
				move(idx + 1, sum + 1, tot - (i + 1) * (i + 1));
				fill(y, x, i, 1);
				++five[i];
			}
		}
	}
	void fill(int y, int x, int k, int num) {
		int i, j;
		for (i = y; i <= y + k; i++) {
			for (j = x; j <= x + k; j++) {
				P[i][j] = num;
			}
		}
	}
	boolean isFill(int y, int x, int k) {
		if (y + k > 9 || x + k > 9) return false;
		int i, j;
		for (i = y; i <= y + k; i++) {
			for (j = x; j <= x + k; j++) {
				if (P[i][j] == 0) return false;
			}
		}
		return true;
	}
}