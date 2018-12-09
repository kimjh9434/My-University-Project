#pragma once
#include <stdio.h>
#include <malloc.h>
#include <memory.h>
#define MAX_ELEMENT 100

typedef struct _HeapData {
	float key;
	int value;
} HeapData;

// ������ ���� 1���� �迭�� ���� ������ ������ ����ü�� ��� ����
typedef struct _HeapType {
	HeapData heap[MAX_ELEMENT];
	int heap_size;
} HeapType;


HeapType* createHeap();
void insertHeap(HeapType *heap, float key, int value);
HeapData* deleteHeap(HeapType *heap, HeapData* data);
void printHeap(HeapType *heap);


// ���� ������ �����ϴ� ����
HeapType* createHeap() {
	HeapType *heap = (HeapType*)malloc(sizeof(HeapType));
	heap->heap_size = 0;
	return heap;
}

// ������ item�� �����ϴ� ����
void insertHeap(HeapType *heap, float key, int value) {
	int i;
	heap->heap_size = heap->heap_size + 1;
	i = heap->heap_size;
	while ((i != 1) && (key < heap->heap[i / 2].key)) {
		heap->heap[i] = heap->heap[i / 2];
		i /= 2;
	}
	heap->heap[i].key = key;
	heap->heap[i].value = value;
}

// ������ ��Ʈ�� �����Ͽ� ��ȯ�ϴ� ����
HeapData* deleteHeap(HeapType *heap, HeapData* data) {
	int parent, child;
	float key, value, temp_key, temp_value;
	key = heap->heap[1].key;
	value = heap->heap[1].value;

	temp_key = heap->heap[heap->heap_size].key;
	temp_value = heap->heap[heap->heap_size].value;

	heap->heap_size = heap->heap_size - 1;
	parent = 1;
	child = 2;
	while (child <= heap->heap_size) {
		if ((child < heap->heap_size) && (heap->heap[child].key) > heap->heap[child + 1].key)
			child++;
		if (temp_key <= heap->heap[child].key) break;
		else {
			heap->heap[parent] = heap->heap[child];
			parent = child;
			child = child * 2;
		}
	}
	heap->heap[parent].key = temp_key;
	heap->heap[parent].value = temp_value;

	data->key = key;
	data->value = value;

	return data;
}

// 1���� �迭 ������ ������ ����ϴ� ����
void printHeap(HeapType *heap) {
	int i;
	printf("Heap : ");
	for (i = 1; i <= heap->heap_size; i++)
		printf("[%.2f, %d] ", heap->heap[i].key, heap->heap[i].value);
}
