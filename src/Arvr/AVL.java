class AVL<T extends Comparable<T>> {
	Node<T> root;

	public void add(T elem) {
		root = add(elem, root);
	}

	public Node<T> add(T elem, Node<T> node) {
		if(node == null) {
			return new Node<>(elem);
		}
		if (elem.compareTo(node.value) > 0) {
			node.r = add(elem, node.r);
		//} else if(elem.compareTo(node.value) < 0) no repeat key
		} else {
			node.l = add(elem, node.l);
		}
		return balance(node);
	}

	public Node<T> balance(Node<T> node) {
		if (node == null){
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

	public Node<T> rotateR(Node<T> node) {
		Node<T> u = node.l;
		node.l = u.r;
		u.r = node;
		return u;
	}

	public Node<T> rotateL(Node<T> node) {
		Node<T> z = node.r;
		node.r = z.l;
		z.l = node;
		return z;
	}

	public int getbf(Node<T> root) {
		return h(root.r) - h(root.l);
	}

	public int H() {
		return h(root);
	}

	public int h(Node<T> node) {
		if (node != null) {
			int hl = h(node.l) + 1, hr = h(node.r) + 1;
			return Math.max(hl, hr);
		}
		return 0;
	}

	public void remove(T elem) {
		root = remove(elem, root);
	}

	public Node<T> remove(T elem, Node<T> node) {
		if(node == null) {
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

	public T max(Node<T> node) {
		while(node.r != null) {
			node = node.r;
		}
		return node.value;
	}

	public String inOrder() {
		if (root == null)
			return "vazio";
		return in(root);
	}

	public String preOrder() {
		if (root == null)
			return "vazio";
		return pre(root);
	}

	public void printTree() {
		print(root, 0);
	}

	private void print(Node<T> node, int lvl) {
		if (node != null) {
			print(node.r, lvl + 1);
			for (int i = 0; i < lvl << 1; i++)
				System.out.print("\t");
			System.out.println(node.value);
			print(node.l, lvl + 1);

		}
	}

	public String in(Node<T> node) {
		String ret = node.value.toString();

		if (node.l != null) {
			ret = " " + in(node.l) + " " + ret;
		}
		if (node.r != null) {
			ret += " " + in(node.r) + " ";
		}

		return ret;
	}

	public String pre(Node<T> node) {
		String ret = node.value.toString() + " ";

		if (node.l != null) {
			ret += pre(node.l) + " ";
		}
		if (node.r != null) {
			ret += pre(node.r) + " ";
		}

		return ret;
	}
}

class Node<T>{
	T value;
	Node<T> l, r;

	Node(T val) {
		value = val;
	}
}
