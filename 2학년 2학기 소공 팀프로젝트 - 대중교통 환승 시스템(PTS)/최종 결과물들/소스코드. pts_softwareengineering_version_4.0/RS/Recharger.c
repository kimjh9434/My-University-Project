//PTS.c
/*
�����̸� : Recharger.c
��    �� : 3���� �ý����� ����ī�� ���� �ý���(Recharger System)�� ����� �ڵ��̴�.

�� �� �� : ������
�ۼ����� : 2014-10-31-�� �ڵ� ���� ~ 2014-11-03-�� �⺻���� ��� �ϼ�

�ο����� : �ϴ� ���踦 �������� ���ڵ� ����, 
           2014-11-20 : ���� ��İ� �°� ����.
*/
#include <stdio.h>     //printf
#include <stdlib.h>    //fscanf, fprintf
#include <Windows.h>   //system("cls");
#include <time.h>	   //time, localtime
#include "Recharger.h"


//RS ���� �Լ�
int main(int argc, char* argv[])
{
	char going='y';//���α׷� ���࿩��. 'y':�������, 'n':���α׷� ����

	while(going!='N' && going!='n')//����ڰ� 'N'�Ǵ� 'n'�� �Է����� �ʴµ��� �ݺ��Ѵ�.
	{
		//system("clear");
                printf("\n\n\n");
		printf("SoftwareEngineering Team Project \n");
		printf("RS - Recharger System \n");

		Recharger_Controller();//�Է¹��� Card Info, money Data�� �����Ͽ� ���� ����� �� ��, ������ Update�� Display�� ���� ���ش�.

		printf("\n\n ��� �����Ͻðڽ��ϱ�? Y(Yes), N(No) : ");
		going=getchar(); getchar();
	}
	printf("\n\n  ���α׷��� �����մϴ�.\n\n\n");
	
	return 0;
}

//�Է¹��� Card Info, money Data�� �����Ͽ� ���� ����� �� ��, ������ Update�� Display�� ���� ���ش�.
void Recharger_Controller()
{	
	Card_info card_info;//ī���� ����
	int money=0;//������迡 ���Ե�, Card�� ������ ������ �����ؼ� INT ���·� ������ ����

	Card_Info_Loader(&card_info);//CID�� ����ؼ� Card Info�� �ҷ��´�.
	money=Money_Sensor_Interface(); //���Ե� ������ �����ؼ� ���������� �޴´�.

	card_info.cash+=money;//ī���� �ܾ��� ������Ų��.

	
	Update(&card_info);     //������ ������ ����ī�带 �����Ѵ�.
	Display(&card_info, money);//����ī�忡 ������ ������ Monitor�� �����ش�.
}

//CID�� ����ؼ� Card Info�� �ҷ��� Recharger Control �� �����Ѵ�.
void Card_Info_Loader(Card_info* card_info)
{
	FILE *file;              //ī�� �ý�Ʈ ������ ������
	char buf[1024];          //�� ó���� ���ʿ��� ������ �б����� �ӽú���
	Card_info temp_card_info;//ī�� �ý�Ʈ ���Ϸκ��� ���پ� ���� ��, �����ص� �ӽú���

	card_info->CID=Card_ID_Receive_Interface();//�±׵� ī���� CID���� �Է¹޴´�.

	file = fopen("./../Card.txt", "r");//���� ����

	if(file != NULL)//������ �� ���ȴ��� Ȯ���Ѵ�.
	{
		fgets(buf,1024,file);//���ʿ��� ������ �д´�.
		//ī�� �ý�Ʈ ���Ϸκ��� ������ �д´�.
		fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
			&temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);

		//ī�� �ý�Ʈ ���Ϸκ��� ������ ���� �ƴϰų�, �Է¹��� CID���� ��ã�� ���� �ݺ��Ѵ�.
		while(!feof(file) && card_info->CID != temp_card_info.CID)
		{
			//ī�� �ý�Ʈ ���Ϸκ��� ������ �д´�.
			fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
				&temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
				&temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
				&temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);
		}
		if(card_info->CID == temp_card_info.CID)//�Է¹��� CID���� ã�Ҵ��� Ȯ���Ѵ�.
		{
			//ī��κ��� ���� �ֱ� ��ϵ��� �о�´�.

			card_info->CID = temp_card_info.CID;
			card_info->CRID = temp_card_info.CRID;

			card_info->tagTime.year = temp_card_info.tagTime.year;
			card_info->tagTime.mon = temp_card_info.tagTime.mon;
			card_info->tagTime.day = temp_card_info.tagTime.day;
			card_info->tagTime.hour = temp_card_info.tagTime.hour;
			card_info->tagTime.min = temp_card_info.tagTime.min;
			card_info->tagTime.sec = temp_card_info.tagTime.sec;

			card_info->tp = temp_card_info.tp;
			card_info->state = temp_card_info.state;
			card_info->cash = temp_card_info.cash;
			card_info->transfer = temp_card_info.transfer;
			card_info->getout = temp_card_info.getout;
		}
		else//�Է¹��� CID���� ã�� ������ ���, �� �߸��� CID���� �Է¹޾��� ���
		{
			card_info->CID=0;
			card_info->CRID=0;
			card_info->cash=0;
			card_info->getout=0;
			card_info->state=0;
			card_info->tp=0;
			card_info->transfer=0;
		}
		fclose(file);//���� �ݱ�
	}
	else
	{
		printf("������ �����ϴ�.  ���� ���� ����\n");
		exit(1); 
	}
}

