package List;

public class List {

    private Node start;
    private Node end;

    public List (){
        initialize();
    }

    public List(Node start, Node end){
        this.start = start;
        this.end = end;
    }

    public void initialize() {
        start = null;
        end = null;
    }

    public void insertAtStart(int info){
        Node element = new Node(null, null, info);
        if(start == null) {
            start = end = element;
        }
        else {
            element.setNext(start);
            start.setPrev(element);
            start = element;
        }
    }

    public void insertAtEnd(int info){
        Node element = new Node(null, null, info);
        if(end == null){
            start = end = element;
        }
        else {
            element.setPrev(end);
            end.setNext(element);
            end = element;
        }
    }

    public void print(){
        Node aux = start;
        while(aux != null){
            System.out.println(aux.getInfo());
            aux = aux.getNext();
        }
    }

    public void remove(int info){
        Node aux = exhaustive_search(info);
        if (aux != null){
            if (aux.getPrev() == null || aux.getNext() == null){
                if (aux.getPrev() == null){
                    start = start.getNext();
                    start.setPrev(null);
                }
                if (aux.getNext() == null){
                    if (end.getPrev() == null){
                        end = start;
                    }
                    else {
                        end = end.getPrev();
                        end.setNext(null);
                    }
                }
            }
            else {
                aux.getPrev().setNext(aux.getNext());
                aux.getNext().setPrev(aux.getPrev());
            }
        }
    }

    public Node exhaustive_search(int info){
        Node aux = start;
        while(aux != null && aux.getInfo() != info){
            aux = aux.getNext();
        }
        return aux;
    }

    public int length(){
        int l = 0;
        Node aux = start;
        while (aux != null){
            l++;
            aux = aux.getNext();
        }
        return l;
    }

    public int length(Node start, Node end){
        int l = 0;
        Node aux = start;
        while (aux != end){
            l++;
            aux = aux.getNext();
        }
        return l;
    }

    public Node middle_calculate(Node start, Node end){
        int middle = Math.floorDiv(length(start, end),2);
        Node aux = start;
        for(int i = 0; i<middle; i++){
            aux = aux.getNext();
        }
        return aux;
    }

    public Node binary_search_to_insertion(int info){
        Node start = this.start, end = this.end, middle = middle_calculate(start, end);
        while(start.getInfo() < end.getInfo() && info != middle.getInfo()){
            if (info > middle.getInfo()){
                start = middle.getNext();
            }
            else {
                end = middle.getPrev();
            }
            middle = middle_calculate(start, end);
        }
        if (info > middle.getInfo()){
            return middle.getNext();
        }
        return middle;
    }

    public void insertion_sort(){
        int auxInfo;
        Node pos = start;
        Node aux = start.getNext();
        while (aux != null){
            while (pos != null && pos.getNext().getInfo() < pos.getInfo()){
                auxInfo = pos.getInfo();
                pos.setInfo(pos.getNext().getInfo());
                pos.getNext().setInfo(auxInfo);
                pos = pos.getPrev();
            }
            pos = aux;
            aux = aux.getNext();
        }
    }

}
