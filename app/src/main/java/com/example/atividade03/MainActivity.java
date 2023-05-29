package com.example.atividade03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.atividade03.entidade.Produto;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Produto[] produtos;
    Activity tela = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Produto p1 = new Produto("Macarronada", "https://static.wikia.nocookie.net/ptstarwars/images/0/01/Hansoloprofile.jpg/revision/latest/top-crop/width/360/height/450?cb=20120222133702\"");
        produtos = new Produto[]{p1};
        for (Produto p : produtos) {
            new ThreadImageFile().execute(p);
        }
    }
    private class ThreadImageFile extends AsyncTask<Produto, Integer, byte[]> {
        @Override protected
        byte[] doInBackground(Produto... produto) {
            String resourceURI = produto[0].getUrl();
            byte[] content= null;
            try {
                URL url = new URL(resourceURI);
                HttpURLConnection con = null;
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                InputStream is = con.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                content = os.toByteArray();
                Bitmap bmp= BitmapFactory.decodeByteArray(content,0,content.length);

                produto[0].setBm(bmp);
            }catch(Exception e){e.printStackTrace();
            }
            return content;
        }
        @Override protected
        void onPostExecute (byte[] result) {
            RecyclerViewAdapter ca = new RecyclerViewAdapter(produtos);
            RecyclerView cv = findViewById(R.id.recyclerViewCardapio);
            cv.setLayoutManager(new LinearLayoutManager(tela, LinearLayoutManager.HORIZONTAL, false));
            cv.setAdapter(ca);
        }
    }
}