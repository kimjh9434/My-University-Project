//Game_ChamChamCham.c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#include "ILoveSoju.h"



const char *TITLE[5] = {"묵", "찌", "빠"};
const char *TITLE2[5] = { "왼쪽", "가운데", "오른쪽" };


//																			목차 : void PrintWhoTurn    :  1차 승부후 나와 컴퓨터의 선공을 가르는 함수
//																			       void PrintHands      : 나와 컴퓨터가 낸 경우를 출력
//																		        	void WhoWins        : 1차 승부 떄 누가이겼는지 알려주는 함수
//																			      int GetcomputerHand   : 컴퓨터가 무엇을 내는 알려주는 랜덤함수
//																		        	void main           : 메인

int Game_ChamChamCham(Student (*students), int drinker)
{
	int myHand; // 사용자가 낸 묵찌빠
	int computerHand; // 컴퓨터가 낸 묵찌빠
	int whoIsTurn = 0; // 0 : 비김 1 : 사용자, 2 : 컴퓨터 

	int computer;
	static int gameExplanation=1;//게임설명.

	system("cls");
	printf("\n\n   7. §§§§참참참게임!§§§§\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   게임 설명 \n\n");
		printf("   1. 가위바위보를 통해 선공을 결정합니다.\n");
		printf("   2.선공을 결정하면 왼쪽,오른쪽, 가운데 중 한개를 고릅니다\n");
		printf("   3. 선공인사람이 방어하는사람의 방향을 맞추면 승리합니다.\n");
		printf("   4. 공격 실패시 턴은 넘어갑니다.\n\n\n");

		printf("   다음번에도, 게임 설명을 들으시겠습니까? \n\n");
		printf("   1. 예. 게임 설명을 듣겠습니다.\n");
		printf("   2. 아니오. 다음부터는 게임설명을 듣지 않겠습니다. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}

	srand((unsigned int)time(NULL));

	// 상대와 나 고르기
	computer = rand()%5;
	while(drinker == computer)
	{
		computer = rand()%5;
	}
	printf("   %s와  %s 가 게임한다\n", students[drinker].name,students[computer].name);

	printf("   선공 결정!\n");

	while(whoIsTurn == 0) // 비긴다면 계속내도록 한다.
	{
		if(drinker==0)
		{
			printf("   0->바위, 1->가위, 2->보:");

			myHand=Get_Only_Number();

			while(myHand < 0 || myHand > 2)
			{
				printf("\n   0~2사이의 수를 입력하세요.\n");
				printf("   0->바위, 1->가위, 2->보:");
				myHand=Get_Only_Number();
			}
			computerHand=rand()%3;
		}else if(computer==0){
			printf("   0->바위, 1->가위, 2->보:");

			computerHand=Get_Only_Number();

			while(computerHand < 0 || computerHand > 2)
			{
				printf("\n   0~2사이의 수를 입력하세요.\n");
				printf("   0->바위, 1->가위, 2->보:");
				computerHand=Get_Only_Number();
			}
			myHand=rand()%3;
		}else 
		{
			myHand=rand()%3;
			computerHand=rand()%3;
		}

		PrintHands(myHand, computerHand,drinker,computer,students); // 현재 정보를 출력한다.
		if(drinker!=0&&computer!=0)Sleep(2000);Sleep(2000);
		whoIsTurn = WhoWins(myHand, computerHand); // 누가 이겼는지 판별해준다.

		// 0일 경우 비긴것이고, 1일 경우 내가 승리,  2일 경우 컴퓨터가 승리

		if(whoIsTurn == 0)
		{
			printf("   비겼습니다.\n\n\n");
		}
	}

	// ------------------------------------------------------------------------------------------ 여기까지 선공정하기

	while(whoIsTurn != 0) // 이제부턴 같을때 까지 같이 한다. 참참참에서는 같은 방향일때 승부가 결정된다.
	{
		
		PrintWhoTrun(whoIsTurn,students,drinker,computer); // 현재 누구 턴인지 출력한다.
		if(drinker == 0)
		{
			printf("\n\n   참!참!참!!\n");
			printf("   0->왼쪽, 1->가운데, 2->오른쪽:");

			myHand=Get_Only_Number();
			while(myHand < 0 || myHand > 2)
			{
				printf("\n   0~2사이의 수를 입력하세요.\n");
				printf("   0->왼쪽, 1->가운데, 2->오른쪽:");
				myHand=Get_Only_Number();
				
			}
			computerHand=rand()%3;
		}else if(computer==0)
		{
			printf("\n\n   참!참!참!!\n");
			printf("   0->왼쪽, 1->가운데, 2->오른쪽:");

			computerHand=Get_Only_Number();
			while(computerHand < 0 || computerHand > 2)
			{
				printf("\n   0~2사이의 수를 입력하세요.\n");
				printf("   0->왼쪽, 1->가운데, 2->오른쪽:");
				computerHand=Get_Only_Number();
				
			}
			myHand=rand()%3;
		}else
		{
			myHand=rand()%3;
			computerHand=rand()%3;
		}

		printf("   ------------------------------중간결과------------------------------\n\n");

		PrintHands2(myHand, computerHand, drinker,  computer,students);
		if(drinker!=0&&computer!=0)Sleep(2000);Sleep(2000);
	
		printf("   ------------------------------중간결과------------------------------\n\n");
  
		switch(WhoWins(myHand, computerHand)) // 현재 누가 이겼는지 판별한다.
		{
		
		case 0: // 비겼으면
			if(whoIsTurn == 1) // 내턴이였다면
			{
				printf("   ★★★★★★★★★%s의 승리★★★★★★★★★\n",students[drinker].name); // 내가 승리한것이였고
				drinker = computer;getchar();
			}
			else if(whoIsTurn == 2) // 컴퓨터턴이였다면
			{
				printf("   ♨♨♨♨♨♨♨♨♨♨%s의 승리♨♨♨♨♨♨♨♨♨\n", students[computer].name); // 컴퓨터가 승리한것이다.
				
				getchar();
			}
			Sleep(3000);
			whoIsTurn = 0; // 비겼다고 0을 대입
			break;
  
		case 1: 
			whoIsTurn = 2; // 나의 턴이였으면 2대입 = 컴퓨터 차례 
			break;
  
		case 2:
			whoIsTurn = 1; // 컴퓨터의 턴이였으면 1대입 = 나의 차례
			break;
		}
	}
	return drinker;

}


void PrintWhoTrun(const int whoIsTurn,Student (*students),int drinker,int computer)
{
	if(whoIsTurn == 1)
		printf("\n   %s의 턴!!\n",students[drinker].name);
	
	else if(whoIsTurn == 2)
		printf("\n   %s의 턴!!\n",students[computer].name);
}

// 나의턴 : 1  컴퓨터의 턴 : 2



void PrintHands(int myHand, int computerHand, int drinker, int computer,Student (*students))
{
	printf("   %6s : %s\n", students[drinker].name, TITLE[myHand]);
	printf("   %6s : %s\n\n", students[computer].name, TITLE[computerHand]);
	
}
// 0 : 비김 , 1 : 사용자가 이김, 2 : 컴퓨터가 이김

void PrintHands2(int myHand, int computerHand,int drinker, int computer,Student (*students))
{
	printf("   %6s : %s\n", students[drinker].name, TITLE2[myHand]);
	printf("   %6s : %s\n\n", students[computer].name, TITLE2[computerHand]);
	
}
// 0 : 비김 , 1 : 사용자가 이김, 2 : 컴퓨터가 이김


int WhoWins(const int myHand, const int computerHand)
{ 
 // 비겼으면 0을 낸다.
 
	if(myHand == computerHand)
  
	 return 0;
 // 내가 묵일때 컴퓨터가 가위이거나,  내가 가위일때 컴퓨터가 보이거나, 내가 보일때 컴퓨터가 묵이면
 
 
	if((myHand == 0 && computerHand == 1) || (myHand == 1 && computerHand == 2) || (myHand == 2 && computerHand == 0))
  
		return 1; // 내가 승리
 
	else
 
		return 2; // 아니라면 컴퓨터 승리
}



