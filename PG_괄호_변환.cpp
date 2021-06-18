#include <string>
#include <vector>
using namespace std;


bool isCor(string s) {
    int i, cnt = 0;
    for (i = 0; i < s.length(); i++) {
        if (s[i] == '(') ++cnt;
        else --cnt;
        
        if (cnt < 0) return false;
    }
    return cnt == 0;
}

pair<string, string> slice(string s) {
    int idx = 0, cnt = 0;
    
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '(') ++cnt;
        else --cnt;
        
        if (cnt == 0) {
            idx = i;
            break;
        }
    }
    return {s.substr(0, idx + 1), s.substr(idx + 1, s.length() - idx - 1)};
}

string solution(string p) {
    string answer = "";
    int i;
    if (p == "") return p;
    
    pair<string, string> sl = slice(p);
    
    string u = sl.first;
    string v = sl.second;
    
    if (isCor(u)) {
        v = solution(v);
        answer = u + v;
    } else {
        answer = "(";
        v = solution(v);
        answer += v;
        answer += ")";
        for (i = 1; i < u.length() - 1; i++) {
            if (u[i] == '(') answer += ')';
            else answer += '(';
        }
    }
    
    
    return answer;
}