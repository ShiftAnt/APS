#include <string>
#include <vector>

using namespace std;

vector<int> tmp;
int solution(vector<int> stones, int k) {
    int answer = 0;
    int ss = stones.size(), i, j = 0;
    
    int mx = stones[0], mi = stones[0];
    
    for (i = 0; i < ss; i++) {
        mx = max(stones[i], mx);
        mi = min(stones[i], mi);
        tmp.push_back(0);
    }
    
    while (mi <= mx) {
        int mid = (mx + mi) / 2;
        
        for (i = 0; i < ss; i++) {
            tmp[i] = stones[i] - mid + 1;
        }
        bool ret = true;
        for (i = -1; i < ss; i += j) {
            for (j = 1; j <= k && i + j < ss; j++) {
                if (tmp[i + j] <= 0) continue;
                else break;
            }
            if (j == k + 1) {
                ret = false;
                break;
            }
            
        }
        if (ret) {
            mi = mid + 1;
            answer = max(mid, answer);
        }
        else mx = mid - 1;
        
    }
    return answer;
}