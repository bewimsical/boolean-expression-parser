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
    private boolean evaluateExpression( boolean[] values){
        Stack stack  = new Stack();
        char c;
        char o;
        for(int i = 0; i < this.postfix.length(); i++){
            c = this.postfix.charAt(i);
            if (Character.isLetterOrDigit(c)){
              o = values[this.key.indexOf(c)] ? 'T' : 'F';
              stack.push(o);
            }
            else{
                switch (c){
                    case '+':
                        o = stack.pop() == 'T' || stack.pop() == 'T' ? 'T' : 'F';
                        stack.push(o);
                        break;
                    case '*':
                        o = stack.pop() == 'T' && stack.pop() == 'T' ? 'T' : 'F';
                        stack.push(o);
                        break;
                    case '!':
                        o = stack.pop() == 'T' ? 'F' : 'T';
                        stack.push(o);
                        break;
                    default:
                        System.out.println("invalid operator");
                        break;
                }
            }
        }
        return stack.pop() == 'T';
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
        int count = 4*key.length() + infix.length() + 1;
        for (int i = 0; i < key.length(); i++){
            System.out.print(key.charAt(i) + " | ");
        }
        System.out.println(infix);
        for(int i = 0; i < count; i++){
            System.out.print('-');
        }
        System.out.println();
        for (boolean[] row: values){
            for(boolean col:row){
                o = col ? 'T':'F';
                System.out.print(o + " | ");
            }
            o = evaluateExpression(row) ? 'T' : 'F';
            System.out.println(o);
        }
    }
}
