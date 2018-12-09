#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <time.h>

#define	SIZE 9

typedef struct location {
	int c;	// x
	int r;	// y
} Location;

typedef struct node {
	Location* loc;	// item¿Ã location¿Œ ≈•
	struct node* next;
} QueueNode;

typedef struct queue {
	QueueNode* front;
	QueueNode* rear;
} QueueType;

Location* init_loc(int x, int y);
void gotoxy(int x, int y);
void print_maze(int maze[SIZE][SIZE], Location* now);

int is_empty(QueueType* q);
QueueType* q_init();
void enqueue(QueueType** pQ, Location* l, int maze[SIZE][SIZE]);
Location* dequeue(QueueType** pQ);
void print_queue(QueueType* q);

int bfs(int maze[SIZE][SIZE], QueueType** qPointer);