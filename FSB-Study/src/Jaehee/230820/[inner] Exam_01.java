package inner;

class Outer01 { //Outer01.class
	//����ʵ�
	int a; //��ü�� �����ؾ� �޸𸮿� �ö�
	private static int b; //���۰� ���ÿ� �޸𸮿� �ö�
	Inner01 in = new Inner01(); //��
	static {
		b=20;
	}
	//������
	public Outer01() { //�����ڴ� ����ʵ��� �ʱⰪ�� �����ϱ� ���� ���� �޼ҵ��̴�.
		a=10;
	}
	//����޼ҵ�
	public void disp() { 
		System.out.println("a= " + a + "b= " + b);
	}
	//��øŬ����(���) - ����̴ϱ� ��ü�� ������ �Ŀ� ��Ÿ����
	class Inner01{ //Ŭ���� ���� Ŭ������ ��øŬ������� �θ���. //Outer01$Inner01.class
		//�ٱ� Ŭ���� ����� �����ٰ� ���� ����
		private int c; //����ʵ�
		public Inner01(){ //������
			c=30;
		}
		public void disp_in() { //����޼ҵ�
			System.out.println("a= " + a + "b= " + b + "c= "+ c);
		}
		class Inner01_in{} //Ŭ���� ���� Ŭ������ ��øŬ������� �θ���. //Outer01$Inner01.Inner01_inclass
	}
}

public class Exam_01 {
	public static void main(String[] args) {
		Outer01 ot = new Outer01(); 
		ot.in.disp_in(); //�� //ot�� �����ϰ� in�� �����Ͽ� �޼ҵ� ����
		/*
		Outer01 ot = new Outer01();
		//Outer01$Inner01.class
		Outer01.Inner01 oi = ot.new Inner01(); //ot�� �����Ͽ� Inner01��øŬ���� ��ü ����
		oi.disp_in();
		
		ot.a = 100;
		oi.disp_in();
	}
	*/
}
}
