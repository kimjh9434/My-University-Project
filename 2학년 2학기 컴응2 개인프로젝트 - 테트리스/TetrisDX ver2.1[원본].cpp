//TetrisDX ver2.1.cpp
/*
�����̸� : TetrisDX ver2.1.cpp
��    �� : ����2 ������Ʈ ����, ��Ʈ����!
��    �� : ������� Ű���� �Է°��� ����Ű(��, ��, ��, ��)�� ����ؼ� Q, W, E, A�� ������.
��    �� : ��Ʈ���� ȭ��
�� �� �� : �Ǳ����б� ��ǻ�Ͱ��к� 201311269 ������
�ۼ����� : ver.2.1 2014-12-12-�� 
������Ʈ �� �ۼ��Ⱓ : 2014-12-08-�� [ver.1.0] ~~ 2014-12-12-�� [ver.2.1]

�ο� ���� : 
���� ��Ȳ - pdf�� ��õ� ��� �䱸������ �����Ͽ���.
���� ��������
- 1.��� ����� ��ü�� ���� �� - OK
- 2.������ ���� �����ڿ��� �޾Ƽ� �ش��ϴ� ��� ����� ������ �� - OK
- 3.Ű �Է��� ���� ��, �����ڷ�(Chapter 11)�� Ŭ������ ����� �� - OK
- 4.��ü ���� ũ�⸦ ���μ��� 1:2�� ������ �ϸ�, �̿� ���� ������ 2���� �迭�� ������ ��.  - OK

���� ��������
- 1.��Ʈ������ �⺻ ���� ����� ������ �� - OK
- 2.��� 1���� �ı��� ������ 10���� ȹ�� - OK
- 3.������ ������ ȹ�� ������ ����ϰ� ��ü ��ŷ�� ������ �� - OK
- 4.���� ����� �ı��ϴ� ��ź �������� 1% Ȯ���� �����ϰ� �� �� - OK
- 5.��� ������ ���� �ϰ� ���� - OK
- 6.���ӽð��� 60�ʰ� ������ 2�ܰ�� �����ϰ� 2�ܰ迡 �����ϸ� ���ο� ��ϵ��� �����ϸ�(���� ������ ����)  - OK
    ����� �������� �ӵ��� 1�ܰ迡���� 1�� �� �� ������ 2�ܰ迡���� 0.5�ʴ� �� ������ �������� �� ��. - OK


2.1 ������ ��ǥ
1. GUI������ ����ϰ� ���̱�
2. Ȥ�� ���ʿ��� �ڵ尡 �ִ��� ������ Ȯ���ϱ�

cf. ���ÿ��� �߸� ���ư��ٰ���, ������ ��¥�� �˼� ���� ���ο� ���ؼ� �Է�Ű�� �ν����� ���ϴ� ������ �߻��ϰ� �ִ�.
    Ư�� q,w,e Ű�� �ν����� ���Ѵ�. �ƿ� Ű�� �ν����� ���ϸ� �𸣰ڴµ�, ����Ű(��, ��, ��, ��)�� �����ϸ鼭
    q,w,eŰ�� �ν����� ���ϴ� ������ �ƹ��� �ڵ带 ������� �𸣰ڴ�... ����� �̷��ٰ��� ��¿���� �ȴ�. ���� �յȰ� ���µ�..
*/

//��Ʈ���� DX
#include <iostream>   //cin, cout ����
#include <string>     //string���. length(), atoi() ���
#include <process.h>  //Ű �Է½� ������ ���.  _beginthreadex ��
#include <fstream>    //���� ����� - ifs, ofs
#include "TetrisDX.h" //TetrisDX �Լ� ���� �� #define��, ������ ���� ����
#include "Tetrimino.h"//Tetrimino ����� ��üȭ
#include "KeyEvent.h" //������������ 3. Ű�Է��� ������, �����ڷ�(Chapter 11)�� Ŭ������ ����� ��

using namespace std;

/////////////////////////////////////////////////// 

int state=0;         //���� ȭ�� ����
bool GameSetup=true; //���� ������۾�
bool gameOver=false; //���� ����
bool PauseGame=false;//���� �Ͻ�����

Tetrimino* tetrimino = new Tetrimino(1);  //��Ʈ���̳� - tetriminos

