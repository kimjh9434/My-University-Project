// CF. ���� PPT �ڵ� 

#include <stdio.h>
#include <math.h>

// 2018-05-29-ȭ
// Maze_Dijkstra_A_Star.c
// �迭�� ������ ��Ʈ���� �̿��ؼ� ���ͽ�Ʈ�� �˰����� ����

/*
���� ���̽��� �� ����Դ� ���� ȯ���� �� ����Դ´�.
�⺻�����δ� BFS�� ����ϴ�.
���� ���� �� ���� ������ ��Ʈ���� �̿��Ѵٴ� ���̰� ���� ���̴�.
��Ʈ���� PPT �����ڷḦ �ο��Ͽ���.
*/

#include <stdio.h>
#include <time.h>
#include <Windows.h>

#define MAZE_SIZE 10


// ��... ������ �����ϱ� ������, ���ٸ� ���̶�� ǥ�õ� �ʿ䰡 ��������.
// ��, ���ͽ�Ʈ�� ǥ���ϱ� ���ؼ� ���� -1�� �ϵ�, '���� 0�̻�'���� ǥ���Ѵ�.
// ���� ���� �ǹ̰� ��������. ���� ���ݰ����� ������ [���� �����ϸ�] ����.
//���   �� : 1 �̻�, �� : -1  ���� ǥ���ϰڴ�.

// �׽�Ʈ�� �̷��� �ּ��� �����ϰ� ������ ��


/*
// ���ͽ�Ʈ��� �̷�. 9999�� ���� �Ȱ��� ��
int maze[MAZE_SIZE][MAZE_SIZE] = {
{ 9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999 },
{ 9999,    -1,    -1,    -1,    -1,    -1,    -1,    -1,  9999,    -1 },
{ 9999,    -1,  9999,  9999,  9999,  9999,  9999,  9999,    -1,  9999 },
{ 9999,    -1,  9999,    -1,  9999,    -1,    -1,  9999,  9999,  9999 },
{ 9999,    -1,  9999,    -1,  9999,  9999,    -1,    -1,    -1,  9999 },
{ 9999,  9999,  9999,    -1,  9999,    -1,  9999,  9999,  9999,  9999 },
{ 9999,    -1,  9999,    -1,    -1,  9999,    -1,  9999,    -1,  9999 },
{ 9999,    -1,  9999,  9999,  9999,  9999,  9999,  9999,    -1,  9999 },
{ 9999,    -1,  9999,    -1,  9999,    -1,  9999,    -1,  9999,    -1 },
{ 9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999 }
};

// Case2. ������ �շ��ִ� ��� - ��尣�� ���� ��� ��
int maze2[MAZE_SIZE][MAZE_SIZE] = {
{ 1,  1,  1,  4,  1,  1,  2,  1, 20,  1 },
{ 2, -1, -1, -1, -1, -1, -1, -1,  1, -1 },
{ 3, -1,  5,  1,  6,  2,  1,  2, -1,  1 },
{ 6, -1,  1, -1,  1, -1, -1,  1,  1,  3 },
{ 3, -1,  3, -1,  7,  1, -1, -1, -1,  1 },
{ 2,  1,  1, -1,  1, -1,  4, 10,  1,  1 },
{ 1, -1,  2, -1, -1,  1, -1,  1, -1,  5 },
{ 1, -1,  1,  1,  1,  1,  1,  1, -1,  1 },
{ 1, -1,  11, -1, 10, -1,  1, -1,  1, -1 },
{ 1,  8,  1,  1,  1,  1,  7,  1,  1,  1 }
};
*/

/*
// Case2. ������ �շ��ִ� ��� - ��尣�� ���� ��� �� [�̰�� �Ϲ����� DFS�� ũ�� ���̰� ����]
int maze2[MAZE_SIZE][MAZE_SIZE] = {
{ 1,  1,  1,  1,  1,  1,  1,  1,  1,  1 },
{ 1, -1, -1, -1, -1, -1, -1, -1,  1, -1 },
{ 1, -1,  1,  1,  1,  1,  1,  1, -1,  1 },
{ 1, -1,  1, -1,  1, -1, -1,  1,  1,  1 },
{ 1, -1,  1, -1,  1,  1, -1, -1, -1,  1 },
{ 1,  1,  1, -1,  1, -1,  1,  1,  1,  1 },
{ 1, -1,  1, -1, -1,  1, -1,  1, -1,  1 },
{ 1, -1,  1,  1,  1,  1,  1,  1, -1,  1 },
{ 1, -1,  1, -1,  1, -1,  1, -1,  1, -1 },
{ 1,  1,  1,  1,  1,  1,  1,  1,  1,  1 }
};
*/


