package com.ng.code.menu.链表;

import com.ng.code.util.RandomListNode;
import com.ng.code.util.Solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://www.nowcoder.com/practice/f836b2c43afc4b35ad6adc41ec941dba?tpId=13&tqId=23254&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
 * 原题描述:
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，
 * 并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）。
 * 下图是一个含有5个结点的复杂链表。图中实线箭头表示next指针，虚线箭头表示random指针。为简单起见，指向null的指针没有画出。
 * <p>
 * 示例:
 * 输入：
 * {1,2,3,4,5,3,5,#,2,#}
 * 返回值：
 * {1,2,3,4,5,3,5,#,2,#}
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_复杂链表的复制 {

    public static void main(String[] args) {

    }

    /**
     * 模拟+哈希表
     * 如果不考虑 random 指针的话，对一条链表进行拷贝，我们只需要使用两个指针：
     * 一个用于遍历原链表，一个用于构造新链表（始终指向新链表的尾部）即可。
     * 这一步操作可看做是「创建节点 + 构建 next 指针关系」。
     * 现在在此基础上增加一个 random 指针，我们可以将 next 指针和 random 指针关系的构建拆开进行：
     * 先不考虑 random 指针，和原本的链表复制一样，创建新新节点，并构造 next 指针关系，同时使用「哈希表」记录原节点和新节点的映射关系；
     * 对原链表和新链表进行同时遍历，对于原链表的每个节点上的 random 都通过「哈希表」找到对应的新 random 节点，并在新链表上构造 random 关系。
     * 时间On  空间On
     */
    private static class EasySolution {

        public RandomListNode Clone(RandomListNode head) {
            Map<RandomListNode, RandomListNode> map = new HashMap<>();
            RandomListNode dummy = new RandomListNode(-1);
            RandomListNode tail = dummy,
                    tmp = head;
            while (tmp != null) {
                RandomListNode node = new RandomListNode(tmp.label);
                map.put(tmp, node);
                tail.next = node;
                tail = tail.next;
                tmp = tmp.next;
            }
            tail = dummy.next;
            while (head != null) {
                if (head.random != null) tail.random = map.get(head.random);
                tail = tail.next;
                head = head.next;
            }
            return dummy.next;
        }

    }

    /**
     * 模拟（原地算法）
     * 显然时间复杂度上无法优化，考虑如何降低空间（不使用「哈希表」）。
     * <p>
     * 我们使用「哈希表」的目的为了实现原节点和新节点的映射关系，更进一步的是为了快速找到某个节点 random 在新链表的位置。
     * <p>
     * 那么我们可以利用原链表的 next 做一个临时中转，从而实现映射。
     * <p>
     * 具体的，我们可以按照如下流程进行：
     * <p>
     * 对原链表的每个节点节点进行复制，并追加到原节点的后面；
     * 完成  操作之后，链表的奇数位置代表了原链表节点，链表的偶数位置代表了新链表节点，且每个原节点的 next 指针执行了对应的新节点。这时候，我们需要构造新链表的 random 指针关系，可以利用 link[i + 1].random = link[i].random.next， 为奇数下标，含义为 新链表节点的 random 指针指向旧链表对应节点的 random 指针的下一个值；
     * 对链表进行拆分操作。
     *
     * 时间On  空间O1
     */
    private static class HardSolution {
        public RandomListNode Clone(RandomListNode head) {
            if (head == null) return null;
            RandomListNode dummy = new RandomListNode(-1);
            dummy.next = head;
            while (head != null) {
                RandomListNode node = new RandomListNode(head.label);
                node.next = head.next;
                head.next = node;
                head = node.next;
            }
            head = dummy.next;
            while (head != null) {
                if (head.random != null) {
                    head.next.random = head.random.next;
                }
                head = head.next.next;
            }
            head = dummy.next;
            RandomListNode ans = head.next;
            while (head != null) {
                RandomListNode tmp = head.next;
                if (head.next != null) head.next = head.next.next;
                head = tmp;
            }
            return ans;
        }
    }

}
