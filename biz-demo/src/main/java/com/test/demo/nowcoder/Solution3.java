package com.test.demo.nowcoder;

import java.util.ArrayList;

/**
 * https://www.nowcoder.com/ta/coding-interviews
 * nowcoder offer3:
 * 	输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 *
 * 思路：
 * 	注意环的处理
 *
 * Created by zhaohan on 2019/5/8.
 */
public class Solution3 {

	public static void main(String[] args) {
		//67,0,24,58
		ListNode listNode1 = new ListNode(67);
		ListNode listNode2 = new ListNode(0);
		ListNode listNode3 = new ListNode(24);
		ListNode listNode4 = new ListNode(58);

		listNode1.next = listNode2;
		listNode2.next = listNode3;
		listNode3.next = listNode4;

		System.out.println("结果：" + printListFromTailToHead(listNode1));
	}

	public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		if (listNode == null) {
			return new ArrayList<>();
		}

		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<ListNode> listNodes = new ArrayList<>();

		ListNode cur = listNode;
		list.add(cur.val);
		listNodes.add(cur);

		do {
			cur = cur.next;
			if (listNodes.contains(cur)) {
				//防止环
				break;
			}

			list.add(cur.val);
			listNodes.add(cur);
		} while (cur.next != null);

		ArrayList<Integer> newList = new ArrayList<>();
		for (int i = list.size() - 1; i >= 0; i--) {
			newList.add(list.get(i));
		}

		return newList;
	}

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
