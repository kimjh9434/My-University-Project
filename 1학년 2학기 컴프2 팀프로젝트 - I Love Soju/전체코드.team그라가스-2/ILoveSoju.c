//ILoveSoju.c
/*
파일이름 : ILoveSoju.c
기    능 : 컴프 팀프로젝트 'I Love Soju'의 내용이다. 술게임하기 등등이 있다.
		  기본적으로 포인터, 구조체, gotoxy를 이용했다.
작 성 자 : 팀명 : '그라가스', 김제헌, 김상민, 김병식, 김태준
작성일자 : 

부연설명 : 매주 금요일마다 새관에 모여서 하루하루 작업해나가기 시작했다.
          각 게임은 별도로 각자 짜고 통합했다.
*/

#include <stdio.h>
#include <time.h>
#include <windows.h>
#include <conio.h>
#include "ILoveSoju.h"

int main(int argc,char* argv[])
{
	int menu=0;
	char stop='n';//프로그램 진행여부. 'y':프로그램 종료, 'n':계속진행
	int myTable=0;
	int i;
	
	char name[15];
	Student students[6][5]=
	{
		{{"",0},{"김제헌",0},{"김태준",0},{"김상민",0},{"김병식",0}},
		{{"",0},{"강길동",0},{"김길동",0},{"박길동",0},{"최길동",0}},
		{{"",0},{"신길동",0},{"구길동",0},{"남길동",0},{"여길동",0}},
		{{"",0},{"나길동",0},{"진길동",0},{"전길동",0},{"이길동",0}},
		{{"",0},{"소길동",0},{"대길동",0},{"오길동",0},{"조길동",0}},
		{{"",0},{"우길동",0},{"주길동",0},{"추길동",0},{"장길동",0}}
	};//이름 결정

	printf("\n\n\n     컴퓨터 공학 프로그래밍 팀프로젝트 'I Love Soju'\n");
	printf("\n     주제 : 술게임                   팀명 : 그라가스\n\n");

	printf("   자신의 이름을 입력하세요. : ");
	Get_String(name,15);
	for(i=0;i<6;i++)
	{
		Strncpy(students[i][0].name,name,sizeof(name));
	}
	
	//게임 설명 및 도입부분. 당신은 ~~로 개파에 갔습니다. ~~~ 등의 상황설명.
	Starting(students[0]);
	system("cls");


	printf("\n   시작은 무조건 술게임부터~!  Go Go~!\n   ");
	getchar();
	Alcoholic_Games(students[0]);

	while(stop!='Y' && stop!='y')
	{
		system("cls");

		menu=Display_MainMenu();//메뉴 보여주고 입력받기.

		switch(menu)
		{
		case 1: Alcoholic_Games(students[myTable]); break;
		case 2: CheckOut_Condition(students[myTable]); break;
		case 3: myTable=Move_Table(students,myTable); break;
		case 4: Go_Bathroom(students[myTable]); break;
		case 5: printf("\n   정말로 집으로 돌아가시겠습니까? 동기들이 서운해 합니다.\n\n   (Y/N) : ");
			stop=getchar(); getchar();
			if(stop=='Y' || stop=='y'){
				printf("   집으로 갔습니다.\n\n\n   ");
			}else{
				printf("   남아서 마저 놉니다.");
			}break;
		default : break;
		}

		
		if(menu==1)
		{
			Increase_DrinkingCapacity(students,myTable);
		}
		if(stop!='Y' && stop!='y')
		{
			stop=Check_DrinkingCapacity(students[myTable]);
			printf("\n\n   엔터(ENTER)를 누르면 다시 메인화면이 나옵니다.\n\n   ");
			while(getchar()!='\n');
		}
	}
	return 0;
}

