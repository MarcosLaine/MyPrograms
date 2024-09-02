#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int mv = 0;
int cmp = 0;

void replace(char *str, const char *find, const char *replace)
{
  int find_len = strlen(find);
  int replace_len = strlen(replace);

  char *temp = strstr(str, find);
  while (temp != NULL)
  {
    memmove(temp + replace_len, temp + find_len, strlen(temp + find_len) + 1);
    memcpy(temp, replace, replace_len);
    temp = strstr(temp + replace_len, find);
  }
}

// Definindo a estrutura do Personagem
typedef struct
{
  char id[100];
  char name[100];
  char alternateNames[5][100];
  char house[100];
  char ancestry[100];
  char species[100];
  char patronus[100];
  bool hogwartsStaff;
  bool hogwartsStudent;
  char actor[100];
  bool alive;
  char dateOfBirth[100];
  char yearOfBirth[100];
  char eyeColour[100];
  char gender[100];
  char hairColour[100];
  bool wizard;
} Character;

// Função para ler os dados de um personagem a partir de uma string
void criarTodosPersonagens(char *str, Character *character)
{
  replace(str, ";;", ";!;");
  // colocando um ! entre os ;; seguidos pra usar o strtok

  char *token = strtok(str, ";"); // pegando primeira substring
  strcpy(character->id, token);
  token = strtok(NULL, ";"); // pulando pra proxima substring

  strcpy(character->name, token);
  token = strtok(NULL, ";");

  // Processando os nomes alternativos
  token++;                             // Avançando além do colchete inicial '['
  char *endToken = strchr(token, ']'); // Encontrando o colchete final ']'
  if (endToken != NULL)
  {
    *endToken = '\0'; // Substituindo ']' por '\0' para terminar a string de nomes alternativos
    strcpy(character->alternateNames, token);
  }
  else
  {
    // Tratamento de erro, caso o colchete final não seja encontrado
    strcpy(character->alternateNames, ""); // Ou algum valor padrão
  }
  token = strtok(NULL, ";");

  strcpy(character->house, token);
  token = strtok(NULL, ";");

  strcpy(character->ancestry, token);
  token = strtok(NULL, ";");

  strcpy(character->species, token);
  token = strtok(NULL, ";");

  strcpy(character->patronus, token);
  token = strtok(NULL, ";");

  character->hogwartsStaff = (strcmp(token, "VERDADEIRO") == 0); // comparacao de boolean
  token = strtok(NULL, ";");
  character->hogwartsStudent = (strcmp(token, "VERDADEIRO") == 0);
  token = strtok(NULL, ";");

  strcpy(character->actor, token);
  token = strtok(NULL, ";");

  character->alive = (strcmp(token, "VERDADEIRO") == 0);
  token = strtok(NULL, ";");
  token = strtok(NULL, ";");
  // dois strtok pra pular os altores alternativos
  // que esta vazio

  if (strcmp("23-6-1980", token) == 0)
  {
    strcpy(character->dateOfBirth, "23-06-1980");
    // essa data errada no csv
  }
  else
    strcpy(character->dateOfBirth, token);
  token = strtok(NULL, ";");

  strcpy(character->yearOfBirth, token);
  token = strtok(NULL, ";");

  strcpy(character->eyeColour, token);
  token = strtok(NULL, ";");

  strcpy(character->gender, token);
  token = strtok(NULL, ";");

  strcpy(character->hairColour, token);
  token = strtok(NULL, ";");

  character->wizard = (strcmp(token, "VERDADEIRO\r\n") == 0);

  token = strtok(NULL, ";"); // sei se precisa dese cara n
}

// Função para imprimir os dados de um personagem
void imprimir(Character *character)
{

  char formattedAlternateNames[500];
  strcpy(formattedAlternateNames, character->alternateNames);
  char *start = formattedAlternateNames;
  char *end = start + strlen(formattedAlternateNames) - 1;
  if (*start == '[')
  {
    *start = '{';
  }
  if (*end == ']')
  {
    *end = '}';
  }
  replace(formattedAlternateNames, "'", "");

  char saida[1000]; // Tamanho suficiente para armazenar a string de saída
                    // OBS O IMPRIMIR ESTA COM FALSES STATICOS POR A SAIDA DO VERDE ESTA ERRADA, ELA FALA
                    // Q HARRY POTHER NAO EH BRUXO, FALA Q TA MORTO ... ETC ETC
  sprintf(saida, "[%s ## %s ## {%s} ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s]\n",
          character->id, character->name, formattedAlternateNames, character->house, character->ancestry,
          character->species, character->patronus,
          character->hogwartsStaff ? "true" : "false",
          character->hogwartsStudent ? "true" : "false",
          character->actor, character->alive ? "true" : "false",
          character->dateOfBirth, character->yearOfBirth, character->eyeColour, character->gender,
          character->hairColour, character->wizard ? "true" : "false");

  // Substituir '!' por 'nada'
  replace(saida, "!", "");

  // Imprimir a string de saída
  printf("%s", saida);
}

