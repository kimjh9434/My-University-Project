import java.util.Scanner;

public class MainDrive extends Exception
{
	
	static Scanner scan = new Scanner(System.in);
	

	public static void main(String[] args) 
	{
		
		AddressBook addressBook = new AddressBook();
		
		int menu;
		int index;
		
		
		//초기값 일부 세팅
		index = addressBook.Record( "홍길동", "서울시 송파구 삼전동", "010-4885-1541", "honghoho@nate.com");
	    index = addressBook.Record( "고길동", "부산광역시 영도구 대교동2가","011-4758-2156","kogildong@naver.com");
	    index = addressBook.Record( "강길동", "서울시 강남구 삼성동", "010-7777-7777", "lukyboy@naver.com"); 
	    index = addressBook.Record( "나길동", "서울시 노원구 상계동", "010-6666-6666", "unlukyboy@naver.com"); 
	    index = addressBook.Record( "김길동", "인천시 중구 인현동  ",  "010-3663-6581", "KimKillDong@hanmail.net");
	    index = addressBook.Record( "박길동", "서울시 성동구 마장동", "010-1234-5678", "nomalboy@naver.com");
	    
	    //index = addressBook.Record( "홍길동", "서울시 강남구 논현동", "010-4444-4444", "gkglgngpgh@hanmail.net");
	    //index = addressBook.Record( "홍길동", "핼리포터의 비밀의 방", "010-4885-6666", "gkgkgk@hanmail.net");
	    index = addressBook.Record( "김제헌", "서울시 성동구 마장동", "010-4013-9434", "kimhk9434@naver.com");


		
		Form.DisplayMenu();
		menu=scan.nextInt();
		
		while ( menu !=0 ) 
		{
			switch (menu) 
			{
			case 1: Form.Recording(addressBook);  break ;
			case 2: Form.Finding(addressBook) ;   break ;
			case 3: Form.Correcting(addressBook); break ;
			case 4: Form.Erasing(addressBook);    break ;
			case 5: Form.Arranging(addressBook);  break ;
			case 6: Form.ListView(addressBook);    break ;
			default : break ;
			}
			Form.DisplayMenu();
			menu=scan.nextInt();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.printf("\t\t\t주소록 관리 프로그램을 종료합니다.");
	}
}