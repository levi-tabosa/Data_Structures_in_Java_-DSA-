package Trees;

public class Rope {
	private Rope l, r, par;
	private int weight;
	private final static int LEAF_LIMIT = 5;
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

	private Rope(Rope l, Rope r) {
		this.l = l;
		this.r = r;
		l.par = r.par = this;
		weight = l.length();
	}

	public char charAt(int idx) {
		return charAt(this, idx);
	}

	private char charAt(Rope node, int idx) {
		if (node.chars != null)
			return node.chars[idx];
		return idx < node.weight ? charAt(node.l, idx) : charAt(node.r, idx - node.weight);
	}

	public static Rope concatenate(Rope r1, Rope r2) { //TODO: case r1 and r2 are mergeable, 
		if (r1.chars == null && r1.weight >> 1 > r2.weight) {
			Rope curr = r1;
			while (curr.r.chars == null && curr.weight >> 1 > r2.weight) {
				curr = curr.r;
			}
			curr.r = new Rope(curr.r, r2);
			return r1;
		} 
		if (r2.chars == null && r2.weight >> 1 > r1.weight) {
			Rope curr = r2;
			while (curr.l.chars == null && curr.weight >> 1 > r1.weight) {
				curr = curr.l;
			}
			curr.l = new Rope(r1, curr.l);
			return r2;
		}
		return new Rope(r1, r2);
	}

	public Rope insert(Rope rope, int idx) {
		Rope[] splitRopes = splitAt(this, idx);
		return
		splitRopes[0].length() < splitRopes[1].length() ?
		concatenate(concatenate(splitRopes[0], rope), splitRopes[1])
		: concatenate(splitRopes[0], concatenate(rope, splitRopes[1]));
	}

	public Rope[] splitAt(int idx) { // changes original rope returns
		return splitAt(this, idx);
	}

	private Rope[] splitAt(Rope node, int idx) {
		if (node.chars == null) {
			return idx < node.weight ? splitAt(node.l, idx) : splitAt(node.r, idx - node.weight);
		}

		Rope otherRope = createSplitNode(node, idx); //TODO: get rid of empty leaf node case idx == 0;
		Rope nodeCopy = node; // storing variable for repairing broken links

		while (node.par != null) {
			if (node == node.par.l) {
				otherRope = concatenate(otherRope, node.par.r);
				node.par.r = null;
			}
			node = node.par;
		}
		rebalance(nodeCopy);

		return new Rope[] { this, otherRope };
	}

	private Rope createSplitNode(Rope node, int idx) { // returns leaf containing the chars from node starting from idx
		Rope newLeaf = new Rope();
		newLeaf.chars = copyRange(node.chars, idx, node.weight - idx);
		newLeaf.weight = node.weight - idx;
		node.chars = copyRange(node.chars, 0, idx);
		node.weight = idx;
		return newLeaf;
	}

	private void rebalance(Rope node) { // clears broken nodes after separating links
		while (node.par != null) {
			if (node.par.r == null || node.par.l == null) {
				node.par.l = node.l;
				node.par.r = node.r;
				node.par.chars = node.chars;
				node.par.weight = node.weight;
			}
			node = node.par;
		}
	}

	private static char[] copyRange(char[] original, int start, int length) {
		char[] copy = new char[length];
		System.arraycopy(original, start, copy, 0, length);
		return copy;
	}

	public int length() {
		return length(this);
	}

	private int length(Rope node) {
		return node != null ? node.weight + length(node.r) : 0;
	}

	public void print() {
		print(this);
		System.out.println();
	}

	private void print(Rope node) {
		if (node == null)
			return;
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
		if (node == null)
			return;
		visualize(node.r, lvl + 1);
		for (int i = 0; i < lvl; i++) {
			System.out.print("\t");
		}
		System.out.println(node.weight + (node.chars != null ? " / " + new String(node.chars) : ""));
		visualize(node.l, lvl + 1);
	}

	public static void main(String[] args) {
		String[] TEST_STRINGS = {
				"hello_i_am_a_rope_tree_data_structure_",
				// "HELLO_WORLD_",
		};

		for (int i = 0; i < TEST_STRINGS.length; i++) {
			for (int j = 0; j <= TEST_STRINGS[i].length(); j++) {
				Rope[] a = new Rope(TEST_STRINGS[i]).splitAt(j);
				Rope b = new Rope(TEST_STRINGS[i]).insert(new Rope("#"), j);
				System.out.println(a[0]);
				System.out.println(a[1]);
				a[1].visualize();
				a[0].visualize();
				// Rope b = concatenate(concatenate(a[0], new Rope("##########")), a[1]);
				// System.out.println(b);
				// b.visualize();
			}
		}
	}
}