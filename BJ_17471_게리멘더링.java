import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class 게리멘더링 {
	public static void main(String[] args) {
		new 게리멘더링().solution();
	}

	int n;
	int[] P;
	boolean[][] M;
	boolean[] vstd;
	void solution() {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		P = new int[n];
		int i, j;
		for (i = 0; i < n; i++) {
			P[i] = sc.nextInt();
		}
		M = new boolean[n][n];

		for (i = 0; i < n; i++) {
			int inp = sc.nextInt();
			for (j = 0; j < inp; j++) {
				int k = sc.nextInt();
				M[i][--k] = true;
				M[k][i] = true;
			}
		}

		vstd = new boolean[n];

		sel(0, 0);

		ret = ret == Integer.MAX_VALUE ? -1 : ret;
		System.out.println(ret);
	}
	int ret = Integer.MAX_VALUE;
	void sel(int idx, int num) {

		if (idx == n ) {
			go();

			return;
		}
		int i;

		vstd[idx] = true;
		sel(idx + 1, num + 1);
		vstd[idx] = false;
		sel(idx + 1, num);
	}

	void go() {
		HashSet<Integer> A = new HashSet<>(), B = new HashSet<>();
		int i;
		int sa = 0, sb = 0;
		for (i = 0; i < n; i++) {
			if (vstd[i]) {
				sa += P[i];
				A.add(i);
			}
			else {
				sb += P[i];
				B.add(i);
			}
		}
		if (check(A) && check(B)) {
			ret = Math.min(ret, Math.abs(sa - sb));
		}
	}
	boolean check(HashSet<Integer> AB) {
		if (AB.size() == 0 || AB.size() == n) return false;
		int stt = (int)AB.toArray()[0];
		AB.remove(stt);
		LinkedList<Integer> al = new LinkedList<>();
		al.add(stt);

		int i;
		while(!al.isEmpty()) {
			int k = al.poll();
			for (i = 0; i < n; i++) {
				if (M[k][i] && AB.contains(i)) {
					al.add(i);
					AB.remove(i);
				}
			}

		}
		return AB.isEmpty();
	}
}