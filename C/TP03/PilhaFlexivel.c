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

void read(Player *p, char *phrase) {}

void print(Player player, int pos) {
  int height = atoi(player.height);
  int weight = atoi(player.weight);
  int birthYear = atoi(player.birthYear);

  printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", pos,
         player.name, height, weight, birthYear, player.university,
         player.birthCity, player.birthState);
}

char *processPhrase(char *phrase) {
  char data[] = "nao informado";
  char *newPhrase = malloc(sizeof(char) * 200);
  if (newPhrase == NULL) {
    return NULL;
  }

  strcpy(newPhrase, "");

  for (int i = 0; phrase[i] != '\0'; i++) {
    if (phrase[i] == ',' && phrase[i + 1] == ',') {
      strcat(newPhrase, ",");
      strcat(newPhrase, data);
    } else if (phrase[i] == ',' && phrase[i + 2] == '\0') {
      strcat(newPhrase, ",");
      strcat(newPhrase, data);
    } else {
      char temp[2] = {phrase[i], '\0'};
      strcat(newPhrase, temp);
    }
  }

  return newPhrase;
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

void split(const char *str, char delimiter, char tokens[8][100]) {
  int line = 0;
  int index = 0;
  while (line < 8) {
    int i = 0;
    while (1) {
      if (str[index] == delimiter || str[index] == '\0' || str[index] == '\n') {
        tokens[line][i] = '\0';
        break;
      }

      tokens[line][i] = str[index];
      i++;
      index++;
    }

    index++;
    line++;
  }
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

void removePlayer(Stack *stack) {
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
  char input[600];

  Player players[3922];

  FILE *file = fopen("/tmp/players.csv", "r");

  fgets(input, sizeof(input), file);

  for (int i = 0; fgets(input, 600, file) != NULL; i++) {
    char *phrase = processPhrase(input);

    char tokens[8][100];
    split(phrase, ',', tokens);
    free(phrase);

    addPlayer(&players[i], tokens);
  }

  Stack *stack = malloc(sizeof(Stack));
  initStack(stack);

  for (int i = 0; 1; i++) {
    char entry[100];
    scanf("%s", entry);
    if (strcmp(entry, "FIM") == 0) {
      break;
    }
    int id = atoi(entry);
    push(players[id], stack);
  }

  Node *temp = stack->top;
  Player array[2000];
  int count = 0;

  while (temp != NULL) {
    array[count] = temp->value;
    temp = temp->next;
    count++;
  }

  for (int i = count - 1; i >= 0; i--) {
    print(array[count - i - 1], i);
  }

  fclose(file);
  return 0;
}
