import java.util.Arrays;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		new Main().solution();
	}
	int n, m, k;
	int[][] P;
	int[][] R;
	int[] tnduf;
	boolean[] vstd;
	int ret = Integer.MAX_VALUE;
	void solution() {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();

		P = new int[n][m];
		R = new int[k][3];
		tnduf = new int[k];
		vstd = new boolean[k];
		int i, j;

		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				P[i][j] = sc.nextInt();
			}
		}
		for (i = 0; i < k; i++) {
			R[i][0] = sc.nextInt();
			R[i][1] = sc.nextInt();
			R[i][2] = sc.nextInt();
			--R[i][0];
			--R[i][1];
			vstd[i] = true;
		}
		move(0);
		System.out.println(ret);
	}
	void move(int idx) {
		int i, j;

		if (idx == k) {
			int[][] tmp = new int[n][m];
			for (i = 0; i < n; i++) for (j = 0; j < m; j++) tmp[i][j] = P[i][j];

			for (i = 0; i < k; i++) {
				rot(i);
			}

			for (i = 0; i < n; i++) {
				int sum = 0;
				for (j = 0; j < m; j++) {
					sum += P[i][j];
				}
				ret = Math.min(sum, ret);
			}
			for (i = 0; i < n; i++) for (j = 0; j < m; j++) P[i][j] = tmp[i][j];

			return;
		}
		for (i = 0; i < k; i++) {
			if (vstd[i]) {
				tnduf[idx] = i;
				vstd[i] = false;
				move(idx + 1);
				vstd[i] = true;
			}
		}
	}


	int[] dr = {0, 1, 0, -1};
	int[] dc = {1, 0, -1, 0};


	void rot(int idx) {
		int y = R[tnduf[idx]][0];
		int x = R[tnduf[idx]][1];
		int s = R[tnduf[idx]][2];
		y -= s; x -= s;
		s *= 2;
		int[][] tmp = new int[s + 1][s + 1];
		int i, j;
		for (i = 0; i < s / 2; i++) {

			int a = i;
			int b = i;
			int c = 0;
			do {
				tmp[a + dr[c]][b + dc[c]] = P[y + a][x + b];
				a += dr[c];
				b += dc[c];
				if (a == i && b == s - i || a == s - i && b == s - i || a == s - i && b == i) ++c;
			} while(a != i || b != i);
		}
		tmp[s / 2][s / 2] = P[y + s / 2][x + s / 2];
		for (i = 0; i < s + 1; i++) {
			for (j = 0; j < s + 1; j++) {
				P[y + i][x + j] = tmp[i][j];
			}
		}
	}


}