#include <string>
#include <vector>
#include <map>
#define Long long long
using namespace std;

const int MX = 44445;
map<Long, int> dp;
int arr[] = {1, 11, 111, 1111, 11111};

vector<int> vec[9];

bool check(int n, int cnt) {
	if (cnt <= 0 || cnt >= MX) return false;
	if (dp[n] == 0 || dp[n] > cnt) {
		dp[n] = cnt;
		return true;
	}
	return false;
}

int solution(int N, int number) {
	int answer = 0;

	dp[N] = 1;
	vec[1].push_back(N);

	if (N == number) return 1;
	int i, j, m, n;
	for (i = 2; i <= 8; i++) {
		for (j = i / 2; j < i; j++) {
			int a = j, b = i - j;
			int as = vec[a].size(), bs = vec[b].size();

			for (m = 0; m < as; m++) {
				for (n = 0; n < bs; n++) {
					int cal = vec[a][m] + vec[b][n];
					if (check(cal, i)) {
						if (cal == number) return i;
						vec[i].push_back(cal);
					}
					cal = vec[a][m] - vec[b][n];
					if (check(cal, i)) {
						if (cal == number) return i;
						vec[i].push_back(cal);
					}
					cal = vec[a][m] * vec[b][n];
					if (check(cal, i)) {
						if (cal == number) return i;
						vec[i].push_back(cal);
					}
					if (vec[b][n] == 0) continue;
					cal = vec[a][m] / vec[b][n];
					if (check(cal, i)) {
						if (cal == number) return i;
						vec[i].push_back(cal);
					}
				}
			}
		}
		int cal = arr[i - 1] * N;
		if (cal == number) return i;
		if (dp[cal] == 0 || dp[cal] > i) {
			dp[cal] = i;
			vec[i].push_back(cal);
		}
	}


	return -1;
}