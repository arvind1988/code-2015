package algo.arrayp;

public class DoubleStack {

    int   top1;
    int   top2;
    int[] S;
    int   maxBufferSize;

    public DoubleStack(int maxBufferSize) {
      this.top1 = -1;
      this.top2 = maxBufferSize;
      this.maxBufferSize = maxBufferSize;
      this.S = new int[maxBufferSize];
    }

    public void pushIntoLeftStack(int item) {
      if (isLeftStackFull()) return;
      S[++top1] = item;
    }

    public void pushIntoRightStack(int item) {
      if (isRightStackFull()) return;
      S[--top2] = item;
    }

    public boolean isLeftStackFull() {
      return (top1 == top2 - 1);
    }

    public boolean isRightStackFull() {
      return (top2 == top1 + 1);
    }

    public int popLeft() {
      if (isLeftStackEmpty()) return -1;
      return S[top1--];
    }

    public int popRight() {
      if (isRightStackEmpty()) return -1;
      return S[top2++];
    }

    public boolean isLeftStackEmpty() {
      return (top1 == -1);
    }

    public boolean isRightStackEmpty() {
      return (top2 == maxBufferSize);
    }

  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
