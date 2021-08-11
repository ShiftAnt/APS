import java.util.*;

class Cube {
	char[] udfblr = new char[6];
	static int[][] tars = {
		{2, 4, 3, 5},   //flbr
		{2, 5, 3, 4},   //frbl
		{0, 5, 1, 4},   //urdl
		{0, 4, 1, 5},   //uldr
		{0, 2, 1, 3},   //ufdb
		{0, 3, 1, 2}	//ubdf
	};
	void rotate(int side, boolean dir) {
		
		int[] tar;
		char[] val = new char[4];
		if (dir) {
			tar = tars[side];
		} else {
			tar = new int[]{tars[side][3], tars[side][2], tars[side][1], tars[side][0]};
		}

		val[1] = udfblr[tar[0]];
		val[2] = udfblr[tar[1]];
		val[3] = udfblr[tar[2]];
		val[0] = udfblr[tar[3]];

		for (int i = 0; i < 4; i++) {
			udfblr[tar[i]] = val[i];
		}
	}
}
public class Main {
	public static void main(String[] args) {
		new Main().solution();
	}
	static HashMap<Character, Integer> hm = new HashMap<>();

	static {
		hm.put('U', 0);
		hm.put('D', 1);
		hm.put('F', 2);
		hm.put('B', 3);
		hm.put('L', 4);
		hm.put('R', 5);
	}
	int n;
	Cube[] cubes;

	/* \
	 0  1  2 
	 3  4  5 
	 6  7  8

	 9 10 11
	12 13 14
	15 16 17

	18 19 20 
	21 22 23
	24 25 26
	*/
	int[][] udfblr = {
		{0, 1, 2, 3, 4, 5, 6, 7, 8},
		{24, 25, 26, 21, 22, 23, 18, 19, 20},
		{6, 7, 8, 15, 16, 17, 24, 25, 26},
		{2, 1, 0, 11, 10, 9, 20, 19, 18},
		{0, 3, 6, 9, 12, 15, 18, 21, 24},
		{8, 5, 2, 17, 14, 11, 26, 23, 20}
	};

	Cube[] rot(Cube[] cur, boolean d) {
		if (d) return new Cube[]{
			cur[6], cur[3], cur[0],
			cur[7], cur[4], cur[1],
			cur[8], cur[5], cur[2]
		};
		return new Cube[]{
			cur[2], cur[5], cur[8],
			cur[1], cur[4], cur[7],
			cur[0], cur[3], cur[6]
		};
	}

	void cuing(String dir) {
		int tar = hm.get(dir.charAt(0));

		boolean d = dir.charAt(1) == '+';

		Cube[] cur = new Cube[9];
		int i;
		for (i = 0; i < 9; i++) {
			cur[i] = cubes[udfblr[tar][i]];
			cur[i].rotate(tar, d);
		}
		Cube[] nxt = rot(cur, d);

		for (i = 0; i < 9; i++) {
			cubes[udfblr[tar][i]] = nxt[i];
		}
	}

	char[] utor = {'w', 'y', 'r', 'o', 'g', 'b'};
	void solution() {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			cubes = new Cube[27];
			int i, j;
			for (i = 0; i < 27; i++) cubes[i] = new Cube();
			for (i = 0; i < 6; i++) {
				for (j = 0; j < 9; j++) {
					cubes[udfblr[i][j]].udfblr[i] = utor[i];
				}
			}
			for (i = 0; i < n; i++) {
				cuing(sc.next());
			}
			for (i = 0; i < 3; i++) {
				for (j = 0; j < 3; j++) {
					System.out.print(cubes[i * 3 + j].udfblr[0]);
				}
				System.out.print("\n");
			}
		}
		sc.close();
	}
}