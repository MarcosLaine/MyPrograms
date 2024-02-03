#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

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

Player duplicate(Player *player) {
  Player newPlayer = *player;
  return newPlayer;
}

void readPlayer(Player *p, char *line) {}

void displayPlayer(Player player, int position) {
  int height = atoi(player.height);
  int weight = atoi(player.weight);
  int birthYear = atoi(player.birthYear);

  printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", position,
         player.name, height, weight, birthYear, player.university,
         player.birthCity, player.birthState);
}

char *processLine(char *line) {
  char placeholder[] = "not informed";
  char *newLine = malloc(sizeof(char) * 200);
  if (newLine == NULL) {
    return NULL;
  }

  strcpy(newLine, "");

  for (int i = 0; line[i] != '\0'; i++) {
    if (line[i] == ',' && line[i + 1] == ',') {
      strcat(newLine, ",");
      strcat(newLine, placeholder);
    } else if (line[i] == ',' && line[i + 2] == '\0') {
      strcat(newLine, ",");
      strcat(newLine, placeholder);
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

void tokenize(const char *str, char delimiter, char tokens[8][100]) {
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

// AVL Tree
typedef struct TreeNode {
  Player *player;
  struct TreeNode *left;
  struct TreeNode *right;
} TreeNode;

TreeNode *createNode(Player *x) {
  TreeNode *node = malloc(sizeof(TreeNode));
  node->player = x;
  node->left = NULL;
  node->right = NULL;
  return node;
}

typedef struct AVLTree {
  TreeNode *root;
} AVLTree;

TreeNode *balanceLeft(TreeNode *root) {
  if (getHeight(root->right->left) > getHeight(root->right->right)) {
    root->right = balanceRight(root->right);
  }
  TreeNode *temp = root->right;
  root->right = temp->left;
  temp->left = root;
  return temp;
}

TreeNode *balanceRight(TreeNode *root) {
  if (getHeight(root->left->right) > getHeight(root->left->left)) {
    root->left = balanceLeft(root->left);
  }
  TreeNode *temp = root->left;
  root->left = temp->right;
  temp->right = root;
  return temp;
}

int getHeight(TreeNode *root) {
  if (root == NULL) {
    return 0;
  }
  int heightRight = (root->right) ? getHeight(root->right) : 0;
  int heightLeft = (root->left) ? getHeight(root->left) : 0;
  return (heightLeft > heightRight) ? heightLeft + 1 : heightRight + 1;
}

TreeNode *balance(TreeNode *root) {
  int difference = getHeight(root->right) - getHeight(root->left);
  if (difference > 1) {
    root = balanceLeft(root);
  } else if (difference < -1) {
    root = balanceRight(root);
  }
  return root;
}

void balanceFromTopToBottom(TreeNode *root){
  if(root == NULL){
    return;
  }
  root = balance(root);
  balanceFromTopToBottom(root->left);
  balanceFromTopToBottom(root->right);
}

TreeNode *insertIntoTree(TreeNode *root, Player *player) {
  if (root == NULL) {
    root = createNode(player);
  } else if (strcmp(player->name, root->player->name) < 0) {
    root->left = insertIntoTree(root->left, player);
    root = balance(root);
  } else {
    root->right = insertIntoTree(root->right, player);
    root = balance(root);
  }
  return root;
}

void insertIntoAVL(AVLTree *tree, Player player) {
  Player *newPlayer = (Player *)malloc(sizeof(Player));
  *newPlayer = player;
  tree->root = insertIntoTree(tree->root, newPlayer);
}

void searchInTree(TreeNode *root, char *name) {
  if (root == NULL) {
    printf(" NO\n");
    return;
  } else if (strcmp(name, root->player->name) == 0) {
    printf(" YES\n");
  } else if (strcmp(name, root->player->name) < 0) {
    printf(" left");
    searchInTree(root->left, name);
  } else {
    printf(" right");
    searchInTree(root->right, name);
  }
}

void searchInAVL(AVLTree *tree, char *name) {
  printf("%s root", name);
  searchInTree(tree->root, name);
}

void freeTreeNode(TreeNode *node) {
    if (node == NULL) {
        return;
    }
    freeTreeNode(node->left);
    freeTreeNode(node->right);
    free(node->player);
    free(node);
}

void freeAVLTree(AVLTree *tree) {
    if (tree == NULL) {
        return;
    }
    freeTreeNode(tree->root);
    free(tree);
}

int main() {
  char lineRead[600];
  Player team[3922];
  FILE *file = fopen("/tmp/players.csv", "r");

  if (file == NULL) {
    perror("Error opening file");
    return -1;
  }

  fgets(lineRead, sizeof(lineRead), file);

  int i = 0;
  while (fgets(lineRead, 600, file) != NULL && i < 3922) {
    char *processedLine = processLine(lineRead);
    char tokens[8][100];
    tokenize(processedLine, ',', tokens);
    free(processedLine);
    addPlayer(&team[i], tokens);
    i++;
  }

  fclose(file);

  AVLTree *tree = malloc(sizeof(AVLTree));
  initializeAVL(tree);

  char buffer[400];
  while (scanf(" %[^\n]", buffer) != EOF) {
    if (strcmp(buffer, "END") == 0) {
      break;
    } else {
      int id = atoi(buffer);
      if (id >= 0 && id < 3922) {
        insertIntoAVL(tree, team[id]);
      }
    }
  }

  balanceFromTopToBottom(tree->root);

  while (scanf(" %[^\n]", buffer) != EOF) {
    if (strcmp(buffer, "END") == 0) {
      break;
    } else {
      searchInAVL(tree, buffer);
    }
  }

  freeAVLTree(tree);

  return 0;
}
