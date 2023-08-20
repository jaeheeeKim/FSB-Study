//홈페이지에서 로그인할때

class A03{
	public static final int OK = 0; //static final: 상수선언
	//모두 접근하게 해줌/객체생성전에도갖다쓰게해줌/초기값한번정의해주면끝
	public static final int NOT_ID = 1;
	public static final int NOT_PW = 2;
	public final static int ERROR = -1;
	//상수는 전부 대문자로 쓴다!!!
	int a;
	static int b;
	public static void disp() {
		//int c = a + b;
	}
}

public class Exam_03 {
	int a = 10;
	static int b = 20;
	//public Exam_03() {}
	public static void main(String[] args) {
		//int c = a + b;
		Exam_03 a = new Exam_03();
		//int c = a.a + Exam_03.b;
	}
}
//int c = a.a + Exam_03.b;
//a에 접근하자
//static이니까 클래스이름으로 접근하자