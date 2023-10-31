public class MyCLL {
    public MyCLL() {
        MyNode meta = new MyNode(0);
        meta.setVal(0);

    }

    // non-static variables

    private int size;
    public MyNode meta;
    public MyNode head;
    public MyNode tail;
    private MyNode currentNode = tail;


    // methods
    public void add(int x){
        MyNode newNode = new MyNode(x);

        if(size == 0){
            newNode.setNextNode(newNode);
            newNode.setVal(x);
            head = newNode;
            tail = newNode;
            currentNode = newNode;
        } else {
            newNode.setNextNode(head);
            tail.setNextNode(newNode);
            tail = newNode;
            currentNode = tail;
        }
        incrementSize();
    }

    private void removeNext(){
        MyNode nextNode = currentNode.getNextNode();
        System.out.println("this is current node and val: " + currentNode + ", " + currentNode.getVal());
        System.out.println("this is next node and val: " + nextNode + ", " + nextNode.getVal());

        System.out.println("about to remove " + currentNode.getNextNode().getVal());
        System.out.println("about to remove " + nextNode.getVal());
        currentNode.setNextNode(currentNode.getNextNode().getNextNode());
        decrementSize();
    }

    public void remove(int x){
        removeNext();
        for(int i = 0; i < x-1; i++){
            currentNode = currentNode.getNextNode();
        }
    }

    public int getSize(){
        return size;
    }

    public void incrementSize(){
        size = size + 1;
    }

    public void decrementSize(){
        size = size - 1;
    }

    public void printList(){
        MyNode node = head;
        for(int i = 0; i < size; i++) {
            System.out.print(node.getVal() + ", ");
            node = node.getNextNode();
        }
        System.out.println();
    }
}
