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
        Deque<Integer> CaseStack = new ArrayDeque<Integer>();
        Deque<Node> RotationsStack = new ArrayDeque<Node>();
        Deque<Node> InsertedStack = new ArrayDeque<Node>();


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
