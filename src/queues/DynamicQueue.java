package queues;

public class DynamicQueue<T> {
    Node tail;
    Node head;
    int count;

    public DynamicQueue() {}

    public int size() {
        return count;
    }

    public void enqueue(T elem) {
        Node node = new Node(elem);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            Node aux = tail;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = node;
            head = node;
        }
        count++;
    }

    public T dequeue() {
        count--;
        T aux = tail.value;
        tail = tail.next;
        return aux;
    }

    public T front() {
        return tail.value;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public String toString() {
        String ret = "";

        Node aux = tail;

        while (aux != null) {
            ret += aux.value + ", ";
            aux = aux.next;
        }

        return ret.substring(0, ret.length() - 2);
    }

    private class Node {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
        }
    }

}