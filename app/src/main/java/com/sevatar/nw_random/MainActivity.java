package com.sevatar.nw_random;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {        //какие параметры можно ввести: ALPHA, Fi, EPOHA, EPS - если сумма ошибок ниже EPS, то расчет окончен, CT - количество векторов,
    // интервал случайных коэффициентов min max


    public String dir = "storage/emulated/0/Download/";
    public String f_name = "emp";
    ImageView img, img2, img3;
    ArrayList<String> str1 = new ArrayList<String>();
    ArrayList<Double> x1 = new ArrayList<Double>();
    ArrayList<Double> x2 = new ArrayList<Double>();
    ArrayList<Double> y1 = new ArrayList<Double>();           //набор векторов, состоящих из весовых коэффициентах - поколение
    ArrayList<Double> y2 = new ArrayList<Double>();

    Double w[][];
    Double e[][];
    Double r[][];
    Double t[][];
    Double Min_Err = 999999999999999.0, MMR = 9999999999999.0;
   // Double Max_Err = 0.0;
    Double[] err; // массив, в который складываются ошибки финальных векторов
    ProgressBar pr;
    Integer ct;  //количество векторов
    vectr[] vec;
    String ttt = "Преждевременный рассчет";
    boolean ggg = false;
    end_vectr[] end;      // новое поколение векторов
    TextView txt1 ,txt2,txt3;
    EditText ep,al,c,mi,ma,f,es,p1;
    Double rand,fi;
    Integer epoha;

    Double alpha,min,max;
    Double MaxX1, MaxX2, MaxY1, MaxY2, MinX1, MinX2, MinY1, MinY2,eps, max_delta = 0.00000001;
    Double x_1[],x_2[],y_1[],y_2[];
    end_vectr fin_vectr;
    /// объявление тестовой выборки
    Double a_x1 = 1.5;
    Double a_x2 = 1.0;
    Double a_y1 = 1.25;
    Double a_y2 = -0.5;
   // Integer num_vec = 410;
    // группа б
    Integer p;
    Double b_x1 = 6.0;
    Double b_x2 = 5.0;
    Double b_y1 = 31.0;
    Double b_y2 = 19.0;

    // группа с
    Double c_x1 = 18.0;
    Double c_x2 = 17.0;
    Double c_y1 = 307.0;
    Double c_y2 = 271.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isStoragePermissionGranted();

        img = (ImageView)findViewById(R.id.imageView);
        img2 = (ImageView)findViewById(R.id.imageView2);
        img3 = (ImageView)findViewById(R.id.imageView3);
        txt3 = (TextView) findViewById(R.id.textView3);
        pr = (ProgressBar) findViewById(R.id.progressBar);
        pr.setVisibility(View.GONE);

        f = (EditText) findViewById(R.id.fi2);
        es = (EditText) findViewById(R.id.eps2);
        mi = (EditText) findViewById(R.id.min2);
        ma = (EditText) findViewById(R.id.max2);
        c = (EditText) findViewById(R.id.ct2);
        al = (EditText) findViewById(R.id.alpha2);
        ep = (EditText) findViewById(R.id.epoha2);
        p1 = (EditText) findViewById(R.id.p1);

        img3.setVisibility(View.INVISIBLE);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // старт активности с графиком

                // создание объекта Intent для запуска SecondActivity
                Intent intent = new Intent(MainActivity.this, graph.class);

                intent.putExtra(end_vectr.class.getSimpleName(), fin_vectr);

                intent.putExtra("MinX1", MinX1);
                intent.putExtra("MinX2", MinX2);
                intent.putExtra("MaxX1", MaxX1);
                intent.putExtra("MaxX2", MaxX2);

                intent.putExtra("MinY1", MinY1);
                intent.putExtra("MinY2", MinY2);
                intent.putExtra("MaxY1", MaxY1);
                intent.putExtra("MaxY2", MaxY2);

                startActivity(intent);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {          //считываем и нормализуем входные значения
                loadComplete();


//               double ghh = (Math.pow(2,5));
//                               Toast.makeText(MainActivity.this,
//                            "E= " +Math.E,Toast.LENGTH_SHORT).show(); /// тут все считыввания данных

                pr.setVisibility(View.VISIBLE);
//                if(str1.size() != 0)
//                    Toast.makeText(MainActivity.this,
//                            "Строка 1 = " +(str1.get(0)),Toast.LENGTH_SHORT).show(); /// тут все считыввания данных
            }
        });
                 //вход // выход





        w = new Double[3][3];            //МАССИВЫ ВЕСОВЫХ КОЭФФИЦИЕНТОВ
        e = new Double[4][3];
        r = new Double[4][3];
        t = new Double[4][2];






        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                   //расчитываем значения
                //  crt();
                get_info();
                   start();


            }
        });



        try(FileWriter writer = new FileWriter(dir + "answers.txt", false))
        {
            // запись всей строк
            writer.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }

    public void loadComplete() {

        // textView.setText(String.valueOf(pageNumber));

        try {
            FileInputStream fstream = new FileInputStream(dir + f_name + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                str1.add(strLine);               //тут надо считывать в массив

            }
            br.close();
            fstream.close();
        } catch (IOException e11) {
            e11.printStackTrace();
        }
        String swap, swap2;
        Integer index;
        Integer i = 0;
//                            Toast.makeText(MainActivity.this,
//                            "Size = " +str1.size(),Toast.LENGTH_SHORT).show();
        for (i = 0; i < str1.size(); i++) {
            index = (str1.get(i)).indexOf("\t");
            swap = str1.get(i).substring(0, index);
            x1.add(Double.valueOf(swap));
            swap = str1.get(i).substring(index + 1);
            index = swap.indexOf("\t");
            x2.add(Double.valueOf(swap.substring(0, index)));
            swap2 = swap.substring(index + 1);
            index = swap2.indexOf("\t");
            y1.add(Double.valueOf(swap2.substring(0, index)));
            swap = swap2.substring(index + 1);
            y2.add(Double.valueOf(swap));

        }

//


        MaxX1 = x1.get(0);
        MinX1 = x1.get(0);

        MaxX2 = x2.get(0);
        MinX2 = x2.get(0);

        for (i = 0; i < x1.size(); i++) {   //максимальное X1
            if (x1.get(i) > MaxX1) {
                MaxX1 = x1.get(i);
            }
        }

        for (i = 0; i < x1.size(); i++) { //мин х1
            if (x1.get(i) < MinX1) {
                MinX1 = x1.get(i);
            }

        }

        for (i = 0; i < x2.size(); i++) {   //максимальное X2
            if (x2.get(i) > MaxX2) {
                MaxX2 = x2.get(i);
            }
        }

        for (i = 0; i < x2.size(); i++) { //мин х2
            if (x2.get(i) < MinX2) {
                MinX2 = x2.get(i);
            }

        }


        ////////////////////////////////////////////////

        MaxY1 = y1.get(0);
        MinY1 = y1.get(0);

        MaxY2 = y2.get(0);
        MinY2 = y2.get(0);

        for (i = 0; i < y1.size(); i++) {   //максимальное y1
            if (y1.get(i) > MaxY1) {
                MaxY1 = y1.get(i);
            }
        }

        for (i = 0; i < y1.size(); i++) { //мин y1
            if (y1.get(i) < MinY1) {
                MinY1 = y1.get(i);
            }

        }

        for (i = 0; i < y2.size(); i++) {   //максимальное y2
            if (y2.get(i) > MaxY2) {
                MaxY2 = y2.get(i);
            }
        }

        for (i = 0; i < y2.size(); i++) { //мин y2
            if (y2.get(i) < MinY2) {
                MinY2 = y2.get(i);
            }

        }



//                                    Toast.makeText(MainActivity.this,
//                                            "min x1 = " + MinX1+ "  maxX1= " + MaxX1 + "  MinY1= " + MinY1 +" MaxY1=  " + MaxY1,
//                                            Toast.LENGTH_SHORT).show();
        x_1 = new Double[x1.size()];
        x_2 = new Double[x2.size()];
        y_1 = new Double[y1.size()];
        y_2 = new Double[y2.size()];


     for(i = 0; i < x1.size(); i++) {

         x_1[i] = (x1.get(i) - MinX1) / (MaxX1 - MinX1);
         x_2[i] = (x2.get(i) - MinX2) / (MaxX2 - MinX2);
         y_1[i] = (y1.get(i) - MinY1) / (MaxY1 - MinY1);
         y_2[i] = (y2.get(i) - MinY2) / (MaxY2 - MinY2);


     }

        a_x1 =(a_x1 - MinX1) / (MaxX1 - MinX1);
        a_x2 =(a_x2 - MinX2) / (MaxX2 - MinX2);
        a_y1 =(a_y1 - MinY1) / (MaxY1 - MinY1);
        a_y2 =(a_y2 - MinY2) / (MaxY2 - MinY2);


        b_x1 =(b_x1 - MinX1) / (MaxX1 - MinX1);
        b_x2 =(b_x2 - MinX2) / (MaxX2 - MinX2);
        b_y1 =(b_y1 - MinY1) / (MaxY1 - MinY1);
        b_y2 =(b_y2 - MinY2) / (MaxY2 - MinY2);


        c_x1 =(c_x1 - MinX1) / (MaxX1 - MinX1);
        c_x2 =(c_x2 - MinX2) / (MaxX2 - MinX2);
        c_y1 =(c_y1 - MinY1) / (MaxY1 - MinY1);
        c_y2 =(c_y2 - MinY2) / (MaxY2 - MinY2);





    }















    public boolean isStoragePermissionGranted() {             //штука которая дает доступ к файлам
        String TAG = "";
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }


    public  void start() {

        int i =0,j, vir = 0;

        Integer rand1;
        Integer rand[] = new Integer[3];
        crt();
      //  int upper=0;
        int creator = 5;
       // Toast.makeText(MainActivity.this, String.valueOf(creator), Toast.LENGTH_SHORT).show();
        start_random(); // тут создается первое поколение векторов



              //20 т.к. у нас 2 входа // начальный вектор
        mutant[] mut = new mutant[ct];       //мутанты
        crash[] cr = new crash[ct];           //скрещенные


        for ( i = 0; i < epoha; i++) {        // ЦИКЛ ПО эпохам
          //  while(Min_Err > 0.01) {
         //   upper++;
            vir++;
//            if(upper == epoha/10) {
//                upper=0;
//                fi/=10;
//            }
            swap(); // тут функция, перемешивающая массив примеров



               if(i!=0) { // если это не первая итерация, то переприсваиваем вектр эндвектр


                   for (int wtf = 0; wtf < ct; wtf++) {          //каждый раз нужно чтобы выполнялось переприсваивание от финального к стартовому
                                                             //старый вектор, новые значения y

                       vec[wtf] = new vectr(end[wtf].w, end[wtf].e, end[wtf].r, end[wtf].t, alpha);
                   }


               }


               /// убираю вырождение

            if(vir == creator) {

                NoDie();
             vir = 0;
            }

////////////////////////////////////////////////////////
            for (j = 0; j < ct; j++) {            //выбор случайныъ векторов, из которых будет собран мутант

                for (int hi = 0; hi < 3; hi++) {
                    for (int no = 0; no < 4; no++) {
                        Random random = new Random();
                        rand1 = random.nextInt(ct);
                        if (rand1 != j) {
                            rand[hi] = rand1;
                        }
                    }

                }
                mut[j] = new mutant(vec[rand[0]], vec[rand[1]], vec[rand[2]],fi);    //массив мутантов

            }     //на выходе цикла получил массив объектов мутантов

            for (j = 0; j < ct; j++) {         //создаю массив объектов, скрещенных
                cr[j] = new crash(mut[j], vec[j],alpha,p);
            }
            boolean isBetter;
            for( int ui = 0; ui < ct; ui++) {
               isBetter = check(vec[ui],cr[ui]); // сравниваю пробный вектор с исходным относительно ошибок для каждого примера
                end[ui] = new end_vectr(vec[ui], cr[ui],isBetter,alpha); // передаю в конструктор класса оба вектора, и результат функции о том, в каком из них ошикба меньше
                 if(Min_Err < eps) {
                     fin_vectr = end[ui];
                     ggg = true;
                     st_text();
                     break;
                 }
            }





               end_vectr try_check;
            try_check = final_check();
            String all = "Эпоха№" + i+ "\n";
//////////////////////////////////////////////////////////
            for (int a =0; a < 3; a ++) {
                for (int b =0; b < 3; b ++) {

                   all+= try_check.w[a][b] + "\n";
                }

            }

            for (int a =0; a < 4; a ++) {
                for (int b =0; b < 3; b ++) {

                    all+= try_check.e[a][b] + "\n";
                }

            }

            for (int a =0; a < 4; a ++) {
                for (int b =0; b < 3; b ++) {

                    all+= try_check.r[a][b] + "\n";
                }

            }

            for (int a =0; a < 4; a ++) {
                for (int b =0; b < 2; b ++) {

                    all+= try_check.t[a][b] + "\n";
                }

            }
      ///////////////////////////////////////////////////////////

                try(FileWriter writer = new FileWriter(dir + "answers.txt", true))
                {
                    // запись всей строки

                    writer.write(all);
                    writer.close();
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }



            if(ggg)
                break; //если был найден удовлетворительный вектор - выход из цикла эпох

        }
        if(!ggg) {
            fin_vectr = final_check();
            st_text();

        }
        pr.setVisibility(View.INVISIBLE);

        img3.setVisibility(View.VISIBLE);



 // записал весовые коэффициенты в массив






    }

   public void crt(){
        for (int a =0; a < 3; a ++) {
            for (int b =0; b < 3; b ++) {
//                Random rnd = new Random(System.currentTimeMillis());
//                rand = min + rnd.nextDouble() * (max - min + 1.0);
                rand = (Math.random()*(max-min) ) + min;
                w[a][b] = rand;

            }

        }

        for (int a =0; a < 4; a ++) {
            for (int b =0; b < 3; b ++) {
//                Random rnd = new Random(System.currentTimeMillis());
//                rand = min + rnd.nextDouble()*(max - min + 1.0);
                rand = (Math.random()*(max-min) ) + min;
                e[a][b] = rand;
            }

        }

        for (int a =0; a < 4; a ++) {
            for (int b =0; b < 3; b ++) {
//                Random rnd = new Random(System.currentTimeMillis());
//                rand = min + rnd.nextDouble() * (max - min + 1.0);
                rand = (Math.random()*(max-min) ) + min;
                r[a][b] = rand;
            }

        }


       for (int a =0; a < 4; a ++) {
           for (int b =0; b < 2; b ++) {
//                Random rnd = new Random(System.currentTimeMillis());
//                rand = min + rnd.nextDouble() * (max - min + 1.0);
               rand = (Math.random()*(max-min) ) + min;
               t[a][b] = rand;
           }

       }





   }


    public void swap() {      //перемешиваем массив
        int i;
        int rand;
        Integer mx = 100;
      //  Integer mn = 0;
        Double xx1,xx2,yy1,yy2; // временные переменные для обмена

        for (i = 0; i < 100; i++) {

            Random rnd = new Random();
            rand = rnd.nextInt(mx);
           // rand = (Math.random()*(mx));

            xx1 = x_1[i];         //запомнил текущие значения
            xx2 = x_2[i];
            yy1 = y_1[i];
            yy2 = y_2[i];


            x_1[i] = x_1[rand];   // текущей присвоил значения случайного
            x_2[i] = x_2[rand];
            y_1[i] = y_1[rand];
            y_2[i] = y_2[rand];

            x_1[rand] = xx1; //случайному присвоил значения текущего
            x_2[rand] = xx2;
            y_1[rand] = yy1;
            y_2[rand] = yy2;

        }

    }

    //ПРОВЕРКА ВЕКТОРА
    public  boolean check(vectr v, crash c) {
          double base_err =0,crash_err = 0;

       //  Max_Err = 0.0;

        for(int i = 0; i <100;i++) { //цикл по всеми примерам
          v.x1 = x_1[i]; // переопределяю значения икса и запускаю расчет
          v.x2 = x_2[i];
          c.x1 = x_1[i];
          c.x2 = x_2[i];
          v.Go();
          c.Go(); // посчитал новые значения Y
            base_err += (y_1[i] - v.y1)*(y_1[i] - v.y1) + (y_2[i] - v.y2)*(y_2[i] - v.y2);            //сумма квадратов ошибокза 100 примеров
            crash_err += (y_1[i] - c.y1)*(y_1[i] - c.y1) + (y_2[i] - c.y2)*(y_2[i] - c.y2);

        }

        if(base_err/200 < crash_err/200) {
            //возвращаю базовый вектор
           // if(Math.sqrt(base_err/200) < Min_Err)
            Min_Err = Math.sqrt(base_err/200);
            if(Min_Err < MMR)
                MMR = Min_Err;

         //   if(Math.sqrt(crash_err/200) > Max_Err)
              //  Max_Err =Math.sqrt(crash_err/200);

            return false;
        } else {
            //возвращаю скрещеный вектор
          //  if(Math.sqrt(crash_err/200) < Min_Err)
            Min_Err = Math.sqrt(crash_err/200);
            if(Min_Err < MMR)
                MMR = Min_Err;

           // if(Math.sqrt(base_err/200)> Max_Err)
              //  Max_Err =Math.sqrt(base_err/200);

            return  true;
        }




                                                        //добавить проверку остановку сюда
    }

   public end_vectr final_check() { // ищу ошибку для  векторов// каждому вектору даю по 100 примеров //ФУНКЦИЮ ВЫЗЫВАТЬ В КОНЦЕ ПОСЛЕДНЕЙ ИТЕРАЦИИ ПОСЛЕДНЕЙ ЭПОХИ
       // Double local_err;
        for(int i = 0; i < ct; i++) { // i - номер вектора
            for( int j = 0; j < 100; j++) { //j - номер примера

                end[i].x1 = x_1[j]; // каждому примеру, по очереди, даю пример и смотрю ошибку
                end[i].x2 = x_2[j];
                end[i].Go(); // запускаю расчет функции
               // local_err = (y_1[j] - end[i].y1)*(y_1[j] - end[i].y1) + (y_2[j] - end[i].y2)*(y_2[j] - end[i].y2); // ошибка на текущем примере
                           // квадрат ошибки по Y1    +  // квадрат ошибки по Y2
                err[i] +=  (y_1[j] - end[i].y1)*(y_1[j] - end[i].y1) + (y_2[j] - end[i].y2)*(y_2[j] - end[i].y2); // в ERR лежит суммарная ошибка по всем примерам для одного вектора
            }
           // local_err =0.0;
        }


        // теперь найдем наименьшую ошибку и вернем этот вектор
        double mmm = 99999999999999999999999.0; //максимальное число
       int index = 0;
       for(int i = 0; i < ct; i++) { // i - номер вектора
           if( err[i] < mmm) {
               mmm = err[i];
               Min_Err = Math.sqrt(err[i]/200);
               if(Min_Err < MMR)
                   MMR = Min_Err;
               index = i;
           }
           }

       return end[index]; // возвращаем вектор с минимальной ошибкой

       }

       public void start_random() {
           for (int go = 0; go < ct; go++) {        //изначальный массив векторов, если мы на первой итерации
               vec[go] = new vectr(w, e, r, t,alpha); // передаю начальный пример и рандомные коэффициенты
               crt();

           }

       }


       public  void  get_info() {
        epoha = Integer.valueOf(ep.getText().toString());
        eps = Double.valueOf(es.getText().toString());
        min = Double.valueOf(mi.getText().toString());
        max = Double.valueOf(ma.getText().toString());
        ct = Integer.valueOf(c.getText().toString());
        fi = Double.valueOf(f.getText().toString());
        alpha = Double.valueOf(al.getText().toString());
        p = Integer.valueOf(p1.getText().toString());
          vec = new vectr[ct];
          end = new end_vectr[ct];      // новое поколение векторов
          err = new Double[ct]; // массив, в который складываются ошибки финальных векторов

           for( int y = 0; y < ct; y++) {
               err[y] = 0.0;
           }
       }


       public  void NoDie(){           //АЛГОРИТМ : СОРТИРУЮ ПО ВОЗРАСТАНИЮ ФУНКЦИИ НА СЛУЧАЙНОМ ПРИМЕРЕ - СРАВНИВАЮ РЯДОМ СТОЯЩИЕ - ЗАМЕНЯЮ ПОХОЖИЕ

        double error;
           for(int i =0; i < ct; i++) { // считаем значения функции для случайного примера во всех векторах
               vec[i].x1 = x_1[0];
               vec[i].x2 = x_2[0];
               vec[i].Go();
           }
        Sort();
           for( int i = 1; i < ct ; i++) { // 0 1 2 3 4 // сравниваем два ближайших - если дельта минимальна то заменяем первый на рандом

                 error = (vec[i].y1 -  vec[i-1].y1) * (vec[i].y1 -  vec[i-1].y1)     +       (vec[i].y2 -  vec[i-1].y2) * (vec[i].y2 -  vec[i-1].y2); // если дельта текущего и предыдущего меньше положенного - заменяем предыдущий на рандом
               if( error < max_delta) { //если разница слишком маленькая
                   crt();
                   vec[i-1] = new vectr(w,e,r,t,alpha);  // заменяю похожий на рандомный
               }


           }

       }
     public  void  Sort() { // сортирует массив от наиенших к наибольшим значениям у1
         vectr swap;
         int ind = 0;
         double mini = 99999999999999999999.0;
         for (int j = 0; j < ct; j++) {

             for (int i = j; i < ct; i++) { // нашел минимальный элемент среди не сортированных

                 if (vec[i].y1 < mini) {
                     mini = vec[i].y1;
                     ind = i; // запомнил индекс минимального элемента
                 }

             }
                 swap = vec[j];
                 vec[j] = vec[ind];
                 vec[ind] = swap;
                 mini = 99999999999999999999.0;
                // ct1++; // теперь цикл пропустит первый элемент

         }

     }

     public  void st_text() {

         vectr[] vec1 = new vectr[3];
         vec1[0] = new vectr(fin_vectr.w, fin_vectr.e, fin_vectr.r,fin_vectr.t,alpha);
         vec1[1] = new vectr(fin_vectr.w, fin_vectr.e, fin_vectr.r,fin_vectr.t,alpha);
         vec1[2] = new vectr(fin_vectr.w, fin_vectr.e, fin_vectr.r,fin_vectr.t,alpha);
         vec1[0].x1 = a_x1;
         vec1[0].x2 = a_x2;
         vec1[0].Go();

         vec1[1].x1 = b_x1;
         vec1[1].x2 = b_x2;
         vec1[1].Go();


         vec1[2].x1 = c_x1;
         vec1[2].x2 = c_x2;
         vec1[2].Go();

//                                    Toast.makeText(MainActivity.this,
//                                            "Расчет окончен. расчтеное значение y1 на итерации 100 = " + fin_vectr[0].y1,
//                                            Toast.LENGTH_SHORT).show();
//        String jh = " Значение y1  для примера 100 = " + fin_vectr.y1;
//                                         txt2.setText(jh);


         String jo =

                 "a_y1 = " + a_y1 + "//"+ (vec1[0].y1) +" = a_y1_r " + "\n"+
                         "a_y2 = " + a_y2 + "//"+ (vec1[0].y2) +" = a_y2_r " + "\n"+
                         "b_y1 = " + b_y1 + "//"+ (vec1[1].y1) +" = b_y1_r " + "\n"+
                         "b_y2 = " + b_y2 + "//"+ (vec1[1].y2) +" = b_y2_r " + "\n"+
                         "c_y1 = " + c_y1 + "//"+ (vec1[2].y1) +" = c_y1_r " + "\n"+
                         "c_y2 = " + c_y2 + "//"+ (vec1[2].y2) +" = c_y2_r " + "\n"+
                         "Min_err= " +Min_Err+ "\n"   +
                         "MMR= " +MMR+ "\n";
                        // "Max_err= " +Max_Err+ "\n";
         if(ggg) {
             jo += ttt;
         }

         txt3.setText(jo);

     }

    }

