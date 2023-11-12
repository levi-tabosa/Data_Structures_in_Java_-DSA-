package Filas;

import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            for (;;) {
                int n = Integer.parseInt(sc.nextLine());
                
                if(n == 0) break;
                
                FilaDinamica<Integer> cartas = new FilaDinamica<>();
                String cartasDescartadas = "";

                for (int i = 0; i < n; i++) {
                    cartas.enqueue(i + 1);
                }

                while(cartas.size() > 1){
                    cartasDescartadas += cartas.dequeue() + ", ";
                    cartas.enqueue(cartas.dequeue());
                }
                System.out.println("Discarded cards : " + cartasDescartadas.substring(0, cartasDescartadas.length()-2));
                System.out.println("Remaining card : " + cartas.dequeue());
            }
        }
    }
}

class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}

class FilaDinamica<T> {
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
        if (head == null) {
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