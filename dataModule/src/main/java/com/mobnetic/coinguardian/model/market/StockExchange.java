package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class StockExchange extends Market {

    private final static String NAME = "StockExchange";
    private final static String TTS_NAME = "StockExchange";
    private final static String URL = "https://app.stex.com/api2/ticker";
    private final static String URL_CURRENCY_PAIRS = "https://app.stex.com/api2/markets";

    public StockExchange() {
        super(NAME, TTS_NAME, null);
    }

    @Override
    public String getUrl(int requestId, CheckerInfo checkerInfo) {
        return URL;
    }

    @Override
    public String getCurrencyPairsUrl(int requestId) {
        return URL_CURRENCY_PAIRS;
    }

    @Override
    protected void parseTicker(int requestId, String responseString, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
        JSONArray jsonArray = new JSONArray(responseString);
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);
            String market_name = pairJsonObject.getString("market_name");
            if(market_name.equals(checkerInfo.getCurrencyPairId())) {
                this.parseTickerFromJsonObject(requestId, pairJsonObject, ticker, checkerInfo);
            }
        }
    }

    @Override
    protected void parseTickerFromJsonObject(int requestId, JSONObject jsonObject, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
        ticker.vol = jsonObject.getDouble("vol");
        ticker.last = jsonObject.getDouble("last");
        ticker.bid = jsonObject.getDouble("bid");
        ticker.ask = jsonObject.getDouble("ask");

    }


    @Override
    protected void parseCurrencyPairs(int requestId, String responseString, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = new JSONArray(responseString);
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);

            String currencyPairId = pairJsonObject.getString("market_name");
            String currencyBase = pairJsonObject.getString("currency");
            String currencyCounter = pairJsonObject.getString("partner");

            pairs.add(new CurrencyPairInfo(currencyBase, currencyCounter, currencyPairId));
        }

    }
}
