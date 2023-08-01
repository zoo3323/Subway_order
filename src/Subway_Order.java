import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

class OrderData {
	int price;
	String orderProduct;

	public OrderData(int price, String orderProduct) {
			this.price = price;
			this.orderProduct = orderProduct;
		}
		public void setprice(int price) {
			this.price = price;
		}
		public int getprice() {
			return price;
		}
		public void setorderProduct(String orderProduct) {
			this.orderProduct = orderProduct;
	}

	public void showInfo() {
		System.out.println("메뉴:" + orderProduct + "\t\t\t\t\t\t\t\t\t\t\t\t\t| " + price + "원");
	}
}

class sanddata extends OrderData {
	String bread;
	String vegi;
	String source;
	int size;
	public sanddata(int price, String orderProduct, String bread, String vegi, String source, int size) {
		super(price, orderProduct);
		this.bread = bread;
		this.vegi = vegi;
		this.source = source;
		this.size = size;
	}
	public void setbread(String bread) {
		this.bread = bread;
	}
	public void setvegi(String vegi) {
		this.vegi = vegi;
	}
	public void setsource(String source) {
		this.source = source;
	}
	public void showInfo() {
		System.out.println("메뉴:" + orderProduct + "\t사이즈 : "+ size + "\t빵:" + bread + "\t야채:" + vegi + "\t소스:" + source + "\t| " + price + "원");
	}
}

public class Subway_Order {

	public static void main(String[] args) {
		ArrayList<OrderData> list = new ArrayList<OrderData>();		// 주문 목록 리스트
		Scanner sc = new Scanner(System.in);
		int total = 0; // 최종 가격
		while (true) {
			int num = inputmenu(sc); 			// 메뉴
			if (num == 0) {break;} 				// 0 입력 -  종료
			else if (num == 1) {				// 1 입력 - 샌드위치
				String sandwich = inputsand(sc);	// 샌드위치 종류선택
				int size = inputsize(sc);			// 사이즈 선택
				String bread = inputbread(sc);		// 빵 선택
				String vegi = inputvegi(sc);		// 야채 선택
				String source = inputsource(sc);	// 소스 선택
				int price = menuprice(sandwich);	// 가격 계산
				if (size == 30 ) {price = price*2;}	// 사이즈가 30cm면 가격 2배
				list.add(new sanddata(price, sandwich, bread, vegi, source, size));	// 샌드위치 정보를 리스트에 저장
			}
			else if (num == 2) {				// 2 입력 - 사이드 메뉴
				String side = inputside(sc);		// 사이드 메뉴 선택
				int price = menuprice(side);		// 가격 계산
				list.add(new OrderData(price, side));								// 사이트 메뉴 정보를 리스트에 저장
			}
			else if (num == 3) {				// 3 입력 - 주문 목록 확인
				System.out.println("\n===========================================[주문 목록]===========================================");
				for (int i = 0; i<list.size(); i++) {								// 리스트 출력
					list.get(i).showInfo();
				}
				int cart = 0;
				for (int i = 0; i<list.size(); i++) { // 장바구니 총 가격 계산
					cart += list.get(i).getprice();
				}
				System.out.println("\t\t\t\t\t\t\t\t\t주문 합계\t= " + cart + "원");

				System.out.println("===============================================================================================");
			}
			else if (num == 4){					// 4 입력 - 주문 목록 삭제
				for (int i = list.size(); i>0; i--) {								// 리스트 전부 삭제
					list.remove(i-1);
				}
				System.out.println("주문목록이 삭제되었습니다.");
				timesleep(1000);
			}
			else if (num == 5) {				// 5 입력 - 결제
				for (int i = 0; i<list.size(); i++) { // 가격 계산
					total += list.get(i).getprice();
				}
				System.out.println("\n========결제중========");
				for (int i = 0; i<20; i++) {
					System.out.print("-");
					timesleep(100);
				}

				System.out.println();
				System.out.println(total+"원 결제가 완료되었습니다.");
				break;
			}
			else {
				continue;
			}
		}
		

		System.out.println();
	}
	static int inputsize(Scanner sc) {
		int size = 0;
		while (true) {
			System.out.print("""
					
					=====[사이즈]=====
					1 : 15cm    (기본)
					2 : 30cm
					================
					사이즈를 선택해주세요 : """);
			try {
				int number = sc.nextInt();
				if (number == 1) {size = 15;break;}
				else if (number == 2) {size = 30; break;}
				else {
					System.out.println("다시 입력해주세요..");
					timesleep(1000);
					continue;
				}
			}
			catch ( InputMismatchException ime){
				System.out.print("\n숫자만 입력해 주세요!!");
				sc.next();
				timesleep(1000);
				continue;
			}
		}
		return size;
	}
	static int inputmenu(Scanner sc) {
		System.out.print("""
				
				┌───────[메뉴]───────┐
				│1 : 샌드위치\t\t\t│
				│2 : 사이드\t\t\t│
				│3 : 주문목록 확인\t│
				│4 : 주문목록 삭제\t│
				│5 : 결제\t\t\t│
				└───────────────────┘
				메뉴를 골라주세요(0:종료) : """);
		try {
			int number = sc.nextInt();
			return number;
		}
		catch ( InputMismatchException ime){
			System.out.print("\n숫자만 입력해 주세요!!");
			sc.next();
			timesleep(1000);
			return -1;
		}
	}
	static String inputside(Scanner sc) {
		String side = "";
		while (true) {
			System.out.print("""
					
					==================[메뉴]==================
					1 : 베지 셀러드\t\t\t\t46Kcal\t5800원
					2 : 로스트 치킨 샐러드\t\t\t138Kcal\t8100원
					3 : 로티세리 바비큐 치킨 샐러드\t168Kcal\t8100원
					4 : 치즈 아보카도 랩\t\t\t478Kcal\t5700원
					5 : 쉬림프 에그마요 랩\t\t\t366Kcal\t5000원
					=========================================
					메뉴를 골라주세요 : """);
			try {
				int number = sc.nextInt();
				if (number == 1) {side = "베지 셀러드         ";break;}
				else if (number == 2) {side = "로스트 치킨 샐러드     ";break;}
				else if (number == 3) {side = "로티세리 바비큐 치킨 샐러드";break;}
				else if (number == 4) {side = "치즈 아보카도 랩      ";break;}
				else if (number == 5) {side = "쉬림프 에그마요 랩     ";break;}
				else {
					System.out.println("다시 입력해주세요..");
					timesleep(1000);
					continue;
				}
			}
			catch ( InputMismatchException ime){
				System.out.print("\n숫자만 입력해 주세요!!");
				sc.next();
				timesleep(1000);
				continue;
			}

		}
		return side;
	}
	
