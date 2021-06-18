#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    int i, cnt = 0;
    for (i = 0; i < 31; i++) {
        if (((1 << i) & n) != 0) {
            n -= (1 << i);
            ++cnt;
            if (((1 << (i + 1)) & n) == 0) {
                n += (1 << (i + 1));
                break;
            }
        }
    }
    --cnt;
    for (i = 0; i < cnt; i++) {
        n += (1 << i);
    }
    answer = n;
    return answer;
}