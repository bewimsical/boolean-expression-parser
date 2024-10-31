public class Main {
    public static void main(String[] args) {
        String expression = "A*B";
        TruthTable table = new TruthTable(expression);
        table.printTable();


        //System.out.println(expression + " evaluates to  " + TruthTable.evaluateExpression(postfix, values));

        //System.out.println(TruthTable.generateKey(expression));


        // read expression - get count of unique operands
        // evaluate final expression
        // print
        //Quiz Thursaday - time complexity, stack, queue  - 10 questions
    }

}
