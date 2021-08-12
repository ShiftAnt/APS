class Solution {
    public int solution(int sticker[]) {
        int answer = 0, n = sticker.length;

        int[][] dp = new int[2][n + 1];
        answer = sticker[0];
        if (n >= 2) {
            dp[0][2] = sticker[1];
            answer = Math.max(answer, sticker[1]);
        }
        if (n >= 3) {
            dp[0][3] = sticker[2];
            answer = Math.max(answer, sticker[2]);
        }
        dp[1][1] = sticker[0];
        if (n >= 2) dp[1][2] = sticker[1];
        int i;
        
        for (i = 3; i <= n; i++) {
            dp[0][i] = Math.max(dp[0][i - 2], dp[0][i - 3]) + sticker[i - 1];
            if (i != n) dp[1][i] = Math.max(dp[1][i - 2], dp[1][i - 3]) + sticker[i - 1];
            answer = Math.max(answer, dp[0][i]);
            answer = Math.max(answer, dp[1][i]);
        }
        
        return answer;
    }
}