class Solution {
    boolean[] vstd;
    int[] answer;
    
    void dfs(int idx, int num, int n, long k, long sum) {
        if (idx == n) return;
        if (vstd[num - 1]) {
            dfs(idx, num + 1, n, k, sum);
            return;
        } 
        long cnt = 1;
        for (int i = 1; i < n - idx; i++) {
            cnt *= i;
        }
        if (sum + cnt > k) {
            answer[idx] = num;
            vstd[num - 1] = true;
            dfs(idx + 1, 1, n, k, sum);
            return;
        }
        dfs(idx, num + 1, n, k, sum + cnt);
    }
    
    public int[] solution(int n, long k) {
        vstd = new boolean[n];
        answer = new int[n];
        dfs(0, 1, n, k, 1);
        return answer;
    }
}