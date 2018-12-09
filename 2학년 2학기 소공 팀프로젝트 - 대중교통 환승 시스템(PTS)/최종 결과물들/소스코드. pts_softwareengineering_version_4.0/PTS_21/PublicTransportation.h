//PublicTransportation.h
// - 각 c소스파일 별로 링크할 구조체 선언, 함수선언
#ifndef _PUBLICTRANSPORTATION_H
#define _PUBLICTRANSPORTATION_H

//구조체 선언

//태그할 당시의 시간을 표현해줄 시간 구조체
typedef struct _time
{
	int year; //년
	int mon;  //월
	int day;  //일
	int hour; //시
	int min;  //분
	int sec;  //초
}Time;

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

//현재 상황. 에러 판별 자료형 – Normal, HopInProcessing, GetOffProcessing, Short, NotAdjust, InvalidInput 이 해당한다.   
typedef enum {
	Normal,          //정상처리 - 아직 에러가 발생하지 않은 상황
	NotAdjust,       //미정산   : 지하철회사 또는 버스회사 둘중 하나라도 아직 정산이 진행 중인 상황
	HopInProcessing, //승차처리 : 승차 승차 & 동일한 단말기 & 15초 미만인 상황
	GetOffProcessing,//하차처리 : 하차 하차 & 동일한 단말기 & 15초 미만인 상황
	Short,           //잔액부족 : 부과금액 > 현재 금액인 상황
	InvalidInput     //잘못된 입력 : 카드 text파일에 없는 CID를 입력받은 상황
} State;

#endif// _PUBLICTRANSPORTATION_H