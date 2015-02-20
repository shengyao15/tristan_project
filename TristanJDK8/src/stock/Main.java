package stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * http://www.it165.net/pro/html/201411/26188.html
 * @author tristan
 *
 */
public class Main {
	public static void main(String[] args) {
//		BigDecimal HUNDRED = new BigDecimal("100");
//		System.out.println("Stocks priced over $100 are "
//				+ Tickers.symbols.stream()
//						.filter(symbol -> YahooFinance.getPrice(symbol).compareTo(HUNDRED) > 0).sorted()
//						.collect(Collectors.joining(", ")));
		
		StockUtil.findHighPriced(Tickers.symbols.parallelStream());
	}
}

class StockInfo {
	public String ticker;
	public BigDecimal price;

	public StockInfo(String symbol, BigDecimal thePrice) {
		ticker = symbol;
		price = thePrice;
	}

	public String toString() {
		return String.format("ticker: %s price: %g", ticker, price);
	}

}

class StockUtil {
	public static StockInfo getPrice(String ticker) {
		return new StockInfo(ticker, YahooFinance.getPrice(ticker));
	}

	public static Predicate<StockInfo> isPriceLessThan(int price) {
		return stockInfo -> stockInfo.price.compareTo(BigDecimal.valueOf(price)) < 0;
	}

	public static StockInfo pickHigh(StockInfo stockInfo1, StockInfo stockInfo2) {
		return stockInfo1.price.compareTo(stockInfo2.price) > 0 ? stockInfo1 : stockInfo2;
	}

	public static void findHighPriced(Stream<String> symbols) {
		StockInfo highPriced = symbols.map(StockUtil::getPrice).filter(StockUtil.isPriceLessThan(500))
				.reduce(StockUtil::pickHigh).get();

		System.out.println("High priced under $500 is " + highPriced);
	}
}

class Tickers {
	public static List<String> symbols = Arrays.asList("AMD", "HPQ", "IBM", "TXN", "VMW", "XRX",
			"AAPL", "ADBE", "AMZN", "CRAY", "CSCO", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN",
			"YHOO");
}

class YahooFinance {
	public static BigDecimal getPrice(String ticker) {
		try {
			URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String data = reader.lines().skip(1).findFirst().get();
			String[] dataItems = data.split(",");
			return new BigDecimal(dataItems[dataItems.length - 1]);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}