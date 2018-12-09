#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <math.h>
#include "MyHeap.h"
#define MAX_VERTEX 50
#define MAZE_SIZE 10

// 길 : 0, 벽 : -1, 출발지 -10, 목적지 10
// Case1. 구멍이 뚫려있지 않은 경우
int maze[MAZE_SIZE][MAZE_SIZE] = {
{ 0, 0,	0,	0,	-1,	0,	0,	0,	0,	0 },
{ 0, -1,	0,	-1,	-1,	0,	-1,	-1,	0,	-1 },
{ 0, -1,	0,	0,	0,	0,	0,	0,	-1,	0 },
{ 0, -1,	0,	-1,	0,	-1,	-1,	0,	-1,	0 },
{ 0, -1,	-1,	-1,	0,	0,	-1,	-1,	-1,	0 },
{ 0, 0,	0,	-1,	0,	-1,	0,	0,	0,	0 },
{ 0, -1,	0,	-1,	-1,	0,	-1,	0,	-1,	0 },
{ 0, -1,	0,	0,	0,	0,	0,	0,	-1,	0 },
{ 0, -1,	0,	-1,	0,	-1,	0,	-1,	0,	-1 },
{ 0, -1,	0,	-1,	0,	-1,	0,	0,	0,	0 }
};

typedef struct _GraphNode {
	int vertex;
	int weight;
	struct _GraphNode* next;
} GraphNode;

typedef struct _GraphType {
	int n;
	int sour;
	int dest;
	GraphNode* adjList_H[MAX_VERTEX];
	float heuri[MAX_VERTEX];
	//float cost[MAX_VERTEX];
} GraphType;


GraphType* createGraph(GraphType* graph);
void insertVertex(GraphType* graph, int v, int x, int y, int dest_x, int dest_y);
GraphNode* insertEdge(GraphType* graph, int u, int v, int w);
void insertEdgeByside(GraphType* graph, int u, int v, int w);
void deleteVertex(GraphType* graph, int v);
void deleteEndNode(GraphType* graph);
int getDegree(GraphNode* node);
void explorGraph(GraphType* graph);
void createMazeGraph(GraphType* graph);
void print_adjList(GraphType* graph);

GraphType* createGraph(GraphType* graph)
{
	int v;
	graph = (GraphType*)malloc(sizeof(GraphType));
	graph->n = 0, graph->sour = 0, graph->dest = 0;
	for (v = 0; v < MAX_VERTEX; v++) graph->adjList_H[v] = NULL;
	memset(graph->heuri, 0, sizeof(float)*MAX_VERTEX);

	return graph;
}

void insertVertex(GraphType* graph, int v, int x, int y, int dest_x, int dest_y)
{
	if (((graph->n) + 1) > MAX_VERTEX)
	{
		printf("\n 그래프 정점의 개수를 초과했습니다.");
		return NULL;
	}
	graph->heuri[graph->n] = (float) sqrt(pow((dest_x - x), 2) + pow(dest_y - y, 2));
	graph->n++;
}

GraphNode* insertEdge(GraphType* graph, int u, int v, int w)
{
	GraphNode* node;

	if (u >= graph->n || v >= graph->n)
	{
		printf("\n 그래프에 없는 정점입니다.");
		return;
	}
	node = (GraphNode*)malloc(sizeof(GraphNode));
	node->vertex = v;
	node->weight = w;
	node->next = graph->adjList_H[u];
	graph->adjList_H[u] = node;

	return node;
}

void insertEdgeByside(GraphType* graph, int u, int v, int w)
{
	insertEdge(graph, u, v, w);
	insertEdge(graph, v, u, w);
}

void deleteVertex(GraphType* graph, int v)
{
	int i = 0;
	GraphNode* node = NULL;
	GraphNode* prev = NULL;
	for (i = 0; i < MAX_VERTEX; i++)
	{
		node = graph->adjList_H[i];
		if (i == v)
		{
			while (node != NULL)
			{
				prev = node;
				node = node->next;
				free(prev);
			}
			graph->adjList_H[i] = NULL;
		}
		else
		{
			while (node != NULL)
			{
				if (node->vertex == v)
				{
					if (graph->adjList_H[i] == node)
					{
						graph->adjList_H[i] = node->next;
						free(node);
						break;
					}
					else
					{
						prev->next = node->next;
						free(node);
						break;
					}
				}
				prev = node;
				node = node->next;
			}
		}
	}
}

