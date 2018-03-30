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

public class RecentTransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<RecentTransaction> recentTransactionArrayList;


    ClickListener clickListener;


    public class ReversalViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, nameTextView, amountTextView, dateTextView;

        public ReversalViewHolder(final View view) {
            super(view);

            titleTextView = view.findViewById(R.id.reversalAdapter_heading_textView);
            nameTextView = view.findViewById(R.id.reversalAdapter_name_textView);
            amountTextView = view.findViewById(R.id.reversalAdapter_amount_textView);
            dateTextView = view.findViewById(R.id.reversalAdapter_date_textView);


        }
    }


    public RecentTransactionAdapter(ArrayList<RecentTransaction> recentTransactionArrayList, Context context) {
        this.recentTransactionArrayList = recentTransactionArrayList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_transaction_adapter_row_layout, parent, false);


        return new ReversalViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ReversalViewHolder viewHolder = (ReversalViewHolder) holder;

        RecentTransaction recentTransaction = recentTransactionArrayList.get(position);

        viewHolder.titleTextView.setText(recentTransaction.getScheme());
        viewHolder.nameTextView.setText(recentTransaction.getName());
        viewHolder.amountTextView.setText(recentTransaction.getAmount());
        viewHolder.dateTextView.setText(recentTransaction.getDate());


    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return recentTransactionArrayList.size();
    }


    public interface ClickListener {
        public void onBookMarkClick(View view, int position);

        public void onTitleClick(View view, int position);
    }

}
