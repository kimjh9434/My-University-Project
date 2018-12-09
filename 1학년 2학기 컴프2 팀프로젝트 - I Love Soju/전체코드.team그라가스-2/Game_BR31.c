//Game_BR31.c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#include "ILoveSoju.h"


int Game_BR31(Student (*students),int drinker)
{
	int numberSum=0;
	int number;
	int count=0;  
	int i;
	int inputOK=1;
	int max;
	int space;
	int index;
	static int gameExplanation=1;//게임설명


	srand((unsigned int)time(NULL));

	system("cls");
	printf("\n\n   5. 베스킨라빈스31\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   게임 설명 \n\n");
		printf("   -1부터 시작하여 각자 최대 3 숫자씩 부른다.\n");
		printf("   -마지막에 31을 부르는 사람이 패배.\n\n\n");
		
		printf("   다음번에도, 게임 설명을 들으시겠습니까? \n\n");
		printf("   1. 예. 게임 설명을 듣겠습니다.\n");
		printf("   2. 아니오. 다음부터는 게임설명을 듣지 않겠습니다. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}

	system("cls");
	printf("\n   =======================================\n");
	printf("   §§§§§§베스킨라빈스 31§§§§§§\n");
	printf("   =======================================\n");
	printf("   ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★\n");
	printf("\n\n");
	printf("   게임을 시작합니다!");
	printf("\n\n");
	//----------------------------------------------------

	count=drinker;
	while(numberSum<31)
	{
		index=count%5;
		if(index==0)
		{
			printf("\n   1에서 3사이의 숫자를 입력하세요!:");
			do{
				number=Get_Only_Number();
				if(number < 1 || number > 3)
				{
					inputOK=0;
					printf("\n   제대로 입력하시죠.\n");
					printf("\n   1에서 3사이의 숫자를 입력하세요! : ");
				}
				else inputOK=1;


			}while(inputOK==0); // 예외처리
		}
		else
		{
			space=31-numberSum;
			if(space==3)
			{
				number = rand()%2+1;
			}
			else if(space==2)
			{
				number = 1;
			}
			else
			{
				number = rand()%3+1;
			}
		}

		i=numberSum+1;
		max=numberSum+number+1;

		printf("   %s : ",students[index].name);
		while(i<=31 && i < max )//초기수1
		{
			if(i==31)
			{
				count--;				
			}
			printf("%d ", i);//값 출력
			numberSum++;
			i++;
		}
		printf("\n");
		count++;
		Sleep(500);
	}

	//---------------------- 자신이 한거

	fflush(stdin);

	drinker = index;

	return drinker;

}