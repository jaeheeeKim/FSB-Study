
public class Exam_03 {
	public static void main(String[] args) {
		int sum = plus(5);
		System.out.println("1~5까지의 합 : "+sum);
	}
	public static int plus(int n) { //n=5에서 n=4, n=3, n=2, n=1
		if(n == 1) return 1;
		return n + plus(n-1); //자기자신을 호출(끝나는 지점을 정해주어야함)
	}
}

/*

n=5 => 5 + plus(4)
n=4 => 4 + plus(3)
n=3 => 3 + plus(2)
n=2 => 2 + plus(1)
plus(1) = 1

따라서, 1 > 2+1 > 3+3 > 4+6 > 5+10 => 최종 sum=15

*/