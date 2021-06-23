#include <string>
#include <vector>
#include <map>
using namespace std;

map<string, int> mp;
vector<int> par;
vector<int> answer;

void dfs(int idx, int cost) {
    if (idx != 0) {
        answer[idx - 1] += (cost - cost / 10);
        dfs(par[idx], cost / 10);
    }
}

vector<int> solution(vector<string> enroll, vector<string> referral, vector<string> seller, vector<int> amount) {
    
    
    int i;
    par.push_back(0);
    
    for (i = 0; i < enroll.size(); i++) {
        mp[enroll[i]] = i + 1;
        par.push_back(0);
        answer.push_back(0);
    }
    
    for (i = 0; i < referral.size(); i++) {
        if (referral[i] != "-") {
            par[mp[enroll[i]]] = mp[referral[i]];
        }
    }
    
    for (i = 0; i < seller.size(); i++) {
        dfs(mp[seller[i]], amount[i] * 100);
    }
    
    return answer;
}