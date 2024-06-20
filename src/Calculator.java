
// 5 + 6 - 9 * 8 / 3
// probelm with space
// do the parentehsis support
// do the decimal
import java.util.Scanner;

public class Calculator {

    private double add(double a, double b) {
        return a+b;
    }

    private double subtract(double a, double b){
        return a-b;
    }

    private double multiply(double a, double b){
        return a*b;
    }

    private double divide(double a, double b) throws Exception {
        try{
            return a/b;
        }catch (ArithmeticException e){
            throw new Exception("Can't Divide by Zero");
        }
    }


    /**
     *
     * @param tree
     * @return `double` that contains the result of the Input expression
     * @throws Exception
     */
    private double calculate_from_tree(Tree tree) throws Exception {

        // Create a checker for values
        Checker _checker = new Checker();

        // Recursively Traverse to the left until it's left value is not an operator
        while(_checker.isAnOperator(tree.left.value.charAt(0))){
            calculate_from_tree(tree.left);
        }

        // We know that LHS is a number
        double lhs = Double.parseDouble(tree.left.value);

        // Traverse toward the Right branch
        double rhs = 0;
        try{
            // Try if the right is a number
            rhs = Double.parseDouble(tree.right.value);
        }catch(Exception e){

            // Recursively traverse the right tree until its value is not an operator
            while (_checker.isAnOperator(tree.right.value.charAt(0))){
                calculate_from_tree(tree.right);
            }

            // Update RHS
            rhs = Double.parseDouble(tree.right.value);
        }


        // We know that the tree_value is an operator
        // Process the total value
        double total = 0;
        switch(tree.value){
            case "+":{
                total = add(lhs, rhs);
                break;
            }
            case "-":{
                total = subtract(lhs, rhs);
                break;
            }
            case "*":{
                total = multiply(lhs, rhs);
                break;
            }
            case "/":{
                total = divide(lhs, rhs);
                break;
            }
            default: {
                System.out.println("Number Exception");
                break;
            }
        }

        // Update the tree to reflect the calculated value
        tree.value = ""+total;
        tree.left = null;
        tree.right = null;

        // Return the evaluated expression
        return total;
    }

    public double calculate(String expression){
        // Try to parse the user input
        Checker checker = new Checker();
        Parser parser = new Parser(checker);
        try {
            parser.parseString(expression);
            return calculate_from_tree(parser.getTree());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
