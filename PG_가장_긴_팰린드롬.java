class Solution
{
    int h, n, answer;
    boolean[][] vstd;
    void move(int stt, int end, String s) {
        if (stt < 0 || end >= n) return;
        int ret = 0;
        int sm = stt == end ? 1 : 0;
        if (vstd[sm][stt]) return;
        vstd[sm][stt] = true;
        
        if (sm == 1) {
            ret = 1;
            --stt;
            ++end;
            sm = 1;
        }
        
        while (stt >= 0 && end < n && s.charAt(stt) == s.charAt(end)) {
            ret += 2;
            --stt;
            ++end;
        }
        answer = Math.max(ret, answer);
        
        
        ++stt; --end;
        
        
        
        move(stt, stt + 1, s);
        move(stt, stt, s);
        
        
        move(end - 1, end, s);
        move(end, end, s);
    }
    public int solution(String s)
    {
        answer = 1;
        n = s.length();
        h = n / 2;
        vstd = new boolean[2][n];
        
        move(h - 1, h, s);
        
        move(h, h, s);
        
        move(h, h + 1, s);
        return answer;
    }
}