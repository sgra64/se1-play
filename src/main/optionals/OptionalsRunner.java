package optionals;

import java.util.Map;
import java.util.Optional;

import runtimeSE.Runner;
import runtimeSE.RuntimeSE;
import runtimeSE.Runner.Accessors;


/**
 * Demo that shows the use of <i>Optional&lt;T&gt;</i> in Java.
 * 
 * <i>Optional&lt;T&gt;</i> is a container class that contains one element
 * or is empty, @see
 * <a href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Optional.html"><i>Optional&lt;T&gt;</i></a>
 */
@Accessors(priority=1)
public class OptionalsRunner implements Runner {

    class Articles {
        private final Map<String, Integer> prices;

        Articles(Map<String, Integer> prices) {
            this.prices = prices;
        }

        Integer getPrice(String article) {
            return prices.get(article);
        }

        Optional<Integer> getPriceOpt(String article) {
            return Optional.ofNullable(prices.get(article));
        }
    }

    /*
     * Prices are in Euro-Cent
     */
    private final Articles articles = new Articles(
        Map.of("Tasse", 999, "Kanne", 1999, "Becher", 749)
    );

    /**
     * Method invoked by the runtime. Application code starts here.
     * @param runtime reference to the runtime singleton instance ({@link RuntimeSE}).
     * @param args arguments passed from the command line.
     */
    @Override
    public void run(RuntimeSE runtime, String[] args) {
        // 
        System.out.println(String.format("Hello, %s (optionals)",
            runtime.properties().getProperty("application.name", "")
        ));
        // 
        for(String article : args) {
            lookupBuggy(article);
            // lookupFixedOldStyle(article);
            // lookupOptional(article);
            // lookupOptionalFunctional(article);
        }
    }

    /**
     * Buggy code that throws NullPointerException if Article is not found.
     * NullPointerException occurs when Integer result returned from getPrice()
     * is attemted to cast to int.
     * 
     * Throws Exception in thread "main" java.lang.NullPointerException:
     * Cannot invoke "java.lang.Integer.intValue()" because the return value of
     * "Articles.getPrice(String)" is null.
     * @param article article to look up
     */
    void lookupBuggy(String article) {
        // 
        int price = articles.getPrice(article);
        // 
        System.out.println(String.format("Der Preis für '%s' ist: %d \u20ac-Cent.", article, price));
    }

    /**
     * Correct Java code: test for null (old 2000'er Java code).
     * @param article article to look up
     */
    void lookupFixedOldStyle(String article) {
        // 
        var priceInt = articles.getPrice(article);
        // 
        if(priceInt != null) { // must test for null, or crash with Nullpointer Exception
            System.out.println(String.format("Der Preis für '%s' ist: %d \u20ac-Cent", article, priceInt));
        } else {
            System.out.println(String.format("Der Artikel '%s' konnte nicht gefunden werden.", article));
        }
    }

    /**
     * Correct code using Optional methods in if-statement (2015'er style).
     * @param article article to look up
     */
    void lookupOptional(String article) {
        // 
        var priceOpt = articles.getPriceOpt(article);
        // 
        if(priceOpt.isPresent()) {
            System.out.println(String.format("Der Preis für '%s' ist: %d \u20ac-Cent", article, priceOpt.get()));
        } else {
            System.out.println(String.format("Der Artikel '%s' konnte nicht gefunden werden.", article));
        }
    }

    /**
     * Modern code using Optional's functional methods (2020'er code).
     * @param article article to look up
     */
    void lookupOptionalFunctional(String article) {
        // 
        String answer = articles.getPriceOpt(article)
            .map(p -> String.format("Der Preis für '%s' ist: %d \u20ac-Cent", article, p))
            .orElse(String.format("Der Artikel '%s' konnte nicht gefunden werden", article));
        // 
        System.out.println(answer);
    }
}
