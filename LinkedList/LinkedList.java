package homework.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

// LinkedList 노드 클래스
class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// LinkedList 클래스
public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // 링크드 리스트의 마지막에 데이터를 추가
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // 주어진 인덱스의 노드 데이터를 반환
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // 주어진 인덱스의 노드를 삭제
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    // Iterator 구현
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    // 큐 구현
    public void enqueue(T data) {
        add(data);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    // 스택 구현
    public void push(T data) {
        add(data);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T data = get(size - 1);
        delete(size - 1);
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // LinkedList 순회
        System.out.println("LinkedList 순회:");
        for (int num : list) {
            System.out.println(num);
        }
        list.add(6);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.println(element);
        }

        // 큐 사용
        LinkedList<Integer> queue = new LinkedList<>();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.dequeue();
        System.out.println("Queue:");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }

        // 스택 사용
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.pop();
        System.out.println("Stack:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
