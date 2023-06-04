package com.example.atividade03;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade03.entidade.Produto;

import org.w3c.dom.Text;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    private Produto[] mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        private final TextView descricao;
        private final TextView preco;
        private final TextView glutem;
        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.textViewNomeComida);
            imageView = (ImageView) v.findViewById(R.id.idImagemComida);
            descricao = (TextView) v.findViewById(R.id.descricaoComida);
            preco = (TextView) v.findViewById(R.id.preco);
            glutem = (TextView) v.findViewById(R.id.glutem);

        }
        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView(){
            return imageView;
        }
        public TextView getDescricao() {
            return descricao;
        }
        public TextView getPreco(){
            return preco;
        }
        public TextView getGlutem(){
            return glutem;
        }
    }

    public RecyclerViewAdapter (Produto[] dataSet) {
        mDataSet = dataSet;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cardapio, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        Produto p = mDataSet[position];
        viewHolder.getTextView().setText(p.getNome());
        viewHolder.getImageView().setImageBitmap(p.getBm());
        viewHolder.getDescricao().setText(p.getDescricao());
        viewHolder.getPreco().setText("Preco: "+p.getPreco());
        viewHolder.getGlutem().setText(p.getGlutem());
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}