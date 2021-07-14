import java.util.*;
class Solution {
    boolean[] vstd;

    int MIF = Integer.MAX_VALUE;

    int distance(int f, int t, boolean isClock, int n) {
        if (isClock) {
            return f <= t ? t - f : n - f + t;
        }
        return f >= t ? f - t : n - t + f;
    }

    Stack<Integer> lstVstd = new Stack<>();

    void dfs(int idx, boolean isClock, int num, int friend, int n, int[] weak, Integer[] idist) {
        if (friend >= MIF || MIF == 1) return;
        if (num == 0) {
            MIF = Math.min(MIF, friend);
            return;
        }
        if (friend == idist.length) return;
        if (vstd[idx]) return;
        int dir = isClock ? 1 : -1, i, moveLen = 0, meet = 1, clear = 1;

        int prev = idx;
        vstd[idx] = true;
        lstVstd.add(idx);
        for (i = idx + dir; meet < weak.length; i += dir) {
            int ii = i < 0 ? weak.length + i : i >= weak.length ? i - weak.length : i;
            int dis = distance(weak[prev], weak[ii], isClock, n);

            moveLen += dis;

            if (moveLen > idist[friend]) break;

            ++meet;

            if (!vstd[ii]) {
                vstd[ii] = true;
                lstVstd.add(ii);
                ++clear;
            }
            prev = ii;
        }

        for (i = 0; i < weak.length; i++) {
            dfs(i, !isClock, num - clear, friend + 1, n, weak, idist);
        }

        for (i = 0; i < clear; i++) {
            vstd[lstVstd.pop()] = false;
        }

    }

    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        
        vstd = new boolean[weak.length];

        Integer[] idist = Arrays.stream(dist).boxed()
            .sorted(Collections.reverseOrder())
            .toArray(Integer[]::new);

        int i;
        if (idist[0] >= n) return 1;
        for (i = 0; i < weak.length; i++) {
            dfs(i, true, weak.length, 0, n, weak, idist);
        }
        answer = MIF == Integer.MAX_VALUE ? -1 : MIF;
        return answer;
    }
}