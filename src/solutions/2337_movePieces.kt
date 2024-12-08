package solutions

class `2337_movePieces` {
    fun canChange(start: String, target: String): Boolean {
        var startPos = 0
        var targetPos = 0
        if(start.count{it != '_'} != target.count{it != '_'}) return false
        while(targetPos < target.length) {
            println(targetPos)
            if(target[targetPos] == '_') {
                targetPos++
                continue
            }
            while(startPos < start.length && start[startPos] == '_') {
                startPos++
            }
            if(start[startPos] != target[targetPos]) {
                return false
            }
            if(target[targetPos] == 'L' && startPos < targetPos) {
                return false
            }
            if(target[targetPos] == 'R' && startPos > targetPos) {
                return false
            }
            startPos++
            targetPos++
        }
        return true
    }
}
/**
 * category:
 * tag: two pointer, easy
 *
 * */