import java.util.ArrayList;
import java.util.Objects;

public class Tree {
    String value;
    Tree left;
    Tree right;

    private boolean _isProcessingParenthesis = false;
    private ArrayList<Tree> _subtree = new ArrayList<>();
    private int _currentSubtreeIndex = -1;

    Tree(){
        this.value = "";
        this.left = null;
        this.right = null;
    }

    Tree(String value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    Tree(Tree tree){
        this.value = tree.value;
        this.left = tree.left;
        this.right = tree.right;
    }

    private void processSubtree(){

        if(_currentSubtreeIndex >= 1){

            // Append latest subtree to the current subtree
            Tree currentSubTree = _subtree.get(_currentSubtreeIndex-1);
            currentSubTree.right = new Tree(_subtree.get(_currentSubtreeIndex));
            _subtree.remove(_currentSubtreeIndex);
        }else{

            // Change the head if it's empty to the latest subtree
            if(Objects.equals(this.value, "")){
                this.value = _subtree.getFirst().value;
                this.left = _subtree.getFirst().left;
                this.right= _subtree.getFirst().right;
                _subtree.remove(_currentSubtreeIndex);
                return;
            }

            // Otherwise, find an available spot to the right branch and append it there
            Tree headTree = this;
            while(headTree.right != null){
                headTree = headTree.right;
            }

            headTree.right = new Tree(_subtree.getFirst());
            _subtree = new ArrayList<>();
        }
    }

    public void set_isProcessingParenthesis(boolean state){
        _isProcessingParenthesis = state;

        // add a new subtree if it's a new parenthesis
        if(_isProcessingParenthesis){
            _currentSubtreeIndex += 1;
            _subtree.add(new Tree());
        }
        // Otherwise, process it
        else{
            processSubtree();
            _currentSubtreeIndex -= 1;
        }
    }

    public void numericalInsert(String data){

        // Insert in subtree if processing a parenthesis
        if(_isProcessingParenthesis){
            _subtree.get(_currentSubtreeIndex).numericalInsert(data);
            return;
        }

        // Otherwise, insert in the head
        if(Objects.equals(this.value, "")){
            this.value = data;
        }
        else if(this.left == null) {
            this.left = new Tree(data);
        }
        else if(this.right == null){
            this.right = new Tree(data);
        }
        // If there are no available slot. Find slot in the right branch.
        else{
            this.right.numericalInsert(data);
        }
    }

    public void operatorInsert(String data){

        // Insert in subtree if processing a parenthesis
        if(_isProcessingParenthesis){
            _subtree.get(_currentSubtreeIndex).operatorInsert(data);
            return;
        }

        try{
            // Try to parse the head value into a double
            // * This check is done for cases where mul(*) and div(/) is the first operation
            // * because mul(*) and div(/) implementation here only replace the left, not changes the head
            double myData = Double.parseDouble(this.value);

            // If it succeeded, change the head into an operator
            changeHead(data);
        }catch(Exception e){

            // Otherwise, check if it's multiplication or division for precedence
            if((Objects.equals(data, "*")) || (Objects.equals(data, "/"))){

                if(Objects.equals(this.value, "*") || Objects.equals(this.value, "/")){
                    changeHead(data);
                    return;
                }

                // If it's, replace the latest right branch into the new data
                // and attaching the previous branch into the new right
                Tree newTree = new Tree(data);
                newTree.left = this.right;
                this.right = newTree;
            }
            // Change the head into a new operator
            else{
                changeHead(data);
            }
        }
    }

    private void changeHead(String data){

        if(this.value.isEmpty()){
            // Change the current head to the new data and set the previous head to its left
            this.value = data;
        }else{
            // Create a new Tree for base on the current head with its values
            Tree previousHead = new Tree(this);

            // Change the current head to the new data and set the previous head to its left
            this.value = data;
            this.left = previousHead;
            this.right = null;
        }

    }

}
