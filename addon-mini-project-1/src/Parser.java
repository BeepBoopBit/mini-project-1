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
        String current_number = "";

        // Loop through the whole expression
        for(int i = 0; i < userExpression.length(); ++i){

            // Get the current char for processing
            char current_char = userExpression.charAt(i);

            // If the character is a space
            if(_checker.isSpace(current_char)  || _checker.isAnOperator(current_char)){

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
                current_number = "";
            }

            // If the character is a number
            else if(_checker.isANumber(current_char)){
                current_number += current_char;
            }

            // If it's a something else
            else {
                // Exception
                throw new Exception("Exception at token: " + current_char);
            }

            // Insert if the current character is an operator
            if(_checker.isAnOperator(current_char)){
                _tree.operatorInsert(""+current_char);
            }
        }

        // Insert the last value
        _tree.numericalInsert(current_number);
    }

    public Tree getTree(){
        return _tree;
    }
}
