package Trees;

public class BST<T extends Comparable<T>> {
      Node root;

      public void add(T elem) {
            if (root == null) {
                  root = new Node(elem);
            } else {
                  add(elem, root);
            }
      }

      private void add(T elem, Node node) {
            if (elem.compareTo(node.value) > 0) {
                  if (node.r == null)
                        node.r = new Node(elem);
                  else
                        add(elem, node.r);
            } else {
                  if (node.l == null)
                        node.l = new Node(elem);
                  else
                        add(elem, node.l);
            }
      }

      public Node get(T elem, Node node) {
            if (node.value.equals(elem))
                  return node;

            if (elem.compareTo(node.value) > 0) {
                  Node aux = get(elem, node.r);
                  if (aux != null)
                        return aux;
            }
            return get(elem, node.l);
      }

      public void remove(T elem) {
            if (root != null)
                  root = remove(elem, root);
      }

      public Node remove(T elem, Node node) {
		if(node == null) {
			return null;
		}
		if (elem.equals(node.value)) {
			if (node.l == null || node.r == null) {
				node = (node.l != null) ? node.l : node.r;
		   } else {
				T 
                        min = min(node.r);
				node.value = 
                        min;
				node.l = remove(
                              min, node.l);
			}
		} else if (elem.compareTo(node.value) > 0) {
			node.r = remove(elem, node.r);
		} else {
			node.l = remove(elem, node.l);
		}
		return node;
	}

      private T min(Node node) {
            if (node.l == null)
                  return node.value;
            return min(node.l);
      }

      public String printaArvre(int i) {
            if (i > 0) return posOrdem(root);
            if (i < 0) return emOrdem(root);
            return preOrdem(root);
      }

      private String emOrdem(Node node) {
            String aux = node.value.toString();
            if (node.l != null) {
                  aux = emOrdem(node.l) + " " + aux;
            }
            if (node.r != null) {
                  aux += " " + emOrdem(node.r);
            }

            return aux;
      }

      private String posOrdem(Node node) {
            String aux = node.value.toString();
            if (node.r != null) {
                  aux = posOrdem(node.r) + " " + aux;
            
            }
            if (node.l != null) {
                  aux = posOrdem(node.l) + " " + aux;
            }

            return aux;
      }

      @Override
      public String toString() {
            return '[' + preOrdem(root) + ']';
      }

      private String preOrdem(Node node) {
            String aux = node.value + "";

            if (node.l != null) {
                  aux += " " + preOrdem(node.l);
            }
            if (node.r != null) {
                  aux += " " + preOrdem(node.r);
            }
            return aux;
      }

      public static void main(String[] args) {
            BST<Integer> a = new BST<>();

            a.add(6);
            a.add(8);
            a.add(2);
            a.add(4);
            a.add(1);
            a.add(3);
            a.add(9);

            System.out.println(a.emOrdem(a.root));
            System.out.println(a.preOrdem(a.root));
            System.out.println(a.posOrdem(a.root));

            System.out.println();

            a.remove(6);
            a.remove(4);

            System.out.println(a.emOrdem(a.root));
            System.out.println(a.preOrdem(a.root));
            System.out.println(a.posOrdem(a.root));

            // System.out.println(a.busca(9, a.raiz).value);
            // System.out.println(a.printaArvre(1));

      }
      class Node {
            T value;
            Node l, r;
      
            public Node(T value) {
                  this.value = value;
            }
      }
}