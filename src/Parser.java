public class Parser {
    // This method is used to check the order of operations
    private static int getPrecedence(char o){
        if (o == '+')
            return 1;
        if (o == '*')
            return 2;
        if (o == '!')
            return 3;
        else
            return -1;
    }
    // this method is used to parse the infix expression into a postfix expression.
    static String parse(String expression){
        String output = ""; //create empty string for output
        Stack operators = new Stack(); //create stack for operators
        char c;
        //iterate through the expression
        for (int i = 0; i < expression.length(); i++){
            c = expression.charAt(i);
            //check if the char is a letter. add letter to the output
            if (Character.isLetterOrDigit(c)){
                output += c;
            }
            // check if char is '('. add to the operators stack
            else if (c == '('){
                operators.push(c);
            }
            //check if char is '('. pop from the operators stack and append to the output until '('
            else if (c == ')'){
                while(!operators.isEmpty() && operators.peek() != '('){
                    output += operators.pop();
                }
                operators.pop();
            }
            // check precedence of operators - pop off higher precedence ones into the output
            else{
                while (!operators.isEmpty() && getPrecedence(c) <= getPrecedence(operators.peek())){
                    output += operators.pop();
                }
                //add lower precedence operators to the stack
                operators.push(c);
            }
        }
        //pop all remaining operators from the operators stack into the output
        while(!operators.isEmpty()){
            output += operators.pop();
        }


        return output;
    }
}
