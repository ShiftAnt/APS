#include <string>
#include <vector>
#include <queue>
using namespace std;

vector<pair<int, int>> lik[100];
priority_queue<pair<int, int>> pq;
bool vstd[100] = {0, };

void add(int idx) {
    vstd[idx] = true;
    for (int i = 0; i < lik[idx].size(); i++) {
        if (!vstd[lik[idx][i].second]) pq.push(lik[idx][i]);
    }
}

int solution(int n, vector<vector<int>> costs) {
    int answer = 0;
    
    int i, cs = costs.size();
    
    for (i = 0; i < cs; i++) {
        lik[costs[i][0]].push_back({-costs[i][2], costs[i][1]});
        lik[costs[i][1]].push_back({-costs[i][2], costs[i][0]});
    }
    add(costs[0][0]);
    --n;
    while (n > 0 && !pq.empty()) {
        int a = pq.top().second;
        int b = pq.top().first;
        pq.pop();
        if (!vstd[a]) {
            add(a);
            --n;
            answer += -b;
        }
    }
    
    return answer;
}