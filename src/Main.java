public class Main {
    public static void main(String[] args) {
        int answer;
        int size = 5;
        int interval = 3;

        answer = josephusProblem(5, 2);
        System.out.println("If there are " + size + " people and they are killed at an interval of "+ interval + ", then you should be person #" + answer + " if you want to survive.");
    }

    public static int josephusProblem(int size, int interval){

        MyCLL list = new MyCLL();
        for(int i = 0; i< size; i++){
            list.add(i+1);
        }


        System.out.println("this is my head: " + list.head + ", " + list.head.getVal());
        System.out.println("this is my tail: " + list.tail + ", " + list.tail.getVal() + ". it points to " + list.tail.nextNode + " with val of " + list.tail.nextNode.getVal());

        System.out.println();


        System.out.print("here's the values of my list: ");
        list.printList();
        System.out.println();
        System.out.println("my list size is " + size);
        System.out.println();

        System.out.println("about to remove at interval " + interval);
        do{
            list.remove(interval);
            list.printList();
        }
        while (list.getSize() > 1);

        System.out.println("list size is: "+ list.getSize());
        int answer = list.head.getVal();

        return(list.head.getVal());
    }
}