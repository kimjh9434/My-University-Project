#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#include "ILoveSoju.h"

const char* TITLE1[3] = { "��", "��", "��" };


//����
//void PrintWhoTurn:  :1�� �º��� ���� ��ǻ���� ������ ������ �Լ�
//void PrintHands1     : ���� ��ǻ�Ͱ� �� ��츦 ���
//void WhoWins1        : 1�� �º� �� �����̰���� �˷��ִ� �Լ�
//int GetcomputerHand : ��ǻ�Ͱ� ������ ���� �˷��ִ� �����Լ�



int Game_RCP(Student (*students),int drinker)
{
	int myHand; // ����ڰ� �� �����
	int computerHand; // ��ǻ�Ͱ� �� �����
	int whoIsTurn1 = 0; // 0 : ��� 1 : �����, 2 : ��ǻ�� 
	int computer;
	static int gameExplanation=1;//���Ӽ���.



	// ���� �� ����


	system("cls");

	

	printf("\n\n   6. ���������\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   ���� ���� \n\n");
		printf("   1. ������������ ���� ������ �����մϴ�.\n");
		printf("   2.������ ���ϸ� �ٽ� ������������ �մϴ�.\n");
		printf("   3. �����λ���� ����ϴ� ����̶� �������� ���� �¸��մϴ�\n");
		printf("   4. ������ ����� ����ϴ»������ �̱�� ���ݱ��� �����մϴ�\n");
		printf("   5. �����λ���� ����ϴ»������ ���� ���ݱ��� �Ѿ�ϴ�.\n\n\n");

		printf("   ����������, ���� ������ �����ðڽ��ϱ�? \n\n");
		printf("   1. ��. ���� ������ ��ڽ��ϴ�.\n");
		printf("   2. �ƴϿ�. �������ʹ� ���Ӽ����� ���� �ʰڽ��ϴ�. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}

	srand((unsigned int)time(NULL));


	computer = rand()%5;
	while(drinker == computer)
	{
		computer = rand()%5;
	}

	printf("   %s��  %s �� �����Ѵ�\n", students[drinker].name,students[computer].name);


	printf("   ���� ����!\n");
	while(whoIsTurn1 == 0) // ���ٸ� ��ӳ����� �Ѵ�.(�ֳ��ϸ� ó�� �����������̱⶧���� ���������ϱ� ���ؼ�)
	{
		if(drinker==0)
		{
			printf("\n   0->��, 1->��, 2->��:");
			myHand=Get_Only_Number();
			while(myHand < 0 || myHand > 2)
			{
				printf("\n   0~2������ ���� �Է��ϼ���.\n");
				printf("\n   0->��, 1->��, 2->��:");
				myHand=Get_Only_Number();
			}
			computerHand=rand()%3;
		}
		else if(computer ==0)
		{
			printf("\n   0->��, 1->��, 2->��:");
			computerHand=Get_Only_Number();
			while(computerHand < 0 || computerHand > 2)
			{
				printf("\n   0~2������ ���� �Է��ϼ���.\n");
				printf("\n   0->��, 1->��, 2->��:");
				computerHand=Get_Only_Number();
			}
			myHand=rand()%3;
		}else
		{
			myHand=rand()%3;
			computerHand=rand()%3;
		}
		

		// ���� ������ ����Ѵ�.
		printf("   %6s : %s\n", students[drinker].name, TITLE1[myHand]);
		printf("   %6s : %s\n\n", students[computer].name, TITLE1[computerHand]);
		if(drinker!=0&&computer!=0)Sleep(2000);
		whoIsTurn1 = WhoWins1(myHand, computerHand); // ���� �̰���� �Ǻ����ش�.


		// 0�� ��� �����̰�, 1�� ��� ���� �¸�,  2�� ��� ��ǻ�Ͱ� �¸�.
		if(whoIsTurn1 == 0)
		{	
			printf("   �����ϴ�..\n\n\n");

			if(drinker!=0&&computer!=0)Sleep(2000);
		}
		//while(whoIsTurn1 == 0)
	}
	// ------------------------------------------------------------------------------------------ ������� �������ϱ�

	while(whoIsTurn1 != 0) // �������� ��涧���� ����Ѵ�, �� ��������� ���� �����ɳ��� ������ ���̳Ŀ� ���� ���°��̴�.
	{
		if(whoIsTurn1 == 1)
		{
			printf("\n   %s�� ��!!\n\n", students[drinker].name);
		}
		else if(whoIsTurn1 == 2)
		{
			printf("\n   %s�� ��!!\n\n",students[computer].name);
		}
		if(drinker ==0)
		{
			printf("   0->��, 1->��, 2->��:");

			myHand=Get_Only_Number();
			while(myHand < 0 || myHand > 2)
			{
				printf("\n   0~2������ ���� �Է��ϼ���.\n");
				printf("\n   0->��, 1->��, 2->��:");
				myHand=Get_Only_Number();
				
			}
			computerHand=rand()%3;
			if(drinker!=0&&computer!=0)Sleep(2000);
		}
		else if(computer ==0)
		{
			printf("   0->��, 1->��, 2->��:");

			computerHand=Get_Only_Number();
			while(computerHand < 0 || computerHand > 2)
			{
				printf("\n   0~2������ ���� �Է��ϼ���.\n");
				printf("\n   0->��, 1->��, 2->��:");
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

		printf("\n\n   ------------------------------�߰����------------------------------\n\n");


		printf("   %6s : %s\n", students[drinker].name, TITLE1[myHand]);
		printf("   %6s : %s\n", students[computer].name, TITLE1[computerHand]);
		if(drinker!=0&&computer!=0)Sleep(2000);

		printf("\n\n   ------------------------------�߰����------------------------------\n\n");
		whoIsTurn1=WhoWins1(myHand, computerHand);
		switch(whoIsTurn1) // ���� ���� �̰���� �Ǻ��Ѵ�.
		{
		case 0: // �������
			Sleep(1000);
			if(whoIsTurn1 == 1) // �����̿��ٸ�
			{
				printf("   �ڡڡڡڡڡڡڡڡ�%s�� �¸��ڡڡڡڡڡڡڡڡ�\n",students[drinker].name); // ���� �¸��Ѱ��̿���
				getchar();

				Sleep(4000);
			}
			else if(whoIsTurn1 == 2) // ��ǻ�����̿��ٸ�
			{
				printf("   �͢͢͢͢͢͢͢͢͢�%s�� �й�͢͢͢͢͢͢͢͢�\n",students[computer].name); // ��ǻ�Ͱ� �¸��Ѱ��̴�.
				drinker=computer;
				getchar();
				Sleep(4000);
			}break;
		default:break;
		}
	}

	return drinker;
}




// 0 : ��� , 1 : ����ڰ� �̱�, 2 : ��ǻ�Ͱ� �̱�


int WhoWins1(int myHand, int computerHand)
{ 
	int ret;//��ȯ��

	//���� �� �Ͱ� ��ǻ�Ͱ� �� ���� ������,
	if(myHand == computerHand)
	{
		ret = 0;// ���. 0�� ��ȯ
	}
	// ���� ���϶� ��ǻ�Ͱ� �����̰ų�,  ���� �����϶� ��ǻ�Ͱ� ���̰ų�, ���� ���϶� ��ǻ�Ͱ� ���̸�
	else if((myHand == 0 && computerHand == 1) || (myHand == 1 && computerHand == 2) || (myHand == 2 && computerHand == 0))
	{
		ret = 1; // ���� �¸�. 1�� ��ȯ
	}
	else
	{
		ret = 2; // �ƴ϶�� ��ǻ�� �¸�. 2�� ��ȯ
	}
	return ret;
}