public class MyCLL {
    public MyCLL() {
        MyNode head;
        MyNode tail;
        int size = 0;
    }

    private static int size;
    private static MyNode head;
    private static MyNode tail;

    public void add(int x){
        MyNode newNode = new MyNode(x);

        if(size == 0){
            newNode.setNextNode(newNode);
            head = newNode;
            size++;
            newNode.setVal(x);
        } else {
            newNode.setNextNode(head);
            size++;
        }
    }

    public void remove(){

    }

    public void remove(int x){

    }

}
