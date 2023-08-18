import java.util.*;
import java.io.*;

class Member{
	private String name;
	private String tel;
	
	Member(String name, String tel){
		this.name = name;
		this.tel = tel;
	}
	
	public String getName() {
		return name;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return tel;
	}
	
	void disp() {
		System.out.println(name +"님의 전화번호는 " + tel +"번 입니다.");
	}
}
class MemberMg{
	Scanner in;
	Member[] mb;
	int increment;	//배열값이 초과되면 배열의 크기를 늘리기 위해(처음에 5개를 만들었지만 3개 더 늘릴거다)
	int po; //배열의 위치
	
	MemberMg(){
		in = new Scanner(System.in);
		mb = new Member[5];
		increment = 3; 
		//증감치 3
		//mb2(8칸짜리)를 만들어서 mb값 다 복사하여 실행
		po = 0;
	}
	
	void input() {
		System.out.print("이름을 입력 : ");
		String name = in.next();
		System.out.print("전화번호를 입력 : ");
		String tel = in.next();
		mb[po] = new Member(name, tel);
		//mb[po] = input; //input에는 힙에 존재하는 name과 tel주소값이 들어있음
		po++; //다음 자리에 입력
		if(po == mb.length) { //po가 5가 되면
		Member[] mb2 = new Member[mb.length+increment];
		for(int i = 0; i<mb.length; ++i) {
			mb2[i] = mb[i];
		}
		mb = mb2;
		}
	}
	void view() {
		for(int i=0; i<po; ++i) {
			mb[i].disp(); //출력하는 메소드 호출
		}
	}
	void edit() {
		System.out.print("수정할 회원의 이름 : ");
		String name = in.next();
		for(int i=0; i<po; ++i) {
			if (name.equals(mb[i].getName())) {
				System.out.println("현재 전화번호는 " + mb[i].getTel() +"번 입니다.");
				System.out.print("수정할 전화번호 : ");
				mb[i].setTel(in.next());
				System.out.println(name+"님의 전화번호를 수정하였습니다.");
				return;
			}
		}
		System.out.println(name+"님은 저희 회원이 아닙니다.");
	}
	void delete() { //다음자리 내용 앞으로 땡겨오기
		System.out.print("삭제할 회원의 이름 : ");
		String name = in.next();
		for(int i=0; i<po; ++i) {
		if(name.equals(mb[i].getName())) { //입력받은 name의 위치
		for(int j=1; j<po-1; ++j) { //j가 1일때부터 비교 //맨 마지막 데이터는 비교하지않아야함
			mb[j] = mb[j+1]; //다음자리 내용(name과 tel) 앞에 넣기
		}
		po--; //앞으로 땡겨가기
		System.out.println(name + "회원님을 삭제하였습니다.");
		return;
	}
	}
		System.out.println(name+"님은 저희 회원이 아닙니다.");
	}
	void exit() {
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);	//프로그램을 강제적으로 종료시킨다.
	}
	
}

public class E {
	public static void main(String[] args) throws IOException{
		MemberMg member = new MemberMg();
		
		while(true) {
			System.out.print("1.입력 2.출력 3.수정 4.삭제 5.종료 : ");
			int select = System.in.read() - 48;
			System.in.skip(5);
			switch(select) {
			case 1 :	member.input(); break;
			case 2 :	member.view(); break;
			case 3 :	member.edit(); break;
			case 4 :	member.delete(); break;
			case 5 :	member.exit(); break;
			default :	System.out.println("잘못입력하셨습니다. 다시 입력해 주세요!!");
			}
		}
	}
}