#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <math.h>

typedef struct Player {
  int id;
  char name[100];
  char height[100];
  char weight[100];
  char university[100];
  char birthYear[100];
  char birthCity[100];
  char birthState[100];
} Player;

Player copy(Player *player) {
  Player newPlayer = *player;
  return newPlayer;
}

void read(Player *p, char *line) {
  // Implemente a função ler (caso necessário) conforme a lógica do seu programa.
}

void print(Player player, int position) {
  int height = atoi(player.height);
  int weight = atoi(player.weight);
  int birthYear = atoi(player.birthYear);

  printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", position,
         player.name, height, weight, birthYear, player.university,
         player.birthCity, player.birthState);
}

char *processLine(char *line) {
  char data[] = "nao informado";
  char *newLine = malloc(sizeof(char) * 200);
  if (newLine == NULL) {
    return NULL;
  }

  strcpy(newLine, "");

  for (int i = 0; line[i] != '\0'; i++) {
    if (line[i] == ',' && line[i + 1] == ',') {
      strcat(newLine, ",");
      strcat(newLine, data);
    } else if (line[i] == ',' && line[i + 2] == '\0') {
      strcat(newLine, ",");
      strcat(newLine, data);
    } else {
      char temp[2] = {line[i], '\0'};
      strcat(newLine, temp);
    }
  }

  return newLine;
}

void addPlayer(Player *player, char tokens[8][100]) {
  player->id = atoi(tokens[0]);
  strcpy(player->name, tokens[1]);
  strcpy(player->height, tokens[2]);
  strcpy(player->weight, tokens[3]);
  strcpy(player->university, tokens[4]);
  strcpy(player->birthYear, tokens[5]);
  strcpy(player->birthCity, tokens[6]);
  strcpy(player->birthState, tokens[7]);
}

void splitLine(const char *line, char delimiter, char tokens[8][100]) {
  int row = 0;
  int index = 0;
  while (row < 8) {
    int i = 0;
    while (1) {
      if (line[index] == delimiter || line[index] == '\0' || line[index] == '\n') {
        tokens[row][i] = '\0';
        break;
      }
      tokens[row][i] = line[index];
      i++;
      index++;
    }
    index++;
    row++;
  }
}

void swap(int *a, int *b) {
  int temp = *a;
  *a = *b;
  *b = temp;
}

///////////////////////// Pilha Ligada /////////////////////////

typedef struct Node {
  Player value;
  struct Node *next;
} Node;

typedef struct Stack {
  Node *top;
} Stack;

void initStack(Stack *stack) {
  stack->top = NULL;
}

void removeTop(Stack *stack) {
  if (stack->top == NULL) {
    return;
  }
  printf("(R) %s\n", stack->top->value.name);
  Node *temp = stack->top;
  stack->top = stack->top->next;
  free(temp);
}

void push(Player x, Stack *stack) {
  Node *newNode = malloc(sizeof(Node));
  newNode->value = x;
  newNode->next = stack->top;
  stack->top = newNode;
}

int main() {
  char line[600];

  Player team[3922];

  FILE *file = fopen("/tmp/players.csv", "r");

  fgets(line, sizeof(line), file);

  for (int i = 0; fgets(line, 600, file) != NULL; i++) {
    char *processedLine = processLine(line);

    char data[8][100];
    splitLine(processedLine, ',', data);
    free(processedLine);

    addPlayer(&team[i], data);
  }

  // Pilha circular aqui
  Stack *stack = malloc(sizeof(Stack));
  initStack(stack);

  for (int i = 0; 1; i++) {
    char input[100];
    scanf("%s", input);
    if (strcmp(input, "FIM") == 0) {
      break;
    }
    int id = atoi(input);
    push(team[id], stack);
    // Calcular média aqui (função calcularmedia não está definida no código)
  }

  int quantity;
  scanf("%d", &quantity);

  for (int i = 0; i < quantity; i++) {
    char input[10];
    scanf("%s", input);

    if (strcmp(input, "I") == 0) {
      int id;
      scanf("%d", &id);
      push(team[id], stack);
      // Calcular média aqui (função calcularmedia não está definida no código)
    } else if (strcmp(input, "R") == 0) {
      removeTop(stack);
    }
  }

  int count = 0;
  Node *temp = stack->top;
  while (temp != NULL) {
    print(temp->value, count);
    count++;
    temp = temp->next;
  }

  fclose(file);
  return 0;
}
