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

   public Node get(T elem) {
      return get(elem, root);
   }

   private Node get(T elem, Node node) {
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

   private Node remove(T elem, Node node) {
      if (node == null) {
         return null;
      }
      if (elem.equals(node.value)) {
         if (node.l == null || node.r == null) {
            node = (node.l != null) ? node.l : node.r;
         } else {
            T min = min(node.r);
            node.value = min;
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

   public String inorder() {
      return root != null ? TraverseInorder(root) : "";
   }

   public String preorder() {
      return root != null ? TraversePreorder(root) : "";
   }

   public String postorder() {
      return root != null ? TraversePostorder(root) : "";
   }

   private String TraverseInorder(Node node) {
      return node != null ? TraverseInorder(node.l) + " " + node.value + TraverseInorder(node.r) : "";
   }

   private String TraversePostorder(Node node) {
      return node != null ? TraversePostorder(node.r) + " " + node.value + TraversePostorder(node.l) : "";
   }

   private String TraversePreorder(Node node) {
      return node != null ? node.value + " " + TraversePreorder(node.l) + TraversePreorder(node.r) : "";
   }

   @Override
   public String toString() {
      return '[' + TraversePreorder(root) + ']';
   }

   class Node {
      T value;
      Node l, r;

      public Node(T value) {
         this.value = value;
      }
   }
}