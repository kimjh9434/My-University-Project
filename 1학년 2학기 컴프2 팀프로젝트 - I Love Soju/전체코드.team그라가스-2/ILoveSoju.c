//ILoveSoju.c
/*
�����̸� : ILoveSoju.c
��    �� : ���� ��������Ʈ 'I Love Soju'�� �����̴�. �������ϱ� ����� �ִ�.
		  �⺻������ ������, ����ü, gotoxy�� �̿��ߴ�.
�� �� �� : ���� : '�׶󰡽�', ������, ����, �躴��, ������
�ۼ����� : 

�ο����� : ���� �ݿ��ϸ��� ������ �𿩼� �Ϸ��Ϸ� �۾��س����� �����ߴ�.
          �� ������ ������ ���� ¥�� �����ߴ�.
*/

#include <stdio.h>
#include <time.h>
#include <windows.h>
#include <conio.h>
#include "ILoveSoju.h"

int main(int argc,char* argv[])
{
	int menu=0;
	char stop='n';//���α׷� ���࿩��. 'y':���α׷� ����, 'n':�������
	int myTable=0;
	int i;
	
	char name[15];
	Student students[6][5]=
	{
		{{"",0},{"������",0},{"������",0},{"����",0},{"�躴��",0}},
		{{"",0},{"���浿",0},{"��浿",0},{"�ڱ浿",0},{"�ֱ浿",0}},
		{{"",0},{"�ű浿",0},{"���浿",0},{"���浿",0},{"���浿",0}},
		{{"",0},{"���浿",0},{"���浿",0},{"���浿",0},{"�̱浿",0}},
		{{"",0},{"�ұ浿",0},{"��浿",0},{"���浿",0},{"���浿",0}},
		{{"",0},{"��浿",0},{"�ֱ浿",0},{"�߱浿",0},{"��浿",0}}
	};//�̸� ����

	printf("\n\n\n     ��ǻ�� ���� ���α׷��� ��������Ʈ 'I Love Soju'\n");
	printf("\n     ���� : ������                   ���� : �׶󰡽�\n\n");

	printf("   �ڽ��� �̸��� �Է��ϼ���. : ");
	Get_String(name,15);
	for(i=0;i<6;i++)
	{
		Strncpy(students[i][0].name,name,sizeof(name));
	}
	
	//���� ���� �� ���Ժκ�. ����� ~~�� ���Ŀ� �����ϴ�. ~~~ ���� ��Ȳ����.
	Starting(students[0]);
	system("cls");


	printf("\n   ������ ������ �����Ӻ���~!  Go Go~!\n   ");
	getchar();
	Alcoholic_Games(students[0]);

	while(stop!='Y' && stop!='y')
	{
		system("cls");

		menu=Display_MainMenu();//�޴� �����ְ� �Է¹ޱ�.

		switch(menu)
		{
		case 1: Alcoholic_Games(students[myTable]); break;
		case 2: CheckOut_Condition(students[myTable]); break;
		case 3: myTable=Move_Table(students,myTable); break;
		case 4: Go_Bathroom(students[myTable]); break;
		case 5: printf("\n   ������ ������ ���ư��ðڽ��ϱ�? ������� ������ �մϴ�.\n\n   (Y/N) : ");
			stop=getchar(); getchar();
			if(stop=='Y' || stop=='y'){
				printf("   ������ �����ϴ�.\n\n\n   ");
			}else{
				printf("   ���Ƽ� ���� ��ϴ�.");
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
			printf("\n\n   ����(ENTER)�� ������ �ٽ� ����ȭ���� ���ɴϴ�.\n\n   ");
			while(getchar()!='\n');
		}
	}
	return 0;
}

//�޴� �����ְ� �Է¹ޱ�.
int Display_MainMenu()
{
	int menu=1;
	int enter=0;
	int ch;
	
	printf("\n\n\n     ��ǻ�� ���� ���α׷��� ��������Ʈ 'I Love Soju'\n");
	printf("\n     ���� : ������                   ���� : �׶󰡽�\n\n");

	gotoxy(10,10); printf("�ൿ����");
	SetColor(0,15); 
	gotoxy(13,12); printf("1. ������ �ϱ�");
	SetColor(15,0); 
	gotoxy(13,13); printf("2. �ַ���Ȳ Ȯ���ϱ�");
	gotoxy(13,14); printf("3. ���̺� �ű��");
	gotoxy(13,15); printf("4. ȭ��� ����");
	gotoxy(13,16); printf("5. ������ ����");

	removeCursor();
	fflush(stdin);
	while(enter==0)
	{
		if(kbhit())// Ű���尡�������ٸ�
		{
			ch=getch();
			if(ch == 0xE0)// Ư��Ű���������ٸ�ex> ����Ű
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
					gotoxy(13,12); printf("1. ������ �ϱ�");
					SetColor(15,0);
					gotoxy(13,13); printf("2. �ַ���Ȳ Ȯ���ϱ�");
					gotoxy(13,16); printf("5. ������ ����");
					break;
				case 2: 
					SetColor(0,15); 
					gotoxy(13,13); printf("2. �ַ���Ȳ Ȯ���ϱ�");
					SetColor(15,0);
					gotoxy(13,12); printf("1. ������ �ϱ�");
					gotoxy(13,14); printf("3. ���̺� �ű��");
					break;
				case 3:
					SetColor(0,15); 
					gotoxy(13,14); printf("3. ���̺� �ű��");
					SetColor(15,0);
					gotoxy(13,13); printf("2. �ַ���Ȳ Ȯ���ϱ�");
					gotoxy(13,15); printf("4. ȭ��� ����");
					break;
				case 4:
					SetColor(0,15); 
					gotoxy(13,15); printf("4. ȭ��� ����");
					SetColor(15,0);
					gotoxy(13,14); printf("3. ���̺� �ű��");
					gotoxy(13,16); printf("5. ������ ����");
					break;
				case 5:
					SetColor(0,15); 
					gotoxy(13,16); printf("5. ������ ����");
					SetColor(15,0);
					gotoxy(13,12); printf("1. ������ �ϱ�");
					gotoxy(13,15); printf("4. ȭ��� ����");
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

//1. �������ϱ�
void Alcoholic_Games(Student (*students))
{
	int gameNumber=0;
	char game_name[8][17]={"���̼�����","�̼��Ű���","���ٿ����","�����ӿ��굥��","����Ų���31","���������","����������","��������"};
	static int drinker=0;
	int drink;

	system("cls");
	printf("\n\n\n   1. ������ �ϱ� \n\n");
	
	printf("   %s�� ",students[drinker].name);Sleep(500);
	printf("�����ϴ� ");Sleep(1000);
	printf("����");Sleep(500);
	printf(" Game! ");Sleep(1000);
	printf("����");Sleep(500);
	printf(" Game! ");Sleep(1000);
	printf("Game ");Sleep(500);
	printf("Start!\n\n");Sleep(1500);
	
	if(drinker==0){//���� �ɸ� ����� �÷��̾���, 
		gameNumber=Display_GameMenu();//���Ӹ޴� �����ְ� �Է¹ޱ�.
	}
	else{//��ǻ�Ͷ��,
		gameNumber=rand()%7+1;//�ٷ� ������ �ޱ�
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

	printf("\n    %s��(��) %s���ӿ��� �ɷȽ��ϴ�.\n",students[drinker].name,game_name[gameNumber-1]);
	getchar();
	drink=Intro();
	students[drinker].drinkingCapacity+=drink;
}

int Display_GameMenu()
{
	int gameNumber=1;
	int enter=0;
	int ch;

	gotoxy(10,7); printf("���Ӽ���");
	SetColor(0,15); 
	gotoxy(13,9);printf("1. ���̼�����[����������]");
	SetColor(15,0);
	gotoxy(13,10);printf("2. �̼��Ű���");
	gotoxy(13,11);printf("3. ���ٿ����");
	gotoxy(13,12);printf("4. �����ӿ��굥��");
	gotoxy(13,13);printf("5. ����Ų���31");
	gotoxy(13,14);printf("6. ���������");
	gotoxy(13,15);printf("7. ����������");
	gotoxy(13,16);printf("8. ��������(�ƹ��ų�)");

	removeCursor();
	fflush(stdin);
	while(enter==0)
	{
		if(kbhit())// Ű���尡�������ٸ�
		{
			ch=getch();
			if(ch == 0xE0)// Ư��Ű���������ٸ�ex> ����Ű
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
					gotoxy(13,9);printf("1. ���̼�����[����������]");
					SetColor(15,0);
					gotoxy(13,10);printf("2. �̼��Ű���");
					gotoxy(13,16);printf("8. ��������(�ƹ��ų�)");
					break;
				case 2: 
					SetColor(0,15); 
					gotoxy(13,10);printf("2. �̼��Ű���");
					SetColor(15,0);
					gotoxy(13,9);printf("1. ���̼�����[����������]");
					gotoxy(13,11);printf("3. ���ٿ����");
					break;
				case 3:
					SetColor(0,15); 
					gotoxy(13,11);printf("3. ���ٿ����");
					SetColor(15,0);
					gotoxy(13,10);printf("2. �̼��Ű���");
					gotoxy(13,12);printf("4. �����ӿ��굥��");
					break;
				case 4:
					SetColor(0,15); 
					gotoxy(13,12);printf("4. �����ӿ��굥��");
					SetColor(15,0);
					gotoxy(13,11);printf("3. ���ٿ����");
					gotoxy(13,13);printf("5. ����Ų���31");
					break;
				case 5:
					SetColor(0,15); 
					gotoxy(13,13);printf("5. ����Ų���31");
					SetColor(15,0);
					gotoxy(13,12);printf("4. �����ӿ��굥��");
					gotoxy(13,14);printf("6. ���������");
					break;
				case 6: 
					SetColor(0,15); 
					gotoxy(13,14);printf("6. ���������");
					SetColor(15,0);
					gotoxy(13,13);printf("5. ����Ų���31");
					gotoxy(13,15);printf("7. ����������");
					break;
				case 7: 
					SetColor(0,15); 
					gotoxy(13,15);printf("7. ����������");
					SetColor(15,0);
					gotoxy(13,14);printf("6. ���������");
					gotoxy(13,16);printf("8. ��������(�ƹ��ų�)");
					break;
				case 8: 
					SetColor(0,15); 
					gotoxy(13,16);printf("8. ��������(�ƹ��ų�)");
					SetColor(15,0);
					gotoxy(13,9);printf("1. ���̼�����[����������]");
					gotoxy(13,15);printf("7. ����������");
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
		printf("   ���Ŷ�~ ");Sleep(1000);printf("���Ŷ�~!\n ");Sleep(700);
		printf("   ���Ŷ�~ ");Sleep(1000);printf("���Ŷ�~!\n\n");Sleep(600);
		printf("   ���� ����!\n\n");Sleep(2000);
		printf("   ��");Sleep(600);printf(" ����");Sleep(600);printf(" ����!\n");Sleep(600);
		printf("   ��");Sleep(600);printf(" ����");Sleep(600);printf(" ����!\n\n");Sleep(600);
		printf("   ��������");Sleep(600);printf(" �����");Sleep(600);printf(" �߰��Ҳ���?\n\n");Sleep(1000);
		printf("   ������� ��.");Sleep(700);printf(" Ż����ܾ�!\n\n");
		break;
	case 1 : 
		printf("   ���� ��~ ��������! ��ī�þ� ���� Ȱ¦ ����!\n"); 
		break;
	case 2 : 
		printf("   ������ ���ϸ� �尡�� �� ���� ��~ �̿� ���!\n"); 
		break;
	case 3 : 
		printf("   ������ ��Ʈ�ε� �����. ��\n"); 
		break;
	case 4 : 
		printf("   ������ ����. ������ ����! \n"); drink=2; 
		break;
	case 5 : 
		printf("   ���� ���� �ð��� ��!��!��! \n"); 
		break;
	case 6 : 
		printf("   ���Ŷ�~ ");Sleep(1000);printf("���Ŷ�~!\n ");Sleep(700);
		printf("   ���Ŷ�~ ");Sleep(1000);printf("���Ŷ�~!\n\n");Sleep(600);
		break;
	case 7: printf("   ���ø鼭~ ����~ ����~ ����~\n");
	case 8: 
		printf("   ���Ŷ�~ ");Sleep(1000);printf("���Ŷ�~!\n ");Sleep(700);
		printf("   ���Ŷ�~ ");Sleep(1000);printf("���Ŷ�~!\n\n");Sleep(600);
		printf("   ���� ����!\n\n");Sleep(2000);
		printf("   ��");Sleep(600);printf(" ����");Sleep(600);printf(" ����!\n");Sleep(600);
		printf("   ��");Sleep(600);printf(" ����");Sleep(600);printf(" ����!\n\n");Sleep(600);
		printf("   ��������");Sleep(600);printf(" �����");Sleep(600);printf(" �߰��Ҳ���?\n\n");Sleep(1000);
		printf("   ������� ��.");Sleep(700);printf(" �߰� ���ݾ�!\n\n");
	default: break;
	}

	Sleep(2000);
	return drink;
}

void CheckOut_Condition(Student (*students))
{
	int i; //�ݺ������
	int bottle;//���� ���� ��
	int glass;//���� ���� ��

	system("cls");
	gotoxy(2,1);printf("2. �ַ���Ȳ Ȯ���ϱ� \n\n\n");
	printf("   ������� \n\n");
	printf("   �̸�  �ַ�  \n");
	for(i=0;i<5;i++)
	{
		bottle=students[i].drinkingCapacity/8;
		glass=students[i].drinkingCapacity-bottle*8;
		printf("  %6s : %d�� %d�� ����. �� %2d��\n",students[i].name,bottle,glass,students[i].drinkingCapacity);
	}
}

int Move_Table(Student (*students)[5],int myTable)
{
	//gotoxy ���. ���̺�`>���̺�� �̵��ϴ� ��� ����.
	//�̵���, ������ �̸����� �����ϸ鼭,���� �⺻�ɹ�����
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
	int spacial=0;//Ư��Ű
	int newTable=0;

	system("cls");
	gotoxy(2,1);printf("3. ���̺� �ű��.");
	Print_Box("��",X_MIN,Y_MIN,X_MAX,Y_MAX);
	Print_Table("��",12,7,22,10);
	gotoxy(14,8);printf("���̺� 1");
	Print_Table("��",34,7,44,10);
	gotoxy(36,8);printf("���̺� 2");
	Print_Table("��",56,7,66,10);
	gotoxy(58,8);printf("���̺� 3");
	Print_Table("��",12,15,22,18);
	gotoxy(14,16);printf("���̺� 4");
	Print_Table("��",34,15,44,18);
	gotoxy(36,16);printf("���̺� 5");
	Print_Table("��",56,15,66,18);
	gotoxy(58,16);printf("���̺� 6");
	
	removeCursor();
	x_=x;
	y_=y;
	SetColor(BLUE,RED);
	gotoxy(x , y); printf("��");

	while(newTable==0)
	{
		if(kbhit())// Ű���尡�������ٸ�
		{
			ch=getch();
			switch(ch)// Ư��Ű���������ٸ�
			{
			case 0xE0 : //����Ű�� �������ٸ�,
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
					
					gotoxy(20,12);printf("���̺� %d�� �̵��Ͻðڽ��ϱ�? (Y, N) : ",newTable);
					cha=getchar();getchar();
					while(cha=='\n'){
						gotoxy(58,12);cha=getchar();
						fflush(stdin);
					}
					if(cha!='Y'&&cha!='y'){SetColor(WHITE,BLACK);
						gotoxy(20,12);printf("\t\t\t\t\t\t\t    ");
						gotoxy(x,y);SetColor(BLUE,RED);printf("��");
						newTable=0;
						
					}
				}
				break;
			default:break;
			}
			SetColor(BLUE,RED);
			gotoxy(x , y); printf("��");

			SetColor(WHITE,BLACK);
			gotoxy(x_,y_); printf("  ");
		}
	}
	students[newTable-1][0].drinkingCapacity=students[myTable][0].drinkingCapacity;
	SetColor(WHITE,BLACK);
	gotoxy(X_MIN,Y_MAX);
	return newTable-1;
}

//ȭ��� ����
void Go_Bathroom(Student (*students))
{
	static int count=0;
	system("cls");
	gotoxy(2,1);printf("4. ȭ��� ����\n\n");

	printf("   ȭ��ǿ� ���̽��ϴ�.\n\n");

	if(count==0){
		printf(" ���밡 ������� ��ȭ�˴ϴ�.\n\n");
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
	int i=1;//�ݺ����� ����
	int sum_drink=0;//��
	int ave_drink;//���
	static int index=0;//����
	static char names[13][10]={"���浿","��浿","���浿","���浿","���浿","õ�浿","���浿","���浿","�α浿","���浿","�ӱ浿","�ѱ浿","��浿"};

	if(students[0].drinkingCapacity>=24)
	{
		printf("   �ʹ� ���� ���ż� ���߽��ϴ�. ������ �����ϴ�.\n");
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
			printf("   %s��(��) ���� �߽��ϴ�. ������ �����ϴ�. \n", students[i].name);

			Strncpy(students[i].name,names[index],10);
			students[i].drinkingCapacity=ave_drink;

			printf("   �����̽� %s(��) �� ���̺� �ɾҽ��ϴ�. \n", students[i].name);
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

//10�ڸ��� ������ �Է¹����� �ִ� �Լ�.
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

//size ��ŭ�� ���ڿ��� �Է¹����� �ִ� �Լ�.
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

	string[length]=0; //���ڿ� �ڿ� ������'��'�� �˸��� null ���� ǥ���ϰ�.

	fflush(stdin); //���� ���� ����

	return length; //���� �Է¹��� ���ڿ��� ��뷮 ��ȯ�ϱ�
}
//string_coppy�� ������ ����[size-1] ���� �����鼭, string�� NULL���ڸ� ������ ������, ���ڿ��� �����մϴ�.
//���ڿ� string�� ���� size-1��ŭ�� string_coppy�� ������.
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

//ȭ�鿡 �׵θ� �׸���
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

// �׽�Ʈ���󺯰�
void SetColor(int font, int back)
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), back*16+font);
}

//��Ÿ�� ��Ʈ�κκ�
void Starting(Student (*students))
{
	system("cls");
	printf("\n\n\n   �Ǵ� �İ������� ������Ƽ ������� ��ü�� �����Ĵ��� ��Ҵ�.\n\n\n");
	Sleep(1000);
	printf("   %s : ���� �԰���� ����� ���...\n\n",students[0].name);
	Sleep(1000);
	printf("   %s : ���� ���ð���� ����� ���... \n\n",students[1].name);
	Sleep(1000);
	printf("   %s : ���� ���ð���� ����� ���... \n\n",students[2].name);
	Sleep(1000);
	printf("   %s : ���� �԰�ʹ�... \n\n",students[3].name);
	Sleep(1000);
	printf("   %s : ���� �谡 ��������... \n\n",students[4].name);
	Sleep(1000);
	printf("   %s : ����, �İ����� ����Ƽ�� �ϴ� ������ ����! \n\n",students[0].name);
	Sleep(1000);
	printf("   %s : ����... �װ� ���� �����̱�! \n\n",students[1].name);
	Sleep(1000);
	printf("   %s : ������.. ���� ���ְ� ����! \n\n",students[2].name);
	Sleep(1000);
	printf("   %s : ����. �������� ������! \n\n",students[3].name);
	Sleep(1000);
	printf("   %s : ��������! ���� ���� �ǵ��� ������! \n\n",students[4].name);
	Sleep(1000);
	printf("   �׸��� �׵��� �����Ĵ翡 �����ߴ�.");
	Sleep(4000);
}