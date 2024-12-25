package solutions.kotlin

class `210_courseSchedule` {
    var n = 0
    var indeg = MutableList(0) {0}
    var adjl = MutableList(0) {MutableList(0) {0}}

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        n = numCourses
        indeg = MutableList(n) {0}
        adjl = MutableList(n) {MutableList(0) {0}}
        for(pr in prerequisites) {
            val u = pr[0]
            val v = pr[1]
            indeg[u]++
            adjl[v].add(u)
        }
        var take = MutableList(0) {0}
        var ans = MutableList(0) {0}
        for(i in 0 .. numCourses - 1) {
            if(indeg[i] == 0) {
                take.add(i)
                ans.add(i)
            }
        }
        while(take.isNotEmpty()) {
            val u = take.last()
            take.removeLast()
            for(v in adjl[u]) {
                indeg[v]--
                if(indeg[v] == 0) {
                    take.add(v)
                    ans.add(v)
                }
            }
        }
        if(ans.size != n) {
            return intArrayOf()
        }
        return ans.toIntArray()

    }
}
/**
 * category:
 * tag: topsort, topological sort, revise for dfs
 *
 * */