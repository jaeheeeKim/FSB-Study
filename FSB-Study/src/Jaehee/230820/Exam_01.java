class Bank{
	private String name;
	private double money;
	static float iyul; //���۰� ���ÿ� �޸� static(��øŬ����)�� ���� '���'�κп�  iyul�� �ö���!
	static { //static ���� : static����ʵ��� �ʱⰪ�� ����(���۰� ���ÿ� �ö󰡱⶧����)
		iyul = 0.05f;
	}
	
	public Bank(String name, double money, float iyul)	{ //�����ڰ� ����ʵ� �ʱⰪ ����
		this.name = name;	//��ü�����ؾ���
		this.money = money;	//��ü�����ؾ���
		Bank.iyul = iyul; 
		//iyul�� ������ �͵� �ƴϰ� ��ΰ� ����ϴ� �����̴ϱ�  Bank����!
		//�ڱ��ڽ��� �ǹ��ϴ� this�� �ƴ϶� Bank����� �����ֱ�
		//��ü ������ ��������ϱ� Ŭ�����̸����� �����Ѵ�.
	}
	/*
	public void setIyul(float iyul) {
		Bank.iyul = iyul;
	}
	*/
	public void disp()	{
		System.out.printf("%s���� ���� �ܾ��� %.1f���̰�," 
				//SystemŬ���� �ȿ��� ������� out�� in�� static ���
				+"���� ����Ǵ� ������ %.2f%%�Դϴ�.\n", name, money, iyul*100);
	}
}

public class Exam_01 {
	public static void main(String[] args) {
		System.out.println("���� ����Ǵ� ���� : " + Bank.iyul + "%");
		/*
		Bank aaa = new Bank("aaa", 1000.0, 0.04f); //f�� �������ָ� double�� �ν�
		aaa.disp();
		System.out.println("==========================================");
		
		Bank bbb = new Bank("bbb", 1000.0, 0.035f);
		//aaa.setIyul(0.035f); //�����������, ��ȭ�ϴ� ��(iyul) �տ� static�� ���̸� ���ϰ� ��ø!
		aaa.disp();
		bbb.disp();
		System.out.println("==========================================");
		
		Bank ccc = new Bank("ccc", 1000.0, 0.032f);
		//aaa.setIyul(0.032f); 
		aaa.disp();
		//bbb.setIyul(0.032f);
		bbb.disp();
		ccc.disp();
		*/
	}
}
