//MoneyCheckController.h
// - �� c�ҽ����� ���� ��ũ�� ����ü ����, �Լ�����
#ifndef _MONEYCHECKCONTROLLER_H
#define _MONEYCHECKCONTROLLER_H

typedef struct _time Time;
typedef struct _card_info Card_info;

/*
Money_Check_Controller
1. Catch_Short() ȣ�� : ���� ������ ��� Short Error
2. ���� ����� ��� Calculation() ȣ��
*/
void Money_Check_Controller(Card_info* card_info, int price, Time tagTime, int CRID);

/*
Catch_Short
1. ������ price�� card_info->cash�� �� : ���� ������ ��� Short Error
*/
int Catch_Short(Card_info* card_info, int price);

/*
Calculation
1. ������ price�� ���ش�
2. �� �� card_info�� ������Ʈ ���ش�.
*/
void Calculation(Card_info* card_info, int price, Time tagTime, int CRID);

#endif// _MONEYCHECKCONTROLLER_H