//메뉴 보여주고 입력받기.
int Display_MainMenu()
{
	int menu=1;
	int enter=0;
	int ch;
	
	printf("\n\n\n     컴퓨터 공학 프로그래밍 팀프로젝트 'I Love Soju'\n");
	printf("\n     주제 : 술게임                   팀명 : 그라가스\n\n");

	gotoxy(10,10); printf("행동선택");
	SetColor(0,15); 
	gotoxy(13,12); printf("1. 술게임 하기");
	SetColor(15,0); 
	gotoxy(13,13); printf("2. 주량상황 확인하기");
	gotoxy(13,14); printf("3. 테이블 옮기기");
	gotoxy(13,15); printf("4. 화장실 가기");
	gotoxy(13,16); printf("5. 집으로 가기");

	removeCursor();
	fflush(stdin);
	while(enter==0)
	{
		if(kbhit())// 키보드가눌려졌다면
		{
			ch=getch();
			if(ch == 0xE0)// 특수키가눌러졌다면ex> 방향키
			{
				ch=getch();
				switch(ch)
				{
				case UP   : 
					menu--; 
					if(menu<1)
					{
						menu=5;
					}
					break ;

				case DOWN : 
					menu++; 
					if(menu>5)
					{
						menu=1; 
					}
					break ;
				}
				switch(menu)
				{
				case 1: 
					SetColor(0,15); 
					gotoxy(13,12); printf("1. 술게임 하기");
					SetColor(15,0);
					gotoxy(13,13); printf("2. 주량상황 확인하기");
					gotoxy(13,16); printf("5. 집으로 가기");
					break;
				case 2: 
					SetColor(0,15); 
					gotoxy(13,13); printf("2. 주량상황 확인하기");
					SetColor(15,0);
					gotoxy(13,12); printf("1. 술게임 하기");
					gotoxy(13,14); printf("3. 테이블 옮기기");
					break;
				case 3:
					SetColor(0,15); 
					gotoxy(13,14); printf("3. 테이블 옮기기");
					SetColor(15,0);
					gotoxy(13,13); printf("2. 주량상황 확인하기");
					gotoxy(13,15); printf("4. 화장실 가기");
					break;
				case 4:
					SetColor(0,15); 
					gotoxy(13,15); printf("4. 화장실 가기");
					SetColor(15,0);
					gotoxy(13,14); printf("3. 테이블 옮기기");
					gotoxy(13,16); printf("5. 집으로 가기");
					break;
				case 5:
					SetColor(0,15); 
					gotoxy(13,16); printf("5. 집으로 가기");
					SetColor(15,0);
					gotoxy(13,12); printf("1. 술게임 하기");
					gotoxy(13,15); printf("4. 화장실 가기");
					break;
				default:break;
				}
			}else if(ch==ENTER)
			{
				enter=1;
			}
		}
	}
	gotoxy(0,17);
	return menu;
}

//1. 술게임하기
void Alcoholic_Games(Student (*students))
{
	int gameNumber=0;
	char game_name[8][17]={"최이선게임","이순신게임","업다운게임","더게임오브데스","베스킨라빈스31","묵찌빠게임","참참참게임","랜덤게임"};
	static int drinker=0;
	int drink;

	system("cls");
	printf("\n\n\n   1. 술게임 하기 \n\n");
	
	printf("   %s가 ",students[drinker].name);Sleep(500);
	printf("좋아하는 ");Sleep(1000);
	printf("랜덤");Sleep(500);
	printf(" Game! ");Sleep(1000);
	printf("무슨");Sleep(500);
	printf(" Game! ");Sleep(1000);
	printf("Game ");Sleep(500);
	printf("Start!\n\n");Sleep(1500);
	
	if(drinker==0){//현재 걸린 사람이 플레이어라면, 
		gameNumber=Display_GameMenu();//게임메뉴 보여주고 입력받기.
	}
	else{//컴퓨터라면,
		gameNumber=rand()%7+1;//바로 랜덤값 받기
	}
	
	switch(gameNumber)
	{
	case 1: drinker=Game_Spoon(students,drinker); break;
	case 2: drinker=Game_YiSunShin(students,drinker); break;
	case 3: drinker=Game_Updown(students,drinker); break;
	case 4: drinker=Game_TheGameOfDeath(students,drinker); break;
	case 5: drinker=Game_BR31(students,drinker); break;
	case 6: drinker=Game_RCP(students,drinker); break;
	case 7: drinker=Game_ChamChamCham(students,drinker); break;
	case 8: drinker=Game_Random(students,drinker); break;
	default:break;
	}

	printf("\n    %s이(가) %s게임에서 걸렸습니다.\n",students[drinker].name,game_name[gameNumber-1]);
	getchar();
	drink=Intro();
	students[drinker].drinkingCapacity+=drink;
}

