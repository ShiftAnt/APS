import java.util.*;

class Pair implements Comparable<Pair> {
    public int y, x, r;

    @Override
    public int compareTo(Pair o) {
        return y < o.y ? -1 : y > o.y ? 1 : x < o.x ? -1 : x > o.x ? 1 : 0;
    }

    public Pair(int y, int x, int r) {
        this.y = y;
        this.x = x;
        this.r = r;
    }

}

public class Main {
    static int n;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    static PriorityQueue<Pair> cch = new PriorityQueue<>();
    static PriorityQueue<Integer> zero = new PriorityQueue<>();
    static ArrayList<Integer> ret = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();

        int i;
        for (i = 0; i < n; i++) {
            pq.add(new Pair(sc.nextInt(), sc.nextInt(), -1));
        }
        Pair prv = pq.poll();
        prv.r = 0;
        ret.add(1);
        while(!pq.isEmpty()) {
            cch.add(new Pair(prv.x, 0, prv.r));
            Pair cur = pq.poll();
            while (!cch.isEmpty() && cch.peek().y < cur.y) {
                zero.add(cch.poll().r);
            }

            if (prv.x <= cur.y) {
                cur.r = zero.poll();
            } else {
                if (!zero.isEmpty()) {
                    cur.r = zero.poll();
                } else {
                    cur.r = ret.size();
                }
            }
            if (cur.r >= ret.size()) {
                ret.add(1);
            } else {
                ret.set(cur.r, ret.get(cur.r) + 1);
            }
            prv = cur;
        }
        System.out.println(ret.size());

        for (i = 0; i < ret.size(); i++) {
            System.out.print(ret.get(i) + " ");
        }

        sc.close();
    }
}