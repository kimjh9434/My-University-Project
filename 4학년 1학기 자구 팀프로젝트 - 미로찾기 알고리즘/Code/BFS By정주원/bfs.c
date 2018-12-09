#include "function.h"

int bfs(int maze[SIZE][SIZE], QueueType** qPointer) {
	Location* now = NULL;
	int i, j;
	
	for (i = 0; i < SIZE; i++) {
		for (j = 0; j < SIZE; j++) {
			if (maze[i][j] == -2) {
				now = init_loc(j, i);
			}
		}
	}

	while (maze[now->r][now->c] != -3) {
		system("cls");

		maze[now->r][now->c] = 1;

		enqueue(qPointer, init_loc(now->c - 1, now->r), maze);
		enqueue(qPointer, init_loc(now->c, now->r - 1), maze);
		enqueue(qPointer, init_loc(now->c + 1, now->r), maze);
		enqueue(qPointer, init_loc(now->c, now->r + 1), maze);

		print_queue(*qPointer);
		print_maze(maze, now);

		if (is_empty(*qPointer)) {
			printf("Fail!\n");
			return -1;
		}

		now = dequeue(qPointer);
		Sleep(1000);
	}

	if (maze[now->r][now->c] == -3) {
		system("cls");

		maze[now->r][now->c] = 1;

		print_queue(*qPointer);
		print_maze(maze, now);

		gotoxy(0, 10);
		printf("Searched\n");
	}

	return 0;
}