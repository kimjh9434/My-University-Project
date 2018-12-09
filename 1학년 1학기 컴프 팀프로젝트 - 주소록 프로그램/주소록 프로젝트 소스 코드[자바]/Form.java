import java.util.Scanner;
//import java.io.*; 


public class Form 
{
	static Scanner scan = new Scanner(System.in);


	//메뉴화면 보여주기
	static void DisplayMenu ()
	{	
		System.out.println();
		System.out.println();
		
		System.out.printf("\t\t\t주소록 관리 프로그램 \n\n");

		System.out.printf("\t\t---------------------------------------------------\n");
		System.out.printf("\t\t\t주\t소\t록\tversion 0.05\n");
		System.out.printf("\t\t---------------------------------------------------\n");
		System.out.printf("\t\t\t1.\t기\t재\t하\t기\n");
		System.out.printf("\t\t\t2.\t검\t색\t하\t기\n");
		System.out.printf("\t\t\t3.\t수\t정\t하\t기\n");
		System.out.printf("\t\t\t4.\t삭\t제\t하\t기\n");
		System.out.printf("\t\t\t5.\t정\t리\t하\t기\n");
		System.out.printf("\t\t\t6.\t목\t록\t보\t기\n");
		System.out.printf("\t\t\t0.\t종\t료\t하\t기\n");
		System.out.printf("\t\t---------------------------------------------------\n");
		System.out.printf("\t\t\t\t메뉴번호를 선택하세요! : ");
	}
	
	static void Recording(AddressBook addressBook)
	{
		String name;
		String address;
		String telephoneNumber;
		String emailAddress;
		
		int recording;
		int going=1;
		
		int index;
		
		do
		{
			System.out.printf("\n\n\n");
			System.out.printf("\t주소록 Version 0.05-기재하기\n") ;
			System.out.printf("\t========================================================================\n") ;
			System.out.printf("\t\t\t기재하고자 하는 사람의 정보들을 입력 하세요 \n");
			System.out.printf("\t------------------------------------------------------------------------\n");
			System.out.printf("\t성              명 : ");
			name=scan.nextLine();
			System.out.printf("\t주              소 : ");
			address=scan.nextLine();
			System.out.printf("\t전 화  번 호 : ");
			telephoneNumber=scan.nextLine();
			System.out.printf("\t이메일주소: ");
			emailAddress=scan.nextLine();
			System.out.printf("\t------------------------------------------------------------------------\n") ;
			System.out.printf("\t기재하시겠습니까? (1.Yes/2.No) ");
			recording = scan.nextInt();
			
			
			
			if ( recording == 1 ) 
			{
				index = addressBook.Record( name, address, telephoneNumber, emailAddress);	
				System.out.printf("\t========================================================================\n");	
				System.out.printf("\t번 호    성  명\t     주    소     \t 전화번호 \t 이메일주소\n");
				System.out.printf("\t------------------------------------------------------------------------\n");	
				System.out.printf("\t  %d    %s    %s  %s  %s\n", index+1, 
						                                          addressBook.personals[index].name,	
				                                                  addressBook.personals[index].address,	
				                                                  addressBook.personals[index].telephoneNumber, 	
				                                                  addressBook.personals[index].emailAddress);	
			}
			System.out.printf("\t========================================================================\n") ;
			System.out.printf("\t계속하시겠습니까? (1.Yes/2.No) ") ;
			going = scan.nextInt();
		}while ( going == 1 );
		
		System.out.println();
		System.out.println();
		System.out.println();
		
	}
	

	static void Finding(AddressBook addressBook) 
	{
		String name;
		
		int finding;
		int going = 1;
		int[] indexes = new int[addressBook.length];
		
		int count;
		int i;
		
		do
		{
			System.out.printf("\n\n\n");
			System.out.printf("\t주소록 Version 0.05-검색하기\n");
			System.out.printf("\t========================================================================\n");
			System.out.printf("\t\t\t찾으시고자 하는 사람의 성명을 입력 하세요 \n");
			System.out.printf("\t------------------------------------------------------------------------\n");
			System.out.printf("\t성    명: ");
			name=scan.next();
			
			System.out.printf("\t------------------------------------------------------------------------\n") ;
			System.out.printf("\t찾으시는 성명이 맞습니까? (1.Yes/2.No) ");
			finding = scan.nextInt();
			
			if ( finding == 1 ) 
			{
				indexes = addressBook.Find(name);
				
				System.out.printf("\t========================================================================\n") ;
				
				if(indexes.length>0)
				{
					System.out.printf ("\t번 호    성  명\t     주    소     \t 전화번호 \t 이메일주소\n") ;
					System.out.printf ("\t------------------------------------------------------------------------\n") ;
					
					for ( i = 0 ; i < indexes.length ; i++ ) 
					{
						System.out.printf("\t  %d    %s    %s  %s  %s\n", i+1,addressBook.personals[indexes[i]].name, 
								                                            addressBook.personals[indexes[i]].address, 
							                                                addressBook.personals[indexes[i]].telephoneNumber,
							                                                addressBook.personals[indexes[i]].emailAddress) ;
					}
				}
				else
				{
					System.out.printf("\t\t 주소록 내에 해당 정보가 없습니다!\n");
				}
			}
			System.out.printf("\t========================================================================\n") ;
			System.out.printf("\t계속하시겠습니까? (1.Yes/2.No) ") ; 
			
			going = scan.nextInt();
				
		}while ( going == 1 );
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
	}

