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

public class SipTransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<SipTransaction> sipTransactionArrayList;


    ClickListener clickListener;


    public class ClientViewHolder extends RecyclerView.ViewHolder {
        public TextView headingTextView, idTextView, sipAmountTextView, currentInvTextView, currentValueTextView, absReturnTextView, divInvTextView, divPayTextView;

        public ClientViewHolder(final View view) {
            super(view);

            headingTextView = view.findViewById(R.id.sipAdapter_heading_textView);
            idTextView = view.findViewById(R.id.sipAdapter_id_textView);
            sipAmountTextView = view.findViewById(R.id.sipAdapter_sipAmount_textView);
            currentInvTextView = view.findViewById(R.id.sipAdapter_currentInv_textView);
            currentValueTextView = view.findViewById(R.id.sipAdapter_currentVal_textView);
            absReturnTextView = view.findViewById(R.id.sipAdapter_absReturn_textView);
            divInvTextView = view.findViewById(R.id.sipAdapter_divInv_textView);
            divPayTextView = view.findViewById(R.id.sipAdapter_divPay_textView);

        }
    }


    public SipTransactionAdapter(ArrayList<SipTransaction> sipTransactionArrayList, Context context) {
        this.sipTransactionArrayList = sipTransactionArrayList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sip_transaction_adapter_row_layout, parent, false);


        return new ClientViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ClientViewHolder viewHolder = (ClientViewHolder) holder;

        SipTransaction sipTransaction = sipTransactionArrayList.get(position);

        viewHolder.headingTextView.setText(sipTransaction.getScheme());
        viewHolder.idTextView.setText(sipTransaction.getFolio_no());
        viewHolder.sipAmountTextView.setText(sipTransaction.getSipAmount());
        viewHolder.currentInvTextView.setText(sipTransaction.getCurrentInvestment());
        viewHolder.currentValueTextView.setText(sipTransaction.getCurrentValue());
        viewHolder.absReturnTextView.setText(sipTransaction.getAbsReturn());
        viewHolder.divPayTextView.setText(sipTransaction.getDivPay());
        viewHolder.divInvTextView.setText(sipTransaction.getDivInvest());


    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return sipTransactionArrayList.size();
    }


    public interface ClickListener {
        public void onBookMarkClick(View view, int position);

        public void onTitleClick(View view, int position);
    }

}
