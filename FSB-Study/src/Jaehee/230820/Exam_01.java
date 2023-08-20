class Bank{
	private String name;
	private double money;
	static float iyul; //시작과 동시에 메모리 static(중첩클래스)로 인해 '상수'부분에  iyul이 올라가짐!
	static { //static 구문 : static멤버필드의 초기값을 구현(시작과 동시에 올라가기때문에)
		iyul = 0.05f;
	}
	
	public Bank(String name, double money, float iyul)	{ //생성자가 멤버필드 초기값 설정
		this.name = name;	//객체생성해야함
		this.money = money;	//객체생성해야함
		Bank.iyul = iyul; 
		//iyul은 누구의 것도 아니고 모두가 사용하는 범위이니까  Bank꺼임!
		//자기자신을 의미하는 this가 아니라 Bank꺼라고 적어주기
		//객체 생성과 상관없으니까 클래스이름으로 접근한다.
	}
	/*
	public void setIyul(float iyul) {
		Bank.iyul = iyul;
	}
	*/
	public void disp()	{
		System.out.printf("%s님의 현재 잔액은 %.1f원이고," 
				//System클래스 안에서 만들어진 out과 in은 static 멤버
				+"현재 적용되는 이율은 %.2f%%입니다.\n", name, money, iyul*100);
	}
}

public class Exam_01 {
	public static void main(String[] args) {
		System.out.println("현재 적용되는 이율 : " + Bank.iyul + "%");
		/*
		Bank aaa = new Bank("aaa", 1000.0, 0.04f); //f로 안적어주면 double로 인식
		aaa.disp();
		System.out.println("==========================================");
		
		Bank bbb = new Bank("bbb", 1000.0, 0.035f);
		//aaa.setIyul(0.035f); //안적었을경우, 변화하는 값(iyul) 앞에 static을 붙이면 편리하게 중첩!
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
