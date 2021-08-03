class Solution {
    
    int timeToInt(String time) {
        String[] hhmmss = time.split(":");
        int ret = 0;
        
        ret += Integer.parseInt(hhmmss[0]) * 60 * 60 * 1000;
        ret += Integer.parseInt(hhmmss[1]) * 60 * 1000;
        String[] ssms = hhmmss[2].split("\\.");
        ret += Integer.parseInt(ssms[0]) * 1000 + Integer.parseInt(ssms[1]);
        
        return ret;
    }

    int dmsToInt(String ms) {
        String ds = ms.substring(0, ms.length() - 1);

        double dms = Double.parseDouble(ds) * 1000;

        return (int)dms;
    }
    final int MXT = 24 * 60 * 60 * 1000 + 3000;
    public int solution(String[] lines) {
        int answer = 0;
        
        int i;
        int[] P = new int[MXT];
        for (i = 0; i < lines.length; i++) {
            String[] timeMs = lines[i].split(" ");

            
            String time = timeMs[1], ms = timeMs[2];
            
            int end = timeToInt(time) + 3000;
            
            int stt = end - dmsToInt(ms) + 1;
            
            
            for (int j = stt - 999; j <= end; j++) {
                if (j >= 0) {
                    ++P[j];
                    answer = Math.max(P[j], answer);
                }
            }
        }
        
        return answer;
    }
}