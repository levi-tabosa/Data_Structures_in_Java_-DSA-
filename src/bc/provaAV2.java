package bc;

import java.util.Scanner;

@SuppressWarnings("unchecked")
public class provaAV2 {

      public static void main(String[] args) throws Exception {
            try (Scanner sc = new Scanner(System.in)) {
                  FilaDinamica<String>[] a = new FilaDinamica[4];
                  
                  int prioridade = Integer.parseInt(sc.nextLine().charAt(1) + "");

                  loop: while (true) {
                        String linha = sc.nextLine();

                        switch (linha) {
                              case "0":
                                    break loop;
                              case "-4":
                                    prioridade = Integer.parseInt(linha.charAt(1) + "");
                                    break;
                              case "-3":
                                    prioridade = Integer.parseInt(linha.charAt(1) + "") - 1;
                                    break;
                              case "-2":
                                    prioridade = Integer.parseInt(linha.charAt(1) + "") + 1;
                                    break;
                              case "-1":
                                    prioridade = Integer.parseInt(linha.charAt(1) + "");
                                    break;
                              default: {
                                    if (a[prioridade - 1] == null)
                                          a[prioridade - 1] = new FilaDinamica<String>();
                                    a[prioridade - 1].enqueue(linha);
                              }
                        }
                  }
                  String res = "";
                  int i = 0;
                  loop2: while (true) {
                        String aux = "";

                        if (a[i].size() > 0)
                              aux += a[i].dequeue() + " ";

                        i = (i + 1) % 4;

                        if (aux.isEmpty()) {
                              boolean o = true;
                              for (int j = 0; j < 4; j++) {
                                    if (a[j].size() > 0)
                                          continue;
                                    break loop2;
                              }
                              if (o)
                                    break loop2;
                              else
                                    continue loop2;
                        } else
                              res += aux;
                  }
                  System.out.println(res);
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
            count = 0;
      }

      public int size() {
            return count;
      }

      public void enqueue(T elem) {
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
                  ret += aux.value + " ";
                  aux = aux.next;
            }

            return ret.substring(0, ret.length() - 1);
      }
}