	static String inputsand(Scanner sc) {
		String sandwich = "";
		while (true) {
			System.out.print("""
					
					============[샌드위치]=============
					1 : 에그마요\t\t\t416Kcal\t4600원
					2 : 이탈리안 비엠티\t388Kcal\t5700원
					3 : 비엘티\t\t\t300Kcal\t5700원
					4 : 로스트 치킨\t\t300Kcal\t6400원
					5 : 써브웨이 클럽\t\t299Kcal\t6200원
					6 : K-바비큐\t\t\t372Kcal\t6200원
					=================================
					메뉴를 골라주세요 : """);
			try {
				int number = sc.nextInt();
				if (number == 1) {sandwich = "에그마요    ";break;}
				else if (number == 2) {sandwich = "이탈리안 비엠티";break;}
				else if (number == 3) {sandwich = "비엘티     ";break;}
				else if (number == 4) {sandwich = "로스트 치킨  ";break;}
				else if (number == 5) {sandwich = "써브웨이 클럽 ";break;}
				else if (number == 6) {sandwich = "K-바비큐   ";break;}
				else {
					System.out.println("다시 입력해주세요..");
					timesleep(1000);
					continue;
				}
			}
			catch ( InputMismatchException ime){
				System.out.print("\n숫자만 입력해 주세요!!");
				sc.next();
				timesleep(1000);
				continue;
			}

		}
		return sandwich;
	}
	
