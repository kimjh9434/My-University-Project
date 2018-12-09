//TetrisDX ver2.1.cpp
/*
파일이름 : TetrisDX ver2.1.cpp
기    능 : 컴응2 프로젝트 과제, 테트리스!
입    력 : 사용자의 키보드 입력값중 방향키(◀, ▲, ▼, ▶)를 비롯해서 Q, W, E, A만 먹힌다.
출    력 : 테트리스 화면
작 성 자 : 건국대학교 컴퓨터공학부 201311269 김제헌
작성일자 : ver.2.1 2014-12-12-금 
프로젝트 총 작성기간 : 2014-12-08-월 [ver.1.0] ~~ 2014-12-12-금 [ver.2.1]

부연 설명 : 
현재 상황 - pdf에 명시된 모든 요구사항을 만족하였다.
구현 제약조건
- 1.모든 블록은 객체로 만들 것 - OK
- 2.랜덤한 값을 생성자에서 받아서 해당하는 블록 모양을 생성할 것 - OK
- 3.키 입력을 받을 때, 수업자료(Chapter 11)의 클래스를 사용할 것 - OK
- 4.전체 판의 크기를 가로세로 1:2의 비율로 하며, 이에 대한 정보를 2차원 배열로 저장할 것.  - OK

게임 제약조건
- 1.테트리스의 기본 게임 기능을 구현할 것 - OK
- 2.블록 1줄이 파괴될 때마다 10점을 획득 - OK
- 3.게임이 끝나면 획득 점수를 기록하고 전체 랭킹을 보여줄 것 - OK
- 4.모듞 블록을 파괴하는 폭탄 아이템이 1% 확률로 등장하게 할 것 - OK
- 5.블록 색깔은 랜덤 하게 지정 - OK
- 6.게임시간이 60초가 지나면 2단계로 진입하고 2단계에 진입하면 새로운 블록들이 등장하며(다음 페이지 참조)  - OK
    블록의 떨어지는 속도가 1단계에서는 1초 당 한 번에서 2단계에서는 0.5초당 한 번으로 빨라지게 할 것. - OK


2.1 버전의 목표
1. GUI적으로 깔끔하게 보이기
2. 혹시 불필요한 코드가 있는지 없는지 확인하기

cf. 평상시에는 잘만 돌아가다가도, 정말로 진짜로 알수 없는 원인에 의해서 입력키를 인식하지 못하는 문제가 발생하고 있다.
    특히 q,w,e 키를 인식하지 못한다. 아예 키를 인식하지 못하면 모르겠는데, 방향키(◀, ▲, ▼, ▶)는 동작하면서
    q,w,e키를 인식하지 못하는 이유는 아무리 코드를 살펴봐도 모르겠다... 웃긴건 이러다가도 어쩔때는 된다. 전혀 손된게 없는데..
*/

//테트리스 DX
#include <iostream>   //cin, cout 등등등
#include <string>     //string사용. length(), atoi() 등등
#include <process.h>  //키 입력시 쓰래드 사용.  _beginthreadex 등
#include <fstream>    //파일 입출력 - ifs, ofs
#include "TetrisDX.h" //TetrisDX 함수 선언 및 #define값, 열거형 변수 선언
#include "Tetrimino.h"//Tetrimino 블록의 객체화
#include "KeyEvent.h" //구현제약조건 3. 키입력을 받을때, 수업자료(Chapter 11)의 클래스를 사용할 것

using namespace std;

/////////////////////////////////////////////////// 

int state=0;         //게임 화면 상태
bool GameSetup=true; //게임 재사전작업
bool gameOver=false; //게임 오버
bool PauseGame=false;//게임 일시정지

Tetrimino* tetrimino = new Tetrimino(1);  //테트리미노 - tetriminos

Tetrimino* tetrisSaved;//테트리미노 킵핑(저장) 
bool tetrisCanSave;    //테트리미노 킵핑(저장) 가능여부

///////////////////////////////////////////////////

const int gridWidth=10; //격자무늬 가로칸 - 10칸
const int gridHeight=20;//격자무늬 세로칸 - 20칸
const int gridHidden=2; //격자무늬 숨겨진칸
const int gridStartX=6; //격자무늬 시작가로위치
const int gridStartY=7; //격자무늬 시작가로위치

///////////////////////////////////////////////////

int speedDelay;        // 테트리미노가 떨어지는 간격 (ms between steps 초간 간격)
int grid[gridWidth][gridHeight];//격자무늬 배열 설정
time_t tick;           //시간
time_t startTick;      //게임 시작 시간

///////////////////////////////////////////////////

