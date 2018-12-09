// 2018-05-12-토
// Maze_DFS_BFS.c
// 연결 리스트 스택 또는 큐를 이용해서 미로탈출 알고리즘을 구현

/*
  사실 내가 했던 Maze_Ver2.c는 실제 상황이라고 가정했으며 따라서 원하는 공간으로 점프를 못해서, DFS가 강제되었었다.
  또한 DFS도 막다른 길에 갔을 때, 가장 최근 갈림길로 점프를 못하고, 다시 돌아나와야하는 코드가 추가되어서
  온전하게 자신이 왔던 길로 돌아나오기 위한 방법들이 필요했었다.

  하지만 그냥 점프를 허용하게 될 경우, 엄청나게 문제가 쉬워지게 되며,
  이 경우의 DFS, BFS를 보여주려고 한다.

  참고로, DFS는 스택을 사용하며, BFS는 큐를 사용한다.
*/

#include <stdio.h>
#include <time.h>
#include <Windows.h>

#define MAZE_SIZE 10


// 음... 점프가 가능하기 때문에, 막다른 길이라는 표시도 필요가 없어진다.
// 길 : 0, 벽 : -1, 갔던 길 : 1이상   으로 표시하겠다.

// 테스트할 미로의 주석을 적절하게 제거할 것

/*
// Case1. 구멍이 뚫려있지 않은 경우
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
// Case3. 막 뚫림 + 길이 막힌 경우
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
*/

