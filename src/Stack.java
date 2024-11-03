
public class Stack<E> {
    private Node<E> top;
    Stack(){
        this.top = null;
    }
    public boolean isEmpty(){
        return (this.top == null);
    }
    public void push(E val){
        if (isEmpty()){
            top = new Node<E>(val);
        }
        else{
            Node<E> temp = new Node<E>(val);
            temp.next = top;
            this.top = temp;
        }
    }
    public E pop(){
        if (!isEmpty()){
            E c = top.data;
            top = top.next;
            return c;
        }
        else return null;
    }
    public E peek(){
        if (!isEmpty()){
            return top.data;
        }
        else {
            System.out.println("Stack is empty");
            return null;
        }
    }
}
