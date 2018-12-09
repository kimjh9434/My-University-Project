//PTS.c
/*
파일이름 : Recharger.c
기    능 : 3개의 시스템중 교통카드 충전 시스템(Recharger System)을 담당한 코드이다.

작 성 자 : 김제헌
작성일자 : 2014-10-31-금 코딩 시작 ~ 2014-11-03-월 기본적인 토대 완성

부연설명 : 일단 설계를 바탕으로 막코딩 시작, 
           2014-11-20 : 보고서 양식과 맞게 수정.
*/
#include <stdio.h>     //printf
#include <stdlib.h>    //fscanf, fprintf
#include <Windows.h>   //system("cls");
#include <time.h>	   //time, localtime
#include "Recharger.h"


//RS 메인 함수
int main(int argc, char* argv[])
{
	char going='y';//프로그램 진행여부. 'y':계속진행, 'n':프로그램 종료

	while(going!='N' && going!='n')//사용자가 'N'또는 'n'을 입력하지 않는동안 반복한다.
	{
		//system("clear");
                printf("\n\n\n");
		printf("SoftwareEngineering Team Project \n");
		printf("RS - Recharger System \n");

		Recharger_Controller();//입력받은 Card Info, money Data를 종합하여 충전 계산을 한 뒤, 적절한 Update와 Display를 실행 해준다.

		printf("\n\n 계속 충전하시겠습니까? Y(Yes), N(No) : ");
		going=getchar(); getchar();
	}
	printf("\n\n  프로그램을 종료합니다.\n\n\n");
	
	return 0;
}

//입력받은 Card Info, money Data를 종합하여 충전 계산을 한 뒤, 적절한 Update와 Display를 실행 해준다.
void Recharger_Controller()
{	
	Card_info card_info;//카드의 정보
	int money=0;//충전기계에 투입된, Card에 충전할 현금을 감지해서 INT 형태로 전달할 정보

	Card_Info_Loader(&card_info);//CID를 사용해서 Card Info를 불러온다.
	money=Money_Sensor_Interface(); //투입된 현금을 인지해서 정수형으로 받는다.

	card_info.cash+=money;//카드의 잔액을 충전시킨다.

	
	Update(&card_info);     //충전된 정보로 교통카드를 갱신한다.
	Display(&card_info, money);//교통카드에 충전된 정보를 Monitor에 보여준다.
}

//CID를 사용해서 Card Info를 불러와 Recharger Control 에 전달한다.
void Card_Info_Loader(Card_info* card_info)
{
	FILE *file;              //카드 택스트 파일의 포인터
	char buf[1024];          //맨 처음의 불필요한 한줄을 읽기위한 임시변수
	Card_info temp_card_info;//카드 택스트 파일로부터 한줄씩 읽을 때, 저장해둘 임시변수

	card_info->CID=Card_ID_Receive_Interface();//태그된 카드의 CID값을 입력받는다.

	file = fopen("./../Card.txt", "r");//파일 열기

	if(file != NULL)//파일이 잘 열렸는지 확인한다.
	{
		fgets(buf,1024,file);//불필요한 한줄을 읽는다.
		//카드 택스트 파일로부터 한줄을 읽는다.
		fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
			&temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);

		//카드 택스트 파일로부터 파일의 끝이 아니거나, 입력받은 CID값을 못찾은 동안 반복한다.
		while(!feof(file) && card_info->CID != temp_card_info.CID)
		{
			//카드 택스트 파일로부터 한줄을 읽는다.
			fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
				&temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
				&temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
				&temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);
		}
		if(card_info->CID == temp_card_info.CID)//입력받은 CID값을 찾았는지 확인한다.
		{
			//카드로부터 가장 최근 기록들을 읽어온다.

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
		else//입력받은 CID값을 찾지 못했을 경우, 즉 잘못된 CID값을 입력받았을 경우
		{
			card_info->CID=0;
			card_info->CRID=0;
			card_info->cash=0;
			card_info->getout=0;
			card_info->state=0;
			card_info->tp=0;
			card_info->transfer=0;
		}
		fclose(file);//파일 닫기
	}
	else
	{
		printf("파일이 없습니다.  파일 열기 실패\n");
		exit(1); 
	}
}

//Recharger Sensor로부터 받은 아날로그 신호를 디지털 신호로 변환한다. 출력값은 CID
int Card_ID_Receive_Interface()
{
	int CID;  //충전기계에 감지된 Card의 ID를 INT 형태로 전달할 정보
	printf("충전하실 카드의 ID를 입력해 주세요. : ");
	CID=Get_Number();

	printf("\nCID : %d \n", CID);

	return CID;
}

//Money Sensor로부터 받은 아날로그 신호를 디지털 신호로 변환한다. 출력값은 Money
int Money_Sensor_Interface()
{
	int money;//충전기계에 투입된, Card에 충전할 현금을 감지해서 INT 형태로 전달할 정보
	printf("카드에 충전하실 금액을 입력해 주세요. : ");
	money=Get_Number();
	return money;
}

//충전된 정보로 교통카드를 갱신한다.
void Update(Card_info* card_info)
{
	FILE *file, *newFile;
	char buf[1024];
	Card_info temp_card_info;

	file = fopen("./../Card.txt", "r");//파일 열기
	newFile = fopen("./../newCard.txt", "w");//파일 열기
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
		fclose(newFile);//파일 닫기
		fclose(file);//파일 닫기

		remove("./../Card.txt");
		rename("./../newCard.txt","./../Card.txt");
	}else
	{
		printf("파일이 없습니다.  파일 열기 실패\n");
		exit(1); 
	}

	Update_Interface(card_info);//update card data를 받아서 Card 정보를 Update시키는 정보를 보내준다
}

//교통카드에 충전된 정보를 Monitor에 보여준다.
void Display(Card_info* card_info, int money)
{
	time_t timer;
	struct tm *t;
	timer = time(NULL);
	t = localtime(&timer);
	
	printf("충전 한   시각 : %04d년 %02d월 %02d일 %02d시 %02d분 %02d초 \n", t->tm_year + 1900,  t->tm_mon + 1, t->tm_mday, t->tm_hour, t->tm_min, t->tm_sec);
	printf("충전 전의 금액 : %d 원\n", card_info->cash-money);
	printf("충전 한   금액 : %d 원\n", money);
	printf("충전 후의 금액 : %d 원\n", card_info->cash);

	Display_Interface(card_info);//display data를 받아서 Monitor에 출력할 display정보를 보내준다.
}

//update card data를 받아서 Card 정보를 Update시키는 정보를 보내준다
void Update_Interface(Card_info* card_info)
{
	//printf("update card data를 받아서 Card로 보내준다");
}

//display data를 받아서 Monitor에 출력할 display정보를 보내준다.
void Display_Interface(Card_info* card_info)
{
	//printf("display data를 받아서 Monitor로 보내준다.");
}

//최대 10자리의 정수를 입력받을수 있는 함수
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
	fflush(stdin); //현재 버퍼 비우기
	return number;
}