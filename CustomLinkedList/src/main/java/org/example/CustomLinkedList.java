package org.example;

import java.util.NoSuchElementException;

public class CustomLinkedList<E> {
    private static class Node<E> {
        E item;
        Node<E> prev, next;

        Node(E element){
            this.item = element;
        }
        Node(Node<E> prev, E element, Node<E> next){
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    private Node<E> first, last;
    private int size = 0;

    public int size(){
        return size;
    }

    public void addFirst(E el){
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, el, f);
        first = newNode;
        if(f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    public void addLast(E el){
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, el, null);
        last = newNode;
        if(l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    public void add(int index, E el){
        checkPositionIndex(index);

        if(index == 0)
            addFirst(el);
        else if(index == size)
            addLast(el);
        else{
            Node<E> current = getNode(index);
            Node<E> prevNode = current.prev;
            Node<E> newNode = new Node<>(prevNode, el, current);
            prevNode.next = newNode;
            current.prev = newNode;
            size++;
        }
    }


    public E getFirst(){
        checkNotEmpty();
        return first.item;
    }

    public E getLast(){
        checkNotEmpty();
        return last.item;
    }

    public E get(int index){
        checkElementIndex(index);
        return getNode(index).item;
    }

    public E removeFirst(){
        checkNotEmpty();
        E item = first.item;
        first = first.next;
        if(first != null)
            first.prev = null;
        else
            last = null;
        size--;
        return item;
    }

    public E removeLast(){
        checkNotEmpty();
        E item = last.item;
        last = last.prev;
        if(last != null)
            last.next = null;
        else
            first = null;
        size--;
        return item;
    }

    public E remove(int index){
        checkElementIndex(index);
        Node<E> node = getNode(index);
        E item = node.item;
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if(prev != null)
            prev.next = next;
        else
            first = next;

        if(next != null)
            next.prev = prev;
        else
            last = prev;

        node.prev = node.next = null;
        node.item = null;

        size--;
        return item;
    }

    private Node<E> getNode(int index){
        Node<E> current;
        if(index < (size / 2)){
            current = first;
            for(int i = 0; i < index; i++)
                current = current.next;
        }else{
            current = last;
            for(int i = size - 1; i > index; i--)
                current = current.prev;
        }
        return current;
    }

    private void checkElementIndex(int index){
        if(!(index >= 0 && index < size)) throw new IndexOutOfBoundsException("Index: " + index);
    }

    private void checkPositionIndex(int index){
        if(!(index >= 0 && index <= size)) throw  new IndexOutOfBoundsException("Index: " + index);
    }

    private void checkNotEmpty(){
        if(size == 0) throw new NoSuchElementException("List is empty");
    }
}
