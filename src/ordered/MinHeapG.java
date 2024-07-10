package ordered;

public class MinHeapG<T> {
      Object[] innerArray;
      int count;

      public MinHeapG() {
            innerArray = new Object[10000];
            count = 0;
      }

      public void enqueue(T elem, int priority) {
            if(count == innerArray.length) {
                  System.out.println("heap cheio.");
                  return;
            }
            Node<T> aux = new Node<T>(elem, priority);
            innerArray[count] = aux;
            count++;
            siftUp(count - 1);
      }

      public T dequeue() {
            Node<T> aux = (Node<T>) innerArray[0];
            innerArray[0] = innerArray[count - 1];
            count--;
            siftDown(0);
            return aux.value;
      }

      public void siftDown(int index) {

            if (index * 2 >= count) {
                  return;
            }
            int min;
            Node<T> minNode; 
            Node<T> curr = (Node<T>) innerArray[index];
            Node<T> left = (Node<T>) innerArray[index * 2];
            Node<T> right = (Node<T>) innerArray[index * 2 + 1];

            if(left.priority > right.priority){
                  min = index * 2 + 1;
            } else {
                  min = index * 2;
            }
            
            minNode = (Node<T>)innerArray[min];

            if (minNode.priority < curr.priority) {
                  Object aux = innerArray[min];
                  innerArray[min] = curr;
                  innerArray[index] = aux;
                  siftDown(min);
            }

      }

      public T get(int index) {
            return ((Node<T>) innerArray[index]).value;
      }

      public void siftUp(int index) {
            if (index >= 0) {
                  int father = index / 2;

                  if (((Node<T>) innerArray[index]).priority < ((Node<T>) innerArray[father]).priority) {
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
      public boolean isEmpty(){
            return count == 0;
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