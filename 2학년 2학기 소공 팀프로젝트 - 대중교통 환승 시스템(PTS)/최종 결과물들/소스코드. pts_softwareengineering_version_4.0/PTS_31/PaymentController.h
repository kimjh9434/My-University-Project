//PaymentController.h
// - �� c�ҽ����� ���� ��ũ�� ����ü ����, �Լ�����
#ifndef _PAYMENTCONTROLLER_H
#define _PAYMENTCONTROLLER_H

typedef struct _time Time;
typedef struct _card_info Card_info;

/*
Payment_Controller
1. Result() ȣ��
===�����ϰ��===
2. Card_Update() ȣ��
3. Card_Reader_Record() ȣ��
*/
void Payment_Controller(Card_info* card_info, int price, Time tagTime);

/*
Result
1. ���� : update�� �ΰ��ݾ�, ī�� �ܾ�, ���� �ð��� ���
2. ���� : �ش� statment ���
3. Display_Interface() ȣ��
*/
void Result(Card_info* card_info, int price, Time tagTime);

/*
Display_Interface
*/
void Display_Interface(Card_info* card_info);

/*
Card_Update
1. update�� ī�� ������ ���Ͽ� ����
2. Card_Interface() ȣ��
*/
void Card_Update(Card_info* card_info);

/*
Card_Interface
*/
void Card_Interface(Card_info* card_info);

/*
Card_Reader_Record
1. ī�� �ܸ��⿡ �ΰ��� ����� �����Ѵ�.
2. Card_Reader_Recording_Interface() ȣ��
*/
void Card_Reader_Record(Card_info* card_info, int price);

/*
Card_Reader_Recording_Interface
*/
void Card_Reader_Recording_Interface(Card_info* card_info);

#endif// _PAYMENTCONTROLLER_H