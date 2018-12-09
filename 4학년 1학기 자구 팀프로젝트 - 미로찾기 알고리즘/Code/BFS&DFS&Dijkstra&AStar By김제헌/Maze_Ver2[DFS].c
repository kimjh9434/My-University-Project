// 2018-04-20-��
// Maze_Ver2.c
// ���� ����Ʈ ������ �̿��ؼ� �̷�Ż�� �˰����� ����

/*
��... ���� 1���� CASE 3���� ó��������, ������� �������� ���� ó������ ���ߴ�.

������ �߻��ϴ� ����Ʈ��, [�̹� ������ �� �����ؼ�] ���ٸ� �濡 �����Ͽ��� ��
�ڽ��� �Դ� ��� '�״��' ���ƿ;� �ߴµ� 
�� : 0, �� : 1, ���� �� : 2, ���ٸ��� : 3 ���θ� ǥ���ϴϱ�,
'�̹� ����������' �� ������ڴ�, �ʹ� ���ڴ�� ���� �������� �߻��ߴ�.

�׷���, ������ �����ϱ�� �ߴ�.
�� : 0, �� : -1, ���� �� : 1�̻�, ���ٸ��� : -2 �� ǥ���ؾ߰ڴ�.

�׸���, '�����϶�����, ���� 1�� ������Ű�� ��'�� ����Ʈ��.
�̷���, ���߿� ���ƿö�, 1�� �پ�� ��ġ�� �ڽ��� �� ���̱� ������ �����ϰ� ���ƿ� ���� �ִ�.
*/


#include <stdio.h>
#include <time.h>
#include <Windows.h>

#define MAZE_SIZE 10


// �� : 0, �� : -1, ���� �� : 1�̻�, ���ٸ��� : -2 �� ǥ���ؾ߰ڴ�.

// �׽�Ʈ�� �̷��� �ּ��� �����ϰ� ������ ��

/*
// Case1. ������ �շ����� ���� ���
int maze[MAZE_SIZE][MAZE_SIZE] = {
{ 0,  0,  0,  0, -1,  0,  0,  0,  0,  0 },
{ 0, -1,  0, -1, -1,  0, -1, -1,  0, -1 },
{ 0, -1,  0,  0,  0,  0,  0,  0, -1,  0 },
{ 0, -1,  0, -1,  0, -1, -1,  0, -1,  0 },
{ 0, -1, -1, -1,  0,  0, -1, -1, -1,  0 },
{ 0,  0,  0, -1,  0, -1,  0,  0,  0,  0 },
{ 0, -1,  0, -1, -1,  0, -1,  0, -1,  0 },
{ 0, -1,  0,  0,  0,  0,  0,  0, -1,  0 },
{ 0, -1,  0, -1,  0, -1,  0, -1,  0, -1 },
{ 0, -1,  0, -1,  0, -1,  0,  0,  0,  0 }
};
*/

/*
// Case2. ������ �շ��ִ� ���
int maze[MAZE_SIZE][MAZE_SIZE] = {
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0, -1, -1, -1, -1, -1, -1, -1,  0, -1 },
{ 0, -1,  0,  0,  0,  0,  0,  0, -1,  0 },
{ 0, -1,  0, -1,  0, -1, -1,  0,  0,  0 },
{ 0, -1,  0, -1,  0,  0, -1, -1, -1,  0 },
{ 0,  0,  0, -1,  0, -1,  0,  0,  0,  0 },
{ 0, -1,  0, -1, -1,  0, -1,  0, -1,  0 },
{ 0, -1,  0,  0,  0,  0,  0,  0, -1,  0 },
{ 0, -1,  0, -1,  0, -1,  0, -1,  0, -1 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 }
};
*/

/*
// Case4. ���� + �Ѱ��
int maze[MAZE_SIZE][MAZE_SIZE] = {
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0, -1, -1, -1,  0,  0,  0 },
{ 0,  0,  0,  0, -1,  0, -1,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0, -1,  0,  0,  0 },
{ 0,  0,  0,  0, -1, -1, -1,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 }
};
*/

/*
// Case5.���� + �Ѱ�� + ���� : ���⼭ ������
int maze[MAZE_SIZE][MAZE_SIZE] = {
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0, -1, -1, -1,  0,  0,  0 },
{ 0,  0,  0,  0, -1,  0, -1,  0,  0,  0 },
{ 0,  0,  0,  0, -1, -1, -1,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 }
};
*/


