import java.util.*;

public class AVLTree implements Iterable<Integer> {
    // You may edit the following nested class:
    protected class Node {
        public Node left = null;
        public Node right = null;
        public Node parent = null;
        public int height = 0;
        public int value;
        public int size = 0;

        public Node(int val) {
            this.value = val;
        }

        protected void updateSize() {
            int leftSize = (left == null) ? 0 : left.size;
            int rightSize = (right == null) ? 0 : right.size;
            size = leftSize + rightSize + 1;
        }

        public void updateHeight() {
            int leftHeight = (left == null) ? -1 : left.height;
            int rightHeight = (right == null) ? -1 : right.height;

            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public int getBalanceFactor() {
            int leftHeight = (left == null) ? -1 : left.height;
            int rightHeight = (right == null) ? -1 : right.height;

            return leftHeight - rightHeight;
        }

        public int Select(int index) {
            int curr_rank = (this.left == null) ? 1 : this.left.size + 1;
            if (curr_rank == index) {
                return this.value;
            } else if (index < curr_rank) {
                return this.left.Select(index);
            } else { // Searching for the respective rank in the right subtree
                return this.right.Select(index - curr_rank);
            }
        }


        public int Rank(int value) {
            int leftSize = (left == null) ? 0 : left.size;
            if (this.value == value) {
                    return leftSize;
                }
            else if (this.value > value) {
                if (this.left == null) {
                   return this.size - 1;
                } else {
                    return this.left.Rank(value);
                }
            }
            else {
                if (this.right == null) {
                    return leftSize + 1;
                } else {
                    return leftSize + 1 + this.right.Rank(value);
                }
            }
        }

    } // End of Class Node

    protected Node root;
    public Deque<Integer> CaseStack = new ArrayDeque<Integer>();
    // Case 1 - LeftLeft
    // 2 - LeftRight
    // 3 - RightRight
    // 4 - RightLeft
    // 5- No rotations
    public Deque<Node> NodeRotatedStack = new ArrayDeque<Node>();
    public Deque<Node> InsertedStack = new ArrayDeque<Node>();
    
    //You may add fields here.
    
    public AVLTree() {
    	this.root = null;
    }
    
    /*
     * IMPORTANT: You may add code to both "insert" and "insertNode" functions.
     */
	public void insert(int value) {
    	root = insertNode(root, value);
        // Added
        CaseStack.push(5);
    }
	
	protected Node insertNode(Node node, int value) {
	    // Perform regular BST insertion
        // InsertedStack.push(node)
        if (node == null) {
        	Node insertedNode = new Node(value);
            InsertedStack.push(insertedNode);
            insertedNode.size++;
            return insertedNode;
        }

        if (value < node.value) {
            node.left = insertNode(node.left, value);
            node.left.parent = node;
        }
        else {
            node.right = insertNode(node.right, value);
            node.right.parent = node;
        }
            
        node.updateHeight();
        node.updateSize();


        /* 
         * Check For Imbalance, and fix according to the AVL-Tree Definition
         * If (balance > 1) -> Left Cases, (balance < -1) -> Right cases
         */
        
        int balance = node.getBalanceFactor();


        if (balance > 1) {
            if (value > node.left.value) { // LeftRight
                node.left = rotateLeft(node.left);
                CaseStack.push(2); // LeftRight
                NodeRotatedStack.push(node.left);
            }
            else {
                CaseStack.push(1); // LeftLeft
            }
            node = rotateRight(node);
            NodeRotatedStack.push(node);
            // Case 1 - LeftLeft
            // 2 - LeftRight
            // 3 - RightRight
            // 4 - RightLeft
            // 5- No rotations
        } else if (balance < -1) { //RightLeft
            if (value < node.right.value) {
                node.right = rotateRight(node.right);
                CaseStack.push(4); // RightLeft
                NodeRotatedStack.push(node.right);
            }
            else{
                CaseStack.push(3); //RightRight
            }
            node = rotateLeft(node); //RightRight
            NodeRotatedStack.push(node);
        }



        return node;
    }
    
	// You may add additional code to the next two functions.
    protected Node rotateRight(Node y) { // 78
        Node x = y.left; // 59
        Node T2 = x.right; //66

        // Perform rotation
        x.right = y; // 59.right <- 78
        y.left = T2; // 78.left <- 66
        
        //Update parents
        if (T2 != null) {
        	T2.parent = y;
        }

        x.parent = y.parent; //59.parent = null
        y.parent = x; // 78.parent = 59
        
        y.updateHeight();
        x.updateHeight();
        x.updateSize();
        y.updateSize();

        // Return new root
        return x;
    }

