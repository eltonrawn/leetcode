package solutions

class `394_decodeString` {
    fun func(i: Int, j: Int, ri: MutableList<Int>, s: String): StringBuilder {
        if(i > j) {
            return StringBuilder()
        }
        val ss = StringBuilder()
        if(s[i] in 'a'..'z') {
            ss.append(s[i])
            ss.append(func(i + 1, j, ri, s))
        }
        if(s[i] in '0'..'9') {
            var iPos = i
            while(iPos <= j && s[iPos] >= '0' && s[iPos] <= '9') {
                iPos++
            }
            val num = s.substring(i, iPos).toInt()

            val tmp = func(iPos + 1, ri[iPos] - 1, ri, s)

            // iPos should point to [
            for(i in 1 .. num) {
                ss.append(tmp)
            }
            ss.append(func(ri[iPos] + 1, j, ri, s))
        }
        return ss
    }

    fun decodeString(s: String): String {
        val ri = MutableList (s.length) {-1}
        val stack = MutableList(0) {0}
        for(i in s.indices) {
            if(s[i] == '[') {
                stack.add(i)
                continue
            }
            if(s[i] == ']') {
                ri[stack.last()] = i
                stack.removeLast()
            }
        }
        return func(0, s.length - 1, ri, s).toString()
    }
}
/**
 * tag: string, implementation, medium
 *
 * */