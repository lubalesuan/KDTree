
public class KDTree {
	private KDNode root;
	public KDNode  node = root;
 // number of dimensions
	public int k;
	
	public KDTree() {
		root = null;
	}
	
	
	public void setK (int k) {
		this.k = k;
	}	
	
	public void insert (double[] t) {//t for input data
		if (root == null) {
			root = new KDNode (t, null);
		} else {
	   root.insert(t, 0);
		}
	}   
	


	public void delete(double [] x) {
		if (root.data == x) ;
		if (root!= null) {
			if (root.lookup(x, 0) != null)
			root.delete(x,root,0);//will it work?
			} 
		
	}
	
	

	
/*
	//nearest neighbor search
	public KDNode nearNSearch(double [] x) {
		if (root == null) {//if tree empty - not found
			return null;
		} else {
			return root.nearNSearch(x,root,0,null);
		}
	}
*/
	
	
	
	
	public void printPreOrder () {
		root.printPreOrder();
	}


	public double[] findMin(int axis) {
		if (root == null) {
			return null;
		} else  {
			return root.findMin(axis, 0, 2).data;//change 2 to k
		}
	}
}

