#Leitura dos valores
capital = input ('Digite seu capital: ')
capitalint = int(capital) #Transforma em int
tempo = input ('Informe o tempo (em anos): ')
tempoint = int(tempo) #Transforma em int
taxa = input ('Digite a taxa em anos: ')
taxaint = float(taxa) #Transforma em int
taxaint /= 100 #Tranforma a taxa em decimal
montante = 0 #Inicializa o montante
montante = montante + capitalint * ((1 + taxaint)** tempoint) #conta dos juros compostos
#Exibição do resultado
print (f'O valor do montante é: {montante:.2f}')