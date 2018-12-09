//Recharger.h
// - �� c�ҽ����� ���� ��ũ�� ����ü ����, �Լ�����
#ifndef _RECHARGER_H
#define _RECHARGER_H

//����ü ����

//�±��� ����� �ð��� ǥ������ �ð� ����ü
typedef struct _time
{
	int year;//��
	int mon; //��
	int day; //��
	int hour;//��
	int min; //��
	int sec; //��
}Time;

//ī�� ����ü
//����ī�忡 ����ִ� ���� - CID, CRID, tagTime, tp, state, cash, transfer, getout �� �ش�ȴ�.
typedef struct _card_info
{	
	int CID;      //����ī���� ����ID (4�ڸ���)  
	int CRID;     //���������� �±��� �ܸ����� ����ID (2�ڸ���)
                  //- ���� �ڸ��� ���, �ܸ����� ���� ���� (���� : 0, ����ö - �Ǵ� �Ա� : 1, ���� : 2, �Ÿ� : 3, ���� : 4, ���빮���繮ȭ���� : 5)
                  //- ���� �ڸ��� ���,  ������ �ܸ��⸦ ���� (�����ܸ��� : 1, �����ܸ��� : 0)
				  //ex) 00 <=> ���� �����ܸ���, 31 <=> ������ �����ܸ���, 20 <=> �Ÿ��� �����ܸ���
	Time tagTime; //������ �±׽ð� - ��(year), ��(mon), ��(day), ��(hour), ��(min), ��(sec)�� �ش��Ѵ�.
	int tp;       //������ ž���������� - 1�̸� ����ö, 0�̸� �����̴�.
	int state;    //������ ���������� - 1�̸� ����, 0�̸� �����̴�.
	int cash;     //������ �ܾ� 
	int transfer; //������ ȯ�¿��� - 1�̸� ȯ��, 0�̸� ��ȯ���̴�.
	int getout ;  //����������, ������ ���Ŀ� ����ÿ� �i�ܳ����� ���� - 1�̸� �i�ܳ����̰�, 0�̸� �i�ܳ��� ���� ���̴�
}Card_info;

int Card_ID_Receive_Interface();              //Recharger Sensor�κ��� ���� �Ƴ��α� ��ȣ�� ������ ��ȣ�� ��ȯ�Ѵ�. ��°��� CID
void Card_Info_Loader(Card_info* card_info);//CID�� ����ؼ� Card Info�� �ҷ��� Recharger Control �� �����Ѵ�.
int Money_Sensor_Interface();                 //Money Sensor�κ��� ���� �Ƴ��α� ��ȣ�� ������ ��ȣ�� ��ȯ�Ѵ�. ��°��� Money

void Recharger_Controller();//�Է¹��� Card Info, money Data�� �����Ͽ� ���� ����� �� ��, ������ Update�� Display�� ���� ���ش�.
void Update(Card_info* card_info);            //������ ������ ����ī�带 �����Ѵ�.
void Display(Card_info* card_info, int money);//����ī�忡 ������ ������ Monitor�� �����ش�.
void Update_Interface(Card_info* card_info);  //update card data�� �޾Ƽ� Card ������ Update��Ű�� ������ �����ش�
void Display_Interface(Card_info* card_info); //display data�� �޾Ƽ� Monitor�� ����� display������ �����ش�.

int Get_Number();                             //�ִ� 10�ڸ��� ������ �Է¹����� �ִ� �Լ�
#endif// _RECHARGER_H