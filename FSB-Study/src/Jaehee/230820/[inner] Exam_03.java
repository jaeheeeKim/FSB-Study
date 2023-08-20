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
	public void disp() { //이 메소드를 쓸때만 지역중첩클래스 1번Inner03가 보임
		class Inner03{	//지역중첩클래스 : 해당지역에서만 쓰이고 사라지기때문에
						//클래스이름이 같을때만 저장하면 인덱스번호를 매겨서 나타냄
						//Outer03 $ 1 Inner03.class <<< 지역중첩클래스1번이라는뜻
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
	public void disp2() { //이 메소드를 쓸때만 지역중첩클래스 2번Inner03가 보임
		class Inner03{	//지역중첩클래스 : 해당지역에서만 쓰이고 사라지기때문에
						//클래스이름이 같을때만 저장하면 인덱스번호를 매겨서 나타냄
						//Outer03 $ 2 Inner03.class <<< 지역중첩클래스2번이라는뜻
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
