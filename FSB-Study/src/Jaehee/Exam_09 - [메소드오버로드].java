
public class Exam_09 {
	public static void main(String[] args) {
		star();
		System.out.println("aaaaaaaaaa");
		star();
		System.out.println("bbbbbbbbbb");
		star();
		star(4.5); //메소드 오버로드(매개변수를 받는 자료형이 맞지 않다면 에러남
		star(6); //메소드 오버로드(매개변수를 하나 받는 메소드가 없다면 에러남
		star();
		System.out.println("cccccccccc");
		star();
	}
	public static void star(double a) { //이름만 보고 호출하는게 아니라 매개변수가 다르면 다른 메소드로 인정하겠다.
		for(int i = 1; i<=a; ++i) {
			System.out.print("*");
		}
		System.out.println();
	}
	public static void star(int a) { //이름만 보고 호출하는게 아니라 매개변수가 다르면 다른 메소드로 인정하겠다.
		for(int i = 1; i<=a; ++i) {
			System.out.print("*");
		}
		System.out.println();
	}
	
	public static void star() {
		System.out.println("**********");
	}
}
