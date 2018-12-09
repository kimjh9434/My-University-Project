#include "avl_tree.h"


int main()
{
	avl_add(&root, 8);
	avl_add(&root, 9);
	avl_add(&root, 10);
	avl_add(&root, 2);
	avl_add(&root, 1);
	avl_add(&root, 5);
	avl_add(&root, 3);
	avl_add(&root, 6);
	avl_add(&root, 4);
	avl_add(&root, 7);
	avl_add(&root, 11);
	avl_add(&root, 12);

	avl_search(root, 12);

	return 0;
}
