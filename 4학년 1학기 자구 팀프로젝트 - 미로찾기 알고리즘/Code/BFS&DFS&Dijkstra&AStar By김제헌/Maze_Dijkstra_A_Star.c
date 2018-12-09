// CF. 수업 PPT 코드 

#include <stdio.h>
#include <math.h>

// 2018-05-29-화
// Maze_Dijkstra_A_Star.c
// 배열로 구현된 힙트리를 이용해서 다익스트라 알고리즘을 구현

/*
기존 베이스는 늘 우려먹던 기존 환경을 또 우려먹는다.
기본적으로는 BFS와 비슷하다.
그저 삽입 및 삭제 연산이 힙트리를 이용한다는 차이가 있을 뿐이다.
힙트리는 PPT 수업자료를 인용하였다.
*/

#include <stdio.h>
#include <time.h>
#include <Windows.h>

#define MAZE_SIZE 10


// 음... 점프가 가능하기 때문에, 막다른 길이라는 표시도 필요가 없어진다.
// 단, 다익스트라를 표현하기 위해서 벽은 -1로 하되, '길은 0이상'으로 표현한다.
// 갔던 길의 의미가 없어진다. 그저 지금값보다 낮으면 [갱신 가능하면] 간다.
//고로   길 : 1 이상, 벽 : -1  으로 표시하겠다.

// 테스트할 미로의 주석을 적절하게 제거할 것


