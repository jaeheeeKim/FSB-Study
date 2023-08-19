package test;

public class Test01 {
	private int a;
	private int b;
	public Test01() {
		a=10;
		b=20;
	}

	
	//setter메소드 : 멤버필드 재설정
	public void setA(int a){ //set+멤버필드 a를 합쳐서 합성어인 setA라는 이름이 나온거다.
				//합성어
		if(a%2 != 0) {
		this.a = a;
		}
	}
	public void setB(int b){
		if(a%2 != 0) {
		this.b = b;
		}
	}
	//getter메소드 : 멤버필드값 꺼내기
	public int getA(){ return a; } //get+멤버필드 a를 합쳐서 합성어인 getA라는 이름이 나온거다.
	public int getB(){ return b; }
	
	public void disp() {
		System.out.println("a="+a+"b="+b);
	}
}
