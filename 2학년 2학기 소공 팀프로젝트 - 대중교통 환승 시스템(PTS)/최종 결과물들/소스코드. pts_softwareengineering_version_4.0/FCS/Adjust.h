//Adjust.h
// - 각 c소스파일 별로 링크할 구조체 선언, 함수선언
#ifndef _ADJUST_H
#define _ADJUST_H
#include <time.h>
//구조체 선언
//태그할 당시의 시간을 표현해줄 시간 구조체
// 순서 정렬시 쓰레기값이 들어가면 line 생각 파일에 마지막에 빈줄 들어가면 일어난것이다.
// mktime()사용시 tagtime.tm_year확인 .txt파일에 2014으로 적혀야 제대로된값을 출력한다.
typedef struct _card_info
{
	int CID;      //교통카드의 고유ID (4두자리수)  
	int CRID;     //마지막으로 태그한 단말기의 고유ID (2자리수)
	struct tm tagTime; //마지막 태그시각 형식은 년,월,일,시,분,초 이다.
	int tp;       //마지막 탑승차량종류 -  1이면 지하철, 0이면 버스이다.
	int state;    //마지막 승하차여부 - 1이면 승차, 0이면 하차이다.
	int cash;     //마지막 잔액 
	int price;
	int transfer; //마지막 환승여부 - 1이면 환승, 0이면 비환승이다.
	int getout;   //정산시 쫒겨났는지여부 - 1이면 쫒겨남, 0이면 안쫒겨남.

	// CRID에서
	// 십의 자리의 경우, 버스 : 0, 지하철 - 건대 입구 : 1, 강남 : 2, 신림 : 3, 합정 : 4, 동대문역사문화공원 : 5
	// 일의 자리의 경우, 승차단말기 : 1, 하차단말기 : 0
}Card_info;

Card_info* CID_Sort(char* fnamem, int* size);
void CID_Merge();
void Adjust(Card_info* real_card_info, int line, float* bus_fee,float* metro_fee);
int All_Adjust(float* bus_fee,float* metro_fee);
void All_Reset();						//모든 단말기 파일에 대하여 초기화한다.
void Reset(char* fname);				//한 단말기 파일을 초기화한다.
void AdjustStart();
void GuestOut();
void Send(int c_well,int bus_fee,int metro_fee);
void Display(int bus_fee, int metro_fee);
int Tick(struct tm st);
void Card_Reader_Info_Loader();
void Fee_Calculation_Controller();
float maketime(struct tm *tagtime);


#endif// _PUBLICTRANSPORTATION_H