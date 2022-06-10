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
        if (!this.CaseStack.isEmpty()) {
            this.CaseStack.pop();
            if(!this.CaseStack.isEmpty()) {
                int caseNum = this.CaseStack.peek();
                switch (caseNum) {
                    case 1:
                        Node popped_C1 = NodeRotatedStack.pop();
                        if (popped_C1.parent == null) {
                            this.root = rotateLeft(popped_C1);
                        } else {
                            if (popped_C1.parent.left == popped_C1) {
                                popped_C1.parent.left = rotateLeft(popped_C1);
                            } else {
                                popped_C1.parent.right = rotateLeft(popped_C1);
                            }
                            //popped_C1.parent.left = rotateLeft(popped_C1);
                        }
                        this.CaseStack.pop();
                        break;

            case 2:
                Node popped_C2 = NodeRotatedStack.pop();
                if (popped_C2.parent == null) {
                    this.root.left = rotateLeft(popped_C2);
                    Node popped_C22 = NodeRotatedStack.pop();

                            if (popped_C22.parent == null) {
                                popped_C22.parent = rotateRight(popped_C22);
                                this.root = popped_C22.parent;
                            } else {
                                if (popped_C22.parent.right == popped_C22) {
                                    popped_C22.parent.right = rotateRight(popped_C22);
                                } else {
                                    popped_C22.parent.left = rotateRight(popped_C22);
                                }
                            }
                        } else {
                            if (popped_C2.parent.left == popped_C2) {
                                popped_C2.parent.left = rotateLeft(popped_C2);
                            } else {
                                popped_C2.parent.right = rotateLeft(popped_C2);
                            }
                            Node popped_C22 = NodeRotatedStack.pop();
                            if (popped_C22.parent == null) {
                                popped_C22.parent = rotateRight(popped_C22);
                                this.root = popped_C22.parent;
                            } else {
                                if (popped_C22.parent.right == popped_C22) {
                                    popped_C22.parent.right = rotateRight(popped_C22);
                                } else {
                                    popped_C22.parent.left = rotateRight(popped_C22);
                                }
                            }
                        }
                        this.CaseStack.pop();
                        break;

                    case 3:
                        Node popped_C3 = NodeRotatedStack.pop();
                        if (popped_C3.parent == null) {
                            popped_C3.parent = rotateRight(popped_C3);
                            this.root = popped_C3.parent;
                        } else {
                            if (popped_C3.parent.right == popped_C3) {
                                popped_C3.parent.right = rotateRight(popped_C3);
                            } else {
                                popped_C3.parent.left = rotateRight(popped_C3);
                            }
                        }
                        this.CaseStack.pop();
                        break;
                    case 4:
                        Node popped_C4 = NodeRotatedStack.pop();
                        if (popped_C4.parent == null) {
                            popped_C4.parent = rotateRight(popped_C4);
                            this.root = popped_C4.parent;


                            Node popped_C42 = NodeRotatedStack.pop();
                            if (popped_C42.parent == null) {

                                popped_C42.parent = rotateLeft(popped_C42);
                                this.root = popped_C42.parent;
                            } else {
                                if (popped_C42.parent.left == popped_C42) {
                                    popped_C42.parent.left = rotateLeft(popped_C42);
                                } else {
                                    popped_C42.parent.right = rotateLeft(popped_C42);
                                }
                            }
                        } else {
                            if (popped_C4.parent.right == popped_C4) {
                                popped_C4.parent.right = rotateRight(popped_C4);
                            } else {
                                popped_C4.parent.left = rotateRight(popped_C4);
                            }

                            Node popped_C42 = NodeRotatedStack.pop();
                            if (popped_C42.parent == null) {

                                popped_C42.parent = rotateLeft(popped_C42);
                                this.root = popped_C42.parent;
                            } else {
                                if (popped_C42.parent.left == popped_C42) {
                                    popped_C42.parent.left = rotateLeft(popped_C42);
                                } else {
                                    popped_C42.parent.right = rotateLeft(popped_C42);
                                }
                                //popped_C42.parent.left = rotateLeft(popped_C42);
                            }
                        }
                        this.CaseStack.pop();
                        break;
                }
            }
            Node toDelete = InsertedStack.pop(); // a leaf
            this.delete(toDelete);

        }
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
//        if(root==null) {
//            return 0;
//        }
        return this.root.Rank(value);
    }
}












// The following is a previous version

