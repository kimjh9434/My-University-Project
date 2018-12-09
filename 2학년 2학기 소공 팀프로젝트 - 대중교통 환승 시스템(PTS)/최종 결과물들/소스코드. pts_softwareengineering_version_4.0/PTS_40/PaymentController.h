//PaymentController.h
// - 각 c소스파일 별로 링크할 구조체 선언, 함수선언
#ifndef _PAYMENTCONTROLLER_H
#define _PAYMENTCONTROLLER_H

typedef struct _time Time;
typedef struct _card_info Card_info;

/*
Payment_Controller
1. Result() 호출
===정상일경우===
2. Card_Update() 호출
3. Card_Reader_Record() 호출
*/
void Payment_Controller(Card_info* card_info, int price, Time tagTime);

/*
Result
1. 정상 : update된 부과금액, 카드 잔액, 현재 시간을 출력
2. 에러 : 해당 statment 출력
3. Display_Interface() 호출
*/
void Result(Card_info* card_info, int price, Time tagTime);

/*
Display_Interface
*/
void Display_Interface(Card_info* card_info);

/*
Card_Update
1. update된 카드 정보를 파일에 저장
2. Card_Interface() 호출
*/
void Card_Update(Card_info* card_info);

/*
Card_Interface
*/
void Card_Interface(Card_info* card_info);

/*
Card_Reader_Record
1. 카드 단말기에 부가된 요금을 저장한다.
2. Card_Reader_Recording_Interface() 호출
*/
void Card_Reader_Record(Card_info* card_info, int price);

/*
Card_Reader_Recording_Interface
*/
void Card_Reader_Recording_Interface(Card_info* card_info);

#endif// _PAYMENTCONTROLLER_H