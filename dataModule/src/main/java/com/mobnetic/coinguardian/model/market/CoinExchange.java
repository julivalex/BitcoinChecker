package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CoinExchange extends Market {

    private final static String NAME = "CoinExchange";
    private final static String TTS_NAME = "CoinExchange";
    private final static String URL = "https://www.coinexchange.io/api/v1/getmarketsummary?market_id=%1$s";
    private final static String URL_CURRENCY_PAIRS = "https://www.coinexchange.io/api/v1/getmarkets";


    public CoinExchange() {
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
        final JSONObject dataJsonObject = jsonObject.getJSONObject("result");
        ticker.last = dataJsonObject.getDouble("LastPrice");
        ticker.bid = dataJsonObject.getDouble("BidPrice");
        ticker.vol = dataJsonObject.getDouble("Volume");
        ticker.ask = dataJsonObject.getDouble("AskPrice");
        ticker.high = dataJsonObject.getDouble("HighPrice");
        ticker.low = dataJsonObject.getDouble("LowPrice");

    }

    @Override
    protected void parseCurrencyPairsFromJsonObject(int requestId, JSONObject jsonObject, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);

            String currencyPairId = pairJsonObject.getString("MarketID");
            String currencyBase = pairJsonObject.getString("BaseCurrencyCode");
            String currencyCounter = pairJsonObject.getString("MarketAssetCode");

            pairs.add(new CurrencyPairInfo(currencyBase, currencyCounter, currencyPairId));
        }
    }
}
