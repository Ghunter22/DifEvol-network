package com.sevatar.nw_random;

public class fin_vec {           //этот класс содержит в себе готовые подобранные весовые коэффициенты
   public fin_vec(end_vectr v){   //принимает в себя вектор, с лучшим значением

       y1 = v.y1;
       y2 = v.y2;

       for ( int i = 0; i < 3; i++) {
           for(int j = 0; j <2; j++) {
               w[i][j] =  v.w[i][j];

           }
       }


       for ( int i = 0; i < 3; i++) {
           for(int j = 0; j <3; j++) {

               e[i][j] =  v.e[i][j];

           }
       }

       for ( int i = 0; i < 4; i++) {
           for(int j = 0; j <2; j++) {

               r[i][j] =  v.r[i][j];

           }
       }


    }

    Double w [][] =  new Double[3][2];            //МАССИВЫ ВЕСОВЫХ КОЭФФИЦИЕНТОВ
    Double e [][]  = new Double[3][3];
    Double r [][]  = new Double[4][2];
    Double y1,y2;
}
