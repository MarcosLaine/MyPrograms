
import cv2
import numpy as np

# Cria uma imagem em branco com fundo branco
image = np.ones((400, 400, 3), dtype=np.uint8) * 255

# Desenha um círculo amarelo para a cabeça do pintinho
center = (200, 200)
radius = 100
color = (0, 255, 255)  # Amarelo
thickness = -1  # Preenchido
cv2.circle(image, center, radius, color, thickness)

# Desenha olhos pretos
eye_radius = 15
left_eye_center = (175, 175)
right_eye_center = (225, 175)
eye_color = (0, 0, 0)  # Preto
cv2.circle(image, left_eye_center, eye_radius, eye_color, thickness)
cv2.circle(image, right_eye_center, eye_radius, eye_color, thickness)

# Desenha um triângulo laranja para o bico
points = np.array([[200, 240], [180, 280], [220, 280]])
color = (0, 165, 255)  # Laranja
cv2.drawContours(image, [points], 0, color, thickness)

# Exibe a imagem gerada
cv2.imshow("Pintinho", image)
cv2.waitKey(0)
cv2.destroyAllWindows()
