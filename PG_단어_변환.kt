import java.util.*

class Solution {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        if (begin.length != target.length) return 0
        var answer = 0
        val n = begin.length
        val hs = mutableSetOf<String>()
        val que = LinkedList<String>()
        val vstd = mutableSetOf<String>()
        for (word in words) hs.add(word)

        if (!hs.contains(target)) return 0
        vstd.add(begin)

        que.add(begin)

        while (!que.isEmpty()) {
            val siz = que.size

            ++answer
            for (T in 0 until siz) {
                val cur = que.poll()
                val sb = StringBuilder(cur)
                for (i in 0 until n) {
                    for (j in 'a'..'z') {
                        sb[i] = j
                        val tmp = sb.toString()
                        if (hs.contains(tmp) && !vstd.contains(tmp)) {
                            if (tmp == target) return answer
                            vstd.add(tmp)
                            que.add(tmp)

                        }
                    }
                    sb[i] = cur[i]
                }

            }

        }

        return 0
    }
}