package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Idax extends Market {

    public final static String NAME = "Idax";
    public final static String TTS_NAME = "Idax";

    private final static String URL = "https://openapi.idax.pro/api/v2/ticker?pair=%1$s";
    private final static String URL_CURRENCY_PAIRS = "https://openapi.idax.pro/api/v2/pairs";

    public Idax() {
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
        final JSONObject dataJsonObject = jsonObject.getJSONArray("ticker").getJSONObject(0);
        ticker.low = dataJsonObject.getDouble("low");
        ticker.high = dataJsonObject.getDouble("high");
        ticker.vol = dataJsonObject.getDouble("vol");
        ticker.ask = dataJsonObject.getDouble("open");
        ticker.bid = dataJsonObject.getDouble("last");
        ticker.last = dataJsonObject.getDouble("last");
    }

    @Override
    protected void parseCurrencyPairsFromJsonObject(int requestId, JSONObject jsonObject, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = jsonObject.getJSONArray("pairs");
        for (int i = 0; i < jsonArray.length(); i++) {
            final String pairJsonString = jsonArray.getString(i);

            String[] currencies = pairJsonString.split("_");
            if(currencies.length != 2)
                continue;
            pairs.add(new CurrencyPairInfo(currencies[0].toUpperCase(), currencies[1].toUpperCase(), pairJsonString));
        }
    }
}
