//Ȩ���������� �α����Ҷ�

class A03{
	public static final int OK = 0; //static final: �������
	//��� �����ϰ� ����/��ü�������������پ�������/�ʱⰪ�ѹ��������ָ鳡
	public static final int NOT_ID = 1;
	public static final int NOT_PW = 2;
	public final static int ERROR = -1;
	//����� ���� �빮�ڷ� ����!!!
	int a;
	static int b;
	public static void disp() {
		//int c = a + b;
	}
}

public class Exam_03 {
	int a = 10;
	static int b = 20;
	//public Exam_03() {}
	public static void main(String[] args) {
		//int c = a + b;
		Exam_03 a = new Exam_03();
		//int c = a.a + Exam_03.b;
	}
}
//int c = a.a + Exam_03.b;
//a�� ��������
//static�̴ϱ� Ŭ�����̸����� ��������