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
            // Try to parse the head value into a double
            // * This check is done for cases where mul(*) and div(/) is the first operation
            // * because mul(*) and div(/) algorithm only replace the left, not changes the head
            double myData = Double.parseDouble(this.value);

            // If it succeeded, change the head into an operator
            changeHead(data);
        }catch(Exception e){

            // Otherwise, check if it's multiplication or division for precedence
            if((Objects.equals(data, "*")) || (Objects.equals(data, "/"))){
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
        // Create a new Tree for base on the current head with its values
        Tree previousHead = new Tree(this.value);
        previousHead.left = this.left;
        previousHead.right = this.right;

        // Change the current head to the new data and set the previous head to its left
        this.value = data;
        this.left = previousHead;
        this.right = null;
    }

}
