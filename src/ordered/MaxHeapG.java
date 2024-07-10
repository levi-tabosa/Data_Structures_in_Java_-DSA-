package ordered;

public class MaxHeapG<T> {
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
            T aux = ((Node<T>) innerArray[0]).value;
            count--;
            innerArray[0] = innerArray[count];
            siftDown(0);
            
            return aux;
      }

      public void siftDown(int index) {
            if (index * 2 >= count) {
                  return;
            }
            int max;
            Node<T> nodeMax;
            Node<T> curr = (Node<T>) innerArray[index];
            Node<T> left = (Node<T>) innerArray[index << 1];
            Node<T> right = (Node<T>) innerArray[(index << 1) + 1];

            if(left.priority < right.priority){
                  max = (index << 1) + 1;
            } else {
                  max = index << 1;
            }
            
            nodeMax = (Node<T>)innerArray[max];

            if (nodeMax.priority > curr.priority) {
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
                  int parent = index >> 1;

                  if (((Node<T>) innerArray[index]).priority > ((Node<T>) innerArray[parent]).priority) {
                        Node<T> aux = (Node<T>) innerArray[parent];
                        innerArray[parent] = innerArray[index];
                        innerArray[index] = aux;
                        siftUp(parent);
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
