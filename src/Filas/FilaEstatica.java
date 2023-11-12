package Filas;
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
        if (tail <= head) {
            return head - tail;
        }
        
        return innerArray.length - tail + head;
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
    public static void main(String[] args) {
        FilaEstatica<Integer> a = new FilaEstatica<>();

        System.out.println(a.size());

        a.enqueue(0);
        a.enqueue(0);
        a.enqueue(0);
        a.enqueue(0);

        System.out.println(a.size());
        
        a.dequeue();
        a.dequeue();
        a.dequeue();
        a.dequeue();

        System.out.println(a.size());

        a.enqueue(0);
        a.enqueue(0);

        System.out.println(a.size());
    }
}