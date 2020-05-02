/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package walkingkooka.j2cl.java.util.currency;

import walkingkooka.collect.map.Maps;
import walkingkooka.collect.set.Sets;
import walkingkooka.j2cl.locale.LocaleAware;
import walkingkooka.text.CharSequences;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@LocaleAware
public final class Currency {

    public static Set<Currency> getAvailableCurrencies() {
        if (null == AVAILABLE_CURRENCIES) {
            final Set<Currency> all = Sets.ordered();
            all.addAll(CODE_TO_CURRENCY.values());
            AVAILABLE_CURRENCIES = Sets.readOnly(all);
        }
        return AVAILABLE_CURRENCIES;
    }

    private static Set<Currency> AVAILABLE_CURRENCIES;

    /**
     * Performs a simple lookup of the currency code to find a {@link Currency} throwing a {@link IllegalArgumentException}
     * when not found.
     */
    public static Currency getInstance(final String currencyCode) {
        Objects.requireNonNull(currencyCode, "currencyCode");

        final Currency currency = CODE_TO_CURRENCY.get(currencyCode);
        if (null == currency) {
            throw new IllegalArgumentException("Invalid currency code " + CharSequences.quote(currencyCode));
        }
        return currency;
    }

    /**
     * Performs a simple lookup of a {@link Locale} using its {@link Locale#toLanguageTag()} with a special case for nn-NO
     * throwing a {@link IllegalArgumentException} when not found.
     */
    public static Currency getInstance(final Locale locale) {
        checkLocale(locale);

        final String languageTag = locale.toLanguageTag();
        final Currency currency = LOCALE_TO_CURRENCY.get(languageTag.equals("nn-NO") ? "no-NO" : languageTag);
        if (null == currency) {
            throw new IllegalArgumentException("No currency available for " + CharSequences.quote(languageTag) + " " + locale);
        }
        return currency;
    }

    private final static Map<String, Currency> LOCALE_TO_CURRENCY = Maps.ordered();

    private static void checkLocale(final Locale locale) {
        Objects.requireNonNull(locale, "locale");
    }

    /**
     * Pre-populated table of code to {@link Currency}.
     */
    private final static Map<String, Currency> CODE_TO_CURRENCY = Maps.sorted();

    private final static String[] EMPTY_LOCALES = new String[0];

    /**
     * Requests the CurrencyProvider to provide records which will be used to create {@link Currency} singleton instances.
     */
    static {
        CurrencyProvider.register((provider) -> {
            new Currency(provider.currencyCode,
                    provider.defaultFractionDigits,
                    provider.numericCode,
                    provider.defaultSymbol,
                    locales(provider.locales),
                    Arrays.stream(provider.symbolToLocales).map(Currency::symbolToLocales).toArray(CurrencySymbolToLocales[]::new)
            );
        });
    }

    private static String[] locales(final String locales) {
        return locales.isEmpty() ?
                EMPTY_LOCALES :
                locales.split(",");
    }

    /**
     * Factory called by the generated code above to construct a {@link CurrencySymbolToLocales}.
     * Allows the generated code to use String literals holding locales rather than an array with many inlined new Locale calls.
     */
    private static CurrencySymbolToLocales symbolToLocales(final String symbolAndLocales) {
        final String[] tokens = symbolAndLocales.split(",");
        return CurrencySymbolToLocales.with(tokens[0],
                Arrays.stream(tokens, 1, tokens.length)
                        .map(Locale::forLanguageTag)
                        .toArray(Locale[]::new));
    }

    /**
     * Private ctor, called only by static initializer.
     */
    private Currency(final String currencyCode,
                     final int defaultFractionDigits,
                     final int numericCode,
                     final String defaultSymbol,
                     final String[] locales,
                     final CurrencySymbolToLocales... symbolToLocales) {
        super();
        this.currencyCode = currencyCode;
        this.defaultFractionDigits = defaultFractionDigits;
        this.numericCode = numericCode;
        this.defaultSymbol = defaultSymbol;
        this.symbolToLocales = symbolToLocales;

        CODE_TO_CURRENCY.put(currencyCode, this);

        for (final String locale : locales) {
            LOCALE_TO_CURRENCY.put(locale, this);
        }
    }

    /**
     * Default the currency symbol for the default {@link Locale}.
     */
    public String getSymbol() {
        return getSymbol(Locale.getDefault());
    }

    /**
     * If the Locale has a 2 character country code search for that, defaulting to the {@link #defaultSymbol}, otherwise
     * return the {@link #currencyCode}.
     */
    public String getSymbol(final Locale locale) {
        checkLocale(locale);

        return locale.toLanguageTag().equals("und") ?
                this.defaultSymbol :
                getSymbol0(locale);
    }

    private String getSymbol0(final Locale locale) {
        String symbol = this.defaultSymbol;

        for (final CurrencySymbolToLocales symbolAndLocales : this.symbolToLocales) {
            if (symbolAndLocales.contains(locale)) {
                symbol = symbolAndLocales.symbol;
                break;
            }
        }

        return symbol;
    }

    private final CurrencySymbolToLocales[] symbolToLocales;

    private final String defaultSymbol;

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    private final String currencyCode;

    public int getDefaultFractionDigits() {
        return this.defaultFractionDigits;
    }

    private final int defaultFractionDigits;

    public int getNumericCode() {
        return this.numericCode;
    }

    public String getNumericCodeAsString() {
        final int code = this.numericCode;
        return code < 100 ?
                code < 10 ?
                        "00" + code :
                        "0" + code :
                String.valueOf(code);
    }

    private final int numericCode;

    // Object...........................................................................................................

    @Override
    public String toString() {
        return this.currencyCode;
    }
}
