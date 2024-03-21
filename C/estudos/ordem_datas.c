#include <stdio.h>

int main() {
    int d1, m1, a1, d2, m2, a2, d3, m3, a3;
    printf("Digite a primeira data (dia, mês, ano): ");
    scanf("%d %d %d", &d1, &m1, &a1);
    printf("Digite a segunda data (dia, mês, ano): ");
    scanf("%d %d %d", &d2, &m2, &a2);
    printf("Digite a terceira data (dia, mês, ano): ");
    scanf("%d %d %d", &d3, &m3, &a3);

    if (a1 < a2 || (a1 == a2 && (m1 < m2 || (m1 == m2 && d1 < d2)))) {
        if (a1 < a3 || (a1 == a3 && (m1 < m3 || (m1 == m3 && d1 < d3)))) {
            printf("%02d/%02d/%d\n", d1, m1, a1);
            if (a2 < a3 || (a2 == a3 && (m2 < m3 || (m2 == m3 && d2 < d3)))) {
                printf("%02d/%02d/%d\n", d2, m2, a2);
                printf("%02d/%02d/%d\n", d3, m3, a3);
            } else {
                printf("%02d/%02d/%d\n", d3, m3, a3);
                printf("%02d/%02d/%d\n", d2, m2, a2);
            }
        } else {
            printf("%02d/%02d/%d\n", d3, m3, a3);
            printf("%02d/%02d/%d\n", d1, m1, a1);
            printf("%02d/%02d/%d\n", d2, m2, a2);
        }
    } else {
        if (a2 < a3 || (a2 == a3 && (m2 < m3 || (m2 == m3 && d2 < d3)))) {
            printf("%02d/%02d/%d\n", d2, m2, a2);
            if (a1 < a3 || (a1 == a3 && (m1 < m3 || (m1 == m3 && d1 < d3)))) {
                printf("%02d/%02d/%d\n", d1, m1, a1);
                printf("%02d/%02d/%d\n", d3, m3, a3);
            } else {
                printf("%02d/%02d/%d\n", d3, m3, a3);
                printf("%02d/%02d/%d\n", d1, m1, a1);
            }
        } else {
            printf("%02d/%02d/%d\n", d3, m3, a3);
            printf("%02d/%02d/%d\n", d2, m2, a2);
            printf("%02d/%02d/%d\n", d1, m1, a1);
        }
    }

    return 0;
}
