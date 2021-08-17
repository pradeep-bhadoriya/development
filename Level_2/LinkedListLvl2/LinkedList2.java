import java.util.*;

public class LinkedList2 {
    public static class ListNode {
        int val = 0;
        ListNode next = null;
        ListNode random = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode midNode2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode midNode1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode mid1 = midNode1(head);
        ListNode head2 = reverse(mid1.next);
        mid1.next = null;
        while (head != null && head2 != null) {
            if (head.val != head2.val) {
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Without using Priority Queue
        ListNode dummy = new ListNode(-1);
        ListNode itr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                itr.next = l1;
                itr = itr.next;
                l1 = l1.next;
            } else {
                itr.next = l2;
                itr = itr.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            itr.next = l1;
        }
        if (l2 != null) {
            itr.next = l2;
        }
        return dummy.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        ListNode forward = head;
        for (int i = 0; i < n; i++) {
            forward = forward.next;
        }
        if (forward == null) {
            return head.next;
        }
        ListNode follower = head;
        while (forward != null && forward.next != null) {
            forward = forward.next;
            follower = follower.next;
        }
        follower.next = follower.next.next;
        return head;
    }

    public static boolean isCyclePresentInLL(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
        int size1 = getsize(headA);
        int size2 = getsize(headB);
        if (size1 > size2) {
            for (int i = 0; i < size1 - size2; i++) {
                headA = headA.next;
            }
        } else {
            for (int i = 0; i < size2 - size1; i++) {
                headB = headB.next;
            }
        }

        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);
        ListNode oddItr = odd;
        ListNode evenItr = even;
        ListNode op = head;
        ListNode ep = head.next;
        while (ep != null && ep.next != null) {
            oddItr.next = op;
            oddItr = oddItr.next;
            op = op.next.next;
            evenItr.next = ep;
            evenItr = evenItr.next;
            ep = ep.next.next;
        }
        oddItr.next = op;
        oddItr = oddItr.next;
        evenItr.next = ep;
        oddItr.next = even.next;
        return odd.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>();
        if (lists.length == 0) {
            return null;
        }
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                pq.add(lists[i]);
        }
        ListNode head = new ListNode(-1);
        ListNode itr = head;
        while (pq.size() > 0 && itr != null) {
            ListNode rem = pq.remove();
            itr.next = rem;
            itr = itr.next;
            if (rem.next != null) {
                pq.add(rem.next);
            }
        }
        return head.next;
    }

    public static void swap(ListNode i, ListNode j) {
        int data = i.val;
        i.val = j.val;
        j.val = data;
    }

    public static ListNode segregate01(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode i = head;
        ListNode j = head;
        while (j != null) {
            if (j.val == 0) {
                swap(i, j);
                i = i.next;
                j = j.next;
            } else {
                j = j.next;
            }
        }
        return head;
    }

    public static ListNode removeDuplicates(ListNode head) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode dp = dummy;
        ListNode itr = head;
        boolean flag = true;
        dp.next = itr;
        itr = itr.next;
        while (itr != null) {
            if (dp.next.val == itr.val) {
                flag = false;
                itr = itr.next;
            } else {
                if (flag == false) {
                    dp.next = itr;
                    itr = itr.next;
                    flag = true;
                } else {
                    dp = dp.next;
                    itr = itr.next;
                }
            }

        }
        if (flag == true) {
            if (dp.next != null) {
                dp = dp.next;
            }
        }
        dp.next = null;

        return dummy.next;
    }

    public static ListNode segregate012(ListNode head) {
        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);
        ListNode two = new ListNode(-1);
        ListNode op = one;
        ListNode zp = zero;
        ListNode tp = two;
        ListNode itr = head;
        while (itr != null) {
            if (itr.val == 0) {
                zp.next = itr;
                zp = zp.next;
            } else if (itr.val == 1) {
                op.next = itr;
                op = op.next;
            } else {
                tp.next = itr;
                tp = tp.next;
            }
            itr = itr.next;
        }
        tp.next = null;
        op.next = two.next;
        zp.next = one.next;

        return zero.next;
    }

    public ListNode copyRandomList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode itr1 = head;
        ListNode dummy = new ListNode(-1);
        ListNode dp = dummy;
        while (itr1 != null) {
            dp.next = new ListNode(itr1.val);
            dp = dp.next;
            itr1 = itr1.next;
        }
        itr1 = head;
        ListNode itr2 = dummy.next;
        ListNode n1 = itr1.next;
        ListNode n2 = itr2.next;
        while (n1 != null && n2 != null) {
            itr1.next = itr2;
            itr2.next = n1;
            itr1 = n1;
            itr2 = n2;
            n1 = n1.next;
            n2 = n2.next;
        }
        itr1.next = itr2;
        itr1 = itr1.next;
        itr1.next = null;

        ListNode itr = head;
        while (itr != null && itr.next != null) {
            if (itr.random == null) {
                itr.next.random = null;
                itr = itr.next.next;
            } else {
                itr.next.random = itr.random.next;
                itr = itr.next.next;
            }
        }

        ListNode t1 = head;
        ListNode head2 = t1.next;
        ListNode t2 = t1.next;

        while (t1 != null && t2 != null) {
            ListNode n11 = t2.next;
            ListNode n22 = n11 == null ? null : n11.next;

            t1.next = n11;
            t2.next = n22;
            t1 = n11;
            t2 = n22;
        }
        return head2;
    }

    public static ListNode getPivot(ListNode head, int pivotIdx) {
        ListNode itr = head;
        while (pivotIdx > 0) {
            itr = itr.next;
            pivotIdx--;
        }
        // printList(itr);
        return itr;
    }

    public static ListNode segregate(ListNode head, int pivotIdx) {
        ListNode pivot = getPivot(head, pivotIdx);
        ListNode left = new ListNode(-1);
        ListNode right = new ListNode(-1);
        ListNode p1 = left;
        ListNode p2 = right;
        ListNode itr = head;
        while (itr != null) {
            if (itr.val <= pivot.val && itr != pivot) {
                p1.next = itr;
                p1 = p1.next;
            } else {
                if (itr != pivot) {
                    p2.next = itr;
                    p2 = p2.next;
                }
            }
            itr = itr.next;
        }
        p1.next = null;
        p2.next = null;
        p1.next = pivot;
        pivot.next = right.next;
        return left.next;
    }

    

    public static void fun() {

    }

    public static void main(String[] args) {
        fun();
    }
}