import java.util.*;

public class Exam_04 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		/*2의 n승을 입력 받아 값을 출력 시켜주는 프로그램
		n = 4
		2^4 = 2 * 2^3
		2^3 = 2 * 2^2
		2^2 = 2 * 2^1
		2^1 = 2 * 2^0
		*/
		System.out.print("n = " );
		int n = in.nextInt();

		long res = ex(n); //큰수를 n으로 입력했을때 나오려면 int보다 큰 long 이어야함

		System.out.printf("2의 %d승은 %d이다.\n", n, res);
		}

		public static long ex(int su) {
		if(su==0) return 1;
		return 2 * ex(su-1);
		}
}