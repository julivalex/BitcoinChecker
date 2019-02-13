package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


public class Southxchange extends Market {

    private final static String NAME = "Southxchange";
    private final static String TTS_NAME = "Southxchange";
    private final static String URL = "https://www.southxchange.com/api/price/%1$s";
    private final static String URL_CURRENCY_PAIRS = "https://www.southxchange.com/api/markets";

    public Southxchange() {
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
        ticker.last = jsonObject.getDouble("Last");
        ticker.vol = jsonObject.getDouble("Volume24Hr");
        ticker.ask = jsonObject.getDouble("Ask");
        ticker.bid = jsonObject.getDouble("Bid");

    }

    @Override
    protected void parseCurrencyPairs(int requestId, String responseString, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = new JSONArray(responseString);
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONArray pairJsonObject = jsonArray.getJSONArray(i);

            String currencyBase = pairJsonObject.get(0).toString();
            String currencyCounter = pairJsonObject.get(1).toString();
            String currencyPairId = currencyBase + "/" + currencyCounter;

            pairs.add(new CurrencyPairInfo(currencyBase, currencyCounter, currencyPairId));
        }

    }
}
