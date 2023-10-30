public class MyCuckooHash {

  // Constructor(s)
  public MyCuckooHash(int size) {
    arr = new int[size];
  }

  // non-static variables
  int i = 0;
  int decimalIndex1 = 0;
  int decimalIndex2 = 1;
  double currentDecimal1 = decimalsArr[decimalIndex1];
  double currentDecimal2 = decimalsArr[decimalIndex2];
  int[] arr;
  private static double[] decimalsArr = { 0.3332, 0.4589, 0.121212, 0.9845, 0.4654, 0.08, 0.9, 0.8742, 
    0.66, 0.25 };
  int evictionStart;
  int rehashCounter = 0;
  int evictionCounter= 0;

  // functions here

  public void printRehashCounter(){
    System.out.println("Number of rehashes: " + rehashCounter);
  }

  public void printEvictionCounter(){
        System.out.println("Number of evictions: " + evictionCounter);

  }

  public void add(int x) {
    int address1 = hashFn1(x); // hash the key
    int address2 = hashFn2(x); // hash the key again

    // legacy debugging help
    //printHash();
    System.out.println("key to be inserted is " + x + ". Its options are index " + address1 + " and index " + address2 + ".");

    if (arr[address1] == 0) {
      arr[address1] = x;
      System.out.println("inserting at "+ address1);
    } else if (arr[address2] == 0) {
      arr[address2] = x;
      System.out.println(address1 + " is taken. inserting at " + address2);
    } else {
      System.out.println("both are full. evicting " + arr[address1] + " from " + address1);
      evictionStart = x;
      evict(address1, x);
      arr[address1] = x;
    }      
    System.out.println("Result:");
    printHash();
  }

  private void evict(int address, int x) {
    evictionCounter++;
    int y = arr[address];
    if(y == evictionStart) {
      reHash(); //then what
    } else {
      arr[address] = x;
      // need to insert y where it belongs, but NOT at address 1!
      printHash();
      reHome(address, y);
    }
  }

  private void reHome(int oldAddress, int y) {
    int newAddress;

    if (hashFn1(y) == oldAddress) {
      newAddress = hashFn2(y);
    } else {
      newAddress = hashFn1(y);
    }
    System.out.println("about to rehome " + y + " in index " + newAddress);

    if(arr[newAddress] == 0){
      arr[newAddress] = y;
    } else {
      evict(newAddress, y);
    }

  }

  public void printHash() {
    System.out.print("[" + arr[0] + ", ");
    for (int i = 1; i < arr.length - 1; i++) {
      System.out.print(arr[i] + ", ");
      
    }
    System.out.println(arr[arr.length - 1] + "]\n");
  }

  /**
   * @param x
   * @return address of given key. If key not found, -1 is returned.
   */
  public int getAddress(int x) {
    if(arr[hashFn1(x)] == x){
      return x;
    }
      else if (arr[hashFn2(x)] == x){
        return x;
      }
      return -1;
  }

  public boolean isOccupied(int x) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == x) {
        return true;
      }
    }
    return false;
  }

  public void remove() {

  }

  private void reHash(){
    rehashCounter++;

    System.out.println("reHash called. About to rehash! Old decimals are: " + currentDecimal1 + " at " + decimalIndex1 + ", and " + currentDecimal2 + " at " + decimalIndex2);
    refreshHashFx();
    System.out.println("New decimals are: " + currentDecimal1 + " at " + decimalIndex1 + ", and " + currentDecimal2 + " at " + decimalIndex2);

    int[] oldArr = arr;
    arr = new int[oldArr.length];

    for(int i = 0; i < oldArr.length; i++){
      if(oldArr[i]!=0){
        add(oldArr[i]);
      }
    }
    System.out.println("done rehash, resuming where we were. results of rehash: ");
    printHash();
  }
  
  private void refreshHashFx() {
    System.out.println("refreshing hash functions");
    if (decimalIndex1 == 6)
      decimalIndex1++;
    if(decimalIndex1 == decimalIndex2)
      decimalIndex1 ++;
    decimalIndex1++;
    decimalIndex2++;

    decimalIndex1 = decimalIndex1%10;
    decimalIndex2 = decimalIndex2%10;    

    currentDecimal1 = decimalsArr[decimalIndex1];
    currentDecimal2 = decimalsArr[decimalIndex2];
    // get new hashing functions

  }

  private int hashFn1(int x) {
    double address1;
    address1 = Math.floor((((currentDecimal1) * x)%1)*arr.length);
    return (int) address1;
  }

  private int hashFn2(int x) {
    double address2;
    address2 = Math.floor(((currentDecimal2 * x * 0.6)%1)*arr.length);
    return (int) address2;
  }
}