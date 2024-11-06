
public class Queue<E> {
    private Node<E> head;
    public  Queue(){
        head = null;
    }
    public boolean isEmpty(){
        return (head == null);
    }
    public boolean enQueue(E val){
        if (isEmpty()){
            head = new Node(val);
            return  true;
        }
        else{
            Node<E> current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = new Node(val);
            return true;
        }
    }
    public E deQueue(){
        if (!isEmpty()){
            E s = head.data;
            head = head.next;
            return s;
        }
        else {
            System.out.println("queue is empty");
            return null;
        }
    }
    public E peek(){
        if (!isEmpty()){
            return head.data;
        }
        else {
            System.out.println("Queue is empty");
            return null;
        }
    }
    public int length(){
        int count = 0;
        if(isEmpty()){
            return count;
        }
        else{
            count = 1;
            Node current = head;
            while(current.next != null){
                count++;
                current = current.next;
            }
        }
        return count;
    }
}
