//인원수를 입력받아 그 인원수 만큼 이름, 국어, 영어점수를 입력받고 이름, 총점, 순위를 출력시켜주는 프로그램
//(단, 이름, 국어, 영어, 총점, 순위를 하나의 자료형으로 만든 클래스를 만들고, 그 클래스를 이용하여 만들어주세요.

//은닉화 응용버전
import java.util.Scanner;

class SungJuk{
	private String name;
	private int kor, eng, tot, rank;
	
	SungJuk(String name, int kor, int eng){
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		tot = kor+eng;
		rank=1; //순위 초기값 1임
	}
	
	public String getName() {return name;} //재설정(set) 없이 멤버필드값만 꺼내기(get) 
	
	public void setKor() {
		this.kor = kor;}
	public int getKor() {return kor;}
	
	public void setEng() {
		this.eng = eng;}
	public int getEng() {return eng;}
	
	public int getTot() {return tot;} //재설정(set) 없이 멤버필드값만 꺼내기(get) 
	public int getrank() {return rank;} //재설정(set) 없이 멤버필드값만 꺼내기(get) 
	public void clearRank() { 
		rank = 1;
	}
	public void plusRank() { 
		rank++;
	}
	
	void disp() {
		System.out.println(name+"님의 총점은"+tot+"점이고, 순위는"+rank+"등입니다.");
	}
}

public class Exam_04 {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		
		System.out.print("학생수를 입력 : ");
		int inwon = in.nextInt();
		
		SungJuk[] sj = new SungJuk[inwon];
		
		for(int i = 0; i<inwon; ++i) {
			System.out.print("이름을 입력 : ");
			String name = in.next();
			System.out.print("국어점수 : ");
			int kor = in.nextInt();
			System.out.print("영어점수 : ");
			int eng = in.nextInt();
		sj[i] = new SungJuk(name, kor, eng);
		}
		for(int i=0; i<inwon; ++i) {
			for(int j=0; j<inwon; ++j) {
				if(sj[i].getTot() < sj[j].getTot()) {
					sj[i].plusRank();
				}
			}
		}
		for(int i=0; i<inwon; ++i) {
			sj[i].disp();
		}
	}
	}

/*
import java.util.Scanner;

class SungJuk{
	String name; //은닉화버전에서 달라짐
	int kor, eng, tot, rank; //은닉화버전에서 달라짐
	
	SungJuk(String name, int kor, int eng){
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		tot = kor+eng;
		rank=1; //순위 초기값 1임
	}
	void disp() {
		System.out.println(name+"님의 총점은"+tot+"점이고, 순위는"+rank+"등입니다.");
	}
}

public class Exam_04 {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		
		System.out.print("학생수를 입력 : ");
		int inwon = in.nextInt();
		
		SungJuk[] sj = new SungJuk[inwon];
		
		for(int i = 0; i<inwon; ++i) {
			System.out.print("이름을 입력 : ");
			String name = in.next();
			System.out.print("국어점수 : ");
			int kor = in.nextInt();
			System.out.print("영어점수 : ");
			int eng = in.nextInt();
		sj[i] = new SungJuk(name, kor, eng);
		}
		for(int i=0; i<inwon; ++i) {
			for(int j=0; j<inwon; ++j) {
				if(sj[i].tot < sj[j].tot) { //은닉화버전에서 달라짐
					sj[i].rank++; //은닉화버전에서 달라짐
				}
			}
		}
		for(int i=0; i<inwon; ++i) {
			sj[i].disp();
		}
	}
	}
*/