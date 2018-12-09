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
1. Catch_Adjust() ȣ��
2. Card_Info_Loader() ȣ��
3. Error() ȣ�� -> FixPrice() ȣ��
*/
void Catch_Error_Controller(Card_info* card_info, int* price , int CRID, Time* tagTime)
{
	if(Catch_Adjust())//���������� Ȯ���ϰ�, �������� �ƴ϶�� �����Ѵ�.
	{
		Card_Info_Loader(card_info, tagTime);//�ֱٿ� �±׵� ī���� ������ �о�´�.

		if(Catch_Error(*tagTime, card_info , CRID))//��������, �������� ������ ��Ƴ���, ����ó����� �����Ѵ�.
		{
			Fix_Price(card_info, *tagTime, price, CRID);//�о�� Card�� ������ ���� price�� ���Ѵ�.
		}
	}
}

/*
Catch_Adjust
1. ������ �̷���������� ���� ������ �޾ƿ´�.
2. ������ �̷������ �ʾ����� !!!
*/
int Catch_Adjust()
{
	FILE *file_bus,*file_metro;         //ī�� �ý�Ʈ ������ ������
	int addjust_bus=0;
	int addjust_metro=0;
	int ret = 1;

	file_bus = fopen("./../send_bus.txt", "r");//���� ����
	file_metro = fopen("./../send_metro.txt", "r");//���� ����

	if(file_bus != NULL && file_metro != NULL)//������ �� ���ȴ��� Ȯ���Ѵ�.
	{
		fscanf(file_bus, "%d", &addjust_bus);//����ȸ��κ��� ���꿩�θ� �д´�.
		fscanf(file_metro, "%d", &addjust_metro);//����öȸ��κ��� ���꿩�θ� �д´�.

		if(!(addjust_bus==0 && addjust_metro==0))//������ ����ö �Ѵ� ������ �Ϸ�� ���°� �ƴ϶��,
		{
			state = NotAdjust;//������ �������̶�� ǥ�����ش�.
			ret = 0;
		}
		fclose(file_bus);//���� �ݱ�
		fclose(file_metro);//���� �ݱ�
	}else
	{
		printf("������ �����ϴ�.  ���� ���� ����\n");
		exit(1); 
	}
	return ret;
}

/*
Card_Info_Loader
1. Card_Reading_Interface() ȣ��, CID�� �޾ƿ´�.
2. CID�� �ش��ϴ� Card�� ������ �о�´�. (����)
*/
void Card_Info_Loader(Card_info* card_info, Time* tagTime)
{
	FILE *file;              //ī�� �ý�Ʈ ������ ������
	char buf[1024];          //�� ó���� ���ʿ��� ������ �б����� �ӽú���
	Card_info temp_card_info;//ī�� �ý�Ʈ ���Ϸκ��� ���پ� ���� ��, �����ص� �ӽú���

	//CID�� card_info->CID�� ����
	Card_Reading_Interface(card_info, tagTime);//�±��ϴ� ī���� ID�� �Է¹ް�, �±� �ð��� ����Ѵ�.

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

			card_info->cash = temp_card_info.cash;
			card_info->getout = temp_card_info.getout;
		}
		else//�Է¹��� CID���� ã�� ������ ���, �� �߸��� CID���� �Է¹޾��� ���
		{
			//������ ǥ�����ش�.
			state=InvalidInput;

			//ī���� ����� �ʱ�ȭ ���ش�. [��� ��� �Ǵ� �κ�]
			card_info->cash=0;
			card_info->getout=0;
			card_info->state=0;
			card_info->tp=0;
			card_info->transfer=0;
		}
		fclose(file);//���� �ݱ�
	}else
	{
		printf("������ �����ϴ�.  ���� ���� ����\n");
		exit(1); 
	}
}

