package com.dataStructure;

import java.util.NoSuchElementException;

public class MyLinkedList<E> {

    private int size;

    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    final private Node<E> head, tail;

    // 构造函数初始化虚拟头尾节点
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = tail.prev;
        /// temp <-> tail
        temp.next = x;
        x.next = tail;
        tail.prev = x;
        x.prev = temp;
        size++;
    }

    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        // head <-> x <-> temp
        Node<E> temp = head.next;
        head.next = x;
        x.next = temp;
        temp.prev = x;
        x.prev = head;
        size++;
    }

    public void add(int index, E e) {
        checkPositionIndex(index);
        if (size == index) {
            addLast(e);
            return;
        }
        Node<E> x = new Node<>(e);
        Node<E> p = getNode(index);
        Node<E> temp = p.prev;
        x.next = p;
        temp.next = x;
        p.prev = x;
        x.prev = temp;
        size++;
    }

    public E removeFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> first = head.next;
        E removeValue = first.val;
        head.next = first.next;
        first.next.prev = head;
        first.next = null;
        first.prev = null;
        size--;
        return removeValue;
    }

    public E removeLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> last = tail.prev;
        E removeValue = last.val;
        Node<E> x = last.prev;
        x.next = tail;
        tail.prev = x;
        last.next = null;
        last.prev = null;
        size--;
        return removeValue;
    }

    public E remove(int index) {
        checkPositionIndex(index);
        Node<E> node = getNode(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        // prev <-> x <-> next
        prev.next = next;
        next.prev = prev;
        node.prev = null;
        node.next = null;
        size--;
        return node.val;
    }

    public E get(int index) {
        checkElementIndex(index);
        Node<E> node = getNode(index);
        return node.val;
    }

    public E getFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        return head.next.val;
    }

    public E getLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        return tail.prev.val;
    }

    public E set(int index, E val) {
        checkElementIndex(index);
        // 找到 index 对应的 Node
        Node<E> p = getNode(index);

        E oldVal = p.val;
        p.val = val;

        return oldVal;
    }

    // ***** 其他工具函数 *****

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;
        // TODO: 可以优化，通过 index 判断从 head 还是 tail 开始遍历
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    // 检查 index 索引位置是否可以存在元素
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    private void display() {
        System.out.println("size = " + size);
        for (Node<E> p = head.next; p != tail; p = p.next) {
            System.out.print(p.val + " <-> ");
        }
        System.out.println("null");
        System.out.println();
    }
    // 检查 index 索引位置是否可以添加元素
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(0);
        list.add(2, 100);

        list.display();
        // size = 5
        // 0 <-> 1 <-> 100 <-> 2 -> 3 -> null
    }


}
