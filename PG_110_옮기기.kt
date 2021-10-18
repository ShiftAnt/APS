class Solution {
    fun solution(s: Array<String>): Array<String> {
        var answer = ArrayList<String>()

        for (cur in s) {
            val sb = StringBuilder()

            var cnt = 0
            for (c in cur) {
                sb.append(c)
                if (sb.length >= 3 && sb[sb.length - 1] == '0' && sb[sb.length - 2] == '1' && sb[sb.length - 3] == '1') {
                    ++cnt
                    sb.delete(sb.length - 3, sb.length)
                }
            }
            var idx = -1

            for (i in 0 until sb.length - 2) {
                if (sb[i] == '1' && sb[i + 1] == '1' && sb[i + 2] == '1') {
                    idx = i
                    break
                }
            }
            if (idx == -1) {
                idx = 0
                for (i in sb.length - 1 downTo 0) {
                    if (sb[i] == '0') {
                        idx = i + 1
                        break
                    }
                }
            }

            val tmp = StringBuilder(sb.substring(0, idx))
            repeat(cnt) {
                tmp.append("110")
            }
            tmp.append(sb.substring(idx, sb.length))
            answer.add(tmp.toString())

        }
        return answer.toTypedArray()
    }
}