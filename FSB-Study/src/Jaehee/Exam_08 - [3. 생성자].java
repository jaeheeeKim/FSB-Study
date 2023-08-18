class A08{ //사용자정의자료형(구조체는 맨앞에만 쓸 수 있음) - java는 구조체 안쓰고 class로
	int a;
	int b;
	//A08(){} : default생성자, 주로 매개변수가 없는 생성자를 말한다, JVM이 만들어 주는 생성자는 내용도 없다
	//생략해도 jvm이 자동으로 만들어줘서 객체를 생성할 수 있음
	
	A08(int x){
		a = x;
		b = 20;
	}
	A08(int x, int y){
		a = x;
		b = y;
	}
	A08(){
		a = 10;
		b = 20;
	}
	void disp() {
		System.out.println("a = " + a +  ", b = " + b);
	}
}
public class Exam_08 {
	public static void main(String[] args) {
		A08 ap = new A08(100, 200); //생성자A08()에 의해 객체ap를 생성한다.★★★
		ap.disp();
		//자료형 객체명(변수명):instance
		//힙(동적메모리) new(동적메모리 할당연산자)
		//생성자는 A08() 안에 들어있어야함
		
		/*위와 같음 , class A08에서 default생성자일 경우!
		A08 ap = new A08(); //생성자A08()에 의해 객체ap를 생성한다.★★★
		ap.a = 100; //a와 b가 초기값으로 주어지지않으면 힙에서 초기값을 0으로 부여해줌  a=0,b=0
		ap.b = 200;
		ap.disp();
		*/
		
		//ap.A08(); 오류발생!! 생성자는 객체를 생성할때만 호출가능!!
	}
}