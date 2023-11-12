package Ordenadas;

public class MaxHeap {
      int[] innerArray;
      int c;

      public MaxHeap(int capacity) {
            innerArray = new int[capacity];
            c = 0;
      }

      public void add(int elem) {
            if (c == innerArray.length) {
                  System.out.println("heap cheio.");
            } else {
                  innerArray[c] = elem;
                  c++;
                  siftUp(c - 1);
            }
      }

      public void siftUp(int i) {
            if (i > 0) {
                  int f = i / 2;
                  if (innerArray[i] > innerArray[f]) {
                        int aux = innerArray[f];
                        innerArray[f] = innerArray[i];
                        innerArray[i] = aux;
                        siftUp(f);
                  }
            }
      }

      public int remove() {
            int aux = innerArray[0];
            c--;
            innerArray[0] = innerArray[c];
            siftDown(0);
            return aux;
      }

      public void siftDown(int i) {
            int max;
            int left = 2 * i;
            int right = 2 * i + 1;

            if (right > c) {
                  return;
            }

            if (innerArray[left] < innerArray[right]) {
                  max = right;
            } else {
                  max = left;
            }
            if (innerArray[i] < innerArray[max]) {
                  int aux = innerArray[max];
                  innerArray[max] = innerArray[i];
                  innerArray[i] = aux;
                  siftDown(max);
            }
      }

      public boolean isEmpty() {
            return c == 0;
      }

      @Override
      public String toString() {
            String ret = "";

            for (int i = 0; i < c; i++) {
                  ret += innerArray[i] + " ";
            }

            return ret;
      }

      public static void main(String[] args) {
            MaxHeap a = new MaxHeap(10);

            a.add(1);
            a.add(2);
            a.add(-3);
            a.add(6);
            a.add(4);
            a.add(3);
            a.add(-4);
            a.add(12);
            System.out.println(a);
            while (!a.isEmpty()) {
                  System.out.println(a.remove());
            }

      }

}