/*
Card_Reading_Interface
1. �±��ϴ� ī���� ID�� �Է¹޴´�.
2. �±� ����, �ð��� ����Ѵ�.
*/
void Card_Reading_Interface(Card_info* card_info, Time* tagTime){
	time_t timer;
	struct tm *t;
	int temp;

	printf("�±��Ͻ� ī���� ID�� �Է��� �ּ���. : ");
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
1. �о�� Card�� ������ ���� Error ���θ� �Ǵ��Ѵ�.
*/
int Catch_Error(Time tagTime, Card_info* card_info , int CRID)
{
	int interval_sec=0;//���� �±׽ð��� �ֱ� �±׽ð��� ���̰�(��)
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

	interval_sec = mktime(&currnet)-mktime(&recent);//���� �±׽ð��� �ֱ� �±׽ð��� ���̰�(��)

	if (card_info->state == 1 &&						//���� ���������� ������ �߰�,
		CRID == card_info->CRID &&					//�Ȱ��� �ܸ��⿡ �±׸� ������,
		interval_sec < 15 &&						//�±׽ð��� ���̰� 15�� �̸��̰�,
		card_info->getout == 0)						//�������� ���� �i�ܳ��� �ʾҴٸ�,
	{
		 state = HopInProcessing;//�̹� ����ó���� ī��
		 ret = 0;
	}
	else if (card_info->state == 0 &&				//���� ���������� ������ �߰�,
			 CRID == card_info->CRID &&				//�Ȱ��� �ܸ��⿡ �±׸� ������,
		     interval_sec < 15 &&					//�±׽ð��� ���̰� 15�� �̸��̰�,
			 card_info->getout == 0)				    //�������� ���� �i�ܳ��� �ʾҴٸ�,
	{
		 state = GetOffProcessing;//�̹� ����ó���� ī��
		 ret = 0;
	}
	return ret;
}

/*
Fix_Price
1. �о�� Card�� ������ ���� price�� ���Ѵ�.
*/
void Fix_Price(Card_info* card_info, Time tagTime, int* price, int CRID)
{
	int tp;					 //���� ���� �ܸ����� ������� 0 = ���� 1 = ����ö
	int interval_sec=0;      //���� �±׽ð��� �ֱ� �±׽ð��� ���̰�(��)
	int interval_station = ((CRID / 10) - (card_info->CRID / 10) + 5) % 5;//���� �� ������ ����
	struct tm currnet, recent;//���� �±� �ð��� �ֱ� �±� �ð�
	
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

	interval_sec = mktime(&currnet)-mktime(&recent);//���� �±׽ð��� �ֱ� �±׽ð��� ���̰�(��)

	if (CRID < 10){			//10, 11 ����
		tp = 0;
	}
	else{					//����ö
		tp = 1;
	}
	if (CRID % 10 == 1)				//1.���� �ܸ���
	{
		printf("���� �ܸ��⿡ �±��Ͽ����ϴ�.\n");
		if (card_info->state == 1)//1.1.���� - ������ ��� �ΰ�
		{
			printf("ī�尡 ���������Դϴ�..\n");
			if (card_info->transfer == 1)//1.1.1.ȯ��
			{
				printf("ī�尡 ȯ�»����Դϴ�. \n");
				if (card_info->tp == 1)//1.1.1.1.����ö
				{
					printf("����ö ������ ��� �ΰ� 1650\n");
					*price = 1650;
				}
				else if (card_info->tp == 0)//1.1.1.2.����
				{
					printf("���� �������� �ΰ� 1550\n");
					*price = 1550;
				}
				else{
					printf("����ġ ���� ��Ȳ�߻�");
				}
			}
			else if (card_info->transfer == 0)//1.1.2.��ȯ��
			{
				printf("ī�� ���°� ȯ���� �ƴմϴ�.\n");
				if (card_info->tp == 1)//1.1.2.1.����ö
				{
					printf("����ö �������� �ΰ� 1250\n");
					*price = 1250;
				}
				else if (card_info->tp == 0)//1.1.2.2.����
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
		else if (card_info->state == 0)//1.2.���� - �⺻��� �Ǵ� ȯ�¿�� �ΰ�
		{
			printf("ī�� ���°� ���������Դϴ�.\n");
			
			if (card_info->transfer == 0)//1.2.1.��ȯ��
			{
				printf("ī�� ���°� ȯ���� �ƴմϴ�.\n");
				if (interval_sec <= 15 && card_info->getout == 0)//1.2.1.1.ȯ�� - ȯ�¿�� �ΰ�
				{
					printf("�����±� �� 15�� �̳��� �±��Ͽ����ϴ�.\n");
					if ((tp + card_info->tp) % 2 == 0)//1.2.1.1.1. ������ ���߱��� �̿�
					{
						printf("����ö -> ����ö, ���� -> ���� �±� 1050\n");
						*price = 1050;//�⺻��� : ����ö -> ����ö ȯ�� �Ǵ� ���� -> ���� ȯ��
					}
					else if (tp == 1 && card_info->tp == 0)//1.2.1.1.2.���� -> ����ö ȯ��
					{
						printf("���� -> ����ö �±� 600\n");
						*price = 600;
						card_info->transfer = 1;
					}
					else if (tp == 0 && card_info->tp == 1)//1.2.1.1.3.����ö -> ���� ȯ��
					{
						printf("����ö -> ���� �±� 500\n");
						*price = 500;
						card_info->transfer = 1;
					}
					else{
						printf("\nWTF?\n");
					}
					printf("ȯ���Դϴ�.\n");
				}
				else if (interval_sec >15)//1.2.1.2.��ȯ�� - �⺻��� �ΰ�
				{
					printf("�����±� �� 15�� ���Ŀ� �±��Ͽ����ϴ�. 1050\n");
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
	���� ���� -> ����ö ����???
	*/
	else if (CRID % 10 == 0)//2.���� �ܸ���
	{
		printf("���� �ܸ��⿡ �±��Ͽ����ϴ�.\n");
		if (card_info->state == 1)//2.1.����
		{
			printf("ī�� ���°� ���������Դϴ�.\n");
			if (card_info->transfer == 1)//2.1.1.ȯ�� - �߰� ȯ�¿�� �δ�
			{
				printf("ī�� ���°� ȯ�»����Դϴ�.\n");
				if (tp == 1)//2.1.1.1.����ö - ���� 300��
				{
					printf("����ö �ܸ��⿡ �±��Ͽ����ϴ�.\n");
					if (interval_station == 1 || interval_station == 4)//1���� �̵��� �߰���� 300��
					{
						printf("1������ �̵��Ͽ����ϴ�.\n");
						*price = 300;
					}
					else if (interval_station == 2 || interval_station == 3)//2���� �̵��� �߰���� 600��
					{
						printf("2������ �̵��Ͽ����ϴ�.\n");
						*price = 600;
					}
					else{
						printf("0������ �̵��Ͽ����ϴ�.\n");
						*price = 0;
					}

				}
				else if (tp == 0)//2.1.1.2.���� - 30�ʴ� 100��
				{
					printf("���� �ܸ��⸦ �±��Ͽ����ϴ�.\n");
					if (interval_sec / 30 > 5)//
					{
						printf("�±����� 150�ʰ� �������ϴ�.\n");
						*price = 500;
					}
					else
					{
						printf("�±����� 150�ʰ� ������ �ʾҽ��ϴ�. ����� �ð� : %d\n", interval_sec);
						*price = interval_sec / 30 * 100;
					}
				}
				else{
					printf("\nWTF?\n");
				}
			}
			else if (card_info->transfer == 0)//2.1.2.��ȯ�� - �߰���� �δ�
			{
				printf("ī�� ���°� ȯ���� �ƴմϴ�.\n");
				if (tp == 1)//2.1.2.1.����ö	
				{
					printf("����ö �ܸ��⸦ �±��߽��ϴ�.\n");
					if (interval_station == 1 || interval_station == 4)//1���� �̵��� �⺻��� : �߰���� ����
					{
						printf("1������ �̵��Ͽ����ϴ�.\n");
						*price = 0;
					}
					else if (interval_station == 2 || interval_station == 3)//2���� �̵��� �߰���� 200��
					{
						printf("2������ �̵��Ͽ����ϴ�.\n");
						*price = 200;
					}
					else{
						printf("0������ �̵��Ͽ����ϴ�.\n");
						*price = 0;
					}
				}
				else if (tp == 0)//2.1.2.2.����
				{
					printf("���� �ܸ��⸦ �±��Ͽ����ϴ�.\n");
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