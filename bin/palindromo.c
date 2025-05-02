#include <stdio.h>
#include <stdbool.h>

int getlenght(char str[])
{
    int lenght = 0;
    while (str[lenght] != '\0')
        lenght++;
    return lenght;
}

bool isPalind(char str[])
{
    bool palin = true;
    int inicio = 0;
    int fim = getlenght(str) - 1;

    while (inicio < fim)
    {
        if (str[inicio] != str[fim])
        {
            palin = false;
            break;
        }
        else
        {
            inicio++;
            fim--;
        }
    }
    return palin;
}

int main()
{
    while (true)
    {
        char string[50] = "";
        scanf(" %49[^\n]", string); 
        if (string[0] == 'F' && string[1] == 'I' && string[2] == 'M' && string[3] == '\0')
            break;
        if (isPalind(string))
            printf("SIM\n");
        else
            printf("NAO\n");
    }
    return 0;
}
