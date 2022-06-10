import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BacktrackingAVL extends AVLTree {


    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }



	//You are to implement the function Backtrack.
    public void Backtrack() {
        this.CaseStack.pop();
        int caseNum = this.CaseStack.peek();
        switch (caseNum) {
            case 1:
                Node popped_C1 = NodeRotatedStack.pop();
                if (popped_C1.parent == null){
                    this.root = rotateLeft(popped_C1);
                }
                else {
                    popped_C1.parent.left = rotateLeft(popped_C1);
                }
                this.CaseStack.pop();
                break;

            case 2:
                Node popped_C2 = NodeRotatedStack.pop();
                if (popped_C2.parent == null) {
                    this.root.left = rotateLeft(popped_C2);
                    Node popped_C22 = NodeRotatedStack.pop();

                    if (popped_C22.parent == null) {
                        this.root.right = rotateRight(popped_C22);
                    }
                    else {
                        popped_C22.parent.right = rotateRight(popped_C22);
                    }
                }
                else {

                    popped_C2.parent.left = rotateLeft(popped_C2);
                    Node popped_C22 = NodeRotatedStack.pop();
                    if (popped_C22.parent == null) {
                        this.root.right = rotateRight(popped_C22);
                    }
                    else {
                        popped_C22.parent.right = rotateRight(popped_C22);
                    }
                }
                this.CaseStack.pop();
                break;

            case 3:
                Node popped_C3 = NodeRotatedStack.pop();
                if (popped_C3.parent == null) {
                    this.root = rotateRight(popped_C3);
                }
                else {
                    popped_C3.parent.right = rotateRight(popped_C3);
                }
//                try{
//                      popped_C3.parent.right = rotateRight(popped_C3);
//                }
//                catch (NullPointerException e){
//                    popped_C3.parent = rotateRight(popped_C3);
//                    this.root = popped_C3.parent;
//                }
                this.CaseStack.pop();
                break;
            case 4:
                Node popped_C4 = NodeRotatedStack.pop();
                if (popped_C4.parent == null) {
                    this.root = rotateRight(popped_C4);
                    Node popped_C42 = NodeRotatedStack.pop();
                    if (popped_C42.parent == null) {
                        this.root = rotateLeft(popped_C42);
                    }
                    else {
                        popped_C42.parent.left = rotateLeft(popped_C42);
                    }
                }
                else {
                    popped_C4.parent.right = rotateRight(popped_C4);
                    Node popped_C42 = NodeRotatedStack.pop();
                    if (popped_C42.parent == null) {
                        this.root = rotateLeft(popped_C42);
                    }
                    else {
                        popped_C42.parent.left = rotateLeft(popped_C42);
                    }
                }
                this.CaseStack.pop();
                break;
        }
        Node toDelete = InsertedStack.pop(); // a leaf
        this.delete(toDelete);

    }

    public void delete(Node node){
        if (node.parent == null){
            this.root = null;
        }
        else {
            if (node.parent.value > node.value) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
        List<Integer> insertions =  new ArrayList<Integer>();
        insertions.add(1);
        insertions.add(2);
        insertions.add(3);
        return insertions;
    }
    
    public int Select(int index) {
        if (root != null) {
            return this.root.Select(index);
        }
        throw new IllegalArgumentException();
    }

    public int Rank(int value) {
         return this.root.Rank(value);
    }
}
