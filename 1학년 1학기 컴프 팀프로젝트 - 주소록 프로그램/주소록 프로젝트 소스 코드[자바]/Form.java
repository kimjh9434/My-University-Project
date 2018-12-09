import java.util.Scanner;
//import java.io.*; 


public class Form 
{
	static Scanner scan = new Scanner(System.in);


	//�޴�ȭ�� �����ֱ�
	static void DisplayMenu ()
	{	
		System.out.println();
		System.out.println();
		
		System.out.printf("\t\t\t�ּҷ� ���� ���α׷� \n\n");

		System.out.printf("\t\t---------------------------------------------------\n");
		System.out.printf("\t\t\t��\t��\t��\tversion 0.05\n");
		System.out.printf("\t\t---------------------------------------------------\n");
		System.out.printf("\t\t\t1.\t��\t��\t��\t��\n");
		System.out.printf("\t\t\t2.\t��\t��\t��\t��\n");
		System.out.printf("\t\t\t3.\t��\t��\t��\t��\n");
		System.out.printf("\t\t\t4.\t��\t��\t��\t��\n");
		System.out.printf("\t\t\t5.\t��\t��\t��\t��\n");
		System.out.printf("\t\t\t6.\t��\t��\t��\t��\n");
		System.out.printf("\t\t\t0.\t��\t��\t��\t��\n");
		System.out.printf("\t\t---------------------------------------------------\n");
		System.out.printf("\t\t\t\t�޴���ȣ�� �����ϼ���! : ");
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
			System.out.printf("\t�ּҷ� Version 0.05-�����ϱ�\n") ;
			System.out.printf("\t========================================================================\n") ;
			System.out.printf("\t\t\t�����ϰ��� �ϴ� ����� �������� �Է� �ϼ��� \n");
			System.out.printf("\t------------------------------------------------------------------------\n");
			System.out.printf("\t��              �� : ");
			name=scan.nextLine();
			System.out.printf("\t��              �� : ");
			address=scan.nextLine();
			System.out.printf("\t�� ȭ  �� ȣ : ");
			telephoneNumber=scan.nextLine();
			System.out.printf("\t�̸����ּ�: ");
			emailAddress=scan.nextLine();
			System.out.printf("\t------------------------------------------------------------------------\n") ;
			System.out.printf("\t�����Ͻðڽ��ϱ�? (1.Yes/2.No) ");
			recording = scan.nextInt();
			
			
			
			if ( recording == 1 ) 
			{
				index = addressBook.Record( name, address, telephoneNumber, emailAddress);	
				System.out.printf("\t========================================================================\n");	
				System.out.printf("\t�� ȣ    ��  ��\t     ��    ��     \t ��ȭ��ȣ \t �̸����ּ�\n");
				System.out.printf("\t------------------------------------------------------------------------\n");	
				System.out.printf("\t  %d    %s    %s  %s  %s\n", index+1, 
						                                          addressBook.personals[index].name,	
				                                                  addressBook.personals[index].address,	
				                                                  addressBook.personals[index].telephoneNumber, 	
				                                                  addressBook.personals[index].emailAddress);	
			}
			System.out.printf("\t========================================================================\n") ;
			System.out.printf("\t����Ͻðڽ��ϱ�? (1.Yes/2.No) ") ;
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
			System.out.printf("\t�ּҷ� Version 0.05-�˻��ϱ�\n");
			System.out.printf("\t========================================================================\n");
			System.out.printf("\t\t\tã���ð��� �ϴ� ����� ������ �Է� �ϼ��� \n");
			System.out.printf("\t------------------------------------------------------------------------\n");
			System.out.printf("\t��    ��: ");
			name=scan.next();
			
			System.out.printf("\t------------------------------------------------------------------------\n") ;
			System.out.printf("\tã���ô� ������ �½��ϱ�? (1.Yes/2.No) ");
			finding = scan.nextInt();
			
			if ( finding == 1 ) 
			{
				indexes = addressBook.Find(name);
				
				System.out.printf("\t========================================================================\n") ;
				
				if(indexes.length>0)
				{
					System.out.printf ("\t�� ȣ    ��  ��\t     ��    ��     \t ��ȭ��ȣ \t �̸����ּ�\n") ;
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
					System.out.printf("\t\t �ּҷ� ���� �ش� ������ �����ϴ�!\n");
				}
			}
			System.out.printf("\t========================================================================\n") ;
			System.out.printf("\t����Ͻðڽ��ϱ�? (1.Yes/2.No) ") ; 
			
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
			System.out.printf("\t�ּҷ� Version 0.05-�����ϱ�\n");
			System.out.printf("\t========================================================================\n");
			
			System.out.printf("\t\t\t�����ϰ��� �ϴ� ����� ������ �Է� �ϼ���\n");
			System.out.printf("\t------------------------------------------------------------------------\n");		
			System.out.printf("\t��    �� : "); 
			name= scan.next();
			System.out.printf("\t------------------------------------------------------------------------\n");		
			System.out.printf("\t�����Ͻ� ������ �½��ϱ�? (1.Yes/2.No) "); 
			finding=scan.nextInt();
			
			if(finding == 1)
			{
				indexes = addressBook.Find(name);
				
				System.out.printf("\t========================================================================\n");
				
				if(indexes.length>0)
				{
					System.out.printf("\t�� ȣ    ��  ��\t     ��    ��     \t ��ȭ��ȣ \t �̸����ּ�\n");
					System.out.printf("\t--------------------------------------------------\n");	
					for(i=0; i<indexes.length ;i++)
					{	
						System.out.printf("\t  %d    %s    %s  %s  %s\n", i+1, addressBook.personals[indexes[i]].name,
						                                                   addressBook.personals[indexes[i]].address,
						                                                   addressBook.personals[indexes[i]].telephoneNumber, 
						                                                   addressBook.personals[indexes[i]].emailAddress);
					}
					
					
					System.out.printf("\t------------------------------------------------------------------------\n");	
					System.out.printf("\t������ ��ȣ�� �Է��ϼ���! ");
					index=scan.nextInt();
					
					System.out.printf("\t------------------------------------------------------------------------\n");
					temp=scan.nextLine();
					System.out.printf("\t��               �� : (%s)  =>  ", addressBook.personals[indexes[index-1]].address);
					address=scan.nextLine();
					System.out.printf("\t�� ȭ  �� ȣ  : (%s)  =>  ",	addressBook.personals[indexes[index-1]].telephoneNumber); 
					telephoneNumber=scan.nextLine();
					System.out.printf("\t�̸����ּ� : (%s)  =>  ", addressBook.personals[indexes[index-1]].emailAddress);
					emailAddress=scan.nextLine();
					System.out.printf("\t------------------------------------------------------------------------\n");	
					System.out.printf("\t���� �Ͻðڽ��ϱ�? (1.yes/2.no) " );
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
						System.out.printf("\t�� ȣ    ��  ��\t     ��    ��     \t ��ȭ��ȣ \t �̸����ּ�\n");
						System.out.printf("\t------------------------------------------------------------------------\n");	
						System.out.printf("\t  %d    %s    %s  %s  %s\n", index+1, addressBook.personals[index].name,
						                                                       addressBook.personals[index].address,
						                                                       addressBook.personals[index].telephoneNumber, 
						                                                       addressBook.personals[index].emailAddress);
					}
				}
				else
				{
					System.out.printf("\t\t �ּҷ� ���� �ش� ������ �����ϴ�!\n");
				}
				System.out.printf("\t========================================================================\n");
				System.out.printf("\t\t����Ͻðڽ��ϱ�?(1.Yes/2.No) ");
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
			System.out.printf("\t�ּҷ� Version 0.05-�����ϱ�\n");
			System.out.printf("\t========================================================================\n");
			System.out.printf("\t\t\t�����ϰ��� �ϴ� ����� ������ �Է� �ϼ���\n");
			System.out.printf("\t------------------------------------------------------------------------\n");		
			System.out.printf("\t��    �� : "); 
			name= scan.next();
			System.out.printf("\t------------------------------------------------------------------------\n");		
			System.out.printf("\t�����Ͻ� ������ �½��ϱ�? (1.Yes/2.No) "); 
			finding=scan.nextInt();
			
			if ( finding == 1 ) 
			{
				indexes = addressBook.Find(name);
				
				System.out.printf ("\t========================================================================\n") ;
				
				if(indexes.length>0)
				{
		
					System.out.printf("\t�� ȣ    ��  ��\t     ��    ��     \t ��ȭ��ȣ \t �̸����ּ�\n");	
					System.out.printf("\t------------------------------------------------------------------------\n");	
					for(i=0; i<indexes.length ;i++)
					{	
						System.out.printf("\t  %d    %s    %s  %s  %s\n", i+1, addressBook.personals[indexes[i]].name,
						                                                   addressBook.personals[indexes[i]].address,
						                                                   addressBook.personals[indexes[i]].telephoneNumber, 
						                                                   addressBook.personals[indexes[i]].emailAddress);
					}
					
					System.out.printf("\t------------------------------------------------------------------------\n");	
					System.out.printf("\t������ ��ȣ�� �Է��ϼ���! ");
					index=scan.nextInt();
					System.out.printf("\t------------------------------------------------------------------------\n");	
					System.out.printf("\t���� �Ͻðڽ��ϱ�? (1.yes/2.no) " );
					erasing=scan.nextInt();
					
					if(erasing==1)
					{
						index = addressBook.Erase(indexes[index-1]);
						
						System.out.printf("\n\n\n");
						System.out.printf("\t\t---------------------------------------------------\n");
						System.out.printf("\t\t\t���������� ������ �Ϸ� �Ͽ����ϴ�.\n");
						System.out.printf("\t\t---------------------------------------------------\n");
					}
				}
				else
				{
					System.out.printf("\t\t �ּҷ� ���� �ش� ������ �����ϴ�!\n");	
				}
				System.out.printf("\n\n\n\t========================================================================\n");
				System.out.printf("\t\t����Ͻðڽ��ϱ�?(1.Yes/2.No) ");
				going=scan.nextInt();
				
			}
			
		}while(going==1);
	}

	static void Arranging(AddressBook addressBook) 
	{
		int view;
		
		System.out.printf("\n\n\n");
		System.out.printf("\t\t---------------------------------------------------\n");
		System.out.printf("\t\t\t\t�����ϱ�\tversion 0.05\n");
		System.out.printf("\t\t---------------------------------------------------\n");
		
		addressBook.Arrange();
		System.out.printf("\n\t\t\t\t������ �Ϸ� �Ͽ����ϴ�.\n");
		System.out.printf("\t\t\t������ ����� ���ðڽ��ϱ�?(1.Yes/2.No)");
		
		view=scan.nextInt();
		
		if(view == 1)
		{
			ListView(addressBook);
		}
	}
	
	//	��Ϻ��� �Լ�
	public static void ListView(AddressBook addressBook)
	{
		int i;
		
		if ( addressBook.length != 0 ) 
		{
			System.out.printf("\n\n\n");
			System.out.printf("\t\t---------------------------------------------------\n");
			System.out.printf("\t\t\t��Ϻ���\tversion 0.05\n");
			System.out.printf("\t\t---------------------------------------------------\n");
			for(i=0;i<addressBook.length;i++)
			{
				System.out.printf("\t\t---------------------------------------------------\n");
				System.out.printf("\t\t ��          �� : %s\n",addressBook.personals[i].name);
				System.out.printf("\t\t ��          �� : %s\n",addressBook.personals[i].address);
				System.out.printf("\t\t ��ȭ��ȣ : %s\n",addressBook.personals[i].telephoneNumber);
				System.out.printf("\t\t ��  ��   �� : %s\n",addressBook.personals[i].emailAddress);
				System.out.printf("\t\t---------------------------------------------------\n");
			}
		}
		else
		{
			System.out.printf("\n\n\n");
			System.out.printf("\t\t---------------------------------------------------\n");
			System.out.println("\t\t���� �ּҷϿ��� ��ϵ� ����� �����ϴ�.");
			System.out.printf("\t\t---------------------------------------------------\n");
		}

	}
}