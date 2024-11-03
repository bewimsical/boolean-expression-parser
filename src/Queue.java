
public class Queue {
    class Node {
        public String data;
        public Node next;
        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node head;
    public  Queue(){
        head = null;
    }
    public boolean isEmpty(){
        return (head == null);
    }
    public boolean enQueue(String val){
        if (isEmpty()){
            head = new Node(val);
            return  true;
        }
        else{
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = new Node(val);
            return true;
        }
    }
    public String deQueue(){
        if (!isEmpty()){
            String s = head.data;
            head = head.next;
            return s;
        }
        else {
            System.out.println("queue is empty");
            return "EMPTY";
        }
    }
    public String peek(){
        if (!isEmpty()){
            return head.data;
        }
        else {
            System.out.println("Queue is empty");
            return "EMPTY";
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