//Recharger Sensor�κ��� ���� �Ƴ��α� ��ȣ�� ������ ��ȣ�� ��ȯ�Ѵ�. ��°��� CID
int Card_ID_Receive_Interface()
{
	int CID;  //������迡 ������ Card�� ID�� INT ���·� ������ ����
	printf("�����Ͻ� ī���� ID�� �Է��� �ּ���. : ");
	CID=Get_Number();

	printf("\nCID : %d \n", CID);

	return CID;
}

//Money Sensor�κ��� ���� �Ƴ��α� ��ȣ�� ������ ��ȣ�� ��ȯ�Ѵ�. ��°��� Money
int Money_Sensor_Interface()
{
	int money;//������迡 ���Ե�, Card�� ������ ������ �����ؼ� INT ���·� ������ ����
	printf("ī�忡 �����Ͻ� �ݾ��� �Է��� �ּ���. : ");
	money=Get_Number();
	return money;
}

//������ ������ ����ī�带 �����Ѵ�.
void Update(Card_info* card_info)
{
	FILE *file, *newFile;
	char buf[1024];
	Card_info temp_card_info;

	file = fopen("./../Card.txt", "r");//���� ����
	newFile = fopen("./../newCard.txt", "w");//���� ����
	if(file != NULL && newFile!= NULL)
	{
		fgets(buf,1024,file);
		fputs(buf,newFile);
		fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
				&temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
				&temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
				&temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);

		while(!feof(file))
		{
			if(card_info->CID == temp_card_info.CID)
			{
				fprintf(newFile, "%d\t%d\t%d %d %d %d %d %d\t%d\t%d\t%d\t%d\t%d\n", card_info->CID, card_info->CRID, 
					card_info->tagTime.year, card_info->tagTime.mon, card_info->tagTime.day, card_info->tagTime.hour,
					card_info->tagTime.min, card_info->tagTime.sec, 
					card_info->tp, card_info->state, card_info->cash, card_info->transfer, card_info->getout);
			}
			else
			{
				fprintf(newFile, "%d\t%d\t%d %d %d %d %d %d\t%d\t%d\t%d\t%d\t%d\n", temp_card_info.CID, temp_card_info.CRID, 
					temp_card_info.tagTime.year, temp_card_info.tagTime.mon, temp_card_info.tagTime.day, temp_card_info.tagTime.hour,
					temp_card_info.tagTime.min, temp_card_info.tagTime.sec, 
					temp_card_info.tp, temp_card_info.state, temp_card_info.cash, temp_card_info.transfer, temp_card_info.getout);
			}
			fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID,
				&temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
				&temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
				&temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);
		}
		fclose(newFile);//���� �ݱ�
		fclose(file);//���� �ݱ�

		remove("./../Card.txt");
		rename("./../newCard.txt","./../Card.txt");
	}else
	{
		printf("������ �����ϴ�.  ���� ���� ����\n");
		exit(1); 
	}

	Update_Interface(card_info);//update card data�� �޾Ƽ� Card ������ Update��Ű�� ������ �����ش�
}

//����ī�忡 ������ ������ Monitor�� �����ش�.
void Display(Card_info* card_info, int money)
{
	time_t timer;
	struct tm *t;
	timer = time(NULL);
	t = localtime(&timer);
	
	printf("���� ��   �ð� : %04d�� %02d�� %02d�� %02d�� %02d�� %02d�� \n", t->tm_year + 1900,  t->tm_mon + 1, t->tm_mday, t->tm_hour, t->tm_min, t->tm_sec);
	printf("���� ���� �ݾ� : %d ��\n", card_info->cash-money);
	printf("���� ��   �ݾ� : %d ��\n", money);
	printf("���� ���� �ݾ� : %d ��\n", card_info->cash);

	Display_Interface(card_info);//display data�� �޾Ƽ� Monitor�� ����� display������ �����ش�.
}

//update card data�� �޾Ƽ� Card ������ Update��Ű�� ������ �����ش�
void Update_Interface(Card_info* card_info)
{
	//printf("update card data�� �޾Ƽ� Card�� �����ش�");
}

//display data�� �޾Ƽ� Monitor�� ����� display������ �����ش�.
void Display_Interface(Card_info* card_info)
{
	//printf("display data�� �޾Ƽ� Monitor�� �����ش�.");
}

//�ִ� 10�ڸ��� ������ �Է¹����� �ִ� �Լ�
int Get_Number()
{
	char s[11]="";
	int i=0;
	char c='0';
	int number=0;
	int minus=0;

	c=getchar();
	if(c=='-'){
		minus=1;
	}
	else if(c>='0' && c<='9'){
		s[i]=c;
		i++;
	}
	while(i<10 && c!='\n')
	{
		c=getchar();
		if(c>='0' && c<='9'){
			s[i]=c;
			i++;
		}
	}
	s[i]=0;

	//number=Atoi(s);
	i=0;
	while(s[i]!=0)
	{
		number=number*10+(s[i]-'0');
		i++;
	}
	if(minus){
		number*=-1;
	}
	fflush(stdin); //���� ���� ����
	return number;
}