//컴프 I Love Soju_이순신게임
//Game_YiSunShin.c

#include <stdio.h>
#include <time.h>
#include <windows.h>
#include "ILoveSoju.h"

int Game_YiSunShin(Student (*students),int drinker)
{
	int glasses[10]={0,};//술잔들
	int headsCoin;//앞면수
	int index=drinker;//색인
	int gameCount=1;//전체 게임 횟수
	int i=0;//반복제어변수
	int drink;
	static int gameExplanation=1;//게임설명.	
	system("cls");
	printf("\n\n   2. 이순신게임\n");
	printf("\n   ############################################################\n\n");
	if(gameExplanation==1)
	{
		printf("   게임 설명 \n\n");
		printf("   -10개의 술잔과 이순신이 그려진 백원짜리 동전 10개를 준비합니다.\n");
		printf("   -5명이서 한 바퀴씩 돌아가면서 동전을 흔듭니다.\n");
		printf("   -동전을 흔들고 나서 이순신이 그려진 앞면의 개수를 셉니다.\n");
		printf("   -그리고 여기서 10개의 술잔 중 앞면의 개수 번째에 해당하는 술잔에\n");
		printf("     술이 비었을 경우는 술잔에 술을 따르고, 이미 그 술잔이 채워져 있으면\n");
		printf("     동전을 그 순번에 던졌던 사람이 그 술잔을 마셔서 비웁니다.\n\n\n");

		printf("   다음번에도, 게임 설명을 들으시겠습니까? \n\n");
		printf("   1. 예. 게임 설명을 듣겠습니다.\n");
		printf("   2. 아니오. 다음부터는 게임설명을 듣지 않겠습니다. : ");
		gameExplanation=Get_Only_Number();
		printf("\n\n");
	}
	printf("  현재 술잔현황  [□:빈잔. ■:채워진 잔]\n");
	printf("   ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩\n");
	printf("   □ □ □ □ □ □ □ □ □ □\n\n\n");

	printf("  1번째 게임\n\n");
	

	while(gameCount<5)
	{
		headsCoin=Pitch_Coin();
		printf("   %s가 동전을 던졌고, 이순신[앞면]이 %d개 나왔습니다.\n",students[index].name,headsCoin);

		if(glasses[headsCoin-1]==0)
		{
			glasses[headsCoin-1]=1;
			printf("   %d번째 술잔이 비었으므로, 술잔을 따릅니다.\n",headsCoin);
			printf("  현재 술잔현황  [□:빈잔. ■:채워진 잔]\n");
			printf("   ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩\n   ");
			for(i=0;i<10;i++)
			{
				if(glasses[i]==0){
					printf("□ ");
				}else{
					printf("■ ");
				}
			}printf("\n\n\n");
			Sleep(1000);
		}
		else if(gameCount!=5)
		{
			printf("\n   %d번째 술잔이 채워져 있었으므로 %s가 벌칙에 걸렸습니다.\n\n\n",headsCoin,students[index].name);
			drink=Intro();
			students[index].drinkingCapacity+=drink;
			glasses[headsCoin-1]=0;
			printf("\n\n   %s가 %d번째 술잔의 술잔을 마셔서 비워습니다.\n\n",students[index].name,headsCoin);
			printf("  현재 술잔현황  [□:빈잔. ■:채워진 잔]\n");
			printf("   ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩\n   ");
			for(i=0;i<10;i++)
			{
				if(glasses[i]==0){
					printf("□ ");
				}else{
					printf("■ ");
				}
			}printf("\n\n\n");

			Sleep(1000);
			getchar();
			gameCount++;
			printf("  %d번째 게임\n\n",gameCount);
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
