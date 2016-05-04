package algo.string;

public class LengthOfStringWithoutLengthMethod {

  public static int length(String s){
    if(s == null || s == "") return 0;
    int count = 0;
    while(true){
      try{
        char c = s.charAt(count);
        count++;
      }catch(Exception e){
        return count;
      }
    }
  }
  public static void main(String[] args) {
    String a = "Arvind";
    System.out.println(length(a));
  }

}
