package Trees;

public class Rope {
	private Rope l, r, par;
	private int weight;
	private final static int LEAF_LIMIT = 4;
	private char[] chars;

	public Rope() {
	}

	public Rope(char[] chars) {
		this(null, chars, 0, chars.length);
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
			this.chars = copyRange(chars, l, r - l);
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

	public Rope[] splitAt(int idx) { // changes original rope
		return splitAt(this, idx);
	}

	private Rope[] splitAt(Rope node, int idx) {
		if (node.chars == null) {
			return idx < node.weight ? splitAt(node.l, idx) : splitAt(node.r, idx - node.weight);
		}

		Rope otherRope = createSplitNode(node, idx);

		node.weight = idx;
		Rope aux = node; // auxiliary variable for balancing after concatenations

		while (node.par != null) {
			if (node == node.par.l) {
				otherRope = concatenate(otherRope, node.par.r);
				node.par.r = null;
			}
			node = node.par;
		}

		rebalance(aux);

		return new Rope[] { this, otherRope };
	}

	private Rope createSplitNode(Rope node, int idx) {
		Rope newRope = new Rope();
		newRope.chars = copyRange(node.chars, idx, node.weight - idx);
		newRope.weight = node.weight - idx;
		node.chars = copyRange(node.chars, 0, idx);
		return newRope;
	}

	private void rebalance(Rope node) {
		while (node.par != null) {
			if (node.par.r == null || node.par.l == null) {
				node.par.l = node.l;
				node.par.r = node.r;
				node.par.weight = node.weight;
				node.par.chars = node.chars;
			}
			node = node.par;
		}
	}

	private static char[] copyRange(char[] original, int start, int length) {
		char[] aux = new char[length];
		System.arraycopy(original, start, aux, 0, length);
		return aux;
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
		if (node == null) return;
		if (node.chars != null) {
			System.out.print(node.chars);
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
		System.out.println(node.weight + " / " + (node.chars != null ? new String(node.chars) : ""));
		visualize(node.l, lvl + 1);
	}

	public static void main(String[] args) {
		String[] TEST_STRINGS = {
			"hello_i_am_a_rope_data_structure_",
		};

		Rope rope = new Rope(TEST_STRINGS[0]);
		System.out.println(rope);

		for (int i = 0; i < TEST_STRINGS.length; i++) {
			for (int j = 0; j < TEST_STRINGS[i].length(); j++) {
				Rope[] a = new Rope(TEST_STRINGS[i]).splitAt(j);
				System.out.println(a[0]);
				a[0].visualize();
				System.out.println(a[1]);
				a[1].visualize();
				System.out.println(concatenate(a[0], a[1]));
			}
		}
	}
}