package inner;

class Outer02{
	int a;
	static int b;
	static {
		b=20;
	}
	public Outer02() {
		a=10;
	}
	public void disp() {
		System.out.println("a= " + a + "b= " + b);
	}
	static class Inner02{ //안의 클래스만 바깥의 클래스 멤버 가져다 쓸 수 있음
		int c;
		public Inner02() {
			c=30;
		}
		public void disp_in() {
			//disp(); //static멤버가 아니라서 사용 불가!!
			System.out.println("b= " + b + "c= " + c); //클래스가 시작과 동시에 올라갔는데 a는 몰라서 오류남
		}
	}
}

public class Exam_02 {
	public static void main(String[]args) {
		Outer02 ot = new Outer02();
		
		Outer02.Inner02 oi = new Outer02.Inner02(); //Inner02객체 안만들어줘도 메모리에 있어서 가져와 쓸 수 있음
		oi.disp_in();
	}
}
