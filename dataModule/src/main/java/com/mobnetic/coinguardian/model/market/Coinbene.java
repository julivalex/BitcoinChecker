package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Coinbene extends Market {

    private final static String NAME = "Coinbene";
    private final static String TTS_NAME = "Coinbene";
    private final static String URL = "http://api.coinbene.com/v1/market/ticker?symbol=%1$s";
    private final static String URL_CURRENCY_PAIRS = "http://api.coinbene.com/v1/market/symbol";

    public Coinbene() {
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
        ticker.last = dataJsonObject.getDouble("last");
        ticker.bid = dataJsonObject.getDouble("bid");
        ticker.vol = dataJsonObject.getDouble("24hrVol");
        ticker.ask = dataJsonObject.getDouble("ask");
        ticker.high = dataJsonObject.getDouble("24hrHigh");
        ticker.low = dataJsonObject.getDouble("24hrLow");

    }

    @Override
    protected void parseCurrencyPairsFromJsonObject(int requestId, JSONObject jsonObject, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = jsonObject.getJSONArray("symbol");
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);

            String currencyPairId = pairJsonObject.getString("ticker");
            String currencyBase = pairJsonObject.getString("baseAsset");
            String currencyCounter = pairJsonObject.getString("quoteAsset");

            pairs.add(new CurrencyPairInfo(currencyBase, currencyCounter, currencyPairId));
        }
    }
}
