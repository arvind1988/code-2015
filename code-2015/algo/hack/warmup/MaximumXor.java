package algo.hack.warmup;

public class MaximumXor {

	static int maxXor(int l, int r) {
		int max = -1;
		for (int i = l; i < r; i++) {
			for (int j = i + 1; j <= r; j++) {
				int current = i ^ j;
				if (current > max)
					max = i ^ j;
			}
		}
		return max;
	}
	public static void main(String[] args) {
		System.out.println(maxXor(10, 15));
	}

}