// Case4. ���� + �Ѱ��
int maze[MAZE_SIZE][MAZE_SIZE] = {
{ 9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999 },
{ 9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999 },
{ 9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999 },
{ 9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999 },
{ 9999,  9999,  9999,  9999,    -1,    -1,    -1,  9999,  9999,  9999 },
{ 9999,  9999,  9999,  9999,    -1,  9999,    -1,  9999,  9999,  9999 },
{ 9999,  9999,  9999,  9999,  9999,  9999,    -1,  9999,  9999,  9999 },
{ 9999,  9999,  9999,  9999,    -1,    -1,    -1,  9999,  9999,  9999 },
{ 9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999 },
{ 9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999,  9999 }
};
// Case4. ������ �շ��ִ� ��� - ��尣�� ���� ��� ��
int maze2[MAZE_SIZE][MAZE_SIZE] = {
{ 1,  1,  1,  4,  1,  1,  2,  1, 20,  1 },
{ 2,  8,  8,  6,  8,  8,  8,  8,  1,  8 },
{ 3,  2,  5,  1,  6,  2,  1,  2,  8,  1 },
{ 6,  8,  1, 20,  1,  8,  8,  1,  1,  3 },
{ 3,  8,  3,  8, -1, -1, -1,  8,  8,  1 },
{ 2,  1,  1, 10, -1,  8, -1, 10,  1,  1 },
{ 1,  8,  2,  8,  8,  1, -1,  1,  8,  5 },
{ 1,  4,  1,  1, -1, -1, -1,  1,  8,  1 },
{ 1,  8,  11, 8, 10,  8,  1,  8,   1, 8 },
{ 1,  5,  1,  1,  1,  1,  7,  1,  1,  1 }
};


// �迭�� ��Ҵ� elementŸ������ ����
typedef struct _point {
	int x;
	int y;
	int d;  //direction : �ڽ��� �Դ� ������ �ǹ���. ��, ��, ��, �� ������� 1, 2, -1, -2 ��Ī�� [���� ��Ī��]
	int n;  //number : ó�� ��ߺ��� �ڽ��� �ִ� ��ġ���� �Ÿ�, ���� - �� ���� �̿��ؼ� �� ���� �Ÿ��� ����Ѵ�.
	int n_; //A* �˰���� ����. ��������κ��� �ڽ��� �ִ� ��ġ������ �Ÿ� [�޸���ƽ ����� ������ ��]
} Point;

Point start_p;   // ���� ��ġ
Point end_p;     // ���� ��ġ

int X_MIN = 20, X_MAX = 42;//�̷��� x����
int Y_MIN = 10, Y_MAX = 21; //�̷��� y����
int width = 10, height = 10;//�̷��� ����, ���� ����

void gotoxy(unsigned int x, unsigned int y);//Ŀ���̵��ϱ�
void removeCursor(void); // Ŀ�� ������ �����ִ� ��.
void DisplayBoard(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX, int width, int height, Point* current_p);//���� ȭ�麸���ֱ�




// �� ���� ��ġ�� ������ Ȯ���ϴ� �Լ�
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

//-----------------------------------------------------------------------------------------------------------------------------------------------------
//��Ʈ�� ����

#define MAX_ELEMENT 200

// Point���� ��� �ִ� ��Ʈ��
typedef struct {
	Point heap[MAX_ELEMENT];
	int heap_size;
} HeapType;

// �ʱ�ȭ �Լ�
void init(HeapType *h)
{
	h->heap_size = 0;
}

