public class Main {
  public static void main(String[] args) {
    int [] testArr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    int [] testArr2 = {5, 86, 124, 9, 5489, 3524, 7849, 356, 1245, 8756, 3501, 3512, 7878, 7979};
    int [] testArr3 = {12, 2, 5, 4, 13};

    boolean test1Results = test(testArr1);
    boolean test2Results = test(testArr2);
    boolean test3Results = test(testArr3);

    if(test1Results && test2Results && test3Results){
      System.out.println("all tests successful");
    }else {
      if (test1Results) {
        System.out.println("test 1 successful.");
      } else{
        System.out.println("test 1 unsuccessful.");
      }
      if (test2Results) {
        System.out.println("test 2 successful.");
      } else{
        System.out.println("test 2 unsuccessful.");
      }
      if (test3Results) {
        System.out.println("test 3 successful.");
      } else{
        System.out.println("test 3 unsuccessful.");
      }
    }
  }
  
  public static boolean test(int[] testArr){
    MyCuckooHash myHash = new MyCuckooHash(testArr.length+2);
    boolean allGood = true;

    for(int i = 0; i < testArr.length; i++){
      myHash.add(testArr[i]);
    }
    System.out.print("final result: ");
    myHash.printHash();

    for(int i = 0; i < testArr.length; i++){ //this checks that each key is found in the hashmap
      int k = myHash.fetch(testArr[i]);
      //System.out.println(testArr[i] + " = " + k); debugging, verifying that keys are found at addresses that they should be hashed to
      if(testArr[i] != k)
        allGood = false;
    }

    myHash.printRehashCounter();
    myHash.printEvictionCounter();
    System.out.println("------------------------------------------------------------");

    return allGood;
  }
}