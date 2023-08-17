import java.util.*;
import java.io.*;

public class Exam_01 {
	public static void main(String[] args) throws IOException{ //read때문에 작성
		
		//사칙연산자를 입력하여 두 수를 계산하는 프로그램
		
		int su1 = input();
		int su2 = input();
		
		
		//사칙연산 입력
		System.out.print("사칙 연산자를 입력 : ");
		char op = (char)System.in.read(); //read는 한개만 읽어오겠다는 뜻
		//짝꿍 throws IOException < import java.io.*;
		int res = 0;

		//사칙연산 (+-*/)
		switch(op) {
		case '+' : res = sum(su1, su2); break;
		case '-' : res = min(su1, su2); break;
		case '*' : res = mul(su1, su2); break;
		case '/' : res = div(su1, su2); break;
		default :
			System.out.println("잘못입력하셨습니다.");
			System.exit(0); //switch~case문에서 나감
		}
		System.out.printf("%d %c %d = %d\n", su1, op, su2, res); //d는 정수, f는 실수, c는 문자, s는 문자열?????
}
		public static int sum(int a, int b) {
			return a+b;
		}
		public static int mul(int a, int b) {
			return a*b;
		}
		public static int min(int a, int b) {
			if(a>b) return a-b;
			return b-a; //else의 경우
		}
		public static int div(int a, int b)	{
			if(a==0) return b; //b는 res가 됨
			//if(a==0) {a=1; return b/a;}랑 같음
			if(b==0) return a; //a는 res가 됨
			if(abs(a) > abs(b)) return a/b; //절대값 큰 수를 작은수로 나누기
			else return b/a;
		}
		//절대값 구하는 메소드(핵심!!!)
		public static int abs(int a) { //절대값(abs) 구하는메소드
			if (a<0) return a*-1;
			return a;
		}
		
		//입력 받을 값
		public static int input() {
			Scanner in = new Scanner(System.in);
			System.out.print("수를 입력 : ");
			int su = in.nextInt();
			return su;
		}
	}

