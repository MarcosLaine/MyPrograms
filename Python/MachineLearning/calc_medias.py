import re

suportes = [0.455, 0.455, 0.636, 0.636, 0.455, 0.545, 0.455, 0.455, 0.636, 0.636, 0.364, 0.364, 0.455, 0.455, 0.455, 0.364, 0.364]
lifts = [1.146, 1.146, 1.203, 1.203, 1.146, 1.179, 1.571, 1.571, 1.203, 1.203, 1.100, 1.100, 1.146, 1.375, 1.375, 1.760, 1.760]
confiancas = [0.833, 0.833, 0.875, 0.875, 0.833, 0.857, 1.000, 1.000, 0.875, 0.875, 0.800, 0.800, 0.833, 1.000, 1.000, 0.800, 0.800]

print(f'Média dos Suportes: {sum(suportes)/len(suportes):.3f}')
print(f'Média dos Lifts: {sum(lifts)/len(lifts):.3f}')
print(f'Média das Confianças: {sum(confiancas)/len(confiancas):.3f}') 