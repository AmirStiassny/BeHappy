package com.example.green.behappy.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.green.behappy.controller.AppController;
import com.example.green.behappy.model.Quote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuoteData {

    ArrayList<Quote> quotesArrayList = new ArrayList<>();

    public void getQuotes(final QuoteListAsyncResponse callBack){

        String url ="https://raw.githubusercontent.com/pdichone/UIUX-Android-Course/master/Quotes.json%20";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0 ; i < response.length(); i++){

                    try {
                        JSONObject quoteObject = response.getJSONObject(i);

                        Quote quote = new Quote();
                        quote.setQuote(quoteObject.getString("quote"));
                        quote.setAuthor(quoteObject.getString("name"));

                        Log.d("Stuff",quoteObject.getString("name"));

                        quotesArrayList.add(quote);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if(null != callBack) {

                    callBack.prosessFinished(quotesArrayList);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}