// Case3. �� �ո� + ���� ���� ���
int maze[MAZE_SIZE][MAZE_SIZE] = {
	{ 0,  0,  0,  0,  0,  0,  0,  0, -1,  0 },
{ 0, -1,  0, -1, -1,  0, -1,  0, -1, -1 },
{ 0, -1,  0,  0,  0,  0,  0,  0, -1,  0 },
{ 0,  0,  0, -1,  0, -1, -1,  0,  0,  0 },
{ 0, -1, -1, -1,  0,  0, -1, -1, -1,  0 },
{ 0,  0,  0, -1,  0, -1,  0,  0,  0,  0 },
{ 0, -1,  0, -1,  0,  0, -1,  0, -1,  0 },
{ 0,  0,  0, -1,  0,  0,  0,  0, -1,  0 },
{ 0, -1,  0, -1, -1, -1,  0, -1, -1,  0 },
{ 0,  0,  0, -1,  0,  0,  0,  0,  0,  0 }
};

// �迭�� ��Ҵ� elementŸ������ ����
typedef struct _point {
	int x;
	int y;
	int d; //direction. ��, ��, ��, �� ������� 1, 2, -1, -2 ��Ī 
		   //�ڽ��� �Դ� ������ �ǹ���. [���� ��Ī��]
	int n; //number : ó�� ��ߺ��� �ڽ��� �ִ� ��ġ���� �Ÿ�, ���� 
} Point;

//���� �����͸� ����ü�� ����  �Լ��� �Ķ���ͷ� ����
typedef struct _stackNode StackNode;
typedef struct _stackNode {  //��� Ÿ��
	Point p;
	StackNode *prev;
} StackNode;

typedef struct {         //���� ����Ʈ ������ Ÿ��
	StackNode *top;      //�ǹ̻� tail�� �ش�
	int length;
} LinkedStackType;

void gotoxy(unsigned int x, unsigned int y);//Ŀ���̵��ϱ�
void removeCursor(void); // Ŀ�� ������ �����ִ� ��.
void DisplayBoard(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX, int width, int height, Point* current_p);//ó�� ȭ�麸���ֱ�

Point start_p;   // ���� ��ġ
Point end_p;     // ���� ��ġ
int X_MIN = 20, X_MAX = 42;//�̷��� x����
int Y_MIN = 10, Y_MAX = 21; //�̷��� y����
int width = 10, height = 10;//�̷��� ����, ���� ����



// ���� �ʱ�ȭ �Լ� 
void init(LinkedStackType* s) {
	s->top = NULL;
	s->length = 0;
}
int is_empty(LinkedStackType* s) {
	return (s->top == NULL);
}

// ���� �Լ� 
void push(LinkedStackType* s, Point item) {
	StackNode* temp = (StackNode*)malloc(sizeof(StackNode));
	if (temp == NULL) {
		fprintf(stderr, "�޸� �Ҵ翡��\n");
		return;
	}
	else {
		temp->p = item;
		temp->prev = s->top;
		s->top = temp;
		s->length++;

		gotoxy(28, 24);
		printf("���ÿ� �ִ� �� : [x,y,n]: [%2d, %2d, %2d]", item.x, item.y, item.n);
	}
}

// ���� �Լ� 
Point pop(LinkedStackType* s) {
	if (is_empty(s)) {
		gotoxy(28, 26);
		fprintf(stderr, "������ �������\n");
		gotoxy(28, 27);
		printf("�������� ���� ���� �����ϴ�. \n");
		gotoxy(28, 28);
		exit(1);
	}
	else {
		StackNode *temp = s->top;
		Point p = temp->p;
		s->top = s->top->prev;
		free(temp);
		s->length--;
		return p;
	}
}

// ���� ��ġ����, ���������� ������ Ȯ���ϰ�, ���� �ִ� ������ ������ ���Ѵ�.
int GetDirectionNum(Point* p) {
	int dn = 0; // Direction Num

	// �̶� �̷� ��輱�� ������ �ȵȴ�.
	if (p->x < 9 && maze[p->y][p->x + 1] == 0) // ����
		dn++;
	if (p->y < 9 && maze[p->y + 1][p->x] == 0) // ����
		dn++;
	if (p->x > 0 && maze[p->y][p->x - 1] == 0) // ����
		dn++;
	if (p->y > 0 && maze[p->y - 1][p->x] == 0) // ����
		dn++;
	return dn;
}

// ���� ��ġ����, ���������� ������ Ȯ���ϰ�, ���� �ִ� ������ ��´�.
void GetDirection(Point* p) {
	int d = 0;

	//direction. ��, ��, ��, �� ������� 1, 2, -1, -2 ��Ī 
	// �̶� �̷� ��輱�� ������ �ȵȴ�.
	if (p->x < 9 && maze[p->y][p->x + 1] == 0) // ����
		d = 1;
	else if (p->y < 9 && maze[p->y + 1][p->x] == 0) // ����
		d = 2;
	else if (p->x > 0 && maze[p->y][p->x - 1] == 0) // ����
		d = -1;
	else if (p->y > 0 && maze[p->y - 1][p->x] == 0) // ����
		d = -2;

	p->d = d;
}