int Display_GameMenu()
{
	int gameNumber=1;
	int enter=0;
	int ch;

	gotoxy(10,7); printf("게임선택");
	SetColor(0,15); 
	gotoxy(13,9);printf("1. 최이선게임[숟가락게임]");
	SetColor(15,0);
	gotoxy(13,10);printf("2. 이순신게임");
	gotoxy(13,11);printf("3. 업다운게임");
	gotoxy(13,12);printf("4. 더게임오브데스");
	gotoxy(13,13);printf("5. 베스킨라빈스31");
	gotoxy(13,14);printf("6. 묵찌빠게임");
	gotoxy(13,15);printf("7. 참참참게임");
	gotoxy(13,16);printf("8. 랜덤게임(아무거나)");

	removeCursor();
	fflush(stdin);
	while(enter==0)
	{
		if(kbhit())// 키보드가눌려졌다면
		{
			ch=getch();
			if(ch == 0xE0)// 특수키가눌러졌다면ex> 방향키
			{
				ch=getch();
				switch(ch)
				{
				case UP   : 
					gameNumber--;
					if(gameNumber<1)
					{
						gameNumber=8;
					}
					break ;

				case DOWN : 
					gameNumber++;
					if(gameNumber>8)
					{
						gameNumber=1; 
					}
					break ;
				}
				switch(gameNumber)
				{
				case 1: 
					SetColor(0,15); 
					gotoxy(13,9);printf("1. 최이선게임[숟가락게임]");
					SetColor(15,0);
					gotoxy(13,10);printf("2. 이순신게임");
					gotoxy(13,16);printf("8. 랜덤게임(아무거나)");
					break;
				case 2: 
					SetColor(0,15); 
					gotoxy(13,10);printf("2. 이순신게임");
					SetColor(15,0);
					gotoxy(13,9);printf("1. 최이선게임[숟가락게임]");
					gotoxy(13,11);printf("3. 업다운게임");
					break;
				case 3:
					SetColor(0,15); 
					gotoxy(13,11);printf("3. 업다운게임");
					SetColor(15,0);
					gotoxy(13,10);printf("2. 이순신게임");
					gotoxy(13,12);printf("4. 더게임오브데스");
					break;
				case 4:
					SetColor(0,15); 
					gotoxy(13,12);printf("4. 더게임오브데스");
					SetColor(15,0);
					gotoxy(13,11);printf("3. 업다운게임");
					gotoxy(13,13);printf("5. 베스킨라빈스31");
					break;
				case 5:
					SetColor(0,15); 
					gotoxy(13,13);printf("5. 베스킨라빈스31");
					SetColor(15,0);
					gotoxy(13,12);printf("4. 더게임오브데스");
					gotoxy(13,14);printf("6. 묵찌빠게임");
					break;
				case 6: 
					SetColor(0,15); 
					gotoxy(13,14);printf("6. 묵찌빠게임");
					SetColor(15,0);
					gotoxy(13,13);printf("5. 베스킨라빈스31");
					gotoxy(13,15);printf("7. 참참참게임");
					break;
				case 7: 
					SetColor(0,15); 
					gotoxy(13,15);printf("7. 참참참게임");
					SetColor(15,0);
					gotoxy(13,14);printf("6. 묵찌빠게임");
					gotoxy(13,16);printf("8. 랜덤게임(아무거나)");
					break;
				case 8: 
					SetColor(0,15); 
					gotoxy(13,16);printf("8. 랜덤게임(아무거나)");
					SetColor(15,0);
					gotoxy(13,9);printf("1. 최이선게임[숟가락게임]");
					gotoxy(13,15);printf("7. 참참참게임");
					break;
				default:break;
				}
			}else if(ch==ENTER)
			{
				enter=1;
			}
		}
	}

	return gameNumber;
}

