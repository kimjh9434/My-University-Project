//PaymentController.c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>     //printf
#include <stdlib.h>    //fscanf, fprint
#include "PublicTransportation.h"
#include "PaymentController.h"

extern State state;

/*
Payment_Controller
1. Result() ȣ��
===�����ϰ��===
2. Card_Update() ȣ��
3. Card_Reader_Record() ȣ��
*/
void Payment_Controller(Card_info* card_info, int price, Time tagTime)
{
	Result(card_info, price, tagTime);//���÷���(ȭ��, �����)�� ����� �����ش�.
	
	if(state==Normal)//���ݱ��� ����ó���Ǿ� �Դٸ�, �����Ѵ�.
	{
		Card_Update(card_info);//ī�忡 ������ �����Ѵ�.
		Card_Reader_Record(card_info, price);//�ܸ��⿡ ������ ����Ѵ�.
	}
}

/*
Result
1. ���� : update�� �ΰ��ݾ�, ī�� �ܾ�, ���� �ð��� ���
2. ���� : �ش� statment ���
3. Display_Interface() ȣ��
*/
void Result(Card_info* card_info, int price, Time tagTime)
{
	printf("�±� ��   �ð� : %04d�� %02d�� %02d�� %02d�� %02d�� %02d�� \n", card_info->tagTime.year, card_info->tagTime.mon, card_info->tagTime.day,
	card_info->tagTime.hour, card_info->tagTime.min, card_info->tagTime.sec);

	switch (state)
	{
	case Normal: //����ó��
		if (card_info->transfer == 1){				//ȯ���� ��� 
			price=0;
		}
		printf("�±׽� �ΰ��ݾ�: %d \n", price);
		printf("�±� ���� �ܾ� : %d \n", card_info->cash);
		break;
	case HopInProcessing: //����ó��
		printf("�̹� ����ó���� �� ī���Դϴ�.\n");
		break;
	case GetOffProcessing: //����ó��
		printf("�̹� ����ó���� �� ī���Դϴ�.\n");
		break;
	case Short: //�ܾ׺���
		printf("������ �ܾ� : %d \n", card_info->cash);
		printf("�ʿ��� �ܾ�: %d \n", price);
		printf("�ܾ��� �����մϴ�.\n");
		break;
	case NotAdjust: //������
		printf("���� ������ �Ϸ���� �ʾҽ��ϴ�. \n");
		break;
	case InvalidInput: //�߸��� �Է�
		printf("��ȿ���� ����, �߸��� CID���� �Է��߽��ϴ�. \n");
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
1. update�� ī�� ������ ���Ͽ� ����
2. Card_Interface() ȣ��
*/
void Card_Update(Card_info* card_info)
{
	FILE *file, *newFile;//ī�� �ý�Ʈ ������ ������
	char buf[1024];       //�� ó���� ���ʿ��� ������ �б����� �ӽú���
	Card_info temp_card_info;//ī�� �ý�Ʈ ���Ϸκ��� ���پ� ���� ��, �����ص� �ӽú���

	file = fopen("./../Card.txt", "r");//���� ����
	newFile = fopen("./../newCard.txt", "w");//���� ����
	
	if(file != NULL && newFile!= NULL)//������ �� ���ȴ��� Ȯ���Ѵ�.
	{
		fgets(buf,1024,file);//���ʿ��� ������ �д´�.
		fputs(buf, newFile);
		//ī�� �ý�Ʈ ���Ϸκ��� ������ �д´�.
		fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
		   &temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
		   &temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
		   &temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);

		while(!feof(file))//ī�� �ý�Ʈ ���Ϸκ��� ������ ���� �ƴѵ��� �ݺ��Ѵ�.
		{
			if(card_info->CID == temp_card_info.CID)//�Է¹��� CID���̶��,
			{
				//���ξ��� ī�� ������ �����ϰ�,
				fprintf(newFile, "%d\t%d\t%d %d %d %d %d %d\t%d\t%d\t%d\t%d\t%d\n", card_info->CID, card_info->CRID, 
					card_info->tagTime.year, card_info->tagTime.mon, card_info->tagTime.day, card_info->tagTime.hour,
					card_info->tagTime.min, card_info->tagTime.sec, 
					card_info->tp, card_info->state, card_info->cash, card_info->transfer, card_info->getout);

			}else//�Է¹��� CID���� �ƴ϶��,
			{
				//���� ī���� ������ �����Ѵ�.
				fprintf(newFile, "%d\t%d\t%d %d %d %d %d %d\t%d\t%d\t%d\t%d\t%d\n", temp_card_info.CID, temp_card_info.CRID, 
					temp_card_info.tagTime.year, temp_card_info.tagTime.mon, temp_card_info.tagTime.day, temp_card_info.tagTime.hour,
					temp_card_info.tagTime.min, temp_card_info.tagTime.sec, 
					temp_card_info.tp, temp_card_info.state, temp_card_info.cash, temp_card_info.transfer, temp_card_info.getout);
			}
			//ī�� �ý�Ʈ ���Ϸκ��� ������ �д´�.
			fscanf(file, "%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			   &temp_card_info.tagTime.year, &temp_card_info.tagTime.mon, &temp_card_info.tagTime.day, &temp_card_info.tagTime.hour,
			   &temp_card_info.tagTime.min, &temp_card_info.tagTime.sec, 
			   &temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);
		}
		fclose(newFile);//���� �ݱ�
		fclose(file);//���� �ݱ�

		remove("./../Card.txt");//���� ī�� �ý�Ʈ ������ �����ϰ�,
		rename("./../newCard.txt","./../Card.txt");//���ο� ī���ý�Ʈ ������ �̸��� ī�� �ý�Ʈ ���Ϸ� �����Ѵ�.
	}else
	{
		printf("������ �����ϴ�.  ���� ���� ����\n");
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
1. ī�� �ܸ��⿡ �ΰ��� ����� �����Ѵ�.
2. Card_Reader_Recording_Interface() ȣ��
*/
void Card_Reader_Record(Card_info* card_info, int price)
{
	FILE *file;//�ܸ��� ������ ������

	file = fopen("./../Card_Reader_5.txt", "a");//���빮���繮ȭ������ �ܸ��� ���� ����
	
	if(file != NULL)//������ �� ���ȴ��� Ȯ���Ѵ�.
	{
		if (card_info->transfer == 1){	//ȯ���� ��� 
			price=0;
		}
		//�ܸ��⿡ ���ξ��� ī�� ������ ����Ѵ�.
		fprintf(file, "%d\t%d\t%d %d %d %d %d %d\t%d\t%d\t%d\t%d\t%d\n", card_info->CID, card_info->CRID,
					card_info->tagTime.year, card_info->tagTime.mon, card_info->tagTime.day, card_info->tagTime.hour,
					card_info->tagTime.min, card_info->tagTime.sec, 
					card_info->tp, card_info->state, price, card_info->transfer, card_info->getout);
		fclose(file);//���� �ݱ�
	}else
	{
		printf("������ �����ϴ�.  ���� ���� ����\n");
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
