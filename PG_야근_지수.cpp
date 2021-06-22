#include <string>
#include <vector>
#include <algorithm>
using namespace std;

long long solution(int n, vector<int> works) {
    long long answer = 0;
    sort(works.begin(), works.end());
    int i, ws = works.size(), j;
    
    int snwjr = 0, subsnwjr = 0, mini = 0;
    for (i = ws - 1; i >= 1; i--) {
        ++snwjr;
        int sub = works[i] - works[i - 1];
        bool isB = false;
        if (sub * snwjr < n) n -= sub * snwjr;
        else {
            for (j = 1; j <= works[i - 1]; j++) {
                if (snwjr < n) n-= snwjr;
                else {
                    mini = works[i] - j;
                    subsnwjr = n;
                    isB = true;
                    n = 0;
                    break;
                }
            }
        }
        if (isB) break;
    }
    if (n > 0) {
        mini = works[0] - n / ws;
        
        subsnwjr = n % ws;
        if (mini <= 0) return 0L;
        answer = ((long long)(mini - 1) * (mini - 1)) * subsnwjr + ((long long)mini * mini) * (ws - subsnwjr);
    } else {
        answer = ((long long)mini * mini) * subsnwjr + ((long long)(mini + 1) * (mini + 1)) * (snwjr - subsnwjr);
        for (j = i - 1; j >= 0; j--) {
            answer += (works[j] * works[j]);
        }
    }
    
    
    return answer;
}