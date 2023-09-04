import pygame
import random

BOARD_WIDTH = 10
BOARD_HEIGHT = 10
NUM_MINES = 10
TILE_SIZE = 30


def draw_board(board, revealed):
    for x in range(BOARD_WIDTH):
        for y in range(BOARD_HEIGHT):
            rect = pygame.Rect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE)
            if revealed[x][y]:
                if board[x][y][2]:
                    pygame.draw.rect(screen, (255, 0, 0), rect)
                else:
                    pygame.draw.rect(screen, (255, 255, 255), rect)
                    num_mines = count_mines(board, x, y)
                    if num_mines > 0:
                        font = pygame.font.SysFont(None, TILE_SIZE)
                        text = font.render(str(num_mines), True, (0, 0, 0))
                        text_rect = text.get_rect(center=rect.center)
                        screen.blit(text, text_rect)
            else:
                pygame.draw.rect(screen, (128, 128, 128), rect)


def count_mines(board, x, y):
    count = 0
    for i in range(-1, 2):
        for j in range(-1, 2):
            if i == 0 and j == 0:
                continue
            if x + i < 0 or x + i >= BOARD_WIDTH:
                continue
            if y + j < 0 or y + j >= BOARD_HEIGHT:
                continue
            if board[x + i][y + j][2]:
                count += 1
    return count


def reveal_neighbors(board, revealed, x, y):
    for i in range(-1, 2):
        for j in range(-1, 2):
            if i == 0 and j == 0:
                continue
            if x + i < 0 or x + i >= BOARD_WIDTH:
                continue
            if y + j < 0 or y + j >= BOARD_HEIGHT:
                continue
            if not revealed[x + i][y + j]:
                revealed[x + i][y + j] = True
                if count_mines(board, x + i, y + j) == 0:
                    reveal_neighbors(board, revealed, x + i, y + j)


board = []
for x in range(BOARD_WIDTH):
    row = []
    for y in range(BOARD_HEIGHT):
        tile = (x, y, False)
        row.append(tile)
    board.append(row)

mines_placed = 0
while mines_placed < NUM_MINES:
    x = random.randint(0, BOARD_WIDTH - 1)
    y = random.randint(0, BOARD_HEIGHT - 1)
    if not board[x][y][2]:
        tile = (x, y, True)
        board[x][y] = tile
        mines_placed += 1

revealed = []
for x in range(BOARD_WIDTH):
    row = []
    for y in range(BOARD_HEIGHT):
        row.append(False)
    revealed.append(row)

pygame.init()
SCREEN_WIDTH = BOARD_WIDTH * TILE_SIZE
SCREEN_HEIGHT = BOARD_HEIGHT * TILE_SIZE
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))

running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.MOUSEBUTTONDOWN:
            (x, y) = pygame.mouse.get_pos()
            tile_x = x // TILE_SIZE
            tile_y = y // TILE_SIZE
            if not revealed[tile_x][tile_y]:
                if board[tile_x][tile_y][2]:
                    running = False
                else:
                    revealed[tile_x][tile_y] = True
                    if count_mines(board, tile_x, tile_y) == 0:
                        reveal_neighbors(board, revealed, tile_x, tile_y)

    draw_board(board, revealed)

    pygame.display.flip()

if running:
    print("You win!")
else:
    print("Game over.")
    draw_board(board, [[True] * BOARD_HEIGHT] * BOARD_WIDTH)
    pygame.display.flip()
    pygame.time.wait(1000)
    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

pygame.quit()