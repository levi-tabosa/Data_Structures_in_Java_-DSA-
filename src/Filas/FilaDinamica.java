package Filas;

public class FilaDinamica<T> {
    Node<T> tail;
    Node<T> head;
    int count;

    public FilaDinamica() {

    }

    public int size(){
        return count;
    }
    public void enqueue(T elem) {
        Node<T> node = new Node<>(elem);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            Node<T> aux = tail;
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

        Node<T> aux = tail;

        while (aux != null) {
            ret += aux.value + ", ";
            aux = aux.next;
        }

        return ret.substring(0, ret.length() - 2);
    }
}