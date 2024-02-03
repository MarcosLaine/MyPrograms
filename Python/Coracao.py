import turtle

# Configuração inicial da tela
tela = turtle.Screen()
tela.bgcolor("white")
tela.title("Desenho de Coração e Mensagem")

# Configuração da tartaruga (cursor que desenha)
tartaruga = turtle.Turtle()
tartaruga.speed(3)  # Velocidade do desenho
tartaruga.color("red")
tartaruga.begin_fill()

# Desenho do coração
tartaruga.left(50)
tartaruga.forward(133)
tartaruga.circle(50, 200)
tartaruga.right(140)
tartaruga.circle(50, 200)
tartaruga.forward(133)
tartaruga.end_fill()

# Movendo a tartaruga para uma posição abaixo do coração para escrever o texto
tartaruga.penup()  # Para não desenhar enquanto se move
tartaruga.goto(-100, -120)  # Ajuste as coordenadas conforme necessário para posicionar o texto
tartaruga.color("black")

# Escrevendo o texto
tartaruga.write("Meu amor eu te amo demais\n(foi muito difícil fazer isso)", font=("Arial", 12, "normal"), align="center")

# Esconder a tartaruga após o término do desenho
tartaruga.hideturtle()

# Para manter a janela aberta até que o usuário decida fechá-la
turtle.done()