// ���� ��ġ����, ���������� ������ Ȯ���ϰ�, �ڽ��� �Դ� ��[= �ڽ� ��ġ�� ���� -1]�� ������ ��´�.
void GetDirection2(Point* p) {
	int d = 0;

	//direction. ��, ��, ��, �� ������� 1, 2, -1, -2 ��Ī 
	if (p->x < 9 && maze[p->y][p->x + 1] == (p->n - 1) ) // ����
		d = 1;
	else if (p->y < 9 && maze[p->y + 1][p->x] == (p->n - 1) ) // ����
		d = 2;
	else if (p->x > 0 && maze[p->y][p->x - 1] == (p->n - 1) ) // ����
		d = -1;
	else if (p->y > 0 && maze[p->y - 1][p->x] == (p->n - 1) ) // ����
		d = -2;

	p->d = d;
}

int IsEqual(Point* p1, Point* p2) {
	int isEqual;

	// ��ġ�� ������,
	if (p1->x == p2->x && p1->y == p2->y) {
		isEqual = 1;
	}
	else {// ��ġ�� �ٸ���,
		isEqual = 0;
	}
	return isEqual;
}


// �ش� ��ġp�� num�� ������ �ٲ۴�.
// - �� : 0, �� : -1, ���� �� : 1�̻�, ���ٸ��� : -2 
void ChangeBoard(Point* p, int num) {
	maze[p->y][p->x] = num;
}

// P�� d�� �������� 1ĭ �̵��Ѵ�.
void MovePoint(Point* p) {
	switch (p->d) {
	case  1: p->x = p->x++; break; // ���� �̵�
	case  2: p->y = p->y++; break; // ���� �̵�
	case -1: p->x = p->x--; break; // ���� �̵�
	case -2: p->y = p->y--; break; // ���� �̵�
	default: break;
	}
}


