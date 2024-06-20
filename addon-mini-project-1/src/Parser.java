public class Parser {
    private final Checker _checker;
    private final Tree _tree;

    Parser(Checker checker){
        _checker = checker;
        _tree = new Tree();
    }

    /**
     * @param userExpression
     * @throws Exception
     * @description parsed the number of string into a binary tree
     */
    public void parseString(String userExpression) throws Exception {

        // Holds the number of the parse expression per token.
        String current_number = "" + userExpression.charAt(0);  // holds the first character to avoid problems with negative numbers

        // Loop through the whole expression
        boolean isPreviousOperator = false;
        for(int i = 1; i < userExpression.length(); ++i){

            // Get the current char for processing
            char current_char = userExpression.charAt(i);

            // If the character is a space
            boolean isSpace = _checker.isSpace(current_char);
            if(isSpace){
                continue;
            }
            boolean isOperator = _checker.isAnOperator(current_char);
            if(isOperator){

                // If it's not a sign
                if(!isPreviousOperator){
                    isPreviousOperator = true;

                    // If it's empty, continue
                    if(current_number.isEmpty()){
                        // Insert if the current character is an operator
                        if(_checker.isAnOperator(current_char)){
                            _tree.operatorInsert(""+current_char);
                        }

                        continue;
                    }

                    // Insert numerical data
                    _tree.numericalInsert(current_number);
                    _tree.operatorInsert(""+current_char);
                    current_number = "";
                }

                // If it's a sign
                else{
                    current_number += current_char;
                }


            }

            // If the character is a number
            else if(_checker.isANumber(current_char)){
                current_number += current_char;
                isPreviousOperator = false;
            }

            // If it's a something else
            else {
                // Exception
                throw new Exception("Exception at token: " + current_char);
            }


        }

        try{
            // Test if it's a number
            double value = Double.parseDouble(current_number);

            // Insert the last value
            _tree.numericalInsert(current_number);
        }catch(Exception e){
            throw new Exception("Not A Valid Input");
        }

    }

    public Tree getTree(){
        return _tree;
    }
}