void print_adjList(GraphType* graph)
{
	int i;
	GraphNode* node;
	for (i = 0; i < graph->n; i++)
	{
		printf("\n\t\t정점 %c의 인접 리스트 ", i + 65);
		node = graph->adjList_H[i];
		while (node != NULL)
		{
			printf(" -> %c", node->vertex + 65);
			node = node->next;
		}
	}
}

void createMazeGraph(GraphType* graph)
{
	int i = 0;

	insertVertex(graph, 0, 0, 9, 9, 0);
	insertVertex(graph, 1, 0, 8, 9, 0);
	insertVertex(graph, 2, 0, 3, 9, 0);
	insertVertex(graph, 3, 0, 2, 9, 0);
	insertVertex(graph, 4, 1, 8, 9, 0);
	insertVertex(graph, 5, 2, 9, 9, 0);
	insertVertex(graph, 6, 2, 7, 9, 0);
	insertVertex(graph, 7, 2, 5, 9, 0);
	insertVertex(graph, 8, 2, 4, 9, 0);
	insertVertex(graph, 9, 2, 2, 9, 0);
	insertVertex(graph, 10, 3, 7, 9, 0);
	insertVertex(graph, 11, 3, 2, 9, 0);
	insertVertex(graph, 12, 4, 5, 9, 0);
	insertVertex(graph, 13, 4, 4, 9, 0);
	insertVertex(graph, 14, 5, 9, 9, 0);
	insertVertex(graph, 15, 5, 7, 9, 0);
	insertVertex(graph, 16, 5, 6, 9, 0);
	insertVertex(graph, 17, 5, 4, 9, 0);
	insertVertex(graph, 18, 5, 0, 9, 0);
	insertVertex(graph, 19, 6, 5, 9, 0);
	insertVertex(graph, 20, 7, 9, 9, 0);
	insertVertex(graph, 21, 7, 6, 9, 0);
	insertVertex(graph, 22, 7, 5, 9, 0);
	insertVertex(graph, 23, 7, 4, 9, 0);
	insertVertex(graph, 24, 7, 2, 9, 0);
	insertVertex(graph, 25, 8, 8, 9, 0);
	insertVertex(graph, 26, 9, 9, 9, 0);
	insertVertex(graph, 27, 9, 8, 9, 0);
	insertVertex(graph, 28, 9, 4, 9, 0);
	insertVertex(graph, 29, 9, 2, 9, 0);
	insertVertex(graph, 30, 9, 0, 9, 0);

	graph->sour = 0;
	graph->dest = 30;

	insertEdgeByside(graph, 0, 1, 1);
	insertEdgeByside(graph, 1, 4, 1);
	insertEdgeByside(graph, 1, 7, 5);
	insertEdgeByside(graph, 7, 8, 1);
	insertEdgeByside(graph, 7, 6, 2);
	insertEdgeByside(graph, 6, 10, 1);
	insertEdgeByside(graph, 8, 9, 2);
	insertEdgeByside(graph, 8, 13, 2);
	insertEdgeByside(graph, 13, 12, 1);
	insertEdgeByside(graph, 13, 18, 1);
	insertEdgeByside(graph, 9, 11, 1);
	insertEdgeByside(graph, 9, 3, 2);
	insertEdgeByside(graph, 3, 2, 1);
	insertEdgeByside(graph, 3, 14, 7);
	insertEdgeByside(graph, 14, 30, 4);
	insertEdgeByside(graph, 14, 24, 4);
	insertEdgeByside(graph, 24, 29, 2);
	insertEdgeByside(graph, 24, 23, 2);
	insertEdgeByside(graph, 23, 28, 2);
	insertEdgeByside(graph, 23, 22, 1);
	insertEdgeByside(graph, 22, 19, 1);
	insertEdgeByside(graph, 22, 21, 1);
	insertEdgeByside(graph, 21, 16, 3);
	insertEdgeByside(graph, 21, 27, 4);
	insertEdgeByside(graph, 16, 17, 1);
	insertEdgeByside(graph, 16, 15, 2);
	insertEdgeByside(graph, 15, 5, 3);
	insertEdgeByside(graph, 15, 20, 2);
	insertEdgeByside(graph, 27, 25, 1);
	insertEdgeByside(graph, 27, 26, 1);

	//단말 노드의 경우 휴리스틱 비용을 무한대로 설정.
	for (i = 0; i < graph->n; i++)
	{
		if (getDegree(graph->adjList_H[i]) == 1 && (i != graph->sour && i != graph->dest)) graph->heuri[i] = 100;
	}
}

