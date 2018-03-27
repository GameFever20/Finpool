package utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import finpool.finance.app.finpool.R;

/**
 * Created by bunny on 23/03/18.
 */

public class ClientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<Client> clientArrayList;


    ClickListener clickListener;


    public class ClientViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ClientViewHolder(final View view) {
            super(view);

            title = view.findViewById(R.id.clientAdapter_title_textView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener!=null){
                        clickListener.onItemClick(getAdapterPosition(),view);
                    }
                }
            });

        }
    }


    public ClientAdapter(ArrayList<Client> clientArrayList, Context context) {
        this.clientArrayList = clientArrayList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_adapter_row_layout, parent, false);


        return new ClientViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ClientViewHolder clientViewHolder = (ClientViewHolder) holder;

        clientViewHolder.title.setText(clientArrayList.get(position).getName());


    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return clientArrayList.size();
    }


    public interface ClickListener {
       public void onItemClick(int position, View view);
    }

}