	static String inputbread(Scanner sc){
		String bread = "";
			while (true) {
				System.out.print("""
						
						=======[빵]=======
						1 : 화이트
						2 : 하티
						3 : 파마산 오레가노
						4 : 허니오트
						5 : 위트
						6 : 플랫브레드
						==================
						빵을 골라주세요 : """);
				try {
					int number = sc.nextInt();
					if (number == 1) {bread = "화이트     ";break;}
					else if (number == 2) {bread = "하티      ";break;}
					else if (number == 3) {bread = "파마산 오레가노";break;}
					else if (number == 4) {bread = "허니오트    ";break;}
					else if (number == 5) {bread = "위트      ";break;}
					else if (number == 6) {bread = "플랫브레드   ";break;}
					else {
						System.out.println("다시 입력해주세요..");
						timesleep(1000);
						continue;
					}
				}
				catch ( InputMismatchException ime){
					System.out.print("\n숫자만 입력해 주세요!!");
					sc.next();
					timesleep(1000);
					continue;
				}
		}
		return bread;
	}
	static String inputvegi(Scanner sc){
		String vegi = "";
		while(true) {
			System.out.print("""
					
					=====[야채]=====
					1 : 양상추
					2 : 토마토
					3 : 오이
					4 : 피망
					5 : 양파
					6 : 피클
					7 : 올리브
					8 : 할라피뇨
					9 : 아보카도
					===============
					야채를 선택해주세요 : """);
			try {
				int number = sc.nextInt();
				if (number == 1) {vegi = "양상추 ";break;}
				else if (number == 2) {vegi = "토마토 ";break;}
				else if (number == 3) {vegi = "오이  ";break;}
				else if (number == 4) {vegi = "피망  ";break;}
				else if (number == 5) {vegi = "양파  ";break;}
				else if (number == 6) {vegi = "피클  ";break;}
				else if (number == 7) {vegi = "올리브 ";break;}
				else if (number == 8) {vegi = "할라피뇨";break;}
				else if (number == 9) {vegi = "아보카도";break;}
				else {
					System.out.println("다시 입력해주세요..");
					timesleep(1000);
					continue;
				}
			}
			catch ( InputMismatchException ime){
				System.out.print("\n숫자만 입력해 주세요!!");
				sc.next();
				timesleep(1000);
				continue;
			}

		}
		return vegi;
	}
	static String inputsource(Scanner sc) {
		String Source = "";
		while (true) {
			System.out.print("""
					
					=======[소스]=======
					1 : 랜치
					2 : 마요네즈
					3 : 스위트 어니언
					4 : 허니 머스타드
					5 : 스위트 칠리
					6 : 소금
					7 : 후추
					8 : 올리브 오일
					===================
					소스를 선택해주세요 : """);
			try {
				int number = sc.nextInt();
				if (number == 1) {Source = "랜치     ";break;}
				else if (number == 2) {Source = "마요네즈   ";break;}
				else if (number == 3) {Source = "스위트 어니언";break;}
				else if (number == 4) {Source = "허니 머스타드";break;}
				else if (number == 5) {Source = "스위트 칠리 ";break;}
				else if (number == 6) {Source = "소금     ";break;}
				else if (number == 7) {Source = "후추     ";break;}
				else if (number == 8) {Source = "올리브 오일 ";break;}
				else {
					System.out.println("다시 입력해주세요..");
					timesleep(1000);
					continue;
				}
			}
			catch ( InputMismatchException ime){
				System.out.print("\n숫자만 입력해 주세요!!");
				sc.next();
				timesleep(1000);
				continue;
			}

		}
		return Source;
	}
	static int menuprice(String menu) {
		int price = 0;
		if (menu == "에그마요    ") {price = 4600;}
		else if (menu == "이탈리안 비엠티") {price = 5700;}
		else if (menu == "비엘티     ") {price = 5700;}
		else if (menu == "로스트 치킨  ") {price = 6400;}
		else if (menu == "써브웨이 클럽 ") {price = 6200;}
		else if (menu == "K-바비큐   ") {price = 6400;}
		else if (menu == "베지 셀러드         ") {price = 5800;}
		else if (menu == "로스트 치킨 샐러드     ") {price = 8100;}
		else if (menu == "로티세리 바비큐 치킨 샐러드") {price = 8100;}
		else if (menu == "치즈 아보카도 랩      ") {price = 5700;}
		else if (menu == "쉬림프 에그마요 랩     ") {price = 5000;}
		return price;
	}
	
	static void timesleep(int time) {		// 콘솔 시간을 지연시켜주는 함수
		try {
			Thread.sleep(time); //1초 대기
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
	
