package com.example.green.behappy;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.green.behappy.data.QuoteData;
import com.example.green.behappy.data.QuoteListAsyncResponse;
import com.example.green.behappy.data.QuoteViewPagerAdapter;
import com.example.green.behappy.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuoteViewPagerAdapter quoteViewPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteViewPagerAdapter = new QuoteViewPagerAdapter(getSupportFragmentManager(),getFragments());

        viewPager = findViewById(R.id.ViewPager);
        viewPager.setAdapter(quoteViewPagerAdapter);

    }

    private List<QuoteFragment> getFragments() {
        final List<QuoteFragment> fragmentsList = new ArrayList<>();

        new QuoteData().getQuotes(new QuoteListAsyncResponse() {
            @Override
            public void prosessFinished(ArrayList<Quote> quotes) {
                for( int i = 0 ; i < quotes.size() ; i++) {

                    QuoteFragment quoteFragment = QuoteFragment.newInstance(
                            quotes.get(i).getQuote(),
                            quotes.get(i).getAuthor()
                    );
                    fragmentsList.add(quoteFragment);

                }

                quoteViewPagerAdapter.notifyDataSetChanged();   ////very important

            }

        });

        return fragmentsList;

    }
}
