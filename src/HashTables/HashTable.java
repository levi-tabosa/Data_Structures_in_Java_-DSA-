package HashTables;

public class HashTable<T> {
   LinkedList[] innerArray;
   int count;

   public HashTable() {
      innerArray = new LinkedList[7];
      count = 0;
   }

   public void add(T elem) {
      int index = h(elem);

      if (innerArray[index] == null)
         innerArray[index] = new LinkedList<T>();

      innerArray[index].add(elem);
   }

   public void remove(T elem) {
      int index = h(elem);
      innerArray[index].remove(elem);
   }

   public boolean Contains(T elem) {
      for (LinkedList<T> listaDinamica : innerArray) {
         if (listaDinamica == null)
            continue;
         if (listaDinamica.Contains(elem))
            return true;
      }
      return false;
   }

   private int h(T elem) {
      int index = 0;
      if (elem instanceof String) {
         char[] arr = ((String) elem).toCharArray();
         int n = ((String) elem).length();

         for (int i = 0; i < n; i++) {
            index += i + arr[i] - 65;
         }
      } else if (elem instanceof Integer) {
         index = (int) elem % innerArray.length;
      } else {
         index = -1;
      }
      return index;
   }

   @Override
   public String toString() {
      String ret = "";

      for (LinkedList<T> list : innerArray) {
         if (list == null)
            continue;
         ret += list;
      }
      if (ret.isEmpty())
         return "[]";
      return '[' + ret.substring(0, ret.length() - 1) + ']';
   }
}

class LinkedList<T> {
   Node<T> head, tail;

   public LinkedList() {
   }

   public void add(T elem) {
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
   }

   public T removeTail() {
      T aux = tail.value;
      tail = tail.next;
      return aux;
   }

   public T remove(T elem) {
      if (tail.value == elem) {
         return removeTail();
      }
      Node<T> aux = tail;
      while (aux.next != null) {
         if (aux.next.value == elem) {
            T value = aux.next.value;
            aux.next = aux.next.next;
            return value;
         }
         aux = aux.next;
      }
      return null;
   }

   public boolean isEmpty() {
      return tail == null;
   }

   public boolean Contains(T elem) {
      Node<T> aux = tail;
      while (aux != null) {
         if (aux.value == elem) {
            return true;
         }
         aux = aux.next;
      }
      return false;
   }

   public int indexOf(T elem) {
      int count = 0;
      Node<T> aux = tail;
      while (aux != null) {
         if (aux.value == elem) {
            return count;
         }
         aux = aux.next;
         count++;
      }
      return -1;
   }

   public void clear() {
      tail = null;
      head = null;
   }

   @Override
   public String toString() {
      String ret = "";

      Node<T> aux = tail;
      while (aux != null) {
         ret += aux.value + " ";
         aux = aux.next;
      }

      return ret;
   }
}

class Node<T> {
   T value;
   Node<T> next;

   public Node(T value) {
      this.value = value;
   }
}