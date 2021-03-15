#include<iostream>
#include<string>
#include<queue>
using namespace std;

int r, c;
string P[50];
int dr[] = { -1, 0, 0, 1 };
int dc[] = { 0, -1, 1, 0 };

queue<pair<int, int>> q[2];

int bfs() {
	int ret = 0;
	while (!q[1].empty()) {
		++ret;

		for (int t = 0; t < 2; t++) {
			int siz = q[t].size();
			for (int k = 0; k < siz; k++) {
				int y = q[t].front().first;
				int x = q[t].front().second;

				for (int i = 0; i < 4; i++) {
					int a = y + dr[i];
					int b = x + dc[i];

					if (a >= 0 && a < r && b >= 0 && b < c) {
						if (t == 1) {
							if (P[a][b] == 'D') return ret;
							if (P[a][b] == '.') {
								P[a][b] = '*';
								q[t].push({ a, b });
							}
						}
						if (t == 0) {
							if (P[a][b] == '.' || P[a][b] == 'S') {
								P[a][b] = '*';
								q[t].push({ a, b });
							}
						}
					}
				}

				q[t].pop();
			}

		}

	}


	return -1;
}

int main() {
	scanf("%d %d", &r, &c);
	int i, j;
	for (i = 0; i < r; i++) {
		cin >> P[i];
		for (j = 0; j < c; j++) {
			if (P[i][j] == 'S') {
				q[1].push({ i, j });
			}
			else if (P[i][j] == '*') {
				q[0].push({ i, j });
			}
		}
	}

	int ret = bfs();

	if (ret == -1) cout << "KAKTUS" << endl;
	else cout << ret << endl;



	return 0;
}