//MoneyCheckController.h
// - 각 c소스파일 별로 링크할 구조체 선언, 함수선언
#ifndef _MONEYCHECKCONTROLLER_H
#define _MONEYCHECKCONTROLLER_H

typedef struct _time Time;
typedef struct _card_info Card_info;

/*
Money_Check_Controller
1. Catch_Short() 호출 : 돈이 부족할 경우 Short Error
2. 돈이 충분할 경우 Calculation() 호출
*/
void Money_Check_Controller(Card_info* card_info, int price, Time tagTime, int CRID);

/*
Catch_Short
1. 측정된 price와 card_info->cash를 비교 : 돈이 부족할 경우 Short Error
*/
int Catch_Short(Card_info* card_info, int price);

/*
Calculation
1. 측정된 price를 빼준다
2. 그 외 card_info를 업데이트 해준다.
*/
void Calculation(Card_info* card_info, int price, Time tagTime, int CRID);

#endif// _MONEYCHECKCONTROLLER_H