package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Bbx extends Market {

    public final static String NAME = "Bbx";
    public final static String TTS_NAME = "Bbx";

    private final static String URL = "https://api.bbxapp.vip/v1/ifmarket/v2/spotTickers?stockCode=%1$s";
    private final static String URL_CURRENCY_PAIRS = "https://api.bbxapp.vip/v1/ifglobal/global";

    public Bbx() {
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
        final JSONObject dataJsonObject = jsonObject.getJSONObject("data").getJSONArray("tickers").getJSONObject(0);
        ticker.low = dataJsonObject.getDouble("low");
        ticker.high = dataJsonObject.getDouble("high");
        ticker.vol = dataJsonObject.getDouble("volume");
        ticker.last = dataJsonObject.getDouble("last_price");
        ticker.bid = dataJsonObject.getDouble("low");
        ticker.ask = dataJsonObject.getDouble("high");
    }

    @Override
    protected void parseCurrencyPairsFromJsonObject(int requestId, JSONObject jsonObject, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("stocks");
        for (int i = 0; i < jsonArray.length(); i++) {

            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);
            final String currencyBase = pairJsonObject.getString("base_coin");
            final String currencyCounter = pairJsonObject.getString("quote_coin");
            final String currencyPairId = pairJsonObject.getString("name");

            pairs.add(new CurrencyPairInfo(currencyBase, currencyCounter, currencyPairId));
        }
    }
}