Tetrimino* tetrisSaved;//��Ʈ���̳� ŵ��(����) 
bool tetrisCanSave;    //��Ʈ���̳� ŵ��(����) ���ɿ���

///////////////////////////////////////////////////

const int gridWidth=10; //���ڹ��� ����ĭ - 10ĭ
const int gridHeight=20;//���ڹ��� ����ĭ - 20ĭ
const int gridHidden=2; //���ڹ��� ������ĭ
const int gridStartX=6; //���ڹ��� ���۰�����ġ
const int gridStartY=7; //���ڹ��� ���۰�����ġ

///////////////////////////////////////////////////

int speedDelay;        // ��Ʈ���̳밡 �������� ���� (ms between steps �ʰ� ����)
int grid[gridWidth][gridHeight];//���ڹ��� �迭 ����
time_t tick;           //�ð�
time_t startTick;      //���� ���� �ð�

///////////////////////////////////////////////////

const int maxQueue=3;  //��Ʈ���� ����� �����ѵ�
Tetrimino* tetrisQueue[maxQueue];//��Ʈ���� �����
int queueX=28;         //������ ������ ���� x��ǥ
int	queueY=6;          //������ ������ ���� y��ǥ

///////////////////////////////////////////////////

int level;          //���� - 60�ʸ� �������� level 2�� ���Ѵ�.
int score;          //����
int totalBreakLine; //�������ൿ�� �� �� �ټ�
int breakLine;      //�ѹ� ��Ʈ���̳밡 �� ���� ��
int tetrisisCount;  //��Ʈ���� ��
int tripleCount;    //Ʈ���� ��
int doubleCount;    //���� ��
int monoCount;      //��� ��

///////////////////////////////////////////////////

// Process input
unsigned int __stdcall keyEvent(void*)
{
	KeyEvent k;
	int keyCode=0;//�Է¹��� key��

	while(1)
	{
		if(state==1)//������ ���ư��� �ִٸ�,
		{
			keyCode=k.getkey();//key���� �Է¹޴´�.
			//gotoxy(10,1);cout<<keyCode;//� key���� �Է¹޾Ҵ��� Ȯ���ϱ� ���� �ӽ� �ڵ�
		}
		if (!tetrimino->isLocked())//��Ʈ���̳밡 ������� �ʴٸ�,
		{
			// �Ͻ�����
			if(keyCode==ENTER && !(PauseGame) && !(gameOver))
			{
				SetColor(WHITE, BLACK);
				gotoxy(24, 2);
				cout<<"���� �Ͻ� ����";
				PauseGame=true;
			}
			if(keyCode==ENTER && PauseGame && !(gameOver))
			{
				SetColor(WHITE, BLACK);
				gotoxy(24, 2);cout<<"              ";
				PauseGame=false;
			}else if (keyCode==LEFT && tetrisCheckMove(-1,0)) // MOVE LEFT
			{
				//gotoxy(1,2);printf("leftleftleftleftleftleftleftleft \n");//���ڱ� q, w, e���� �Է������� keycode�� �ν����� ���ϴ��� Ȯ���ϴ� �ڵ�
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
			}else if ((keyCode=='a' || keyCode=='A')) //���� ����Ű. �׽����� ���� ������, AŰ�� �Է� ��, ���� ��Ʈ���̳밡 ��ź���� �ٲ��.
			{ 
				//gotoxy(1,2);printf("aaaaaaaaaaaaaaaaaaaaaaaaaaa \n");
				tetrimino->setType(13);
			}
		}
	}
	return 0;
}


