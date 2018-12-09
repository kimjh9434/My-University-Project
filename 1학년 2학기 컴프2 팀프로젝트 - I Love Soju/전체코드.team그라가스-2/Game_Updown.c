#include <stdio.h>//업다운
#include <time.h>
#include <stdlib.h>
#include <windows.h>
#include "ILoveSoju.h"

#define SIZEUP 5

void UpInput(int *num, int min, int max)
{
	printf("\n   정답이라고 생각하는 수를 입력하세요 : ");
	do
	{
		*num=Get_Only_Number();
		if(*num<=min || *num>=max)
			printf("   %d보다 크고, %d보다 작을 수로, 다시 입력하세요 : ",min,max);
	}while(*num<=min || *num>=max);
}

void UpDown(int num, int answer, int *min, int *max, int (*com), int *drink)
{
	int i;

	if(answer>num)
	{
		printf("\n   Up~!Up~!Up~!Up~!Up~!Up~!Up~!Up~!Up~!\n");
		*min=num;
	}
	else if(answer<num)
	{
		printf("\n   Down~!Down~!Down~!Down~!Down~!Down~!\n");
		*max=num;
	}
	else
	{
		for(i=0;i<5;i++)
		{
			if(com[i]==1)
			{
				printf("\n   정답~! \n");
				*drink=i;
			}
		}
	}
}

void ComInput(int *num, int min, int max)
{
	srand(time(NULL));
	rand();rand();rand();

	*num=rand()%(max-min-1)+min+1;
}

int Game_Updown(Student (*students), int drinker)
{
	int num,answer;
	int min=0,max=100;
	int com[SIZEUP]={0};
	int i=1;
	int drink;
	static int gameExplanation=1;

	srand(time(NULL));
	rand();rand();rand();
	answer=rand()%99+1;

	system("cls");
	printf("\n\n   3.업다운게임\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   게임 설명.\n\n");
		printf("   게임 플레이어가 돌아가면서 부1~99중 숫자를 부릅니다.\n");
		printf("   1~99사이의 정답 숫자를 맞추는 사람은 벌칙자가 됩니다\n\n\n");	
		printf("   다음번에도, 게임 설명을 들으시겠습니까? \n\n");
		printf("   1. 예. 게임 설명을 듣겠습니다.\n");
		printf("   2. 아니오. 다음부터는 게임설명을 듣지 않겠습니다. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}
	do
	{
		UpInput(&num, min, max);
		if(answer==num)
		{
			com[0]=1;
		}
		UpDown(num,answer,&min,&max,com,&drink);

		i=1;

		while(i<SIZEUP && answer!=num)
		{
			ComInput(&num,min, max);
			Sleep(1000);
			printf("\n   %s은(는) %d 입력하였습니다~\n",students[i].name,num);
			if(answer==num)
			{
				com[i]=1;
			}
			UpDown(num,answer,&min,&max,com,&drink);
			i++;
		}
	}while(answer!=num);

	return drink;
}