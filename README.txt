README
KDtree is a binary tree but contains array of coordinates (k coordinates for k dimensions) than just a coordinate
Contains 3 classes KDNode, KDTree and Test
Test has the main method that shows how the k-d tree works. It prints the kd tree in preorder (from root to leaves), prints minimum in xy dimensions and deletes two arrays of points, one of which is not contained in the tree. Unfortunately, I didn’t have time to change the scanner to file reading, that’s why I have to input the points manually (in comments in main())


KDTree 
Constructor: sets root to null
setK:sets k to input number of dimensions
Insert: creates a new node if the tree is empty, or inserts the point into the tree
Delete: if node is in the tree deletes it
printPreOrder:prints tree from root to leaves
findMin:returns a point containing a min coordinate in given axis


*Nearest Neighbor Search is commented out. It’s not the main part of k-d tree data structure, but rather one of its applications. I started doing it but because of 0 time left, haven’t finished.


KDNode
Most of the code for KDNode is explained in my paper and will be explained in the presentation
Insert: works similarly to bs tree, but only coordinates in given axis are compared. The current axis alternates while traversing through tree, and equals to depth % number of dimensions of points (k).When reaches the leaf,creates a new KDNode
 
*distance ,equalPoint, axisDist are for nearestNeighborSearch


printPreorder - same as in binary search tree


Lookup - comparison works same way as in insert, returns null if node is not in the tree, returns node if it’s in the tree


findMin and minNode - findMin returns the node with minimum coordinate at the given axis (not min point). If current axis is same as given axis, call recursively on left subtree. If not, call recursively on left and right subtrees and find minimum value of both these calls and current node’s coordinate value


Delete - if node to be deleted is leaf, just set it to 0
If right is not null, substitute node’s data with minimum value from right subtree and delete the value from right subtree.
If left is not null, replace node’s data with left subtree’s minimum, delete from left and set left child to right