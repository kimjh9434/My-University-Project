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
	static int gameExplanation=1;//���Ӽ���


	srand((unsigned int)time(NULL));

	system("cls");
	printf("\n\n   5. ����Ų���31\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   ���� ���� \n\n");
		printf("   -1���� �����Ͽ� ���� �ִ� 3 ���ھ� �θ���.\n");
		printf("   -�������� 31�� �θ��� ����� �й�.\n\n\n");
		
		printf("   ����������, ���� ������ �����ðڽ��ϱ�? \n\n");
		printf("   1. ��. ���� ������ ��ڽ��ϴ�.\n");
		printf("   2. �ƴϿ�. �������ʹ� ���Ӽ����� ���� �ʰڽ��ϴ�. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}

	system("cls");
	printf("\n   =======================================\n");
	printf("   �ססססס׺���Ų��� 31�ססססס�\n");
	printf("   =======================================\n");
	printf("   �١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١�\n");
	printf("\n\n");
	printf("   ������ �����մϴ�!");
	printf("\n\n");
	//----------------------------------------------------

	count=drinker;
	while(numberSum<31)
	{
		index=count%5;
		if(index==0)
		{
			printf("\n   1���� 3������ ���ڸ� �Է��ϼ���!:");
			do{
				number=Get_Only_Number();
				if(number < 1 || number > 3)
				{
					inputOK=0;
					printf("\n   ����� �Է��Ͻ���.\n");
					printf("\n   1���� 3������ ���ڸ� �Է��ϼ���! : ");
				}
				else inputOK=1;


			}while(inputOK==0); // ����ó��
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
		while(i<=31 && i < max )//�ʱ��1
		{
			if(i==31)
			{
				count--;				
			}
			printf("%d ", i);//�� ���
			numberSum++;
			i++;
		}
		printf("\n");
		count++;
		Sleep(500);
	}

	//---------------------- �ڽ��� �Ѱ�

	fflush(stdin);

	drinker = index;

	return drinker;

}