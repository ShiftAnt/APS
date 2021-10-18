class Solution {
    fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
        var answer: Long = -1

        val ret = arrayOf(x, y, x, y)

        queries.reverse()

        for (query in queries) {
            when (query[0]) {
                0 -> {
                    if (ret[1] != 0) {
                        ret[1] += query[1]
                        if (ret[1] >= m) return 0
                    }
                    ret[3] = if (ret[3] + query[1] >= m) m - 1 else ret[3] + query[1]
                }
                1 -> {
                    if (ret[3] != m - 1) {
                        ret[3] -= query[1]
                        if (ret[3] < 0) return 0
                    }
                    ret[1] = if (ret[1] - query[1] < 0) 0 else ret[1] - query[1]
                }
                2 -> {
                    if (ret[0] != 0) {
                        ret[0] += query[1]
                        if (ret[0] >= n) return 0
                    }
                    ret[2] = if (query[1] + ret[2] >= n) n - 1 else query[1] + ret[2]
                }
                3 -> {
                    if (ret[2] != n - 1) {
                        ret[2] -= query[1]
                        if (ret[2] < 0) return 0
                    }
                    ret[0] = if (ret[0] - query[1] < 0) 0 else ret[0] - query[1]
                }
            }
        }
        answer = (ret[2] - ret[0] + 1).toLong() * (ret[3] - ret[1] + 1).toLong()

        return answer
    }
}