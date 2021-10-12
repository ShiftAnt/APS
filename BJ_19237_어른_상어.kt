import java.io.*
import java.util.*

class SH (
    var y: Int,
    var x: Int,
    val num: Int,
    var dir: Int
        ) {
    private fun find(idx: Int): Boolean {
        for (i in dirs[num - 1][dir]) {
            val a = y + dr[i]
            val b = x + dc[i]
            if (a in 0 until n && b in 0 until n) {
                if (idx == -1) {
                    if (Q[a][b] == null) {
                        dir = i
                        y = a
                        x = b
                        return true
                    }
                } else {
                    if (Q[a][b]!!.num == num) {
                        dir = i
                        y = a
                        x = b
                        return true
                    }
                }
            }
        }
        return false
    }
    fun mkSM(): SH {
        Q[y][x] = SM(y, x, num, k)
        return this
    }
    fun move(): SH {
        if (!find(-1)) {
            find(0)
        }
        return this
    }
}
class SM (
    val y: Int,
    val x: Int,
    val num: Int,
    var time: Int
        )

val dr = arrayOf(-1, 1, 0, 0)
val dc = arrayOf(0, 0, -1, 1)
var dirs = Array(0) {Array(0) {IntArray(0)} }
var P = Array(0) {Array(0) {LinkedList<SH>()} }
var Q = Array(0) {Array<SM?>(0) { null} }
var n = 0
var m = 0
var k = 0
var shs = Array(0) {SH(0, 0, 0, -1)}

fun del(lst: LinkedList<SH>): Int {
    if (lst.size < 2) return 0

    var tar = SH(0, 0, m + 1, 0)

    for (sh in lst) {
        if (tar.num > sh.num) {
            tar = sh
        }
    }
    var ret = 0
    for (sh in lst) {
        if (sh.num != tar.num) {
            sh.y = -1
            ++ret
        }
    }
    lst.clear()
    lst.add(tar)

    return ret
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val inp = br.readLine().split(" ").map { it.toInt() }
    n = inp[0]; m = inp[1]; k = inp[2]
    P = Array(n) {Array(n) {LinkedList<SH>()}}
    Q = Array(n) {Array(n) {null}}
    shs = Array(m) {SH(0, 0, 0, -1)}
    repeat(n) { i->
        val st = StringTokenizer(br.readLine())
        repeat(n) { j->
            val num = st.nextToken().toInt()

            if (num > 0) {
                shs[num - 1] = SH(i, j, num, -1)
                P[i][j].add(shs[num - 1])
            }
        }
    }
    val st = StringTokenizer(br.readLine())
    dirs = Array(m) {Array(4) {IntArray(4)} }
    repeat(m) {
        shs[it].dir = st.nextToken().toInt() - 1
    }

    for (i in 0 until m) {
        for (j in 0 until 4) {
            val tmp = br.readLine().split(" ").map { it.toInt() - 1 }

            for (l in 0 until 4) {
                dirs[i][j][l] = tmp[l]
            }
        }
    }

    var rem = m
    var time = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (!P[i][j].isEmpty()) P[i][j][0].mkSM()
        }
    }
    while (true) {
        ++time
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (!P[i][j].isEmpty()) {
                    P[i][j][0].move()
                    P[i][j].poll()
                }
            }
        }
        for (sh in shs) {
            if (sh.y != -1) P[sh.y][sh.x].add(sh)
        }

        for (i in 0 until n) {
            for (j in 0 until n) {
                rem -= del(P[i][j])
            }
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (Q[i][j] != null && --Q[i][j]!!.time == 0) Q[i][j] = null
            }
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (!P[i][j].isEmpty()) P[i][j][0].mkSM()
            }
        }

        if (rem == 1 || time == 1000) break
    }

    if (rem == 1) bw.write("$time")
    else bw.write("-1")

    br.close()
    bw.close()
}