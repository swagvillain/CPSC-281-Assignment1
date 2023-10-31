public class MyNode {

    // constructors
    public MyNode(int val, MyNode next) {

    }

    public MyNode(int val){
        this.val = val;

    }

    //static variables
    private MyNode nextNode;
    private int val;

    //getters and setters
    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public MyNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(MyNode nextNode) {
        this.nextNode = nextNode;
    }


}
