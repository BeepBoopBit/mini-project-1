/**
 * Checks whether a value is numerical, operator, or a space
 */
public class Checker {
    final String NUMBERS = "1234567890";
    final String OPERATOR = "+-*/";
    final char SPACE = ' ';
    enum CHECK_TYPE  {NUMBER, OPERATOR};

    private boolean constantChecker(char input, CHECK_TYPE type){

        // Check the type of checking to be done
        String checkingString = "";
        switch(type){
            case NUMBER -> {
                checkingString = NUMBERS;
            }
            case OPERATOR -> {
                checkingString = OPERATOR;
            }
        }

        // Find the input in the string
        for(int i = 0; i < checkingString.length(); ++i){
            if (input == checkingString.charAt(i)){
                return true;
            }
        }
        return false;
    }

    // Abstract "constantChecker" for easier usage
    public boolean isANumber(char input){
        return constantChecker(input, CHECK_TYPE.NUMBER);
    }

    // Abstract "constantChecker" for easier usage
    public boolean isAnOperator(char input){
        return constantChecker(input, CHECK_TYPE.OPERATOR);
    }

    public boolean isSpace(char input){
        return input == SPACE;
    }

}
