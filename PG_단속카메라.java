import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes, (a, b) -> {
            return a[0] < b[0] ? -1 : a[0] > b[0] ? 1 : a[1] < b[1] ? -1 : a[1] > b[1] ? 1 : 0; 
        });
        int stt = routes[0][1];
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] <= stt) {
                stt = Math.min(stt, routes[i][1]);
            } else {
                stt = routes[i][1];
                ++answer;
            }
        }

        return answer;
    }
}