import java.io.*;

class A06{
	int a;
	int b;
	public A06() { //부모클래스의 생성자
		a=10;
		b=20;
	}
	public A06(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public void disp() throws IOException{ //예외전가
		//disp()메소드를 실행하는데 IOException이 발생할 수 있습니다. 만약에 발생이 되면
		//disp()메소드를 호출한 곳에서 처리하겠습니다.
		System.out.println("좌표 : ["+a+","+b+"]");
	}
}

class B06 extends A06{ //a, b, c=30
	int c;
	int b;
	public B06() {
		//super(100, 200); //부모클래스의 생성자를 호출, super() - 부모클래스의 디폴트생성자
		//= A06클래스의 A06()생성자를 호출
		c=30;
		b=100;
	}
	@Override //어노테이션(@:길잡이) //반드시 써야하는건 아니지만 써주면 프로그램에 도움이 됨
	public void disp() throws IOException{ //예외전가(부모에 없는 예외전가를 자식은 만들 수 없다.)
		//disp()메소드를 실행하는데 IOException이 발생할 수 있습니다. 만약에 발생이 되면
		//disp()메소드를 호출한 곳에서 처리하겠습니다.
		System.out.println("좌표 : ["+a+","+super.b+","+c+"]"); 
						//super.b => 생략되어있는 this로 인해 B06클래스에 있는 b값(100)을 가져오기때문에 부모클래스에 접근하여 가져오기!
						//super가 있을때는 20 출력, 없을때는 100(자기자신) 출력
	}
}
public class Exam_06 {
	public static void main(String[] args) throws IOException{ //예외처리 안하겠다고 눈속임하는 것
		//부모이름과 자식이름을 같게 만드는 것 : 오버라이드
		B06 ap = new B06();
		ap.disp(); //호출된 곳(main)에서 처리해줘야함
		
		/*
		A06 ap = new A06();
		ap.disp();
		B06 bp = new B06();
		bp.disp2();
		*/
	}
}
