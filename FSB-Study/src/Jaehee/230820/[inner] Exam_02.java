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
	static class Inner02{ //���� Ŭ������ �ٱ��� Ŭ���� ��� ������ �� �� ����
		int c;
		public Inner02() {
			c=30;
		}
		public void disp_in() {
			//disp(); //static����� �ƴ϶� ��� �Ұ�!!
			System.out.println("b= " + b + "c= " + c); //Ŭ������ ���۰� ���ÿ� �ö󰬴µ� a�� ���� ������
		}
	}
}

public class Exam_02 {
	public static void main(String[]args) {
		Outer02 ot = new Outer02();
		
		Outer02.Inner02 oi = new Outer02.Inner02(); //Inner02��ü �ȸ�����൵ �޸𸮿� �־ ������ �� �� ����
		oi.disp_in();
	}
}
