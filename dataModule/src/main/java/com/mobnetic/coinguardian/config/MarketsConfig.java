package com.mobnetic.coinguardian.config;

import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.market.*;
import com.mobnetic.coinguardian.model.market.example.CryptoBridge;

import java.util.LinkedHashMap;

public class MarketsConfig {

	public final static LinkedHashMap<String, Market> MARKETS = new LinkedHashMap<String, Market>();
	private final static void addMarket(Market market) {
		MarketsConfig.MARKETS.put(market.key, market);
	}

	static {
		addMarket(new Bitstamp());
		addMarket(new Btcchina());
		addMarket(new Btce());
		addMarket(new Bitcurex());
		addMarket(new Mercado());
		addMarket(new Kraken());
		addMarket(new Bitfinex());
		addMarket(new Okcoin());
		addMarket(new Cryptsy());
		addMarket(new Coinbase());
		addMarket(new Vircurex());
		addMarket(new Fxbtc());
		addMarket(new Bter());
		addMarket(new Justcoin());
		addMarket(new Btcturk());
		addMarket(new Coinse());
		addMarket(new Campbx());
		addMarket(new BitcoinAverage());
		addMarket(new TheRock());
		addMarket(new CexIO());
		addMarket(new Virtex());
		addMarket(new Huobi());
		addMarket(new VaultOfSatoshi());
		addMarket(new CoinMarketIO());
		addMarket(new McxNOW());
		addMarket(new CryptoTrade());
		addMarket(new MintPal());
		addMarket(new CoinJar());
		addMarket(new Poloniex());
		addMarket(new Winkdex());
		addMarket(new Anxpro());
		addMarket(new BitX());
		addMarket(new CCex());
		addMarket(new BitMarketPL());
		addMarket(new Bitorado());
		addMarket(new CryptoRush());
		addMarket(new CoinDesk());
		addMarket(new Koinim());
		addMarket(new FybSE());
		addMarket(new FybSG());
		addMarket(new Prelude());
		addMarket(new BitKonan());
		addMarket(new BitTrex());
		addMarket(new Comkort());
		addMarket(new Bit2c());
		addMarket(new CryptoAltex());
		addMarket(new BtcMarkets());
		addMarket(new SwissCex());
		addMarket(new Bleutrade());
		addMarket(new ShareXcoin());
		addMarket(new Unisend());
		addMarket(new BitcoinVenezuela());
		addMarket(new Korbit());
		addMarket(new CoinTree());
		addMarket(new Cryptonit());
		addMarket(new LakeBTC());
		addMarket(new BitMaszyna());
		addMarket(new BitCore());
		addMarket(new Zaydo());
		addMarket(new AllCoin());
		addMarket(new Ripio());
		addMarket(new DolarBlueNet());
		addMarket(new Basebit());
		addMarket(new CoinSwap());
		addMarket(new Paymium());
		addMarket(new Bitso());
		addMarket(new Cryptoine());
		addMarket(new BitcoinToYou());
		addMarket(new BitexLa());
		addMarket(new ItBit());
		addMarket(new BitcoinCoId());
		addMarket(new HitBtc());
		addMarket(new CleverCoin());
		addMarket(new BitBay());
		addMarket(new FoxBit());
		addMarket(new QuadrigaCX());
		addMarket(new CoinMateIO());
		addMarket(new BitMarket24PL());
		addMarket(new Buttercoin());
		addMarket(new CoinTraderNet());
		addMarket(new LocalBitcoins());
		addMarket(new Cryptopia());
		addMarket(new Igot());
		addMarket(new SevenNineSix());
		addMarket(new Mexbt());
		addMarket(new Vaultoro());
		addMarket(new BitxCom());
		addMarket(new BtcBox());
		addMarket(new BtcXIndia());
		addMarket(new Uphold());
		addMarket(new YoBit());
		addMarket(new ShapeShift());
		addMarket(new BitoEX());
		addMarket(new OKCoinFutures());
//		addMarket(new FoscEx());
		addMarket(new CoinSecure());
		addMarket(new Dashcurex());
		addMarket(new Quoine());
		addMarket(new Livecoin());
		addMarket(new Gemini());
		addMarket(new Coinapult());
		addMarket(new Btc38());
		addMarket(new ETHEXIndia());
		addMarket(new GateCoin());
		addMarket(new Liqui());
		addMarket(new ChileBit());
		addMarket(new SurBitcoin());
		addMarket(new VBtc());
		addMarket(new Urdubit());
		addMarket(new NegocieCoins());
		addMarket(new BitMEX());
		addMarket(new BitFlyer());
		addMarket(new BitFlyerFX());
		addMarket(new Coinone());
		addMarket(new Bithumb());
		addMarket(new Coinsph());
		addMarket(new Bl3p());
		addMarket(new SurBtc());
		addMarket(new CoinFloor());
		addMarket(new Lykke());
		addMarket(new Coinnest());
		addMarket(new Braziliex());
		addMarket(new Abucoins());
		addMarket(new Zebpay());
		addMarket(new Paribu());
		addMarket(new SatoshiTango());
		addMarket(new Koinex());
//		addMarket(new Unocoin());
		addMarket(new BlinkTrade());
		addMarket(new Exmo());
		addMarket(new Binance());
		addMarket(new Kucoin());

		addMarket(new CryptoBridge());
		addMarket(new BtcAlpha());
		addMarket(new Graviex());
		addMarket(new StockExchange());
		addMarket(new Crex24());

		addMarket(new TrocaNinja());
		addMarket(new Southxchange());
		addMarket(new Coinbene());
		addMarket(new CoinExchange());
		addMarket(new BitZ());

		addMarket(new Idax());
		addMarket(new Okex());

		addMarket(new Bbx());
	}
}
