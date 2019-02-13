package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class BtcAlpha extends Market {

    public final static String NAME = "BtcAlpha";
    public final static String TTS_NAME = "BTC-Alpha";

    private final static String URL = "https://btc-alpha.com/api/charts/%1$s/1/chart/";
    private final static String URL_CURRENCY_PAIRS = "https://btc-alpha.com/api/v1/pairs/";

    public BtcAlpha() {
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
    protected void parseTicker(int requestId, String responseString, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
        this.parseTickerFromJsonObject(requestId, new JSONArray(responseString).getJSONObject(0), ticker, checkerInfo);
    }

    @Override
    protected void parseTickerFromJsonObject(int requestId, JSONObject jsonObject, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
        ticker.last = jsonObject.getDouble("close");
        ticker.vol = jsonObject.getDouble("volume");
        ticker.high = jsonObject.getDouble("high");
        ticker.low = jsonObject.getDouble("low");

    }

    @Override
    protected void parseCurrencyPairs(int requestId, String responseString, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = new JSONArray(responseString);
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);

            String currencyPairId = pairJsonObject.getString("name");
            String currencyBase = pairJsonObject.getString("currency1");
            String currencyCounter = pairJsonObject.getString("currency2");

            pairs.add(new CurrencyPairInfo(currencyBase, currencyCounter, currencyPairId));
        }

    }
}
