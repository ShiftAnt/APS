import java.io.*
import java.util.*
import kotlin.math.*


var P = arrayOf(intArrayOf())
var n = 0
var k = 0

fun minMax(): Pair<Int, Int> {
	var mx = 0
	var mi = Int.MAX_VALUE
	for (i in 0 until n) {

		mi = min(mi, P[0][i])
		mx = max(mx, P[0][i])

	}
	return mi to mx
}

fun addMin() {
	var mi = Int.MAX_VALUE
	for (i in 0 until n) {
		mi = min(mi, P[0][i])
	}
	for (i in 0 until n) {
		if (P[0][i] == mi) ++P[0][i]
	}
}
fun rotate(arr: Array<IntArray>): Array<IntArray> {
	val y = arr.size
	val x = arr[0].size
	val ret = Array(x) {IntArray(y)}

	for (i in 0 until y) {
		for (j in 0 until x) {
			ret[x - 1 - j][i] = arr[i][j]
		}
	}
	return ret
}

fun go1() {
	var idx = 0
	var y = 1
	var x = 1
	var m = n - 1
	while (y <= m) {
		val before = Array(y) {IntArray(x)}
		for (i in 0 until y) {
			for (j in 0 until x) {
				before[i][j] = P[i][idx + j]
				P[i][idx + j] = 0
			}
		}
		val after = rotate(before)

		for (i in 0 until x) {
			for (j in 0 until y) {
				P[1 + i][idx + x + j] = after[i][j]
			}
		}
		val tmp = y
		y = x + 1
		idx += x
		x = tmp
		m = n - y * x
	}
}
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun spread() {
	val que = LinkedList<Pair<Int, Int>>()
	val vstd = Array(n){BooleanArray(n)};
	vstd[0][n - 1] = true
	val ord = ArrayList<Triple<Pair<Int, Int>, Pair<Int, Int>, Int>>()
	que.add(0 to n - 1)

	while (!que.isEmpty()) {
		val cur = que.poll()


		for (i in dr.indices) {
			val a = cur.first + dr[i]
			val b = cur.second + dc[i]
			if (a in 0 until n && b in 0 until n && P[a][b] != 0) {
				if (!vstd[a][b]) {
					vstd[a][b] = true
					que.add(a to b)
				}
				if (i == 1 || i == 3) {
					val prv = P[cur.first][cur.second]
					val nxt = P[a][b]
					val value = abs(prv - nxt) / 5
					if (value > 0) {
						if (prv > nxt) ord.add(Triple(cur, a to b, value))
						else ord.add(Triple(a to b, cur, value))
					}
				}
			}
		}
	}
	for (or in ord) {
		P[or.first.first][or.first.second] -= or.third
		P[or.second.first][or.second.second] += or.third
	}
}
fun makeFlat() {
	val al = ArrayList<Int>()
	for (i in 0 until n) {
		for (j in 0 until n) {
			if (P[j][i] != 0) {
				al.add(P[j][i])
				P[j][i] = 0
			}
		}
	}
	for (i in 0 until n) {
		P[0][i] = al[i]
	}
}
fun go2() {
	var x = n / 2
	var y = 1
	var idx = 0
	repeat(2) {
		val before = Array(y) {IntArray(x)}
		for (i in 0 until y) {
			for (j in 0 until x) {
				before[i][j] = P[i][idx + j]
				P[i][idx + j] = 0
			}
		}
		val after = rotate(rotate(before))
		idx += x
		for (i in 0 until y) {
			for (j in 0 until x) {
				P[y + i][idx + j] = after[i][j]
			}
		}
		y *= 2
		x /= 2
	}
}
fun move() {
	addMin()
	go1()
	spread()
	makeFlat()
	go2()
	spread()
	makeFlat()
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.`out`))
	var st = StringTokenizer(br.readLine())
	n = st.nextToken().toInt()
	k = st.nextToken().toInt()
	P = Array(n) { IntArray(n) }

	st = StringTokenizer(br.readLine())

	repeat(n) {
		P[0][it] = st.nextToken().toInt()
	}

	var mimx = minMax()
	var ret = 0
	while (mimx.second - mimx.first > k) {
		++ret
		move();
		mimx = minMax()
	}
	bw.write("$ret")
	br.close()
	bw.close()
}