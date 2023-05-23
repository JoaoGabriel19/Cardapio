package com.example.atividade03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.atividade03.entidade.Produto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Produto p1 = new Produto();
        p1.setNome("Macarronada");
        Produto p2 = new Produto();
        p2.setNome("Café");
        RecyclerViewAdapter ca= new RecyclerViewAdapter(new Produto[]{p1,p2});
        RecyclerView cv=this.findViewById(R.id.recyclerViewCardapio);
        cv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        cv.setAdapter(ca);
    }


}