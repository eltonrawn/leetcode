package solutions

class `721_accountsMerged` {
    class UnionFind {
        val par: MutableList<Int> = MutableList(0) {-1}
        val gSize: MutableList<Int> = MutableList(0) {-1}
        constructor(n: Int) {
            for(i in 0 .. n - 1) {
                par.add(i)
                gSize.add(1)
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
            gSize[x] += gSize[y]
        }

        fun union(x: Int, y: Int) {
            val parX = findPar(x)
            val parY = findPar(y)
            if(parX == parY) {
                return
            }
            if(gSize[x] >= gSize[y]) {
                makePar(parX, parY)
            } else {
                makePar(parY, parX)
            }
        }

    }
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val mp = mutableMapOf<String, Int>()
        val accountMap = mutableMapOf<String, String>()
        var cnt = 0
        for(account in accounts) {
            for(i in 1 ..< account.size) {
                val email = account[i]
                if(mp[email] == null) {
                    mp[email] = cnt
                    accountMap[email] = account[0]
                    cnt++
                }
            }
        }
        val unionFind = UnionFind(cnt)
        for(account in accounts) {
            for(i in 2 ..< account.size) {
                unionFind.union(mp[account[1]]!!, mp[account[i]]!!)
            }
        }

        val yo = MutableList(cnt) {MutableList(0) {""}}

        for(account in accounts) {
            for(i in 1 ..< account.size) {
                val par = unionFind.findPar(mp[account[i]]!!)
                yo[par].add(account[i])
            }
        }

        val ret = MutableList(0) {List(0) {""}}

        for(i in 0 ..< yo.size) {
            if(yo[i].size <= 0) {
                continue
            }
            val cur = MutableList(0) {""}
            for(value in yo[i]) {
                cur.add(value)
            }
            ret.add(listOf(accountMap[yo[i][0]]!!) + cur.toSortedSet().toList())
        }
        return ret.toList()
    }
}
/**
 * category:
 * tag: union set, graph, dsu, disjoint set union, revise, implementation, medium
 *
 * */