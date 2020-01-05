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

package walkingkooka.javautilcurrencyj2cl.java.util;

import org.junit.jupiter.api.Test;
import walkingkooka.ToStringTesting;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CurrencyTest implements ClassTesting<Currency>,
        ToStringTesting<Currency> {

    @Test
    public void testInstanceInvalidCodeFails() {
        assertThrows(IllegalArgumentException.class, () -> Currency.getInstance("123"));
    }

    @Test
    public void testGetInstanceAud() {
        check(java.util.Currency.getInstance("AUD"));
    }

    @Test
    public void testGetInstanceNz() {
        check(java.util.Currency.getInstance("NZD"));
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
