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


        return output;
    }
}
