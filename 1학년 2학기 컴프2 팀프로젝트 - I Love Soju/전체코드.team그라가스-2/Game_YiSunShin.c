//���� I Love Soju_�̼��Ű���
//Game_YiSunShin.c

#include <stdio.h>
#include <time.h>
#include <windows.h>
#include "ILoveSoju.h"

int Game_YiSunShin(Student (*students),int drinker)
{
	int glasses[10]={0,};//���ܵ�
	int headsCoin;//�ո��
	int index=drinker;//����
	int gameCount=1;//��ü ���� Ƚ��
	int i=0;//�ݺ������
	int drink;
	static int gameExplanation=1;//���Ӽ���.	
	system("cls");
	printf("\n\n   2. �̼��Ű���\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   ���� ���� \n\n");
		printf("   -10���� ���ܰ� �̼����� �׷��� ���¥�� ���� 10���� �غ��մϴ�.\n");
		printf("   -5���̼� �� ������ ���ư��鼭 ������ ���ϴ�.\n");
		printf("   -������ ���� ���� �̼����� �׷��� �ո��� ������ ���ϴ�.\n");
		printf("   -�׸��� ���⼭ 10���� ���� �� �ո��� ���� ��°�� �ش��ϴ� ���ܿ�\n");
		printf("     ���� ����� ���� ���ܿ� ���� ������, �̹� �� ������ ä���� ������\n");
		printf("     ������ �� ������ ������ ����� �� ������ ���ż� ���ϴ�.\n\n\n");

		printf("   ����������, ���� ������ �����ðڽ��ϱ�? \n\n");
		printf("   1. ��. ���� ������ ��ڽ��ϴ�.\n");
		printf("   2. �ƴϿ�. �������ʹ� ���Ӽ����� ���� �ʰڽ��ϴ�. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}
	printf("  ���� ������Ȳ  [��:����. ��:ä���� ��]\n");
	printf("   �� �� �� �� �� �� �� �� �� ��\n");
	printf("   �� �� �� �� �� �� �� �� �� ��\n\n\n");

	printf("  1��° ����\n\n");
	

	while(gameCount<5)
	{
		headsCoin=Pitch_Coin();
		printf("   %s�� ������ ������, �̼���[�ո�]�� %d�� ���Խ��ϴ�.\n",students[index].name,headsCoin);

		if(glasses[headsCoin-1]==0)
		{
			glasses[headsCoin-1]=1;
			printf("   %d��° ������ ������Ƿ�, ������ �����ϴ�.\n",headsCoin);
			printf("  ���� ������Ȳ  [��:����. ��:ä���� ��]\n");
			printf("   �� �� �� �� �� �� �� �� �� ��\n   ");
			for(i=0;i<10;i++)
			{
				if(glasses[i]==0){
					printf("�� ");
				}else{
					printf("�� ");
				}
			}printf("\n\n\n");
			Sleep(1000);
		}
		else if(gameCount!=5)
		{
			printf("\n   %d��° ������ ä���� �־����Ƿ� %s�� ��Ģ�� �ɷȽ��ϴ�.\n\n\n",headsCoin,students[index].name);
			drink=Intro();
			students[index].drinkingCapacity+=drink;
			glasses[headsCoin-1]=0;
			printf("\n\n   %s�� %d��° ������ ������ ���ż� ������ϴ�.\n\n",students[index].name,headsCoin);
			printf("  ���� ������Ȳ  [��:����. ��:ä���� ��]\n");
			printf("   �� �� �� �� �� �� �� �� �� ��\n   ");
			for(i=0;i<10;i++)
			{
				if(glasses[i]==0){
					printf("�� ");
				}else{
					printf("�� ");
				}
			}printf("\n\n\n");

			Sleep(1000);
			getchar();
			gameCount++;
			printf("  %d��° ����\n\n",gameCount);
		}
		index++;
		index=index%5;
	}
	return index;
}

int Pitch_Coin()
{
	int randomNumber;
	int headsCoin=0;
	int i;

	srand(time(NULL));

	for(i=0;i<10;i++)
	{
		randomNumber=rand()%101;

		if(randomNumber>50)
		{
			headsCoin++;
		}
	}
	return headsCoin;
}
