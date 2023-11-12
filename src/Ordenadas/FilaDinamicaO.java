package Ordenadas;

public class FilaDinamicaO<T> {
      Node<T> tail, head;

      public FilaDinamicaO()
      {
      }

      public void clear() {
      }

      public void enqueue(T elem, int priority){
            Node<T> node = new Node<>(elem, priority);
            
            if(tail == null){
                  tail = node;
                  head = node;
            } else if(tail.priority < node.priority){
                  node.next = tail;
                  tail = node;
            } else if(head.priority >= node.priority){
                  head.next = node;
                  head = node;
            } else {
                  Node<T> aux = tail;
                  while(aux.next != null && node.priority <= aux.next.priority){
                        aux = aux.next;
                  }
                  node.next = aux.next;
                  aux.next = node;
            }
      }

      public T dequeue(){    
            T aux = tail.value;
            tail = tail.next;
            return aux;
      }
      @Override
      public String toString(){
            
            String ret = "";

            Node<T> aux = tail;

            while(aux != null){
                  ret += aux.value + ", ";
                  aux = aux.next;
            }

            return '['+ret.substring(0, ret.length()-2)+']';
            
      }
      public static void main(String[] args) {
            FilaDinamicaO<Integer> a = new FilaDinamicaO<>();

            a.enqueue(1, 1);
            a.enqueue(3, 3);
            a.enqueue(2, 2);
            a.enqueue(4, 4);
            a.enqueue(99, 2);

            System.out.println(a);
      }

}
class Node<T>{
      T value;
      int priority;
      Node<T> next;
      public Node(T value, int priority) {
            this.value = value;
            this.priority = priority;
      }
      @Override
      public String toString(){
            return "(" + value + ", " + priority + ")";
      }
      
}
