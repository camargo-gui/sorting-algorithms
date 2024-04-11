package Stack;

import List.Node;

public class Stack {

    private Element start;
    private int length;

    public void push(int element){
        Element newElement = new Element(element);
        if (length == 0){
            start = newElement;
        }
        else {
            newElement.setNext(start);
            start = newElement;
        }
        length++;
    }

    public int pop(){
        int content = start.getContent();
        start = start.getNext();
        length--;
        return content;
    }

    public boolean isEmpty(){
        return length == 0;
    }
}
