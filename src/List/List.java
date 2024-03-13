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

    public Node getStart(){
        return this.start;
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

    private int max(){
        int major = start.getInfo();
        for(Node i = start; i != null; i = i.getNext()){
            if(i.getInfo() > major){
                major = i.getInfo();
            }
        }
        return major;
    }

    private int min(){
        int minor = start.getInfo();
        for(Node i = start; i != null; i = i.getNext()){
            if(i.getInfo() < minor){
                minor = i.getInfo();
            }
        }
        return minor;
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

    public Node getByPos(int pos){
        Node i = start;
        int currentPos = 0;
        while(i != null && currentPos < pos){
            i = i.getNext();
            currentPos++;
        }
        return i;
    }

    public Node middle_calculate(Node start, Node end){
        int middle = Math.floorDiv(length(start, end),2);
        Node aux = start;
        for(int i = 0; i<middle; i++){
            aux = aux.getNext();
        }
        return aux;
    }

    public Node binary_search_to_insertion(int info, Node end){
        Node start = this.start, middle = middle_calculate(start, end);
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

    public void binary_insertion_sort(){
        Node pos = start, sub, aux;
        while (pos != null){
            sub = binary_search_to_insertion(pos.getInfo(), pos);
            aux = pos;
            pos.getPrev().setNext(pos.getNext());
            pos.getNext().setPrev(pos.getPrev());
            sub.getNext().setPrev(aux);
            sub.getPrev().setNext(aux);
            pos = pos.getNext();
        }
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

    public void selection_sort(){
        Node pos = start, minor, i;
        int aux;
        while (pos.getNext() != null){
            i = pos.getNext();
            minor = pos;
            while (i != null) {
                if (i.getInfo() < minor.getInfo()){
                    minor = i;
                }
                i = i.getNext();
            }
            aux = minor.getInfo();
            minor.setInfo(pos.getInfo());
            pos.setInfo(aux);
            pos = pos.getNext();
        }
    }

    public void bubble_sort(){
        Node end = this.end, aux = start;
        int auxInfo;
        boolean change = true;
        while(end != start && change){
            change = false;
            while(aux != end){
                if(aux.getInfo() > aux.getNext().getInfo()){
                    auxInfo = aux.getInfo();
                    aux.setInfo(aux.getNext().getInfo());
                    aux.getNext().setInfo(auxInfo);
                    change = true;
                }
                aux = aux.getNext();
            }
            aux = start;
            end = end.getPrev();
        }
    }

    public void shake_sort(){
        Node start = this.start, end = this.end, aux;
        int auxInfo;
        boolean change = true;
        while (start != end && change){
            change = false;
            aux = start;
            while(aux != end){
                if(aux.getInfo() > aux.getNext().getInfo()){
                    auxInfo = aux.getNext().getInfo();
                    aux.getNext().setInfo(aux.getInfo());
                    aux.setInfo(auxInfo);
                    change = true;
                }
                aux = aux.getNext();
            }
            end = end.getPrev();
            if(change){
                change = false;
                aux = end;
                while(aux != start){
                    if(aux.getInfo() < aux.getPrev().getInfo()){
                        auxInfo = aux.getPrev().getInfo();
                        aux.getPrev().setInfo(aux.getInfo());
                        aux.setInfo(auxInfo);
                        change = true;
                    }
                    aux = aux.getPrev();
                }
                start = start.getNext();
            }
        }
    }

    public void counting_sort(){
        //find the major
        int major = 0, pos, j=0;
        for(Node i = start; i != null; i = i.getNext()){
            if(i.getInfo() > major){
                major = i.getInfo();
            }
        }

        int [] B = new int[major+1], C = new int[length()];

        for(Node i = start; i != null; i = i.getNext()){
            B[i.getInfo()] += 1;
        }

        for(int i=1; i<=major; i++){
            B[i] += B[i-1];
        }

        for(Node i = end; i != start; i = i.getPrev()){
            pos = B[i.getInfo()];
            B[i.getInfo()] -= 1;
            C[pos - 1] = i.getInfo();
        }

        Node aux = start;
        for(int i = 0; aux != null && i < length(); i++){
            aux.setInfo(C[i]);
            aux = aux.getNext();
        }
    }
    public void heap_sort(){
        int TL = length(), root, nodeL, nodeR, aux, majorNode;
        while(TL > 1){
            for(root = TL/2-1; root >= 0; root--){
                nodeL = 2 * root + 1;
                nodeR = nodeL + 1;
                majorNode = nodeR < TL && getByPos(nodeR).getInfo() > getByPos(nodeL).getInfo() ? nodeR : nodeL;
                if(getByPos(majorNode).getInfo() > getByPos(root).getInfo()){
                    aux = getByPos(majorNode).getInfo();
                    getByPos(majorNode).setInfo(getByPos(root).getInfo());
                    getByPos(root).setInfo(aux);
                }
            }
            aux = getByPos(0).getInfo();
            getByPos(0).setInfo(getByPos(TL-1).getInfo());
            getByPos(TL-1).setInfo(aux);
            TL--;
        }
    }

    public void bucket_sort(int n){
        int i, aux, index, min = min(), max = max();
        List [] buckets = new List[n];
        Node node, OLNode;
        //initialize buckets
        for(i=0; i<n; i++){
            buckets[i] = new List();
        }

        for(node = start; node != null; node = node.getNext()){
            index = (node.getInfo() - min) * (n-1) / (max - min);
            buckets[index].insertAtEnd(node.getInfo());
        }

        for(i=0; i<n; i++){
            if(buckets[i].getStart() != null){
                buckets[i].insertion_sort();
            }
        }

        OLNode = start;
        for(i = 0; i<n; i++){
            if(buckets[i] != null){
                node = buckets[i].getStart();
                while (node!=null){
                    OLNode.setInfo(node.getInfo());
                    OLNode = OLNode.getNext();
                    node = node.getNext();
                }
            }
        }
    }

    public void shell_sort(){
        int i, j, aux, dist = 1, length = length();

        while(dist < length){
            dist = dist * 3 + 1;
        }
        dist /= 3;

        while(dist > 0) {
            for(i = dist; i < length; i++){
                j = i;
                aux = getByPos(j).getInfo();
                while(j - dist >= 0 && getByPos(j-dist).getInfo() > getByPos(j).getInfo()){
                    getByPos(j).setInfo(getByPos(j-dist).getInfo());
                    j -= dist;
                }
                getByPos(i).setInfo(aux);
            }
            dist /= 3;
        }

    }


}
