package inner;

class Outer03{
	int a;
	static int b;
	static {
		b=20;
	}
	public Outer03() {
		a=10;
	}
	public void disp() { //�� �޼ҵ带 ������ ������øŬ���� 1��Inner03�� ����
		class Inner03{	//������øŬ���� : �ش����������� ���̰� ������⶧����
						//Ŭ�����̸��� �������� �����ϸ� �ε�����ȣ�� �Űܼ� ��Ÿ��
						//Outer03 $ 1 Inner03.class <<< ������øŬ����1���̶�¶�
			int c;
			public Inner03() {
				c=30;
			}
			public void disp_in() {
				System.out.println("a=" + a + "b= " + b + "c= " + c);
			}
		}
		Inner03 in = new Inner03();
		in.disp_in();
	}
	public void disp2() { //�� �޼ҵ带 ������ ������øŬ���� 2��Inner03�� ����
		class Inner03{	//������øŬ���� : �ش����������� ���̰� ������⶧����
						//Ŭ�����̸��� �������� �����ϸ� �ε�����ȣ�� �Űܼ� ��Ÿ��
						//Outer03 $ 2 Inner03.class <<< ������øŬ����2���̶�¶�
			int c;
			public Inner03() {
				c=30;
			}
			public void disp_in() {
				System.out.println("a=" + a + "b= " + b + "c= " + c);
			}
		}
		Inner03 in = new Inner03();
		in.disp_in();
	}
}

public class Exam_03 {
	public static void main(String[]args) {
		
}
}
