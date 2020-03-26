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

package walkingkooka.j2cl.java.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import walkingkooka.ToStringTesting;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.util.SystemProperty;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CurrencyTest implements ClassTesting<Currency>,
        ToStringTesting<Currency> {

    @BeforeAll
    public static void beforeJre9Check() {
        final String version = SystemProperty.JAVA_VERSION.requiredPropertyValue();
        final String[] versionComponents = version.split("\\.");
        final int majorVersion = Integer.parseInt(versionComponents[0]);
        assertEquals(9, majorVersion, () -> "Tests assume JRE 9.x because it makes assumptions based on the number Locales provided with that, version=" + version);
    }

    @Test
    public void testGetInstanceLocaleNullFails() {
        assertThrows(NullPointerException.class, () -> Currency.getInstance((Locale)null));
    }

    @Test
    public void testGetInstanceLocaleUnknownFails() {
        final Locale unknown = Locale.forLanguageTag("und");

        assertThrows(IllegalArgumentException.class, () -> java.util.Currency.getInstance(unknown));
        assertThrows(IllegalArgumentException.class, () -> Currency.getInstance(unknown));
    }

    @Test
    public void testGetInstanceLocaleEnAu() {
        final Locale locale = Locale.forLanguageTag("en-AU");
        this.check(Currency.getInstance(locale), java.util.Currency.getInstance(locale));
    }

    @Test
    public void testGetInstanceLocaleNnNo() {
        final Locale locale = Locale.forLanguageTag("nn-NO");
        assertEquals("NO", locale.getCountry(), "country");
        this.check(Currency.getInstance(locale), java.util.Currency.getInstance(locale));
    }

    @Test
    public void testGetInstanceLocaleNoNoNy() {
        final Locale locale = Locale.forLanguageTag("no-NO-NY");
        assertEquals("NO", locale.getCountry(), "country");
        this.check(Currency.getInstance(locale), java.util.Currency.getInstance(locale));
    }

    @Test
    public void testGetInstanceLocaleAllLocales() {
        for(final Locale locale : Locale.getAvailableLocales()) {
            // not all Locales have a currency, eg "ar" for arabic doesnt belong to a single country.

            java.util.Currency jre;
            try {
                jre = java.util.Currency.getInstance(locale);
            } catch (final IllegalArgumentException ignore) {
                jre = null;
            }

            if(null!=jre) {
                this.check(Currency.getInstance(locale), jre);
            }
        }
    }

    @Test
    public void testGetInstanceStringNullFails() {
        assertThrows(NullPointerException.class, () -> Currency.getInstance((String)null));
    }

    @Test
    public void testGetInstanceStringInvalidCodeFails() {
        assertThrows(IllegalArgumentException.class, () -> Currency.getInstance("123"));
    }

    @Test
    public void testGetInstanceAud() {
        this.check(java.util.Currency.getInstance("AUD"));
    }

    @Test
    public void testGetInstanceNz() {
        this.check(java.util.Currency.getInstance("NZD"));
    }

    @Test
    public void testGetInstanceSingletons() {
        final String countryCode = "AUD";
        assertSame(Currency.getInstance(countryCode), Currency.getInstance(countryCode));
    }

    @Test
    public void testAllCurrencies() {
        java.util.Currency.getAvailableCurrencies().forEach(this::check);
    }

    @Test
    public void testAllCurrencies2() {
        Currency.getAvailableCurrencies().forEach(this::check);
    }

    @Test
    public void testGetSymbolAud_ar_jo() {
        this.checkLocale(Currency.getInstance("AUD"),
                java.util.Currency.getInstance("AUD"),
                Locale.forLanguageTag("ar-JO"));
    }

    @Test
    public void testGetSymbolAud_nn() {
        this.checkLocale(Currency.getInstance("AUD"),
                java.util.Currency.getInstance("AUD"),
                Locale.forLanguageTag("nn"));
    }

    @Test
    public void testGetSymbolNOK_nn_NO() {
        this.checkLocale(Currency.getInstance("NOK"),
                java.util.Currency.getInstance("NOK"),
                Locale.forLanguageTag("nn-NO"));
    }

    @Test
    public void testGetSymbolUnknownLocale() {
        this.checkLocale(Currency.getInstance("AUD"),
                java.util.Currency.getInstance("AUD"),
                Locale.forLanguageTag("qrstuv"));
    }

    private void check(final Currency currency) {
        this.check(currency, java.util.Currency.getInstance(currency.getCurrencyCode()));
    }

    private void check(final java.util.Currency currency) {
        this.check(Currency.getInstance(currency.getCurrencyCode()), currency);
    }

    private void check(final Currency emulated,
                       final java.util.Currency jre) {
        assertEquals(jre.getCurrencyCode(), emulated.getCurrencyCode(), "currencyCode");
        assertEquals(jre.getDefaultFractionDigits(), emulated.getDefaultFractionDigits(), "defaultFractionDigits");
        assertEquals(jre.getNumericCode(), emulated.getNumericCode(), "numericCode");
        assertEquals(jre.getNumericCodeAsString(), emulated.getNumericCodeAsString(), "numericCodeAsString");

        for (final Locale locale : Locale.getAvailableLocales()) {
            this.checkLocale(emulated, jre, locale);
        }
    }

    private void checkLocale(final Currency emulated,
                             final java.util.Currency jre,
                             final Locale locale) {
        boolean invalidLocale;
        try {
            jre.getSymbol(locale);
            invalidLocale = false;
        } catch (final Exception e) {
            invalidLocale = true;
        }

        if (invalidLocale) {
            assertThrows(NullPointerException.class, () -> emulated.getSymbol(locale));
        } else {
            assertEquals(jre.getSymbol(locale),
                    emulated.getSymbol(locale),
                    () -> jre + " getSymbol for locale.languageTag: " + locale.toLanguageTag() + " Locale.language: " + locale.getLanguage() + " Locale.country: " + locale.getCountry() + " Locale.toString " + locale);
        }
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        final String countryCode = "AUD";
        this.toStringAndCheck(Currency.getInstance(countryCode), countryCode);
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<Currency> type() {
        return Currency.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