int Game_Random(Student (*students),int drinker)
{
	int random;
	srand((unsigned)time(NULL));

	random=rand()%6;

	switch(random)
	{
	case 0: drinker=Game_Spoon(students,drinker); break;
	case 1: drinker=Game_YiSunShin(students,drinker); break;
	case 2: drinker=Game_Updown(students,drinker); break;
	case 3: drinker=Game_TheGameOfDeath(students,drinker); break;
	case 4: drinker=Game_BR31(students,drinker); break;
	case 5: drinker=Game_RCP(students,drinker); break;
	case 6: drinker=Game_ChamChamCham(students,drinker); break;
	default: break;
	}
	return drinker;
}

int Intro()
{
	int random;
	int drink=1;

	srand((unsigned)time(NULL));

	random=rand()%7;

	switch(random)
	{
	case 0 : 
		printf("   마셔라~ ");Sleep(1000);printf("마셔라~!\n ");Sleep(700);
		printf("   마셔라~ ");Sleep(1000);printf("마셔라~!\n\n");Sleep(600);
		printf("   술이 들어간다!\n\n");Sleep(2000);
		printf("   쭉");Sleep(600);printf(" 쭉쭉");Sleep(600);printf(" 쭉쭉!\n");Sleep(600);
		printf("   쭉");Sleep(600);printf(" 쭉쭉");Sleep(600);printf(" 쭉쭉!\n\n");Sleep(600);
		printf("   언제까지");Sleep(600);printf(" 어께춤을");Sleep(600);printf(" 추게할꺼야?\n\n");Sleep(1000);
		printf("   내어깨를 봐.");Sleep(700);printf(" 탈골됬잔아!\n\n");
		break;
	case 1 : 
		printf("   동구 밖~ 과수원샷! 아카시아 꽃이 활짝 투샷!\n"); 
		break;
	case 2 : 
		printf("   원샷을 못하면 장가를 못 가요 아~ 미운 사람!\n"); 
		break;
	case 3 : 
		printf("   병신은 인트로도 없어요. 뿅\n"); 
		break;
	case 4 : 
		printf("   병신은 두잔. 병신은 두잔! \n"); drink=2; 
		break;
	case 5 : 
		printf("   안주 먹을 시간도 없!어!요! \n"); 
		break;
	case 6 : 
		printf("   마셔라~ ");Sleep(1000);printf("마셔라~!\n ");Sleep(700);
		printf("   마셔라~ ");Sleep(1000);printf("마셔라~!\n\n");Sleep(600);
		break;
	case 7: printf("   마시면서~ 배우는~ 랜덤~ 게임~\n");
	case 8: 
		printf("   마셔라~ ");Sleep(1000);printf("마셔라~!\n ");Sleep(700);
		printf("   마셔라~ ");Sleep(1000);printf("마셔라~!\n\n");Sleep(600);
		printf("   술이 들어간다!\n\n");Sleep(2000);
		printf("   쭉");Sleep(600);printf(" 쭉쭉");Sleep(600);printf(" 쭉쭉!\n");Sleep(600);
		printf("   쭉");Sleep(600);printf(" 쭉쭉");Sleep(600);printf(" 쭉쭉!\n\n");Sleep(600);
		printf("   언제까지");Sleep(600);printf(" 어께춤을");Sleep(600);printf(" 추게할꺼야?\n\n");Sleep(1000);
		printf("   내어깨를 봐.");Sleep(700);printf(" 추고 있잖아!\n\n");
	default: break;
	}

	Sleep(2000);
	return drink;
}

void CheckOut_Condition(Student (*students))
{
	int i; //반복제어변수
	int bottle;//마신 병의 수
	int glass;//마신 잔의 수

	system("cls");
	gotoxy(2,1);printf("2. 주량상황 확인하기 \n\n\n");
	printf("   현재상태 \n\n");
	printf("   이름  주량  \n");
	for(i=0;i<5;i++)
	{
		bottle=students[i].drinkingCapacity/8;
		glass=students[i].drinkingCapacity-bottle*8;
		printf("  %6s : %d병 %d잔 마심. 총 %2d잔\n",students[i].name,bottle,glass,students[i].drinkingCapacity);
	}
}

