import java.util.*;

public class Exam_07 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//Day09. 하노이의탑 문제(매우 어려움)
		
		System.out.print("원판의 수를 입력 : ");
		int n = in.nextInt();
		
		hanoi(n, 1, 2, 3);
		//원판의 개수와 pol 순서(기둥 순서)를 하노이 메소드로 전달한다.

	}
	
	public static void hanoi(int n, int f_pol, int s_pol, int t_pol) {
		if (n == 1) { //원판 1개라면 1번기둥에서 3번 기둥으로 보내고
			System.out.println(n + " : " + f_pol + " -> " + t_pol);
			return;
		} 
		// N-1개의 원판을 1번 막대에서 2번 막대로 이동
		// N-1, 1(시작), 3(중간지점), 2(옮길 목표)
		hanoi(n-1, f_pol, t_pol, s_pol);
		// 가장 큰 원판을 1번 막대에서 3번 막대로 이동
		System.out.println(n + " : " + f_pol + " -> " + t_pol);
		// N-1개의 원판을 2번 막대에서 3번 막대로 이동
		// N-1, 2(시작), 1(중간지점), 3(옮길 목표)
		hanoi(n-1, s_pol, f_pol, t_pol);
	}
}

//문제 : http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=441&sca=2080
//해설 : https://st-lab.tistory.com/96

//중간지점 거쳐서 가는 과정을 반복

/*

3, 1, 2, 3
2, 1, 3, 2
1, 

*/