const int maxQueue=3;  //테트리스 대기목록 저장한도
Tetrimino* tetrisQueue[maxQueue];//테트리스 대기목록
int queueX=28;         //예비목록 보여줄 기준 x좌표
int	queueY=6;          //예비목록 보여줄 기준 y좌표

///////////////////////////////////////////////////

int level;          //레벨 - 60초를 기준으로 level 2로 변한다.
int score;          //점수
int totalBreakLine; //게임진행동안 껜 총 줄수
int breakLine;      //한번 테트리미노가 깬 줄의 수
int tetrisisCount;  //테트리스 수
int tripleCount;    //트리플 수
int doubleCount;    //더블 수
int monoCount;      //모노 수

///////////////////////////////////////////////////

// Process input
unsigned int __stdcall keyEvent(void*)
{
	KeyEvent k;
	int keyCode=0;//입력받은 key값

	while(1)
	{
		if(state==1)//게임이 돌아가고 있다면,
		{
			keyCode=k.getkey();//key값을 입력받는다.
			//gotoxy(10,1);cout<<keyCode;//어떤 key값을 입력받았는지 확인하기 위한 임시 코드
		}
		if (!tetrimino->isLocked())//테트리미노가 잠겨있지 않다면,
		{
			// 일시정지
			if(keyCode==ENTER && !(PauseGame) && !(gameOver))
			{
				SetColor(WHITE, BLACK);
				gotoxy(24, 2);
				cout<<"게임 일시 정지";
				PauseGame=true;
			}
			if(keyCode==ENTER && PauseGame && !(gameOver))
			{
				SetColor(WHITE, BLACK);
				gotoxy(24, 2);cout<<"              ";
				PauseGame=false;
			}else if (keyCode==LEFT && tetrisCheckMove(-1,0)) // MOVE LEFT
			{
				//gotoxy(1,2);printf("leftleftleftleftleftleftleftleft \n");//값자기 q, w, e값을 입력했을때 keycode가 인식하지 못하는지 확인하는 코드
				tetrimino->moveLeft();//tetrimino->posX--;
				
			}else if (keyCode==RIGHT && tetrisCheckMove(1,0)) // MOVE RIGHT
			{
				//gotoxy(1,2);printf("rightrightrightrightrightright \n");
				tetrimino->moveRight();//tetrimino->posX++;
			}else if (keyCode==DOWN) // DOWN
			{
				//gotoxy(1,2);printf("downdowndowndowndowndowndown \n");
				tetrimino->setIsDownStep(true);
			}else if (keyCode==UP) // UP
			{
				//gotoxy(1,2);printf("upupupupupupupupupupupupup \n");
				tetrimino->setIsDrop(true);
			}else if ((keyCode=='w' || keyCode=='W') && tetrisCanSave)// SAVE tetrisIMINO 
			{
				//gotoxy(1,2);printf("wwwwwwwwwwwwwwwwwwwwwwwww \n");
				tetrisSave();
			}else if ((keyCode=='q' || keyCode=='Q') && tetrisCheckRotate(-1)) // ROTATE LEFT
			{
				//gotoxy(1,2);printf("qqqqqqqqqqqqqqqqqqqqqqq \n");
				tetrimino->turnLeft();
			}else if ((keyCode=='e' || keyCode=='E') && tetrisCheckRotate(+1)) // ROTATE RIGHT
			{ 
				//gotoxy(1,2);printf("eeeeeeeeeeeeeeeeeeeeeeeee \n");
				tetrimino->turnRight();
			}else if ((keyCode=='a' || keyCode=='A')) //히든 조작키. 테스팅을 위한 것으로, A키를 입력 시, 현재 테트리미노가 폭탄으로 바뀐다.
			{ 
				//gotoxy(1,2);printf("aaaaaaaaaaaaaaaaaaaaaaaaaaa \n");
				tetrimino->setType(13);
			}
		}
	}
	return 0;
}


