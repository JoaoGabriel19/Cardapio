package com.example.atividade03.entidade;

import android.graphics.Bitmap;
import android.widget.ImageView;


public class Produto {
    private String nome;
    private String url;
    private ImageView imagemProduto;
    private Bitmap bm;

    private String descricao;
    private double preco;
    private int calorias;
    private String glutem;



    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public double getPreco() {
        return preco;
    }


    public void setPreco(double preco) {
        this.preco = preco;
    }


    public String getGlutem() {
        return glutem;
    }


    public void setGlutem(String glutem) {
        this.glutem = glutem;
    }


    public int getCalorias() {
        return calorias;
    }


    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public ImageView getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(ImageView imagemProduto) {
        this.imagemProduto = imagemProduto;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Produto(String nome, String url, String descricao, double preco, int calorias, String glutem) {
        this.nome = nome;
        this.url = url;
        this.descricao = descricao;
        this.preco = preco;
        this.calorias = calorias;
        this.glutem = glutem;
    }

    public Produto() {
    }
}
