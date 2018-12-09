//ILoveSoju.h
// - �� c�ҽ����� ���� ��ũ�� ����ü ����, �Լ�����
#ifndef _ILIVESOJU_H
#define _ILIVESOJU_H

#define UP 72    // ����Ű ��Ű 
#define DOWN 80  // ����Ű �Ʒ�Ű 
#define LEFT 75  // ����Ű ����Ű 
#define RIGHT 77 // ����Ű ������Ű 

#define ESC 27   // ESC Ű
#define FUN1 59  // F1 Ű 
#define FUN2 60  // F2 Ű
#define ENTER 13 // ����Ű 
#define SPACE 32 // �����̽��� 

//����ü ����
typedef struct _student
{
	char name[15];       //�л��̸�
	int drinkingCapacity;//�����ַ�
}Student;

enum{
	BLACK,
	D_BLUE,
	D_GREEN,
	D_SKYBLUE,
	D_RED,
	D_VIOLET,
	D_YELLOW,
	GRAY,
	D_GRAY,
	BLUE,
	GREEN,
	SKYBLUE,
	RED,
	VIOLET,
	YELLOW,
	WHITE,
};

//main.c
int main(int argc, char* argv[]);
int Display_MainMenu();//�޴� �����ְ� �Է¹ޱ�.

void Alcoholic_Games(Student (*students));//������
int Display_GameMenu();//���Ӹ޴� �����ְ� �Է¹ޱ�.
int Game_Random(Student (*students),int drinker);//��������
int Intro();//��Ʈ��

void CheckOut_Condition(Student (*students));//���� Ȯ��

int Move_Table(Student (*students)[5],int myTable);//���̺� �̵�

void Go_Bathroom(Student (*students));//ȭ��� ����

char Check_DrinkingCapacity(Student (*students));
void Increase_DrinkingCapacity(Student (*students)[5],int myTable);
Print_Box(char ch[],int X_MIN,int Y_MIN,int X_MAX,int Y_MAX);//ȭ�鿡 �׵θ� �׸���
Print_Table(char ch[],int X_MIN,int Y_MIN,int X_MAX,int Y_MAX);//ȭ�鿡 ���̺� �׸���
void gotoxy(unsigned int x, unsigned int y);//Ŀ����ǥ�̵�
void removeCursor(void);//Ŀ�� ������ �����ִ� ��
void SetColor(int font, int back);// �׽�Ʈ���󺯰�

int Get_Only_Number();
int Get_String(char (*string), int size);
void Strncpy(char (*string_coppy), char (*string), int size);
void Starting(Student (*students));

//1.Game_Spoon.c
int Game_Spoon(Student (*students),int drinker);
void Input(int (*spoons)[5]);
int GameDecision(int (*spoons)[5]);
int GoingDecision(int (*spoons)[5]);

//2.Game_YiSunShin.c
int Game_YiSunShin(Student (*students),int drinker);
int Pitch_Coin();

//3.Game_Updown.c
int Game_Updown(Student (*students), int drinker);
void UpInput(int *num, int min, int max);
void UpDown(int num, int answer, int *min, int *max, int (*com), int *drink);
void ComInput(int *num, int min, int max);

//4.Game_TheGameOfDeath.c
int Game_TheGameOfDeath(Student (*students), int drinker);
void TheInput(int *num, int *play, Student (*students));

//5.Game_BR31.c
int Game_BR31(Student (*students),int drinker);

//6.Game_RCP
int WhoWins1(int myHand1, int computerHand1);
int GetcomputerHand1();
int Game_RCP(Student (*students),int drinker);

//7.Game_ChamChamCham
void PrintWhoTrun(const int whoIsTurn,Student (*students),int drinker,int computer);
void PrintHands(int myHand, int computerHand,int drinker, int computer,Student (*students));
void PrintHands2(int myHand, int computerHand,int drinker, int computer,Student (*students));
int WhoWins(const int myHand, const int computerHand);
int Game_ChamChamCham(Student (*students),int drinker);

#endif// _ILIVESOJU_H