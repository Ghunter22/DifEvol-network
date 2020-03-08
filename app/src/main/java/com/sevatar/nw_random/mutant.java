package com.sevatar.nw_random;

public class mutant {


    public  mutant (vectr v1, vectr v2, vectr v3,double fi1){

        fi = fi1;
        for ( int i = 0; i < 3; i++) {
            for(int j = 0; j <3; j++) {
                w[i][j] = v1.w[i][j] + fi * (v2.w[i][j] - v3.w[i][j]); // Возможно формула мутации слишком слабая
                //rand = (Math.random()*(max-min) ) + min;

               // w[i][j] = rand;
                // пробные формулы
               // w[i][j] =  (((Math.random()*(v1.w[i][j]-v2.w[i][j]) ) + v2.w[i][j] ) -v3.w[i][j])*fi ;
               // w[i][j] = (v1.w[i][j] +v2.w[i][j] - v3.w[i][j]) / fi;
              //  w[i][j] = (v1.w[i][j] + v2.w[i][j] + v3.w[i][j])*fi;
            }
       }


        for ( int i = 0; i < 4; i++) {
            for(int j = 0; j <3; j++) {
                e[i][j] = v1.e[i][j] + fi * (v2.e[i][j] - v3.e[i][j]);

              //  rand = (Math.random()*(max-min) ) + min;

               // e[i][j] = rand;

               // e[i][j] = (v1.e[i][j] + v2.e[i][j] + v3.e[i][j])*fi;
              //  e[i][j] =  (((Math.random()*(v1.e[i][j]-v2.e[i][j]) ) + v2.e[i][j] ) -v3.e[i][j])*fi ;
               // e[i][j] = (v1.e[i][j] + v2.e[i][j] - v3.e[i][j]) / fi;
            }
        }

        for ( int i = 0; i < 4; i++) {
            for(int j = 0; j <3; j++) {
                r[i][j] = v1.r[i][j] + fi * (v2.r[i][j] - v3.r[i][j]);


              //  rand = (Math.random()*(max-min) ) + min;

                //r[i][j] = rand;

               // r[i][j] =  (((Math.random()*(v1.r[i][j]-v2.r[i][j]) ) + v2.r[i][j] ) -v3.r[i][j])*fi ;
            }
        }


        for ( int i = 0; i < 4; i++) {
            for(int j = 0; j <2; j++) {
                 t[i][j] = v1.t[i][j] + fi * (v2.t[i][j] - v3.t[i][j]);



                //rand = (Math.random()*(max-min) ) + min;

             //   t[i][j] = rand;


                // r[i][j] =  (((Math.random()*(v1.r[i][j]-v2.r[i][j]) ) + v2.r[i][j] ) -v3.r[i][j])*fi ;
            }
        }

    }

    Double w [][] =  new Double[3][3];            //МАССИВЫ ВЕСОВЫХ КОЭФФИЦИЕНТОВ // новая схема
    Double e [][]  = new Double[4][3];
    Double r [][]  = new Double[4][3];
    Double t [][]  = new Double[4][2];

    double fi;
    //Double rand = 0.0;
}
