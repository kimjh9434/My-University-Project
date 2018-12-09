class AddressBook 
{
	Personal[] personals;
	
	public int capacity;
	public int length;
	
	
	AddressBook() //AddressBook ������ �޼ҵ�
	{
		personals = new Personal[100];
		capacity=100;
		length=0;
	}
	
	
	//�����Լ�
	public int Record(String name,String address, String telephoneNumber, String emailAddress) 
	{
		// 1.����, �ּ�, ����ó, �̸��� �ּҸ� �Է� �޴´�.
		int index ;
		
		// 2.���� ��ġ�� ���Ѵ�.
		index = this.length;
		
		// 3.�Է� ���� �ڷ���� �� ���� ���´�.
		this.personals[index]=new Personal(name,address,telephoneNumber,emailAddress);
		
		this.length++ ;
		
		// 4.��ġ�� ����Ѵ�.
		return index ;
		
		// 5.������.
	}
	
	//�˻��Լ�
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
	
	//�����Լ�
	public int Correct(int index, String address, String telephoneNumber, String emailAddress)
	{
		this.personals[index]= new Personal(this.personals[index].name,address,telephoneNumber,emailAddress);
		
		return index;
	}


	//�����Լ�
	public int Erase(int index) 
	{
		Personal[] temp = new Personal[this.capacity];
		int count=0;
		
		
		for(int i=0; i<this.length; i++)
		{				//temp�迭�� ������ �����͸� ������ �������� �ִ� �۾�
			if(i != index)
			{
				temp[count] = this.personals[i];
				count++;
			}
		}
		this.length--;
		
		//index�� -1�� ��� "�����Ͽ����ϴ�" ��� �޼��� ���
		this.personals=temp;
		index = -1;
		
		return index;
	}
	
	//�����Լ�
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