import java.util.*;



class Solution {
   
    public int[] solution(String[] ops) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        int siz = 0;
        for (int i = 0; i < ops.length; i++) {
            String s = ops[i];
            
            if (s.charAt(0) == 'I') {
                int inp = Integer.parseInt(s.substring(2, s.length()));
                pq.add(inp);
                pq1.add(-inp);
                ++siz;
            } else {
                if (s.charAt(2) == '-') {
                    if (!pq.isEmpty()) {
                        --siz;
                        pq.poll();
                    }
                } else if (!pq1.isEmpty()) {
                    if (!pq1.isEmpty()) {
                        --siz;
                        pq1.poll();
                    }
                }
                if (siz == 0) {
                    pq.clear();
                    pq1.clear();
                }
                
            }
        }
        int[] answer = {0, 0};
        if (!pq.isEmpty()) {
            answer[0] = -pq1.peek();
            answer[1] = pq.peek();
        }
        return answer;
    }
}