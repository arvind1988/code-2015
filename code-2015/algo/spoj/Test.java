package algo.spoj;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

  public static void main(String[] args) {
    testLife();

  }
  
  public static void testLife(){
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> list = new ArrayList<>();
    while(sc.hasNextInt()){
      int temp = sc.nextInt();
      if(temp == 42) break;
      list.add(temp);
    }
    sc.close();
    for(int i : list)
      System.out.println(i);
  }

}
