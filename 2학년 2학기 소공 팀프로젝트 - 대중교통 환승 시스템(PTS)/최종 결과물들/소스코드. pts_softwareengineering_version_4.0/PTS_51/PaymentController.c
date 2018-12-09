//PaymentController.c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>     //printf
#include <stdlib.h>    //fscanf, fprint
#include "PublicTransportation.h"
#include "PaymentController.h"

extern State state;

/*
Payment_Controller
1. Result() 호출
===정상일경우===
2. Card_Update() 호출
3. Card_Reader_Record() 호출
*/
void Payment_Controller(Card_info* card_info, int price, Time tagTime)
{
	Result(card_info, price, tagTime);//디스플레이(화면, 모니터)에 결과를 보여준다.
	
	if(state==Normal)//지금까지 정상처리되어 왔다면, 진행한다.
	{
		Card_Update(card_info);//카드에 정보를 갱신한다.
		Card_Reader_Record(card_info, price);//단말기에 정보를 기록한다.
	}
}

/*
Result
1. 정상 : update된 부과금액, 카드 잔액, 현재 시간을 출력
2. 에러 : 해당 statment 출력
3. Display_Interface() 호출
*/
void Result(Card_info* card_info, int price, Time tagTime)
{
	printf("태그 한   시각 : %04d년 %02d월 %02d일 %02d시 %02d분 %02d초 \n", card_info->tagTime.year, card_info->tagTime.mon, card_info->tagTime.day,
	card_info->tagTime.hour, card_info->tagTime.min, card_info->tagTime.sec);

	switch (state)
	{
	case Normal: //정상처리
		if (card_info->transfer == 1){				//환승의 경우 
			price=0;
		}
		printf("태그시 부과금액: %d \n", price);
		printf("태그 후의 잔액 : %d \n", card_info->cash);
		break;
	case HopInProcessing: //승차처리
		printf("이미 승차처리가 된 카드입니다.\n");
		break;
	case GetOffProcessing: //하차처리
		printf("이미 하차처리가 된 카드입니다.\n");
		break;
	case Short: //잔액부족
		printf("현재의 잔액 : %d \n", card_info->cash);
		printf("필요한 잔액: %d \n", price);
		printf("잔액이 부족합니다.\n");
		break;
	case NotAdjust: //미정산
		printf("아직 정산이 완료되지 않았습니다. \n");
		break;
	case InvalidInput: //잘못된 입력
		printf("유효하지 않은, 잘못된 CID값을 입력했습니다. \n");
	default: break;
	}

	Display_Interface(card_info);
}
/*
Display_Interface
*/
void Display_Interface(Card_info* card_info)
{
}

/*
Card_Update
1. update된 카드 정보를 파일에 저장
2. Card_Interface() 호출
*/
void Card_Update(Card_info* card_info)
{
	FILE *file, *newFile;//카드 택스트 파일의 포인터
	char buf[1024];       //맨 처음의 불필요한 한줄을 읽기위한 임시변수
	Card_info temp_card_info;//카드 택스트 파일로부터 한줄씩 읽을 때, 저장해둘 임시변수

	file = fopen("./../Card.txt", "r");//파일 열기
	newFile = fopen("./../newCard.txt", "w");//파일 열기
	
	if(file != NULL && newFile!= NULL)//파일이 잘 열렸는지 확인한다.
	{
		fgets(buf,1024,file);//불필요한 한줄을 읽는다.
		fputs(buf, newFile);
		//카드 택스트 파일로부터 한줄을 읽는다.
		fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
		   &temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
		   &temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
		   &temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);

		while(!feof(file))//카드 택스트 파일로부터 파일의 끝이 아닌동안 반복한다.
		{
			if(card_info->CID == temp_card_info.CID)//입력받은 CID값이라면,
			{
				//새로얻은 카드 정보를 갱신하고,
				fprintf(newFile, "%d\t%d\t%d %d %d %d %d %d\t%d\t%d\t%d\t%d\t%d\n", card_info->CID, card_info->CRID, 
					card_info->tagTime.year, card_info->tagTime.mon, card_info->tagTime.day, card_info->tagTime.hour,
					card_info->tagTime.min, card_info->tagTime.sec, 
					card_info->tp, card_info->state, card_info->cash, card_info->transfer, card_info->getout);

			}else//입력받은 CID값이 아니라면,
			{
				//기존 카드의 정보를 유지한다.
				fprintf(newFile, "%d\t%d\t%d %d %d %d %d %d\t%d\t%d\t%d\t%d\t%d\n", temp_card_info.CID, temp_card_info.CRID, 
					temp_card_info.tagTime.year, temp_card_info.tagTime.mon, temp_card_info.tagTime.day, temp_card_info.tagTime.hour,
					temp_card_info.tagTime.min, temp_card_info.tagTime.sec, 
					temp_card_info.tp, temp_card_info.state, temp_card_info.cash, temp_card_info.transfer, temp_card_info.getout);
			}
			//카드 택스트 파일로부터 한줄을 읽는다.
			fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			   &temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
			   &temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
			   &temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);
		}
		fclose(newFile);//파일 닫기
		fclose(file);//파일 닫기

		remove("./../Card.txt");//기존 카드 택스트 파일은 제거하고,
		rename("./../newCard.txt","./../Card.txt");//새로운 카드택스트 파일의 이름을 카드 택스트 파일로 변경한다.
	}else
	{
		printf("파일이 없습니다.  파일 열기 실패\n");
		exit(1); 
	}
	Card_Interface(card_info);
}

/*
Card_Interface
*/
void Card_Interface(Card_info* card_info)
{
}

/*
Card_Reader_Record
1. 카드 단말기에 부가된 요금을 저장한다.
2. Card_Reader_Recording_Interface() 호출
*/
void Card_Reader_Record(Card_info* card_info, int price)
{
	FILE *file;//단말기 파일의 포인터

	file = fopen("./../Card_Reader_5.txt", "a");//동대문역사문화공원역 단말기 파일 열기
	
	if(file != NULL)//파일이 잘 열렸는지 확인한다.
	{
		if (card_info->transfer == 1){	//환승의 경우 
			price=0;
		}
		//단말기에 새로얻은 카드 정보를 기록한다.
		fprintf(file, "%d\t%d\t%d %d %d %d %d %d\t%d\t%d\t%d\t%d\t%d\n", card_info->CID, card_info->CRID,
					card_info->tagTime.year, card_info->tagTime.mon, card_info->tagTime.day, card_info->tagTime.hour,
					card_info->tagTime.min, card_info->tagTime.sec, 
					card_info->tp, card_info->state, price, card_info->transfer, card_info->getout);
		fclose(file);//파일 닫기
	}else
	{
		printf("파일이 없습니다.  파일 열기 실패\n");
		exit(1); 
	}

	Card_Reader_Recording_Interface(card_info);
}

/*
Card_Reader_Recording_Interface
*/
void Card_Reader_Recording_Interface(Card_info* card_info)
{
}
