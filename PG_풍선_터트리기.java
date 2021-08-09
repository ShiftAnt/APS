class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int[][] left = new int[2][a.length];
        int[][] right = new int[2][a.length];
        
        left[0][0] = left[1][0] = a[0];
        int lmi = a[0], lmi2 = Integer.MAX_VALUE;
        int i;
        for (i = 1; i < a.length; i++) {
            if (a[i] < lmi) {
                lmi2 = lmi;
                lmi = a[i];
            } else if (a[i] < lmi2) {
                lmi2 = a[i];
            }
            left[0][i] = lmi;
            left[1][i] = lmi2;
        }
        right[0][a.length - 1] = right[1][a.length - 1] = a[a.length - 1];
        int rmi = a[a.length - 1], rmi2 = Integer.MAX_VALUE;
        
        for (i = a.length - 1; i >= 0; i--) {
            if (a[i] < rmi) {
                rmi2 = rmi;
                rmi = a[i];
            } else if (a[i] < rmi2) {
                rmi2 = a[i];
            }
            right[0][i] = rmi;
            right[1][i] = rmi2;
        }
        for (i = 0; i < a.length; i++) {
            boolean[] isL = {true, true};
            boolean[] isR = {true, true};
            if (i > 0) {
                if (a[i] > left[0][i - 1]) isL[0] = false;
                else if (a[i] > left[1][i - 1]) isL[1] = false;
            }
            if (i < a.length - 1) {
                if (a[i] > right[0][i + 1]) isR[0] = false;
                else if (a[i] > right[1][i + 1]) isR[1] = false;
            }
            
            if(isL[0] && isR[0] || isL[0] && isR[1] || isL[1] && isR[0]) ++answer;
        }
        
        return answer;
    }
}