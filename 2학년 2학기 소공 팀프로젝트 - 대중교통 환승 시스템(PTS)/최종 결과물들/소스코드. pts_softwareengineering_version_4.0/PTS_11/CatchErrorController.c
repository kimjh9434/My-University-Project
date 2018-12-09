//CatchErrorController.c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>     //printf
#include <stdlib.h>    //fscanf, fprint
#include <time.h>	   //time, localtime
#include "PublicTransportation.h"
#include "CatchErrorController.h"

extern State state;

/*
Catch_Error_Controller
1. Catch_Adjust() 호출
2. Card_Info_Loader() 호출
3. Error() 호출 -> FixPrice() 호출
*/
void Catch_Error_Controller(Card_info* card_info, int* price , int CRID, Time* tagTime)
{
	if(Catch_Adjust())//정산중인지 확인하고, 정산중이 아니라면 동작한다.
	{
		Card_Info_Loader(card_info, tagTime);//최근에 태그된 카드의 정보를 읽어온다.

		if(Catch_Error(*tagTime, card_info , CRID))//승차승차, 하차하차 에러를 잡아내고, 정상처리라면 동작한다.
		{
			Fix_Price(card_info, *tagTime, price, CRID);//읽어온 Card의 정보를 통해 price를 정한다.
		}
	}
}

/*
Catch_Adjust
1. 정산이 이루어졌는지에 대한 정보를 받아온다.
2. 정산이 이루어지지 않았으면 !!!
*/
int Catch_Adjust()
{
	FILE *file_bus,*file_metro;         //카드 택스트 파일의 포인터
	int addjust_bus=0;
	int addjust_metro=0;
	int ret = 1;

	file_bus = fopen("./../send_bus.txt", "r");//파일 열기
	file_metro = fopen("./../send_metro.txt", "r");//파일 열기

	if(file_bus != NULL && file_metro != NULL)//파일이 잘 열렸는지 확인한다.
	{
		fscanf(file_bus, "%d", &addjust_bus);//버스회사로부터 정산여부를 읽는다.
		fscanf(file_metro, "%d", &addjust_metro);//지하철회사로부터 정산여부를 읽는다.

		if(!(addjust_bus==0 && addjust_metro==0))//버스와 지하철 둘다 정산이 완료된 상태가 아니라면,
		{
			state = NotAdjust;//에러로 정산중이라고 표시해준다.
			ret = 0;
		}
		fclose(file_bus);//파일 닫기
		fclose(file_metro);//파일 닫기
	}else
	{
		printf("파일이 없습니다.  파일 열기 실패\n");
		exit(1); 
	}
	return ret;
}

/*
Card_Info_Loader
1. Card_Reading_Interface() 호출, CID를 받아온다.
2. CID에 해당하는 Card의 정보를 읽어온다. (갱신)
*/
void Card_Info_Loader(Card_info* card_info, Time* tagTime)
{
	FILE *file;              //카드 택스트 파일의 포인터
	char buf[1024];          //맨 처음의 불필요한 한줄을 읽기위한 임시변수
	Card_info temp_card_info;//카드 택스트 파일로부터 한줄씩 읽을 때, 저장해둘 임시변수

	//CID는 card_info->CID를 수정
	Card_Reading_Interface(card_info, tagTime);//태그하는 카드의 ID를 입력받고, 태그 시각을 기억한다.

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

			card_info->cash = temp_card_info.cash;
			card_info->getout = temp_card_info.getout;
		}
		else//입력받은 CID값을 찾지 못했을 경우, 즉 잘못된 CID값을 입력받았을 경우
		{
			//에러를 표시해준다.
			state=InvalidInput;

			//카드의 기록은 초기화 해준다. [사실 없어도 되는 부분]
			card_info->cash=0;
			card_info->getout=0;
			card_info->state=0;
			card_info->tp=0;
			card_info->transfer=0;
		}
		fclose(file);//파일 닫기
	}else
	{
		printf("파일이 없습니다.  파일 열기 실패\n");
		exit(1); 
	}
}

/*
Card_Reading_Interface
1. 태그하는 카드의 ID를 입력받는다.
2. 태그 순간, 시간을 기억한다.
*/
void Card_Reading_Interface(Card_info* card_info, Time* tagTime){
	time_t timer;
	struct tm *t;
	int temp;

	printf("태그하실 카드의 ID를 입력해 주세요. : ");
	scanf("%d", &temp);
	card_info->CID = temp;
	fflush(stdin);

	time(&timer);
	//timer = time(NULL);
	t = localtime(&timer);
	tagTime->year = t->tm_year + 1900;
	tagTime->mon = t->tm_mon + 1;
	tagTime->day = t->tm_mday;
	tagTime->hour = t->tm_hour;
	tagTime->min = t->tm_min;
	tagTime->sec = t->tm_sec;
}

