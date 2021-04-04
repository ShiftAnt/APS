import java.util.*;


public class Main {
	public static void main(String[] args) {
		new Main().solution();
	}

	int R, C, N, MX = 100;
	String[] sP;
	char[][] P;
	void solution() {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		sP = new String[R];
		P = new char[R][C];
		int i, j;

		for (i = 0; i < R; i++) {
			sP[i] = sc.next();
			for (j = 0; j < C; j++) {
				P[i][j] = sP[i].charAt(j);
			}
		}

		N = sc.nextInt();

		for (i = 0; i < N; i++) {
			move(R - sc.nextInt(), i % 2 == 0);
		}
		
		for (i = 0; i < R; i++) {
			for (j = 0; j < C; j++) {
				System.out.print(P[i][j]);
			}
			System.out.print("\n");
		}
		sc.close();
	}
	int[] dr = {-1, 0, 0, 1};
	int[] dc = {0, -1, 1, 0};

	void move(int idx, boolean isL) {
		int cur = isL ? 0 : C - 1;
		int mv = isL ? 1 : -1;

		while (cur >= 0 && cur < C && P[idx][cur] != 'x') cur += mv;

		if (cur == -1 || cur == C) return;

		go(idx, cur);
	}

	void go(int y, int x) {
		int i, j;
		boolean[][] vstd = new boolean[R][C];
		vstd[y][x] = true;
		P[y][x] = '.';
		for (i = 0; i < 4; i++) {
			ArrayList<Integer> al = new ArrayList<>();
			int a = y + dr[i];
			int b = x + dc[i];

			if (check(a, b, vstd)) {
				vstd[a][b] = true;
				al.add(a * MX + b);
				for (int ix = 0; ix < al.size(); ix++) {
					int da = al.get(ix) / MX;
					int db = al.get(ix) % MX;

					for (j = 0; j < 4; j++) {
						int ta = da + dr[j];
						int tb = db + dc[j];

						if (check(ta, tb, vstd)) {
							vstd[ta][tb] = true;
							al.add(ta * MX + tb);
						}
					}
				}

				
				int mv = 0;
				boolean can = true;
				for (j = 0; j < al.size(); j++) {
					int da = al.get(j) / MX;
					int db = al.get(j) % MX;
					P[da][db] = '.';
				}
				
				while (can) {
					for (j = 0; j < al.size(); j++) {
						int da = al.get(j) / MX;
						int db = al.get(j) % MX;
						if (da + mv + 1 >= R || P[da + mv + 1][db] == 'x') {
							can = false;
							break;
						}
					}
					if (can) ++mv;
				}
				for (j = 0; j < al.size(); j++) {
					int da = al.get(j) / MX;
					int db = al.get(j) % MX;
					P[da + mv][db] = 'x';
				}
				if (mv > 0) break;
				
			}
		}
	}

	boolean check(int a, int b, boolean[][] vstd) {
		return (a >= 0 && a < R && b >= 0 && b < C && P[a][b] == 'x' && !vstd[a][b]);
	}
}