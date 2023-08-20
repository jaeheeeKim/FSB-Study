import java.io.*;

class A06{
	int a;
	int b;
	public A06() { //�θ�Ŭ������ ������
		a=10;
		b=20;
	}
	public A06(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public void disp() throws IOException{ //��������
		//disp()�޼ҵ带 �����ϴµ� IOException�� �߻��� �� �ֽ��ϴ�. ���࿡ �߻��� �Ǹ�
		//disp()�޼ҵ带 ȣ���� ������ ó���ϰڽ��ϴ�.
		System.out.println("��ǥ : ["+a+","+b+"]");
	}
}

class B06 extends A06{ //a, b, c=30
	int c;
	int b;
	public B06() {
		//super(100, 200); //�θ�Ŭ������ �����ڸ� ȣ��, super() - �θ�Ŭ������ ����Ʈ������
		//= A06Ŭ������ A06()�����ڸ� ȣ��
		c=30;
		b=100;
	}
	@Override //������̼�(@:������) //�ݵ�� ����ϴ°� �ƴ����� ���ָ� ���α׷��� ������ ��
	public void disp() throws IOException{ //��������(�θ� ���� ���������� �ڽ��� ���� �� ����.)
		//disp()�޼ҵ带 �����ϴµ� IOException�� �߻��� �� �ֽ��ϴ�. ���࿡ �߻��� �Ǹ�
		//disp()�޼ҵ带 ȣ���� ������ ó���ϰڽ��ϴ�.
		System.out.println("��ǥ : ["+a+","+super.b+","+c+"]"); 
						//super.b => �����Ǿ��ִ� this�� ���� B06Ŭ������ �ִ� b��(100)�� �������⶧���� �θ�Ŭ������ �����Ͽ� ��������!
						//super�� �������� 20 ���, �������� 100(�ڱ��ڽ�) ���
	}
}
public class Exam_06 {
	public static void main(String[] args) throws IOException{ //����ó�� ���ϰڴٰ� �������ϴ� ��
		//�θ��̸��� �ڽ��̸��� ���� ����� �� : �������̵�
		B06 ap = new B06();
		ap.disp(); //ȣ��� ��(main)���� ó���������
		
		/*
		A06 ap = new A06();
		ap.disp();
		B06 bp = new B06();
		bp.disp2();
		*/
	}
}
