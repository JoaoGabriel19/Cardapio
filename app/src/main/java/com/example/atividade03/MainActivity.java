package com.example.atividade03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;


import com.example.atividade03.entidade.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Produto[] produtos;
    List<Produto> listaProdutos = new ArrayList<>();

    Activity tela = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new request().execute();
        //Produto p1 = new Produto("macarronada", "https://static.wikia.nocookie.net/ptstarwars/images/0/01/Hansoloprofile.jpg/revision/latest/top-crop/width/360/height/450?cb=20120222133702\"", null, 1, 1, null);
        //produtos = new Produto[]{p1};

        //produtos = listaProdutos.toArray(new Produto[listaProdutos.size()]);
        /*for (Produto p : produtos) {
            new ThreadImageFile().execute(p);

        }*/
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
            Produto[] novosProdutos = listaProdutos.toArray(new Produto[listaProdutos.size()]);
            RecyclerViewAdapter ca = new RecyclerViewAdapter(novosProdutos);
            RecyclerView cv = findViewById(R.id.recyclerViewCardapio);
            cv.setLayoutManager(new LinearLayoutManager(tela, LinearLayoutManager.HORIZONTAL, false));
            cv.setAdapter(ca);
        }
    }
    private class request extends AsyncTask<Void, Void, String> {
        String resultado;


        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.0.100:8080/Cardapio/Servlet")
                    .build();
            try (Response response = client.newCall(request).execute()) {
                resultado = response.body().string();
                return resultado;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        protected void onPostExecute(String result) {
            if (resultado != null) {
                try {
                    JSONObject jsonObject = new JSONObject(resultado);
                    JSONArray produtoArray = jsonObject.getJSONArray("produto");
                    for (int i = 0; i < produtoArray.length(); i++) {
                        JSONObject objetoProduto = produtoArray.getJSONObject(i);

                        String nome = objetoProduto.getString("nome");
                        String descricao = objetoProduto.getString("descricao");
                        double preco = objetoProduto.getDouble("preco");
                        String glutem = objetoProduto.getString("glutem");
                        int calorias = objetoProduto.getInt("calorias");
                        String url = objetoProduto.getString("imagem");

                        Produto produto = new Produto(nome, url, descricao, preco, calorias, glutem);
                        listaProdutos.add(produto);
                        System.out.println(listaProdutos.get(i).getNome());
                        new ThreadImageFile().execute(produto);
                    }


                        } catch (JSONException e) {
                    throw new RuntimeException(e);
                        }
                    }
                }
            }

    }