// ���� �ִ� ������ 0���� ���, [���� ���� ���] ���Ƴ��;��Ѵ�.
// ���� ��ġ(current_p)���� ������ ��ġ(save_p)���� ���� ���´�.
Point ReturnSavePoint(LinkedStackType* stack, Point current_p) {
	Point save_p;    // ������ ��ġ [�߰� üũ����Ʈ. ����ǥ]
	int dn;
	//���ÿ��� ���� �ֱ� �������� ��ġ�� ������.
	save_p = pop(stack);

	//�ݴ� �������� ���Ƽ� �����Ѵ�.
	//�ϴ�, �Դ� ������ �ݴ�������� ��ĭ ���� �����δ�.
	
	while (!IsEqual(&current_p, &save_p)) { //������ ��ġ�� ������ �� ���� �ݺ��Ѵ�.

		//���� ��[2]�� ���� ���� �´�.
		ChangeBoard(&current_p, -2);//������ġ�� �����ǿ� ǥ���ϰ�, // ���ٸ��� : -2
		GetDirection2(&current_p); //������ġ�κ���, �Դ� ��[2]�� ������ ���ϰ�, [�������� ��]
		MovePoint(&current_p);     //������ġ�κ��� ��ĭ �����δ�.
		current_p.n--;

		gotoxy(28, 23);
		printf("current_p.n : %2d, s->length = %2d", current_p.n, stack->length);
		Sleep(200);
		DisplayBoard("��", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
	}

	//�ش� �����濡�� �� ������ �ִ��� Ȯ���Ѵ�.
	dn = GetDirectionNum(&current_p);

	if (dn >= 1) {
		//�ش� �����濡�� �� ������ �ִٸ� ���ÿ� �ְ�, �˾Ƽ� �����϶�� �Ѵ�.
		push(stack, current_p);
	}

	return current_p;
}

void DFS2() {
	LinkedStackType stack;

	Point current_p; // ���� ��ġ

	int dn; // �̵� ������ ������ ��. dn = Direction Number

	// ������ �ʱ�ȭ�Ѵ�.
	init(&stack);
	
	// ��������κ��� ���������� �˻�[�� �� �ִ� ������ ���� ��]�Ѵ�.
	dn = GetDirectionNum(&start_p);

	//���� �ִ� ������ 0���� ���鼭 ���� ��ġ�� ���� ��ġ�� �ƴѰ��,
	if (dn == 0 && !IsEqual(&start_p, &end_p)) {
		//�̷θ� ã�� ���� ����.
		printf("���ۺ���, ���� ��ġ���� �����߽��ϴ�. \n");
		return;
	}

	//������ġ�� ������ġ�� ��´�.
	current_p = start_p;      

	//�������� ������ �� ���� �ݺ��Ѵ�. [== ������ġ�� �������� ���� �������� �ݺ��Ѵ�.]
	while (!IsEqual(&current_p, &end_p)) {

		//���� ��ġ ����, ���������� �˻��Ѵ�.[������ �Դ� ������ �����Ѵ�]
		dn = GetDirectionNum(&current_p);

		//���� �ִ� ������ 2�� �̻��� ���[�������� ������ ���],
		if (dn >= 2) {
			//���� ��ġ�� ���ÿ� �����Ѵ�.
			push(&stack, current_p);
		}
		//���� �ִ� ������ 1���� ���, [1���� ����� ���]
		else if (dn == 1) {
			//�ش� �������� �״�� �����Ѵ�.
			//pass
		}
		else {//���� �ִ� ������ 0���� ���, [���� ���� ���] ���Ƴ��;��Ѵ�.
			current_p = ReturnSavePoint(&stack, current_p); // ���� ��ġ(current_p)���� ������ ��ġ(save_p)���� ���� ���´�.
		}

		ChangeBoard(&current_p, current_p.n);//������ġ�� �����ǿ� ǥ���ϰ�, // ���� ��ġ�� ����
		GetDirection(&current_p);  //������ġ�κ���, ���Ƿ� ������ ���ϰ�, [�������� ��]
		if (current_p.d != 0) {
			MovePoint(&current_p); //������ġ�κ��� ��ĭ �����δ�.
			current_p.n++;

			gotoxy(28, 23);
			printf("current_p.n : %2d, s->length = %2d", current_p.n, stack.length);
			Sleep(200);
			DisplayBoard("��", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
		}
	}
}


int main() {
	// ������ġ�� ���Ѵ�.								
	start_p.x = 0; start_p.y = 9; start_p.d = 0, start_p.n = 1; // ---------------------------------------------------- CASE 1, 2, 3
	//start_p.x = 2; start_p.y = 7; start_p.d = 0, start_p.n = 1; // ---------------------------------------------------- CASE 4, 5

	// ������ġ�� ���Ѵ�.
	end_p.x = 9; end_p.y = 0; end_p.d = 0, end_p.n = 1;    // ---------------------------------------------------- CASE 1, 2, 3
    //end_p.x = 5; end_p.y = 5; end_p.d = 0, end_p.n = 1;  // ---------------------------------------------------- CASE 4, 5

	gotoxy(10, 7);
	printf("DFS2 [������Ȳ ����] �̷� Ž�� �˰��� ����");

	DFS2();

	gotoxy(28, 25);
	printf("�������� �����߽��ϴ�. \n");
	gotoxy(28, 26);
	return 0;
}


// ===============================================================


//�߰��� �ۼ��� �ڵ�
// Ŀ����ǥ�̵�
void gotoxy(unsigned int x, unsigned int y)
{
	COORD xy = { x, y };
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), xy);
}

// Ŀ���������ְų������

void removeCursor(void) // Ŀ�� ������ �����ִ� ��.
{
	CONSOLE_CURSOR_INFO cur;
	GetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cur);
	cur.bVisible = 0;
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cur);
}


//ó�� ȭ�麸���ֱ�
void DisplayBoard(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX, int width, int height, Point* current_p)
{
	int i, j;
	int x, y;
	SetCursor(0);// Ŀ���������.
				 //ȭ�鿡 �׵θ� �׸���
	for (i = X_MIN; i <= X_MAX; i += 2)
	{
		for (j = Y_MIN; j <= Y_MAX; j++)
		{
			if (i == X_MIN || i == X_MAX || j == Y_MAX || j == Y_MIN)
			{
				gotoxy(i, j);
				printf("%s", ch);
			}
		}
	}
	//ȭ�鿡 �� ä���
	// �� : 0, �� : -1, ���� �� : 1�̻�, ���ٸ��� : -2
	x = X_MIN + 2;
	for (i = 0; i<width; i++){
		y = Y_MIN + 1;
		for (j = 0; j<height; j++){
			gotoxy(x, y);

			//printf("%2d", maze[j][i]);//������
			
			
			switch (maze[j][i]) {
			case 0: printf("��"); break; //���� �������� ��
			case -1: printf("��"); break;//��
			case -2: printf("��"); break;//������ + ���ٸ� ��
			default: printf("��"); break;// 1 �̻� [������ ��]
			}
			
			y++;
		}
		x += 2;

	}

	x = X_MIN + (2 * current_p->x) + 2;
	y = Y_MIN + current_p->y + 1;

	gotoxy(x, y);
	printf("��");

	x = X_MIN + (2 * end_p.x) + 2;
	y = Y_MIN + end_p.y + 1;

	gotoxy(x, y);
	printf("��");
}
