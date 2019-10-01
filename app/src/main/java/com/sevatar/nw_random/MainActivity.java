package com.sevatar.nw_random;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    public String dir = "storage/emulated/0/Download/";
    public String f_name = "emp";
    ImageView img, img2;
    ArrayList<String> str1 = new ArrayList<String>();
    ArrayList<Double> x1 = new ArrayList<Double>();
    ArrayList<Double> x2 = new ArrayList<Double>();
    ArrayList<Double> y1 = new ArrayList<Double>();
    ArrayList<Double> y2 = new ArrayList<Double>();
    Double w[][];
    Double e[][];
    Double r[][];
    Integer exm, min, max;
    Double n1,n2,n3,n4,n5,n6,n7,alpha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isStoragePermissionGranted();

        img = (ImageView)findViewById(R.id.imageView);
        img2 = (ImageView)findViewById(R.id.imageView2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadComplete();
//                if(str1.size() != 0)
//                    Toast.makeText(MainActivity.this,
//                            "Строка 1 = " +(str1.get(0)),Toast.LENGTH_SHORT).show();
            }
        });
                 //вход // выход



        w = new Double[3][2];            //МАССИВЫ ВЕСОВЫХ КОЭФФИЦИЕНТОВ
        e = new Double[3][3];
        r = new Double[4][2];
        alpha = 2.0;
        exm = 0;
        min =1;
        max = 5;
        Integer rand;

        for (int a =0; a < 3; a ++) {
            for (int b =0; b < 2; b ++) {
                Random rnd = new Random(System.currentTimeMillis());
                rand = min + rnd.nextInt(max - min + 1);
                w[a][b] = Double.valueOf(rand);
            }

        }

        for (int a =0; a < 3; a ++) {
            for (int b =0; b < 3; b ++) {
                Random rnd = new Random(System.currentTimeMillis());
                rand = min + rnd.nextInt(max - min + 1);
                e[a][b] = Double.valueOf(rand);
            }

        }

        for (int a =0; a < 4; a ++) {
            for (int b =0; b < 2; b ++) {
                Random rnd = new Random(System.currentTimeMillis());
                rand = min + rnd.nextInt(max - min + 1);
                r[a][b] = Double.valueOf(rand);
            }

        }
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1 = s1(w[0][0],w[1][0],w[2][0],exm, alpha);
                n2 = s1(w[0][1],w[1][1],w[2][1],exm, alpha);
////////////////////первый слой /////////////////////////////
                n3 = s2(e[0][0],e[1][0],e[2][0],exm, alpha);
                n4 = s2(e[0][1],e[1][1],e[2][1],exm, alpha);
                n5 = s2(e[0][2],e[1][2],e[2][2],exm, alpha);
                ////////////////второй слой//////////////////
                n6 = s3(r[0][0],r[1][0],r[2][0],r[3][0],exm, alpha);
                n7 = s3(r[0][1],r[1][1],r[2][1],r[3][1],exm, alpha);

                                    Toast.makeText(MainActivity.this,
                            "y1 = " +n6 + "   y2 = " + n7,Toast.LENGTH_SHORT).show();

            }
        });
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
        for ( i = 0; i < str1.size(); i++) {
            index = (str1.get(i)).indexOf("\t");
             swap = str1.get(i).substring(0,index);
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

//                                    Toast.makeText(MainActivity.this,
//                                            "x1 = " +x1.get(3) + "x2 = "+ x2.get(3) +
//                                                    "y1 = " + y1.get(3) + "y2 = " + y2.get(3),
//                                            Toast.LENGTH_SHORT).show();
    }





public  double  s1(double w01, double w11, double w21, Integer i, double alpha){
        double s = w01 + (w11 * x1.get(i)) +(w21 * x2.get(i));
        double fs = 1/(1+(Math.pow(Math.E,-alpha*s)));

return fs;
}
    public  double  s2 (double e01, double e11, double e21, Integer i, double alpha){
        double s = e01 + (e11 * n1 +(e21 * n2));
        double fs = 1/(1+(Math.pow(Math.E,-alpha*s)));

        return fs;
    }

    public  double  s3 (double e01, double e11, double e21, double e31, Integer i, double alpha){
        double s = e01 + (e11 * n3 +(e21 * n4) +(e31 * n5) );
        double fs = 1/(1+(Math.pow(Math.E,-alpha*s)));

        return fs;
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

}