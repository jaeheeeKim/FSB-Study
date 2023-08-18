
public class test01 {
	public static void main(String[] args) {
		int score = 93;
		
		if(score >= 90) { // score 가 90 이상인 경우, 아래의 실행문 2개를 실행
			System.out.println("점수가 90보다 큽니다.");
			System.out.println("등급은 A 입니다.");
		}
		
		if(score < 90)
			System.out.println("점수가 90보다 작습니다."); // score 가 90보다 작은 경우, 실행
			System.out.println("이 실행문은, if문과 관계없이 실행됩니다."); 
	}
}
