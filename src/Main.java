public class Main {
  public static void main(String[] args) {
    int [] testArr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    int [] testArr2 = {5, 86, 124, 9, 5489, 3524, 7849, 356, 1245, 8756, 3501, 3512, 7878, 7979};
    int [] testArr3 = {12, 2, 5, 4, 13};

    //test(testArr1);
    test(testArr2);
    //test(testArr3);

  }
  
  public static void test(int[] testArr){
      MyCuckooHash myHash = new MyCuckooHash(testArr.length+2);
      
      for(int i = 0; i < testArr.length; i++){
        myHash.add(testArr[i]);
      }

      System.out.print("final result: ");
      myHash.printHash();
      myHash.printRehashCounter();
      myHash.printEvictionCounter();
      System.out.println("------------------------------------------------------------");

  }
}