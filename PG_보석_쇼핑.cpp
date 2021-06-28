#include <string>
#include <vector>
#include <map>
using namespace std;

int gemCnt = 0;
map<string, int> mp;
vector<int> vec;
pair<int, int> ret;
int mi = 2100000000;
void move(int stt, int end, vector<string> &gems, int cnt) {
    
    if (cnt == gemCnt) {
        if (mi > end - stt + 1) {
            mi = end - stt + 1;
            ret = {stt + 1, end + 1};
        }
        else if (mi == end - stt + 1) {
            if (ret.first > stt + 1) {
                ret = {stt + 1, end + 1};
            }
        }
    }
    
    int a = mp[gems[stt]] - 1;
    
    
    if (vec[a] > 1) {
        --vec[a];
        move(stt + 1, end, gems, cnt);
    } else {
        if (gems.size() - 1 == end) return;
        int b = mp[gems[end + 1]] - 1;
        ++vec[b];
        if (vec[b] == 1) {
            move(stt, end + 1, gems, cnt + 1);
        } else {
            move(stt, end + 1, gems, cnt);
        }
        
    }
}

vector<int> solution(vector<string> gems) {
    vector<int> answer;
    
    int i, n = gems.size();
    
    for (i = 0; i < n; i++) {
        if (mp[gems[i]] == 0) {
            mp[gems[i]] = ++gemCnt;
            vec.push_back(0);
        } 
    }
    ++vec[mp[gems[0]] - 1];
    move(0, 0, gems, 1);
    
    answer.push_back(ret.first);
    answer.push_back(ret.second);
    return answer;
}