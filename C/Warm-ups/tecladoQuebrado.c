/*#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
typedef struct Node {
    char data;
    struct Node* next;
} Node;

Node* createNode(char data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

void insertAtEnd(Node** head, char data) {
    Node* newNode = createNode(data);
    if (*head == NULL) {
        *head = newNode;
    } else {
        Node* temp = *head;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = newNode;
    }
}

void insertAtBeginning(Node** head, char data) {
    Node* newNode = createNode(data);
    newNode->next = *head;
    *head = newNode;
}

void printList(Node* head) {
    Node* temp = head;
    while (temp != NULL) {
        printf("%c", temp->data);
        temp = temp->next;
    }
    printf("\n");
}

void freeList(Node** head) {
    Node* temp = *head;
    while (temp != NULL) {
        Node* next = temp->next;
        free(temp);
        temp = next;
    }
    *head = NULL;
}

int main() {
    char ch;
    Node* head = NULL;
    Node** current = &head;

    while ((ch = getchar()) != EOF) {
        if (ch == '\n') {
            printList(head);
            freeList(&head);
            current = &head;
        } else if (ch == '[') {
            current = &head;
        } else if (ch == ']') {
            while (*current != NULL) {
                current = &((*current)->next);
            }
        } else {
            insertAtEnd(current, ch);
            current = &((*current)->next);
        }
    }

    return 0;
}*/

/*void getSubsting(int pos, int len, int c, char string[])
{
    char substring[10000];

    while (c < len)
    {
        substring = string[pos + c - 1];
        c++;
    }

    return 0;
}

int main()
{
    while (!EOF)
    {
        char string[10000], substrini[10000], substrfim[10000];
        for(int i = 0; i < strlen(string); i++){
            scanf("%s", &string[i]);
            if (string[i] == '[')
            {
                substrini[i] = string[i];
            }
            else if (string[i] == ']')
            {
                
            }
        }
    }
    return 0;
}
*/

#include <stdio.h>
#include <string.h>

int main() {
    char input[100001]; // Aumentei o tamanho para 100001 para comportar até 100000 caracteres + o caractere nulo.

    while (scanf("%s", input) != EOF) {
        char result[100001] = ""; // String para armazenar o resultado final.
        char temp[100001] = "";   // String temporária para armazenar partes do texto.
        int temp_len = 0;         // Comprimento da string temporária.

        for (int i = 0; input[i] != '\0'; i++) {
            if (input[i] == '[') {
                // Insere a string temporária no início do resultado.
                temp[temp_len] = '\0'; // Finaliza a string temporária.
                strcat(temp, result);  // Concatena o resultado ao final da string temporária.
                strcpy(result, temp);  // Copia a string temporária para o resultado.
                temp_len = 0;          // Reinicia o comprimento da string temporária.
            } else if (input[i] == ']') {
                // Insere a string temporária no final do resultado.
                temp[temp_len] = '\0'; // Finaliza a string temporária.
                strcat(result, temp);  // Concatena a string temporária ao final do resultado.
                temp_len = 0;          // Reinicia o comprimento da string temporária.
            } else {
                // Adiciona o caractere atual à string temporária.
                temp[temp_len++] = input[i];
            }
        }

        // Adiciona qualquer texto restante na string temporária ao final do resultado.
        temp[temp_len] = '\0'; // Finaliza a string temporária.
        strcat(result, temp);  // Concatena a string temporária ao final do resultado.

        printf("%s\n", result); // Imprime o resultado final.
    }

    return 0;
}
