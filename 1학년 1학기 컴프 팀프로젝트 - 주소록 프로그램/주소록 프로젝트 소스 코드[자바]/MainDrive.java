import java.util.Scanner;

public class MainDrive extends Exception
{
	
	static Scanner scan = new Scanner(System.in);
	

	public static void main(String[] args) 
	{
		
		AddressBook addressBook = new AddressBook();
		
		int menu;
		int index;
		
		
		//�ʱⰪ �Ϻ� ����
		index = addressBook.Record( "ȫ�浿", "����� ���ı� ������", "010-4885-1541", "honghoho@nate.com");
	    index = addressBook.Record( "��浿", "�λ걤���� ������ �뱳��2��","011-4758-2156","kogildong@naver.com");
	    index = addressBook.Record( "���浿", "����� ������ �Ｚ��", "010-7777-7777", "lukyboy@naver.com"); 
	    index = addressBook.Record( "���浿", "����� ����� ��赿", "010-6666-6666", "unlukyboy@naver.com"); 
	    index = addressBook.Record( "��浿", "��õ�� �߱� ������  ",  "010-3663-6581", "KimKillDong@hanmail.net");
	    index = addressBook.Record( "�ڱ浿", "����� ������ ���嵿", "010-1234-5678", "nomalboy@naver.com");
	    
	    //index = addressBook.Record( "ȫ�浿", "����� ������ ������", "010-4444-4444", "gkglgngpgh@hanmail.net");
	    //index = addressBook.Record( "ȫ�浿", "�۸������� ����� ��", "010-4885-6666", "gkgkgk@hanmail.net");
	    index = addressBook.Record( "������", "����� ������ ���嵿", "010-4013-9434", "kimhk9434@naver.com");


		
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
		System.out.printf("\t\t\t�ּҷ� ���� ���α׷��� �����մϴ�.");
	}
}