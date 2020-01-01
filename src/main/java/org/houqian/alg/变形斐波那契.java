package org.houqian.alg;

/**
 * 动态规划法解题
 * 先用递归思想找到规律（自顶向下）
 * 再用迭代法实现（自底向上）
 *
 * @author : houqian
 * @version : 1.0
 * @since : 2019-06-06
 */
public class 变形斐波那契 {

  public static void main(String[] args) {
    System.out.println(theOtherFab(100000));
  }

  /**
   * 题目：
   * 自己瞎编的
   * <p>
   * 有这么一个数列，前三项是0, 1, 2，第四项开始f(n) = f(n - 1) + f(n - 2) + f(n - 3)
   * 现在需要你写这样一个函数，输入是序列号，输出是该序列号对应的值
   * <p>
   * 示例：
   * 序列号: 0 1 2 3 4 5  6
   * 值   : 0 1 2 3 6 11 20
   *
   * @param no 第n项的序列号n
   * @return
   */
  public static long theOtherFab(int no) {
    long f = 0;
    long g = 1;
    long k = 2;
    long b = 3;

    if (no < 4) throw new IllegalArgumentException("非法输入，必须大于3!");
    if (no == 4) return b;

    while (4 < no--) {
      b = g + k + b;
      k = b - g - k;
      g = b - k - g;
      f = b - k - f;
    }
    return b;
  }
}
