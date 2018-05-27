package hw1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Test {
	public static void main(String[] args) {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(8);
		uf.union(2, 3);
		uf.union(3, 4);
		uf.union(4, 5);
		uf.union(5, 6);
		System.out.println(1 + StdRandom.uniform(5));
	}

}
