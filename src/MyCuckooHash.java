public class MyCuckooHash {

  // Constructor(s)
  public MyCuckooHash(int size) {
    arr = new int[size];
  }

  // non-static variables
  private int i = 0;
  private int decimalIndex1 = 0;
  private int decimalIndex2 = 1;
  private double currentDecimal1 = decimalsArr[decimalIndex1];
  private double currentDecimal2 = decimalsArr[decimalIndex2];
  private int[] arr;
  private static double[] decimalsArr = { 0.3332, 0.4589, 0.121212, 0.9845, 0.4654, 0.08, 0.9, 0.8742, 
    0.66, 0.25 };
  private int evictionStart;
  private int rehashCounter = 0; //tracks how many times hash functions are renewed
  private int evictionCounter= 0; //tracks how many times evictions occur

  // functions below

  // prints number of times hash functions have been renewed
  public void printRehashCounter(){
    System.out.println("Number of rehashes: " + rehashCounter);
  }

  // prints number of times evictions have occured
  public void printEvictionCounter(){
        System.out.println("Number of evictions: " + evictionCounter);
  }

  //adds key to the hashtable
  public void add(int x) {
    int address1 = hashFn1(x); // hash the key
    int address2 = hashFn2(x); // hash the key again

    System.out.println("key to be inserted is " + x + ". Its options are index " + address1 + " and index " + address2 + ".");

    if (arr[address1] == 0) { // if first address is empty, the key will be assigned there
      arr[address1] = x;
      System.out.println("inserting at "+ address1);
    } else if (arr[address2] == 0) { // if first option is full, second is used if free
      arr[address2] = x;
      System.out.println(address1 + " is taken. inserting at " + address2);
    } else { // if both are full, we evict the first and take its place.
      System.out.println("both are full. evicting " + arr[address1] + " from " + address1);
      evictionStart = x; // noting where we are when we begin evictions, to prevent infinite loop
      boolean reHashed = evict(address1, x); // evicting value at first address.
      if(reHashed) // if eviction above resulted in a loop, and we rehashed the table after refreshing the functions,
        arr[address1] = x; // then we need to remember to assign this value where it belongs.
    }
    System.out.println("Result:");
    printHash();
  }
  /**
   * @param address,
   * @param x,
   * @return boolean, if eviction resulted in a rehashing
   */
  private boolean evict(int address, int x) {
    boolean reHashed = false;
    evictionCounter++;
    int y = arr[address];
    if(y == evictionStart) { //checking if this is a return to a beginning of an infinite loop
      reHash(x); //if is a loop, then get new hash functions and reset.
      reHashed = true;
    } else {
      arr[address] = x;
      printHash();
      reHome(address, y);
    }
    return reHashed;
  }

  private void reHome(int oldAddress, int y) {
    int newAddress;

    if (hashFn1(y) == oldAddress) { //determining which of the 2 hashfunctions to use
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
      return hashFn1(x);
    } else if (arr[hashFn2(x)] == x) {
        return hashFn2(x);
    }
      return 4;
  }

  private boolean isOccupied(int x) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == x) {
        return true;
      }
    }
    return false;
  }

  public void remove(int x) {
    int address = getAddress(x);
    arr[address] = 0;
  }

  public int fetch (int x){
    int address = getAddress(x);
    return arr[address];
  }

  private void reHash(int x){
    rehashCounter++;

    // this was to help with debugging
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
    add(x);
    System.out.println("done rehash, resuming where we were. results of rehash: ");
    printHash();
  }
  
  private void refreshHashFx() { // systematically cycles through decimal coefficients used in hash functions
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

  // functions used to hash keys
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