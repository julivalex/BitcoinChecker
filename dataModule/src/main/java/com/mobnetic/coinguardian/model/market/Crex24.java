package com.mobnetic.coinguardian.model.market;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.CurrencyPairInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

public class Crex24 extends Market {

    private final static String NAME = "Crex24";
    private final static String TTS_NAME = "Crex24";
    private final static String URL = "https://api.crex24.com/CryptoExchangeService/BotPublic/ReturnTicker?request=[NamePairs=%1$s]";
    private final static String URL_CURRENCY_PAIRS = "https://api.crex24.com/CryptoExchangeService/BotPublic/ReturnTicker";

    public Crex24() {
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
        final JSONObject dataJsonObject = jsonObject.getJSONArray("Tickers").getJSONObject(0);
        ticker.vol = dataJsonObject.getDouble("BaseVolume");
        ticker.high = dataJsonObject.getDouble("HighPrice");
        ticker.ask = dataJsonObject.getDouble("LowPrice");
        ticker.last = dataJsonObject.getDouble("Last");
    }

    @Override
    protected void parseCurrencyPairsFromJsonObject(int requestId, JSONObject jsonObject, List<CurrencyPairInfo> pairs) throws Exception {
        JSONArray jsonArray = jsonObject.getJSONArray("Tickers");
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject pairJsonObject = jsonArray.getJSONObject(i);

            String currencyPairId = pairJsonObject.getString("PairName");
            String[] currencies = currencyPairId.split("_");
            if(currencies.length != 2)
                continue;
            pairs.add(new CurrencyPairInfo(currencies[0], currencies[1], currencyPairId));
        }
    }
}
