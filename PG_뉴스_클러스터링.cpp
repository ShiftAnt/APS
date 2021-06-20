#include <string>
#include <vector>
#include <map>
using namespace std;


vector<string> vec;
map<string, pair<int, int>> mp;

string change(string s) {
    string n = "";
    
    for (int i = 0; i < s.length(); i++) {
        if (s[i] >= 'a' && s[i] <= 'z') {
            n.push_back(s[i] - 'a' + 'A');
        } else {
            n.push_back(s[i]);
        }
    }
    return n;
}

bool check(char c) {
    return c >= 'A' && c <= 'Z';
}

int solution(string str1, string str2) {
    int answer = 0;
    
    int i;
    
    str1 = change(str1);
    str2 = change(str2);
    for (i = 1; i < str1.length(); i++) {
        if (check(str1[i - 1]) && check(str1[i])) {
            string tar = str1.substr(i - 1, 2);
            vec.push_back(tar);
            ++mp[tar].first;
        }
        
        
    }
    for (i = 1; i < str2.length(); i++) {
        if (check(str2[i - 1]) && check(str2[i])) {
            string tar = str2.substr(i - 1, 2);
            vec.push_back(tar);
            ++mp[tar].second;
        }
        
    }
    int uu = 0, nn = 0;
    for (i = 0; i < vec.size(); i++) {
        int& a = mp[vec[i]].first;
        int& b = mp[vec[i]].second;
        
        if (a < 0) continue;
        
        if (a == 0 || b == 0) {
            uu += (a + b);
        } else {
            uu += max(a, b);
            nn += min(a, b);
        }
        a  = -1;
    }
    if (uu == 0) answer = 65536;
    else answer = (double)nn / uu * 65536;
    
    return answer;
}