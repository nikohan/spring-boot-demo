package com.test.demo.nowcoder;

/**
 * https://www.nowcoder.com/ta/coding-interviews
 * nowcoder offer2:
 * 	请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 *
 *
 * Created by zhaohan on 2019/5/8.
 */
public class Solution2 {

	public static void main(String[] args) {
		StringBuffer str = new StringBuffer("   ");
		System.out.println("结果：" + replaceSpace(str));
	}

	public static String replaceSpace(StringBuffer str) {
		if (str == null) {
			return null;
		}

		int len = str.length();

		StringBuffer newStr = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (c == ' ') {
				newStr.append("%20");
			} else {
				newStr.append(c);
			}
		}

		return newStr.toString();
	}
}
