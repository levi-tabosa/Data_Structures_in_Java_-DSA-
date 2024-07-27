package Trees;

class AVL<T extends Comparable<T>> {
	Node root;

	public void add(T elem) {
		root = add(elem, root);
	}

	public Node add(T elem, Node node) {
		if (node == null) {
			return new Node(elem);
		}
		if (elem.compareTo(node.value) > 0) {
			node.r = add(elem, node.r);
			// } else if(elem.compareTo(node.value) < 0) no repeat key
		} else {
			node.l = add(elem, node.l);
		}
		return balance(node);
	}

	public Node balance(Node node) {
		if (node == null) {
			return null;
		}

		int bf = getbf(node);

		if (bf < -1) {
			if (getbf(node.l) > 0) {
				node.l = rotateL(node.l);
			}
			return rotateR(node);

		} else if (bf > 1) {

			if (getbf(node.r) < 0) {
				node.r = rotateR(node.r);
			}
			return rotateL(node);
		}
		return node;
	}

	public Node rotateR(Node node) {
		Node u = node.l;
		node.l = u.r;
		u.r = node;
		return u;
	}

	public Node rotateL(Node node) {
		Node z = node.r;
		node.r = z.l;
		z.l = node;
		return z;
	}

	public int getbf(Node root) {
		return h(root.r) - h(root.l);
	}

	public int H() {
		return h(root);
	}

	public int h(Node node) {
		return node != null ? Math.max(h(node.l) + 1, h(node.r) + 1) : 0;
	}

	public void remove(T elem) {
		root = remove(elem, root);
	}

	public Node remove(T elem, Node node) {
		if (node == null) {
			return null;
		}
		if (elem.equals(node.value)) {
			if (node.l == null || node.r == null) {
				node = (node.l != null) ? node.l : node.r;
			} else {
				T max = max(node.l);
				node.value = max;
				node.l = remove(max, node.l);
			}
		} else if (elem.compareTo(node.value) > 0) {
			node.r = remove(elem, node.r);
		} else {
			node.l = remove(elem, node.l);
		}
		return balance(node);
	}

	public T max(Node node) {
		while (node.r != null) {
			node = node.r;
		}
		return node.value;
	}

	public String inorder() {
		if (root == null)
			return "vazio";
		return TraverseInorder(root);
	}

	public String preorder() {
		if (root == null)
			return "vazio";
		return TraversePreorder(root);
	}

	public String postorder() {
		if (root == null)
			return "vazio";
		return TraversePostorder(root);
	}

	public void visualize() {
		visualize(root, 0);
	}

	private void visualize(Node node, int lvl) {
		if (node != null) {
			visualize(node.r, lvl + 1);
			for (int i = 0; i < lvl << 1; i++) {
				System.out.print("\t");
			}
			System.out.println(node.value);
			visualize(node.l, lvl + 1);
		}
	}

	private String TraverseInorder(Node node) {
		if (node == null) return "";
		return TraverseInorder(node.l) + " " + node.value + TraverseInorder(node.r);
   }

   private String TraversePostorder(Node node) {
		if (node == null) return "";
		return TraversePostorder(node.r) + " " + node.value + TraversePostorder(node.l);
   }

   private String TraversePreorder(Node node) {
		if(node == null) return "";
		return node.value + " " + TraversePreorder(node.l) + TraversePreorder(node.r);
   }

	public static void main(String[] args) {
		var a = new AVL();
		for (int i = 0; i < 100; i++) {
			a.add(i);
		}
		System.out.println(a.inorder());
		System.out.println(a.postorder());
	}

	private class Node {
		T value;
		Node l, r;

		Node(T val) {
			value = val;
		}
	}
}
