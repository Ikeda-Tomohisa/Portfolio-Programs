//
//oldmaid.c
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void shuffle_deck(int list[],int listsize){
    for(int i = 0; i < listsize; i++){
        int random = rand() % listsize;
        int tmp = list[i];
        list[i] = list[random];
        list[random] = tmp;
    }
}


void match(int **list,int columnsize,int player){
    for(int i = 0; i < columnsize - 1; i++){
        for(int j = i + 1; j < columnsize; j++){
            if(list[player][i] == list[player][j]){
                list[player][i] = 0;
                list[player][j] = 0;
            }
        }
    }
}

void descendingsort(int **list,int columnsize,int player){
    for(int i = 0; i < columnsize - 1; i++){
        for(int j = i + 1; j < columnsize; j++){
            if(list[player][i] < list[player][j]){
                int tmp = list[player][i];
                list[player][i] = list[player][j];
                list[player][j] = tmp;
            }
        }
    }
}

int count(int **list,int columnsize,int player){
    int number = 0;
    for(int i = 0;i < columnsize; i++){
        if(list[player][i] != 0){
            number++;
        }
    }
    return number;
}

void shuffle_hand(int **list,int columnsize,int player){
    for(int i = 0; i < columnsize; i++){
        int random = rand() % columnsize;
        int tmp = list[player][i];
        list[player][i] = list[player][random];
        list[player][random] = tmp;
    }
}

