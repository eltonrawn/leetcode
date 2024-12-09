package solutions

import java.util.Collections.swap

class `1101_theEarliestMoment` {
    class UnionSet {
        var n = 0
        lateinit var par: MutableList<Int>
        lateinit var gsize: MutableList<Int>
        var noOfGroup = 0
        constructor(n: Int) {
            this.n = n
            noOfGroup = n
            par = MutableList(n) {0}
            gsize = MutableList(n) {0}
            for(i in 0 .. n - 1) {
                par[i] = i
                gsize[i] = 1
            }
        }
        fun findPar(x: Int): Int {
            if(x == par[x]) {
                return x
            }
            par[x] = findPar(par[x])
            return par[x]
        }

        fun makePar(x: Int, y: Int) {
            par[y] = x
            gsize[x] += gsize[y]
        }

        fun union(x: Int, y: Int) {
            val parX = findPar(x)
            val parY = findPar(y)
            if(parX == parY) return
            noOfGroup--
            if(gsize[x] < gsize[y]) {
                makePar(parY, parX)
            } else {
                makePar(parX, parY)
            }

        }
    }

    fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
        val unionSet = UnionSet(n)
        logs.sortWith(compareBy<IntArray> {it[0]})
        for(log in logs) {
            unionSet.union(log[1], log[2])
            if(unionSet.noOfGroup == 1) {
                return log[0]
            }
        }
        return -1
    }
}
/**
 * category:
 * tag: union set, graph, dsu, disjoint set union
 *
 * */