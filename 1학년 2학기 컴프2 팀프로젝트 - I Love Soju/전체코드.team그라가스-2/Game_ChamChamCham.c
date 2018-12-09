//Game_ChamChamCham.c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#include "ILoveSoju.h"



const char *TITLE[5] = {"��", "��", "��"};
const char *TITLE2[5] = { "����", "���", "������" };


//																			���� : void PrintWhoTurn    :  1�� �º��� ���� ��ǻ���� ������ ������ �Լ�
//																			       void PrintHands      : ���� ��ǻ�Ͱ� �� ��츦 ���
//																		        	void WhoWins        : 1�� �º� �� �����̰���� �˷��ִ� �Լ�
//																			      int GetcomputerHand   : ��ǻ�Ͱ� ������ ���� �˷��ִ� �����Լ�
//																		        	void main           : ����

int Game_ChamChamCham(Student (*students), int drinker)
{
	int myHand; // ����ڰ� �� �����
	int computerHand; // ��ǻ�Ͱ� �� �����
	int whoIsTurn = 0; // 0 : ��� 1 : �����, 2 : ��ǻ�� 

	int computer;
	static int gameExplanation=1;//���Ӽ���.

	system("cls");
	printf("\n\n   7. �ססס�����������!�ססס�\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   ���� ���� \n\n");
		printf("   1. ������������ ���� ������ �����մϴ�.\n");
		printf("   2.������ �����ϸ� ����,������, ��� �� �Ѱ��� ���ϴ�\n");
		printf("   3. �����λ���� ����ϴ»���� ������ ���߸� �¸��մϴ�.\n");
		printf("   4. ���� ���н� ���� �Ѿ�ϴ�.\n\n\n");

		printf("   ����������, ���� ������ �����ðڽ��ϱ�? \n\n");
		printf("   1. ��. ���� ������ ��ڽ��ϴ�.\n");
		printf("   2. �ƴϿ�. �������ʹ� ���Ӽ����� ���� �ʰڽ��ϴ�. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}

	srand((unsigned int)time(NULL));

	// ���� �� ����
	computer = rand()%5;
	while(drinker == computer)
	{
		computer = rand()%5;
	}
	printf("   %s��  %s �� �����Ѵ�\n", students[drinker].name,students[computer].name);

	printf("   ���� ����!\n");

	while(whoIsTurn == 0) // ���ٸ� ��ӳ����� �Ѵ�.
	{
		if(drinker==0)
		{
			printf("   0->����, 1->����, 2->��:");

			myHand=Get_Only_Number();

			while(myHand < 0 || myHand > 2)
			{
				printf("\n   0~2������ ���� �Է��ϼ���.\n");
				printf("   0->����, 1->����, 2->��:");
				myHand=Get_Only_Number();
			}
			computerHand=rand()%3;
		}else if(computer==0){
			printf("   0->����, 1->����, 2->��:");

			computerHand=Get_Only_Number();

			while(computerHand < 0 || computerHand > 2)
			{
				printf("\n   0~2������ ���� �Է��ϼ���.\n");
				printf("   0->����, 1->����, 2->��:");
				computerHand=Get_Only_Number();
			}
			myHand=rand()%3;
		}else 
		{
			myHand=rand()%3;
			computerHand=rand()%3;
		}

		PrintHands(myHand, computerHand,drinker,computer,students); // ���� ������ ����Ѵ�.
		if(drinker!=0&&computer!=0)Sleep(2000);Sleep(2000);
		whoIsTurn = WhoWins(myHand, computerHand); // ���� �̰���� �Ǻ����ش�.

		// 0�� ��� �����̰�, 1�� ��� ���� �¸�,  2�� ��� ��ǻ�Ͱ� �¸�

		if(whoIsTurn == 0)
		{
			printf("   �����ϴ�.\n\n\n");
		}
	}

	// ------------------------------------------------------------------------------------------ ������� �������ϱ�

	while(whoIsTurn != 0) // �������� ������ ���� ���� �Ѵ�. ������������ ���� �����϶� �ºΰ� �����ȴ�.
	{
		
		PrintWhoTrun(whoIsTurn,students,drinker,computer); // ���� ���� ������ ����Ѵ�.
		if(drinker == 0)
		{
			printf("\n\n   ��!��!��!!\n");
			printf("   0->����, 1->���, 2->������:");

			myHand=Get_Only_Number();
			while(myHand < 0 || myHand > 2)
			{
				printf("\n   0~2������ ���� �Է��ϼ���.\n");
				printf("   0->����, 1->���, 2->������:");
				myHand=Get_Only_Number();
				
			}
			computerHand=rand()%3;
		}else if(computer==0)
		{
			printf("\n\n   ��!��!��!!\n");
			printf("   0->����, 1->���, 2->������:");

			computerHand=Get_Only_Number();
			while(computerHand < 0 || computerHand > 2)
			{
				printf("\n   0~2������ ���� �Է��ϼ���.\n");
				printf("   0->����, 1->���, 2->������:");
				computerHand=Get_Only_Number();
				
			}
			myHand=rand()%3;
		}else
		{
			myHand=rand()%3;
			computerHand=rand()%3;
		}

		printf("   ------------------------------�߰����------------------------------\n\n");

		PrintHands2(myHand, computerHand, drinker,  computer,students);
		if(drinker!=0&&computer!=0)Sleep(2000);Sleep(2000);
	
		printf("   ------------------------------�߰����------------------------------\n\n");
  
		switch(WhoWins(myHand, computerHand)) // ���� ���� �̰���� �Ǻ��Ѵ�.
		{
		
		case 0: // �������
			if(whoIsTurn == 1) // �����̿��ٸ�
			{
				printf("   �ڡڡڡڡڡڡڡڡ�%s�� �¸��ڡڡڡڡڡڡڡڡ�\n",students[drinker].name); // ���� �¸��Ѱ��̿���
				drinker = computer;getchar();
			}
			else if(whoIsTurn == 2) // ��ǻ�����̿��ٸ�
			{
				printf("   �͢͢͢͢͢͢͢͢͢�%s�� �¸��͢͢͢͢͢͢͢͢�\n", students[computer].name); // ��ǻ�Ͱ� �¸��Ѱ��̴�.
				
				getchar();
			}
			Sleep(3000);
			whoIsTurn = 0; // ���ٰ� 0�� ����
			break;
  
		case 1: 
			whoIsTurn = 2; // ���� ���̿����� 2���� = ��ǻ�� ���� 
			break;
  
		case 2:
			whoIsTurn = 1; // ��ǻ���� ���̿����� 1���� = ���� ����
			break;
		}
	}
	return drinker;

}


void PrintWhoTrun(const int whoIsTurn,Student (*students),int drinker,int computer)
{
	if(whoIsTurn == 1)
		printf("\n   %s�� ��!!\n",students[drinker].name);
	
	else if(whoIsTurn == 2)
		printf("\n   %s�� ��!!\n",students[computer].name);
}

// ������ : 1  ��ǻ���� �� : 2



void PrintHands(int myHand, int computerHand, int drinker, int computer,Student (*students))
{
	printf("   %6s : %s\n", students[drinker].name, TITLE[myHand]);
	printf("   %6s : %s\n\n", students[computer].name, TITLE[computerHand]);
	
}
// 0 : ��� , 1 : ����ڰ� �̱�, 2 : ��ǻ�Ͱ� �̱�

void PrintHands2(int myHand, int computerHand,int drinker, int computer,Student (*students))
{
	printf("   %6s : %s\n", students[drinker].name, TITLE2[myHand]);
	printf("   %6s : %s\n\n", students[computer].name, TITLE2[computerHand]);
	
}
// 0 : ��� , 1 : ����ڰ� �̱�, 2 : ��ǻ�Ͱ� �̱�


int WhoWins(const int myHand, const int computerHand)
{ 
 // ������� 0�� ����.
 
	if(myHand == computerHand)
  
	 return 0;
 // ���� ���϶� ��ǻ�Ͱ� �����̰ų�,  ���� �����϶� ��ǻ�Ͱ� ���̰ų�, ���� ���϶� ��ǻ�Ͱ� ���̸�
 
 
	if((myHand == 0 && computerHand == 1) || (myHand == 1 && computerHand == 2) || (myHand == 2 && computerHand == 0))
  
		return 1; // ���� �¸�
 
	else
 
		return 2; // �ƴ϶�� ��ǻ�� �¸�
}



