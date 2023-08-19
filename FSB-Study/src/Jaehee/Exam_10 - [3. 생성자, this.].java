class A10{ //사용자정의자료형(구조체는 맨앞에만 쓸 수 있음)
	int a;
	int b;
	int c, d, e, f, g;
	//모든 멤버필드값에 this.가 JVM에 의해서 자동으로 붙음
	A10(){		this.a=10;	this.b=20;
				this.c=30;	this.d=40;	this.e=50;
				this.f=60;	this.g=70;
	}
	A10(int a){
		this(); //매개변수 없는 생성자를 호출(=a빼고 다 호출) - 생성자의 첫번째 출에만 사용 가능
		//객체 생성중에는 생성자this로 다른 생성자 호출 가능!
		this.a=a;}//멤버필드 a와 이름이 같다면, 멤버필드 변수명 앞에 this.를 붙이면 멤버필드를 가리킨다.
	//멤버필드와 이름이 같으면 this.를 JVM이 자동 생성 불가능
	A10(int x, int y){
		this(x); //매개변수 하나 있는 생성자 호출(=앞의 A10(int a){}내용 호출)
		this.b=y;}
	
	void disp() {
		System.out.println("a="+this.a+"b="+this.b);
	}
}
	
public class Exam_10 {
	public static void main(String[]args) {
		A10 ap = new A10();// A10()생성자로 ap객체를 생성한다
		ap.disp();
		A10 bp = new A10(100); //A10(int x)생성자를 bp객체로 생성한다
		bp.disp();
		A10 cp = new A10(100,200); //A10(int x, int y)생성자를 cp객체로 생성한다
		cp.disp();
	}
}
