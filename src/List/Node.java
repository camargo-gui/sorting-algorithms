package List;

public class Node {
    private Node prev;
    private Node next;
    private int info;

    public Node () {
        this.prev = null;
        this.next = null;
        this.info = -1;
    }

    public Node (Node prev, Node next, int info) {
        this.prev = prev;
        this.next = next;
        this.info = info;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext(){
        return next;
    }

    public int getInfo() {
        return info;
    }

    public void setPrev(Node prev){
        this.prev = prev;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public void setInfo(int info){
        this.info = info;
    }
}
