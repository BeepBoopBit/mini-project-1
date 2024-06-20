import java.util.Objects;

public class Tree {
    String value;
    Tree left;
    Tree right;

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

    public void numericalInsert(String data) throws Exception {

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
        try{
            // Try to parse the value into a double
            double myData = Double.parseDouble(this.value);

            // If it succeed, change the head into an operator
            changeOperator(data);
        }catch(Exception e){

            // Otherwise, check if it's multiplication or division for precedence
            if((Objects.equals(data, "*")) || (Objects.equals(data, "/"))){
                // Create a new tree from the data (operator)
                Tree newTree = new Tree(data);

                // Get the current right tree
                Tree rhs = this.right;

                // Put RHS to the newTree
                newTree.left = rhs;

                // Make the right branch the new tree
                this.right = newTree;

            }else{
                // Change the head into a new operator
                changeOperator(data);
            }
        }
    }

    private void changeOperator(String data){
        // Create a new Tree for the current head
        Tree previousHead = new Tree(this.value);

        // Initialize its values
        previousHead.left = this.left;
        previousHead.right = this.right;

        // Change the current head to the new data and set the previous head to its left
        this.value = data;
        this.left = previousHead;
        this.right = null;
    }

}
