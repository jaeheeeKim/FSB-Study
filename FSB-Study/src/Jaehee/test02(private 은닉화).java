package test;

public class test02 {
	public static void main(String[] args) {
		Test01 t1 = new Test01(); //접근제한자 private때문에 접근할 수 없었지만 은닉화(setter메소드,getter메소드)하여 접근 가능!
		
		//t1.a = 100;//a로 접근하고싶다는 뜻
		//t1.b = 200;// 은닉화하기전에는 error발생!! 멤버필드에 private이 있어 접근 불가능!!
		
		t1.setA(11); //멤버필드 재설정된 setA에 접근하고싶다는 뜻
		
		System.out.println("t1.a = " + t1.getA()*10); //t1.a=11*10
	}
}