/*
// 다익스트라용 미로. 9999는 아직 안가본 곳
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

// Case2. 구멍이 뚫려있는 경우 - 노드간의 간선 비용 값
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
// Case2. 구멍이 뚫려있는 경우 - 노드간의 간선 비용 값 [이경우 일반적인 DFS와 크게 차이가 없다]
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


// Case4. 공터 + 한가운데
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
// Case4. 구멍이 뚫려있는 경우 - 노드간의 간선 비용 값
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


// 배열의 요소는 element타입으로 선언
typedef struct _point {
	int x;
	int y;
	int d;  //direction : 자신이 왔던 방향을 의미함. 동, 남, 서, 북 순서대로 1, 2, -1, -2 매칭함 [서로 대칭형]
	int n;  //number : 처음 출발부터 자신이 있는 위치까지 거리, 숫자 - 이 값을 이용해서 총 합의 거리를 계산한다.
	int n_; //A* 알고리즘용 변수. 출발점으로부터 자신이 있는 위치까지의 거리 [휴리스틱 비용을 제외한 값]
} Point;

Point start_p;   // 시작 위치
Point end_p;     // 종료 위치

int X_MIN = 20, X_MAX = 42;//미로판 x범위
int Y_MIN = 10, Y_MAX = 21; //미로판 y범위
int width = 10, height = 10;//미로판 가로, 세로 길이

void gotoxy(unsigned int x, unsigned int y);//커서이동하기
void removeCursor(void); // 커서 깜빡임 없애주는 것.
void DisplayBoard(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX, int width, int height, Point* current_p);//게임 화면보여주기




// 두 점의 위치가 같은지 확인하는 함수
int IsEqual(Point* p1, Point* p2) {
	int isEqual;

	// 위치가 같으면,
	if (p1->x == p2->x && p1->y == p2->y) {
		isEqual = 1;
	}
	else {// 위치가 다르면,
		isEqual = 0;
	}
	return isEqual;
}


// 해당 위치p를 num의 값으로 바꾼다.
// - 길 : 0, 벽 : -1, 갔던 길 : 1이상, 막다른길 : -2
void ChangeBoard(Point* p, int num) {
	maze[p->y][p->x] = num;
}

// P의 d의 방향으로 1칸 이동한다.
void MovePoint(Point* p) {
	switch (p->d) {
	case  1: p->x = p->x++; break; // 동쪽 이동
	case  2: p->y = p->y++; break; // 남쪽 이동
	case -1: p->x = p->x--; break; // 서쪽 이동
	case -2: p->y = p->y--; break; // 북쪽 이동
	default: break;
	}
}

//-----------------------------------------------------------------------------------------------------------------------------------------------------
//힙트리 관련

#define MAX_ELEMENT 200

// Point들을 담고 있는 힙트리
typedef struct {
	Point heap[MAX_ELEMENT];
	int heap_size;
} HeapType;

// 초기화 함수
void init(HeapType *h)
{
	h->heap_size = 0;
}

// 삽입 함수
void insert_min_heap(HeapType *h, int x, int y, int n) 
{
	Point p;
	int i;

	// 삽입 조건 3가지
	// 1. x, y의 위치값이 미로 밖이 아니면서
	if (x < 0 || y < 0 || x > 9 || y > 9) return;

	// 2. x, y의 위치값이 벽이 아니며,
	if (maze[y][x] == -1) return;

	// 3. dist[v] < dist[u] + length(u, v) 하지 않을 경우
	if (maze[y][x] < maze2[y][x] + n) return;

	//위 3가지를 만족한 경우에만, 힙트리에 추가한다.
	p.x = x;
	p.y = y;
	p.n = maze2[y][x] + n;

	i = ++(h->heap_size);

	//  트리를 거슬러 올라가면서 부모 노드와 비교하는 과정
	while ((i != 1) && (p.n < h->heap[i / 2].n)) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}
	h->heap[i] = p;     // 새로운 노드를 삽입

	gotoxy(28, 24);
	printf("힙트리에 넣는 값 : [x,y,n]: [%2d, %2d, %4d]", p.x, p.y, p.n);
	ChangeBoard(&p, p.n);//현재위치를 보드판에 표시한다.
}

// 현재 위치로부터 목적지까지의 휴리스틱 비용을 구한다.
int heuristic(int x, int y) {
	gotoxy(30, 26);
	printf("휴리스틱 비용 : %4d", ((abs(end_p.x - x) * 20) + (abs(end_p.y - y) * 20)));
	return  ( (abs(end_p.x - x) * 20) + (abs(end_p.y - y) * 20) );
}

void insert_min_heap_A(HeapType *h, int x, int y, int n)
{
	Point p;
	int i;

	// 삽입 조건 3가지
	// 1. x, y의 위치값이 미로 밖이 아니면서
	if (x < 0 || y < 0 || x > 9 || y > 9) return;

	// 2. x, y의 위치값이 벽이 아니며,
	if (maze[y][x] == -1) return;

	// 3. dist[v] < dist[u] + length(u, v) 하지 않을 경우
	if (maze[y][x] < maze2[y][x] + n + 10 + heuristic(x, y)) return;

	//위 3가지를 만족한 경우에만, 힙트리에 추가한다.
	p.x = x;
	p.y = y;
	p.n_ = maze2[y][x] + n + 10;
	p.n = maze2[y][x] + n + 10 + heuristic(x, y);

	i = ++(h->heap_size);

	//  트리를 거슬러 올라가면서 부모 노드와 비교하는 과정
	while ((i != 1) && (p.n < h->heap[i / 2].n)) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}
	h->heap[i] = p;     // 새로운 노드를 삽입

	gotoxy(28, 24);
	printf("힙트리에 넣는 값 : [x,y,n]: [%2d, %2d, %4d]", p.x, p.y, p.n);
	ChangeBoard(&p, p.n_);//현재위치를 보드판에 표시한다.
}

// 삭제 함수
Point delete_min_heap(HeapType *h)
{
	int parent, child;
	Point item, temp;

	if (h->heap_size == 0) {
		gotoxy(28, 26);
		fprintf(stderr, "힙트리가 비어있음\n");
		gotoxy(28, 27);
		printf("목적지로 가는 길이 없습니다. \n");
		gotoxy(28, 28);
		exit(1);
	}

	item = h->heap[1];
	temp = h->heap[(h->heap_size)--];
	parent = 1;
	child = 2;
	while (child <= h->heap_size) {
		// 현재 노드의 자식노드중 더 큰 자식노드를 찾는다.
		if ((child < h->heap_size) && (h->heap[child].n) > h->heap[child + 1].n)
			child++;
		if (temp.n <= h->heap[child].n) break;
		// 한단계 아래로 이동
		h->heap[parent] = h->heap[child];
		parent = child;
		child *= 2;
	}
	h->heap[parent] = temp;
	return item;
}
//-----------------------------------------------------------------------------------------------------------------------------------------------------

void Dijkstra() {
	HeapType heap;	// 힙 생성
	Point current_p; // 현재 위치

	init(&heap);	 // 힙 초기화

	current_p = start_p;       //시작위치를 현재위치로 삼는다.

	insert_min_heap(&heap, current_p.x, current_p.y, current_p.n); // 동쪽
	current_p = delete_min_heap(&heap);// 큐로부터 노드를 하나 꺼낸다.
	Sleep(300);
	DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);

	//목적지에 도달할 때 까지 반복한다. [== 지금위치와 목적지가 같지 않은동안 반복한다.]
	while (!IsEqual(&current_p, &end_p)) {

		//현재 위치 기준, 동남서북을 검사하며, 3가지 조건에 맞으면 힙에 넣는다.
		insert_min_heap(&heap, current_p.x + 1, current_p.y, current_p.n); // 동쪽
		insert_min_heap(&heap, current_p.x, current_p.y + 1, current_p.n); // 남쪽
		insert_min_heap(&heap, current_p.x - 1, current_p.y, current_p.n); // 서쪽
		insert_min_heap(&heap, current_p.x, current_p.y - 1, current_p.n); // 북쪽

		gotoxy(28, 23);
		printf("current_p.n : %4d, h.heap_size = %2d", current_p.n, heap.heap_size);
		Sleep(300);
		DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
		current_p = delete_min_heap(&heap);// 큐로부터 노드를 하나 꺼낸다.
	}
	DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
}

void A_Star() {
	HeapType heap;	// 힙 생성
	Point current_p; // 현재 위치

	init(&heap);	 // 힙 초기화

	current_p = start_p;       //시작위치를 현재위치로 삼는다.

	insert_min_heap_A(&heap, current_p.x, current_p.y, current_p.n); // 동쪽
	current_p = delete_min_heap(&heap);// 큐로부터 노드를 하나 꺼낸다.
	Sleep(300);
	DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);

	//목적지에 도달할 때 까지 반복한다. [== 지금위치와 목적지가 같지 않은동안 반복한다.]
	while (!IsEqual(&current_p, &end_p)) {

		//현재 위치 기준, 동남서북을 검사하며, 3가지 조건에 맞으면 힙에 넣는다.
		insert_min_heap_A(&heap, current_p.x + 1, current_p.y, current_p.n); // 동쪽
		insert_min_heap_A(&heap, current_p.x, current_p.y + 1, current_p.n); // 남쪽
		insert_min_heap_A(&heap, current_p.x - 1, current_p.y, current_p.n); // 서쪽
		insert_min_heap_A(&heap, current_p.x, current_p.y - 1, current_p.n); // 북쪽

		gotoxy(28, 23);
		printf("current_p.n : %4d, h.heap_size = %2d", current_p.n, heap.heap_size);
		Sleep(300);
		DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
		current_p = delete_min_heap(&heap);// 큐로부터 노드를 하나 꺼낸다.
	}
	DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
}

int main()
{
	// 시작위치를 정한다.
	//start_p.x = 0; start_p.y = 9; start_p.d = 0, start_p.n = 0; // ---------------------------------------------------- CASE 1, 2, 3
	//start_p.x = 2; start_p.y = 7; start_p.d = 0, start_p.n = 0; // ---------------------------------------------------- CASE 4, 5
	//start_p.x = 4; start_p.y = 4; start_p.d = 0, start_p.n = 0; // ---------------------------------------------------- CASE 2'

	start_p.x = 5; start_p.y = 5; start_p.d = 0, start_p.n = 0; // ---------------------------------------------------- CASE 5'


	// 종료위치를 정한다.
	end_p.x = 9; end_p.y = 0; end_p.d = 0, end_p.n = 0;    // ---------------------------------------------------- CASE 1, 2, 3
	//end_p.x = 5; end_p.y = 5; end_p.d = 0, end_p.n = 0;  // ---------------------------------------------------- CASE 4, 5

	int input;

	gotoxy(10, 5);
	printf("원하는 미로 탐색 알고리즘을 입력하시오 [1:Dijkstra, 그외 : A * ] : ");
	scanf("%d", &input);

	gotoxy(10, 7);
	if (input == 1) {
		printf("Dijkstra 알고리즘 미로 탐색 알고리즘 동작");
		Dijkstra();
	}
	else {
		printf("A * 알고리즘 미로 탐색 알고리즘 동작");
		A_Star();
	}

	gotoxy(28, 25);
	printf("목적지에 도착했습니다. \n");
	gotoxy(28, 26);

	return 0;
}


// ===============================================================


//추가로 작성한 코드
// 커서좌표이동
void gotoxy(unsigned int x, unsigned int y)
{
	COORD xy = { x, y };
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), xy);
}

// 커서를보여주거나숨긴다

void removeCursor(void) // 커서 깜빡임 없애주는 것.
{
	CONSOLE_CURSOR_INFO cur;
	GetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cur);
	cur.bVisible = 0;
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cur);
}


//게임 화면보여주기
void DisplayBoard(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX, int width, int height, Point* current_p)
{
	int i, j;
	int x, y;
	SetCursor(0);// 커서를숨긴다.
	//화면에 테두리 그리기
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
	//화면에 값 채우기
	// 길 : 0, 벽 : -1, 갔던 길 : 1이상, 막다른길 : -2
	x = X_MIN + 2;
	for (i = 0; i < width; i++) {
		y = Y_MIN + 1;
		for (j = 0; j < height; j++) {
			gotoxy(x, y);

			//printf("%2d", maze[j][i]);//디버깅용

			
			switch (maze[j][i]) {
			case 9999: printf("□"); break; //아직 안지나간 길
			case -1: printf("■"); break;//벽
			default: printf("◇"); break;// 1 이상 [지나간 길]
			}
			

			y++;
		}
		x += 2;
	}

	x = X_MIN + (2 * current_p->x) + 2;
	y = Y_MIN + current_p->y + 1;

	gotoxy(x, y);
	printf("●");

	x = X_MIN + (2 * end_p.x) + 2;
	y = Y_MIN + end_p.y + 1;

	gotoxy(x, y);
	printf("★");
}

