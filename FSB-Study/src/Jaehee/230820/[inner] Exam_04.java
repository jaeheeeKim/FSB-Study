package inner;

class Outer04{
	public void aaa() {
		System.out.println("aaa 메소드입니다.");
	}
	public void bbb() {
		System.out.println("ddd 메소드입니다.");
}
	public void ccc() {
		System.out.println("ccc 메소드입니다.");
}
}

public class Exam_04 {
	public static void main(String[]args) {
		Outer04 ot = new Outer04() { //보이지않는 일회용클래스(익명중첩클래스)가 생성되어 ot객체를 생성
			public void bbb() {
				System.out.println("ddd 메소드입니다.");
			}
		};
		
		ot.aaa();
		ot.bbb();
		ot.ccc();
		System.out.println("=======");
		Outer04 ot2 = new Outer04();
		ot2.bbb();
	}
}

