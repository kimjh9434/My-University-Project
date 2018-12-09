#include "function.h"

int maze[SIZE][SIZE] = {
	{-1, -1, -1, -1, -1, -1, -1, -1, -1},
	{-2,  0, -1,  0,  0,  0,  0,  0, -1},
	{-1,  0, -1,  0, -1, -1,  0, -1, -1},
	{-1,  0,  0,  0,  0, -1,  0,  0, -1},
	{-1,  0, -1, -1, -1, -1, -1,  0, -1},
	{-1,  0,  0, -1,  0,  0,  0,  0, -1},
	{-1,  0, -1, -1,  0, -1, -1,  0, -3},
	{-1, -1, -1,  0,  0,  0, -1,  0, -1},
	{-1, -1, -1, -1, -1, -1, -1, -1, -1}
};
// -3 : Exit
// -2 : Entrance
// -1 : Wall
//  0 : Road
//  1 : Visited Road

void main(void) {
	QueueType* path = q_init();

	// 커서 지우기
	HANDLE hConsole;
	CONSOLE_CURSOR_INFO ConsoleCursor;
	hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	ConsoleCursor.bVisible = 0;
	ConsoleCursor.dwSize = 1;
	SetConsoleCursorInfo(hConsole, &ConsoleCursor);

	print_maze(maze, NULL);

	bfs(maze, &path);
}