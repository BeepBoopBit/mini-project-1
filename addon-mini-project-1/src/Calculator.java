
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
        // Handle Division by zero
        if(b == 0){
            throw new ArithmeticException("Can't Divide by zero");
        }
        return a/b;
    }

    private double calculate_from_tree(Tree tree) throws Exception {

        // Create a checker for values
        Checker _checker = new Checker();

        // Recursively Traverse to the left until it's left value is a number
        double lhs = 0;
        try{
            lhs = Double.parseDouble(tree.left.value);
        }catch(Exception e){
            calculate_from_tree(tree.left);
        }

        // Update LHS Value
        lhs = Double.parseDouble(tree.left.value);

        // Recursively traverse the right tree until its value is a number
        double rhs = 0;
        try{
            rhs = Double.parseDouble(tree.right.value);
        }catch(Exception e){
            calculate_from_tree(tree.right);
        }

        // Update RHS
        rhs = Double.parseDouble(tree.right.value);

        // Do the Calculation
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
        Checker checker = new Checker();
        Parser parser = new Parser(checker);
        try {
            // Try to parse the user input and calculate the expression
            parser.parseString(expression);
            return calculate_from_tree(parser.getTree());
        } catch (Exception e) {
            System.out.println("[!] Not A Valid Input");
            return Double.POSITIVE_INFINITY;
        }
    }
}
