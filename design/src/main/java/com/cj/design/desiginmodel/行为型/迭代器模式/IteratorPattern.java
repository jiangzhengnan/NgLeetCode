package com.cj.design.desiginmodel.行为型.迭代器模式;

import java.util.NoSuchElementException;

/**
 * 概念：
 * 迭代器是用来遍历容器的，所以，一个完整的迭代器模式一般会涉及容器和容器迭代器两部分内容。
 * 为了达到基于接口而非实现编程的目的，容器又包含容器接口、容器实现类，迭代器又包含迭代器接口、迭代器实现类。
 */
public class IteratorPattern {

    public interface Iterator<E> {
        boolean hasNext();
        void next();
        E currentItem();
    }

    static class ArrayIterator<E> implements Iterator<E> {
        private int cursor;
        private ArrayList<E> arrayList;

        public ArrayIterator(ArrayList<E> arrayList) {
            this.cursor = 0;
            this.arrayList = arrayList;
        }

        @Override
        public boolean hasNext() {
            return cursor != arrayList.size(); //注意这里，cursor在指向最后一个元素的时候，hasNext()仍旧返回true。
        }

        @Override
        public void next() {
            cursor++;
        }

        @Override
        public E currentItem() {
            if (cursor >= arrayList.size()) {
                throw new NoSuchElementException();
            }
            return arrayList.get(cursor);
        }
    }

    public interface List<E> {
        Iterator iterator();
        //...省略其他接口函数...

        void add(E e);

        int size();

        E get(int index);
    }

    static class ArrayList<E> implements List<E> {
        //...
        public Iterator iterator() {
            return new ArrayIterator(this);
        }

        @Override
        public void add(final E e) {
            //...
        }

        @Override
        public int size() {
            //...
            return 0;
        }

        @Override
        public E get(int index) {
            return null;
        }
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("xzg");
        names.add("wang");
        names.add("zheng");

        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }

}
