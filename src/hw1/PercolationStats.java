package hw1;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
		private int n, trials;
		private double[] mean;
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials < 0) throw new java.lang.IllegalArgumentException();
		this.n = n;
		this.trials = trials;
		this.mean = new double[trials];
		int numofblocks = n * n;
		for (int i = 0; i < trials; i++) {
			mean[i] = (double) onetrial()/numofblocks;
		}
	}
	public double mean() {
		return StdStats.mean(mean);
	}
	public double stddev() {
		return StdStats.stddev(mean);
	}
	public double confidenceLo() {
		return mean() - (1.96 * stddev()) / Math.sqrt(this.trials);
	}
	public double confidenceHi() {
		return mean() + (1.96 * stddev()) / Math.sqrt(this.trials);	
	}
	private int onetrial() {
		Percolation p = new Percolation(n);
		while(!p.percolates()) {
			int row = 1 + StdRandom.uniform(n);
			int col = 1 + StdRandom.uniform(n);
			p.open(row, col);
		}
		return p.numberOfOpenSites();
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);

		PercolationStats p1 = new PercolationStats(n, t);
		System.out.println("mean" + " = " + p1.mean());
		System.out.println("stddev" + " = " + p1.stddev());
		System.out.println("95% condidence internal" + " = " +
						"[" + p1.confidenceHi() + ", " + p1.confidenceLo() + "]");
	
	}
}
