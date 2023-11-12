package bc;

import java.util.Scanner;

public class App2 {// Lista hash questao 1
      public static void main(String[] args) {
            System.out.println();
      }
}

@SuppressWarnings("unchecked")
class HashTablela<T> {
      ListaDinamica<T>[] innerArray;
      int count;

      public HashTablela() {
            innerArray = new ListaDinamica[7];
            count = 0;
      }

      public HashTablela(int capacity) {
            innerArray = new ListaDinamica[capacity];
            count = 0;
      }

      public void add(T elem) {
            int index = h(elem);

            if (innerArray[index] == null) {
                  innerArray[index] = new ListaDinamica<T>();

            }
            innerArray[index].add(elem);
      }

      public boolean Contains(T elem) {
            for (ListaDinamica<T> listaDinamica : innerArray) {
                  if (listaDinamica == null)
                        continue;
                  if (listaDinamica.Contains(elem))
                        return true;
            }
            return false;
      }

      public int h(T n) {
            int index = 0;
            if (n instanceof String) {
                  char[] a = ((String) n).toCharArray();
                  int c = ((String) n).length();

                  for (int i = 0; i < c; i++) {
                        index += i + a[i] - 65;
                  }
            } else if (n instanceof Integer) {
                  index = (int) n % innerArray.length;
            } else {
                  index = -1;
            }
            return index;
      }

      public void remove(T elem) {
            int index = h(elem);
            innerArray[index].remove(elem);
      }

      @Override
      public String toString() {
            String ret = "";
            int n = innerArray.length;
            for (int i = 0; i < n; i++) {
                  ret += i + " -> ";
                  if (innerArray[i] == null){
                        ret += "\u005c\\n";
                  } else {
                        String[] aux = innerArray[i].toString().split(" ");
                        for (String string : aux) {
                              ret += string + " -> ";
                        }
                        ret = ret + "\u005c\\n";
                  }
            }
            if (ret.isEmpty())
                  return "[]";
            return ret.substring(0, ret.length() - 1);
      }

}

class ListaDinamica<T> {
      Node<T> head, tail;

      public ListaDinamica() {
      }

      public void add(T elem) {
            Node<T> newNode = new Node<>(elem);
            if (tail == null) {
                  tail = newNode;
                  head = newNode;
            } else {
                  Node<T> aux = tail;
                  while (aux.next != null) {
                        aux = aux.next;
                  }
                  aux.next = newNode;
                  head = newNode;
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