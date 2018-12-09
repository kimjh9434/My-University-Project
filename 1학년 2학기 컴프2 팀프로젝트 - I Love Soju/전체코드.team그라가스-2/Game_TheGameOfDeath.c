#include <stdio.h>//더게임오브데스
#include <time.h>
#include <stdlib.h>
#include <Windows.h>
#include "ILoveSoju.h"


#define SIZETHE 5

int Game_TheGameOfDeath(Student (*students), int drinker)
{
	int i=0,j=1,k=1,result;
	int num,play=0;
	int player[SIZETHE];
	int drink;
	static int gameExplanation=1;

	srand(time(NULL));
	rand();rand();rand();

	system("cls");
	printf("\n\n   4.더 게임 오브 데스\n");
	printf("\n   ############################################################\n\n");

	if(gameExplanation==1)
	{
		printf("   게임 설명.\n\n");
		printf("   지목하고 싶은 플레이어를 지목한 후에\n");
		printf("   정한 횟수 만큼 따라가다가 정해진 횟수에 지목 당한\n");
		printf("   사람이 벌칙자가 되는 게임입니다\n\n\n");
		printf("   다음번에도, 게임 설명을 들으시겠습니까? \n\n");
		printf("   1. 예. 게임 설명을 듣겠습니다.\n");
		printf("   2. 아니오. 다음부터는 게임설명을 듣지 않겠습니다. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}

	TheInput(&num,&play,students);

	player[0]=num;

	while(j<SIZETHE)
	{
		player[j]=j;
		j++;
	}

	while(k<SIZETHE)
	{
		i=rand()%SIZETHE;
		while(player[k]==i)
		{
			i=rand()%SIZETHE;
		}
		player[k]=i;
		k++;
	}

	printf("\n   당신은 %s 을(를)지목 \n\n", students[player[0]].name);

	for(i=1;i<5;i++)
	{
		printf("   %s은(는) %s 을(를)지목 \n",students[i].name, students[player[i]].name);
		printf("   \n");
	}
	Sleep(3000);

	i=0;
	k=1;

	while(k<play+1)
	{		
		result=player[i];
		printf("\n   %s  ☞  %s  %d번~\n",students[i].name, students[player[i]].name,k);
		i=player[i];
		k++;
		Sleep(1500);
	}
	drink=result;
	printf("   \n");
	return drink;
}

void TheInput(int *num, int *play, Student (*students))
{
	printf("\n   지목하고자 하는 플레이어의 번호 입력 \n\n");
	printf("   (%s = 1, %s = 2, %s = 3, %s = 4) : ", students[1].name, students[2].name, students[3].name, students[4].name);

	do
	{
		*num=Get_Only_Number();
		if(*num<1 || *num>(SIZETHE-1))
			printf("\n   다시 입력하세요 : ");
	}while(*num<1 || *num>(SIZETHE-1));

	printf("\n   원하는 회전 횟수 입력 : ");
	do
	{
		*play=Get_Only_Number();
		if(*play<1)
			printf("   다시 입력하세요 : ");
	}while(*play<1);

}
