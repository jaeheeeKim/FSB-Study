
public class test01 {
	public static void main(String[] args) {
		int score = 93;
		
		// if 절 2개로 표현하기
		if(score >= 90) { // score 가 90 이상인 경우, 아래의 실행문 2개를 실행
			System.out.println("점수가 90보다 큽니다.");
			System.out.println("등급은 A 입니다.");
		}
		
		if(score < 90)
			System.out.println("점수가 90보다 작습니다."); // score 가 90보다 작은 경우, 실행
			System.out.println("이 실행문은, if문과 관계없이 실행됩니다.");
			
		// if else 절 로 표현하기
			if(score >= 90) {
				System.out.println("점수가 90보다 큽니다.");
				System.out.println("등급은 A 입니다.");
			}else {
				System.out.println("점수가 90보다 작습니다.");
				System.out.println("등급은 B 입니다.");
			}
		// 조건문이 여러개인 if (else if)
		if(score >=95) {
			System.out.println("점수가 95보다 큽니다.");
		}else if(score >=90) {
			System.out.println("점수가 90보다 큽니다.");
		}else {
			System.out.println("점수가 90보다 작습니다.");
		}
	}
}
