//PublicTransportation.C
/*
파일이름 : PublicTransportation.C
기    능 : 3개의 시스템중 핵심인 대중 교통 시스템(Public Transportation System)을 담당한 코드이다.

팀    원 : 김제헌, 박상희, 박형민, 엄현식
작성일자 : 2014-11-03-월 ~ 2014-11-04-화
수 정 일 : 2014-11-15 토 : 중간수정을 함.
수 정 일 : 2014-11-18 화 : 중간수정을 함.
수 정 일 : 2014-11-21 금 : 현식이 코드를 반영 현재, PTS, RS에 한해서 SRA, SD, 코드가 일치된 상황
*/
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>     //printf
#include <Windows.h>   //system("cls");
#include "PublicTransportation.h"
#include "CatchErrorController.h"
#include "MoneyCheckController.h"
#include "PaymentController.h"

State state;//에러 판별 전역변수

//PTS 메인 함수
int main(int argc, char* argv[])
{
	Card_info card_info;//최근에 태그된 카드의 정보
	const int CRID=31;  //신림역 승차단말기 - 단말기마다 바뀌는 부분
	Time tagTime;       //태그시 시각
	int price=0;        //태그시 부과되는 금액

	char cont = 'G';//프로그램 진행여부. 'y':계속진행, 'n':프로그램 종료

	while (cont != 'q' && cont != 'Q')//사용자가 'q'또는 'Q'을 입력하지 않는동안 반복한다.
	{
		state=Normal;

		//system("cls");
		printf("\n\n\n");
		printf("SoftwareEngineering Team Project \n");
		printf("PTS - Public Transportation System \n");
		printf("신림역 승차단말기(Card Leader) - CRID : 31 \n\n");

		Catch_Error_Controller(&card_info, &price, CRID, &tagTime);
		Money_Check_Controller(&card_info, price, tagTime, CRID);
		Payment_Controller(&card_info, price, tagTime);

		getchar();
		fflush(stdin);
	}

	printf("\n\n  프로그램을 종료합니다.\n\n\n");
	
	return 0;
}