	static void Correcting(AddressBook addressBook) 
	{	
		String name;
		String address;
		String telephoneNumber;
		String emailAddress;
		
		String temp;
		
	    int finding;
	    int correcting;
	    int going=1;		
		int index;		
		int[] indexes = new int[addressBook.length];
		int count;				
		int i;			
					
		while(going==1) 
		{
			System.out.printf("\n\n\n");
			System.out.printf("\t주소록 Version 0.05-수정하기\n");
			System.out.printf("\t========================================================================\n");
			
			System.out.printf("\t\t\t수정하고자 하는 사람의 성명을 입력 하세요\n");
			System.out.printf("\t------------------------------------------------------------------------\n");		
			System.out.printf("\t성    명 : "); 
			name= scan.next();
			System.out.printf("\t------------------------------------------------------------------------\n");		
			System.out.printf("\t수정하실 성명이 맞습니까? (1.Yes/2.No) "); 
			finding=scan.nextInt();
			
			if(finding == 1)
			{
				indexes = addressBook.Find(name);
				
				System.out.printf("\t========================================================================\n");
				
				if(indexes.length>0)
				{
					System.out.printf("\t번 호    성  명\t     주    소     \t 전화번호 \t 이메일주소\n");
					System.out.printf("\t--------------------------------------------------\n");	
					for(i=0; i<indexes.length ;i++)
					{	
						System.out.printf("\t  %d    %s    %s  %s  %s\n", i+1, addressBook.personals[indexes[i]].name,
						                                                   addressBook.personals[indexes[i]].address,
						                                                   addressBook.personals[indexes[i]].telephoneNumber, 
						                                                   addressBook.personals[indexes[i]].emailAddress);
					}
					
					
					System.out.printf("\t------------------------------------------------------------------------\n");	
					System.out.printf("\t변경할 번호를 입력하세요! ");
					index=scan.nextInt();
					
					System.out.printf("\t------------------------------------------------------------------------\n");
					temp=scan.nextLine();
					System.out.printf("\t주               소 : (%s)  =>  ", addressBook.personals[indexes[index-1]].address);
					address=scan.nextLine();
					System.out.printf("\t전 화  번 호  : (%s)  =>  ",	addressBook.personals[indexes[index-1]].telephoneNumber); 
					telephoneNumber=scan.nextLine();
					System.out.printf("\t이메일주소 : (%s)  =>  ", addressBook.personals[indexes[index-1]].emailAddress);
					emailAddress=scan.nextLine();
					System.out.printf("\t------------------------------------------------------------------------\n");	
					System.out.printf("\t변경 하시겠습니까? (1.yes/2.no) " );
					correcting=scan.nextInt();
					
					if(correcting == 1)
					{
						if(address.equals(""))
						{
							address=addressBook.personals[indexes[index-1]].address;
						}
						if(telephoneNumber.equals(""))
						{
							telephoneNumber=addressBook.personals[indexes[index-1]].telephoneNumber;
						}
						if(emailAddress.equals(""))
						{
						    emailAddress=addressBook.personals[indexes[index-1]].emailAddress;
						}
						
						index = addressBook.Correct( indexes[index-1], address, telephoneNumber, emailAddress);
						
						System.out.printf("\t------------------------------------------------------------------------\n");	
						System.out.printf("\t번 호    성  명\t     주    소     \t 전화번호 \t 이메일주소\n");
						System.out.printf("\t------------------------------------------------------------------------\n");	
						System.out.printf("\t  %d    %s    %s  %s  %s\n", index+1, addressBook.personals[index].name,
						                                                       addressBook.personals[index].address,
						                                                       addressBook.personals[index].telephoneNumber, 
						                                                       addressBook.personals[index].emailAddress);
					}
				}
				else
				{
					System.out.printf("\t\t 주소록 내에 해당 정보가 없습니다!\n");
				}
				System.out.printf("\t========================================================================\n");
				System.out.printf("\t\t계속하시겠습니까?(1.Yes/2.No) ");
				going=scan.nextInt();
				
			}
		}
	}
	
	
	static void Erasing(AddressBook addressBook) 
	{
		String name;

		int index;
		int going=1;
		int count;
		int[] indexes = new int[addressBook.length];
		int i;
		int erasing=1;
		int finding=1;
		
		
		do
		{
			System.out.printf("\n\n\n");
			System.out.printf("\t주소록 Version 0.05-삭제하기\n");
			System.out.printf("\t========================================================================\n");
			System.out.printf("\t\t\t삭제하고자 하는 사람의 성명을 입력 하세요\n");
			System.out.printf("\t------------------------------------------------------------------------\n");		
			System.out.printf("\t성    명 : "); 
			name= scan.next();
			System.out.printf("\t------------------------------------------------------------------------\n");		
			System.out.printf("\t삭제하실 성명이 맞습니까? (1.Yes/2.No) "); 
			finding=scan.nextInt();
			
			if ( finding == 1 ) 
			{
				indexes = addressBook.Find(name);
				
				System.out.printf ("\t========================================================================\n") ;
				
				if(indexes.length>0)
				{
		
					System.out.printf("\t번 호    성  명\t     주    소     \t 전화번호 \t 이메일주소\n");	
					System.out.printf("\t------------------------------------------------------------------------\n");	
					for(i=0; i<indexes.length ;i++)
					{	
						System.out.printf("\t  %d    %s    %s  %s  %s\n", i+1, addressBook.personals[indexes[i]].name,
						                                                   addressBook.personals[indexes[i]].address,
						                                                   addressBook.personals[indexes[i]].telephoneNumber, 
						                                                   addressBook.personals[indexes[i]].emailAddress);
					}
					
					System.out.printf("\t------------------------------------------------------------------------\n");	
					System.out.printf("\t삭제할 번호를 입력하세요! ");
					index=scan.nextInt();
					System.out.printf("\t------------------------------------------------------------------------\n");	
					System.out.printf("\t삭제 하시겠습니까? (1.yes/2.no) " );
					erasing=scan.nextInt();
					
					if(erasing==1)
					{
						index = addressBook.Erase(indexes[index-1]);
						
						System.out.printf("\n\n\n");
						System.out.printf("\t\t---------------------------------------------------\n");
						System.out.printf("\t\t\t성공적으로 삭제를 완료 하였습니다.\n");
						System.out.printf("\t\t---------------------------------------------------\n");
					}
				}
				else
				{
					System.out.printf("\t\t 주소록 내에 해당 정보가 없습니다!\n");	
				}
				System.out.printf("\n\n\n\t========================================================================\n");
				System.out.printf("\t\t계속하시겠습니까?(1.Yes/2.No) ");
				going=scan.nextInt();
				
			}
			
		}while(going==1);
	}

