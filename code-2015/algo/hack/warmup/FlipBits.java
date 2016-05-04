package algo.hack.warmup;

import java.math.BigInteger;
import java.util.Scanner;

public class FlipBits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        BigInteger[] flipBits = new BigInteger[N];
        int i = 0;
        while (i < N) {
            if (sc.hasNext()) flipBits[i++] = sc.nextBigInteger();
        }
        sc.close();
        FlipBits fb = new FlipBits();
        fb.flipBits(flipBits);
    }

    public void flipBits(BigInteger[] A) {
        BigInteger mask = new BigInteger("4294967296");
        for (BigInteger i : A) {
            System.out.println(i.not().add(mask));
        }
    }
}
