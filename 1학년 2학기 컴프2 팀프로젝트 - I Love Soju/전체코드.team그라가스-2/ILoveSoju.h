//ILoveSoju.h
// - 각 c소스파일 별로 링크할 구조체 선언, 함수선언
#ifndef _ILIVESOJU_H
#define _ILIVESOJU_H

#define UP 72    // 방향키 위키 
#define DOWN 80  // 방향키 아래키 
#define LEFT 75  // 방향키 왼쪽키 
#define RIGHT 77 // 방향키 오른쪽키 

#define ESC 27   // ESC 키
#define FUN1 59  // F1 키 
#define FUN2 60  // F2 키
#define ENTER 13 // 엔터키 
#define SPACE 32 // 스페이스바 

//구조체 선언
typedef struct _student
{
	char name[15];       //학생이름
	int drinkingCapacity;//현재주량
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
int Display_MainMenu();//메뉴 보여주고 입력받기.

void Alcoholic_Games(Student (*students));//술게임
int Display_GameMenu();//게임메뉴 보여주고 입력받기.
int Game_Random(Student (*students),int drinker);//램덤게임
int Intro();//인트로

void CheckOut_Condition(Student (*students));//상태 확인

int Move_Table(Student (*students)[5],int myTable);//테이블 이동

void Go_Bathroom(Student (*students));//화장실 가기

char Check_DrinkingCapacity(Student (*students));
void Increase_DrinkingCapacity(Student (*students)[5],int myTable);
Print_Box(char ch[],int X_MIN,int Y_MIN,int X_MAX,int Y_MAX);//화면에 테두리 그리기
Print_Table(char ch[],int X_MIN,int Y_MIN,int X_MAX,int Y_MAX);//화면에 테이블 그리기
void gotoxy(unsigned int x, unsigned int y);//커서좌표이동
void removeCursor(void);//커서 깜빡임 없애주는 것
void SetColor(int font, int back);// 테스트색상변경

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