package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class TrocaNinja extends Market {

    public final static String NAME = "TrocaNinja";
    public final static String TTS_NAME = "Troca Ninja";

    private final static String URL = "https://troca.ninja/api/ticker/%1$s";
    private final static String URL_CURRENCY_PAIRS = "https://troca.ninja/api/markets";

    public TrocaNinja() {
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
        final JSONObject dataJsonObject = jsonObject.getJSONArray("result").getJSONObject(0);
        ticker.last = dataJsonObject.getDouble("Last");
        ticker.vol = dataJsonObject.getDouble("BaseVolume");
        ticker.ask = dataJsonObject.getDouble("Ask");
        ticker.bid = dataJsonObject.getDouble("Bid");
        ticker.high = dataJsonObject.getDouble("High");
        ticker.low = dataJsonObject.getDouble("Low");

    }

    @Override
    protected void parseCurrencyPairsFromJsonObject(int requestId, JSONObject jsonObject, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);

            String currencyBase = pairJsonObject.getString("BaseCurrency");
            String currencyCounter = pairJsonObject.getString("MarketCurrency");
            String currencyPairId = currencyBase + "_" + currencyCounter;

            pairs.add(new CurrencyPairInfo(currencyBase, currencyCounter, currencyPairId));
        }
    }

}
