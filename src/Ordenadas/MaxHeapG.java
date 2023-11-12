package Ordenadas;

import java.util.Random;
@SuppressWarnings("unchecked")
public class MaxHeapG<T> {
      public static void main(String[] args) {
            
            MaxHeapG<Integer> a = new MaxHeapG<>(99);
            Random random = new Random();
            while(a.count < a.innerArray.length){
                  
                  int n = random.nextInt();
                  a.enqueue(n, n);
            }


            System.out.println(a);

            while (!a.isEmpty()) {
                  for (int i = 0; i < a.count; i++) {
                        System.out.println(a.innerArray[i]);
                  }
                  System.out.println(a.dequeue());
            }
      }

      Object[] innerArray;
      int count;
      public MaxHeapG() {
            innerArray = new Object[10];
            count = 0;
      }

      public MaxHeapG(int capacity) {
            innerArray = new Object[capacity];
            count = 0;
      }

      public void enqueue(T elem, int priority) {
            Node<T> aux = new Node<T>(elem, priority);
            innerArray[count] = aux;
            count++;
            siftUp(count - 1);
      }

      public boolean isEmpty() {
            return count == 0;
      }

      public T dequeue() {
            Node<T> aux = (Node<T>) innerArray[0];
            innerArray[0] = innerArray[count - 1];
            count--;
            siftDown(0);
            // siftUp(aux);
            return aux.value;
      }

      public void siftDown(int index) {
            if (index * 2 >= count) {
                  return;
            }
            int max;
            Node<T> nmax;
            Node<T> curr = (Node<T>) innerArray[index];
            Node<T> left = (Node<T>) innerArray[index * 2];
            Node<T> right = (Node<T>) innerArray[index * 2 + 1];

            if(left.priority < right.priority){
                  max = index * 2 + 1;
            } else {
                  max = index * 2;
            }
            
            nmax = (Node<T>)innerArray[max];

            if (nmax.priority > curr.priority) {
                  Object aux = innerArray[max];
                  innerArray[max] = curr;
                  innerArray[index] = aux;
                  siftDown(max);
            }

      }

      public T get(int index) {
            return ((Node<T>) innerArray[index]).value;
      }

      public void siftUp(int index) {
            if (index >= 0) {
                  int father = index / 2;

                  if (((Node<T>) innerArray[index]).priority > ((Node<T>) innerArray[father]).priority) {
                        Node<T> aux = (Node<T>) innerArray[father];
                        innerArray[father] = innerArray[index];
                        innerArray[index] = aux;
                        siftUp(father);
                  }
            }
      }

      @Override
      public String toString() {
            String ret = "";

            for (int i = 0; i < count; i++) {

                  ret += ((Node<T>) innerArray[i]).value + " ";
            }

            return '[' + ret.substring(0, ret.length() - 1) + ']';
      }
}

class Node<T> {
      T value;
      int priority;

      public Node(T value, int p) {
            this.value = value;
            this.priority = p;
      }
      @Override
      public String toString(){
            return "(" + value + ", " + priority + ")";
      }
}
