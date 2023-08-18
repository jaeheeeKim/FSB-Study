//class room 활용해서 빈방문제 풀어보기 
//Room정의>메인(객체화하여 기능만들기)>각 메소드 생성

import java.io.*;
import java.util.*;

//Room정의①
class Room{
	int roomSu;		//방의 갯수 입력
	boolean room[];	//갯수만큼 배열만들기
	//false일때는 빈방, true일때는 사용중
	int roomNum;	//입실과 퇴실시 입력받을 방번호
	Scanner in;
	
	Room(){
		in = new Scanner(System.in);
		System.out.print("방의 갯수를 입력 : ");
		roomSu = in.nextInt();
		room = new boolean[roomSu];
	}
	
	//각 메소드 생성③
	void input() {
		do {
			System.out.print("입실하실 방의 번호 : ");
			roomNum = in.nextInt();
		}while(roomNum < 1 || roomNum > roomSu);
		if (room[roomNum-1]) {//true라면
			System.out.println(roomNum+"호실은 사용중입니다.");
		}else {//false라면 true해서 사용중으로 만들어주고 입실안내
			room[roomNum-1] = true;
			System.out.println(roomNum+"호실에 입실하셨습니다.");
		}
	}
	void output() {
		do {
			System.out.print("퇴실하실 방의 번호 : ");
			roomNum = in.nextInt();
		}while(roomNum < 1 || roomNum > roomSu);
		if (!room[roomNum-1]) {
			System.out.println(roomNum+"호실은 빈방입니다.");
		}else {
			room[roomNum-1] = false;
			System.out.println(roomNum+"호실에서 퇴실하셨습니다.");
		}
	}
	void view() {
		for(int i=0; i<roomSu; ++i) {
			if (room[i]) {
				System.out.printf("%d호실은 사용중\n", i+1);
			}else {
				System.out.printf("%d호실은 빈방\n", i+1);
			}
		}
	}
	void exit() {
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);	//프로그램을 강제적으로 종료시킨다.
	}
}

public class Exam_02 {
	public static void main(String[] args) throws IOException {
		Room room = new Room(); //Room메소드 객체 생성②
		
		while(true) {
			System.out.print("1.입실 2.퇴실 3.보기 4.종료 : ");
			int select = System.in.read() - 48; //Scanner클래스를 사용하지않아서 아스키코드로 입력되니까!
			System.in.skip(5);
			switch(select) {
			case 1 :	room.input(); break;
			case 2 :	room.output(); break;
			case 3 :	room.view(); break;
			case 4 :	room.exit(); break;
			default :	System.out.println("잘못누르셨습니다. 다시 입력해 주세요");
			}
		}
	}
}