void createMazeGraph2(GraphType* graph)
{
	int i = 0;
	insertVertex(graph, 0, 0, 9, 9, 0);
	insertVertex(graph, 1, 0, 5, 9, 0);
	insertVertex(graph, 2, 0, 2, 9, 0);
	insertVertex(graph, 3, 2, 7, 9, 0);
	insertVertex(graph, 4, 2, 5, 9, 0);
	insertVertex(graph, 5, 2, 4, 9, 0);
	insertVertex(graph, 6, 2, 2, 9, 0);
	insertVertex(graph, 7, 3, 9, 9, 0);
	insertVertex(graph, 8, 3, 0, 9, 0);
	insertVertex(graph, 9, 4, 5, 9, 0);
	insertVertex(graph, 10, 4, 4, 9, 0);
	insertVertex(graph, 11, 5, 9, 9, 0);
	insertVertex(graph, 12, 5, 7, 9, 0);
	insertVertex(graph, 13, 5, 6, 9, 0);
	insertVertex(graph, 14, 5, 0, 9, 0);
	insertVertex(graph, 15, 7, 6, 9, 0);
	insertVertex(graph, 16, 7, 4, 9, 0);
	insertVertex(graph, 17, 7, 2, 9, 0);
	insertVertex(graph, 18, 7, 0, 9, 0);
	insertVertex(graph, 19, 9, 6, 9, 0);
	insertVertex(graph, 20, 9, 4, 9, 0);
	insertVertex(graph, 21, 9, 0, 9, 0);

	graph->sour = 0;
	graph->dest = 21;

	insertEdgeByside(graph, 0, 7, 3);
	insertEdgeByside(graph, 7, 3, 3);
	insertEdgeByside(graph, 7, 11, 2);
	insertEdgeByside(graph, 3, 1, 4);
	insertEdgeByside(graph, 3, 4, 2);
	insertEdgeByside(graph, 1, 2, 3);
	insertEdgeByside(graph, 1, 4, 2);
	insertEdgeByside(graph, 4, 5, 1);
	insertEdgeByside(graph, 5, 6, 2);
	insertEdgeByside(graph, 5, 10, 2);
	insertEdgeByside(graph, 2, 6, 2);
	insertEdgeByside(graph, 2, 8, 5);
	insertEdgeByside(graph, 6, 8, 3);
	insertEdgeByside(graph, 10, 9, 1);
	insertEdgeByside(graph, 10, 16, 3);
	insertEdgeByside(graph, 8, 14, 2);
	insertEdgeByside(graph, 11, 12, 2);
	insertEdgeByside(graph, 11, 19, 7);
	insertEdgeByside(graph, 12, 13, 1);
	insertEdgeByside(graph, 12, 15, 3);
	insertEdgeByside(graph, 14, 17, 4);
	insertEdgeByside(graph, 14, 18, 2);
	insertEdgeByside(graph, 15, 16, 2);
	insertEdgeByside(graph, 15, 19, 2);
	insertEdgeByside(graph, 16, 17, 2);
	insertEdgeByside(graph, 17, 21, 4);
	insertEdgeByside(graph, 18, 17, 2);
	insertEdgeByside(graph, 18, 21, 2);
	insertEdgeByside(graph, 19, 20, 2);


	//단말 노드의 경우 휴리스틱 비용을 무한대로 설정.
	for (i = 0; i < graph->n; i++)
	{
		if (getDegree(graph->adjList_H[i]) == 1 && (i != graph->sour && i != graph->dest)) graph->heuri[i] = 100;
	}
}

