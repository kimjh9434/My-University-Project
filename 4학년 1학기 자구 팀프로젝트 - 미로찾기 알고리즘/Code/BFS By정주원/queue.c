#include "function.h"

int is_empty(QueueType* q) {
	return (q->front == NULL);
}

QueueType* q_init() {
	QueueType* q = (QueueType*)malloc(sizeof(QueueType));
	if (q == NULL) {
		printf("Allocation Error\n");
		exit(1);
	}
	q->front = (QueueNode*)malloc(sizeof(QueueNode));
	q->rear = (QueueNode*)malloc(sizeof(QueueNode));

	q->front = q->rear = NULL;
	return q;
}

void enqueue(QueueType** pQ, Location* l, int maze[SIZE][SIZE]) {
	QueueNode* newQ = (QueueNode*)malloc(sizeof(QueueNode));
	newQ->loc = l;
	newQ->next = NULL;

	if (l->r < 0 || l->c < 0 || l->r > SIZE, l->c > SIZE) {
		free(newQ);
		return;	// 미로판 밖
	}

	if (maze[l->r][l->c] == -1 || maze[l->r][l->c] == 1) {
		free(newQ);
		return;	// 접근할 수 없는 공간
	}

	if (is_empty(*pQ)) {
		(*pQ)->front = (*pQ)->rear = newQ;
		return;
	}


	(*pQ)->rear->next = newQ;
	(*pQ)->rear = newQ;
}

Location* dequeue(QueueType** pQ) {
	Location* temp;
	if (is_empty(*pQ)) {
		printf("큐가 비었습니다.\n");
		exit(1);
	}
	temp = (*pQ)->front->loc;

	(*pQ)->front = (*pQ)->front->next;
	return temp;
}

void print_queue(QueueType* q) {
	QueueNode* check = q->front;

	gotoxy(0, 23);
	printf("FRONT ");
	while (check) {
		printf("(%d, %d) ", check->loc->c, check->loc->r);
		if (check->next)
			check = check->next;
		else break;
	}
	printf("REAR");
}