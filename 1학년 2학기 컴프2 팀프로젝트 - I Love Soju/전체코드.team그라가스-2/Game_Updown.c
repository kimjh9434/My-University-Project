#include <stdio.h>//���ٿ�
#include <time.h>
#include <stdlib.h>
#include <windows.h>
#include "ILoveSoju.h"

#define SIZEUP 5

void UpInput(int *num, int min, int max)
{
	printf("\n   �����̶�� �����ϴ� ���� �Է��ϼ��� : ");
	do
	{
		*num=Get_Only_Number();
		if(*num<=min || *num>=max)
			printf("   %d���� ũ��, %d���� ���� ����, �ٽ� �Է��ϼ��� : ",min,max);
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
				printf("\n   ����~! \n");
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
	printf("\n\n   3.���ٿ����\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   ���� ����.\n\n");
		printf("   ���� �÷��̾ ���ư��鼭 ��1~99�� ���ڸ� �θ��ϴ�.\n");
		printf("   1~99������ ���� ���ڸ� ���ߴ� ����� ��Ģ�ڰ� �˴ϴ�\n\n\n");	
		printf("   ����������, ���� ������ �����ðڽ��ϱ�? \n\n");
		printf("   1. ��. ���� ������ ��ڽ��ϴ�.\n");
		printf("   2. �ƴϿ�. �������ʹ� ���Ӽ����� ���� �ʰڽ��ϴ�. : ");
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
			printf("\n   %s��(��) %d �Է��Ͽ����ϴ�~\n",students[i].name,num);
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