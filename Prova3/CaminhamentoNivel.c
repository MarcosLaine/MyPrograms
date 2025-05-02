#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *left, *right;
} Node;

Node* newNode(int data) {
    Node* node = (Node*)malloc(sizeof(Node));
    node->data = data;
    node->left = node->right = NULL;
    return node;
}

Node* insert(Node* node, int data) {
    if (node == NULL) return newNode(data);

    if (data < node->data)
        node->left = insert(node->left, data);
    else if (data > node->data)
        node->right = insert(node->right, data);

    return node;
}

void printLevelOrder(Node* root) {
    if (root == NULL) return;

    int h = height(root);
    for (int i = 1; i <= h; i++)
        printGivenLevel(root, i);
}

int height(Node* node) {
    if (node == NULL) return 0;
    else {
        int lheight = height(node->left);
        int rheight = height(node->right);

        if (lheight > rheight)
            return(lheight + 1);
        else return(rheight + 1);
    }
}

void printGivenLevel(Node* root, int level) {
    if (root == NULL) return;
    if (level == 1)
        printf("%d ", root->data);
    else if (level > 1) {
        printGivenLevel(root->left, level - 1);
        printGivenLevel(root->right, level - 1);
    }
}

int main() {
    int C;
    scanf("%d", &C);
    for (int i = 1; i <= C; i++) {
        int N;
        scanf("%d", &N);
        Node* root = NULL;
        for (int j = 0; j < N; j++) {
            int data;
            scanf("%d", &data);
            root = insert(root, data);
        }
        printf("Case %d: ", i);
        printLevelOrder(root);
        printf("\n");
    }
    return 0;
}
