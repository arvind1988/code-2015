package algo.ds.binarytree;

public class Interval<K extends Comparable<K>> {

  // Why public keys?
  public final  K a;
  public final  K b;

  public Interval(K a, K b) {
    if (lessThan(b, a)) throw new RuntimeException("Illegal argument");
    this.a = a;
    this.b = b;
  }

  // Does x is in this interval?
  public boolean contains(K x) {
    return lessThan(this.a, x) && lessThan(x, this.b);
  }

  // Does this interval intersects other interval?
  public boolean intersect(Interval<K> other) {
    if (lessThan(this.b, other.a)) return false;
    if (lessThan(other.b, this.a)) return false;
    return true;
  }

  // Is x less than y?
  public boolean lessThan(K x, K y) {
    return x.compareTo(y) <= 0;
  }

  public boolean equals(Interval<K> other) {
    return this.a.equals(other.a) && this.b.equals(other.b);
  }

  @Override
  public String toString(){
    return "[" + this.a + ", " + this.b + "]";
  }
  public static void main(String[] args) {
    
  }

}
