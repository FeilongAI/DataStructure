package com.dataStructure;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class DynamicArray<E> {
    private E[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 1;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }
    public DynamicArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }
    public  void addLast(E e) {
        int cap = data.length;
        if(size == cap){
            resize(cap * 2);
        }
        data[size++] = e;
    }
    public void add(int index,E e){
        checkPositionIndex(index);
        int cap = data.length;
        if(size == cap){
            resize(cap * 2);
        }
        for(int i = size - 1; i >= index; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }
    public void addFirst(E e){
        add(0,e);
    }
    public E removeLast(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        int cap = data.length;
        if(size == cap/4){
            resize(cap/2);
        }
        E removeValue = data[size-1];
        data[size-1] = null;
        size--;
        return removeValue;
    }
    public E remove(int index){
        checkElementIndex(index);
        int cap = data.length;
        if (size == cap / 4) {
            resize(cap / 2);
        }
        E removeValue = data[index];
        for(int i = index; i < size-1; i++){
            data[i] = data[i+1];
        }
        data[size - 1] = null;
        size--;
        return removeValue;
    }
    public E removeFirst(){
        return remove(0);
    }
    public E get(int index){
        checkElementIndex(index);
        return data[index];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public E set(int index,E e){
        checkElementIndex(index);
        E oldValue = data[index];
        data[index] = e;
        return oldValue;
    }
    private void resize(int capacity){
       E[] newData = (E[]) new Object[capacity];
       for(int i = 0; i < size; i++){
           newData[i] = data[i];
       }
       data = newData;
    }
    private void checkElementIndex(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }
    private void checkPositionIndex(int index) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
    }
    private void display(){
        System.out.println("size = " + size + " cap = " + data.length);
        System.out.println(Arrays.toString(data));
    }
    public static void main(String[] args) {
        // 初始容量设置为 3
        DynamicArray<Integer> arr = new DynamicArray<>(3);

        // 添加 5 个元素
        for (int i = 1; i <= 5; i++) {
            arr.addLast(i);
        }

        arr.remove(3);
        arr.add(1, 9);
        arr.addFirst(100);
        int val = arr.removeLast();


        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }


}