int Move_Table(Student (*students)[5],int myTable)
{
	//gotoxy 사용. 테이블`>테이블로 이동하는 모습 구현.
	//이동시, 기존의 이름들을 자장하면서,여기 기본맴버를고
	int X_MIN=2;
	int Y_MIN=3;
	int X_MAX=76;
	int Y_MAX=22;
	int ch;
	char cha;
	static int x=10;
	static int y=8;
	int x_,y_;
	int space=0;
	int enter=0;
	int spacial=0;//특수키
	int newTable=0;

	system("cls");
	gotoxy(2,1);printf("3. 테이블 옮기기.");
	Print_Box("▩",X_MIN,Y_MIN,X_MAX,Y_MAX);
	Print_Table("▨",12,7,22,10);
	gotoxy(14,8);printf("테이블 1");
	Print_Table("▩",34,7,44,10);
	gotoxy(36,8);printf("테이블 2");
	Print_Table("▧",56,7,66,10);
	gotoxy(58,8);printf("테이블 3");
	Print_Table("▧",12,15,22,18);
	gotoxy(14,16);printf("테이블 4");
	Print_Table("▩",34,15,44,18);
	gotoxy(36,16);printf("테이블 5");
	Print_Table("▨",56,15,66,18);
	gotoxy(58,16);printf("테이블 6");
	
	removeCursor();
	x_=x;
	y_=y;
	SetColor(BLUE,RED);
	gotoxy(x , y); printf("●");

	while(newTable==0)
	{
		if(kbhit())// 키보드가눌려졌다면
		{
			ch=getch();
			switch(ch)// 특수키가눌러졌다면
			{
			case 0xE0 : //방향키가 눌러졌다면,
				ch=getch();
				switch(ch)
				{
				case UP : 
					x_=x;y_=y;
					y--; 
					if(y < Y_MIN+1)
					{
						y = Y_MAX-1;
					}else if(x>=11&&x<=22&&y>=7&&y<=10){
						newTable=1;y++;x_=0;y_=0;
					}else if(x>=34&&x<=44&&y>=7&&y<=10){
						newTable=2;y++;x_=0;y_=0;
					}else if(x>=56&&x<=66&&y>=7&&y<=10){
						newTable=3;y++;x_=0;y_=0;
					}else if(x>=11&&x<=22&&y>=15&&y<=18){
						newTable=4;y++;x_=0;y_=0;
					}else if(x>=34&&x<=44&&y>=15&&y<=18){
						newTable=5;y++;x_=0;y_=0;
					}else if(x>=56&&x<=66&&y>=15&&y<=18){
						newTable=6;y++;x_=0;y_=0;
					}break ;

				case DOWN : 
					x_=x;y_=y;
					y++; 
					if(y > Y_MAX-1)
					{
						y = Y_MIN+1;
					}else if(x>=11&&x<=22&&y>=7&&y<=10){
						newTable=1;y--;x_=0;y_=0;
					}else if(x>=34&&x<=44&&y>=7&&y<=10){
						newTable=2;y--;x_=0;y_=0;
					}else if(x>=56&&x<=66&&y>=7&&y<=10){
						newTable=3;y--;x_=0;y_=0;
					}else if(x>=11&&x<=22&&y>=15&&y<=18){
						newTable=4;y--;x_=0;y_=0;
					}else if(x>=34&&x<=44&&y>=15&&y<=18){
						newTable=5;y--;x_=0;y_=0;
					}else if(x>=56&&x<=66&&y>=15&&y<=18){
						newTable=6;y--;x_=0;y_=0;
					}break ;

				case LEFT : 
					x_=x;y_=y;
					x-=2; 
					if(x < X_MIN+2)
					{
						x = X_MAX-2;
					}else if(x>=11&&x<=22&&y>=7&&y<=10){
						newTable=1;x+=2;x_=0;y_=0;
					}else if(x>=34&&x<=44&&y>=7&&y<=10){
						newTable=2;x+=2;x_=0;y_=0;
					}else if(x>=56&&x<=66&&y>=7&&y<=10){
						newTable=3;x+=2;x_=0;y_=0;
					}else if(x>=11&&x<=22&&y>=15&&y<=18){
						newTable=4;x+=2;x_=0;y_=0;
					}else if(x>=34&&x<=44&&y>=15&&y<=18){
						newTable=5;x+=2;x_=0;y_=0;
					}else if(x>=56&&x<=66&&y>=15&&y<=18){
						newTable=6;x+=2;x_=0;y_=0;
					}break ;

				case RIGHT : 
					x_=x;y_=y;
					x+=2; 
					if(x > X_MAX-2)
					{
						x = X_MIN+2;
					}else if(x>=11&&x<=22&&y>=7&&y<=10){
						newTable=1;x-=2;x_=0;y_=0;
					}else if(x>=34&&x<=44&&y>=7&&y<=10){
						newTable=2;x-=2;x_=0;y_=0;
					}else if(x>=56&&x<=66&&y>=7&&y<=10){
						newTable=3;x-=2;x_=0;y_=0;
					}else if(x>=11&&x<=22&&y>=15&&y<=18){
						newTable=4;x-=2;x_=0;y_=0;
					}else if(x>=34&&x<=44&&y>=15&&y<=18){
						newTable=5;x-=2;x_=0;y_=0;
					}else if(x>=56&&x<=66&&y>=15&&y<=18){
						newTable=6;x-=2;x_=0;y_=0;
					}break ;
				default:break;
				}
				
				if(newTable!=0){
					SetColor(RED,YELLOW);
					
					gotoxy(20,12);printf("테이블 %d로 이동하시겠습니까? (Y, N) : ",newTable);
					cha=getchar();getchar();
					while(cha=='\n'){
						gotoxy(58,12);cha=getchar();
						fflush(stdin);
					}
					if(cha!='Y'&&cha!='y'){SetColor(WHITE,BLACK);
						gotoxy(20,12);printf("\t\t\t\t\t\t\t    ");
						gotoxy(x,y);SetColor(BLUE,RED);printf("●");
						newTable=0;
						
					}
				}
				break;
			default:break;
			}
			SetColor(BLUE,RED);
			gotoxy(x , y); printf("●");

			SetColor(WHITE,BLACK);
			gotoxy(x_,y_); printf("  ");
		}
	}
	students[newTable-1][0].drinkingCapacity=students[myTable][0].drinkingCapacity;
	SetColor(WHITE,BLACK);
	gotoxy(X_MIN,Y_MAX);
	return newTable-1;
}