int main(){
    int **hand;
    char **player;
    int *order, *countp;
    int i, j, k, n, min_first_number_of_cards, max_first_number_of_cards, max_number_of_cards;

    printf("人数を入力してください。（3人〜10人）\n");
    do{
        printf("人数 : ");
        scanf("%d",&n);
    }while(n < 3 || n > 10);

    int cardlist[] = {1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12,12,13,13,13,13,14};
    int cardsize = sizeof(cardlist) / sizeof(cardlist[0]);

    min_first_number_of_cards = cardsize / n;
    max_first_number_of_cards = cardsize / n + 1;
    max_number_of_cards = cardsize / n + 2;

    srand((unsigned int)time(NULL));
    shuffle_deck(cardlist,cardsize);

    hand = malloc(sizeof(int *) * n);
    for (i = 0; i < n; i++) {
        hand[i] = malloc(sizeof(int) * max_number_of_cards);
    }

    order = malloc(sizeof(int) * n);
    for(i = 0; i < n; i++){
        order[i] = i;
    }
    //シャッフル前の順番確認用
    /*
    for(i = 0; i < n; i++){
        printf("%d ",order[i]);
    }
    printf("\n");
    */

    shuffle_deck(order,n);

    //シャッフル後の順番確認用
    /*
    for(i = 0; i < n; i++){
        printf("%d ",order[i]);
    }
    printf("\n");
    */

    //player[0]をあなた、[1]からはCOM1...と続く
    player = malloc(sizeof(char *) * n);
    for (i = 0; i < n; i++) {
        player[i] = malloc(sizeof(char) * 12);
    }
    char tmp[12] = {0};
    sprintf(tmp, "%s", "あなた");
    strcpy(player[0],tmp);

    char *com = "COM";
    char tmp1[12] = {0};
    for(i = 1; i < n; i++){
        sprintf(tmp1, "%s%d", com, i);
        strcpy(player[i], tmp1);
    }
    //プレーヤーの名前確認用
    /*
    for(i = 0; i < n; i++){
        printf("%s ",player[i]);
    }
    printf("\n");
    */

    //シャッフル後の山札確認用
    /*
    printf("DECK : ");
    for(i = 0; i < cardsize; i++){
        printf("%d ",cardlist[i]);
    }
    printf("\n");
    */

    //手札を配る
    for(i = 0;i < n; i++){
        for(j = 0; j < min_first_number_of_cards; j++){
            hand[i][j] = cardlist[min_first_number_of_cards * i + j];
        }
    }
    //i = 6, j = 8(n=6)
    for(j = 0;j < cardsize % n; j++){
        hand[j][min_first_number_of_cards] = cardlist[min_first_number_of_cards * i + j];
    }

    //配った初期手札確認用
    /*
    printf("初期Hand\n");
    //int matrixcount = 0;
    for (i = 0; i < n; i++) {
        for (j = 0; j < max_number_of_cards; j++) {
            printf("%d ", hand[i][j]);
            //matrixcount++;
        }
        printf("\n");
    }
    //printf("%d",matrixcount);
    */

    for(i = 0; i < n; i++){
        match(hand,max_first_number_of_cards,i);
    }
    
    //同じ手札を捨てた後の手札確認用
    /*
    printf("match後hand\n");
    for (i = 0; i < n; i++) {
        for (j = 0; j < max_number_of_cards; j++) {
            printf("%d ", hand[i][j]);
        }
        printf("\n");
    }
    */

    for(i = 0; i < n; i++){
        descendingsort(hand,max_first_number_of_cards,i);
    }

    //降順ソートした後の手札確認用
    /*
    printf("降順ソート後hand\n");
    for (i = 0; i < n; i++) {
        for (j = 0; j < max_number_of_cards; j++) {
            printf("%d ", hand[i][j]);
        }
        printf("\n");
    }
    */

    countp = malloc(sizeof(int) * n);
    for(i = 0; i < n; i++){
        countp[i] = count(hand,max_first_number_of_cards,i);
    }

    //手札の枚数確認用
    /*
    printf("handの枚数");
    for(i = 0; i < n; i++){
        printf("%d ",countp[i]);
    }
    printf("\n");
    */

    for(i = 0; i < n; i++){
        shuffle_hand(hand,countp[i],i);
    }

    //シャッフル後の手札確認用
    /*
    printf("shuffle後hand\n");
    for (i = 0; i < n; i++) {
        for (j = 0; j < max_number_of_cards; j++) {
            printf("%d ", hand[i][j]);
        }
        printf("\n");
    }
    */

    printf("%d人でババ抜き\n",n);
    printf("順番をランダムで決定します。\n");
    printf("順番は");
    for(i = 0; i < n - 1; i++){
        printf("%s→",player[order[i]]);
    }
    printf("%sになりました\n",player[order[i]]);
    printf("勝負開始！\n");

    int random_take,take;
    int flag = 0;

    //配列の要素が0のものを除いた初期手札確認用
    /*
    for(i = 0; i < n; i++){
        printf("%s : ",player[order[i]]);
        for(j = 0;j < countp[i]; j++){
            printf("%d ",hand[i][j]);
        }
        printf("\n");
    }
    */

    while(1){
        //最初から0枚勝ち判定
        for(i = 0; i < n; i++){
            if(countp[i] == 0){
                printf("%sの勝ち！\n",player[order[i]]);
                flag = 1;
                break;
            }
        }
        //誰かが勝っていたらbreak終了
        if(flag){
            break;
        }

        for(i = 0; i < n; i++){
            if(order[i%n] != 0){
                printf("%sの番 %sから1枚取りました。\n",player[order[i%n]],player[order[(i+1)%n]]);
                int random_take = rand() % countp[(i+1)%n];
                hand[i%n][countp[i%n]] = hand[(i+1)%n][random_take];
                hand[(i+1)%n][random_take] = 0;

                match(hand,countp[i%n]+1,i%n);

                descendingsort(hand,countp[i%n],i%n);
                descendingsort(hand,countp[(i+1)%n],(i+1)%n);

                countp[i%n] = count(hand,countp[i%n]+1,i%n);
                countp[(i+1)%n] = count(hand,countp[(i+1)%n],(i+1)%n);

                shuffle_hand(hand,countp[i%n],i%n);
                shuffle_hand(hand,countp[(i+1)%n],(i+1)%n);
            }else if(order[i%n] == 0){
                //途中のみんなの手札確認用
                /*
                for(j = 0; j < n; j++){
                    printf("%s : ",player[order[j]]);
                    for(k = 0;k < countp[j%n]+1; k++){
                        if(hand[j%n][k] == 1){
                            printf("A ");
                        }else if(hand[j%n][k] == 11){
                            printf("J ");
                        }else if(hand[j%n][k] == 12){
                            printf("Q ");
                        }else if(hand[j%n][k] == 13){
                            printf("K ");
                        }else if(hand[j%n][k] == 14){
                            printf("Joker ");
                        }else if(hand[j%n][k] == 0){
                            break;
                        }else{
                            printf("%d ",hand[j%n][k]);
                        }
                    }
                    printf("\n");
                }
                */
                /*
                printf("あなたの手札 : ");
                for(j = 0; j < countp[i%n]; j++){
                    if(hand[i%n][j] == 1){
                        printf("A ");
                    }else if(hand[i%n][j] == 11){
                        printf("J ");
                    }else if(hand[i%n][j] == 12){
                        printf("Q ");
                    }else if(hand[i%n][j] == 13){
                        printf("K ");
                    }else if(hand[i%n][j] == 14){
                        printf("Joker ");
                    }else{
                        printf("%d ",hand[i%n][j]);
                    }
                }
                printf("\n");
                */
                printf("%sの番  %sから取る手札を選んでください。\n",player[order[i%n]],player[order[(i+1)%n]]);
                printf("0〜%dを選んでください。\n",countp[(i+1)%n]-1);
                printf("選んだ数字 : ");
                scanf("%d",&take);

                hand[i%n][countp[i%n]] = hand[(i+1)%n][take];
                hand[(i+1)%n][take] = 0;

                match(hand,countp[i%n]+1,i%n);

                descendingsort(hand,countp[i%n],i%n);
                descendingsort(hand,countp[(i+1)%n],(i+1)%n);

                countp[i%n] = count(hand,countp[i%n]+1,i%n);
                countp[(i+1)%n] = count(hand,countp[(i+1)%n],(i+1)%n);

                shuffle_hand(hand,countp[i%n],i%n);
                shuffle_hand(hand,countp[(i+1)%n],(i+1)%n);

                printf("あなたの手札 : ");
                for(j = 0; j < countp[i%n]; j++){
                    if(hand[i%n][j] == 1){
                        printf("A ");
                    }else if(hand[i%n][j] == 11){
                        printf("J ");
                    }else if(hand[i%n][j] == 12){
                        printf("Q ");
                    }else if(hand[i%n][j] == 13){
                        printf("K ");
                    }else if(hand[i%n][j] == 14){
                        printf("Joker ");
                    }else{
                        printf("%d ",hand[i%n][j]);
                    }
                }
                printf("\n");
            }

            if(countp[i%n] == 0){
                printf("%sの勝ち\n",player[order[i%n]]);
                flag = 1;
                if(flag){
                    break;
                }
            }else if(countp[(i+1)%n] == 0){
                printf("%sの勝ち\n",player[order[(i+1)%n]]);
                flag = 1;
                if(flag){
                    break;
                }
            }   
        }
        //勝っていたらbreak終了
        if(flag){
            break;
        }
    }

    //メモリ開放
    for (i=0;i<n;i++) {
        free(hand[i]);
    }
    free(hand);

    free(order);

    for (i=0;i<n;i++) {
        free(player[i]);
    }
    free(player);

    free(countp);

    return 0;
}