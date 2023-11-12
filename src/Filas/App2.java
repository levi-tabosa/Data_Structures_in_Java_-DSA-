package Filas;

public class App2 {

    public static void main(String[] args) throws Exception {
        FilaEstatica<Integer> fila = new FilaEstatica<>();

        System.out.println("Fila está vazia? " + fila.isEmpty()); // Deve imprimir true
        System.out.println("Tamanho da fila: " + fila.size()); // Deve imprimir 0

        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        fila.enqueue(4);
        System.out.println("Fila após adicionar 4 elementos: " + fila); // Deve imprimir [1, 2, 3, 4]
        System.out.println("Frente da fila: " + fila.front()); // Deve imprimir 1

        fila.dequeue();
        fila.dequeue();
        System.out.println("Fila após remover um elemento: " + fila); // Deve imprimir [2, 3, 4]
        System.out.println("Tamanho da fila após remoção: " + fila.size()); // Deve imprimir 3

        fila.enqueue(5);
        //fila.enqueue(6);
        System.out.println("Fila após adicionar 2 elementos: " + fila); // Deve imprimir [2, 3, 4, 5, 6]
        System.out.println("Posição do elemento 5 na fila: " + fila.indexOf(5)); // Deve imprimir 2
        System.out.println(fila.innerArray[0]);
        System.out.println("Fila está vazia? " + fila.isEmpty()); // Deve imprimir false
        System.out.println("Tamanho da fila: " + fila.size()); // Deve imprimir 5
    }
}
@SuppressWarnings("unchecked")
class FilaEstatica<T> {
    int tail, head;
    Object[] innerArray;

    public FilaEstatica(int capacity) {
        tail = 0;
        head = 0;
        innerArray = new Object[capacity];
    }

    public FilaEstatica() {
        tail = 0;
        head = 0;
        innerArray = new Object[4];
    }

    public void clear() {
        tail = 0;
        head = 0;
    }

    public int size() {
        int currCapacity = innerArray.length;
        if (tail <= head) {
            return head - tail;
        }
        if (head < 0)
            return 0;
        return currCapacity - tail + head;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(T elem) {
        int currCapacity = innerArray.length;

        if (head == currCapacity) {
            if (tail == 0) {
                Object[] aux = new Object[currCapacity + currCapacity / 2];

                for (int i = tail; i < head; i++) {
                    aux[i] = innerArray[i];
                }
                aux[head] = elem;
                innerArray = aux;
            } else {
                head = 0;
                innerArray[head] = elem;
            }
        } else if (head == tail - 1 && tail != 0) {
            System.out.println("pley");
            Object[] aux = new Object[currCapacity + currCapacity / 2];

            for (int i = 0; i < currCapacity - tail; i++) {
                aux[i] = innerArray[i + tail];
            }
            for (int i = 0; i < tail; i++) {
                aux[currCapacity - tail + i] = innerArray[i];
            }
            tail = 0;
            head = currCapacity - 1;
            aux[head] = elem;
            innerArray = aux;
        } else {
            innerArray[head] = elem;
        }
        head++;
    }

    public T dequeue() {
        tail++;
        return (T) innerArray[tail - 1];
    }

    public T front() {
        return (T) innerArray[tail];
    }

    @Override
    public String toString() {
        String aux = "";

        if (head > tail) {
            for (int i = tail; i < head; i++) {
                aux += innerArray[i] + " ";
            }
        } else {
            for (int i = tail; i < innerArray.length; i++) {
                aux += innerArray[i] + " ";
            }
            for (int i = 0; i < head; i++) {
                aux += innerArray[i] + " ";
            }
        }
        return aux.substring(0, aux.length() - 1);
    }

    public int indexOf(T elem) {
        if (head > tail) {
            for (int i = tail; i < head; i++) {
                if (innerArray[i].equals(elem)) {
                    return i - tail;
                }
            }
        } else {
            for (int i = tail; i < innerArray.length; i++) {
                if (innerArray[i].equals(elem)) {
                    return i - tail;
                }
            }
            for (int i = 0; i < head; i++) {
                if (innerArray[i].equals(elem)) {
                    return innerArray.length - i - tail;
                }
            }
        }
        return -1;
    }
}