package com.ng.base.utils;

/**
 * 双向链表
 */
public class DoubleListNode {
  public int val;
  public DoubleListNode next = null;
  public DoubleListNode prev = null;

  public DoubleListNode(int val) {
    this.val = val;
  }

  public static DoubleListNode getNodeList(int... array) {
    if (array == null) {
      return null;
    }
    DoubleListNode head = new DoubleListNode(array[0]);
    DoubleListNode cur = head;
    for (int i = 1; i < array.length; i++) {
      DoubleListNode tempNode = new DoubleListNode(array[i]);
      cur.next = tempNode;
      tempNode.prev = cur;
      cur = tempNode;
    }
    return head;
  }


  @Override
  public String toString() {
    String result = "[";
    DoubleListNode node = this;
    while (node != null) {
      result += "{";
      result += "val:" + node.val;
      if (node.prev != null) {
        result += " ,prev:" + node.prev.val;
      }
      if (node.next != null) {
        result += " ,next:" + node.next.val;
      }
      result += "}";
      node = node.next;
    }
    return result.substring(0, result.length() - 1) + "]";
  }
}