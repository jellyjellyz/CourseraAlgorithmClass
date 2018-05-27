package hw1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
		private int N, openSites;
		private int[] array;  //only for saving open/close status
		private WeightedQuickUnionUF uf;
	public Percolation(int n) {
		//create an array represented matrix
		this.uf = new WeightedQuickUnionUF(n*n + 1);
		this.N = n;
		this.array = new int[n*n + 1];
		this.openSites = 0;

	}
	public void open(int row, int col) {
		validate(row, col);
		
		int idx = xyTo1D(row, col);
		if (!isOpen(row, col)) {	
			this.array[idx] = 1;
			this.openSites += 1;
		}
	
		//check the upper block
		if (row - 1 > 1 && isOpen(row - 1, col)) {
			int upper = xyTo1D(row - 1, col);
			uf.union(idx, upper);
		}
		//check the left block
		if (col - 1 >= 1 && isOpen(row, col - 1)) {
			int left = xyTo1D(row, col - 1);
			uf.union(idx, left);
		}
		//check the right block
		if (col + 1 <= N && isOpen(row, col + 1)) {
			int right = xyTo1D(row, col + 1);
			uf.union(idx, right);
		}
		//check the lower block
		if (row + 1 <= N && isOpen(row + 1, col)) {
			int lower = xyTo1D(row + 1, col);
			uf.union(idx, lower);
		}
		
		if (row == 1) {
			uf.union(idx, 0);
		}
		
		
	}

	public boolean isOpen(int row, int col) {
		validate(row, col);
		int idx = xyTo1D(row, col);
		if (this.array[idx] == 1) {
			return true;
		} else {
			return false;
		}		
	}
	
	//check if an open block's root is 0
	public boolean isFull(int row, int col) {
		validate(row, col);
		int idx = xyTo1D(row, col);
		return uf.connected(idx, 0);
	}
	
	public int numberOfOpenSites() {
		return openSites;
	}
	
	public boolean percolates(){
		for (int i = 1; i <= N; i++) {
			int idx = xyTo1D(N, i);
			if (isOpen(N, i) && uf.connected(idx, 0)) {
				return true;
			}
		}
		return false;
	}
	
	private int xyTo1D(int row, int col) {
		return (row - 1) * N + col;
	}
	
	private void validate(int row, int col) {
		if (row <= 0 || row > N || col <= 0 || col > N) throw new IndexOutOfBoundsException("row index i out of bounds");
	}
	
//	public boolean isconnected(int r1, int c1, int r2, int c2) {
//		int idx1 = xyTo1D(r1, c1);
//		int idx2 = xyTo1D(r2, c2);
//		return uf.connected(idx1, idx2);
//	}
//	
//	public int find(int row, int col) {
//		int idx = xyTo1D(row, col);
//		return uf.find(idx);
//	}
	
	public static void main(String[] args) {
		Percolation p = new Percolation(5);
		p.open(3, 3);
		p.open(3, 4);
		p.open(4, 3);
		p.open(4, 4);
		p.open(2, 2);
		p.open(2, 5);
		p.open(1, 2);
		p.open(5, 4);
		p.open(3, 2);

		System.out.println(p.percolates());
		
		
	}



}
