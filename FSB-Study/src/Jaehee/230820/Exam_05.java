class A05 extends Object{
	@Override
	public String toString() { //Ŭ������ ����, ���� //@Override�� toString�� ¦���̸� �������Ѵٴ� �ǹ̷� ���ֱ�
		return "A05Ŭ�����Դϴ�.";
	}
}

public class Exam_05 {
	public static void main(String[] args) {
		A05 ap = new A05(); //ap�� �������� �ּҰ��� �������ִ� ��������
		System.out.println("ap.getClass = " + ap.getClass()); 
											//ObjectŬ������ getClass�� �����־ ��� �޾Ƽ� ��°�����
		System.out.println("ap.toString = "+ap.toString());
		System.out.println("ap = "+ap);//ap�ڿ� .toString() �����Ǿ����� / ap��� �ּҰ��� ���
		
		String name = "ȫ�浿";
		System.out.println("name = " + name); //name�ڿ� .toString() �����Ǿ����� / name �ڱ��ڽ��� ��Ÿ��
		String a = new String("ȫ�浿");
		if (name.equals(a)) { //equals���� name�� a�� ���� ���� �� ����
			
		}
	}
}

//java�� ��� Ŭ������ ObjectŬ������ ���(extends)�ް� �ֽ��ϴ�.
