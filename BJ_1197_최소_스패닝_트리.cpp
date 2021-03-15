#include<iostream>
#include<vector>
#include<queue>
using namespace std;

vector<pair<int, int>> P[10000];
priority_queue<pair<int, int>> pq;
bool vstd[10000] = { 0, };
int v, e;

void insert(int idx) {
	for (int i = 0; i < P[idx].size(); i++) {
		if (!vstd[P[idx][i].first]) {
			pq.push({-P[idx][i].second, P[idx][i].first});
		}
	}
}
int main() {
	cin >> v >> e;

	int i, j, a, b, c;
	P[0].push_back({0, 0});
	for (i = 0; i < e; i++) {
		cin >> a >> b >> c;
		P[--a].push_back({ --b, c });
	}
	insert(0);
	vstd[0] = 1;
	int ret = 0;

	while (!pq.empty()) {
		int y = pq.top().first;
		int x = pq.top().second;
		pq.pop();
		if (vstd[x]) continue;
		ret += y;
		vstd[x] = 1;
		insert(x);
	}
	cout << -ret << endl;

	return 0;
}