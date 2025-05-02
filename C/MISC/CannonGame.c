#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <windows.h>
#include <time.h>

#define WIDTH 30
#define HEIGHT 20
#define CANNON '^'
#define STONE 'O'
#define EMPTY ' '
#define BORDER_HORIZONTAL '-'
#define BORDER_VERTICAL '|'
#define BORDER_CORNER '+'
#define PROJECTILE '|'
#define FOREGROUND_WHITE 15

typedef struct {
    int x;
    int y;
    int active;
} Projectile;

char screen[HEIGHT][WIDTH];
int cannonPos = WIDTH / 2;
int running = 1;
int score = 0;
Projectile projectiles[10];

void initializeScreen() {
    for (int i = 0; i < HEIGHT; i++) {
        for (int j = 0; j < WIDTH; j++) {
            if (i == 0 || i == HEIGHT - 1) {
                if (j == 0 || j == WIDTH - 1) {
                    screen[i][j] = BORDER_CORNER;
                } else {
                    screen[i][j] = BORDER_HORIZONTAL;
                }
            } else if (j == 0 || j == WIDTH - 1) {
                screen[i][j] = BORDER_VERTICAL;
            } else {
                screen[i][j] = EMPTY;
            }
        }
    }
}

void initializeProjectiles() {
    for (int i = 0; i < 10; i++) {
        projectiles[i].active = 0;
    }
}

void updateStones() {
    for (int i = HEIGHT - 3; i > 0; i--) {
        for (int j = 1; j < WIDTH - 1; j++) {
            if (screen[i][j] == STONE) {
                screen[i][j] = EMPTY;
                if (i + 1 < HEIGHT - 1) {
                    screen[i + 1][j] = STONE;
                }
            }
        }
    }

    for (int j = 1; j < WIDTH - 1; j++) {
        if (rand() % 30 == 0) { // Diminui a frequência de spawn das pedras
            screen[1][j] = STONE;
        }
    }
}

void updateProjectiles() {
    for (int i = 0; i < 10; i++) {
        if (projectiles[i].active) {
            if (projectiles[i].y > 1) {
                screen[projectiles[i].y][projectiles[i].x] = EMPTY;
                projectiles[i].y--;
                if (screen[projectiles[i].y][projectiles[i].x] == STONE) {
                    screen[projectiles[i].y][projectiles[i].x] = EMPTY;
                    projectiles[i].active = 0;
                    score += 10;
                } else {
                    screen[projectiles[i].y][projectiles[i].x] = PROJECTILE;
                }
            } else {
                projectiles[i].active = 0;
                screen[projectiles[i].y][projectiles[i].x] = EMPTY;
            }
        }
    }
}

void shootProjectile() {
    for (int i = 0; i < 10; i++) {
        if (!projectiles[i].active) {
            projectiles[i].x = cannonPos;
            projectiles[i].y = HEIGHT - 3;
            projectiles[i].active = 1;
            break;
        }
    }
}

void updateScreen() {
    for (int j = 1; j < WIDTH - 1; j++) {
        screen[HEIGHT - 2][j] = EMPTY;
    }

    screen[HEIGHT - 2][cannonPos] = CANNON;
}

void setColor(int color) {
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), color);
}

void printScreen() {
    // Mover o cursor para o canto superior esquerdo da tela
    COORD coord = {0, 0};
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);

    for (int i = 0; i < HEIGHT; i++) {
        for (int j = 0; j < WIDTH; j++) {
            if (screen[i][j] == CANNON) {
                setColor(FOREGROUND_GREEN | FOREGROUND_INTENSITY);
            } else if (screen[i][j] == STONE) {
                setColor(FOREGROUND_RED | FOREGROUND_INTENSITY);
            } else if (screen[i][j] == PROJECTILE) {
                setColor(FOREGROUND_BLUE | FOREGROUND_INTENSITY);
            } else if (screen[i][j] == BORDER_HORIZONTAL || screen[i][j] == BORDER_VERTICAL || screen[i][j] == BORDER_CORNER) {
                setColor(FOREGROUND_WHITE | FOREGROUND_INTENSITY);
            } else {
                setColor(0);
            }
            putchar(screen[i][j]);
        }
        putchar('\n');
    }
    setColor(FOREGROUND_WHITE | FOREGROUND_INTENSITY);
    printf("Score: %d\n", score);
}

void processInput() {
    if (_kbhit()) {
        char key = _getch();
        if (key == 'a' && cannonPos > 1) {
            cannonPos--;
        } else if (key == 'd' && cannonPos < WIDTH - 2) {
            cannonPos++;
        } else if (key == 'w') {
            shootProjectile();
        }
    }
}

void checkCollisions() {
    if (screen[HEIGHT - 2][cannonPos] == STONE) {
        running = 0;
    }
}

int main() {
    srand(time(NULL));

    // Ocultar o cursor
    HANDLE consoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_CURSOR_INFO cursorInfo;
    GetConsoleCursorInfo(consoleHandle, &cursorInfo);
    cursorInfo.bVisible = FALSE;
    SetConsoleCursorInfo(consoleHandle, &cursorInfo);

    initializeScreen();
    initializeProjectiles();

    while (running) {
        updateStones();
        updateProjectiles();
        updateScreen();
        printScreen();
        processInput();
        checkCollisions();
        Sleep(30);
    }

    // Mostrar o cursor novamente
    cursorInfo.bVisible = TRUE;
    SetConsoleCursorInfo(consoleHandle, &cursorInfo);

    printf("Game Over! Score: %d\n", score);

    return 0;
}