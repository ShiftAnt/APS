import java.util.*;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}
	int n;
	int[][][] BG;
	int ret = 0, sum = 0;
	int t, y, x;
	void run() {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();

		BG = new int[2][10][4];

		int i;
		for (i = 0; i < n; i++) {
			t = sc.nextInt();
			y = sc.nextInt();
			x = sc.nextInt();
			move(0);
			move(1);
		}
		System.out.println(ret);
		System.out.println(sum);
	}
	void move(int idx) {
		int[][] blo = {};

		
		if (idx == 0) {
			if (t == 1) blo = new int[][] {{x, y}};
			if (t == 2) blo = new int[][] {{x + 1, y}, {x, y}};
			else if (t == 3) blo = new int[][] {{x, y + 1}, {x, y}};
		} else {
			if (t == 1) blo = new int[][] {{y, x}};
			if (t == 2) blo = new int[][] {{y, x + 1}, {y, x}};
			else if (t == 3) blo = new int[][] {{y + 1, x}, {y, x}};
		}
		int len = blo.length;
		int i, j, k;

		L:
		while (true) {
			for (i = 0; i < len; i++) {
				if (++blo[i][0] > 9 || BG[idx][blo[i][0]][blo[i][1]] == 1) {
					--blo[i][0];
					if (i == 1 && blo[0][1] != blo[1][1]) --blo[0][0];
					break L;
				}
			}
		}
		sum += len;
		for (i = 0; i < len; i++) {
			BG[idx][blo[i][0]][blo[i][1]] = 1;
		}
		int down = 0, stt = 0;
		for (i = 0; i < len; i++) {
			int cnt = 0;
			for (j = 0; j < 4; j++) {
				cnt += BG[idx][blo[i][0]][j];
			}
			if (cnt == 4) {
				sum -= 4;
				++down;
				stt = Math.max(stt, blo[i][0]);
				for (j = 0; j < 4; j++) {
					BG[idx][blo[i][0]][j] = 0;
				}
			}
		}
		ret += down;
		if (down != 0) moveDown(idx, stt, down);
		down = 0;
		for (i = 4; i < 6; i++) {
			for (j = 0; j < 4; j++) {
				if (BG[idx][i][j] == 1) {
					++down;
					break;
				}
			}
		}
		if (down != 0) {
			for (i = 9; i > 9 - down; i--) {
				for (j = 0; j < 4; j++) {
					if (BG[idx][i][j] == 1) --sum;
				}
			}
			moveDown(idx, 9, down);
		}
	}
	void moveDown(int idx, int stt, int down) {
		int i, j;
		for (i = stt; i > 3; i--) {
			for (j = 0; j < 4; j++) {
				BG[idx][i][j] = BG[idx][i - down][j];
			}
		}
	}
}