//void convertMazeToGraph(GraphType* graph)
//{
//	int count = 0;
//	int i, j;
//	/*
//	for (i = 1; i < MAZE_SIZE - 1; i++)
//		for (j = 1; j < MAZE_SIZE - 1; j++) maze[i][j] == 1 ? maze[i][j] = 0 : (maze[i][j] = 1);*/
//
//	for (i = 0; i < MAZE_SIZE; i++)
//	{
//		for (j = 0; j < MAZE_SIZE; j++)
//		{
//			if (maze[i][j] == 1)
//			{
//				if (i - 1 >= 0) if(maze[i - 1][j] == 0) count ++ ;
//				if (i + 1 < MAZE_SIZE) if (maze[i + 1][j] == 0) count++;
//				if (j - 1 >= 0) if (maze[i][j - 1] == 0) count++;
//				if (j + 1 < MAZE_SIZE) if (maze[i][j + 1] == 0) count++;
//				if (count != 2)insertVertex_temp(g, i, j, 9, 0);
//				count = 0;
//			}
//		}
//	}
//
//}

void explorGraph(GraphType* graph)
{
	HeapType* heap = createHeap();
	HeapData* data = (HeapData*)malloc(sizeof(HeapData));
	GraphNode* node = NULL;
	int visited[31] = { 0 }; //정점을 방문하면 1로 변경한다. 예를들어 정즘1을 방문하면 vistied[1]=1을 한다.

	//출발지 우선 순위큐에 삽입 key가 비용이고, value가 vertext다.
	insertHeap(heap, graph->heuri[graph->sour], graph->sour);
	visited[0] = 1;
	while (heap->heap_size != 0)
	{
		data = deleteHeap(heap, data);

		printf("\n현재 %c노드 도착\n", data->value + 65);
		if (data->value == graph->dest)
		{
			printf("\n목적지 도착\n");
			return;
		}

		//현재 정점에 인접한 정점을 대상으로 탐색한다.
		node = graph->adjList_H[data->value];
		while (node != NULL)
		{
			//방문한 정점은 방문하지 않는다.
			if(visited[node->vertex]==0)
			{
				//key값 = 이전까지 탐색 비용 + 정점으로 이동하는 비용 + 정점에서 목적지까지 예상비용(휴리스틱), value=정점의 번호를 넣는다.
				insertHeap(heap,  data->key - graph->heuri[data->value] + node->weight + graph->heuri[node->vertex], node->vertex);
				visited[node->vertex]++;
			}
			node = node->next;
		}
		printHeap(heap);
	}
}

void deleteEndNode(GraphType* graph)
{
	int i = 0;
	for (i = 0; i < graph->n; i++)
	{
		if (getDegree(graph->adjList_H[i]) == 1 && (i != graph->sour && i!= graph->dest)) {
			deleteVertex(graph, i);
		}
	}

	return;
}

int getDegree(GraphNode* node)
{
	int count = 0;
	while (node != NULL)
	{
		node = node->next;
		count++;
	}
	return count;
}

int main()
{
	int i;
	GraphType* graph = NULL;
	GraphType* graph2 = NULL;
	//graph = (GraphType*)malloc(sizeof(GraphType));
	graph = createGraph(graph);
	graph2 = createGraph(graph2);

	createMazeGraph(graph);
	printf("\n graph의 인접리스트");
	print_adjList(graph);
	
	printf("\n");
	explorGraph(graph);

	printf("\n graph의 인접리스트");
	deleteEndNode(graph);
	print_adjList(graph);

	printf("\n graph의 인접리스트");
	deleteEndNode(graph);
	print_adjList(graph);

	printf("\n graph의 인접리스트");
	deleteEndNode(graph);
	print_adjList(graph);

	printf("\n");
	explorGraph(graph);

	printf("\n---------------------------------------\n");
	createMazeGraph2(graph2);
	printf("\n graph2의 인접리스트");
	print_adjList(graph2);

	printf("\n");
	explorGraph(graph2);

	printf("\n graph2의 인접리스트");
	deleteEndNode(graph2);
	print_adjList(graph2);

	printf("\n graph2의 인접리스트");
	deleteEndNode(graph2);
	print_adjList(graph2);

	printf("\n graph2의 인접리스트");
	deleteEndNode(graph2);
	print_adjList(graph2);

	printf("\n");
	explorGraph(graph2);
}
