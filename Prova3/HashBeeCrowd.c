#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int key;
    struct Node* next;
} Node;

Node* createNode(int key) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->key = key;
    newNode->next = NULL;
    return newNode;
}

void insert(Node** hashTable, int index, int key) {
    Node* newNode = createNode(key);
    if (hashTable[index] == NULL) {
        hashTable[index] = newNode;
    } else {
        Node* temp = hashTable[index];
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = newNode;
    }
}

void printHashTable(Node** hashTable, int M) {
    for (int i = 0; i < M; i++) {
        printf("%d -> ", i);
        Node* temp = hashTable[i];
        while (temp != NULL) {
            printf("%d -> ", temp->key);
            temp = temp->next;
        }
        printf("\\\n");
    }
}

void freeHashTable(Node** hashTable, int M) {
    for (int i = 0; i < M; i++) {
        Node* temp = hashTable[i];
        while (temp != NULL) {
            Node* toFree = temp;
            temp = temp->next;
            free(toFree);
        }
    }
}

int main() {
    int N;
    scanf("%d", &N);

    for (int i = 0; i < N; i++) {
        int M, C;
        scanf("%d %d", &M, &C);
        Node** hashTable = (Node**)malloc(M * sizeof(Node*));

        for (int j = 0; j < M; j++) {
            hashTable[j] = NULL;
        }

        for (int j = 0; j < C; j++) {
            int key;
            scanf("%d", &key);
            int hashIndex = key % M;
            insert(hashTable, hashIndex, key);
        }

        printHashTable(hashTable, M);

        if (i < N - 1) {
            printf("\n");
        }

        freeHashTable(hashTable, M);
        free(hashTable);
    }

    return 0;
}