/*
Catch_Error
1. 읽어온 Card의 정보를 통해 Error 여부를 판단한다.
*/
int Catch_Error(Time tagTime, Card_info* card_info , int CRID)
{
	int interval_sec=0;//현재 태그시각과 최근 태그시각의 차이값(초)
	struct tm currnet, recent;
	int ret=1;

	currnet.tm_year = tagTime.year - 1900;
	currnet.tm_mon = tagTime.mon - 1;
	currnet.tm_mday = tagTime.day;
	currnet.tm_hour = tagTime.hour;
	currnet.tm_min = tagTime.min;
	currnet.tm_sec = tagTime.sec;

	recent.tm_year = card_info->tagTime.year - 1900;
	recent.tm_mon = card_info->tagTime.mon - 1;
	recent.tm_mday = card_info->tagTime.day;
	recent.tm_hour = card_info->tagTime.hour;
	recent.tm_min = card_info->tagTime.min;
	recent.tm_sec = card_info->tagTime.sec;

	interval_sec = mktime(&currnet)-mktime(&recent);//현재 태그시각과 최근 태그시각의 차이값(초)

	if (card_info->state == 1 &&						//가장 마지막으로 승차를 했고,
		CRID == card_info->CRID &&					//똑같은 단말기에 태그를 했으며,
		interval_sec < 15 &&						//태그시각의 차이가 15초 미만이고,
		card_info->getout == 0)						//정산으로 인해 쫒겨나지 않았다면,
	{
		 state = HopInProcessing;//이미 승차처리된 카드
		 ret = 0;
	}
	else if (card_info->state == 0 &&				//가장 마지막으로 하차를 했고,
			 CRID == card_info->CRID &&				//똑같은 단말기에 태그를 했으며,
		     interval_sec < 15 &&					//태그시각의 차이가 15초 미만이고,
			 card_info->getout == 0)				    //정산으로 인해 쫒겨나지 않았다면,
	{
		 state = GetOffProcessing;//이미 하차처리된 카드
		 ret = 0;
	}
	return ret;
}