//TetrisDX의 메인함수
int main(int argc, char* argv[])
{
	int going=1;//게임 진행여부
	tetrimino = new Tetrimino(1);  //전역변수에 초기값 설정
	tetrisSaved = new Tetrimino(1);//전역변수에 초기값 설정

	HANDLE handleA = (HANDLE)_beginthreadex(0, 0, &keyEvent, (void*)0, 0, 0);//입력 쓰래드

	while(going==1)
	{
		//게임 처음 화면
		state=Display_MainMenu();//메뉴 보여주고 입력받기.

		switch(state)
		{
		case 1 : //테트리스 하기
			gameOver=false;
			while(!gameOver){
				TetrisStart();
			}
			break;
		case 2 : //게임 조작법
			GameDescription();
			break;
		case 3 : //게임 랭킹
			GameRanking();
			break;
		case 4 : //게임 구현 제약사항
			GameImplementationConstraints();
			break;
		case 5 : //테트리스 종료
			going=0; 
			cout<<"\n\n     Tetris DX를 종료합니다.\n\n\n     "<<endl;
			break;
		default: break;
		}
	}
	CloseHandle(handleA);

	return 0;
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//메뉴 보여주고 입력받는 함수
int Display_MainMenu()
{
	int menu=1;
	KeyEvent k;
	int enter=0;
	int keyCode;
	system("cls");
	cout<<"\n\n\n     컴퓨터 응용 및 실습2 개인프로젝트 'Tetris DX'\n"<<endl;
	cout<<"\n     학생 : 건국대학교 컴퓨터공학부 201311269 김제헌\n\n"<<endl;

	gotoxy(10,10); cout<<"행동선택"<<endl;
	SetColor(0,15); 
	gotoxy(13,12); cout<<"1. Let's Go, 테트리스!"<<endl;
	SetColor(15,0); 
	gotoxy(13,13); cout<<"2. 게임 조작법"<<endl;
	gotoxy(13,14); cout<<"3. 게임 랭킹"<<endl;
	gotoxy(13,15); cout<<"4. 게임 구현 제약사항"<<endl;
	gotoxy(13,16); cout<<"5. 테트리스 종료"<<endl;

	removeCursor();
	fflush(stdin);
	while(enter==0)
	{
		keyCode=k.getkey();

		switch(keyCode)
		{
		case UP   : 
			menu--; 
			if(menu<1){
				menu=5;
			}
			break ;

		case DOWN : 
			menu++; 
			if(menu>5){
				menu=1; 
			}
			break ;
		default : break;
		}

		switch(menu)
		{
		case 1: 
			SetColor(0,15); 
			gotoxy(13,12); cout<<"1. Let's Go, 테트리스!"<<endl;
			SetColor(15,0);
			gotoxy(13,13); cout<<"2. 게임 조작법"<<endl;
			gotoxy(13,16); cout<<"5. 테트리스 종료"<<endl;
			break;
		case 2: 
			SetColor(0,15); 
			gotoxy(13,13); cout<<"2. 게임 조작법"<<endl;
			SetColor(15,0);
			gotoxy(13,12); cout<<"1. Let's Go, 테트리스!"<<endl;
			gotoxy(13,14); cout<<"3. 게임 랭킹"<<endl;
			break;
		case 3:
			SetColor(0,15); 
			gotoxy(13,14); cout<<"3. 게임 랭킹"<<endl;
			SetColor(15,0);
			gotoxy(13,13); cout<<"2. 게임 조작법"<<endl;
			gotoxy(13,15); cout<<"4. 게임 구현 제약사항"<<endl;
			break;
		case 4:
			SetColor(0,15); 
			gotoxy(13,15); cout<<"4. 게임 구현 제약사항"<<endl;
			SetColor(15,0);
			gotoxy(13,14); cout<<"3. 게임 랭킹"<<endl;
			gotoxy(13,16); cout<<"5. 테트리스 종료"<<endl;
			break;
		case 5:
			SetColor(0,15); 
			gotoxy(13,16); cout<<"5. 테트리스 종료"<<endl;
			SetColor(15,0);
			gotoxy(13,12); cout<<"1. Let's Go, 테트리스!"<<endl;
			gotoxy(13,15); cout<<"4. 게임 구현 제약사항"<<endl;
			break;
		default:break;
		}
		
		if(keyCode==ENTER){
			enter=1;
		}
	}
	gotoxy(0,17);
	return menu;
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//2. 게임 조작법
void GameDescription()
{
	//간단하게 보여준다.
	system("cls");
	cout<<"\n\n\n     Tetris DX, 게임 조작법!!!\n\n"<<endl;
	cout<<"     기본적인 테트리스와 같습니다.!"<<endl;
	cout<<"     다양한 테트리미노 블록을 이용해서"<<endl;
	cout<<"     줄을 모두 채우면, 해당 줄이 사라집니다."<<endl<<endl;

	cout<<"     방향키 "<<endl;
	cout<<"     -   ▲   : 블록 바로 내리기"<<endl;
	cout<<"     - ◀▼▶ : 왼쪽,아래쪽,오른쪽으로 이동"<<endl<<endl;
	cout<<"     Q, W, E "<<endl;
	cout<<"     -  Q or q : 블록 왼쪽 회전"<<endl;
	cout<<"     -  W or w : 블록 키핑"<<endl;
	cout<<"     -  E or e : 블록 오른쪽 회전"<<endl<<endl;

//	cout<<"     Enter : 일시정지"<<endl<<endl;//현재 일시정지키가 잘 안먹힘

	cout<<"     엔터(Enter)를 입력하시면 메인화면으로 이동합니다."<<endl<<endl;
	getchar();
}

//////////////////////////////////////////////

//3. 게임 랭킹
void GameRanking()
{
	ifstream ifs; // input file stream 변수
	string str="";// 파일로부터 한줄씩 읽어들일 문자열 변수

	string name=""; //읽어들인 한줄로부터 파싱된 name 값
	string score="";//읽어들인 한줄로부터 파싱된 score 값

	int i=1;
	int index, index2;

	//저장된 랭킹들 정렬후 순위 보여지게 할수 있어야함.
	
	system("cls");
	cout<<"\n\n\n     Tetris DX, 랭킹!!!\n\n"<<endl;

	ifs.open("TetrisRanking.txt"); //파일을 연다.
	
	if(ifs==NULL)//파일이 존재하지 않는다면,
	{
		cout<<"파일이 존재하지 않습니다."<<endl;
		exit(0);//강제종료시킨다.
	}

	cout<<"     등수\t 성명 \t 점수"<<endl;
	
	// 파일로부터 한줄씩 읽어나갈수 있는 동안 반복한다.
	while(getline(ifs,str)) // str은 한줄을 읽는다.	
	{
		//str형식 : name,score.
		index  = str.find(",",0);
		name = str.substr(0,index);
		index2 = str.find(".",index);
		index++;
		score = str.substr(index,index2-index);
		cout<<"      "<<i<<" : \t "<<name<<" \t "<<score<<endl;
		i++;
	}
	ifs.close();//파일을 닫는다.

	cout<<"\n\n\n     엔터(Enter)를 입력하시면 메인화면으로 이동합니다."<<endl<<endl;
	getchar();
}

//////////////////////////////////////////////

//4. 게임 구현 제약사항 
void GameImplementationConstraints()
{
	//간단하게 보여준다.
	system("cls");
	cout<<"\n\n\n     Tetris DX, 게임 구현시 제약조건!!!\n\n"<<endl;
	cout<<"     1. 모든 블록은 객체로 만들것"<<endl;
	cout<<"     2. 랜덤한 값을 생성자에서 받아서 해당하는 블록모양을 생성할 것"<<endl;
	cout<<"     3. 키 입력을 받을 때, 수업자료(Chapter 11)의 클래스를 사용할 것"<<endl;
	cout<<"     4. 전체판의 크기를 가로:세로=1:2 의 비율로 하며 이에대한 정보를"<<endl;
	cout<<"        2차원 배열로 저장할 것."<<endl;
	cout<<"     5. 테트리스의 기본 게임기능을 구현할 것"<<endl;
	cout<<"     6. 블록 1줄이 파괴될 때마다 10점을 획득"<<endl;
	cout<<"     7. 게임이 끝나면 획득점수를 기록하고 전체랭킹을 보여줄것"<<endl;
	cout<<"     8. 모든블록을 파괴하는 폭탄아이템이 1% 확률로 등장하게 할 것"<<endl;
	cout<<"     9. 블록 색깔은 랜덤하게 지정"<<endl;
	cout<<"     10. 게임시간이 60초가 지나면 2단계로 진입하고 2단계에 진입하면"<<endl;
	cout<<"         새로운 블록들이 등장하며 블록의 떨어지는 속도가 1단계에서는"<<endl;
	cout<<"         1초당 한번에서 2단계에서는 0.5초당 한번으로 빨라지게 할 것."<<endl<<endl;

	cout<<"     엔터(Enter)를 입력하시면 메인화면으로 이동합니다."<<endl<<endl;
	getchar();
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//1. 테트리스 하기
void TetrisStart()
{
	if(GameSetup)//게임 셋팅이 필요하다면,
	{
		GameSeting();//게임을 셋팅한다.
	}

	if(!PauseGame) //게임을 일시정지한 상황이 아니라면,
	{
		levelCheck();//레벨 확인 함수
	
		BackGround();//부가적인 뒷 배경그려주는 함수

		GameImplementation();//실질적인  게임내부 구현 함수
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//게임 셋팅 함수
void GameSeting()
{
	system("cls");
	SetColor(WHITE, BLACK);
	gotoxy(22,1);cout<<"Tetris DX"<<endl;

	Print_Box("▩",4,4,4+gridWidth*4+4,4+gridHeight+1);//전체 창
	Print_Box("▩",4,4,4+gridWidth*2+2,4+gridHeight+1);//테트리스 창

	gotoxy(28,5);cout<<"Preview1";
	gotoxy(28,11);cout<<"Preview2";
	gotoxy(28,17);cout<<"Preview3";
	gotoxy(38,5);cout<<"Keeping";

	//전역변수 초기화
	score=0;
	totalBreakLine=0;
	tetrisisCount=0;
	tripleCount=0;
	doubleCount=0;
	monoCount=0;

	//격자무늬 이차원 배열 초기화
	for (int x=0;x<gridWidth;x++) 
	{
		for (int y=0;y<gridHeight;y++) 
		{
			grid[x][y]=0;
		}
	}

	//테트리스 대기목록 설정
	for(int i=0;i<maxQueue;i++) 
	{
		tetrisQueue[i] = new Tetrimino(1);
	}

	//키핑 테트리미노 설정
	tetrisSaved->setType(14);
	tetrisCanSave=true;

	tetrisNew();//첫번째 테트리미노 불러오기
	tick = clock();//시간 체크 시작
	startTick = clock();

	GameSetup=false;
}

////////////////////////////////////////////////

// 새로운 테트리스 생성
void tetrisNew() 
{
	delete(tetrimino);//기존에 있던 테트리미노는 할당 해제한다.

	tetrimino=tetrisQueue[0];//현재 테트리미노를 대기목록 첫번째로 갈아치운다.

	for(int i=0;i<maxQueue-1;i++)//대기목록을 한칸씩 앞당긴다.
	{
		tetrisQueue[i]=tetrisQueue[i+1];
	}

	tetrisQueue[maxQueue-1] = new Tetrimino(level);//맨 마지막의 대기목록에 새로운 테트리미노를 추가시킨다.
}

////////////////////////////////////////////////

//레벨 확인 함수
void levelCheck()
{
	//난이도에 따른 레벨 설정 구간
	if((tick-startTick)/1000<60)
	{
		level=1;
		speedDelay=1000;
	}
	else
	{
		level=2;
		speedDelay=500;
	}
}

////////////////////////////////////////////////

//부가적인 뒷 배경그려주는 함수
void BackGround()
{
	SetColor(WHITE, BLACK);
	gotoxy(36, 3);cout<<"진행시간 :"<<(tick-startTick)/1000<<"초";//진행 시각
	gotoxy(38, 11);cout<<"Level :" << level;         //레벨
	gotoxy(38, 12);cout<<"Score :" << score;         //스코어
	gotoxy(38, 13);cout<<"Total :" << totalBreakLine;//지금까지 깬 줄수
	gotoxy(38, 14);cout<<"Tetris:" << tetrisisCount; //지금까지 깬 테트리스수
	gotoxy(38, 15);cout<<"Triple:" << tripleCount;   //지금까지 깬 트리플수
	gotoxy(38, 16);cout<<"Double:" << doubleCount;   //지금까지 깬 더블수
	gotoxy(38, 17);cout<<"Mono  :" << monoCount;     //지금까지 깬 모노수
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//실질적인 게임내부 구현 함수
void GameImplementation()
{
	renderGrid();  //테트리스 판 나타내기
	renderQueue(); //테트리스 예비목록, 킵킹 모양 나타내기

	Sleep(100);
	if (!gameOver && (clock()-tick>speedDelay || tetrimino->isDownStep() || tetrimino->isDrop()))//게임오버가 아닐경우 && (시간이 speedDelay 경과 || ↓눌렀을때  || ↑눌렀을때 )
	{
		if (!tetrimino->isDrop())  //↑을 안눌렀을때
		{
			tetrisStepDown(); //테트리미노 떨어트리기
		}
		else  //↑을 눌렀을때
		{
			while (tetrimino->isDrop()) //테트리미노가 아래로 내려갈수 있는동안 내려간다.
			{ 
				//전체가 떨어지기 전에 왼쪽/오른쪽으로 이동 할 수 있어야한다.
				tetrisStepDown(); //테트리미노 떨어트리기
				tetrimino->setIsDrop(tetrisCheckMove(0,1));
			}
		}
		tetrimino->setIsDownStep(false);
		tetrimino->setIsDrop(false);
		tick=clock();//시간 체크
	}

	if (gameOver)//게임 오버시
	{
		tetrimino->setIsLocked(true);//테트리미노를 이동못하게 막는다. 
		renderGameOver();            //게임오버화면을 보여준다.
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//테트리스 판 나타내기
void renderGrid()
{  
	for(int x=0;x<gridWidth;x++)
	{
		for(int y=0;y<gridHeight;y++)
		{
			switchFillColor(grid[x][y]);
			gotoxy(gridStartX + x*2, gridStartY + (y-gridHidden));cout<<"■";
		}
	}

	// 테트리미노 그림자 나타내기
	renderShadow();

	// 테트리미노 내려오는것 나타내기
	int tetriminoSize=tetrimino->getSize();
	for(int y=0;y<tetriminoSize;y++) 
	{
		for(int x=0;x<tetriminoSize;x++) 
		{
			if (tetrimino->getGrid(y, x) !=0 && y + tetrimino->getPosY() >= gridHidden) 
			{
				switchFillColor(tetrimino->getGrid(y, x));
				gotoxy(gridStartX + (x+ tetrimino->getPosX())*2, gridStartY + ( (y+ tetrimino->getPosY() - gridHidden)));cout<<"■";
			}
		}
	}
}

////////////////////////////////////////////////

// 테트리미노 그림자 나타내기
void renderShadow() 
{
	int xx=tetrimino->getPosX();
	int yy=tetrimino->getPosY();

	while (tetrisCheckMove(0,1,xx,yy,tetrimino->getRotation())) 
	{
		yy++;
	}

	int tetriminoSize=tetrimino->getSize();
	for(int y=0;y<tetriminoSize;y++) 
	{
		for(int x=0;x<tetriminoSize;x++) 
		{
			if (tetrimino->getGrid(y, x) != 0 && y + yy >= gridHidden) 
			{
				if (tetrimino->getGrid(y, x)!=0) 
				{
					SetColor(GRAY, BLACK);
				}
				gotoxy(gridStartX + (x+xx)*2, gridStartY + ((y+yy-gridHidden)));cout<<"■";
			}
		}
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//테트리스 예비목록, 킵킹 모양 나타내기
void renderQueue()
{
	//테트리스 대기목록 첫번째
	for(int y=0;y<4;y++) 
	{
		for(int x=0;x<4;x++) 
		{
			switchFillColor(tetrisQueue[0]->getGrid(x,y)); 
			gotoxy(queueX + x*2, queueY + y);cout<<"■";
		}
	}

	//테트리스 대기목록 두번째,세번째
	for(int i=1;i<maxQueue;i++)
	{
		for(int y=0;y<4;y++) 
		{
			for(int x=0;x<4;x++) 
			{
				int yi = queueY + 6 *i;
				switchFillColor(tetrisQueue[i]->getGrid(x,y)); 
				gotoxy(queueX + x*2, yi + (y));cout<<"■";
			}
		}
	}

	//킵킹 테트리미노
	int xi = queueX+10;
	for(int y=0;y<4;y++)
	{
		for(int x=0;x<4;x++)
		{
			switchFillColor(tetrisSaved->getGrid(y,x));
			gotoxy(xi + x*2, queueY + y);cout<<"■";
		}
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//테트리미노 떨어트리기 : 아래로 tetrisimino를 한 단계 이동, 만약 중지가 된 경우 TRUE를 반환한다.
bool tetrisStepDown() 
{
	bool moveOK = tetrisCheckMove(0,1);//테트리미노를 아래로 한칸 이동할수 있는지 없는지 여부를 먼저 확인한다.

	if (moveOK)//이동 가능하면,
	{
		tetrimino->moveDown();//테트리미노를 아래로 한칸 이동한다. - tetrisPosY++;
	}
	else //이동 가능하지 않다면, <=> [무언가에 닿은 경우]
	{
		if(tetrimino->getType()==13)//여기서 해당 테트리미노가 폭탄이라면,
		{
			bomb();     //펑!. gird판을 0으로 초기화
			tetrisNew();//새로운 테트리미노
		}
		else if (tetrimino->getPosY()!=0)//테트리미노가 천장에 닿은 경우가 아니라면,
		{
			tetrisFixToGrid(); // 격자무늬 판에 테트리미노를 수정시키기
			gridCheck();       // 격자무늬 판에 가득찬 줄이 있는지 판단하기
			tetrisNew();       // 새로운 테트리스 생성
			tetrisCanSave=true;// 테트리스 킵핑 가능
		} 
		else//테트리미노가 천장에 닿은 경우라면,
		{
			gameOver=true;     //게임오버
		}
	}

	return !moveOK;
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//현재 위치에서 x축으로 xm, y축으로 ym만큼 이동하기 전에 움직일수 있는지 일반적인 검사를 시행한다.
bool tetrisCheckMove(int xm, int ym)
{
	return tetrisCheckMove(xm, ym, tetrimino->getPosX(), tetrimino->getPosY(), tetrimino->getRotation());//현재 테트리미노의 x,y좌표, 회전상태도 넘져준다.
}

//확장된 이동여부 검사 함수
bool tetrisCheckMove(int xm, int ym, int tetrisX, int tetrisY, int tetrisR) 
{
	bool moveOK=true;//이동 가능 여부
	int gridC=0;     //이탈 여부
	int tetriminoSize=tetrimino->getSize();
	for(int y=0;y<tetriminoSize;y++)
	{
		for(int x=0;x<tetriminoSize;x++)
		{
			//테트리미노의 각 블록을 이동한 좌표가 모두 테트리스 판 내무에 들어있는지 확인한다.
			if ((tetrisY + y + ym < 0 || tetrisY + y + ym >= gridHeight) || (tetrisX + x + xm < 0 || tetrisX + x + xm >= gridWidth)) 
			{
				gridC=1;  //테트리미노 조각중 하나가 격자무늬 판중 하나를 벗어남.
			}
			else
			{
				gridC=grid[tetrisX + x + xm][tetrisY+ y + ym];
			}
			if (gridC!=0 && tetrimino->getGrid(y,x)!=0)
			{
				moveOK=false;
			}
		}
	}

	return moveOK;
}

/////////////////////////////////////////////

// 만약 회전하면서 움직일때, 테트리미노가 격자무늬 밖으로 나가는지 검사하는 함수
bool tetrisCheckRotate(int rm)
{
	bool rotateOK = false;//회전 가능 여부
	int x = tetrimino->getPosX();
	int y = tetrimino->getPosY();
	int originalRotation = tetrimino->getRotation();
	int tetrisR = originalRotation + rm; //현재 회전 상태에, 원하는 회전 방향을 더한 회전상태값을 얻는다.
	if (tetrisR == 4) tetrisR = 0;
	if (tetrisR == -1) tetrisR = 3;


	tetrimino->setRotation(tetrisR);
	rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);//회전가능한지 여부를 본다.

	if (!rotateOK && tetrisCheckMove(-1, 0, x, y, tetrisR))
	{
		x--;
		rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
	}
	if (!rotateOK && tetrisCheckMove(-2, 0, x, y, tetrisR))
	{
		x = x - 2;
		rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
	}
	if (!rotateOK && tetrisCheckMove(+1, 0, x, y, tetrisR))
	{
		x++;
		rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
	}
	if (!rotateOK && tetrisCheckMove(+2, 0, x, y, tetrisR))
	{
		x = x + 2;
		rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
	}

	if (rotateOK)//회전이 된다면,
	{
		tetrimino->setPosX(x);//문제 없으므로 그대로 수정한다.
	}
	tetrimino->setRotation(originalRotation); //다시 원상태로 돌린다.

	return rotateOK;//회전가능여부 반환
}

////////////////////////////////////////////////////////////////////////////////////////////////////

// 격자무늬 판에 테트리미노를 수정시키기
void tetrisFixToGrid()
{
	int tetriminoSize=tetrimino->getSize();
	int posX = tetrimino->getPosX();
	int posY = tetrimino->getPosY();

	for(int y=0;y<tetriminoSize;y++)
	{
		for(int x=0;x<tetriminoSize;x++)
		{
			if (tetrimino->getGrid(y,x) != 0) 
			{
				grid[posX+x][posY+y] = tetrimino->getGrid(y,x);
			}
		}
	}
}

///////////////////////////////////////////////

// 격자무늬 판에 가득찬 줄이 있는지 판단하기
void gridCheck() 
{
	bool removeLine;
	breakLine=0;

	for (int y=0;y<gridHeight;y++) 
	{
		removeLine=true;
		for (int x=0;x<gridWidth;x++) 
		{
			if (grid[x][y]==0)
			{
				removeLine=false; 
				break;
			}
		}

		// 가득찬 줄 제거
		if (removeLine) 
		{
			breakLine++;
			totalBreakLine++;

			for (int yi=y ; yi>0 ; yi--)
			{
				for (int xi=0 ; xi<gridWidth ; xi++) 
				{
					grid[xi][yi]=grid[xi][yi-1];
				}
			}
		}
	}
	switch(breakLine)
	{ 
	case 0: break;
	case 1: score=score+10*1;  monoCount++; break;
	case 2: score=score+10*4; doubleCount++; break;
	case 3: score=score+10*9;  tripleCount++; break;
	case 4: score=score+10*16; tetrisisCount++; break;
	default : break;
	}
}

///////////////////////////////////////////////

//폭탄이 터졌을 때
void bomb()
{
	//격자무늬 객체 초기화
	for (int x=0;x<gridWidth;x++) 
	{
		for (int y=0;y<gridHeight;y++) 
		{
			grid[x][y]=0;
		}
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//색을 바꾸는 함수
void switchFillColor(int c) 
{
	SetColor(c, BLACK);
}

////////////////////////////////////////////////////////////////////////////////////////////////////

// 현재 테트리미노를 저장하는 함수
void tetrisSave() 
{
	Tetrimino* temp = tetrisSaved;//기존에 저장된 테트리미노를 임시변수로 기억한다.

	tetrisSaved = tetrimino;//현재 테트리미노를 저장한다.

	if (temp->getType()==14)//기존에 저장된 테트리미노의 유형이 공백이라면,
	{	
		tetrisNew();//현재 저장된 테트리미노의 유형을 저장후, 새로운 테트리미노를 생성한다.
	}
	else//무언가 값이 있다면
	{
		tetrimino = temp;//기존에 저장된 테트리미노를 현재 테트리미노로 바꾼다.
		tetrimino->reSeting();//위치를 조정한다.
		tetrisCanSave=false;  //테트리미노 저장 불가능
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//게임오버화면을 보여주기
void renderGameOver()
{
	SetColor(WHITE, BLACK);
	Print_Table("▣",18,13,34,17);
	SetColor(RED, YELLOW);
	gotoxy(22, 15);cout<<"Game Over!";
	Sleep(5000);

	SetColor(WHITE, BLACK);
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//score 저장공간
	// 1. 점수 저장후 게임 순위안에 들어가는지 판단 후에 순위내에 들면 점수 파일 입력을 이용해서 text파일로 저장
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	ifstream ifs; // input file stream 변수
	ofstream ofs; // output file stream 변수
	string str="";// 파일로부터 한줄씩 읽거나 쓸때 쓰일 문자열 변수

	int scores[10];//읽어들인 한줄로부터 파싱된 name 값의 배열
	string names[10]; //읽어들인 한줄로부터 파싱된 score 값의 배열
	int i;      //반복제어변수
	int index, index2;//파싱시 사용되는 인덱스

	int ranking=11;  //현재 순위

	//파일을 열고 값을 읽는다.
	ifs.open("TetrisRanking.txt");
	if(ifs==NULL)//파일이 존재하지 않는다면,
	{
		cout<<"파일이 존재하지 않습니다."<<endl;
		exit(0);//강제종료시킨다.
	}

	i=0;
	// 파일로부터 한줄씩 읽어나갈수 있는 동안 반복한다.
	while(getline(ifs,str)) // str은 한줄을 읽는다.	
	{
		//str형식 : name,score.
		index  = str.find(",",0);
		names[i] = str.substr(0,index);
		index2 = str.find(".",index);
		index++;
		scores[i] = atoi( (str.substr(index,index2-index)).c_str() );
		i++;
	}
	ifs.close(); //파일을 닫는다.

	//랭킹을 정한다.
	i=9;
	while(i>=0 && score >= scores[i])
	{
		ranking--;
		i--;
	}

	if(ranking<11)//만약 랭킹이 10권 내이면
	{
		//배열의 값을 갱신한다.
		i=9;
		while(i>ranking-1)
		{
			names[i]=names[i-1];
			scores[i]=scores[i-1];
			i--;
		}	
		if(ranking<11)
		{

			Print_Table("★",18,12,36,17);
			gotoxy(22, 13);cout<<"축하합니다.";
			gotoxy(22, 14);cout<<"전체순위 중";
			gotoxy(22, 15);cout<<"에서 "<<ranking<<"위를 ";
			gotoxy(22, 16);cout<<"하셨습니다.";
		}
		gotoxy(22, 19);cout<<"성명 : ";
		cin>>names[i];
		scores[i]=score;

		//갱신된 배열을 파일에 저장한다.
		ofs.open("TetrisRanking.txt");
		i=0;
		while(i<10)
		{
			ofs <<names[i]<<","<<scores[i]<<"."<<endl;
			i++;
		}
		ofs.close();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	state=0;   //메인화면
	GameSetup=true; 
	tetrimino->setIsLocked(false);
	
	GameRanking();//랭킹 화면을 보여주기
	getchar();
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//화면에 테두리 그리기
void Print_Box(char ch[],int X_MIN,int Y_MIN,int X_MAX,int Y_MAX)
{
	int i, j;
	for (i=X_MIN;i<=X_MAX;i+=2)
	{   
		for(j=Y_MIN;j<=Y_MAX;j++)
		{
			if(i==X_MIN || i==X_MAX || j==Y_MAX || j==Y_MIN)
			{
				gotoxy(i,j);
				cout<<ch;
			}
		}
	}
}

void Print_Table(char ch[],int X_MIN,int Y_MIN,int X_MAX,int Y_MAX)
{
	int i, j;
	for (i=X_MIN;i<=X_MAX;i+=2)
	{   
		for(j=Y_MIN;j<=Y_MAX;j++)
		{
			gotoxy(i,j);
			cout<<ch;

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

//텍스트 색상 변경
void SetColor(int font, int back)
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), back*16+font);
}