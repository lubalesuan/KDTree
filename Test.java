/**
 * handle exceptions with input
 */
import java.util.Scanner;
public class Test {
	public static void main(String[] args) {

		//file with coordinates
		Scanner scanner = new Scanner (System.in);
		System.out.println("Print number of points:");
		int num = scanner.nextInt(); //print 7
		System.out.println("Specify number of dimensions");
		int k = scanner.nextInt(); //print 2
		//print 3 6 17 15  13 15 6 12 9 1 2 7 10 19
		KDTree kdtree = new KDTree();
		kdtree.setK(k);//set num of dimensions
		for (int i = 0; i < num; i++) { 	
			kdtree.insert (scanArray(k,scanner));	
		}
		kdtree.printPreOrder();

		kdtree.findMin(0);
		kdtree.findMin(1);

		kdtree.delete(scanArray(k,scanner));
		kdtree.delete(scanArray(k,scanner));
		kdtree.printPreOrder();
		kdtree.nearN(scanArray(k,scanner),kdtree.root);

	}


	public static double[] scanArray (int k, Scanner scanner) {
		double [] array = new double [k];
		System.out.println("Print coordinates without commas: ");
		for (int i = 0; i < array.length; i++) {
			array [i] = scanner.nextDouble();
		}
		return array;
	}
}
