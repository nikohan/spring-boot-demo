package com.test.demo.nowcoder;

/**
 * https://www.nowcoder.com/ta/coding-interviews
 * nowcoder offer4:
 * 	输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 	例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 术语：
 * 	前序遍历：根节点->左子树->右子树
 * 	中序遍历：左子树->根节点->右子树
 *	后序遍历：左子树->右子树->根节点
 *
 * 思路：
 * 	1.根据前序遍历第一位是根，得知1是树的根，其他是树的左右子节点
 * 	2.根据中序遍历组成： {左子树} 根 {右子树}，得知{4,7,2}是左子树，{5,3,8,6}是右子树
 * 	3.处理1级左子树:根据前序遍历{2,4,7}，得知根是2;根据中序遍历{4,7,2},得知{4,7}是2级左子树
 * 	4.其他子树处理逻辑类似，可以使用递归的方式复原二叉树
 *
 *
 * @author zhaohan
 * @date 2019/5/8
 */
public class Solution4 {

	public static void main(String[] args) {
		int[] pre = {1,2,4,7,3,5,6,8};
		int[] in = {4,7,2,1,5,3,8,6};

		TreeNode root = reConstructBinaryTree(pre, in);
		System.out.print("前序遍历：");
		preDisplay(root);
		System.out.print("\n中序遍历：");
		inDisplay(root);
		System.out.print("\n后序遍历：");
		postDisplay(root);
	}

	public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (isEmpty(pre) || isEmpty(in)
				|| pre.length != in.length) {
			return null;
		}

		int root = pre[0];
		TreeNode rootNode = new TreeNode(root);

		//获取根在中序序列的位置
		int rootIndex = 0;
		for (int i = 0; i < in.length; i++) {
			if (root == in[i]) {
				rootIndex = i;
				break;
			}
		}

		//子树中序
		int[] leftIn = subArr(in, 0, rootIndex);
		int[] rightIn = subArr(in, rootIndex + 1, in.length - rootIndex - 1);

		//子树前序
		int[] leftPre = subArr(pre, 1, rootIndex);
		int[] rightPre = subArr(pre, rootIndex + 1, in.length - rootIndex - 1);

		rootNode.left = reConstructBinaryTree(leftPre, leftIn);
		rootNode.right = reConstructBinaryTree(rightPre, rightIn);

		return rootNode;
	}

	private static int[] subArr(int[] arr, int start, int len) {
		if (isEmpty(arr)) {
			return null;
		}

		int[] subArr = new int[len];
		for (int i = start, j = 0; i < start + len; i++) {
			subArr[j] = arr[i];
			j++;
		}
		return subArr;
	}

	private static boolean isEmpty(int[] arr) {
		return arr == null || arr.length == 0;
	}

	private static void preDisplay(TreeNode node) {
		if (node == null) {
			return;
		}
		System.out.print(node.val + ",");
		preDisplay(node.left);
		preDisplay(node.right);
	}

	private static void inDisplay(TreeNode node) {
		if (node == null) {
			return;
		}
		inDisplay(node.left);
		System.out.print(node.val + ",");
		inDisplay(node.right);
	}

	private static void postDisplay(TreeNode node) {
		if (node == null) {
			return;
		}
		postDisplay(node.left);
		postDisplay(node.right);
		System.out.print(node.val + ",");
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
