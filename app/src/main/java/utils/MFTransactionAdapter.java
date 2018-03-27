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

public class MFTransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<MFTransaction> mfTransactionArrayList;


    ClickListener clickListener;


    public class ClientViewHolder extends RecyclerView.ViewHolder {
        public TextView headingTextView, idTextView, cagrTextView, currentInvTextView, currentValueTextView, absReturnTextView, divInvTextView, divPayTextView, nameTextView, sinceDateTextView;

        public ClientViewHolder(final View view) {
            super(view);

            headingTextView = view.findViewById(R.id.mfAdapter_heading_textView);
            idTextView = view.findViewById(R.id.mfAdapter_id_textView);
            cagrTextView = view.findViewById(R.id.mfAdapter_cagr_textView);
            currentInvTextView = view.findViewById(R.id.mfAdapter_currentInv_textView);
            currentValueTextView = view.findViewById(R.id.mfAdapter_currentVal_textView);
            absReturnTextView = view.findViewById(R.id.mfAdapter_absReturn_textView);
            divInvTextView = view.findViewById(R.id.mfAdapter_divInv_textView);
            divPayTextView = view.findViewById(R.id.mfAdapter_divPay_textView);
            nameTextView = view.findViewById(R.id.mfAdapter_name_textView);
            sinceDateTextView = view.findViewById(R.id.mfAdapter_since_textView);

        }
    }


    public MFTransactionAdapter(ArrayList<MFTransaction> mfTransactionArrayList, Context context) {
        this.mfTransactionArrayList = mfTransactionArrayList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mf_transaction_adapter_row_layout, parent, false);


        return new ClientViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ClientViewHolder viewHolder = (ClientViewHolder) holder;

        MFTransaction mfTransaction = mfTransactionArrayList.get(position);

        viewHolder.headingTextView.setText(mfTransaction.getScheme());
        viewHolder.idTextView.setText(mfTransaction.getFolio_no());
        viewHolder.cagrTextView.setText(mfTransaction.getCagr());
        viewHolder.currentInvTextView.setText(mfTransaction.getAmount());
        viewHolder.currentValueTextView.setText(mfTransaction.getCurrentValue());
        viewHolder.absReturnTextView.setText(mfTransaction.getAbsReturn());
        viewHolder.divPayTextView.setText(mfTransaction.getDivPay());
        viewHolder.divInvTextView.setText(mfTransaction.getDivInvest());

        viewHolder.nameTextView.setText(mfTransaction.getName());
        viewHolder.sinceDateTextView.setText(mfTransaction.getTraddate());

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return mfTransactionArrayList.size();
    }


    public interface ClickListener {
        public void onBookMarkClick(View view, int position);

        public void onTitleClick(View view, int position);
    }

}
