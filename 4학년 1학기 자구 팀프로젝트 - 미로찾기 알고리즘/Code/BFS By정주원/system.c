#include "function.h"

Location* init_loc(int x, int y) {
	Location* l = (Location*)malloc(sizeof(Location));
	l->c = x;
	l->r = y;
	return l;
}

void gotoxy(int x, int y) {
	COORD Pos = { x, y };	// 0부터 시작
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), Pos);
}

void print_maze(int maze[SIZE][SIZE], Location* now) {
	int i, j;

	for (i = 0; i < SIZE; i++) {
		for (j = 0; j < SIZE; j++) {
			gotoxy(2 * j, i);
			
			switch (maze[i][j]) {
			case -3:
				printf("▷");	// Exit
				break;
			case -2:
				printf("▶");	// Entrance
				break;
			case -1:
				printf("■");	// Wall
				break;
			case 0:
				printf("□");	// Road
			 	break;
			case 1:
				printf("☆");	// Visited Road
				break;
			}
		}

		if (now != NULL) {
			gotoxy(2 * now->c, now->r);
			printf("★");	// Current Location
		}	// 지난 길과 구분되어 보이도록...
	}
}