//화장실 가기
void Go_Bathroom(Student (*students))
{
	static int count=0;
	system("cls");
	gotoxy(2,1);printf("4. 화장실 가기\n\n");

	printf("   화장실에 가셨습니다.\n\n");

	if(count==0){
		printf(" 숙취가 어느정도 완화됩니다.\n\n");
		if(students[0].drinkingCapacity>0){
			students[0].drinkingCapacity--;
		}
		count++;
	}else if(count==5){
		count=count-5;
	}else{
		count++;
	}
}


char Check_DrinkingCapacity(Student (*students))
{
	int i=1;//반복제어 변수
	int sum_drink=0;//합
	int ave_drink;//평균
	static int index=0;//색인
	static char names[13][10]={"서길동","방길동","공길동","문길동","엄길동","천길동","정길동","차길동","민길동","유길동","임길동","한길동","노길동"};

	if(students[0].drinkingCapacity>=24)
	{
		printf("   너무 많이 마셔서 취했습니다. 집으로 갔습니다.\n");
		return 'y';
	}
	for(i=0;i<5;i++)
	{
		sum_drink+=students[i].drinkingCapacity;
	}
	ave_drink=sum_drink/5;

	for(i=1;i<5;i++)
	{
		if(students[i].drinkingCapacity>=24)
		{
			printf("   %s이(가) 만취 했습니다. 집으로 갔습니다. \n", students[i].name);

			Strncpy(students[i].name,names[index],10);
			students[i].drinkingCapacity=ave_drink;

			printf("   뉴페이스 %s(가) 이 테이블에 앉았습니다. \n", students[i].name);
			index++;
		}
	}
	return 'n';
}

void Increase_DrinkingCapacity(Student (*students)[5],int myTable)
{
	int index;
	int random;

	for(index=0;index<6;index++)
	{
		if(index!=myTable)
		{
			random=rand()%4+1;
			students[index][random].drinkingCapacity+=1;
		}
	}
}