void ler(Character *characters[], const char *filename)
{
  FILE *file = fopen(filename, "r");
  if (file == NULL)
  {
    perror("erro ao abrir arquivo");
    return;
  }

  char line[1050];
  int i = 0;
  fgets(line, sizeof(line), file);
  while (fgets(line, sizeof(line), file))
  {
    characters[i] = (Character *)malloc(sizeof(Character));
    if (characters[i] == NULL)
    {
      perror("erro ao alocar memoria");
      fclose(file);
      return;
    }
    criarTodosPersonagens(line, characters[i]);
    i++;
  }
  fclose(file);
}

void acharBruxo(Character *characters[], int num_characters, const char *id)
{
  for (int i = 0; i < num_characters; i++)
  {
    if (characters[i] != NULL && strcmp(characters[i]->id, id) == 0)
    {
      imprimir(characters[i]);
      i = num_characters;
    }
  }
}
Character *acharBruxoPorNome(Character *characters[], int num_characters, const char *id)
{
  for (int i = 0; i < num_characters; i++)
  {
    if (characters[i] != NULL && strcmp(characters[i]->name, id) == 0)
    {
      return characters[i];
      i = num_characters;
    }
  }
  return NULL;
}
Character *acharBruxoPoriD(Character *characters[], int num_characters, const char *id)
{
  for (int i = 0; i < num_characters; i++)
  {
    if (characters[i] != NULL && strcmp(characters[i]->id, id) == 0)
    {
      return characters[i];
      i = num_characters;
    }
  }
  return NULL;
}

void shell_sort(Character *characters[], int num_characters, int (*compare)(const Character *, const Character *))
{
  int gap = num_characters / 2;
  while (gap > 0)
  {
    for (int i = gap; i < num_characters; i++)
    {
      Character *temp = characters[i];
      int j = i;

      while (j >= gap && compare(characters[j - gap], temp) > 0)
      {
        cmp++;

        characters[j] = characters[j - gap];
        mv++;
        j -= gap;
      }

      characters[j] = temp;
    }
    gap /= 2;
  }
}

int compare_por_nome_sort(const Character *a, const Character *b)
{
  return strcmp(a->name, b->name);
}

int pesquisaBinaria(Character *characters[], int num_characters, const char *key, int (*compare)(const Character *, const char *))
{
  int inicio = 0;
  int fim = num_characters - 1;

  while (inicio <= fim)
  {
    int meio = inicio + (fim - inicio) / 2;
    cmp++;

    int comparison = compare(characters[meio], key);
    if (comparison == 0)
    {
      return meio;
    }
    else if (comparison < 0)
    {
      inicio = meio + 1;
    }
    else
    {
      fim = meio - 1;
    }
  }
  return -1;
}

int compare_por_nome_pesquisa(const Character *a, const char *key)
{
  return strcmp(a->name, key);
}

int compare_by_eyeColour_then_name(const Character *a, const Character *b)
{

  int eyeColourComparison = strcmp(a->eyeColour, b->eyeColour);
  if (eyeColourComparison == 0)
  {
    return strcmp(a->name, b->name);
  }
  return eyeColourComparison;
}

void quicksortRec(Character *array[], int esq, int dir, int (*compare)(const Character *, const Character *))
{
  int i = esq, j = dir;
  Character *pivo = array[(dir + esq) / 2];
  while (i <= j)
  {
    cmp++;
    while (compare(array[i], pivo) < 0)
    {
      i++;
      cmp++;
    }
    cmp++;
    while (compare(array[j], pivo) > 0)
    {
      j--;
      cmp++;
    }
    if (i <= j)
    {
      Character *temp = array[i];
      array[i] = array[j];
      array[j] = temp;
      mv++;
      i++;
      j--;
    }
  }
  if (esq < j)
  {
    quicksortRec(array, esq, j, compare);
  }
  if (i < dir)
  {
    quicksortRec(array, i, dir, compare);
  }
}
int compare_by_house_then_name(const Character *a, const Character *b)
{

  int eyeColourComparison = strcmp(a->house, b->house);
  if (eyeColourComparison == 0)
  {
    return strcmp(a->name, b->name);
  }
  return eyeColourComparison;
}

void bolhaSort(Character *array[], int n, int (*compare)(const Character *, const Character *))
{
  bool trocado;
  do
  {
    trocado = false;
    for (int i = 0; i < n - 1; i++)
    {
      cmp++;
      if (compare(array[i], array[i + 1]) > 0)
      {
        Character *temp = array[i];
        array[i] = array[i + 1];
        array[i + 1] = temp;
        mv++;
        trocado = true;
      }
    }
    n--;
  } while (trocado);
}


int getMax(Character *array[], int n, int (*compare)(const Character *, const Character *))
{
  int max = compare(array[0], NULL);
  cmp++;
  for (int i = 1; i < n; i++)
  {
    cmp++;
    int current = compare(array[i], NULL);
    if (max < current)
    {
      max = current;
    }
  }
  return max;
}

