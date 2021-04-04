import java.util.*;


class Pair {
	public int y, x;
	public Pair(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	public static void main(String[] args) {
		new Main().solution();
	}

	int R, C;
	char[][] P;
	String[] sP;
	Pair[] se = new Pair[2];
	int dir = -1;
	HashMap<Character, HashMap<Integer, Integer>> hm = new HashMap<>();

	{
		HashMap<Integer, Integer> hp = new HashMap<>();
		hp.put(0, 0);
		hp.put(3, 3);
		hm.put('|', hp);
		
		hp = new HashMap<>();
		hp.put(2, 2);
		hp.put(1, 1);
		hm.put('-', hp);
		
		hp = new HashMap<>();
		hp.put(0, 0);
		hp.put(1, 1);
		hp.put(2, 2);
		hp.put(3, 3);
		hm.put('+', hp);
		
		hp = new HashMap<>();
		hp.put(0, 2);
		hp.put(1, 3);
		hm.put('1', hp);
		
		hp = new HashMap<>();
		hp.put(3, 2);
		hp.put(1, 0);
		hm.put('2', hp);
		
		hp = new HashMap<>();
		hp.put(3, 1);
		hp.put(2, 0);
		hm.put('3', hp);
		
		hp = new HashMap<>();
		hp.put(2, 3);
		hp.put(0, 1);
		hm.put('4', hp);
	}
	void solution() {
		Scanner sc = new Scanner(System.in);
		

		R = sc.nextInt();
		C = sc.nextInt();

		int i, j;
		P = new char[R][C];
		sP = new String[R];
		for (i = 0; i < R; i++) {
			sP[i] = sc.next();
			for (j = 0; j < C; j++) {
				P[i][j] = sP[i].charAt(j);
				if (P[i][j] == 'M') se[0] = new Pair(i, j);
				else if (P[i][j] == 'Z') se[1] = new Pair(i, j);
			}
		}
		boolean isFirst = true;
		for (i = 0; i < 4; i++) {
			int y = se[0].y;
			int x = se[0].x;
			if (check(y, x, i).y != -1) {
				isFirst = false;
				break;
			}
		}
		if (isFirst) {
			Pair tmp = se[0], tmp1 = se[1];

			se[0] = tmp1;
			se[1] = tmp;
		}

		for (i = 0; i < 4; i++) {
			int y = se[0].y;
			int x = se[0].x;
			Pair nxt = check(y, x, i);
			if (nxt.y != -1) {
				dir = i;
				break;
			}
		}

		move (se[0].y, se[0].x, dir);

		System.out.println((ret.y + 1) + " " + (ret.x + 1) + " " + P[ret.y][ret.x]);

		sc.close();
	}
	char[] blo = {'|', '-', '+', '1', '2', '3', '4'};
	Pair ret = new Pair(-1, -1);
	boolean move(int y, int x, int d) {
		y += dr[d];
		x += dc[d];
		if (y < 0 || y > R - 1 || x < 0 || x > C - 1) {
			return false;
		}
			if (ret.y != -1 && P[y][x] == '.') return false;
		if (P[y][x] == '.') {
			ret.y = y;
			ret.x = x;
			for (int i = 0; i < blo.length; i++) {
				if (hm.get(blo[i]).containsKey(d)) {
					P[y][x] = blo[i];
					if (move(y, x, hm.get(blo[i]).get(d))) return true;
				}
			}
			return false;
		}
		if (y == se[1].y && x == se[1].x) {
			return true;
		}
		if (!hm.get(P[y][x]).containsKey(d)) return false;
		d = hm.get(P[y][x]).get(d);


		return move(y, x, d);

	}
	int[] dr = {-1, 0, 0, 1};
	int[] dc = {0, -1, 1, 0};

	
	Pair check(int y, int x, int d) {
		y += dr[d];
		x += dc[d];

		if (y >= 0 && y < R && x >= 0 && x < C && P[y][x] != '.') {
			if (y == se[1].y && x == se[1].x) return new Pair(-1, -1);
			if (hm.get(P[y][x]).containsKey(d)) return new Pair(y, x);
		}
		return new Pair(-1, -1);

	}
}