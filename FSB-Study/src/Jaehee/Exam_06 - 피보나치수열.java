import java.util.*;
public class Exam_06 {
 public static void main(String[] args) {
	 
	 //피보나치 수열(토끼 번식력)
	 //첫달은 자기자신만, 1 1 2 3 5 8 13 21 34 55 89 144
	 //n = 2 이면 1쌍
	 Scanner in = new Scanner(System.in);
	 
	 System.out.print("피보나치 수열의 위치를 입력 : ");
	 int po = in.nextInt();
	 
	 for(int i = 1; i<po; ++i) { //10을 입력한다면 9번 반복하여 출력하고
	 System.out.print(fibo(i) + ",");
	 }
	 System.out.println(fibo(po)); //마지막 값 10번째로 출력

 }
 	public static int fibo(int n) {
 		if(n<=2) return 1; //수열 1부터 "1, 1"
 		return fibo(n-2) + fibo(n-1);
 	} //1 1 2 3=fibo(1)+fibo(2) 5=fibo(2)+fibo(3).......
}

