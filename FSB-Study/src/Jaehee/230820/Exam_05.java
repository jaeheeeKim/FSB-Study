class A05 extends Object{
	@Override
	public String toString() { //클래스의 정의, 내용 //@Override는 toString의 짝꿍이며 재정의한다는 의미로 써주기
		return "A05클래스입니다.";
	}
}

public class Exam_05 {
	public static void main(String[] args) {
		A05 ap = new A05(); //ap는 데이터의 주소값을 가지고있는 참조변수
		System.out.println("ap.getClass = " + ap.getClass()); 
											//Object클래스가 getClass를 갖고있어서 상속 받아서 출력가능함
		System.out.println("ap.toString = "+ap.toString());
		System.out.println("ap = "+ap);//ap뒤에 .toString() 생략되어있음 / ap라는 주소값을 출력
		
		String name = "홍길동";
		System.out.println("name = " + name); //name뒤에 .toString() 생략되어있음 / name 자기자신을 나타냄
		String a = new String("홍길동");
		if (name.equals(a)) { //equals으로 name과 a를 각각 비교할 수 있음
			
		}
	}
}

//java의 모든 클래스는 Object클래스를 상속(extends)받고 있습니다.