// ���� �Լ�
void insert_min_heap(HeapType *h, int x, int y, int n) 
{
	Point p;
	int i;

	// ���� ���� 3����
	// 1. x, y�� ��ġ���� �̷� ���� �ƴϸ鼭
	if (x < 0 || y < 0 || x > 9 || y > 9) return;

	// 2. x, y�� ��ġ���� ���� �ƴϸ�,
	if (maze[y][x] == -1) return;

	// 3. dist[v] < dist[u] + length(u, v) ���� ���� ���
	if (maze[y][x] < maze2[y][x] + n) return;

	//�� 3������ ������ ��쿡��, ��Ʈ���� �߰��Ѵ�.
	p.x = x;
	p.y = y;
	p.n = maze2[y][x] + n;

	i = ++(h->heap_size);

	//  Ʈ���� �Ž��� �ö󰡸鼭 �θ� ���� ���ϴ� ����
	while ((i != 1) && (p.n < h->heap[i / 2].n)) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}
	h->heap[i] = p;     // ���ο� ��带 ����

	gotoxy(28, 24);
	printf("��Ʈ���� �ִ� �� : [x,y,n]: [%2d, %2d, %4d]", p.x, p.y, p.n);
	ChangeBoard(&p, p.n);//������ġ�� �����ǿ� ǥ���Ѵ�.
}

// ���� ��ġ�κ��� ������������ �޸���ƽ ����� ���Ѵ�.
int heuristic(int x, int y) {
	gotoxy(30, 26);
	printf("�޸���ƽ ��� : %4d", ((abs(end_p.x - x) * 20) + (abs(end_p.y - y) * 20)));
	return  ( (abs(end_p.x - x) * 20) + (abs(end_p.y - y) * 20) );
}

void insert_min_heap_A(HeapType *h, int x, int y, int n)
{
	Point p;
	int i;

	// ���� ���� 3����
	// 1. x, y�� ��ġ���� �̷� ���� �ƴϸ鼭
	if (x < 0 || y < 0 || x > 9 || y > 9) return;

	// 2. x, y�� ��ġ���� ���� �ƴϸ�,
	if (maze[y][x] == -1) return;

	// 3. dist[v] < dist[u] + length(u, v) ���� ���� ���
	if (maze[y][x] < maze2[y][x] + n + 10 + heuristic(x, y)) return;

	//�� 3������ ������ ��쿡��, ��Ʈ���� �߰��Ѵ�.
	p.x = x;
	p.y = y;
	p.n_ = maze2[y][x] + n + 10;
	p.n = maze2[y][x] + n + 10 + heuristic(x, y);

	i = ++(h->heap_size);

	//  Ʈ���� �Ž��� �ö󰡸鼭 �θ� ���� ���ϴ� ����
	while ((i != 1) && (p.n < h->heap[i / 2].n)) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}
	h->heap[i] = p;     // ���ο� ��带 ����

	gotoxy(28, 24);
	printf("��Ʈ���� �ִ� �� : [x,y,n]: [%2d, %2d, %4d]", p.x, p.y, p.n);
	ChangeBoard(&p, p.n_);//������ġ�� �����ǿ� ǥ���Ѵ�.
}

// ���� �Լ�
Point delete_min_heap(HeapType *h)
{
	int parent, child;
	Point item, temp;

	if (h->heap_size == 0) {
		gotoxy(28, 26);
		fprintf(stderr, "��Ʈ���� �������\n");
		gotoxy(28, 27);
		printf("�������� ���� ���� �����ϴ�. \n");
		gotoxy(28, 28);
		exit(1);
	}

	item = h->heap[1];
	temp = h->heap[(h->heap_size)--];
	parent = 1;
	child = 2;
	while (child <= h->heap_size) {
		// ���� ����� �ڽĳ���� �� ū �ڽĳ�带 ã�´�.
		if ((child < h->heap_size) && (h->heap[child].n) > h->heap[child + 1].n)
			child++;
		if (temp.n <= h->heap[child].n) break;
		// �Ѵܰ� �Ʒ��� �̵�
		h->heap[parent] = h->heap[child];
		parent = child;
		child *= 2;
	}
	h->heap[parent] = temp;
	return item;
}
//-----------------------------------------------------------------------------------------------------------------------------------------------------

