class Node {
    public char data;
    public Node next;
    Node(char data) {
        this.data = data;
        this.next = null;
    }
}
public class Stack {
    private Node top;
    Stack(){
        this.top = null;
    }
    public boolean isEmpty(){
        return (this.top == null);
    }
    public void push(char val){
        if (isEmpty()){
            top = new Node(val);
        }
        else{
            Node temp = new Node(val);
            temp.next = top;
            this.top = temp;
        }
    }
    public char pop(){
        if (!isEmpty()){
            char c = top.data;
            top = top.next;
            return c;
        }
        else return ' ';
    }
    public int peek(){
        if (!isEmpty()){
            return top.data;
        }
        else {
            System.out.println("Stack is empty");
            return 0;
        }
    }
}
