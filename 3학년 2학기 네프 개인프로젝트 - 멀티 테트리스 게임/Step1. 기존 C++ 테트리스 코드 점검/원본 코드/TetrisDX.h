#pragma once
using namespace std;

#define UP 38    // 방향키 위키 
#define DOWN 40  // 방향키 아래키 
#define LEFT 37  // 방향키 왼쪽키 
#define RIGHT 39 // 방향키 오른쪽키 

#define ENTER 13 // 엔터키 

enum {
	BLACK, D_BLUE, D_GREEN, D_SKYBLUE, D_RED,   // 0:검정색, 1:검파란색, 2:검녹색, 3:검하늘색, 4:검빨간색
	D_YIOLET, D_YELLOW, GRAY, D_GRAY, BLUE,       // 5:검보라색, 6:검노란색, 7:회색, 8:검회색, 9:파란색
	GREEN, SKYBLUE, RED, YIOLET, YELLOW, WHITE,
};// 10:녹색, 11:하늘색, 12:빨간색, 13:보라색, 14:노란색, 15:흰색

  //main.c
int main(int argc, char* argv[]);
int Display_MainMenu();//메뉴 보여주고 입력받는 함수
void GameDescription();//2. 게임 조작법
void GameRanking();    //3. 게임 랭킹
void GameImplementationConstraints();//4. 게임 구현 제약사항 cf. 5. 테트리스 종료

void TetrisStart();    //1. 테트리스 하기
void GameSeting();     //게임 셋팅 함수
void tetrisNew();      //새로운 테트리스 생성
void levelCheck();     //레벨 확인 함수
void BackGround();     //부가적인 뒷 배경그려주는 함수
void GameImplementation();//실질적인 게임내부 구현 함수
void renderGrid();     //테트리스 판 나타내기
void renderShadow();   //테트리미노 그림자 나타내기
void renderQueue();    //테트리스 예비목록, 킵킹 모양 나타내기
bool tetrisStepDown(); //테트리미노 떨어트리기 : 아래로 tetrisimino를 한 단계 이동, 만약 중지가 된 경우 TRUE를 반환한다.
bool tetrisCheckMove(int xm, int ym);//현재 위치에서 x축으로 xm, y축으로 ym만큼 이동하기 전에 움직일수 있는지 일반적인 검사하는 함수
bool tetrisCheckMove(int xm, int ym, int tetrisX, int tetrisY, int tetrisR);//확장된 이동여부 검사 함수
bool tetrisCheckRotate(int rm);// 만약 회전하면서 움직일때, 테트리미노가 격자무늬 밖으로 나가는지 검사하는 함수
void tetrisFixToGrid();// 격자무늬 판에 테트리미노를 수정시키기
void gridCheck();     // 격자무늬 판에 가득찬 줄이 있는지 판단하기
void bomb();           // 펑!. gird판을 0으로 초기화
void switchFillColor(int c);//색을 바꾸는 함수
void tetrisSave();    //현재 테트리미노를 저장하는 함수
void renderGameOver(); //게임오버화면을 보여주기



void Print_Box(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX);  //화면에 테두리 그리기
void Print_Table(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX);//화면에 테이블 그리기
void gotoxy(unsigned int x, unsigned int y);                        //커서좌표이동
void removeCursor(void);                                            //커서 깜빡임 없애주는 것
void SetColor(int font, int back);                                  //텍스트 색상 변경