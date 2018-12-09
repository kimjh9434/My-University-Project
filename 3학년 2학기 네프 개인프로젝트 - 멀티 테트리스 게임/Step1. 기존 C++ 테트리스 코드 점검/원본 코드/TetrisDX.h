#pragma once
using namespace std;

#define UP 38    // ����Ű ��Ű 
#define DOWN 40  // ����Ű �Ʒ�Ű 
#define LEFT 37  // ����Ű ����Ű 
#define RIGHT 39 // ����Ű ������Ű 

#define ENTER 13 // ����Ű 

enum {
	BLACK, D_BLUE, D_GREEN, D_SKYBLUE, D_RED,   // 0:������, 1:���Ķ���, 2:�˳��, 3:���ϴû�, 4:�˻�����
	D_YIOLET, D_YELLOW, GRAY, D_GRAY, BLUE,       // 5:�˺����, 6:�˳����, 7:ȸ��, 8:��ȸ��, 9:�Ķ���
	GREEN, SKYBLUE, RED, YIOLET, YELLOW, WHITE,
};// 10:���, 11:�ϴû�, 12:������, 13:�����, 14:�����, 15:���

  //main.c
int main(int argc, char* argv[]);
int Display_MainMenu();//�޴� �����ְ� �Է¹޴� �Լ�
void GameDescription();//2. ���� ���۹�
void GameRanking();    //3. ���� ��ŷ
void GameImplementationConstraints();//4. ���� ���� ������� cf. 5. ��Ʈ���� ����

void TetrisStart();    //1. ��Ʈ���� �ϱ�
void GameSeting();     //���� ���� �Լ�
void tetrisNew();      //���ο� ��Ʈ���� ����
void levelCheck();     //���� Ȯ�� �Լ�
void BackGround();     //�ΰ����� �� ���׷��ִ� �Լ�
void GameImplementation();//�������� ���ӳ��� ���� �Լ�
void renderGrid();     //��Ʈ���� �� ��Ÿ����
void renderShadow();   //��Ʈ���̳� �׸��� ��Ÿ����
void renderQueue();    //��Ʈ���� ������, ŵŷ ��� ��Ÿ����
bool tetrisStepDown(); //��Ʈ���̳� ����Ʈ���� : �Ʒ��� tetrisimino�� �� �ܰ� �̵�, ���� ������ �� ��� TRUE�� ��ȯ�Ѵ�.
bool tetrisCheckMove(int xm, int ym);//���� ��ġ���� x������ xm, y������ ym��ŭ �̵��ϱ� ���� �����ϼ� �ִ��� �Ϲ����� �˻��ϴ� �Լ�
bool tetrisCheckMove(int xm, int ym, int tetrisX, int tetrisY, int tetrisR);//Ȯ��� �̵����� �˻� �Լ�
bool tetrisCheckRotate(int rm);// ���� ȸ���ϸ鼭 �����϶�, ��Ʈ���̳밡 ���ڹ��� ������ �������� �˻��ϴ� �Լ�
void tetrisFixToGrid();// ���ڹ��� �ǿ� ��Ʈ���̳븦 ������Ű��
void gridCheck();     // ���ڹ��� �ǿ� ������ ���� �ִ��� �Ǵ��ϱ�
void bomb();           // ��!. gird���� 0���� �ʱ�ȭ
void switchFillColor(int c);//���� �ٲٴ� �Լ�
void tetrisSave();    //���� ��Ʈ���̳븦 �����ϴ� �Լ�
void renderGameOver(); //���ӿ���ȭ���� �����ֱ�



void Print_Box(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX);  //ȭ�鿡 �׵θ� �׸���
void Print_Table(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX);//ȭ�鿡 ���̺� �׸���
void gotoxy(unsigned int x, unsigned int y);                        //Ŀ����ǥ�̵�
void removeCursor(void);                                            //Ŀ�� ������ �����ִ� ��
void SetColor(int font, int back);                                  //�ؽ�Ʈ ���� ����