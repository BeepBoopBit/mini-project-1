import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void menuCalculator(){
        boolean inInput = true;
        Scanner my_scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        while(inInput){
            System.out.println("---Input (type \"exit\" to exit)");
            System.out.print(">");
            String user_input = my_scanner.nextLine();

            if(Objects.equals(user_input, "exit")){
                inInput = false;
            }else{
                double result = calc.calculate(user_input);
                if(result != Double.POSITIVE_INFINITY){
                    System.out.println("The result is: " + result);

                }
            }
        }
    }

    public static void menuExamples(){
        System.out.println("\n---------------------------");
        System.out.println("Examples:");
        System.out.println("1.) 1+2+3*4/6*2");
        System.out.println("2.) 1 + 2 + 3 * 4 / 6 * 2");
        System.out.println("Notes:");
        System.out.println("* Doesn't support Parenthesis");
        System.out.println("---------------------------\n");
    }


    public static void menu(){
        System.out.println("-----Calculator-----");

        // Main Loop
        boolean in_calculator = true;
        while(in_calculator){

            // Menu
            System.out.println("1. Start");
            System.out.println("2. Examples/Help");
            System.out.println("3. Exit");

            // Get the user Input
            System.out.print("> ");
            Scanner my_scanner = new Scanner(System.in);
            String user_input = my_scanner.nextLine();

            switch(user_input){
                case "1": {
                    menuCalculator();
                    break;
                }
                case "2":{
                    menuExamples();
                    break;
                }
                case "3":{
                    in_calculator = false;
                    break;
                }
                default:{
                    System.out.println("[!] Invalid Input");
                    break;
                }
            }
        }
    }

    public static void main(String[] args){
        menu();
    }
}
