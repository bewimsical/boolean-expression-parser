public class TruthTable {
    private String key;
    private final String infix;
    private final String postfix;
    private boolean [][] values;
    private int count = 0;
    public TruthTable(String expression){
        this.postfix = Parser.parse(expression);
        this.infix = expression;
        this.key = generateKey();
        this.values = new boolean[(int)Math.pow(2,(double)key.length())][key.length()];
        generateKeyTable(key.length(), 0, new boolean[key.length()]);
    }

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
    private Queue decomposeExpression(){
        Queue<String> expression = new Queue<>();
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
    private void generateKeyTable(int size, int index, boolean [] row){
        if (index == size){
            for(int i = 0; i < size; i++) {
                this.values[count][i] = row[i];
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

    public void printTable(){
        char o;
        for (int i = 0; i < key.length(); i++){
            System.out.print(key.charAt(i) + " | ");
        }
        System.out.println(infix);

        for (boolean[] row: values){
            for(boolean col:row){
                o = col ? 'T':'F';
                System.out.print(o + " | ");
            }
            o = evaluateExpression(row, this.postfix) ? 'T' : 'F';
            System.out.println(o);
        }
    }
    public void printExpandedTable(){
        Queue<String> expressions = decomposeExpression();
        String[] expressonsArray = new String[expressions.length()];
        int length = expressions.length();
        String o;
        char c;

        for (int i = 0; i < key.length(); i++){
            System.out.print(key.charAt(i) + " | ");
        }
        for(int i = 0; i < length; i++){
            o = expressions.deQueue();
            expressonsArray[i] = o;
            System.out.print(o + " | ");
        }
        System.out.println();

        for (boolean[] row: values) {
            for (boolean col : row) {
                c = col ? 'T' : 'F';
                System.out.print(c + " | ");
            }

            for(int i =0; i < expressonsArray.length; i++){
                int middle = expressonsArray[i].length()/2;
                o = "";
                for (int j = 0; j <= expressonsArray[i].length(); j++){
                    if(j == middle) {
                        o += evaluateExpression(row, Parser.parse(expressonsArray[i])) ? "T" : "F";
                    }
                    else {
                        o += " ";
                    }
                }
                System.out.print(o + "| ");
            }
            System.out.println();
        }

    }
}
