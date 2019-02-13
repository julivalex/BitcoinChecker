package com.mobnetic.coinguardian.model.market.example;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CryptoBridge extends Market {

    public final static String NAME = "CryptoBridge";
    public final static String TTS_NAME = "Crypto Bridge";

    private final static String URL = "https://api.crypto-bridge.org/api/v1/ticker/%1$s/";
    private final static String URL_CURRENCY_PAIRS = "https://api.crypto-bridge.org/api/v1/ticker";

    public CryptoBridge() {
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
        ticker.last = jsonObject.getDouble("last");
        ticker.vol = jsonObject.getDouble("volume");
        ticker.ask = jsonObject.getDouble("ask");
        ticker.bid = jsonObject.getDouble("bid");

    }

    @Override
    protected void parseCurrencyPairs(int requestId, String responseString, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = new JSONArray(responseString);
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);

            String currencyPairId = pairJsonObject.getString("id");
            String[] currencies = currencyPairId.split("_");
            if(currencies.length != 2)
                continue;
            pairs.add(new CurrencyPairInfo(currencies[0], currencies[1], currencyPairId));
        }

    }

}
