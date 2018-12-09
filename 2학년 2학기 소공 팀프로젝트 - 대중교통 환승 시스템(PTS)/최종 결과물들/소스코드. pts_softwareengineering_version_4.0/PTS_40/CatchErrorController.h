//CatchErrorController.h
// - 각 c소스파일 별로 링크할 구조체 선언, 함수선언
#ifndef _CATCHERRORCONTROLLER_H
#define _CATCHERRORCONTROLLER_H

typedef struct _time Time;
typedef struct _card_info Card_info;

/*
Catch_Error_Controller
1. Catch_Adjust() 호출
2. Card_Info_Loader() 호출
3. Catch_Error() 호출 -> FixPrice() 호출
*/
void Catch_Error_Controller(Card_info* card_info, int* price , int CRID, Time* tagTime);

/*
Catch_Adjust
1. 정산이 이루어졌는지에 대한 정보를 받아온다.
2. 정산이 이루어지지 않았으면 !!!
*/
int Catch_Adjust();

/*
Card_Info_Loader
1. Card_Reading_Interface() 호출, CID를 받아온다.
2. CID에 해당하는 Card의 정보를 읽어온다. (갱신)
*/
void Card_Info_Loader(Card_info* card_info, Time* tagTime);


/*
Card_Reading_Interface
1. 태그하는 카드의 ID를 입력받는다.
2. 태그 순간, 시간을 기억한다.
*/
void Card_Reading_Interface(Card_info* card_info, Time* tagTime);

/*
Catch_Error
1. 읽어온 Card의 정보를 통해 Error 여부를 판단한다.
*/
int Catch_Error(Time tagTime, Card_info* card_info , int CRID);


/*
Fix_Price
1. 읽어온 Card의 정보를 통해 price를 정한다.
*/
void Fix_Price(Card_info* card_info, Time tagTime, int* price, int CRID);

#endif// _CATCHERRORCONTROLLER_H