//TetrisDX�� �����Լ�
int main(int argc, char* argv[])
{
	int going=1;//���� ���࿩��
	tetrimino = new Tetrimino(1);  //���������� �ʱⰪ ����
	tetrisSaved = new Tetrimino(1);//���������� �ʱⰪ ����

	HANDLE handleA = (HANDLE)_beginthreadex(0, 0, &keyEvent, (void*)0, 0, 0);//�Է� ������

	while(going==1)
	{
		//���� ó�� ȭ��
		state=Display_MainMenu();//�޴� �����ְ� �Է¹ޱ�.

		switch(state)
		{
		case 1 : //��Ʈ���� �ϱ�
			gameOver=false;
			while(!gameOver){
				TetrisStart();
			}
			break;
		case 2 : //���� ���۹�
			GameDescription();
			break;
		case 3 : //���� ��ŷ
			GameRanking();
			break;
		case 4 : //���� ���� �������
			GameImplementationConstraints();
			break;
		case 5 : //��Ʈ���� ����
			going=0; 
			cout<<"\n\n     Tetris DX�� �����մϴ�.\n\n\n     "<<endl;
			break;
		default: break;
		}
	}
	CloseHandle(handleA);

	return 0;
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//�޴� �����ְ� �Է¹޴� �Լ�
int Display_MainMenu()
{
	int menu=1;
	KeyEvent k;
	int enter=0;
	int keyCode;
	system("cls");
	cout<<"\n\n\n     ��ǻ�� ���� �� �ǽ�2 ����������Ʈ 'Tetris DX'\n"<<endl;
	cout<<"\n     �л� : �Ǳ����б� ��ǻ�Ͱ��к� 201311269 ������\n\n"<<endl;

	gotoxy(10,10); cout<<"�ൿ����"<<endl;
	SetColor(0,15); 
	gotoxy(13,12); cout<<"1. Let's Go, ��Ʈ����!"<<endl;
	SetColor(15,0); 
	gotoxy(13,13); cout<<"2. ���� ���۹�"<<endl;
	gotoxy(13,14); cout<<"3. ���� ��ŷ"<<endl;
	gotoxy(13,15); cout<<"4. ���� ���� �������"<<endl;
	gotoxy(13,16); cout<<"5. ��Ʈ���� ����"<<endl;

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
			gotoxy(13,12); cout<<"1. Let's Go, ��Ʈ����!"<<endl;
			SetColor(15,0);
			gotoxy(13,13); cout<<"2. ���� ���۹�"<<endl;
			gotoxy(13,16); cout<<"5. ��Ʈ���� ����"<<endl;
			break;
		case 2: 
			SetColor(0,15); 
			gotoxy(13,13); cout<<"2. ���� ���۹�"<<endl;
			SetColor(15,0);
			gotoxy(13,12); cout<<"1. Let's Go, ��Ʈ����!"<<endl;
			gotoxy(13,14); cout<<"3. ���� ��ŷ"<<endl;
			break;
		case 3:
			SetColor(0,15); 
			gotoxy(13,14); cout<<"3. ���� ��ŷ"<<endl;
			SetColor(15,0);
			gotoxy(13,13); cout<<"2. ���� ���۹�"<<endl;
			gotoxy(13,15); cout<<"4. ���� ���� �������"<<endl;
			break;
		case 4:
			SetColor(0,15); 
			gotoxy(13,15); cout<<"4. ���� ���� �������"<<endl;
			SetColor(15,0);
			gotoxy(13,14); cout<<"3. ���� ��ŷ"<<endl;
			gotoxy(13,16); cout<<"5. ��Ʈ���� ����"<<endl;
			break;
		case 5:
			SetColor(0,15); 
			gotoxy(13,16); cout<<"5. ��Ʈ���� ����"<<endl;
			SetColor(15,0);
			gotoxy(13,12); cout<<"1. Let's Go, ��Ʈ����!"<<endl;
			gotoxy(13,15); cout<<"4. ���� ���� �������"<<endl;
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

//2. ���� ���۹�
void GameDescription()
{
	//�����ϰ� �����ش�.
	system("cls");
	cout<<"\n\n\n     Tetris DX, ���� ���۹�!!!\n\n"<<endl;
	cout<<"     �⺻���� ��Ʈ������ �����ϴ�.!"<<endl;
	cout<<"     �پ��� ��Ʈ���̳� ����� �̿��ؼ�"<<endl;
	cout<<"     ���� ��� ä���, �ش� ���� ������ϴ�."<<endl<<endl;

	cout<<"     ����Ű "<<endl;
	cout<<"     -   ��   : ��� �ٷ� ������"<<endl;
	cout<<"     - ���墺 : ����,�Ʒ���,���������� �̵�"<<endl<<endl;
	cout<<"     Q, W, E "<<endl;
	cout<<"     -  Q or q : ��� ���� ȸ��"<<endl;
	cout<<"     -  W or w : ��� Ű��"<<endl;
	cout<<"     -  E or e : ��� ������ ȸ��"<<endl<<endl;

//	cout<<"     Enter : �Ͻ�����"<<endl<<endl;//���� �Ͻ�����Ű�� �� �ȸ���

	cout<<"     ����(Enter)�� �Է��Ͻø� ����ȭ������ �̵��մϴ�."<<endl<<endl;
	getchar();
}

//////////////////////////////////////////////

//3. ���� ��ŷ
void GameRanking()
{
	ifstream ifs; // input file stream ����
	string str="";// ���Ϸκ��� ���پ� �о���� ���ڿ� ����

	string name=""; //�о���� ���ٷκ��� �Ľ̵� name ��
	string score="";//�о���� ���ٷκ��� �Ľ̵� score ��

	int i=1;
	int index, index2;

	//����� ��ŷ�� ������ ���� �������� �Ҽ� �־����.
	
	system("cls");
	cout<<"\n\n\n     Tetris DX, ��ŷ!!!\n\n"<<endl;

	ifs.open("TetrisRanking.txt"); //������ ����.
	
	if(ifs==NULL)//������ �������� �ʴ´ٸ�,
	{
		cout<<"������ �������� �ʽ��ϴ�."<<endl;
		exit(0);//���������Ų��.
	}

	cout<<"     ���\t ���� \t ����"<<endl;
	
	// ���Ϸκ��� ���پ� �о���� �ִ� ���� �ݺ��Ѵ�.
	while(getline(ifs,str)) // str�� ������ �д´�.	
	{
		//str���� : name,score.
		index  = str.find(",",0);
		name = str.substr(0,index);
		index2 = str.find(".",index);
		index++;
		score = str.substr(index,index2-index);
		cout<<"      "<<i<<" : \t "<<name<<" \t "<<score<<endl;
		i++;
	}
	ifs.close();//������ �ݴ´�.

	cout<<"\n\n\n     ����(Enter)�� �Է��Ͻø� ����ȭ������ �̵��մϴ�."<<endl<<endl;
	getchar();
}

//////////////////////////////////////////////

//4. ���� ���� ������� 
void GameImplementationConstraints()
{
	//�����ϰ� �����ش�.
	system("cls");
	cout<<"\n\n\n     Tetris DX, ���� ������ ��������!!!\n\n"<<endl;
	cout<<"     1. ��� ����� ��ü�� �����"<<endl;
	cout<<"     2. ������ ���� �����ڿ��� �޾Ƽ� �ش��ϴ� ��ϸ���� ������ ��"<<endl;
	cout<<"     3. Ű �Է��� ���� ��, �����ڷ�(Chapter 11)�� Ŭ������ ����� ��"<<endl;
	cout<<"     4. ��ü���� ũ�⸦ ����:����=1:2 �� ������ �ϸ� �̿����� ������"<<endl;
	cout<<"        2���� �迭�� ������ ��."<<endl;
	cout<<"     5. ��Ʈ������ �⺻ ���ӱ���� ������ ��"<<endl;
	cout<<"     6. ��� 1���� �ı��� ������ 10���� ȹ��"<<endl;
	cout<<"     7. ������ ������ ȹ�������� ����ϰ� ��ü��ŷ�� �����ٰ�"<<endl;
	cout<<"     8. ������� �ı��ϴ� ��ź�������� 1% Ȯ���� �����ϰ� �� ��"<<endl;
	cout<<"     9. ��� ������ �����ϰ� ����"<<endl;
	cout<<"     10. ���ӽð��� 60�ʰ� ������ 2�ܰ�� �����ϰ� 2�ܰ迡 �����ϸ�"<<endl;
	cout<<"         ���ο� ��ϵ��� �����ϸ� ����� �������� �ӵ��� 1�ܰ迡����"<<endl;
	cout<<"         1�ʴ� �ѹ����� 2�ܰ迡���� 0.5�ʴ� �ѹ����� �������� �� ��."<<endl<<endl;

	cout<<"     ����(Enter)�� �Է��Ͻø� ����ȭ������ �̵��մϴ�."<<endl<<endl;
	getchar();
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//1. ��Ʈ���� �ϱ�
void TetrisStart()
{
	if(GameSetup)//���� ������ �ʿ��ϴٸ�,
	{
		GameSeting();//������ �����Ѵ�.
	}

	if(!PauseGame) //������ �Ͻ������� ��Ȳ�� �ƴ϶��,
	{
		levelCheck();//���� Ȯ�� �Լ�
	
		BackGround();//�ΰ����� �� ���׷��ִ� �Լ�

		GameImplementation();//��������  ���ӳ��� ���� �Լ�
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//���� ���� �Լ�
void GameSeting()
{
	system("cls");
	SetColor(WHITE, BLACK);
	gotoxy(22,1);cout<<"Tetris DX"<<endl;

	Print_Box("��",4,4,4+gridWidth*4+4,4+gridHeight+1);//��ü â
	Print_Box("��",4,4,4+gridWidth*2+2,4+gridHeight+1);//��Ʈ���� â

	gotoxy(28,5);cout<<"Preview1";
	gotoxy(28,11);cout<<"Preview2";
	gotoxy(28,17);cout<<"Preview3";
	gotoxy(38,5);cout<<"Keeping";

	//�������� �ʱ�ȭ
	score=0;
	totalBreakLine=0;
	tetrisisCount=0;
	tripleCount=0;
	doubleCount=0;
	monoCount=0;

	//���ڹ��� ������ �迭 �ʱ�ȭ
	for (int x=0;x<gridWidth;x++) 
	{
		for (int y=0;y<gridHeight;y++) 
		{
			grid[x][y]=0;
		}
	}

	//��Ʈ���� ����� ����
	for(int i=0;i<maxQueue;i++) 
	{
		tetrisQueue[i] = new Tetrimino(1);
	}

	//Ű�� ��Ʈ���̳� ����
	tetrisSaved->setType(14);
	tetrisCanSave=true;

	tetrisNew();//ù��° ��Ʈ���̳� �ҷ�����
	tick = clock();//�ð� üũ ����
	startTick = clock();

	GameSetup=false;
}

////////////////////////////////////////////////

// ���ο� ��Ʈ���� ����
void tetrisNew() 
{
	delete(tetrimino);//������ �ִ� ��Ʈ���̳�� �Ҵ� �����Ѵ�.

	tetrimino=tetrisQueue[0];//���� ��Ʈ���̳븦 ����� ù��°�� ����ġ���.

	for(int i=0;i<maxQueue-1;i++)//������� ��ĭ�� �մ���.
	{
		tetrisQueue[i]=tetrisQueue[i+1];
	}

	tetrisQueue[maxQueue-1] = new Tetrimino(level);//�� �������� ����Ͽ� ���ο� ��Ʈ���̳븦 �߰���Ų��.
}

////////////////////////////////////////////////

//���� Ȯ�� �Լ�
void levelCheck()
{
	//���̵��� ���� ���� ���� ����
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

//�ΰ����� �� ���׷��ִ� �Լ�
void BackGround()
{
	SetColor(WHITE, BLACK);
	gotoxy(36, 3);cout<<"����ð� :"<<(tick-startTick)/1000<<"��";//���� �ð�
	gotoxy(38, 11);cout<<"Level :" << level;         //����
	gotoxy(38, 12);cout<<"Score :" << score;         //���ھ�
	gotoxy(38, 13);cout<<"Total :" << totalBreakLine;//���ݱ��� �� �ټ�
	gotoxy(38, 14);cout<<"Tetris:" << tetrisisCount; //���ݱ��� �� ��Ʈ������
	gotoxy(38, 15);cout<<"Triple:" << tripleCount;   //���ݱ��� �� Ʈ���ü�
	gotoxy(38, 16);cout<<"Double:" << doubleCount;   //���ݱ��� �� �����
	gotoxy(38, 17);cout<<"Mono  :" << monoCount;     //���ݱ��� �� ����
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//�������� ���ӳ��� ���� �Լ�
void GameImplementation()
{
	renderGrid();  //��Ʈ���� �� ��Ÿ����
	renderQueue(); //��Ʈ���� ������, ŵŷ ��� ��Ÿ����

	Sleep(100);
	if (!gameOver && (clock()-tick>speedDelay || tetrimino->isDownStep() || tetrimino->isDrop()))//���ӿ����� �ƴҰ�� && (�ð��� speedDelay ��� || �鴭������  || �购������ )
	{
		if (!tetrimino->isDrop())  //���� �ȴ�������
		{
			tetrisStepDown(); //��Ʈ���̳� ����Ʈ����
		}
		else  //���� ��������
		{
			while (tetrimino->isDrop()) //��Ʈ���̳밡 �Ʒ��� �������� �ִµ��� ��������.
			{ 
				//��ü�� �������� ���� ����/���������� �̵� �� �� �־���Ѵ�.
				tetrisStepDown(); //��Ʈ���̳� ����Ʈ����
				tetrimino->setIsDrop(tetrisCheckMove(0,1));
			}
		}
		tetrimino->setIsDownStep(false);
		tetrimino->setIsDrop(false);
		tick=clock();//�ð� üũ
	}

	if (gameOver)//���� ������
	{
		tetrimino->setIsLocked(true);//��Ʈ���̳븦 �̵����ϰ� ���´�. 
		renderGameOver();            //���ӿ���ȭ���� �����ش�.
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//��Ʈ���� �� ��Ÿ����
void renderGrid()
{  
	for(int x=0;x<gridWidth;x++)
	{
		for(int y=0;y<gridHeight;y++)
		{
			switchFillColor(grid[x][y]);
			gotoxy(gridStartX + x*2, gridStartY + (y-gridHidden));cout<<"��";
		}
	}

	// ��Ʈ���̳� �׸��� ��Ÿ����
	renderShadow();

	// ��Ʈ���̳� �������°� ��Ÿ����
	int tetriminoSize=tetrimino->getSize();
	for(int y=0;y<tetriminoSize;y++) 
	{
		for(int x=0;x<tetriminoSize;x++) 
		{
			if (tetrimino->getGrid(y, x) !=0 && y + tetrimino->getPosY() >= gridHidden) 
			{
				switchFillColor(tetrimino->getGrid(y, x));
				gotoxy(gridStartX + (x+ tetrimino->getPosX())*2, gridStartY + ( (y+ tetrimino->getPosY() - gridHidden)));cout<<"��";
			}
		}
	}
}

////////////////////////////////////////////////

// ��Ʈ���̳� �׸��� ��Ÿ����
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
				gotoxy(gridStartX + (x+xx)*2, gridStartY + ((y+yy-gridHidden)));cout<<"��";
			}
		}
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//��Ʈ���� ������, ŵŷ ��� ��Ÿ����
void renderQueue()
{
	//��Ʈ���� ����� ù��°
	for(int y=0;y<4;y++) 
	{
		for(int x=0;x<4;x++) 
		{
			switchFillColor(tetrisQueue[0]->getGrid(x,y)); 
			gotoxy(queueX + x*2, queueY + y);cout<<"��";
		}
	}

	//��Ʈ���� ����� �ι�°,����°
	for(int i=1;i<maxQueue;i++)
	{
		for(int y=0;y<4;y++) 
		{
			for(int x=0;x<4;x++) 
			{
				int yi = queueY + 6 *i;
				switchFillColor(tetrisQueue[i]->getGrid(x,y)); 
				gotoxy(queueX + x*2, yi + (y));cout<<"��";
			}
		}
	}

	//ŵŷ ��Ʈ���̳�
	int xi = queueX+10;
	for(int y=0;y<4;y++)
	{
		for(int x=0;x<4;x++)
		{
			switchFillColor(tetrisSaved->getGrid(y,x));
			gotoxy(xi + x*2, queueY + y);cout<<"��";
		}
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//��Ʈ���̳� ����Ʈ���� : �Ʒ��� tetrisimino�� �� �ܰ� �̵�, ���� ������ �� ��� TRUE�� ��ȯ�Ѵ�.
bool tetrisStepDown() 
{
	bool moveOK = tetrisCheckMove(0,1);//��Ʈ���̳븦 �Ʒ��� ��ĭ �̵��Ҽ� �ִ��� ������ ���θ� ���� Ȯ���Ѵ�.

	if (moveOK)//�̵� �����ϸ�,
	{
		tetrimino->moveDown();//��Ʈ���̳븦 �Ʒ��� ��ĭ �̵��Ѵ�. - tetrisPosY++;
	}
	else //�̵� �������� �ʴٸ�, <=> [���𰡿� ���� ���]
	{
		if(tetrimino->getType()==13)//���⼭ �ش� ��Ʈ���̳밡 ��ź�̶��,
		{
			bomb();     //��!. gird���� 0���� �ʱ�ȭ
			tetrisNew();//���ο� ��Ʈ���̳�
		}
		else if (tetrimino->getPosY()!=0)//��Ʈ���̳밡 õ�忡 ���� ��찡 �ƴ϶��,
		{
			tetrisFixToGrid(); // ���ڹ��� �ǿ� ��Ʈ���̳븦 ������Ű��
			gridCheck();       // ���ڹ��� �ǿ� ������ ���� �ִ��� �Ǵ��ϱ�
			tetrisNew();       // ���ο� ��Ʈ���� ����
			tetrisCanSave=true;// ��Ʈ���� ŵ�� ����
		} 
		else//��Ʈ���̳밡 õ�忡 ���� �����,
		{
			gameOver=true;     //���ӿ���
		}
	}

	return !moveOK;
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//���� ��ġ���� x������ xm, y������ ym��ŭ �̵��ϱ� ���� �����ϼ� �ִ��� �Ϲ����� �˻縦 �����Ѵ�.
bool tetrisCheckMove(int xm, int ym)
{
	return tetrisCheckMove(xm, ym, tetrimino->getPosX(), tetrimino->getPosY(), tetrimino->getRotation());//���� ��Ʈ���̳��� x,y��ǥ, ȸ�����µ� �����ش�.
}

//Ȯ��� �̵����� �˻� �Լ�
bool tetrisCheckMove(int xm, int ym, int tetrisX, int tetrisY, int tetrisR) 
{
	bool moveOK=true;//�̵� ���� ����
	int gridC=0;     //��Ż ����
	int tetriminoSize=tetrimino->getSize();
	for(int y=0;y<tetriminoSize;y++)
	{
		for(int x=0;x<tetriminoSize;x++)
		{
			//��Ʈ���̳��� �� ����� �̵��� ��ǥ�� ��� ��Ʈ���� �� ������ ����ִ��� Ȯ���Ѵ�.
			if ((tetrisY + y + ym < 0 || tetrisY + y + ym >= gridHeight) || (tetrisX + x + xm < 0 || tetrisX + x + xm >= gridWidth)) 
			{
				gridC=1;  //��Ʈ���̳� ������ �ϳ��� ���ڹ��� ���� �ϳ��� ���.
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

// ���� ȸ���ϸ鼭 �����϶�, ��Ʈ���̳밡 ���ڹ��� ������ �������� �˻��ϴ� �Լ�
bool tetrisCheckRotate(int rm)
{
	bool rotateOK = false;//ȸ�� ���� ����
	int x = tetrimino->getPosX();
	int y = tetrimino->getPosY();
	int originalRotation = tetrimino->getRotation();
	int tetrisR = originalRotation + rm; //���� ȸ�� ���¿�, ���ϴ� ȸ�� ������ ���� ȸ�����°��� ��´�.
	if (tetrisR == 4) tetrisR = 0;
	if (tetrisR == -1) tetrisR = 3;


	tetrimino->setRotation(tetrisR);
	rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);//ȸ���������� ���θ� ����.

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

	if (rotateOK)//ȸ���� �ȴٸ�,
	{
		tetrimino->setPosX(x);//���� �����Ƿ� �״�� �����Ѵ�.
	}
	tetrimino->setRotation(originalRotation); //�ٽ� �����·� ������.

	return rotateOK;//ȸ�����ɿ��� ��ȯ
}

////////////////////////////////////////////////////////////////////////////////////////////////////

// ���ڹ��� �ǿ� ��Ʈ���̳븦 ������Ű��
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

// ���ڹ��� �ǿ� ������ ���� �ִ��� �Ǵ��ϱ�
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

		// ������ �� ����
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

//��ź�� ������ ��
void bomb()
{
	//���ڹ��� ��ü �ʱ�ȭ
	for (int x=0;x<gridWidth;x++) 
	{
		for (int y=0;y<gridHeight;y++) 
		{
			grid[x][y]=0;
		}
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//���� �ٲٴ� �Լ�
void switchFillColor(int c) 
{
	SetColor(c, BLACK);
}

////////////////////////////////////////////////////////////////////////////////////////////////////

// ���� ��Ʈ���̳븦 �����ϴ� �Լ�
void tetrisSave() 
{
	Tetrimino* temp = tetrisSaved;//������ ����� ��Ʈ���̳븦 �ӽú����� ����Ѵ�.

	tetrisSaved = tetrimino;//���� ��Ʈ���̳븦 �����Ѵ�.

	if (temp->getType()==14)//������ ����� ��Ʈ���̳��� ������ �����̶��,
	{	
		tetrisNew();//���� ����� ��Ʈ���̳��� ������ ������, ���ο� ��Ʈ���̳븦 �����Ѵ�.
	}
	else//���� ���� �ִٸ�
	{
		tetrimino = temp;//������ ����� ��Ʈ���̳븦 ���� ��Ʈ���̳�� �ٲ۴�.
		tetrimino->reSeting();//��ġ�� �����Ѵ�.
		tetrisCanSave=false;  //��Ʈ���̳� ���� �Ұ���
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//���ӿ���ȭ���� �����ֱ�
void renderGameOver()
{
	SetColor(WHITE, BLACK);
	Print_Table("��",18,13,34,17);
	SetColor(RED, YELLOW);
	gotoxy(22, 15);cout<<"Game Over!";
	Sleep(5000);

	SetColor(WHITE, BLACK);
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//score �������
	// 1. ���� ������ ���� �����ȿ� ������ �Ǵ� �Ŀ� �������� ��� ���� ���� �Է��� �̿��ؼ� text���Ϸ� ����
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	ifstream ifs; // input file stream ����
	ofstream ofs; // output file stream ����
	string str="";// ���Ϸκ��� ���پ� �аų� ���� ���� ���ڿ� ����

	int scores[10];//�о���� ���ٷκ��� �Ľ̵� name ���� �迭
	string names[10]; //�о���� ���ٷκ��� �Ľ̵� score ���� �迭
	int i;      //�ݺ������
	int index, index2;//�Ľ̽� ���Ǵ� �ε���

	int ranking=11;  //���� ����

	//������ ���� ���� �д´�.
	ifs.open("TetrisRanking.txt");
	if(ifs==NULL)//������ �������� �ʴ´ٸ�,
	{
		cout<<"������ �������� �ʽ��ϴ�."<<endl;
		exit(0);//���������Ų��.
	}

	i=0;
	// ���Ϸκ��� ���پ� �о���� �ִ� ���� �ݺ��Ѵ�.
	while(getline(ifs,str)) // str�� ������ �д´�.	
	{
		//str���� : name,score.
		index  = str.find(",",0);
		names[i] = str.substr(0,index);
		index2 = str.find(".",index);
		index++;
		scores[i] = atoi( (str.substr(index,index2-index)).c_str() );
		i++;
	}
	ifs.close(); //������ �ݴ´�.

	//��ŷ�� ���Ѵ�.
	i=9;
	while(i>=0 && score >= scores[i])
	{
		ranking--;
		i--;
	}

	if(ranking<11)//���� ��ŷ�� 10�� ���̸�
	{
		//�迭�� ���� �����Ѵ�.
		i=9;
		while(i>ranking-1)
		{
			names[i]=names[i-1];
			scores[i]=scores[i-1];
			i--;
		}	
		if(ranking<11)
		{

			Print_Table("��",18,12,36,17);
			gotoxy(22, 13);cout<<"�����մϴ�.";
			gotoxy(22, 14);cout<<"��ü���� ��";
			gotoxy(22, 15);cout<<"���� "<<ranking<<"���� ";
			gotoxy(22, 16);cout<<"�ϼ̽��ϴ�.";
		}
		gotoxy(22, 19);cout<<"���� : ";
		cin>>names[i];
		scores[i]=score;

		//���ŵ� �迭�� ���Ͽ� �����Ѵ�.
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
	
	state=0;   //����ȭ��
	GameSetup=true; 
	tetrimino->setIsLocked(false);
	
	GameRanking();//��ŷ ȭ���� �����ֱ�
	getchar();
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//ȭ�鿡 �׵θ� �׸���
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

// Ŀ����ǥ�̵�
void gotoxy(unsigned int x, unsigned int y)
{
	COORD xy = {x, y} ;
	SetConsoleCursorPosition( GetStdHandle(STD_OUTPUT_HANDLE) , xy  );
}

//Ŀ�� ������ �����ִ� ��.
void removeCursor(void) 
{
	CONSOLE_CURSOR_INFO cur;
	GetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE),&cur);
	cur.bVisible=0;
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE),&cur);
}

//�ؽ�Ʈ ���� ����
void SetColor(int font, int back)
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), back*16+font);
}