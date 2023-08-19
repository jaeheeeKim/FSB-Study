import java.util.Scanner;

public class Exam_01 {
	public static void main(String[] args) {
		
		/*
		매우 큰 수의 뺄셈(자릿수 생각해야함) 
		첫번째 수 입력 : 1234560123456 <<<정수로 받으면 안되고 문자열로 받아야함
		두번째 수 입력 : 1
		첫번째 수 : 1234560123456
		두번째 수 : 0000000000001
		결과 : 1234560123455
		*/
		
		//문자열로 출력
		String su1 = input("첫번째");
		String su2 = input("두번째");
		
		//
		if (su1.length() > su2.length()) su2 = po(su1, su2); //po();메소드
		else su1 = po(su2, su1);
		
		int po = 0; //앞자리에서 값을 빌려오면 1, 아니면 0
		
		String res = ""; //결과값(res)의 초기값은 공백(0)
		
		for(int i=su1.length()-1; i>=0; --i) {
			char f1 = (char)(su1.charAt(i) - po); //뺄때 앞자리에서 값을 가져와야하기때문에 <<< 예시)100-1=099
			char f2 = su2.charAt(i);
			if (f1<f2) {
				po = 1; //앞자리에서 값을 빌려오면 1, 아니면 0
				f1 += 10;
				if (i==0 && po==1) {
					//음수
				}
			}else {
				po = 0;
			}
			res = (f1-f2) + res; //결과값(res) 앞에 (f1-f2) 갖다붙이기
			// f1과 f2는 char형이기때문에 아스키코드로 값이 나옴
		}
		System.out.println("첫번째수 : " + su1);
		System.out.println("두번째수 : "+ su2);
		System.out.println("결과       : " + res);
	}
	
	public static String po(String big, String small) {
		String str = small;
		for(int i=0; i<big.length()-small.length(); ++i) {
			str = "0" + str; //작은수(su2) 앞자리를 공백으로 채움
		}
		return str;
	}
	
	public static String input(String str) {
		Scanner in = new Scanner(System.in);
		System.out.print(str+" 수를 입력 : ");
		String su = in.next();
		return su;
	}
}

