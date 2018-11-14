package com.example.green.behappy;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

import static android.support.v4.content.ContextCompat.checkSelfPermission;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 1;
    String quote;
    String author;
    ImageView imageView;

    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        int colors[] = new int[]{R.color.md_blue_500, R.color.md_pink_900, R.color.md_green_400
                , R.color.md_deep_orange_400, R.color.md_amber_800};


        TextView quoteText = (TextView) view.findViewById(R.id.quoteText);
        TextView Author = (TextView) view.findViewById(R.id.byAuthor);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        Button ShareQuotes = (Button) view.findViewById(R.id.ShareButton);
       // Button picButton = (Button) view.findViewById(R.id.picButton);
        imageView = (ImageView) view.findViewById(R.id.imageshot);

        quote = getArguments().getString("quote");
        author = getArguments().getString("author");

        quoteText.setText(quote);
        Author.setText("-" + author);


        cardView.setBackgroundResource(getRandomQuote(colors));

        ShareQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "ציטוט היום:" + "\n" + quote + "\n" + "הכותב הוא: " + author;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }

        });

        return view;
    }

    public static final QuoteFragment newInstance(String quote, String author) {

        QuoteFragment fragment = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author", author);
        fragment.setArguments(bundle);

        return fragment;
    }

    int getRandomQuote(int[] colorArray) {

        int color;
        int quotesArrayLen = colorArray.length;

        int randomNum = ThreadLocalRandom.current().nextInt(quotesArrayLen);

        color = colorArray[randomNum];

        return color;
    }
}


