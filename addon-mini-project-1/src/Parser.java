public class Parser {
    private final Checker _checker;
    private final Tree _tree;

    Parser(Checker checker){
        _checker = checker;
        _tree = new Tree();
    }

    public void parseString(String userExpression) throws Exception {

        // holds the first character to handle expression starting with a sign
        String current_number = "" + userExpression.charAt(0);

        // Check if the first character is a sign
        boolean isPreviousOperator = _checker.isAnOperator(current_number.charAt(0));

        // update the tree state if the current_number is a parenthesis
        // * this is done to ensure that expression starting with '(' will be process accordingly
        int parenthesisCount = 0;
        if(current_number.equals("(")){
            parenthesisCount += 1;
            _tree.set_isProcessingParenthesis(true);
            current_number = "";
        }

        // Loop through all the expression starting at index 1
        for(int i = 1; i < userExpression.length(); ++i){

            // Get the current char for processing
            char current_char = userExpression.charAt(i);

            // If it's a space, continue
            boolean isSpace = _checker.isSpace(current_char);
            if(isSpace){
                continue;
            }

            // Handling Parenthesis
            boolean isOpenParenthesis = _checker.isOpenParenthesis(current_char);
            boolean isCloseParenthesis = _checker.isCloseParenthesis(current_char);

            // Token Flags
            boolean isOperator = _checker.isAnOperator(current_char);
            boolean isNumber = _checker.isANumber(current_char);
            if(isOperator){

                // If it's not a sign
                if(!isPreviousOperator){
                    isPreviousOperator = true;

                    // If it's empty, then we'll just push operator
                    if(current_number.isEmpty()){
                        _tree.operatorInsert(""+current_char);
                        continue;
                    }

                    // Otherwise, insert operator and number.
                    _tree.numericalInsert(current_number);
                    _tree.operatorInsert(""+current_char);
                    current_number = "";
                }

                // If it's a sign
                else{
                    current_number += current_char;
                }
            }
            else if(isNumber){
                current_number += current_char;
                isPreviousOperator = false;
            }
            else if(isOpenParenthesis){
                _tree.set_isProcessingParenthesis(true);
                parenthesisCount += 1;
            }
            else if(isCloseParenthesis){

                // If there's something inside the current_number, insert it to the tree
                if(!current_number.isEmpty()){
                    _tree.numericalInsert(current_number);
                    current_number = "";
                }

                // Update the tree state
                parenthesisCount -= 1;
                if(parenthesisCount < 0){
                    throw new Exception("[!] Exception: Mismatched Parenthesis");
                }
                _tree.set_isProcessingParenthesis(false);
            }
            // If it's a something else
            else {

                // If it's a decimal, append it
                boolean isDecimal = current_char == '.';
                if(isDecimal){
                    current_number += current_char;
                    continue;
                }

                // Throw Exception if it's not
                throw new Exception("Exception at token: " + current_char);
            }
        }

        try{
            // Test if it's a number
            double value = Double.parseDouble(current_number);

            // Insert the last value if it's
            _tree.numericalInsert(current_number);
        }catch(Exception e){

            // If it's empty, then it's not an exception
            if(current_number.isEmpty()){
                return;
            }

            throw new Exception("Not A Valid Input");
        }

    }

    public Tree getTree(){
        return _tree;
    }
}
