package inner;

class Outer04{
	public void aaa() {
		System.out.println("aaa �޼ҵ��Դϴ�.");
	}
	public void bbb() {
		System.out.println("ddd �޼ҵ��Դϴ�.");
}
	public void ccc() {
		System.out.println("ccc �޼ҵ��Դϴ�.");
}
}

public class Exam_04 {
	public static void main(String[]args) {
		Outer04 ot = new Outer04() { //�������ʴ� ��ȸ��Ŭ����(�͸���øŬ����)�� �����Ǿ� ot��ü�� ����
			public void bbb() {
				System.out.println("ddd �޼ҵ��Դϴ�.");
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

