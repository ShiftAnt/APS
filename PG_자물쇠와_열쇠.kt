class Solution {
    var P = Array(0) { Array(0) { 0 } }
    var n = 0
    var m = 0

    fun rotate(key: Array<IntArray>) {
        val siz = key.size
        val tmp = Array(siz) { IntArray(siz) }

        for (i in 0 until siz) {
            for (j in 0 until siz) {
                tmp[j][siz - 1 - i] = key[i][j]
            }
        }
        for (i in 0 until siz) {
            for (j in 0 until siz) {
                key[i][j] = tmp[i][j]
            }
        }
    }

    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        m = key.size; n = lock.size
        val nm = n + m * 2
        P = Array(nm) { Array(nm) { -1 } }
        var blk = 0
        for (i in 0 until n) {
            for (j in 0 until n) {
                P[m + i][m + j] = lock[i][j]
                if (lock[i][j] == 0) ++blk
            }
        }

        repeat(4) {
            for (i in 1 until n + m) {
                loop@
                for (j in 1 until n + m) {
                    var fnd = blk
                    for (y in 0 until m) {
                        for (x in 0 until m) {
                            val cur = P[i + y][j + x]
                            if (key[y][x] == 1) {
                                if (cur == 1) continue@loop
                                else if (cur == 0) --fnd
                            } else {
                                if (cur == 0) continue@loop
                            }
                        }
                    }
                    if (fnd == 0) return true
                }
            }
            rotate(key)
        }
        return false
    }
}