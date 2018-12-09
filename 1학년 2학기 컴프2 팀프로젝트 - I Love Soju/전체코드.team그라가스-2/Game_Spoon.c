//컴프 I Love Soju_최이선게임(숟가락게임)
//Game_Spoon.c

#include <stdio.h>
#include <windows.h>
#include <time.h>
#include "ILoveSoju.h"

int Game_Spoon(Student (*students),int drinker)
{
	int spoons[3][5]={{1,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0}};//숟가락 배열.
	//spoons[0][i]:앞면=1,뒷면=-1, spoons[1][i]가위=1, 바위=2, 보=3, spoons[2][i]승=0, 무=1, 패=2
	int draw=1;//무승부 여부 판단
	int index=-1;//술게임 끝났는지 여부 판단
	int i;     //반복제어변수
	static int gameExplanation=1;//게임설명.
	char RockScissorsPaper[3][5]={"가위","바위","보"};
	char WinDrawLose[3][7]={"승리","무승부","패배"};
	char* previous;
	char* current;
	system("cls");
	printf("\n\n   1. 최이선게임[숟가락게임]\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   게임 설명 \n\n");
		printf("   -각자 자신의 숟가락을 테이블 중앙으로 모아놓는다.\n");
		printf("   -5명이 동시에 가위바위보를 한다.\n");
		printf("   -가위바위보에서 지는 사람은 숟가락을 뒤집는다.\n");
		printf("   -단 한명만이 숟가락 방향(위/아래)이 다를 때까지 반복한다.\n\n\n");

		printf("   다음번에도, 게임 설명을 들으시겠습니까? \n\n");
		printf("   1. 예. 게임 설명을 듣겠습니다.\n");
		printf("   2. 아니오. 다음부터는 게임설명을 듣지 않겠습니다. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}

	srand(time(NULL));

	printf("   처음의 상황.\n");
	for(i=0;i<5;i++)
	{
		//출력 예시 : "홍길동" : 숟가락상태 : 앞면 
		printf("   \"%s\" : 숟가락상태 : %s\n",students[i].name, "앞면");
	}

	while(index==-1)//술래가 결정되지 않은동안, 반복한다. 
	{
		while(draw==1)//무승부가 진행되는 동안, 가위바위보를 계속 한다.
		{
			printf("\n\n");
			Input(spoons);

			printf("\n\n");
			printf("   가위바위보 판정 \n");
			draw=GameDecision(spoons);

			for(i=0;i<5;i++)
			{
				if(spoons[2][i]==2){
					if(spoons[0][i]>0){
						previous="뒷면";current="앞면";
					}else{
						previous="앞면";current="뒷면";
					}
				}else{
					if(spoons[0][i]>0){
						previous="앞면";current="앞면";
					}else{
						previous="뒷면";current="뒷면";
					}
				}
				//출력 예시 : '홍길동'이 낸 것 : '보'   ∴ '패'    숟가락 상태 : '앞면' -> '뒷면' 
				printf("   \"%s\"이 낸 것 : %4s  ∴%6s   숟가락상태 : %s → %s \n",students[i].name,RockScissorsPaper[spoons[1][i]-1],
					WinDrawLose[spoons[2][i]],previous,current);
			}
			printf("\n   ===============================================================\n");
			printf("\n");
			if(draw==1)
			{
				printf("   비겼습니다. 다시 가위바위보를 합니다. \n");
			}
		}
		index=GoingDecision(spoons);

		if(index==-1)
		{
			printf("   게임이 아직 끝나지 않았습니다. 다시 가위바위보를 합니다. \n");
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
	
	printf("   가위바위보 중 내고싶은것을 입력하세요.\n");
	printf("   1.가위 2.바위 3.보 : ");
	spoons[1][0]=Get_Only_Number();

	while(spoons[1][0]<1 || spoons[1][0]>3)//잘못 입력받았으면, 제대로 입력받을때까지 입력받기.
	{
		printf("   잘못 입력하셨습니다. 다시 입력하세요.\n\n"); 
		printf("   1.가위 2.바위 3.보 : ");
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
	int scissors=0;//가위
	int rock=0;//바위
	int paper=0;//보
	int draw=0;//무승부 여부
	int i;//반복제어변수

	for(i=0;i<5;i++)//5번 반복하면서 각 가위, 바위, 보의 개수를 센다.
	{
		switch(spoons[1][i])
		{
		case 1 : scissors++; break;
		case 2 : rock++; break;
		case 3 : paper++; break;
		default : break;
		}
	}
	//모두 가위 또는 바위 또는 보를 내지 않으면서[1가지만 나왔을때 제외]
	//가위, 바위, 보 3개 모두 0이 아닌경우가 아닐때,[3가지 모두 나왔을때 제외] 
	if(!(scissors==5 || rock==5 || paper==5) && !(scissors !=0 && rock !=0 && paper !=0))
	{
		if(scissors==0)//가위가 0개이면,
		{//바위(=2)가 보(=3)에 짐. 숟가락이 뒤집혀짐.
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
		else if(rock==0)//바위가 0개이면,
		{//보(=3)가 가위(=1)에 짐. 숟가락이 뒤집혀짐.
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
		else//보가 0개이면,
		{//가위(=1)가 바위(=2)에 짐. 숟가락이 뒤집혀짐.
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
	else//무승부 처리
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
	int count=0;   //동일 숟가락 상태 개수
	int index=-1; //술 마실 사람. 게임에 걸릴 사람 순번.
	int i;         //반복제어변수

	for(i=0;i<5;i++)
	{
		if(spoons[0][i]==1)//숟가락 상태가 앞면인 사람들의 수를 센다.
		{
			count++;
		}
	}
	i=0;
	if(count==1)//만약 숟가락 상태가 앞면인 사람들의 수가 한명이라면,
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

