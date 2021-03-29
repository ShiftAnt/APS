import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_15591_MooTube {

	public static void main(String[] args) {
		new BJ_15591_MooTube().solution();
	}

	int n, q;
	class Node {
		public int a, b;
		public Node(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	ArrayList<Node>[] al;
	void solution() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		q = sc.nextInt();
		al = new ArrayList[n];
		int i;
		for (i = 0; i < n; i++) al[i] = new ArrayList<>();
		for (i = 0; i < n - 1; i++) {
			int p, q, r;
			p = sc.nextInt();
			q = sc.nextInt();
			r = sc.nextInt();
			--p; --q;

			al[p].add(new Node(q, r));
			al[q].add(new Node(p, r));

		}

		for (i = 0; i < q; i++) {
			int k, v;
			k = sc.nextInt();
			v = sc.nextInt();
			--v;
			boolean[] vstd = new boolean[n];
			vstd[v] = true;
			Queue<Node> que = new LinkedList<>();
			que.add(new Node(v, Integer.MAX_VALUE));
			int ret = 0;
			while (!que.isEmpty()) {
				int idx = que.peek().a;
				int b = que.poll().b;

				if (idx != v && b >= k) ++ret;

				for (int j = 0; j < al[idx].size(); j++) {
					int y = al[idx].get(j).a;
					int x = al[idx].get(j).b;
					if (!vstd[y]) {
						vstd[y] = true;
						que.add(new Node(y, Math.min(b, x)));
					}
				}

			}
			System.out.println(ret);
		}

	}
}