package finpool.finance.app.finpool;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import utils.Client;

public class TransactionReportActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Client groupSlected;
    private Client clientSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        groupSlected = (Client)getIntent().getSerializableExtra("groupSelected");
        clientSelected = (Client)getIntent().getSerializableExtra("clientSelected");


        viewPager = (ViewPager) findViewById(R.id.portfolio_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.portfolio_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {



        TransactionReportActivity.ViewPagerAdapter adapter = new TransactionReportActivity.ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(TransactionReportFragment.newInstance(1,clientSelected.getId(),groupSlected.getId()), "Purchase");

        adapter.addFragment(TransactionReportFragment.newInstance(2,clientSelected.getId(),groupSlected.getId()), "Redumption");


        viewPager.setAdapter(adapter);
    }



    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
