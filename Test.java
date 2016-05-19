
import java.util.Scanner;
public class Test {
	public static void main(String[] args) {
  
		//file with coordinates
		Scanner scanner = new Scanner (System.in);
	
		System.out.println("Print number of points:");
		int num = scanner.nextInt(); //print 7
		System.out.println("Specify number of dimensions");
		int k = scanner.nextInt(); //print 2
		System.out.println("Print coordinates for points"); //print 3 6 17 15  13 15 6 12 9 1 2 7 10 19
		KDTree kdtree = new KDTree();
		kdtree.setK(k);//set num of dimensions
		for (int i = 0; i < num; i++) { 
			double[] point = new double [k];
			for (int j = 0; j < k; j++) { //make a point with k coordinates
				point [j] = scanner.nextDouble(); 
			}
			kdtree.insert (point);	
		}
		kdtree.printPreOrder();
		System.out.println();
		System.out.print("Node contains minimum x : ");
		double [] testArr = kdtree.findMin(0);
		for (double m :testArr) {
			System.out.print(m+" ");
		}
		System.out.println();
		System.out.print("Node contains minimum y: ");
		double [] testArr2 = kdtree.findMin(1);
		for (double m :testArr2) {
			System.out.print(m+" ");
		}
		System.out.println();
		double [] deleteArr = {3,6};
		double [] deleteArr2 = {0,0};
		kdtree.delete(deleteArr);
		kdtree.delete(deleteArr2);
		kdtree.printPreOrder();
		}
	
	
}
