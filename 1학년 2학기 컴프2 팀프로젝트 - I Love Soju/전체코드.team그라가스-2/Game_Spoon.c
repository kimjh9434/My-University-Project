//���� I Love Soju_���̼�����(����������)
//Game_Spoon.c

#include <stdio.h>
#include <windows.h>
#include <time.h>
#include "ILoveSoju.h"

int Game_Spoon(Student (*students),int drinker)
{
	int spoons[3][5]={{1,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0}};//������ �迭.
	//spoons[0][i]:�ո�=1,�޸�=-1, spoons[1][i]����=1, ����=2, ��=3, spoons[2][i]��=0, ��=1, ��=2
	int draw=1;//���º� ���� �Ǵ�
	int index=-1;//������ �������� ���� �Ǵ�
	int i;     //�ݺ������
	static int gameExplanation=1;//���Ӽ���.
	char RockScissorsPaper[3][5]={"����","����","��"};
	char WinDrawLose[3][7]={"�¸�","���º�","�й�"};
	char* previous;
	char* current;
	system("cls");
	printf("\n\n   1. ���̼�����[����������]\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   ���� ���� \n\n");
		printf("   -���� �ڽ��� �������� ���̺� �߾����� ��Ƴ��´�.\n");
		printf("   -5���� ���ÿ� ������������ �Ѵ�.\n");
		printf("   -�������������� ���� ����� �������� �����´�.\n");
		printf("   -�� �Ѹ��� ������ ����(��/�Ʒ�)�� �ٸ� ������ �ݺ��Ѵ�.\n\n\n");

		printf("   ����������, ���� ������ �����ðڽ��ϱ�? \n\n");
		printf("   1. ��. ���� ������ ��ڽ��ϴ�.\n");
		printf("   2. �ƴϿ�. �������ʹ� ���Ӽ����� ���� �ʰڽ��ϴ�. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}

	srand(time(NULL));

	printf("   ó���� ��Ȳ.\n");
	for(i=0;i<5;i++)
	{
		//��� ���� : "ȫ�浿" : ���������� : �ո� 
		printf("   \"%s\" : ���������� : %s\n",students[i].name, "�ո�");
	}

	while(index==-1)//������ �������� ��������, �ݺ��Ѵ�. 
	{
		while(draw==1)//���ºΰ� ����Ǵ� ����, ������������ ��� �Ѵ�.
		{
			printf("\n\n");
			Input(spoons);

			printf("\n\n");
			printf("   ���������� ���� \n");
			draw=GameDecision(spoons);

			for(i=0;i<5;i++)
			{
				if(spoons[2][i]==2){
					if(spoons[0][i]>0){
						previous="�޸�";current="�ո�";
					}else{
						previous="�ո�";current="�޸�";
					}
				}else{
					if(spoons[0][i]>0){
						previous="�ո�";current="�ո�";
					}else{
						previous="�޸�";current="�޸�";
					}
				}
				//��� ���� : 'ȫ�浿'�� �� �� : '��'   �� '��'    ������ ���� : '�ո�' -> '�޸�' 
				printf("   \"%s\"�� �� �� : %4s  ��%6s   ���������� : %s �� %s \n",students[i].name,RockScissorsPaper[spoons[1][i]-1],
					WinDrawLose[spoons[2][i]],previous,current);
			}
			printf("\n   ===============================================================\n");
			printf("\n");
			if(draw==1)
			{
				printf("   �����ϴ�. �ٽ� ������������ �մϴ�. \n");
			}
		}
		index=GoingDecision(spoons);

		if(index==-1)
		{
			printf("   ������ ���� ������ �ʾҽ��ϴ�. �ٽ� ������������ �մϴ�. \n");
		}
		draw=1;
		printf("\n");
	}
	drinker=index;

	return drinker;
}

void Input(int (*spoons)[5])
{
	int number;
	int team1;
	int team2;
	int i=1;
	
	printf("   ���������� �� ����������� �Է��ϼ���.\n");
	printf("   1.���� 2.���� 3.�� : ");
	spoons[1][0]=Get_Only_Number();

	while(spoons[1][0]<1 || spoons[1][0]>3)//�߸� �Է¹޾�����, ����� �Է¹��������� �Է¹ޱ�.
	{
		printf("   �߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���.\n\n"); 
		printf("   1.���� 2.���� 3.�� : ");
		spoons[1][0]=Get_Only_Number();
	}

	srand( (unsigned)time( NULL ) );

	number=rand()%3+1;
	team1=rand()%3+1;
	team2=rand()%3+1;
	while(number>0)
	{
		spoons[1][i]=team1;
		i++;
		number--;
	}
	while(i<5)
	{
		spoons[1][i]=team2;
		i++;
	}
}

int GameDecision(int (*spoons)[5])
{
	int scissors=0;//����
	int rock=0;//����
	int paper=0;//��
	int draw=0;//���º� ����
	int i;//�ݺ������

	for(i=0;i<5;i++)//5�� �ݺ��ϸ鼭 �� ����, ����, ���� ������ ����.
	{
		switch(spoons[1][i])
		{
		case 1 : scissors++; break;
		case 2 : rock++; break;
		case 3 : paper++; break;
		default : break;
		}
	}
	//��� ���� �Ǵ� ���� �Ǵ� ���� ���� �����鼭[1������ �������� ����]
	//����, ����, �� 3�� ��� 0�� �ƴѰ�찡 �ƴҶ�,[3���� ��� �������� ����] 
	if(!(scissors==5 || rock==5 || paper==5) && !(scissors !=0 && rock !=0 && paper !=0))
	{
		if(scissors==0)//������ 0���̸�,
		{//����(=2)�� ��(=3)�� ��. �������� ��������.
			for(i=0;i<5;i++)
			{
				if(spoons[1][i]==2)
				{
					spoons[0][i]*=(-1);
					spoons[2][i]=2;
				}
				else
				{
					spoons[2][i]=0;
				}
			}
		}
		else if(rock==0)//������ 0���̸�,
		{//��(=3)�� ����(=1)�� ��. �������� ��������.
			for(i=0;i<5;i++)
			{
				if(spoons[1][i]==3)
				{
					spoons[0][i]*=(-1);
					spoons[2][i]=2;
				}
				else
				{
					spoons[2][i]=0;
				}
			}
		}
		else//���� 0���̸�,
		{//����(=1)�� ����(=2)�� ��. �������� ��������.
			for(i=0;i<5;i++)
			{
				if(spoons[1][i]==1)
				{
					spoons[0][i]*=(-1);
					spoons[2][i]=2;
				}
				else
				{
					spoons[2][i]=0;
				}
			}
		}
	}
	else//���º� ó��
	{
		draw=1;
		for(i=0;i<5;i++)
		{
			spoons[2][i]=1;
		}

	}

	return draw;
}

int GoingDecision(int (*spoons)[5])
{
	int count=0;   //���� ������ ���� ����
	int index=-1; //�� ���� ���. ���ӿ� �ɸ� ��� ����.
	int i;         //�ݺ������

	for(i=0;i<5;i++)
	{
		if(spoons[0][i]==1)//������ ���°� �ո��� ������� ���� ����.
		{
			count++;
		}
	}
	i=0;
	if(count==1)//���� ������ ���°� �ո��� ������� ���� �Ѹ��̶��,
	{
		index++;
		while(i<5 && spoons[0][i]!=1)
		{
			index++;
			i++;
		}
	}
	else if(count==4)
	{
		index++;
		while(i<5 && spoons[0][i]!=-1)
		{
			index++;
			i++;
		}
	}
	return index;
}

