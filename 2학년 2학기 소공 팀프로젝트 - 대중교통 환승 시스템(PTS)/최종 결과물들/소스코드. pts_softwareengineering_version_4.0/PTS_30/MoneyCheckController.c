//MoneyCheckController.c
#define _CRT_SECURE_NO_WARNINGS
#include "PublicTransportation.h"
#include "MoneyCheckController.h"

extern State state;

/*
Money_Check_Controller
1. Catch_Short() 호출 : 돈이 부족할 경우 Short Error
2. 돈이 충분할 경우 Calculation() 호출
*/
void Money_Check_Controller(Card_info* card_info, int price, Time tagTime, int CRID)
{
	if(state==Normal)//지금까지 정상처리되어 왔다면, 진행한다.
	{
		if(Catch_Short(card_info, price))//잔액이 부족한지 확인하고
		{
			Calculation(card_info, price, tagTime, CRID);//잔액이 충분할 경우, 계산한다.
		}
	}
}

/*
Catch_Short
1. 측정된 price와 card_info->cash를 비교 : 돈이 부족할 경우 Short Error
*/
int Catch_Short(Card_info* card_info, int price)
{
	int ret = 1;
	if( (card_info->cash-price) < 0 )
	{
		state = Short;
		ret = 0;
	}
	return ret;
}

/*
Calculation
1. 측정된 price를 빼준다
2. 그 외 card_info를 업데이트 해준다.
*/
void Calculation(Card_info* card_info, int price, Time tagTime, int CRID)
{
	int tp;
	if (CRID < 10){ //버스
		tp = 0;
	}
	else{
		tp = 1;		//지하철
	}

	if (card_info->transfer == 0){
		card_info->cash -= price;
	}
	
	card_info->CRID = CRID;
	card_info->getout = 0;
	//0 = 하차, 1 = 승차
	if (CRID % 10 == 0){
		card_info->state = 0;
	}
	else{
		card_info->state = 1;
	}
	card_info->tagTime = tagTime;
	card_info->tp = tp;
	//card_info->transfer = 0; // FixPrice에서 갱신
}