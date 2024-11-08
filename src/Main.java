import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String expression;
        int choice = -1;
        TruthTable table;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Truth Table generator!");
        System.out.print("Enter a boolean expression to get started. Use only letters and the following symbols: \n \t * for AND \n\t + for OR \n\t ! for NOT\n");
        expression = sc.nextLine();
        table = new TruthTable(expression);
        do{
            System.out.println("Select one of the following options:");
            System.out.println("\t (1) Print Table");
            System.out.println("\t (2) Print Expanded Table");
            System.out.println("\t (3) Print Table and Expanded Table");
            System.out.println("\t (4) Enter a new expression");
            System.out.println("\t (0) Quit");
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    table.printTable();
                    break;
                case 2:
                    table.printExpandedTable();
                    break;
                case 3:
                    table.printTable();
                    System.out.println();
                    table.printExpandedTable();
                    break;
                case 4:
                    System.out.println("Enter your expression");
                    expression = sc.next();
                    table = new TruthTable(expression);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }while(choice != 0);

    }

}
