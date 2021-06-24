#include <iostream>
#include <vector>
using namespace std;

int solution(int n, vector<int> stations, int w)
{
    int answer = 0;

    
    int plus = 0, i, ss = stations.size(), add = 0, prev = 0, cur = 0;
    int b = 2 * w + 1;
    for (i = 0; i < ss; i++) {
        int a = stations[i] - plus + w;
        if (stations[i] - plus <= 0) {
            plus = stations[i] + w;
            continue;
        }
        
        int order = a / b;
        
        if (a % b == 0) {
            answer += order - 1;
        } else {
            answer += order;
        }
        plus += a;
        
    }
    cout << answer;
    if (n - plus > 0) {
        int rem = n - plus;
        int order = rem / b;
        if (rem % b == 0) {
            answer += order;
        } else {
            answer += order + 1;
        }
        
    }
    
    return answer;
}