package utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import finpool.finance.app.finpool.R;

/**
 * Created by bunny on 23/03/18.
 */

public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<Client> clientArrayList;


    ClickListener clickListener;


    public class ClientViewHolder extends RecyclerView.ViewHolder {
        public TextView groupName,emailEditText, phoneNumberEditText, userIDEditText, passwordEditText;

        public ClientViewHolder(final View view) {
            super(view);

            groupName = view.findViewById(R.id.groupAdapter_heading_textView);
            emailEditText = view.findViewById(R.id.groupAdapter_email_textView);
            phoneNumberEditText = view.findViewById(R.id.groupAdapter_phoneNumber_textView);
            userIDEditText = view.findViewById(R.id.groupAdapter_userid_textView);
            passwordEditText = view.findViewById(R.id.groupAdapter_password_textView);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener!=null){
                        clickListener.onItemClick(getAdapterPosition(),view);
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (clickListener!=null){
                        clickListener.onItemLongClick(getAdapterPosition(),view);
                    }
                    return false;
                }
            });

        }
    }


    public GroupAdapter(ArrayList<Client> clientArrayList, Context context) {
        this.clientArrayList = clientArrayList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_adapter_row_layout, parent, false);


        return new ClientViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ClientViewHolder clientViewHolder = (ClientViewHolder) holder;

        clientViewHolder.groupName.setText(clientArrayList.get(position).getName());
        clientViewHolder.emailEditText.setText(clientArrayList.get(position).getEmail());
        clientViewHolder.phoneNumberEditText.setText(clientArrayList.get(position).getMobileNumber());
        clientViewHolder.userIDEditText.setText(clientArrayList.get(position).getUserID());
        clientViewHolder.passwordEditText.setText(clientArrayList.get(position).getPassword());



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

       public void onItemLongClick(int position, View view);

    }

}
