//Recharger.h
// - 각 c소스파일 별로 링크할 구조체 선언, 함수선언
#ifndef _RECHARGER_H
#define _RECHARGER_H

//구조체 선언

//태그할 당시의 시간을 표현해줄 시간 구조체
typedef struct _time
{
	int year;//년
	int mon; //월
	int day; //일
	int hour;//시
	int min; //분
	int sec; //초
}Time;

//카드 구조체
//교통카드에 들어있는 정보 - CID, CRID, tagTime, tp, state, cash, transfer, getout 이 해당된다.
typedef struct _card_info
{	
	int CID;      //교통카드의 고유ID (4자리수)  
	int CRID;     //마지막으로 태그한 단말기의 고유ID (2자리수)
                  //- 십의 자리의 경우, 단말기의 역을 구분 (버스 : 0, 지하철 - 건대 입구 : 1, 강남 : 2, 신림 : 3, 합정 : 4, 동대문역사문화공원 : 5)
                  //- 일의 자리의 경우,  승하차 단말기를 구분 (승차단말기 : 1, 하차단말기 : 0)
				  //ex) 00 <=> 버스 하차단말기, 31 <=> 합정역 승차단말기, 20 <=> 신림역 하차단말기
	Time tagTime; //마지막 태그시각 - 년(year), 월(mon), 일(day), 시(hour), 분(min), 초(sec)가 해당한다.
	int tp;       //마지막 탑승차량종류 - 1이면 지하철, 0이면 버스이다.
	int state;    //마지막 승하차여부 - 1이면 승차, 0이면 하차이다.
	int cash;     //마지막 잔액 
	int transfer; //마지막 환승여부 - 1이면 환승, 0이면 비환승이다.
	int getout ;  //마지막으로, 승차를 한후에 정산시에 쫒겨났는지 여부 - 1이면 쫒겨난것이고, 0이면 쫒겨나지 않은 것이다
}Card_info;

int Card_ID_Receive_Interface();              //Recharger Sensor로부터 받은 아날로그 신호를 디지털 신호로 변환한다. 출력값은 CID
void Card_Info_Loader(Card_info* card_info);//CID를 사용해서 Card Info를 불러와 Recharger Control 에 전달한다.
int Money_Sensor_Interface();                 //Money Sensor로부터 받은 아날로그 신호를 디지털 신호로 변환한다. 출력값은 Money

void Recharger_Controller();//입력받은 Card Info, money Data를 종합하여 충전 계산을 한 뒤, 적절한 Update와 Display를 실행 해준다.
void Update(Card_info* card_info);            //충전된 정보로 교통카드를 갱신한다.
void Display(Card_info* card_info, int money);//교통카드에 충전된 정보를 Monitor에 보여준다.
void Update_Interface(Card_info* card_info);  //update card data를 받아서 Card 정보를 Update시키는 정보를 보내준다
void Display_Interface(Card_info* card_info); //display data를 받아서 Monitor에 출력할 display정보를 보내준다.

int Get_Number();                             //최대 10자리의 정수를 입력받을수 있는 함수
#endif// _RECHARGER_H