/*
Fix_Price
1. 읽어온 Card의 정보를 통해 price를 정한다.
*/
void Fix_Price(Card_info* card_info, Time tagTime, int* price, int CRID)
{
	int tp;					 //현재 찍은 단말기의 교통수단 0 = 버스 1 = 지하철
	int interval_sec=0;      //현재 태그시각과 최근 태그시각의 차이값(초)
	int interval_station = ((CRID / 10) - (card_info->CRID / 10) + 5) % 5;//역과 역 사이의 간격
	struct tm currnet, recent;//현재 태그 시각과 최근 태그 시각
	
	currnet.tm_year = tagTime.year - 1900;
	currnet.tm_mon = tagTime.mon - 1;
	currnet.tm_mday = tagTime.day;
	currnet.tm_hour = tagTime.hour;
	currnet.tm_min = tagTime.min;
	currnet.tm_sec = tagTime.sec;

	recent.tm_year = card_info->tagTime.year - 1900;
	recent.tm_mon = card_info->tagTime.mon - 1;
	recent.tm_mday = card_info->tagTime.day;
	recent.tm_hour = card_info->tagTime.hour;
	recent.tm_min = card_info->tagTime.min;
	recent.tm_sec = card_info->tagTime.sec;

	interval_sec = mktime(&currnet)-mktime(&recent);//현재 태그시각과 최근 태그시각의 차이값(초)

	if (CRID < 10){			//10, 11 버스
		tp = 0;
	}
	else{					//지하철
		tp = 1;
	}
	if (CRID % 10 == 1)				//1.승차 단말기
	{
		printf("승차 단말기에 태그하였습니다.\n");
		if (card_info->state == 1)//1.1.승차 - 미정산 요금 부과
		{
			printf("카드가 승차상태입니다..\n");
			if (card_info->transfer == 1)//1.1.1.환승
			{
				printf("카드가 환승상태입니다. \n");
				if (card_info->tp == 1)//1.1.1.1.지하철
				{
					printf("지하철 미정산 요금 부과 1650\n");
					*price = 1650;
				}
				else if (card_info->tp == 0)//1.1.1.2.버스
				{
					printf("버스 미정산요금 부과 1550\n");
					*price = 1550;
				}
				else{
					printf("예외치 못한 상황발생");
				}
			}
			else if (card_info->transfer == 0)//1.1.2.비환승
			{
				printf("카드 상태가 환승이 아닙니다.\n");
				if (card_info->tp == 1)//1.1.2.1.지하철
				{
					printf("지하철 미정산요금 부과 1250\n");
					*price = 1250;
				}
				else if (card_info->tp == 0)//1.1.2.2.버스
				{
					printf("1050\n");
					*price = 1050;
				}
				else{
					printf("\nWTF?\n");
				}
			}
			else{
				printf("\nWTF?\n");
			}
			card_info->transfer = 0;
		}
		else if (card_info->state == 0)//1.2.하차 - 기본요금 또는 환승요금 부과
		{
			printf("카드 상태가 하차상태입니다.\n");
			
			if (card_info->transfer == 0)//1.2.1.비환승
			{
				printf("카드 상태가 환승이 아닙니다.\n");
				if (interval_sec <= 15 && card_info->getout == 0)//1.2.1.1.환승 - 환승요금 부과
				{
					printf("하차태그 후 15초 이내에 태그하였습니다.\n");
					if ((tp + card_info->tp) % 2 == 0)//1.2.1.1.1. 동일한 대중교통 이용
					{
						printf("지하철 -> 지하철, 버스 -> 버스 태그 1050\n");
						*price = 1050;//기본요금 : 지하철 -> 지하철 환승 또는 버스 -> 버스 환승
					}
					else if (tp == 1 && card_info->tp == 0)//1.2.1.1.2.버스 -> 지하철 환승
					{
						printf("버스 -> 지하철 태그 600\n");
						*price = 600;
						card_info->transfer = 1;
					}
					else if (tp == 0 && card_info->tp == 1)//1.2.1.1.3.지하철 -> 버스 환승
					{
						printf("지하철 -> 버스 태그 500\n");
						*price = 500;
						card_info->transfer = 1;
					}
					else{
						printf("\nWTF?\n");
					}
					printf("환승입니다.\n");
				}
				else if (interval_sec >15)//1.2.1.2.비환승 - 기본요금 부과
				{
					printf("하차태그 후 15초 이후에 태그하였습니다. 1050\n");
					card_info->getout = 0;
					card_info->transfer = 0;
					*price = 1050;
				}
				else{
					printf("\nWTF?\n");
				}
			}
			else{
				printf("\nWTF?\n");
			}
		}
		else{
			printf("\nWTF?\n");
		}
	}////////////////////////////////////////////
	/*
	버스 승차 -> 지하철 하차???
	*/
	else if (CRID % 10 == 0)//2.하차 단말기
	{
		printf("하차 단말기에 태그하였습니다.\n");
		if (card_info->state == 1)//2.1.승차
		{
			printf("카드 상태가 승차상태입니다.\n");
			if (card_info->transfer == 1)//2.1.1.환승 - 추가 환승요금 부담
			{
				printf("카드 상태가 환승상태입니다.\n");
				if (tp == 1)//2.1.1.1.지하철 - 역당 300원
				{
					printf("지하철 단말기에 태그하였습니다.\n");
					if (interval_station == 1 || interval_station == 4)//1개역 이동시 추가요금 300원
					{
						printf("1개역을 이동하였습니다.\n");
						*price = 300;
					}
					else if (interval_station == 2 || interval_station == 3)//2개역 이동시 추가요금 600원
					{
						printf("2개역을 이동하였습니다.\n");
						*price = 600;
					}
					else{
						printf("0개역을 이동하였습니다.\n");
						*price = 0;
					}

				}
				else if (tp == 0)//2.1.1.2.버스 - 30초당 100원
				{
					printf("버스 단말기를 태그하였습니다.\n");
					if (interval_sec / 30 > 5)//
					{
						printf("태그한지 150초가 지났습니다.\n");
						*price = 500;
					}
					else
					{
						printf("태그한지 150초가 지나지 않았습니다. 경과된 시간 : %d\n", interval_sec);
						*price = interval_sec / 30 * 100;
					}
				}
				else{
					printf("\nWTF?\n");
				}
			}
			else if (card_info->transfer == 0)//2.1.2.비환승 - 추가요금 부담
			{
				printf("카드 상태가 환승이 아닙니다.\n");
				if (tp == 1)//2.1.2.1.지하철	
				{
					printf("지하철 단말기를 태그했습니다.\n");
					if (interval_station == 1 || interval_station == 4)//1개역 이동시 기본요금 : 추가요금 없음
					{
						printf("1개역을 이동하였습니다.\n");
						*price = 0;
					}
					else if (interval_station == 2 || interval_station == 3)//2개역 이동시 추가요금 200원
					{
						printf("2개역을 이동하였습니다.\n");
						*price = 200;
					}
					else{
						printf("0개역을 이동하였습니다.\n");
						*price = 0;
					}
				}
				else if (tp == 0)//2.1.2.2.버스
				{
					printf("버스 단말기를 태그하였습니다.\n");
					*price = 0;
				}
				else{
					printf("\nWTF?\n");
				}
			}
			else{
				printf("\nWTF?\n");
			}
		}
		else{
			printf("\nWTF?\n");
		}
		card_info->transfer = 0;
	}
	else{
		printf("\nWTF?\n");
	}
}