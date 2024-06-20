import java.util.Scanner;

public class Main {

    // Simplify asking for a number
    public static double askForNumber(Scanner scanner) throws Exception {
        System.out.print("> ");
        try{
            return Double.parseDouble(scanner.nextLine());
        }catch(Exception e){
            throw new Exception("Input Error");
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println("----------Calculator----------");
        System.out.println("[!] Start entering a number");
        System.out.println("[!] Enter anything else to exit the program");


        Calculator calc         = new Calculator();
        boolean inCalculator    = true;
        Scanner scanner         = new Scanner(System.in);

        double lhs      = 0,
               rhs      = 0;
        String operator = "";

        // Get the first Number
        try{
            lhs = askForNumber(scanner);
        }catch(Exception e){
            // Return peacefully without the exception printing out
            System.out.println("[!] Exiting....");
            return;
        }

        while(inCalculator){
            try{
                rhs = askForNumber(scanner);
            }catch(Exception e){
                System.out.println("[!] Exiting....");
                break;
            }

            System.out.print("Enter an operation (+, -, *, /): ");
            operator = scanner.nextLine();
            switch(operator){
                case "+": lhs=calc.add(lhs,rhs); break;
                case "-": lhs=calc.sub(lhs,rhs); break;
                case "*": lhs=calc.mul(lhs,rhs); break;
                case "/": lhs=calc.div(lhs,rhs); break;
                default: inCalculator = false; break;
            }
        }

        System.out.println("The total is: " + lhs);
    }
}