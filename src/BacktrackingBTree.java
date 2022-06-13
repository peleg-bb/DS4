import java.util.ArrayList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {
	// For clarity only, this is the default ctor created implicitly.
	public BacktrackingBTree() {
		super();
	}

	public BacktrackingBTree(int order) {
		super(order);
	}

	//You are to implement the function Backtrack.
	public void Backtrack() {
		if(!insertedStack.isEmpty()){
			T delVal = insertedStack.pop();
			Node<T> toDeleteFrom = getNode(delVal);
			if(root == toDeleteFrom && toDeleteFrom.numOfKeys==1){
				root = null;
			}
			else {
				toDeleteFrom.removeKey(delVal);
			}
		}
		if(!splitStack.isEmpty()) {
			Integer split = splitStack.pop();
			while (split ==1) {
				T median = medianStack.pop();
				Node<T> medNode = getNode(median);
				int index = medNode.indexOf(median);
				Node<T> merged = merge(median, index);
				merged.parent = medNode;
				if (merged.parent.numOfKeys == 1) { //the split action created a new root containing only the median value.
					root = merged;
				} else { // the median value joined an existing node
					merged.parent.removeKey(median);
					merged.parent.addChild(merged);
				}
				split=splitStack.pop();
			}
//			}
			if(splitStack.peek()==-1){
				splitStack.pop();
			}
		}

	}

//	public void deleteFromNode(Node<T>)


	public Node<T> merge(T median, int index){
		Node<T> medNode = getNode(median);
		Node<T> leftChild = medNode.getChild(index);
		Node<T> rightChild = medNode.getChild(index+1);
//		if(med.numOfKeys==1){ //while splitting a new root was created with med
		Node<T> merged = new Node<>(medNode,this.getMaxDegree());
		for (int i = 0; i < leftChild.numOfKeys; i++) {
			merged.addKey(leftChild.getKey(i));
		}
		for(int i = 0;i< leftChild.getNumberOfChildren();i++){
			merged.addChild(leftChild.getChild(i));
		}
		merged.addKey(median);
		for (int i = 0; i < rightChild.numOfKeys; i++) {
			merged.addKey(rightChild.getKey(i));
		}
		for(int i = 0;i< rightChild.getNumberOfChildren();i++){
			merged.addChild(rightChild.getChild(i));
		}
		leftChild.parent.removeChild(leftChild);
		rightChild.parent.removeChild(rightChild);
		return merged;
	}

	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample(){
		List<Integer> insertions =  new ArrayList<Integer>();
		insertions.add(1);
		insertions.add(2);
		insertions.add(3);
		insertions.add(4);
		return insertions;
	}
}
