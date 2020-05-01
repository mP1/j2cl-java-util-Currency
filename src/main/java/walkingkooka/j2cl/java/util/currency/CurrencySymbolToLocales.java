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

import java.util.Arrays;
import java.util.Locale;

/**
 * Internal value holder that holds the symbol used by a many locales for a currency.
 */
final class CurrencySymbolToLocales {

    static CurrencySymbolToLocales with(final String symbol,
                                        final Locale[] locales) {
        return new CurrencySymbolToLocales(symbol, locales);
    }

    private CurrencySymbolToLocales(final String symbol,
                                    final Locale[] locales) {
        super();
        this.symbol = symbol;
        this.locales = locales;
    }

    boolean contains(final Locale locale) {
        final String languageTag = locale.toLanguageTag();

        return Arrays.stream(this.locales)
                .anyMatch(l -> l.toLanguageTag().equals(languageTag));
    }

    final String symbol;
    private final Locale[] locales;

    @Override
    public String toString() {
        return symbol + "=" + Arrays.toString(locales);
    }
}
