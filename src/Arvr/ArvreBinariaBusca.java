package Arvr;

public class ArvreBinariaBusca<T extends Comparable<T>> {

      Node<T> raiz;

      public void add(T elem) {
            if (raiz == null) {
                  raiz = new Node<>(elem);
            } else {
                  add(elem, raiz);
            }
      }

      private void add(T elem, Node<T> node) {
            if (elem.compareTo(node.value) > 0) {
                  if (node.dir == null)
                        node.dir = new Node<>(elem);
                  else
                        add(elem, node.dir);
            } else {
                  if (node.esq == null)
                        node.esq = new Node<>(elem);
                  else
                        add(elem, node.esq);
            }
      }

      public Node<T> busca(T elem, Node<T> node) {
            if (node.value.equals(elem))
                  return node;

            if (elem.compareTo(node.value) > 0) {
                  Node<T> aux = busca(elem, node.dir);
                  if (aux != null)
                        return aux;
            }
            return busca(elem, node.esq);
      }

      public void remove(T elem) {
            if (raiz != null)
                  remove(elem, raiz, null);
      }

      private void remove(T elem, Node<T> node, Node<T> f) {
            if (elem.equals(node.value)) {
                  if (node.esq == null && node.dir == null) {
                        if (node == f.dir)
                              f.dir = null;
                        else
                              f.esq = null;
                  } else if (node.dir == null) {
                        if (node == f.dir)
                              f.dir = node.esq;
                        else
                              f.esq = node.esq;
                  } else if (node.esq == null) {
                        if (node == f.dir)
                              f.dir = node.dir;
                        else
                              f.esq = node.dir;
                  } else {
                        // node.value = max(node.esq);
                        // remove(node.value, node.esq, node);
                        node.value = min(node.dir);
                        remove(node.value, node.dir, node);
                  }
            } else if (elem.compareTo(node.value) > 0) {
                  remove(elem, node.dir, node);
            } else
                  remove(elem, node.esq, node);
      }

      private T min(Node<T> node) {
            if (node.esq == null)
                  return node.value;
            return min(node.esq);
      }
      /*
       * private T max(Node<T> node) {
       * if (node.dir == null)
       * return node.value;
       * return max(node.dir);
       * }
       */

      public String printaArvre(int i) {
            if (i > 0)
                  return posOrdem(raiz);
            if (i < 0)
                  return emOrdem(raiz);

            return preOrdem(raiz);
      }

      private String emOrdem(Node<T> node) {
            String aux = node.value.toString();
            if (node.esq != null) {
                  aux = emOrdem(node.esq) + " " + aux;
            }
            if (node.dir != null) {
                  aux += " " + emOrdem(node.dir);
            }

            return aux;
      }

      private String posOrdem(Node<T> node) {
            String aux = node.value.toString();
            if (node.dir != null) {
                  aux = posOrdem(node.dir) + " " + aux;
            
            }
            if (node.esq != null) {
                  aux = posOrdem(node.esq) + " " + aux;
            }

            return aux;
      }

      @Override
      public String toString() {
            return '[' + preOrdem(raiz) + ']';
      }

      private String preOrdem(Node<T> node) {
            String aux = node.value + "";

            if (node.esq != null) {
                  aux += " " + preOrdem(node.esq);
            }
            if (node.dir != null) {
                  aux += " " + preOrdem(node.dir);
            }
            return aux;
      }

      public static void main(String[] args) {
            ArvreBinariaBusca<Integer> a = new ArvreBinariaBusca<>();

            a.add(6);
            a.add(8);
            a.add(2);
            a.add(4);
            a.add(1);
            a.add(3);
            a.add(9);

            System.out.println(a.emOrdem(a.raiz));
            System.out.println(a.preOrdem(a.raiz));
            System.out.println(a.posOrdem(a.raiz));

            System.out.println();

            a.remove(6);
            a.remove(4);

            System.out.println(a.emOrdem(a.raiz));
            System.out.println(a.preOrdem(a.raiz));
            System.out.println(a.posOrdem(a.raiz));

            // System.out.println(a.busca(9, a.raiz).value);
            // System.out.println(a.printaArvre(1));

      }
}

/**
 * Node<T>
 */
class Node<T> {
      T value;
      Node<T> esq, dir, f;

      public Node(T value) {
            this.value = value;
      }
}