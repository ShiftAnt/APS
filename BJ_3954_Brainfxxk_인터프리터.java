import java.util.*;

public class Main {
	public static void main(String[] args) {
		new Main().solution();
	}


	int[] inp = new int[3];
	String oper;
	int[] nxt;
	int chrIdx = 0;
	String Q;
	int[] P;
	void solution() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			inp[0] = sc.nextInt();
			inp[1] = sc.nextInt();
			inp[2] = sc.nextInt();
			chrIdx = 0;
			oper = sc.next();
			nxt = new int[inp[1]];

			Q = sc.next();
			P = new int[inp[0]];
			la = lb = Integer.MAX_VALUE;

			Stack<Integer> rhkfgh = new Stack<>();

			for (int i = 0; i < inp[1]; i++) {
				if (oper.charAt(i) == '[') rhkfgh.push(i);
				else if (oper.charAt(i) == ']') {
					int prv = rhkfgh.pop();
					nxt[prv] = i;
					nxt[i] = prv;
				}
			}

			if (move() == -1) {
				lb = nxt[la];
				System.out.println("Loops " + la + " " + lb);
			} else System.out.println("Terminates");
		}
	}
	int la, lb;

	int move() {
		int ord = 0;

		int idx = 0;
		int MX = inp[0];

		int ret = 0;
		while (ord < inp[1]) {
			++ret;
			char cur = oper.charAt(ord);

			switch (cur) {
				case '-':
					if (--P[idx] == -1) P[idx] = 255;
					break;
				case '+':
					if (++P[idx] == 256) P[idx] = 0;
					break;
				case '<':
					if(--idx == -1) idx = MX - 1;
					break;
				case '>':
					if (++idx == MX) idx = 0;
					break;
				case '[':
					if (P[idx] == 0) {
						ord = nxt[ord];
					}
					break;
				case ']':
					if (P[idx] != 0) {
						ord = nxt[ord];
					}
					break;
				case '.':
					break;
				case ',':
					if (chrIdx == Q.length()) P[idx] = 255;
					else P[idx] = Q.charAt(chrIdx++);
					break;
			}
			if (ret > 50000000) la = Math.min(la, ord);
			if (ret > 100000000) return -1;
			++ord;
		}

		return ret;
	}
}