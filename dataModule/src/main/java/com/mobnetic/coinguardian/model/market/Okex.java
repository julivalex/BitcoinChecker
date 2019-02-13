package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Okex extends Market {

    public final static String NAME = "Okex";
    public final static String TTS_NAME = "Okex";

    private final static String URL = "https://www.okex.com/api/v1/ticker.do?symbol=%1$s";
    private final static String URL_CURRENCY_PAIRS = "https://www.okex.com/v2/spot/markets/products";

    public Okex() {
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
        ticker.low = dataJsonObject.getDouble("low");
        ticker.high = dataJsonObject.getDouble("high");
        ticker.vol = dataJsonObject.getDouble("vol");
        ticker.last = dataJsonObject.getDouble("last");
        ticker.bid = dataJsonObject.getDouble("sell");
        ticker.ask = dataJsonObject.getDouble("buy");
    }

    @Override
    protected void parseCurrencyPairsFromJsonObject(int requestId, JSONObject jsonObject, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);
            final String pairJsonString = pairJsonObject.getString("symbol");

            String[] currencies = pairJsonString.split("_");
            if(currencies.length != 2)
                continue;
            pairs.add(new CurrencyPairInfo(currencies[0].toUpperCase(), currencies[1].toUpperCase(), pairJsonString));
        }
    }
}
