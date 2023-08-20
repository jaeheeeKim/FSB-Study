import java.util.Scanner;

import sung.*;

class SungJuk2 extends SungJuk{
	//name, kor, eng, tot, rank
	private int com;
	public SungJuk2(String name, int kor, int eng, int com) {
		super(name, kor, eng);
		this.com = com;
		this.tot = kor + eng + com;
	}
	
	public void setCom(int com) {		this.com = com;	}
	public int getCom() {		return com;	}
	
	@Override
	public void setTot() {		tot = getKor() + getEng() + com;	}
	
}

public class Exam_07{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("학생수를 입력 : ");
		int inwon = in.nextInt();
		
		SungJuk2[] sj = new SungJuk2[inwon];
		
		for(int i=0; i<inwon; ++i) {
			System.out.print("이름을 입력 : ");
			String name = in.next();
			System.out.print("국어점수를 입력 : ");
			int kor = in.nextInt();
			System.out.print("영어점수를 입력 : ");
			int eng = in.nextInt();
			System.out.print("컴퓨터점수를 입력 : ");
			int com = in.nextInt();
			sj[i] = new SungJuk2(name, kor, eng, com);
		}
		
		for(int i=0; i<inwon; ++i) {
			for(int j=0; j<inwon; ++j) {
				if (sj[i].getTot() < sj[j].getTot()) {
					sj[i].plusRank();
				}
			}
		}
		
		for(int i=0; i<inwon; ++i) {
			sj[i].disp();
		}
		//SungJuk sj = new SungJuk("aaa", 100, 100);
		//sj.tot = 1000;	//상속관계가 아니므로 오류발생!!
	}
}