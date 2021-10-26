import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	class Shark {
		int y, x;
	}
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	class Fish {
		int y, x, d;
		Fish(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
		Fish cloneFish() {
			return new Fish(y, x, d);
		}
		void move() {
			int i, j;
			for (i = 0; i < dr.length; i++) {
				int z = (dr.length + d - i) % dr.length;
				int a = y + dr[z];
				int b = x + dc[z];
				if (a >= 0 && a < n && b >= 0 && b < n && smell[a][b] == 0) {
					if (shark.y != a || shark.x != b) {
						y = a;
						x = b;
						d = z;
						return;
					}
				}
			}
		}

	}
	class Node {
		int num;
		int ord;
		Node(int num, int ord) {
			this.num = num;
			this.ord = ord;
		}
	}
	void insert() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (Fish fish : P[i][j]) {
					clone.add(fish.cloneFish());
				}
			}
		}
	}
	void moveFish() {
		Queue<Fish> mv = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (Fish fish : P[i][j]) {
					fish.move();
					mv.add(fish);
				}
				P[i][j].clear();
			}
		}
		while (!mv.isEmpty()) {
			Fish cur = mv.poll();
			P[cur.y][cur.x].add(cur);
		}
	}
	int[] rr = {-1, 0, 1, 0};
	int[] cc = {0, -1, 0, 1};

	void dfs(int idx, int y, int x, int num, int ord, Queue<Node> que, boolean[][] vstd) {
		if (idx == 3) {
			que.add(new Node(num, ord));
			return;
		}
		int i;
		for (i = 0; i < rr.length; i++) {
			int a = y + rr[i];
			int b = x + cc[i];
			if (a >= 0 && a < n && b >= 0 && b < n) {
				int nxt = vstd[a][b] ? num : num + P[a][b].size();
				boolean st = vstd[a][b];

				if (!st) vstd[a][b] = true;

				dfs(idx + 1, a, b, nxt, ord * 10 + i + 1, que, vstd);

				if (!st) vstd[a][b] = false;

			}
		}
	}

	void moveShark() {
		Queue<Node> que = new LinkedList<>();
		boolean[][] vstd = new boolean[n][n];
		int y = shark.y;
		int x = shark.x;
		int i, j;

		dfs(0, y, x, 0, 0, que, vstd);

		Node top = new Node(0, 99999);

		while (!que.isEmpty()) {
			Node cur = que.poll();
			if (top.num < cur.num) top = cur;
			else if (top.num == cur.num) {
				if (top.ord > cur.ord) top = cur;
			}
		}
		String ord = ((Integer) top.ord).toString();
		for (i = 0; i < 3; i++) {
			int d = ord.charAt(i) - '0' - 1;
			shark.y += rr[d];
			shark.x += cc[d];
			if (!P[shark.y][shark.x].isEmpty()) {
				smell[shark.y][shark.x] = 3;
			}
			P[shark.y][shark.x].clear();
		}
	}
	void moveSmell() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (smell[i][j] > 0) --smell[i][j];
			}
		}
	}
	void doClone() {
		while(!clone.isEmpty()) {
			Fish fish = clone.poll();
			P[fish.y][fish.x].add(fish);
		}
	}
	void move() {
		insert();
		moveFish();
		moveShark();
		moveSmell();
		doClone();
	}

	static final int n = 4;
	Queue<Fish> clone = new LinkedList<>();
	List<Fish>[][] P = new ArrayList[n][n];
	int[][] smell = new int[n][n];
	Shark shark = new Shark();
	{
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				P[i][j] = new ArrayList<>();
			}
		}
	}

	void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int i, j;
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			P[y][x].add(new Fish(y, x, d));
		}
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken()) - 1;
		int x = Integer.parseInt(st.nextToken()) - 1;
		shark.y = y;
		shark.x = x;

		int ret = 0;

		for (i = 0; i < s; i++) {
			move();
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				ret += P[i][j].size();
			}
		}
		bw.write(ret + "");
		bw.close();
		br.close();
	}
}