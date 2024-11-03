public class Main {
    public static void main(String[] args) {
        String expression = "!(A*B+C+!D)";
        TruthTable table = new TruthTable(expression);
        table.printTable();
        table.printExpandedTable();
        //Quiz Thursday - time complexity, stack, queue  - 10 questions
    }

}
