package tristan;

import java.util.Arrays;
import java.util.Collections;

public class TestIndex {
	/** * @param args */
	public static void main(String[] args) {
		int n[] = { 2, 3, 5, 4, 1, 6, 8, 7, 9, 0 };
		int t[] = new int[10];
		System.arraycopy(n, 0, t, 0, 10);
		Arrays.sort(n);
		
		System.out.println(Arrays.toString(n));
		for (int i = n.length-1; i >= 0; i--) {
			for (int j = 0; j < t.length; j++) {
				if (n[i] == t[j]) {
					System.out.println(n[i] + "的原下标为:" + j);
				}
			}
		}
	}
}