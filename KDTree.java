
public class KDTree {
	public KDNode root;
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
			root = new KDNode (t, null,0);
		} else {
			root.insert(t, 0);
		}
	}   



	public void delete(double [] x) {
		if (root.data == x) ;
		if (root!= null) {
			if (root.lookup(x, 0) != null) {
				System.out.print("Delete ");
				for (double i: x) {
					System.out.print(i+" ");
				}
				System.out.println();
				root.delete(x,root,0);//will it work?
			}
		} 

	}



	public double curDist  = Double.MAX_VALUE;
	public KDNode curBest = root;



	public void nearN (double [] t, KDNode cur) {
		System.out.print("Nearest neighbor: ");
		double [] nearN = nearNSearch (t,cur).data;
		for (double point : nearN) {
			System.out.print(point+" ");
		}
		System.out.println();
	}




	public KDNode nearNSearch (double [] t, KDNode cur) {
		if (root!= null) {
			if (t[cur.axis]<(cur.data[cur.axis]) && cur.left != null){
				nearNSearch(t,cur.left);
			} else  if (t[cur.axis]>=(cur.data[cur.axis]) && cur.right != null) { //>= or <= or does it even matter?
				nearNSearch(t,cur.right);
			} 
			//here you reach the leaf 
			/* if dist between cur node and point < (or <=?) than shortest curDist, update curDist */
			double dist = cur.distance (cur.data, t);
			if (dist < curDist) {
				curBest = cur;
				curDist = dist;
			}
			if (cur.axisDist (t [cur.axis],cur.data[cur.axis]) <curDist) {
				if (cur.left != null && cur.left.vis == false) { // if not a leaf and not visited 
					nearNSearch (t, cur.left);
				} 
				if (cur.right != null &&cur.right.vis == false) {
					nearNSearch(t, cur.right);
				}
			}
			cur.vis = true;
			return curBest;
		} else {
			return null;
		}
	}



	public void printPreOrder () {
		root.printPreOrder();
		System.out.println();
	}


	public double[] findMin(int axis) {
		if (root == null) {
			return null;
		} else  {
			System.out.print("Node contains minimum on axis "+axis+": ");
			double [] testArr = root.findMin(axis, 0, k).data;
			for (double m :testArr) {
				System.out.print(m+" ");
			}
			System.out.println();
			return testArr;//change 2 to k
		}
	}
}

