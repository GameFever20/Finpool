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

public class TransactionReportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<TransactionReport> transactionReportArrayList;


    ClickListener clickListener;


    public class ClientViewHolder extends RecyclerView.ViewHolder {
        public TextView headingTextView, idTextView, investorNameTextView, tradeDateTextView, transTypeTextView, schemeTypeTextView, amountTextView, purcahsePriceTextView, unitTextView;

        public ClientViewHolder(final View view) {
            super(view);

            headingTextView = view.findViewById(R.id.transactionAdapter_heading_textView);
            idTextView = view.findViewById(R.id.transactionAdapter_id_textView);
            investorNameTextView = view.findViewById(R.id.transactionAdapter_name_textView);
            tradeDateTextView = view.findViewById(R.id.transactionAdapter_date_textView);
            transTypeTextView = view.findViewById(R.id.transactionAdapter_transType_textView);
            schemeTypeTextView = view.findViewById(R.id.transactionAdapter_schemeType_textView);
            amountTextView = view.findViewById(R.id.transactionAdapter_amount_textView);
            purcahsePriceTextView = view.findViewById(R.id.transactionAdapter_purchasePrice_textView);
            unitTextView = view.findViewById(R.id.transactionAdapter_unit_textView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });

        }
    }


    public TransactionReportAdapter(ArrayList<TransactionReport> transactionReportArrayList, Context context) {
        this.transactionReportArrayList = transactionReportArrayList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_report_adapter_row_layout, parent, false);


        return new ClientViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ClientViewHolder viewHolder = (ClientViewHolder) holder;

        TransactionReport transactionReport = transactionReportArrayList.get(position);

        viewHolder.headingTextView.setText(transactionReport.getScheme());
        viewHolder.idTextView.setText(transactionReport.getFolio_no());
        viewHolder.investorNameTextView.setText(transactionReport.getInvName());
        viewHolder.tradeDateTextView.setText(transactionReport.getTradeDate());
        viewHolder.transTypeTextView.setText(transactionReport.getSubType());
        viewHolder.schemeTypeTextView.setText(transactionReport.getSchemeType());
        viewHolder.amountTextView.setText(transactionReport.getAmount());
        viewHolder.purcahsePriceTextView.setText(transactionReport.getPurchasePrice());

        viewHolder.unitTextView.setText(transactionReport.getUnits());


    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return transactionReportArrayList.size();
    }


    public interface ClickListener {
        public void onItemClick(View view, int position);


    }

}
