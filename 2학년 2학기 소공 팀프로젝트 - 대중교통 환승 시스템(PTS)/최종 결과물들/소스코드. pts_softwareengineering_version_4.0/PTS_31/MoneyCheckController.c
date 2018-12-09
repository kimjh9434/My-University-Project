//MoneyCheckController.c
#define _CRT_SECURE_NO_WARNINGS
#include "PublicTransportation.h"
#include "MoneyCheckController.h"

extern State state;

/*
Money_Check_Controller
1. Catch_Short() ȣ�� : ���� ������ ��� Short Error
2. ���� ����� ��� Calculation() ȣ��
*/
void Money_Check_Controller(Card_info* card_info, int price, Time tagTime, int CRID)
{
	if(state==Normal)//���ݱ��� ����ó���Ǿ� �Դٸ�, �����Ѵ�.
	{
		if(Catch_Short(card_info, price))//�ܾ��� �������� Ȯ���ϰ�
		{
			Calculation(card_info, price, tagTime, CRID);//�ܾ��� ����� ���, ����Ѵ�.
		}
	}
}

/*
Catch_Short
1. ������ price�� card_info->cash�� �� : ���� ������ ��� Short Error
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
1. ������ price�� ���ش�
2. �� �� card_info�� ������Ʈ ���ش�.
*/
void Calculation(Card_info* card_info, int price, Time tagTime, int CRID)
{
	int tp;
	if (CRID < 10){ //����
		tp = 0;
	}
	else{
		tp = 1;		//����ö
	}

	if (card_info->transfer == 0){
		card_info->cash -= price;
	}
	
	card_info->CRID = CRID;
	card_info->getout = 0;
	//0 = ����, 1 = ����
	if (CRID % 10 == 0){
		card_info->state = 0;
	}
	else{
		card_info->state = 1;
	}
	card_info->tagTime = tagTime;
	card_info->tp = tp;
	//card_info->transfer = 0; // FixPrice���� ����
}