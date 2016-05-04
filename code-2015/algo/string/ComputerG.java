package algo.string;

public class ComputerG {

  /**
   * Function to find Number of customers who could not get a computer Write a
   * function “runCustomerSimulation” that takes following two inputs a) An
   * integer ‘n': total number of computers in a cafe and a string: b) A
   * sequence of uppercase letters ‘seq': Letters in the sequence occur in
   * pairs. The first occurrence indicates the arrival of a customer; the second
   * indicates the departure of that same customer.
   * 
   * A customer will be serviced if there is an unoccupied computer. No letter
   * will occur more than two times. Customers who leave without using a
   * computer always depart before customers who are currently using the
   * computers. There are at most 20 computers per cafe.
   * 
   * For each set of input the function should output a number telling how many
   * customers, if any walked away without using a computer. Return 0 if all the
   * customers were able to use a computer.
   * 
   * runCustomerSimulation (2, “ABBAJJKZKZ”) should return 0
   * 
   * runCustomerSimulation (3, “GACCBDDBAGEE”) should return 1 as ‘D’ was not
   * able to get any computer
   * 
   * runCustomerSimulation (3, “GACCBGDDBAEE”) should return 0
   * 
   * runCustomerSimulation (1, “ABCBCA”) should return 2 as ‘B’ and ‘C’ were not
   * able to get any computer.
   * 
   * runCustomerSimulation(1, “ABCBCADEED”) should return 3 as ‘B’, ‘C’ and ‘E’
   * were not able to get any computer
   */
  public static int howManyWithoutComputer(int n, String sequence) {

    // Assuming only character set of 256 chars
    boolean[] using = new boolean[256];
    
    // On  start all the computers are vacant
    int vacant = n;
    
    // Counter to track how many person have not used sofar
    int notUsed = 0;
    
    for (int i = 0; i < sequence.length(); i++) {
      if (!using[sequence.charAt(i)]) {
        if (vacant > 0) { // If ith guy has not used and there is vacant computer
          using[sequence.charAt(i)] = true;
          vacant--;
        } else notUsed++; // here ith guy will not be able to use the computer
      } else{
        vacant++; // If ith departs and hence creates vacant position
        using[sequence.charAt(i)] = false;
      }
    }
    // Since each guy which has not used the computer appears twice in the
    // input sequence (arrival and depart) we divide the result by two.
    return notUsed/2;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
