package Trees;

public class Rope {
	Rope l, r, par;
	int weight;
	final static int LEAF_LIMIT = 4;
	char[] chars;

	public Rope() {
	}

	public Rope(String string) {
		this(null, string.toCharArray(), 0, string.length());
	}

	private Rope(Rope parent, char[] chars, int l, int r) {
		par = parent;
		if (r - l > LEAF_LIMIT) {
			this.weight = r - l >> 1;
			this.l = new Rope(this, chars, l, r + l >> 1);
			this.r = new Rope(this, chars, r + l >> 1, r);
		} else {
			this.chars = new char[r - l];
			this.weight = r - l;
			System.arraycopy(chars, l, this.chars, 0, r - l);
		}
	}

	public static Rope concatenate(Rope r1, Rope r2) {
		Rope aux = new Rope();
		aux.l = r1;
		aux.r = r2;
		r1.par = r2.par = aux;
		aux.weight = r1.length();
		return aux;
	}

	public char charAt(int idx) {
		return charAt(this, idx);
	}

	private char charAt(Rope node, int idx) {
		if (node.chars != null) {
			return node.chars[idx];
		}
		return idx < node.weight ? charAt(node.l, idx) : charAt(node.r, idx - node.weight);
	}

	public Rope[] splitAt(int idx) {
		return splitAt(this, idx);
	}

	private Rope[] splitAt(Rope node, int idx) { // changes original rope
		if (node.chars == null) {
			return idx < node.weight ? splitAt(node.l, idx) : splitAt(node.r, idx - node.weight);
		}

		Rope otherRope = new Rope();

		if (node == node.par.l) {
			otherRope.l = new Rope();
			otherRope.l.chars = new char[node.weight - idx];
			otherRope.l.weight = otherRope.weight = node.weight - idx;
			otherRope.weight = node.weight - idx;
			otherRope.r = node.par.r;

			char[] aux = new char[idx];
			System.arraycopy(node.chars, idx, otherRope.l.chars, 0, node.weight - idx);
			System.arraycopy(node.chars, 0, aux, 0, idx);

			node.par.r = null;
			node.chars = aux;
			node.weight = idx;

			rebalance(node);
			node = node.par;

			while (node.par != null) {
				if (node.par.l == node) {
					otherRope = concatenate(otherRope, node.par.r);
					node.par.r = null;
				}
				node = node.par;
			}
		} else {
			otherRope.r = new Rope();
			otherRope.r.chars = new char[node.weight - idx];
			otherRope.r.weight = idx;

			node.l = new Rope();
			node.l.chars = new char[idx];
			node.l.weight = idx;
			node.l.par = node;

			System.arraycopy(node.chars, 0, node.l.chars, 0, idx);
			System.arraycopy(node.chars, idx, otherRope.r.chars, 0, node.weight - idx);
			node.chars = null;

			node.weight = node.l.weight;
			rebalance(node);

			while (node.par != null) {
				if (node == node.par.l) {
					otherRope = concatenate(otherRope, node.par.r);
					node.par.r = null;
				}
				node = node.par;
			}
		}
		return new Rope[] { this, otherRope };
	}

	private void rebalance(Rope node) {
		while (node.l == null || node.r == null) {
			node = node.par;
			if (node.l == null) {
				node.chars = node.r.chars;
				node.weight = node.r.weight;
				node.r = null;
			} else if (node.r == null) {
				node.chars = node.l.chars;
				node.weight = node.l.weight;
				node.l = null;
			}
		}
	}

	public int length() {
		return length(this);
	}

	private int length(Rope rope) {
		return rope != null ? rope.weight + length(rope.r) : 0;
	}

	public void print() {
		print(this);
		System.out.println();
	}

	private void print(Rope node) {
		if (node == null) {
			return;
		}
		if (node.chars != null) {
			System.out.print(new String(node.chars));
		}
		print(node.l);
		print(node.r);
	}

	@Override
	public String toString() {
		return parse(this);
	}

	private String parse(Rope node) {
		return node.chars != null ? new String(node.chars) : parse(node.l) + parse(node.r);
	}

	public void visualize() {
		visualize(this, 0);
		System.out.println();
	}

	private void visualize(Rope node, int lvl) {
		if (node == null) {
			return;
		}
		visualize(node.r, lvl + 1);
		for (int i = 0; i < lvl; i++) {
			System.out.print("\t");
		}
		System.out.println(node.weight + " / " + (node.chars != null ? node.chars : ""));
		visualize(node.l, lvl + 1);
	}

	public static void main(String[] args) {
		for (Rope r : new Rope("HELLO_HELLO_BEAULTIFUL_WORLD").splitAt(5)) {
		r.print();
		r.visualize();
		}

		for (Rope r : new Rope("HELLO_BEAULTIFUL_WORLD").splitAt(6)) {
		r.print();
		r.visualize();
		}

		for (int i = 0; i < "HELLO_BEAULTIFUL_COOL_WORLD".length(); i++) {
			for (Rope r : new Rope("HELLO_BEAULTIFUL_COOL_WORLD").splitAt(i)) {
				r.print();
				//r.visualize();
			}
		}
	}
}