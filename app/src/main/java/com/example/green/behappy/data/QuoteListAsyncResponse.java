package com.example.green.behappy.data;

import com.example.green.behappy.model.Quote;

import java.util.ArrayList;

public interface QuoteListAsyncResponse {

    void prosessFinished(ArrayList<Quote>quotes);

}
