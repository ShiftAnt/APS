import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        
        int[] dp = new int[n + 1];
        
        int mod = 1000000007;
        int i, j, k;
        
        
        for (i = 0; i <= n; i+= money[0]) dp[i] = 1;
        
        for (i = 1; i < money.length; i++) {
            for (j = n; j >= 0; j--) {
                if (dp[j] == 0) continue;
                for (k = money[i]; k <= n; k += money[i]) {
                    if (j + k > n) break;
                    dp[j + k] += dp[j];
                    dp[j + k] %= mod;
                }
            }
        }
        
        answer = dp[n];
        return answer;
    }
}