void Dijkstra() {
	HeapType heap;	// �� ����
	Point current_p; // ���� ��ġ

	init(&heap);	 // �� �ʱ�ȭ

	current_p = start_p;       //������ġ�� ������ġ�� ��´�.

	insert_min_heap(&heap, current_p.x, current_p.y, current_p.n); // ����
	current_p = delete_min_heap(&heap);// ť�κ��� ��带 �ϳ� ������.
	Sleep(300);
	DisplayBoard("��", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);

	//�������� ������ �� ���� �ݺ��Ѵ�. [== ������ġ�� �������� ���� �������� �ݺ��Ѵ�.]
	while (!IsEqual(&current_p, &end_p)) {

		//���� ��ġ ����, ���������� �˻��ϸ�, 3���� ���ǿ� ������ ���� �ִ´�.
		insert_min_heap(&heap, current_p.x + 1, current_p.y, current_p.n); // ����
		insert_min_heap(&heap, current_p.x, current_p.y + 1, current_p.n); // ����
		insert_min_heap(&heap, current_p.x - 1, current_p.y, current_p.n); // ����
		insert_min_heap(&heap, current_p.x, current_p.y - 1, current_p.n); // ����

		gotoxy(28, 23);
		printf("current_p.n : %4d, h.heap_size = %2d", current_p.n, heap.heap_size);
		Sleep(300);
		DisplayBoard("��", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
		current_p = delete_min_heap(&heap);// ť�κ��� ��带 �ϳ� ������.
	}
	DisplayBoard("��", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
}

void A_Star() {
	HeapType heap;	// �� ����
	Point current_p; // ���� ��ġ

	init(&heap);	 // �� �ʱ�ȭ

	current_p = start_p;       //������ġ�� ������ġ�� ��´�.

	insert_min_heap_A(&heap, current_p.x, current_p.y, current_p.n); // ����
	current_p = delete_min_heap(&heap);// ť�κ��� ��带 �ϳ� ������.
	Sleep(300);
	DisplayBoard("��", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);

	//�������� ������ �� ���� �ݺ��Ѵ�. [== ������ġ�� �������� ���� �������� �ݺ��Ѵ�.]
	while (!IsEqual(&current_p, &end_p)) {

		//���� ��ġ ����, ���������� �˻��ϸ�, 3���� ���ǿ� ������ ���� �ִ´�.
		insert_min_heap_A(&heap, current_p.x + 1, current_p.y, current_p.n); // ����
		insert_min_heap_A(&heap, current_p.x, current_p.y + 1, current_p.n); // ����
		insert_min_heap_A(&heap, current_p.x - 1, current_p.y, current_p.n); // ����
		insert_min_heap_A(&heap, current_p.x, current_p.y - 1, current_p.n); // ����

		gotoxy(28, 23);
		printf("current_p.n : %4d, h.heap_size = %2d", current_p.n, heap.heap_size);
		Sleep(300);
		DisplayBoard("��", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
		current_p = delete_min_heap(&heap);// ť�κ��� ��带 �ϳ� ������.
	}
	DisplayBoard("��", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
}

int main()
{
	// ������ġ�� ���Ѵ�.
	//start_p.x = 0; start_p.y = 9; start_p.d = 0, start_p.n = 0; // ---------------------------------------------------- CASE 1, 2, 3
	//start_p.x = 2; start_p.y = 7; start_p.d = 0, start_p.n = 0; // ---------------------------------------------------- CASE 4, 5
	//start_p.x = 4; start_p.y = 4; start_p.d = 0, start_p.n = 0; // ---------------------------------------------------- CASE 2'

	start_p.x = 5; start_p.y = 5; start_p.d = 0, start_p.n = 0; // ---------------------------------------------------- CASE 5'


	// ������ġ�� ���Ѵ�.
	end_p.x = 9; end_p.y = 0; end_p.d = 0, end_p.n = 0;    // ---------------------------------------------------- CASE 1, 2, 3
	//end_p.x = 5; end_p.y = 5; end_p.d = 0, end_p.n = 0;  // ---------------------------------------------------- CASE 4, 5

	int input;

	gotoxy(10, 5);
	printf("���ϴ� �̷� Ž�� �˰����� �Է��Ͻÿ� [1:Dijkstra, �׿� : A * ] : ");
	scanf("%d", &input);

	gotoxy(10, 7);
	if (input == 1) {
		printf("Dijkstra �˰��� �̷� Ž�� �˰��� ����");
		Dijkstra();
	}
	else {
		printf("A * �˰��� �̷� Ž�� �˰��� ����");
		A_Star();
	}

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


//���� ȭ�麸���ֱ�
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
	for (i = 0; i < width; i++) {
		y = Y_MIN + 1;
		for (j = 0; j < height; j++) {
			gotoxy(x, y);

			//printf("%2d", maze[j][i]);//������

			
			switch (maze[j][i]) {
			case 9999: printf("��"); break; //���� �������� ��
			case -1: printf("��"); break;//��
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

