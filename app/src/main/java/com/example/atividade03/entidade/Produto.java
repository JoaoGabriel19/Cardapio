package com.example.atividade03.entidade;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Produto {
    private String nome;
    private String url = "https://static.wikia.nocookie.net/ptstarwars/images/0/01/Hansoloprofile.jpg/revision/latest/top-crop/width/360/height/450?cb=20120222133702";
    private ImageView imagemProduto;
    private Bitmap bm;

    public String getNome()  {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ImageView getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(ImageView imagemProduto) {
        this.imagemProduto = imagemProduto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Produto(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }
}
