import test.*; //test패키지에서 Test01클래스를 가져온다는 뜻!

class A05 {
	private int a; //private는 클래스 내부에서만 접근 가능한 ★접근제한자★
	private int b;
	void A05(){
		a = 10;
		b = 20;
	}
	void disp() {
		System.out.println("a="+a+"b="+b);
	}
}


public class Exam_05 {
	public static void main(String[]args) {
		Test01 t1 = new Test01();
		//Test01은 import를 써서 test패키지에서 가져올 수 있다.
		
		/*
		A05 ap = new A05();
		ap.a = 100; //a로 접근하는 객체 ap
		ap.b = 200;
		ap.disp();
		*/
	}
}
