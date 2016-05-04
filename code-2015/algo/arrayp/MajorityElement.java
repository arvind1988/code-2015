package algo.arrayp;

public class MajorityElement {
  /**
   * <b>Majority Element:</b> A majority element in an array A[] of size n is an
   * element that appears more than n/2 times (and hence there is at most one
   * such element)
   * 
   * @param elements
   * @return
   */
  public static int majorityElement(int[] elements) {
    if (elements == null) return -1;
    int count = 1;
    int candidate = elements[0];
    for (int i = 1; i < elements.length; i++) {
      if (count == 0) {
        candidate = elements[i];
        count = 1;
      } else {
        if (elements[i] == candidate) count++;
        else count--;
      }
    }
    count = 0;
    for (int element : elements) {
      if (element == candidate) count++;
    }
    if (count > elements.length / 2) return candidate;
    return -1;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
