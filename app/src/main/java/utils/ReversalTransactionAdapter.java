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

public class ReversalTransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<ReversalTransaction> reversalTransactionArrayList;


    ClickListener clickListener;


    public class ReversalViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, nameTextView, amountTextView, reversalDateTextView;

        public ReversalViewHolder(final View view) {
            super(view);

            titleTextView = view.findViewById(R.id.reversalAdapter_heading_textView);
            nameTextView = view.findViewById(R.id.reversalAdapter_name_textView);
            amountTextView = view.findViewById(R.id.reversalAdapter_amount_textView);
            reversalDateTextView = view.findViewById(R.id.reversalAdapter_date_textView);


        }
    }


    public ReversalTransactionAdapter(ArrayList<ReversalTransaction> reversalTransactionArrayList, Context context) {
        this.reversalTransactionArrayList = reversalTransactionArrayList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reversal_transaction_adapter_row_layout, parent, false);


        return new ReversalViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ReversalViewHolder viewHolder = (ReversalViewHolder) holder;

        ReversalTransaction reversalTransaction = reversalTransactionArrayList.get(position);

        viewHolder.titleTextView.setText(reversalTransaction.getScheme());
        viewHolder.nameTextView.setText(reversalTransaction.getName());
        viewHolder.amountTextView.setText(reversalTransaction.getAmount());
        viewHolder.reversalDateTextView.setText(reversalTransaction.getReversalDate());


    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return reversalTransactionArrayList.size();
    }


    public interface ClickListener {
        public void onBookMarkClick(View view, int position);

        public void onTitleClick(View view, int position);
    }

}
