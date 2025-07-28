class Node {
	int data; //holds val of node
	Node parent; //pointer to parent node
	Node left; //pointer to left child
	Node right; //pointer to right child

	public Node(int data) {
		this.data = data;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
}

public class SplayTreeAssignment { 
	private Node root; //root of splay

	public SplayTreeAssignment() {
		root = null; 
	}

    //Prints recursively the tree structure 
	private void printHelper(Node currPtr, String indent, boolean last) {
	   	if (currPtr != null) {
		   System.out.print(indent);
		   if (last) {
		      System.out.print("R----");
		      indent += "     ";
		   } else {
		      System.out.print("L----");
		      indent += "|    ";
		   }

		   System.out.println(currPtr.data);

		   printHelper(currPtr.left, indent, false);
		   printHelper(currPtr.right, indent, true);
		}
	}

    //Searches for node with the key
	private Node searchTreeHelper(Node node, int key) {
		if (node == null || key == node.data) {
			return node;
		}

		if (key < node.data) {
			return searchTreeHelper(node.left, key);
		} 
		return searchTreeHelper(node.right, key);
	}

    //using splay operation it finds and deletes the node with the key
	private void deleteNodeHelper(Node node, int key) {
		Node x = null;
		Node t = null; 
		Node s = null;
		while (node != null){
			if (node.data == key) {
				x = node;
			}

			if (node.data <= key) {
				node = node.right;
			} else {
				node = node.left;
			}
		}

		if (x == null) {
			System.out.println("Couldn't find key in the tree");
			return;
		}
		splay(x);
		if (x.right != null) {
			t = x.right;
			t.parent = null;
		} else {
			t = null;
		}
		s = x;
		s.right = null;
		x = null;

		if (s.left != null){ 
			s.left.parent = null;
		}
		root = join(s.left, t);
		s = null;
	}

	//perform left rotation on node x
	private void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

    //right rotation on the node x
	private void rightRotate(Node x) {
		Node y = x.left;
		x.left = y.right;
		if (y.right != null) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.right) {
			x.parent.right = y;
		} else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}

    //splays the node to the root 
	private void splay(Node x) {
		while (x.parent != null) {
			if (x.parent.parent == null) {
				if (x == x.parent.left) {
					rightRotate(x.parent);
				} else {
					leftRotate(x.parent);
				}
			} else if (x == x.parent.left && x.parent == x.parent.parent.left) {
				rightRotate(x.parent.parent);
				rightRotate(x.parent);
			} else if (x == x.parent.right && x.parent == x.parent.parent.right) {
				leftRotate(x.parent.parent);
				leftRotate(x.parent);
			} else if (x == x.parent.right && x.parent == x.parent.parent.left) {
				leftRotate(x.parent);
				rightRotate(x.parent);
			} else {
				rightRotate(x.parent);
				leftRotate(x.parent);
			}
		}
	}

    //joins 2 trees s and t
	private Node join(Node s, Node t){
		if (s == null) {
			return t;
		}

		if (t == null) {
			return s;
		}
		Node x = maximum(s);
		splay(x);
		x.right = t;
		t.parent = x;
		return x;
	}

    //performs recursive pre order pransversal
	private void preOrderHelper(Node node) {
		if (node != null) {
			System.out.print(node.data + " ");
			preOrderHelper(node.left);
			preOrderHelper(node.right);
		} 
	}

    //perform inorder traversal
	private void inOrderHelper(Node node) {
		if (node != null) {
			inOrderHelper(node.left);
			System.out.print(node.data + " ");
			inOrderHelper(node.right);
		} 
	}

    //perform postorder traversal
	private void postOrderHelper(Node node) {
		if (node != null) {
			postOrderHelper(node.left);
			postOrderHelper(node.right);
			System.out.print(node.data + " ");
		} 
	}
    //publicly performs preorder tranversal
	public void preorder() {
		preOrderHelper(this.root);
	}

    //publicly performs iorder transversal
	public void inorder() {
		inOrderHelper(this.root);
	}

    //publicly performs postorder transversal
	public void postorder() {
		postOrderHelper(this.root);
	}

    //searches for node with key k and splays to the root
	public Node searchTree(int k) {
		Node x = searchTreeHelper(root, k);
		if (x != null) {
			splay(x);
		}
		return x;
	}

    //locates min value in the subtree rooted
	public Node minimum(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
    //locates max value in the subtree rooted
	public Node maximum(Node node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

    //locate successor of node in the tree
	public Node successor(Node x) {
	
		if (x.right != null) {
			return minimum(x.right);
		}

	
		Node y = x.parent;
		while (y != null && x == y.right) {
			x = y;
			y = y.parent;
		}
		return y;
	}

	//locate predecessor of node in the tree
	public Node predecessor(Node x) {
		
		if (x.left != null) {
			return maximum(x.left);
		}

		Node y = x.parent;
		while (y != null && x == y.left) {
			x = y;
			y = y.parent;
		}

		return y;
	}

    //new node to be inserted with the key in the tree
	public void insert(int key) {
		Node node = new Node(key);
		Node y = null;
		Node x = this.root;

		while (x != null) {
			y = x;
			if (node.data < x.data) {
				x = x.left;
			} else {
				x = x.right;
			}
		}

		node.parent = y;
		if (y == null) {
			root = node;
		} else if (node.data < y.data) {
			y.left = node;
		} else {
			y.right = node;
		}

		splay(node);
	}

    //public- delete node with the data provided
	void deleteNode(int data) {
		deleteNodeHelper(this.root, data);
	}

    //prints the tree structure
	public void prettyPrint() {
		printHelper(this.root, "", true);
	}

    //shows how its functioned
	public static void main(String [] args) {
		SplayTreeAssignment tree = new SplayTreeAssignment();
		tree.insert(33);
		tree.insert(44);
		tree.insert(67);
		tree.insert(5);
		tree.insert(89);
		tree.insert(41);
		tree.insert(98);
		tree.insert(1);
		tree.prettyPrint();
		tree.searchTree(33);
		tree.searchTree(44);
		tree.prettyPrint();
		tree.deleteNode(89);
		tree.deleteNode(67);
		tree.deleteNode(41);
		tree.deleteNode(5);
		tree.prettyPrint();
		tree.deleteNode(98);
		tree.deleteNode(1);
		tree.deleteNode(44);
		tree.deleteNode(33);
		tree.prettyPrint();
	}
}