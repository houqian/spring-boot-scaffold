package org.houqian.core.bootstrap;

import org.houqian.core.source.collection.ArrayList;

import java.util.List;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-06-17
 */
public class ArrayListBS {

  public static void main(String[] args) {
    // ------------------------无参数构造器实例化--------------------------
    // 0. ArrayList 是如何实例化的？
    // 0.1 无参数构造器
    /** 直接开辟一个空数组
     */
    List<String> list = new ArrayList<>();

    /**
     * 如何添加元素的？
     *
     * 计算 Capcity
     *  如果当前数组长度为 0，并且 minCap（即用户指定 ArrayList 初始容量）为 1（这里会对指定容量做+1操作）
     *  那么，将当前 capcity 设置为Math.max(DEFAULT_CAPACITY, minCapacity)
     *
     * if (minCapacity - elementData.length > 0)
     *  扩容
     *    newCap = 1.5 * oldCap
     *    if newCap < minCap
     *      newCap = minCap
     *    将 old 数组的元素拷贝到新数组
     *
     * 赋值
     *  eleArr[size++] = 传进来的元素
     *
     */
    list.add("1");

    /**
     * 如何判断元素是否存在？
     *
     * 实际上调用 indexOf 方法
     * 如果传入 null
     *  用 eleArr[i] == null 判断，true 就返回
     * 如果传入不为 null
     *  用 equals 判断
     *
     * 总结，其实就是为了防止空指针异常。这里用的是遍历数组查找
     *
     * 时间复杂度 O(N)
     */
    list.contains("1");

    /**
     * 如何实现 fail-fast？
     * 记录一个 modCount，每次修改数组modCount+1
     * 在需要 fail-fast 的方法体中：
     *  expectedModCount = modCount;
     *  xxxxx modCount ++；
     *  if(expectedModCount != modCount) throw ConcurrentModificationException
     */

    /**
     * subList 是如何实现的？
     *  检测长度
     *    fromIdx < 0 || toIndex > currentArrayListSize ,抛出IndexOutOfBoundsException
     *    fromIdx > toIdx , 抛出IllegalArgumentException
     *
     *  创建视图
     *  其实是 new Sublist(当前 ArrayList 实例, fromIdx, toIdx)
     *  与 ArrayList 继承了相同的抽象类，实现了相同的方法
     *  private class SubList extends AbstractList<E> implements RandomAccess
     *
     *    维护 Offset，lastRef
     *
     *
     */
    List<String> subList = list.subList(0, 1);


    // ------------------------指定初始化容量实例化--------------------------
    List<String> list2 = new ArrayList<>(1);

    list.add("2");
  }
}
