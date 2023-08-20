package inner;

class Outer01 { //Outer01.class
	//멤버필드
	int a; //객체를 생성해야 메모리에 올라감
	private static int b; //시작과 동시에 메모리에 올라감
	Inner01 in = new Inner01(); //①
	static {
		b=20;
	}
	//생성자
	public Outer01() { //생성자는 멤버필드의 초기값을 정의하기 위해 만든 메소드이다.
		a=10;
	}
	//멤버메소드
	public void disp() { 
		System.out.println("a= " + a + "b= " + b);
	}
	//중첩클래스(멤버) - 멤버이니까 객체를 생성한 후에 나타나짐
	class Inner01{ //클래스 안의 클래스를 중첩클래스라고 부른다. //Outer01$Inner01.class
		//바깥 클래스 멤버를 가져다가 쓰기 위해
		private int c; //멤버필드
		public Inner01(){ //생성자
			c=30;
		}
		public void disp_in() { //멤버메소드
			System.out.println("a= " + a + "b= " + b + "c= "+ c);
		}
		class Inner01_in{} //클래스 안의 클래스를 중첩클래스라고 부른다. //Outer01$Inner01.Inner01_inclass
	}
}

public class Exam_01 {
	public static void main(String[] args) {
		Outer01 ot = new Outer01(); 
		ot.in.disp_in(); //② //ot에 접근하고 in에 접근하여 메소드 실행
		/*
		Outer01 ot = new Outer01();
		//Outer01$Inner01.class
		Outer01.Inner01 oi = ot.new Inner01(); //ot에 접근하여 Inner01중첩클래스 객체 생성
		oi.disp_in();
		
		ot.a = 100;
		oi.disp_in();
	}
	*/
}
}