    protected Node rotateLeft(Node x) { // 59
        Node y = x.right; // 78
        Node T2 = y.left; // 66

        // Perform rotation
        y.left = x; // 78.left = 59
        x.right = T2; // 59.right = 66
        
        //Update parents
        if (T2 != null) {
        	T2.parent = x; // 66.parent = 59
        }
        
        y.parent = x.parent; // 78.parent =  null
        x.parent = y; // 59.parent = 78
        
        x.updateHeight();
        y.updateHeight();
        x.updateSize();
        y.updateSize();

//        59 = 59.rotateLeft(78);
        // Return new root
        return y;
    }
    
    public void printTree() {
    	TreePrinter.print(this.root);
    }

    /***
     * A Printer for the AVL-Tree. Helper Class for the method printTree().
     * Not relevant to the assignment.
     */
    private static class TreePrinter {
        private static void print(Node root) {
            if(root == null) {
                System.out.println("(XXXXXX)");
            } else {    
                final int height = root.height + 1;
                final int halfValueWidth = 4;
                int elements = 1;
                
                List<Node> currentLevel = new ArrayList<Node>(1);
                List<Node> nextLevel    = new ArrayList<Node>(2);
                currentLevel.add(root);
                
                // Iterating through the tree by level
                for(int i = 0; i < height; i++) {
                    String textBuffer = createSpaceBuffer(halfValueWidth * ((int)Math.pow(2, height-1-i) - 1));
        
                    // Print tree node elements
                    for(Node n : currentLevel) {
                        System.out.print(textBuffer);
        
                        if(n == null) {
                            System.out.print("        ");
                            nextLevel.add(null);
                            nextLevel.add(null);
                        } else {
                            System.out.printf("(%6d)", n.value);
                            nextLevel.add(n.left);
                            nextLevel.add(n.right);
                        }
                        
                        System.out.print(textBuffer);
                    }
        
                    System.out.println();
                    
                    if(i < height - 1) {
                        printNodeConnectors(currentLevel, textBuffer);
                    }
        
                    elements *= 2;
                    currentLevel = nextLevel;
                    nextLevel = new ArrayList<Node>(elements);
                }
            }
        }
        
        private static String createSpaceBuffer(int size) {
            char[] buff = new char[size];
            Arrays.fill(buff, ' ');
            
            return new String(buff);
        }
        
        private static void printNodeConnectors(List<Node> current, String textBuffer) {
            for(Node n : current) {
                System.out.print(textBuffer);
                if(n == null) {
                    System.out.print("        ");
                } else {
                    System.out.printf("%s      %s",
                            n.left == null ? " " : "/", n.right == null ? " " : "\\");
                }
    
                System.out.print(textBuffer);
            }
    
            System.out.println();
        }
    }

    /***
     * A base class for any Iterator over Binary-Search Tree.
     * Not relevant to the assignment, but may be interesting to read!
     * DO NOT WRITE CODE IN THE ITERATORS, THIS MAY FAIL THE AUTOMATIC TESTS!!!
     */
    private abstract class BaseBSTIterator implements Iterator<Integer> {
        private List<Integer> values;
        private int index;
        public BaseBSTIterator(Node root) {
            values = new ArrayList<>();
            addValues(root);
            
            index = 0;
        }
        
        @Override
        public boolean hasNext() {
            return index < values.size();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            return values.get(index++);
        }
        
        protected void addNode(Node node) {
            values.add(node.value);
        }
        
        abstract protected void addValues(Node node);
    }
    
    public class InorderIterator extends BaseBSTIterator {
        public InorderIterator(Node root) {
            super(root);
        }

        @Override
        protected void addValues(Node node) {
            if (node != null) {
                addValues(node.left);
                addNode(node);
                addValues(node.right);
            }
        }    
      
    }
    
    public class PreorderIterator extends BaseBSTIterator {

        public PreorderIterator(Node root) {
            super(root);
        }

        @Override
        protected void addValues(AVLTree.Node node) {
            if (node != null) {
                addNode(node);
                addValues(node.left);
                addValues(node.right);
            }
        }        
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return getInorderIterator();
    }
    
    public Iterator<Integer> getInorderIterator() {
        return new InorderIterator(this.root);
    }
    
    public Iterator<Integer> getPreorderIterator() {
        return new PreorderIterator(this.root);
    }
}