//10자리의 정수를 입력받을수 있는 함수.
int Get_Only_Number()
{
	char s[11]="";
	int i=0;
	char c='0';
	int number;

	while(i<10 && c!='\n')
	{
		c=getchar();
		if(c>='0' && c<='9')
		{
			s[i]=c;
			i++;
		}
	}
	s[i]=0;
	number=atoi(s);

	return number;
}

//size 만큼의 문자열을 입력받을수 있는 함수.
int Get_String(char (*string), int size)
{
	int length=0;
	char c=getchar();

	//printf("input string(size : %d) : ",size);

	while( length < size-1 && c!='\n')
	{
		string[length]=c;
		length++;
		c=getchar();
	} 

	string[length]=0; //문자열 뒤에 문자의'끝'을 알리는 null 문자 표시하고.

	fflush(stdin); //현재 버퍼 비우기

	return length; //현재 입력받은 문자열의 사용량 반환하기
}
//string_coppy의 지정된 길이[size-1] 보다 작으면서, string의 NULL문자를 만나기 전까지, 문자열을 복사합니다.
//문자열 string의 값을 size-1만큼만 string_coppy로 복사함.
void Strncpy(char (*string_coppy), char (*string), int size)
{
	int i=0;
	while(i<size-1 && string[i]!=0)
	{
		string_coppy[i]=string[i];
		i++;
	}
	string_coppy[i]=0;
}

//화면에 테두리 그리기
Print_Box(char ch[],int X_MIN,int Y_MIN,int X_MAX,int Y_MAX)
{
	int i, j;
	for (i=X_MIN;i<=X_MAX;i+=2)
	{   
		for(j=Y_MIN;j<=Y_MAX;j++)
		{
			if(i==X_MIN || i==X_MAX || j==Y_MAX || j==Y_MIN)
			{
				gotoxy(i,j);
				printf("%s",ch);
			}
		}
	}
}

Print_Table(char ch[],int X_MIN,int Y_MIN,int X_MAX,int Y_MAX)
{
	int i, j;
	for (i=X_MIN;i<=X_MAX;i+=2)
	{   
		for(j=Y_MIN;j<=Y_MAX;j++)
		{
			gotoxy(i,j);
			printf("%s",ch);
			
		}
	}
}

// 커서좌표이동
void gotoxy(unsigned int x, unsigned int y)
{
    COORD xy = {x, y} ;
    SetConsoleCursorPosition( GetStdHandle(STD_OUTPUT_HANDLE) , xy  );
}

//커서 깜빡임 없애주는 것.
void removeCursor(void) 
{
	CONSOLE_CURSOR_INFO cur;
	GetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE),&cur);
	cur.bVisible=0;
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE),&cur);
}

// 테스트색상변경
void SetColor(int font, int back)
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), back*16+font);
}

//스타팅 인트로부분
void Starting(Student (*students))
{
	system("cls");
	printf("\n\n\n   건대 컴공과에서 종강파티 기념으로 단체로 ㅁㅁ식당을 잡았다.\n\n\n");
	Sleep(1000);
	printf("   %s : 뭔가 먹고싶은 기분이 든다...\n\n",students[0].name);
	Sleep(1000);
	printf("   %s : 술이 마시고싶은 기분이 든다... \n\n",students[1].name);
	Sleep(1000);
	printf("   %s : 뭔가 마시고싶은 기분이 든다... \n\n",students[2].name);
	Sleep(1000);
	printf("   %s : 뭔가 먹고싶다... \n\n",students[3].name);
	Sleep(1000);
	printf("   %s : 점점 배가 고파진다... \n\n",students[4].name);
	Sleep(1000);
	printf("   %s : 좋아, 컴공에서 쫑파티를 하니 ㅁㅁ로 가자! \n\n",students[0].name);
	Sleep(1000);
	printf("   %s : 오오... 그거 좋은 생각이군! \n\n",students[1].name);
	Sleep(1000);
	printf("   %s : 오오오.. 나는 소주가 좋다! \n\n",students[2].name);
	Sleep(1000);
	printf("   %s : 오오. 배터지게 마시자! \n\n",students[3].name);
	Sleep(1000);
	printf("   %s : 오오오오! 술에 떡이 되도록 마시자! \n\n",students[4].name);
	Sleep(1000);
	printf("   그리고 그들은 ㅁㅁ식당에 도착했다.");
	Sleep(4000);
}