	static void Arranging(AddressBook addressBook) 
	{
		int view;
		
		System.out.printf("\n\n\n");
		System.out.printf("\t\t---------------------------------------------------\n");
		System.out.printf("\t\t\t\t정리하기\tversion 0.05\n");
		System.out.printf("\t\t---------------------------------------------------\n");
		
		addressBook.Arrange();
		System.out.printf("\n\t\t\t\t정리를 완료 하였습니다.\n");
		System.out.printf("\t\t\t정리된 결과를 보시겠습니까?(1.Yes/2.No)");
		
		view=scan.nextInt();
		
		if(view == 1)
		{
			ListView(addressBook);
		}
	}
	
	//	목록보기 함수
	public static void ListView(AddressBook addressBook)
	{
		int i;
		
		if ( addressBook.length != 0 ) 
		{
			System.out.printf("\n\n\n");
			System.out.printf("\t\t---------------------------------------------------\n");
			System.out.printf("\t\t\t목록보기\tversion 0.05\n");
			System.out.printf("\t\t---------------------------------------------------\n");
			for(i=0;i<addressBook.length;i++)
			{
				System.out.printf("\t\t---------------------------------------------------\n");
				System.out.printf("\t\t 이          름 : %s\n",addressBook.personals[i].name);
				System.out.printf("\t\t 주          소 : %s\n",addressBook.personals[i].address);
				System.out.printf("\t\t 전화번호 : %s\n",addressBook.personals[i].telephoneNumber);
				System.out.printf("\t\t 이  메   일 : %s\n",addressBook.personals[i].emailAddress);
				System.out.printf("\t\t---------------------------------------------------\n");
			}
		}
		else
		{
			System.out.printf("\n\n\n");
			System.out.printf("\t\t---------------------------------------------------\n");
			System.out.println("\t\t현재 주소록에는 등록된 사람이 없습니다.");
			System.out.printf("\t\t---------------------------------------------------\n");
		}

	}
}