//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Deque;
//import java.util.List;
//
//public class BacktrackingAVL extends AVLTree {
//
//
//    // For clarity only, this is the default ctor created implicitly.
//    public BacktrackingAVL() {
//        super();
//    }
//
//
//
//    //You are to implement the function Backtrack.
//    public void Backtrack() {
//        this.CaseStack.pop();
//        int caseNum = this.CaseStack.peek();
//        switch (caseNum) {
//            case 1:
//                Node popped_C1 = NodeRotatedStack.pop();
//                if (popped_C1.parent == null){
//                    this.root = rotateLeft(popped_C1);
//                }
//                else {
//                    if (popped_C1.parent.left == popped_C1) {
//                        popped_C1.parent.left = rotateLeft(popped_C1);
//                    }
//                    else {
//                        popped_C1.parent.right = rotateLeft(popped_C1);
//                    }
//                    //popped_C1.parent.left = rotateLeft(popped_C1);
//                }
//                this.CaseStack.pop();
//                break;
//
//            case 2:
//                Node popped_C2 = NodeRotatedStack.pop();
//                if (popped_C2.parent == null) {
//                    this.root.left = rotateLeft(popped_C2);
//                    Node popped_C22 = NodeRotatedStack.pop();
//
//                    if (popped_C22.parent == null) {
//                        this.root.right = rotateRight(popped_C22);
//                    }
//                    else {
//                        popped_C22.parent.right = rotateRight(popped_C22);
//                    }
//                }
//                else {
//                    if (popped_C2.parent.left == popped_C2) {
//                        popped_C2.parent.left = rotateLeft(popped_C2);
//                    }
//                    else {
//                        popped_C2.parent.right = rotateLeft(popped_C2);
//                    }
//                    Node popped_C22 = NodeRotatedStack.pop();
//                    if (popped_C22.parent == null) {
//                        this.root.right = rotateRight(popped_C22);
//                    }
//                    else {
//                        popped_C22.parent.right = rotateRight(popped_C22);
//                    }
//                }
//                this.CaseStack.pop();
//                break;
//
//            case 3:
//                Node popped_C3 = NodeRotatedStack.pop();
//                if (popped_C3.parent == null) {
//                    this.root = rotateRight(popped_C3);
//                }
//                else {
//                    if (popped_C3.parent.right == popped_C3) {
//                        popped_C3.parent.right = rotateRight(popped_C3);
//                    }
//                    else {
//                        popped_C3.parent.left = rotateRight(popped_C3);
//                    }
//                }
//                this.CaseStack.pop();
//                break;
//            case 4:
//                Node popped_C4 = NodeRotatedStack.pop();
//                if (popped_C4.parent == null) {
//                    this.root = rotateRight(popped_C4);
////                    //this.root = popped_C4.parent;
//                    Node popped_C42 = NodeRotatedStack.pop();
//                    if (popped_C42.parent == null) {
//                        this.root = rotateLeft(popped_C42);
//                    }
//                    else {
//                        if (popped_C42.parent.left == popped_C42) {
//                            popped_C42.parent.left = rotateLeft(popped_C42);
//                        }
//                        else{
//                            popped_C42.parent.right = rotateLeft(popped_C42);
//                        }
//                    }
//                }
//                else {
//                    if (popped_C4.parent.right == popped_C4) {
//                        popped_C4.parent.right = rotateRight(popped_C4);
//                    }
//                    else {
//                        popped_C4.parent.left = rotateRight(popped_C4);
//                    }
//
//                    Node popped_C42 = NodeRotatedStack.pop();
//                    if (popped_C42.parent == null) {
//                        this.root = rotateLeft(popped_C42);
//                    }
//                    else {
//                        if (popped_C42.parent.left == popped_C42) {
//                            popped_C42.parent.left = rotateLeft(popped_C42);
//                        }
//                        else {
//                            popped_C42.parent.right = rotateLeft(popped_C42);
//                        }
//                        //popped_C42.parent.left = rotateLeft(popped_C42);
//                    }
//                }
//                this.CaseStack.pop();
//                break;
//        }
//        Node toDelete = InsertedStack.pop(); // a leaf
//        this.delete(toDelete);
//
//    }
//
//    public void delete(Node node){
//        if (node.parent == null){
//            this.root = null;
//        }
//        else {
//            if (node.parent.value > node.value) {
//                node.parent.left = null;
//            } else {
//                node.parent.right = null;
//            }
//        }
//    }
//
//    //Change the list returned to a list of integers answering the requirements
//    public static List<Integer> AVLTreeBacktrackingCounterExample() {
//        List<Integer> insertions =  new ArrayList<Integer>();
//        insertions.add(1);
//        insertions.add(2);
//        insertions.add(3);
//        return insertions;
//    }
//
//    public int Select(int index) {
//        if (root != null) {
//            return this.root.Select(index);
//        }
//        throw new IllegalArgumentException();
//    }
//
//    public int Rank(int value) {
//        return this.root.Rank(value);
//    }
//}
