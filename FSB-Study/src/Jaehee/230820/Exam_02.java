class A02{
	final int a; //					final int a = 10;	<<< java는 선언과 동시에 값을 줄 수 있음 ①순위
	private static int b;
	static { //static멤버메소드
		System.out.println("static 구문 실행중......."); 
		//메모리를 한번 차지함!(프로그램 시작할때 딱 한 번 올라가서 프로그램 끝날때까지 남아있음)
	}
	{
		System.out.println("일반 구문 실행중.......");		//<<<일반구문 ②순위
		//일반 구문은 어떤 객체를 생성하던지 상관없이 앞서 실행해줌!
		//맨위가 아닌 일반구문에서 초기값인 a=10;를 주면 생성자실행중에는 초기값 더이상 주지못함
	}
	public A02() {
		System.out.println("A02() 생성자 실행중.......");	//<<<생성자 실행중 ③순위
		a = 10; 
		//만약에 final int a=10;이라고 선언과 동시에 값을 줬다면, 맨위에서 한번 줬으니 초기값을 더 이상 주지못함
	}
	public A02(int a) {
		System.out.println("A02(int a) 생성자 실행중.......");
		this.a = a; 
		//final때문에 객체를 생성하기 전에 꼭 한 번 초기값을 줘야함
		//만약에 final int a=10;이라고 선언과 동시에 값을 줬다면, 맨위에서 한번 줬으니 초기값을 더 이상 주지못함
	}
}
	//★final 초기값 순서 : ①선언과 동시에 -> ②일반구문 -> ③생성자실행중

public class Exam_02 {
	public static void main(String[] args) {
		A02 ap = new A02();		//A02()생성자 객체 생성
		A02 bp = new A02(100);	//A02(int a)생성자 객체 생성
	}
}
