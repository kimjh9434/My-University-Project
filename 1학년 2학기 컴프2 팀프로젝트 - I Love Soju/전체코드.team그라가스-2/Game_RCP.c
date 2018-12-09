#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#include "ILoveSoju.h"

const char* TITLE1[3] = { "묵", "찌", "빠" };


//목차
//void PrintWhoTurn:  :1차 승부후 나와 컴퓨터의 선공을 가르는 함수
//void PrintHands1     : 나와 컴퓨터가 낸 경우를 출력
//void WhoWins1        : 1차 승부 떄 누가이겼는지 알려주는 함수
//int GetcomputerHand : 컴퓨터가 무엇을 내는 알려주는 랜덤함수



int Game_RCP(Student (*students),int drinker)
{
	int myHand; // 사용자가 낸 묵찌빠
	int computerHand; // 컴퓨터가 낸 묵찌빠
	int whoIsTurn1 = 0; // 0 : 비김 1 : 사용자, 2 : 컴퓨터 
	int computer;
	static int gameExplanation=1;//게임설명.



	// 상대와 나 고르기


	system("cls");

	

	printf("\n\n   6. 묵찌빠게임\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   게임 설명 \n\n");
		printf("   1. 가위바위보를 통해 선공을 결정합니다.\n");
		printf("   2.선공을 정하면 다시 가위바위보를 합니다.\n");
		printf("   3. 선공인사람이 방어하는 사람이랑 같은것을 내면 승리합니다\n");
		printf("   4. 선공인 사람이 방어하는사람에게 이기면 공격권을 유지합니다\n");
		printf("   5. 선공인사람이 방어하는사람에게 지면 공격권은 넘어갑니다.\n\n\n");

		printf("   다음번에도, 게임 설명을 들으시겠습니까? \n\n");
		printf("   1. 예. 게임 설명을 듣겠습니다.\n");
		printf("   2. 아니오. 다음부터는 게임설명을 듣지 않겠습니다. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}

	srand((unsigned int)time(NULL));


	computer = rand()%5;
	while(drinker == computer)
	{
		computer = rand()%5;
	}

	printf("   %s와  %s 가 게임한다\n", students[drinker].name,students[computer].name);


	printf("   선공 결정!\n");
	while(whoIsTurn1 == 0) // 비긴다면 계속내도록 한다.(왜냐하면 처음 가위바위보이기때문에 선공결정하기 위해서)
	{
		if(drinker==0)
		{
			printf("\n   0->묵, 1->찌, 2->빠:");
			myHand=Get_Only_Number();
			while(myHand < 0 || myHand > 2)
			{
				printf("\n   0~2사이의 수를 입력하세요.\n");
				printf("\n   0->묵, 1->찌, 2->빠:");
				myHand=Get_Only_Number();
			}
			computerHand=rand()%3;
		}
		else if(computer ==0)
		{
			printf("\n   0->묵, 1->찌, 2->빠:");
			computerHand=Get_Only_Number();
			while(computerHand < 0 || computerHand > 2)
			{
				printf("\n   0~2사이의 수를 입력하세요.\n");
				printf("\n   0->묵, 1->찌, 2->빠:");
				computerHand=Get_Only_Number();
			}
			myHand=rand()%3;
		}else
		{
			myHand=rand()%3;
			computerHand=rand()%3;
		}
		

		// 현재 정보를 출력한다.
		printf("   %6s : %s\n", students[drinker].name, TITLE1[myHand]);
		printf("   %6s : %s\n\n", students[computer].name, TITLE1[computerHand]);
		if(drinker!=0&&computer!=0)Sleep(2000);
		whoIsTurn1 = WhoWins1(myHand, computerHand); // 누가 이겼는지 판별해준다.


		// 0일 경우 비긴것이고, 1일 경우 내가 승리,  2일 경우 컴퓨터가 승리.
		if(whoIsTurn1 == 0)
		{	
			printf("   비겼습니다..\n\n\n");

			if(drinker!=0&&computer!=0)Sleep(2000);
		}
		//while(whoIsTurn1 == 0)
	}
	// ------------------------------------------------------------------------------------------ 여기까지 선공정하기

	while(whoIsTurn1 != 0) // 이제부턴 비길때까지 계속한다, 즉 묵찌빠에서 서로 같은걸내면 누구의 턴이냐에 따라 지는것이다.
	{
		if(whoIsTurn1 == 1)
		{
			printf("\n   %s의 턴!!\n\n", students[drinker].name);
		}
		else if(whoIsTurn1 == 2)
		{
			printf("\n   %s의 턴!!\n\n",students[computer].name);
		}
		if(drinker ==0)
		{
			printf("   0->묵, 1->찌, 2->빠:");

			myHand=Get_Only_Number();
			while(myHand < 0 || myHand > 2)
			{
				printf("\n   0~2사이의 수를 입력하세요.\n");
				printf("\n   0->묵, 1->찌, 2->빠:");
				myHand=Get_Only_Number();
				
			}
			computerHand=rand()%3;
			if(drinker!=0&&computer!=0)Sleep(2000);
		}
		else if(computer ==0)
		{
			printf("   0->묵, 1->찌, 2->빠:");

			computerHand=Get_Only_Number();
			while(computerHand < 0 || computerHand > 2)
			{
				printf("\n   0~2사이의 수를 입력하세요.\n");
				printf("\n   0->묵, 1->찌, 2->빠:");
				computerHand=Get_Only_Number();
				
			}
			myHand=rand()%3;
			if(drinker!=0&&computer!=0)Sleep(2000);
		}
		else
		{
			myHand=rand()%3;
			computerHand=rand()%3;
		}


		system("cls");

		printf("\n\n   ------------------------------중간결과------------------------------\n\n");


		printf("   %6s : %s\n", students[drinker].name, TITLE1[myHand]);
		printf("   %6s : %s\n", students[computer].name, TITLE1[computerHand]);
		if(drinker!=0&&computer!=0)Sleep(2000);

		printf("\n\n   ------------------------------중간결과------------------------------\n\n");
		whoIsTurn1=WhoWins1(myHand, computerHand);
		switch(whoIsTurn1) // 현재 누가 이겼는지 판별한다.
		{
		case 0: // 비겼으면
			Sleep(1000);
			if(whoIsTurn1 == 1) // 내턴이였다면
			{
				printf("   ★★★★★★★★★%s의 승리★★★★★★★★★\n",students[drinker].name); // 내가 승리한것이였고
				getchar();

				Sleep(4000);
			}
			else if(whoIsTurn1 == 2) // 컴퓨터턴이였다면
			{
				printf("   ♨♨♨♨♨♨♨♨♨♨%s의 패배♨♨♨♨♨♨♨♨♨\n",students[computer].name); // 컴퓨터가 승리한것이다.
				drinker=computer;
				getchar();
				Sleep(4000);
			}break;
		default:break;
		}
	}

	return drinker;
}




// 0 : 비김 , 1 : 사용자가 이김, 2 : 컴퓨터가 이김


int WhoWins1(int myHand, int computerHand)
{ 
	int ret;//반환값

	//내가 낸 것과 컴퓨터가 낸 것이 같으면,
	if(myHand == computerHand)
	{
		ret = 0;// 비김. 0을 반환
	}
	// 내가 묵일때 컴퓨터가 가위이거나,  내가 가위일때 컴퓨터가 보이거나, 내가 보일때 컴퓨터가 묵이면
	else if((myHand == 0 && computerHand == 1) || (myHand == 1 && computerHand == 2) || (myHand == 2 && computerHand == 0))
	{
		ret = 1; // 내가 승리. 1을 반환
	}
	else
	{
		ret = 2; // 아니라면 컴퓨터 승리. 2를 반환
	}
	return ret;
}