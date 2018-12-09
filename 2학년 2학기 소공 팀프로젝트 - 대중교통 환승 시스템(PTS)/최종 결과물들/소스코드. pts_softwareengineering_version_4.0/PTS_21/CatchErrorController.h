//CatchErrorController.h
// - �� c�ҽ����� ���� ��ũ�� ����ü ����, �Լ�����
#ifndef _CATCHERRORCONTROLLER_H
#define _CATCHERRORCONTROLLER_H

typedef struct _time Time;
typedef struct _card_info Card_info;

/*
Catch_Error_Controller
1. Catch_Adjust() ȣ��
2. Card_Info_Loader() ȣ��
3. Catch_Error() ȣ�� -> FixPrice() ȣ��
*/
void Catch_Error_Controller(Card_info* card_info, int* price , int CRID, Time* tagTime);

/*
Catch_Adjust
1. ������ �̷���������� ���� ������ �޾ƿ´�.
2. ������ �̷������ �ʾ����� !!!
*/
int Catch_Adjust();

/*
Card_Info_Loader
1. Card_Reading_Interface() ȣ��, CID�� �޾ƿ´�.
2. CID�� �ش��ϴ� Card�� ������ �о�´�. (����)
*/
void Card_Info_Loader(Card_info* card_info, Time* tagTime);


/*
Card_Reading_Interface
1. �±��ϴ� ī���� ID�� �Է¹޴´�.
2. �±� ����, �ð��� ����Ѵ�.
*/
void Card_Reading_Interface(Card_info* card_info, Time* tagTime);

/*
Catch_Error
1. �о�� Card�� ������ ���� Error ���θ� �Ǵ��Ѵ�.
*/
int Catch_Error(Time tagTime, Card_info* card_info , int CRID);


/*
Fix_Price
1. �о�� Card�� ������ ���� price�� ���Ѵ�.
*/
void Fix_Price(Card_info* card_info, Time tagTime, int* price, int CRID);

#endif// _CATCHERRORCONTROLLER_H