package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Graviex extends Market {

    private final static String NAME = "Graviex";
    private final static String TTS_NAME = "Graviex";
    private final static String URL = "https://graviex.net//api/v2/tickers/%1$s.json";
    private final static String URL_CURRENCY_PAIRS = "https://graviex.net//api/v2/markets.json";

    public Graviex() {
        super(NAME, TTS_NAME, null);
    }

    @Override
    public String getUrl(int requestId, CheckerInfo checkerInfo) {
        return String.format(URL, checkerInfo.getCurrencyPairId());
    }

    @Override
    public String getCurrencyPairsUrl(int requestId) {
        return URL_CURRENCY_PAIRS;
    }

    @Override
    protected void parseTickerFromJsonObject(int requestId, JSONObject jsonObject, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
        final JSONObject dataJsonObject = jsonObject.getJSONObject("ticker");
        ticker.vol = dataJsonObject.getDouble("vol");
        ticker.high = dataJsonObject.getDouble("high");
        ticker.low = dataJsonObject.getDouble("low");
        ticker.last = dataJsonObject.getDouble("last");
        ticker.bid = dataJsonObject.getDouble("buy");
        ticker.ask = dataJsonObject.getDouble("sell");

    }

    @Override
    protected void parseCurrencyPairs(int requestId, String responseString, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = new JSONArray(responseString);
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);

            String currencyPairId = pairJsonObject.getString("id");
            String currencyTemp = pairJsonObject.getString("name");
            String[] currencies = currencyTemp.split("/");
            if(currencies.length != 2)
                continue;
            pairs.add(new CurrencyPairInfo(currencies[0], currencies[1], currencyPairId));
        }

    }

}
