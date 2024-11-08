public class TruthTable {
    private String key; // Stores unique variables in the expression
    private final String infix; // Stores the original infix expression
    private final String postfix; // Stores the postfix version of the expression
    private boolean [][] values;  // Stores boolean values for each row of the truth table
    private int count = 0; // Counts rows in the truth table while assigning values
    // Constructor that initializes the truth table for a given logical expression
    public TruthTable(String expression){
        this.postfix = Parser.parse(expression);
        this.infix = expression;
        this.key = generateKey();
        this.values = new boolean[(int)Math.pow(2,(double)key.length())][key.length()];
        generateKeyTable(key.length(), 0, new boolean[key.length()]);
    }
    // Generates a unique key with all variables in the expression, excluding duplicates
    private String generateKey(){
        String key = "";
        for (int i = 0; i < this.postfix.length(); i++){
            char c = this.postfix.charAt(i);
            if (Character.isLetterOrDigit(c) && !key.contains(Character.toString(c))){
                key += c;
            }
        }
        return key;
    }
    // Evaluates the expression for a specific row of boolean values
    private boolean evaluateExpression( boolean[] values, String expression){
        Stack<Character> stack  = new Stack<>();
        char c;
        char o;
        for(int i = 0; i < expression.length(); i++){
            c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)){
              o = values[this.key.indexOf(c)] ? '1' : '0';
              stack.push(o);
            }
            else{
                switch (c){
                    case '+':
                        o = stack.pop() == '1' || stack.pop() == '1' ? '1' : '0';
                        stack.push(o);
                        break;
                    case '*':
                        o = stack.pop() == '1' && stack.pop() == '1' ? '1' : '0';
                        stack.push(o);
                        break;
                    case '!':
                        o = stack.pop() == '1' ? '0' : '1';
                        stack.push(o);
                        break;
                    default:
                        System.out.println("invalid operator");
                        break;
                }
            }
        }
        return stack.pop() == '1';
    }
    // Decomposes postfix expression to display each sub-expression. Returns a queue containing sub expressions
    private Queue<String> decomposeExpression(){
        Queue<String> expression = new Queue<>();// Queue to store sub-expressions
        Stack<String> stack = new Stack<>();
        char c;
        String x,y;
        for(int i = 0; i < this.postfix.length(); i++){
            c = this.postfix.charAt(i);
            if (Character.isLetterOrDigit(c)){
                stack.push(Character.toString(c));
            }
            else{
                switch (c){
                    case '+':
                        x = stack.pop();
                        y = stack.pop();
                        expression.enQueue(y + "+" + x);
                        stack.push("("+y + "+" + x+")");
                        break;
                    case '*':
                        x = stack.pop();
                        y = stack.pop();
                        expression.enQueue(y + "*" + x);
                        stack.push("("+y + "*" + x+")");
                        break;
                    case '!':
                        x = stack.pop();
                        expression.enQueue("!"+ x);
                        stack.push("!"+ x);
                        break;
                    default:
                        System.out.println("invalid operator");
                        break;
                }
            }
        }
        return expression;
    }
    //RECURSIVE method to generate all possible combinations of boolean values. - EXTRA CREDIT
    private void generateKeyTable(int size, int index, boolean [] row){
        if (index == size){
            for(int i = 0; i < size; i++) {
                this.values[count][i] = row[i];// Adds row to truth table
            }
            count++;

        }
        else{
            for(int i = 0; i < 2; i ++){
                row[index] = i == 0;
                generateKeyTable(size, index + 1, row);
            }
        }
    }
    // Prints the truth table with values of each variable and final expression result
    public void printTable(){
        char o;
        String s;
        for (int i = 0; i < key.length(); i++){
            System.out.print(key.charAt(i) + " │ ");
        }
        System.out.println(infix);
        System.out.print("──");
        for (int i = 0; i < key.length(); i++){
            System.out.print("┼");
            if(i < key.length() - 1) {
                System.out.print("───");
            }
        }
        for (int i = 0; i < infix.length() +2; i++){
            System.out.print("─");
        }


        System.out.println();
        for (boolean[] row: values){
            for(boolean col:row){
                o = col ? 'T':'F';
                System.out.print(o + " │ ");
            }
            int middle = this.postfix.length()/2;
            s = "";
            for (int j = 0; j <= this.postfix.length(); j++){
                if(j == middle) {
                    s += evaluateExpression(row, this.postfix) ? "T" : "F";
                }
                else {
                    s += " ";
                }
            }
            System.out.println(s);
        }
    }
    // Prints the truth table with additional columns for each sub-expression - EXTRA POINTS
    public void printExpandedTable(){
        Queue<String> expressions = decomposeExpression();
        String[] expressionArray = new String[expressions.length()];
        int length = expressions.length();
        String o;
        char c;

        for (int i = 0; i < key.length(); i++){
            System.out.print(key.charAt(i) + " │ ");
        }
        for(int i = 0; i < length; i++){
            o = expressions.deQueue();
            expressionArray[i] = o;
            System.out.print(o);
            if (i < length -1) {
                System.out.print(" │ ");
            }
        }
        System.out.println();
        System.out.print("──");
        for (int i = 0; i < key.length(); i++) {
            System.out.print("┼");
            if (i < key.length() - 1) {
                System.out.print("───");
            }
        }
        for(int i = 0; i < expressionArray.length; i++){
            for(int j = 0; j < expressionArray[i].length()+2; j++){
                System.out.print("─");
            }
            if(i < expressionArray.length -1){
                System.out.print("┼");
            }
        }
        System.out.println();
        for (boolean[] row: values) {
            for (boolean col : row) {
                c = col ? 'T' : 'F';
                System.out.print(c + " │ ");
            }

            for(int i = 0; i < expressionArray.length; i++){
                int middle = expressionArray[i].length()/2;
                o = "";
                for (int j = 0; j <= expressionArray[i].length(); j++){
                    if(j == middle) {
                        o += evaluateExpression(row, Parser.parse(expressionArray[i])) ? "T" : "F";
                    }
                    else {
                        o += " ";
                    }
                }
                System.out.print(o);
                if (i < expressionArray.length - 1){
                    System.out.print("│ ");
                }
            }
            System.out.println();
        }

    }
}
