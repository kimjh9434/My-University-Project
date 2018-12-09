// 2018-04-20-금
// Maze_Ver2.c
// 연결 리스트 스택을 이용해서 미로탈출 알고리즘을 구현

/*
음... 버전 1에서 CASE 3까지 처리했으나, 빈공간이 많아짐에 따라 처리하지 못했다.

에러가 발생하는 포인트는, [이미 지나간 길 포함해서] 막다른 길에 도달하였을 때
자신이 왔던 길로 '그대로' 돌아와야 했는데 
길 : 0, 벽 : 1, 갔던 길 : 2, 막다른길 : 3 으로만 표시하니까,
'이미 지나갔던길' 만 따라오자니, 너무 지멋대로 가는 문제점이 발생했다.

그래서, 기준을 변경하기로 했다.
길 : 0, 벽 : -1, 갔던 길 : 1이상, 막다른길 : -2 로 표시해야겠다.

그리고, '움직일때마다, 길을 1씩 증가시키는 것'이 포인트다.
이러면, 나중에 돌아올때, 1씩 줄어든 위치가 자신이 온 길이기 때문에 안전하게 돌아올 수가 있다.
*/


#include <stdio.h>
#include <time.h>
#include <Windows.h>

#define MAZE_SIZE 10


// 길 : 0, 벽 : -1, 갔던 길 : 1이상, 막다른길 : -2 로 표시해야겠다.

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

// 배열의 요소는 element타입으로 선언
typedef struct _point {
	int x;
	int y;
	int d; //direction. 동, 남, 서, 북 순서대로 1, 2, -1, -2 매칭 
		   //자신이 왔던 방향을 의미함. [서로 대칭형]
	int n; //number : 처음 출발부터 자신이 있는 위치까지 거리, 숫자 
} Point;

//관련 데이터를 구조체로 묶어  함수의 파라미터로 전달
typedef struct _stackNode StackNode;
typedef struct _stackNode {  //노드 타입
	Point p;
	StackNode *prev;
} StackNode;

typedef struct {         //연결 리스트 스택의 타입
	StackNode *top;      //의미상 tail에 해당
	int length;
} LinkedStackType;

void gotoxy(unsigned int x, unsigned int y);//커서이동하기
void removeCursor(void); // 커서 깜빡임 없애주는 것.
void DisplayBoard(char ch[], int X_MIN, int Y_MIN, int X_MAX, int Y_MAX, int width, int height, Point* current_p);//처음 화면보여주기

Point start_p;   // 시작 위치
Point end_p;     // 종료 위치
int X_MIN = 20, X_MAX = 42;//미로판 x범위
int Y_MIN = 10, Y_MAX = 21; //미로판 y범위
int width = 10, height = 10;//미로판 가로, 세로 길이



// 스택 초기화 함수 
void init(LinkedStackType* s) {
	s->top = NULL;
	s->length = 0;
}
int is_empty(LinkedStackType* s) {
	return (s->top == NULL);
}

// 삽입 함수 
void push(LinkedStackType* s, Point item) {
	StackNode* temp = (StackNode*)malloc(sizeof(StackNode));
	if (temp == NULL) {
		fprintf(stderr, "메모리 할당에러\n");
		return;
	}
	else {
		temp->p = item;
		temp->prev = s->top;
		s->top = temp;
		s->length++;

		gotoxy(28, 24);
		printf("스택에 넣는 값 : [x,y,n]: [%2d, %2d, %2d]", item.x, item.y, item.n);
	}
}