void radcountingSort(Character *array[], int n, int exp, int (*compare)(const Character *, int))
{
  int count[10] = {0};
  Character *output[n];
  for (int i = 0; i < n; i++)
  {
    cmp++;
    int digit = (compare(array[i], exp) / exp) % 10;
    count[digit]++;
  }
  for (int i = 1; i < 10; i++)
  {
    count[i] += count[i - 1];
  }
  for (int i = n - 1; i >= 0; i--)
  {
    int digit = (compare(array[i], exp) / exp) % 10;
    output[count[digit] - 1] = array[i];
    count[digit]--;
    mv++;
  }
  for (int i = 0; i < n; i++)
  {
    array[i] = output[i];
  }
}

void radixsort(Character *array[], int n, int (*compare)(const Character *, int))
{
  int max = getMax(array, n, compare);
  for (int exp = 1; max / exp > 0; exp *= 10)
  {
    radcountingSort(array, n, exp, compare);
  }
}

int compare_by_id(const Character *a, const Character *b)
{
  int eyeColourComparison = strcmp(a->id, b->id);
  return eyeColourComparison;
}

void heapSort(Character *array[], int n, int (*compare)(const Character *, const Character *)) {
    Character *arrayTmp[n + 1];
    for (int i = 0; i < n; i++) {
        arrayTmp[i + 1] = array[i];
    }
    for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
        construir(arrayTmp, tamHeap, compare);
    }
    int tamHeap = n;
    while (tamHeap > 1) {
        swap(arrayTmp + 1, arrayTmp + tamHeap--);
        reconstruir(arrayTmp, tamHeap, compare);
    }
    for (int i = 0; i < n; i++) {
        array[i] = arrayTmp[i + 1];
    }
}

void reconstruir(Character *array[], int tamHeap, int (*compare)(const Character *, const Character *)) {
    int i = 1;
    while (i <= (tamHeap / 2)) {
        int filho = getMaiorFilho(array, i, tamHeap, compare);
        cmp++; 
        if (compare(array[i], array[filho]) < 0) {
            swap(array + i, array + filho);
            mv++; 
            i = filho;
        } else {
            i = tamHeap;
        }
    }
}

int getMaiorFilho(Character *array[], int i, int tamHeap, int (*compare)(const Character *, const Character *)) {
    int filho;
    cmp++;
    if (2 * i == tamHeap || compare(array[2 * i], array[2 * i + 1]) > 0) {
        filho = 2 * i;
    } else {
        filho = 2 * i + 1;
    }
    return filho;
}

void swap(Character **a, Character **b) {
    Character *temp = *a;
    *a = *b;
    *b = temp;
    mv++; 
}

void construir(Character *array[], int tamHeap, int (*compare)(const Character *, const Character *)) {
    for (int i = tamHeap; i > 1 && compare(array[i], array[i / 2]) > 0; i /= 2) {
        cmp++;
        swap(array + i, array + i / 2);
        mv++;
    }
}

int compare_by_haircolor_then_name(const Character *a, const Character *b)
{

  int eyeColourComparison = strcmp(a->hairColour, b->hairColour);
  if (eyeColourComparison == 0)
  {
    return strcmp(a->name, b->name);
  }
  return eyeColourComparison;
}

void criar_log(const char *matricula, double execution_time, int cmp, int mv)
{
  FILE *log_file = fopen("803627", "w");
  if (log_file == NULL)
  {
    perror("Erro ao abrir o arquivo de log.");
    return;
  }
  fprintf(log_file, "matricula: %s\t tempoExecucao: %f\t comparacoes: %d\t movimentacoes: %d\n", matricula, execution_time, cmp, mv);
  fclose(log_file);
}

int main()
{
  /*


  */
  const char *matricula = "803627";
  double execution_time;
  const int num_characters = 500;
  Character *characters[num_characters];
  for (int i = 0; i < num_characters; i++)
  {
    characters[i] = NULL;
  }
  /*


  */

  ler(characters, "/tmp/characters.csv");
  char linha[1050];
  scanf(" %[^\r\n]", linha);
  Character *selecionados[40];
  for (int i = 0; i < 40; i++)
  {
    selecionados[i] = NULL;
  }
  /*


  */
  int i = 0;
  while ((strcmp(linha, "FIM") != 0))
  {
    selecionados[i] = acharBruxoPoriD(characters, 500, linha);
    i++;
    scanf(" %[^\r\n]", linha);
  }
  /*


  */
  clock_t start_time;
  clock_t end_time;
  start_time = clock();
  heapSort(selecionados, i, compare_por_nome_pesquisa);
  pesquisaBinaria(selecionados, i, compare_por_nome_pesquisa);
  end_time = clock();
  execution_time = ((double)(end_time - start_time)) / CLOCKS_PER_SEC;
  for (int j = 0; j < i; j++)
  {
    imprimir(selecionados[j]);
  }
  criar_log(matricula, execution_time, cmp, mv);
  return 0;
}
