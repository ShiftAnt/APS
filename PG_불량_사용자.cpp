#include <string>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;


int us, bs, ret = 0;;
bool vstd[8] = {0, };
vector<string> vec;
string temp[8];
map<string, int> mp;
void dfs(int a, vector<string>& user_id, vector<string>& banned_id) {
    int i, j;
    if (a == banned_id.size()) {
        for (i = 0; i < vec.size(); i++) {
            temp[i] = vec[i];
        }
        sort(temp, temp + vec.size());
        string rets = "";
        for (i = 0; i < vec.size(); i++) {
            rets += temp[i];
        }
        if (mp[rets] == 0) {
            ++ret;
            ++mp[rets];
        }
        return;
    }
    
    
    for (i = 0; i < us; i++) {
        if (!vstd[i]) {
            if (user_id[i].length() == banned_id[a].length()) {
                bool isOk = true;
                for (j = 0; j < user_id[i].length(); j++) {
                    if (banned_id[a][j] == '*') continue;
                    if (user_id[i][j] != banned_id[a][j]) {
                        isOk = false;
                        break;
                    }
                }
                if (isOk) {
                    vstd[i] = true;
                    vec.push_back(user_id[i]);
                    dfs(a + 1, user_id, banned_id);
                    vec.pop_back();
                    vstd[i] = false;
                }
            }
        }
        
    }
}

int solution(vector<string> user_id, vector<string> banned_id) {
    
    
    us = user_id.size(); bs = banned_id.size();
    
    int i, j, k;
    
    dfs(0, user_id, banned_id);
    
    
    
    return ret;
}