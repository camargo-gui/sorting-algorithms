package Stack;

public class Element {
    private int content;
    private Element next;

    public Element(int content){
        this.content = content;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }
}