// 삭제 함수 
Point pop(LinkedStackType* s) {
	if (is_empty(s)) {
		gotoxy(28, 26);
		fprintf(stderr, "스택이 비어있음\n");
		gotoxy(28, 27);
		printf("목적지로 가는 길이 없습니다. \n");
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

// 현재 위치에서, 동남서북의 방향을 확인하고, 자신이 왔던 길[= 자신 위치의 숫자 -1]의 방향을 얻는다.
void GetDirection2(Point* p) {
	int d = 0;

	//direction. 동, 남, 서, 북 순서대로 1, 2, -1, -2 매칭 
	if (p->x < 9 && maze[p->y][p->x + 1] == (p->n - 1) ) // 동쪽
		d = 1;
	else if (p->y < 9 && maze[p->y + 1][p->x] == (p->n - 1) ) // 남쪽
		d = 2;
	else if (p->x > 0 && maze[p->y][p->x - 1] == (p->n - 1) ) // 서쪽
		d = -1;
	else if (p->y > 0 && maze[p->y - 1][p->x] == (p->n - 1) ) // 북쪽
		d = -2;

	p->d = d;
}

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


// 갈수 있는 방향이 0개인 경우, [막힌 길인 경우] 돌아나와야한다.
// 현재 위치(current_p)에서 갈림길 위치(save_p)까지 돌아 나온다.
Point ReturnSavePoint(LinkedStackType* stack, Point current_p) {
	Point save_p;    // 갈림길 위치 [중간 체크포인트. 이정표]
	int dn;
	//스택에서 가장 최근 갈림길의 위치를 꺼낸다.
	save_p = pop(stack);

	//반대 방향으로 돌아서 진행한다.
	//일단, 왔던 방향의 반대방향으로 한칸 먼저 움직인다.
	
	while (!IsEqual(&current_p, &save_p)) { //갈림길 위치에 도달할 때 까지 반복한다.

		//갔던 길[2]의 값만 따라 온다.
		ChangeBoard(&current_p, -2);//현재위치를 보드판에 표시하고, // 막다른길 : -2
		GetDirection2(&current_p); //현재위치로부터, 왔던 길[2]로 방향을 정하고, [동남서북 순]
		MovePoint(&current_p);     //현재위치로부터 한칸 움직인다.
		current_p.n--;

		gotoxy(28, 23);
		printf("current_p.n : %2d, s->length = %2d", current_p.n, stack->length);
		Sleep(200);
		DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
	}

	//해당 갈림길에서 더 갈곳이 있는지 확인한다.
	dn = GetDirectionNum(&current_p);

	if (dn >= 1) {
		//해당 갈림길에서 더 갈곳이 있다면 스택에 넣고, 알아서 진행하라고 한다.
		push(stack, current_p);
	}

	return current_p;
}

void DFS2() {
	LinkedStackType stack;

	Point current_p; // 현재 위치

	int dn; // 이동 가능한 방향의 수. dn = Direction Number

	// 스택을 초기화한다.
	init(&stack);
	
	// 출발점으로부터 동남서북을 검사[갈 수 있는 방향의 수를 샘]한다.
	dn = GetDirectionNum(&start_p);

	//갈수 있는 방향이 0개인 경우면서 현재 위치가 도착 위치가 아닌경우,
	if (dn == 0 && !IsEqual(&start_p, &end_p)) {
		//미로를 찾을 수가 없다.
		printf("시작부터, 고립된 위치에서 시작했습니다. \n");
		return;
	}

	//시작위치를 현재위치로 삼는다.
	current_p = start_p;      

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
			current_p = ReturnSavePoint(&stack, current_p); // 현재 위치(current_p)에서 갈림길 위치(save_p)까지 돌아 나온다.
		}

		ChangeBoard(&current_p, current_p.n);//현재위치를 보드판에 표시하고, // 현재 위치의 숫자
		GetDirection(&current_p);  //현재위치로부터, 임의로 방향을 정하고, [동남서북 순]
		if (current_p.d != 0) {
			MovePoint(&current_p); //현재위치로부터 한칸 움직인다.
			current_p.n++;

			gotoxy(28, 23);
			printf("current_p.n : %2d, s->length = %2d", current_p.n, stack.length);
			Sleep(200);
			DisplayBoard("■", X_MIN, Y_MIN, X_MAX, Y_MAX, width, height, &current_p);
		}
	}
}


int main() {
	// 시작위치를 정한다.								
	start_p.x = 0; start_p.y = 9; start_p.d = 0, start_p.n = 1; // ---------------------------------------------------- CASE 1, 2, 3
	//start_p.x = 2; start_p.y = 7; start_p.d = 0, start_p.n = 1; // ---------------------------------------------------- CASE 4, 5

	// 종료위치를 정한다.
	end_p.x = 9; end_p.y = 0; end_p.d = 0, end_p.n = 1;    // ---------------------------------------------------- CASE 1, 2, 3
    //end_p.x = 5; end_p.y = 5; end_p.d = 0, end_p.n = 1;  // ---------------------------------------------------- CASE 4, 5

	gotoxy(10, 7);
	printf("DFS2 [실제상황 가정] 미로 탐색 알고리즘 동작");

	DFS2();

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


//처음 화면보여주기
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
	for (i = 0; i<width; i++){
		y = Y_MIN + 1;
		for (j = 0; j<height; j++){
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
