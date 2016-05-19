/* Le Xuan Thuong
 * HW 7
 * Lab TA Jordan Brown
 * M-W 2-3.15
 */

public class KDNode {
	public double [] data; //array of coordinates
	public KDNode left;
	public KDNode right;
	public KDNode parent;

	public KDNode (double [] x, KDNode  node) {
		left = null;
		right = null;
		data = x; 
		parent = node;
	}




	//usually uses widest spread
	public void insert (double [] t,  int depth) {
		int axis = depth % t.length;  //find the axis that we are comparing to
		//comparing the axis of the node and given point
		if (t[axis]<(data[axis])){
			if (left == null ) {
				left = new KDNode (t,this);
			} else  {
				left.insert(t, depth+1);
			}
		} else  if (t[axis]>=(data[axis])) {
			if (right == null) {
				right = new KDNode  (t,this);
			} else  {
				right.insert(t, depth+1);
			}
		}
	}

/*
	//works for double type input
	//TODO check if this is how it works
	public KDNode nearNSearch(double [] t, KDNode  node, int depth, KDNode  curBest, double dist) {
		int axis = depth %t.length;
		//if (node.equalPoint(t, node)== false) {
		if (node.left == null &&node.right == null) {
			curBest = node;
			dist = node.distance(t,node.data,t.length);
			return node; 	   
		} else if (t[axis]<(node.data[axis])){
			return node.left.nearNSearch (t,node.left, depth+1,curBest);
		}  else  if (t[axis]>(node.data[axis])) {
			return node.right.nearNSearch(t, node.right, depth+1,curBest);
		} 
		//} else {
		//	return node;
	}
*/

	//take distance squared to avoid roots
	public double distance (double [] t, double [] data, int k) {
		double [] delta = new double [k];
		for (int i = 0; i <k; i++ ) {
			//find delta between two x, y ... k coordinates
			delta[i] = (t[i]-data[i])*(t[i]-data[i]);
		}
		//sum up distances
		double distance = 0;
		for (double del : delta) {
			distance+=del;
		}
		System.out.println(distance);
		return distance;
	}



	public double axisDist (double a, double b) {
		double delta = (a-b)*(a-b);
		return delta;

	}
	public boolean equalPoint (double [] x, KDNode node) {

		//x.length - dimension
		if (x == (node.data)) {//check if  this works
			return true;
		} else {
			return false;
		}
	}


	public void printPreOrder () {
		for (double i:data) {
			System.out.print(i+" ");
		}
		if (left != null) 
			left.printPreOrder();
		if (right != null)
			right.printPreOrder();
	}


	public KDNode lookup (double [] t,int depth) {
		int axis = depth % t.length;  
	//less than data of given node
		if (t[axis]<(data[axis])){
			if (left == null ) {
				return null;
			} else  {
				return left.lookup(t, depth+1);
			}
	//bigger than data of given node
		} else  if (t[axis]>(data[axis])) {
			if (right == null) {
				return null;
			} else  {
				return right.lookup(t, depth+1);
			}
	//if equal to data at this node
		} else {
			return this;
		}
	}
	

	public void delete(double[] t, KDNode  parent, int depth) {
		int k = t.length;
		int axis = depth % k;  
		if (t[axis]<(data[axis])){
			left.delete(t, this, depth+1);
		} else  if (t[axis]>(data[axis])) {
			right.delete(t, this, depth+1);
		} else if (parent.left == this &&left == null && right == null) {
			parent.left = null;
			} else if (parent.right == this &&left == null && right == null) {
				parent.right = null;
			} else if (right != null) {
				//data of minimum node
				this.data = right.findMin(axis, depth+1, k).data; 
				right.delete(this.data, this, depth+1);
			} else {
				this.data = left.findMin(axis, depth+1, k).data;
				left.delete(this.data, this, depth+1);
				//substitute right subtree with left child
				right = left;
			} 
		}



		//find minimum value to replace the node's data
		public  KDNode findMin(int searchAxis, int depth, int k) {
			int axis = depth %k;
			//if on same axis search in left subtree
			if (searchAxis == axis) {
				KDNode minNode = (left== null) ? this : left.findMin(searchAxis,depth+1,k);
				return minNode;
				//if not on same axis take min of left subtree, right subtree and current node axis coordinate
			} else  /*if (searchAxis != axis)*/ {
				if (right == null && left == null) {
					return this;
				} else if (right != null && left != null) {
					return minNode(left.findMin(searchAxis, depth+1, k),right.findMin(searchAxis, depth+1, k),this,searchAxis);
				} else  if (right != null) {
					return minNode(null,right.findMin(searchAxis, depth+1, k),this,searchAxis);
				} else {
					return minNode(left.findMin(searchAxis, depth+1, k),null,this,searchAxis);
				}
			} 
		}

		//modify? too long think of better way to do this
		public KDNode minNode (KDNode left, KDNode right, KDNode node, int axis) {
			KDNode minNode = null;
			//left, right != null find minimum of three
			if (right != null && left != null) {
				double min = Math.min(left.data[axis],Math.min( right.data[axis],node.data[axis]));
				//double condition return the node with minimum value for input axis
				minNode =  (min == left.data[axis]) ? left:(min == right.data[axis]) ? right : node;
				//left == null, find minimum of node and right
			} else if (right != null) {
				double min = Math.min(right.data[axis],node.data[axis]);
				minNode = (min == right.data[axis]) ? right : node;
				//right == null, find minimum of node and left
			} else /*(left != null )*/{
				double min =  Math.min(left.data[axis],node.data[axis]);
				minNode = (min == left.data[axis]) ? left : node;
			}
			return minNode;
		}
	}