/*
// Case4. 공터 + 한가운데
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
// Case5.공터 + 한가운데 + 막힘 : 여기서 실패함
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



// Case2. 구멍이 뚫려있는 경우
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

// 배열의 요소는 element타입으로 선언
typedef struct _point {
	int x;
	int y;
	int d; //direction : 자신이 왔던 방향을 의미함. 동, 남, 서, 북 순서대로 1, 2, -1, -2 매칭함 [서로 대칭형]
	int n; //number : 처음 출발부터 자신이 있는 위치까지 거리, 숫자
		   //기존의 Maze_Ver2.c에서는 n의 값이 실질적으로 필요하지만, 지금의 경우는 그냥 보여주기 식으로 사용되며 알고리즘상으로 꼭 필요하지는 않다.
} Point;

Point start_p;   // 시작 위치
Point end_p;     // 종료 위치

int X_MIN = 20, X_MAX = 42;//미로판 x범위
int Y_MIN = 10, Y_MAX = 21; //미로판 y범위
int width = 10, height = 10;//미로판 가로, 세로 길이

void gotoxy(unsigned int x, unsigned int y);//커서이동하기
void removeCursor(void); // 커서 깜빡임 없애주는 것.
void DisplayBoard(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX, int width, int height, Point* current_p);//게임 화면보여주기

// 현재 위치에서, 동남서북의 방향을 확인하고, 갈수 있는 방향의 개수를 구한다.
int GetDirectionNum(Point* p) {
	int dn = 0; // Direction Num

	// 이때 미로 경계선을 나가면 안된다.
	if (p->x < 9 && maze[p->y][p->x + 1] == 0) // 동쪽
		dn++;
	if (p->y < 9 && maze[p->y + 1][p->x] == 0) // 남쪽
		dn++;
	if (p->x > 0 && maze[p->y][p->x - 1] == 0) // 서쪽
		dn++;
	if (p->y > 0 && maze[p->y - 1][p->x] == 0) // 북쪽
		dn++;
	return dn;
}

// 현재 위치에서, 동남서북의 방향을 확인하고, 갈수 있는 방향을 얻는다.
void GetDirection(Point* p) {
	int d = 0;

	//direction. 동, 남, 서, 북 순서대로 1, 2, -1, -2 매칭
	// 이때 미로 경계선을 나가면 안된다.
	if (p->x < 9 && maze[p->y][p->x + 1] == 0) // 동쪽
		d = 1;
	else if (p->y < 9 && maze[p->y + 1][p->x] == 0) // 남쪽
		d = 2;
	else if (p->x > 0 && maze[p->y][p->x - 1] == 0) // 서쪽
		d = -1;
	else if (p->y > 0 && maze[p->y - 1][p->x] == 0) // 북쪽
		d = -2;

	p->d = d;
}

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

//Point 구조체를 담는 연결 리스트의 노드
typedef struct _node Node;
typedef struct _node {  //노드 타입
	Point p;
	Node *next;
} Node;


//DFS 스택 관련
typedef struct _linkedStackType{  //연결 리스트 스택의 타입
	Node *top;      //의미상 tail에 해당
	int length;     //현재 연결 리스트의 사용량
} LinkedStackType;


// 스택 초기화 함수
void init_S(LinkedStackType* s) {
	s->top = NULL;
	s->length = 0;
}
// 스택이 비어있는지 검사하는 함수
int is_empty_S(LinkedStackType* s) {
	return (s->top == NULL);
}

// 삽입 함수
void push(LinkedStackType* s, Point item) {
	Node* temp = (Node*)malloc(sizeof(Node));
	if (temp == NULL) {
		fprintf(stderr, "메모리 할당에러\n");
		return;
	}
	else {
		temp->p = item;
		temp->next = s->top;
		s->top = temp;
		s->length++;

		gotoxy(28, 24);
		printf("스택에 넣는 값 : [x,y,n]: [%2d, %2d, %2d]", item.x, item.y, item.n);
	}
}

// 삭제 함수
Point pop(LinkedStackType* s) {
	if (is_empty_S(s)) {
		gotoxy(28, 26);
		fprintf(stderr, "스택이 비어있음\n");
		gotoxy(28, 27);
		printf("목적지로 가는 길이 없습니다. \n");
		gotoxy(28, 28);
		exit(1);
	}
	else {
		Node *temp = s->top;
		Point p = temp->p;
		s->top = s->top->next;
		free(temp);
		s->length--;
		return p;
	}
}
//-----------------------------------------------------------------------------------------------------------------------------------------------------
//BFS 큐 관련

typedef struct _linkedQueueType {  //연결 리스트 큐의 타입
	Node* front;
	Node* rear;
	int length;
} LinkedQueueType;


// 스택 초기화 함수
void init_Q(LinkedQueueType* q) {
	q->front = NULL;
	q->rear = NULL;
	q->length=0;
}
int is_empty_Q(LinkedQueueType *q)
{
	return (q->front == NULL);
}

// 삽입 함수
void enqueue(LinkedQueueType* q, int x, int y, int n) {
	Node* temp;
	Point p;
	// int x, y의 위치값이 미로 밖이 아니면서 
	if (x < 0 || y < 0 || x > 9 || y > 9) return;

	// 0일 경우만, 큐에 넣는다.
	if (maze[y][x] != 0) return;

	p.x = x;
	p.y = y;
	p.n = n+1;

	temp = (Node*)malloc(sizeof(Node));
	if (temp == NULL) {
		fprintf(stderr, "메모리 할당에러\n");
		return;
	}
	else {
		temp->p = p;
		temp->next = NULL;

		if (is_empty_Q(q))
		{
			q->front = temp;
			q->rear = temp;
		}
		else {
			q->rear->next = temp;
			q->rear = temp;
		}
		q->length++;

		gotoxy(28, 24);
		printf("큐에 넣는 값 : [x,y,n]: [%2d, %2d, %2d]", p.x, p.y, p.n);
		ChangeBoard(&p, -2);//현재위치를 보드판에 표시한다. // 현재 큐에 들어와 있음.
	}
}


// 삭제 함수
Point dequeue(LinkedQueueType* q) {
	if (is_empty_Q(q)) {
		gotoxy(28, 26);
		fprintf(stderr, "큐가 비어있음\n");
		gotoxy(28, 27);
		printf("목적지로 가는 길이 없습니다. \n");
		gotoxy(28, 28);
		exit(1);
	}
	else {
		Node *temp = q->front;
		Point p = temp->p;

		q->front = q->front->next;
		if (q->front == NULL)
			q->rear = NULL;

		free(temp);

		q->length--;

		return p;
	}
}
//-----------------------------------------------------------------------------------------------------------------------------------------------------

void DFS() {
	LinkedStackType stack;
	int dn;         // 이동 가능한 방향의 수. dn = Direction Number

	Point current_p; // 현재 위치

	init_S(&stack);	// 스택을 초기화한다.

	// 출발점으로부터 동남서북을 검사[갈 수 있는 방향의 수를 샘]한다.
	dn = GetDirectionNum(&start_p);

	//갈수 있는 방향이 0개인 경우면서 현재 위치가 도착 위치가 아닌경우,
	if (dn == 0 && !IsEqual(&start_p, &end_p)) {
		//미로를 찾을 수가 없다.
		printf("시작부터, 고립된 위치에서 시작했습니다. \n");
		return;
	}

	current_p = start_p;       //시작위치를 현재위치로 삼는다.

	//목적지에 도달할 때 까지 반복한다. [== 지금위치와 목적지가 같지 않은동안 반복한다.]
	while (!IsEqual(&current_p, &end_p)) {

		//현재 위치 기준, 동남서북을 검사한다.[본인이 왔던 방향은 제외한다]
		dn = GetDirectionNum(&current_p);

		//갈수 있는 방향이 2개 이상일 경우[갈림길이 존재할 경우],
		if (dn >= 2) {
			//현재 위치를 스택에 저장한다.
			push(&stack, current_p);
		}
		//갈수 있는 방향이 1개인 경우, [1자형 통로인 경우]
		else if (dn == 1) {
			//해당 방향으로 그대로 직진한다.
			//pass
		}
		else {//갈수 있는 방향이 0개인 경우, [막힌 길인 경우] 돌아나와야한다.
			ChangeBoard(&current_p, current_p.n);//현재위치를 보드판에 표시하고, // 현재 위치의 숫자
			current_p = pop(&stack); // 현재 위치(current_p)에서 갈림길 위치를 꺼낸다.
		}

		ChangeBoard(&current_p, current_p.n);//현재위치를 보드판에 표시하고, // 현재 위치의 숫자
		GetDirection(&current_p);  //현재위치로부터, 임의로 방향을 정하고, [동남서북 순]
		MovePoint(&current_p);     //현재위치로부터 한칸 움직인다.
		current_p.n++;

		gotoxy(28, 23);
		printf("current_p.n : %2d, s->length = %2d", current_p.n, stack.length);
		Sleep(200);
		DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
	}
}

void BFS() {
	LinkedQueueType queue;
	int dn;         // 이동 가능한 방향의 수. dn = Direction Number

	Point current_p; // 현재 위치

	init_Q(&queue);	// 큐를 초기화한다.


	// 음... DFS의 경우 그래도 최소한으로 스택을 사용할 수 있었는데, BFS의 경우는 무조건 한칸마다 스택에 값을 집어 넣어줘야겠다.

	// 출발점으로부터 동남서북을 검사[갈 수 있는 방향의 수를 샘]한다.
	dn = GetDirectionNum(&start_p);

	//갈수 있는 방향이 0개인 경우면서 현재 위치가 도착 위치가 아닌경우,
	if (dn == 0 && !IsEqual(&start_p, &end_p)) {
		//미로를 찾을 수가 없다.
		printf("시작부터, 고립된 위치에서 시작했습니다. \n");
		return;
	}

	current_p = start_p;       //시작위치를 현재위치로 삼는다.

	//목적지에 도달할 때 까지 반복한다. [== 지금위치와 목적지가 같지 않은동안 반복한다.]
	while (!IsEqual(&current_p, &end_p)) {

		//현재 위치 기준, 동남서북을 검사하며, 갈 수 있으면 큐에 넣는다.
		enqueue(&queue, current_p.x+1, current_p.y, current_p.n); // 동쪽
		enqueue(&queue, current_p.x, current_p.y+1, current_p.n); // 남쪽
		enqueue(&queue, current_p.x-1, current_p.y, current_p.n); // 서쪽
		enqueue(&queue, current_p.x, current_p.y-1, current_p.n); // 북쪽

		gotoxy(28, 23);
		printf("current_p.n : %2d, q->length = %2d", current_p.n, queue.length);
		Sleep(300);
		DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
		ChangeBoard(&current_p, current_p.n);
		current_p = dequeue(&queue);// 큐로부터 노드를 하나 꺼낸다.
	}
}

int main() 
{
	// 시작위치를 정한다.								
	start_p.x = 0; start_p.y = 9; start_p.d = 0, start_p.n = 1; // ---------------------------------------------------- CASE 1, 2, 3
	//start_p.x = 2; start_p.y = 7; start_p.d = 0, start_p.n = 1; // ---------------------------------------------------- CASE 4, 5

	// 종료위치를 정한다.
	end_p.x = 9; end_p.y = 0; end_p.d = 0, end_p.n = 1;    // ---------------------------------------------------- CASE 1, 2, 3
	//end_p.x = 5; end_p.y = 5; end_p.d = 0, end_p.n = 1;  // ---------------------------------------------------- CASE 4, 5

	int input;

	gotoxy(10, 5);
	printf("원하는 미로 탐색 알고리즘을 입력하시오 [1:DFS, 그외:BFS] : " );
	scanf("%d", &input);

	gotoxy(10, 7);
	if (input == 1) {
		printf("DFS 미로 탐색 알고리즘 동작");
		DFS();
	}
	else{
		printf("BFS 미로 탐색 알고리즘 동작");
		BFS();
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
			case 0: printf("□"); break; //아직 안지나간 길
			case -1: printf("■"); break;//벽
			case -2: printf("▦"); break;//지나간 + 막다른 길
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
