import java.util.ArrayList;
import java.util.Scanner;

public class 컨베이어_벨트_위의_로봇 {
	public static void main(String[] args) {
		new 컨베이어_벨트_위의_로봇().solution();
	}

	int n, k;
	class Belt {
		public boolean robot;
		public int A;
		public int idx;
	}
	Belt[] belts;
	ArrayList<Belt> al = new ArrayList<>();
	void solution() {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		k = sc.nextInt();

		belts = new Belt[n * 2];
		int i;

		for (i = 0; i < n * 2; i++) {
			Belt b = new Belt();
			b.robot = false;
			b.A = sc.nextInt();
			b.idx = i;
			belts[i] = b;
		}
		int n2 = 2 * n;
		int mv = 0;
		int ret = 0;
		Belt temp = belts[n2 - 1];
		while (mv < k) {
			++ret;
			Belt stt = belts[n2 - 1];
			for (i = n2 - 1; i >= 1; i--) {
				belts[i] = belts[i - 1];
				belts[i].idx = i;
			}
			belts[0] = stt;
			belts[0].idx = 0;
			for (i = 0; i < al.size(); i++) {
				int cur = al.get(i).idx;
				if (cur == n - 1) {
					belts[cur].robot = false;
					al.remove(i--);
					continue;
				}
				int nxt = (cur + 1) % n2;
				if (!belts[nxt].robot && belts[nxt].A > 0) {
					if (--belts[nxt].A == 0) ++mv;
					belts[cur].robot = false;
					al.remove(i--);
					if (nxt != n - 1) {
						al.add(++i, belts[nxt]);
						belts[nxt].robot = true;
					}

				}
			}
			if (!belts[0].robot && belts[0].A > 0) {
				if (--belts[0].A == 0) ++mv;
				belts[0].robot = true;
				al.add(belts[0]);
			}
		}
		System.out.println(ret);
	}
}
