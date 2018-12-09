class AddressBook 
{
	Personal[] personals;
	
	public int capacity;
	public int length;
	
	
	AddressBook() //AddressBook 생성자 메소드
	{
		personals = new Personal[100];
		capacity=100;
		length=0;
	}
	
	
	//기재함수
	public int Record(String name,String address, String telephoneNumber, String emailAddress) 
	{
		// 1.성명, 주소, 연락처, 이메일 주소를 입력 받는다.
		int index ;
		
		// 2.적을 위치를 정한다.
		index = this.length;
		
		// 3.입력 받은 자료들을 각 란에 적는다.
		this.personals[index]=new Personal(name,address,telephoneNumber,emailAddress);
		
		this.length++ ;
		
		// 4.위치를 출력한다.
		return index ;
		
		// 5.끝낸다.
	}
	
	//검색함수
	public int[] Find(String name) 
	{
		int i=0;
		int j=0;
		int count=0;
		
		while(i<this.length)
		{
		    if(this.personals[i].name.equals(name))
		    {
		        count++;
		    }
		    i++;
		}
		int[] indexes = new int[count];
		
		i=0;
		while(i<this.length)
		{
		    if(this.personals[i].name.equals(name))
		    {
		        indexes[j]=i;
		        j++;
		    }
		    i++;
		}
		
		return indexes ;
	}
	
	//수정함수
	public int Correct(int index, String address, String telephoneNumber, String emailAddress)
	{
		this.personals[index]= new Personal(this.personals[index].name,address,telephoneNumber,emailAddress);
		
		return index;
	}


	//삭제함수
	public int Erase(int index) 
	{
		Personal[] temp = new Personal[this.capacity];
		int count=0;
		
		
		for(int i=0; i<this.length; i++)
		{				//temp배열에 삭제할 데이터를 제외한 나머지를 넣는 작업
			if(i != index)
			{
				temp[count] = this.personals[i];
				count++;
			}
		}
		this.length--;
		
		//index가 -1일 경우 "삭제하였습니다" 라는 메세지 출력
		this.personals=temp;
		index = -1;
		
		return index;
	}
	
	//정리함수
	public void Arrange()
	{
		Personal temp;
		
		int i;
		int j;
		
		for(i=0;i<this.length-1;i++)
		{
			for(j=i+1;j<this.length;j++)
			{
				if( this.personals[i].name.compareTo(this.personals[j].name)>0)
				{
					temp = this.personals[i];
					this.personals[i]=this.personals[j];
					this.personals[j]=temp;
				}
			}
		}
	}
	
}

class Personal
{
	String name;
	String address;
	String telephoneNumber;
	String emailAddress;
	
	Personal(String name,String address,String telephoneNumber,String emailAddress)
	{
		this.name=name;
		this.address=address;
		this.telephoneNumber=telephoneNumber;
		this.emailAddress=emailAddress;
	}
}