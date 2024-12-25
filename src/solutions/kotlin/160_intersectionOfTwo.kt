package solutions.kotlin

class `160_intersectionOfTwo` {

    class ListNode {
        val next: ListNode? = null
    }

    fun moveForward(cur: ListNode?, cntOfForward: Int): ListNode? {
        if(cntOfForward == 0) {
            return cur
        }
        return moveForward(cur?.next, cntOfForward - 1)
    }

    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
        var curA = headA
        var curB = headB

        var aForward = 0
        var bForward = 0
        while(curA != null || curB != null) {
            var isBreak = 0
            if(curA != null) {
                curA = curA.next
            }
            else {
                bForward++
            }
            if(curB != null) {
                curB = curB.next
            } else {
                aForward++
            }
        }

        println("aForward: $aForward")
        println("bForward: $bForward")

        var newHeadA = moveForward(headA, aForward)
        var newHeadB = moveForward(headB, bForward)


        curA = newHeadA
        curB = newHeadB

        while(curA != curB) {
            curA = curA?.next
            curB = curB?.next
        }
        return curA

    }
}
/**
 * category:
 * tag: linked list, memory optimize, memory optimization, constant memory, tricky, revise
 *
 * */