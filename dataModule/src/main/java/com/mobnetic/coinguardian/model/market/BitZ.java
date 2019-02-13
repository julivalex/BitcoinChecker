package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class BitZ extends Market {

    private final static String NAME = "BitZ";
    private final static String TTS_NAME = "BitZ";
    private final static String URL = "https://apiv2.bitz.com/Market/ticker?symbol=%1$s";
    private final static String URL_CURRENCY_PAIRS = "https://apiv2.bitz.com/Market/tickerall";

    public BitZ() {
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
        final JSONObject dataJsonObject = jsonObject.getJSONObject("data");
        ticker.bid = dataJsonObject.getDouble("bidPrice");
        ticker.ask = dataJsonObject.getDouble("askPrice");
        ticker.high = dataJsonObject.getDouble("high");
        ticker.low = dataJsonObject.getDouble("low");
        ticker.vol = dataJsonObject.getDouble("volume");
        ticker.last = dataJsonObject.getDouble("now");

    }

    @Override
    protected void parseCurrencyPairsFromJsonObject(int requestId, JSONObject jsonObject, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = jsonObject.getJSONObject("data").names();
        for (int i = 0; i < jsonArray.length(); i++) {

            String currencyPairId = jsonArray.get(i).toString();

            String[] currencies = currencyPairId.split("_");
            if(currencies.length != 2)
                continue;
            pairs.add(new CurrencyPairInfo(currencies[0].toUpperCase(), currencies[1].toUpperCase(), currencyPairId));
        }
    }
}
