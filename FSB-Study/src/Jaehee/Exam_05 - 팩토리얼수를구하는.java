import java.util.Scanner;
public class Exam_05 {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		//팩토리얼 수를 구하는 프로그램
		System.out.print("팩토리얼 수를 입력 : ");
		int su = in.nextInt();
		
		int res = fac(su);
		
		System.out.printf("팩토리얼 수 %d의 결과는 %d입니다.", su, res);
	}
	public static int fac(int n) {
		if(n==1) return 1;
		return n * fac(n-1);
	}
}

/* su = 5일때
fac(5) = 5 * fac(4)
fac(4) = 4 * fac(3)
.
.
.
fac(1) = 1 //거꾸로 계산식 작성
*/
