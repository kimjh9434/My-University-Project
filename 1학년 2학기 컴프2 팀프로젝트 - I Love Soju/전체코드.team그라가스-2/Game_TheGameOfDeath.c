#include <stdio.h>//�����ӿ��굥��
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
	printf("\n\n   4.�� ���� ���� ����\n");
	printf("\n   ############################################################\n\n");

	if(gameExplanation==1)
	{
		printf("   ���� ����.\n\n");
		printf("   �����ϰ� ���� �÷��̾ ������ �Ŀ�\n");
		printf("   ���� Ƚ�� ��ŭ ���󰡴ٰ� ������ Ƚ���� ���� ����\n");
		printf("   ����� ��Ģ�ڰ� �Ǵ� �����Դϴ�\n\n\n");
		printf("   ����������, ���� ������ �����ðڽ��ϱ�? \n\n");
		printf("   1. ��. ���� ������ ��ڽ��ϴ�.\n");
		printf("   2. �ƴϿ�. �������ʹ� ���Ӽ����� ���� �ʰڽ��ϴ�. : ");
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

	printf("\n   ����� %s ��(��)���� \n\n", students[player[0]].name);

	for(i=1;i<5;i++)
	{
		printf("   %s��(��) %s ��(��)���� \n",students[i].name, students[player[i]].name);
		printf("   \n");
	}
	Sleep(3000);

	i=0;
	k=1;

	while(k<play+1)
	{		
		result=player[i];
		printf("\n   %s  ��  %s  %d��~\n",students[i].name, students[player[i]].name,k);
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
	printf("\n   �����ϰ��� �ϴ� �÷��̾��� ��ȣ �Է� \n\n");
	printf("   (%s = 1, %s = 2, %s = 3, %s = 4) : ", students[1].name, students[2].name, students[3].name, students[4].name);

	do
	{
		*num=Get_Only_Number();
		if(*num<1 || *num>(SIZETHE-1))
			printf("\n   �ٽ� �Է��ϼ��� : ");
	}while(*num<1 || *num>(SIZETHE-1));

	printf("\n   ���ϴ� ȸ�� Ƚ�� �Է� : ");
	do
	{
		*play=Get_Only_Number();
		if(*play<1)
			printf("   �ٽ� �Է��ϼ��� : ");
	}while(*play<1);

}
