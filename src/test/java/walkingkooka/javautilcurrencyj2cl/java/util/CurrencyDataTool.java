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

import walkingkooka.collect.map.Maps;
import walkingkooka.collect.set.Sets;
import walkingkooka.text.CharSequences;
import walkingkooka.text.Indentation;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printers;

import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A simple tool that generates each and every currency known to the system.
 */
public final class CurrencyDataTool {

    public static void main(final String[] args) {
        final IndentingPrinter printer = Printers.sysOut().indenting(Indentation.with("  "));
        new CurrencyDataTool(printer).printAll();
        printer.flush();
    }

    private CurrencyDataTool(final IndentingPrinter printer) {
        super();
        this.printer = printer;
    }

    private void printAll() {
        java.util.Currency.getAvailableCurrencies()
                .stream()
                .sorted(CurrencyDataTool::compare)
                .forEach(this::print);
    }

    private static int compare(final Currency left, final Currency right) {
        return left.getCurrencyCode().compareToIgnoreCase(right.getCurrencyCode());
    }

    private void print(final Currency currency) {
        final java.util.Currency currencyJre = java.util.Currency.getInstance(currency.getCurrencyCode());

        final Map<String, Set<Locale>> symbolToLocales = Maps.sorted();

        // gather all symbol to locales
        for (final Locale locale : Locale.getAvailableLocales()) {
            final String symbol = currencyJre.getSymbol(locale);
            if (symbol.isEmpty()) {
                continue;
            }

            Set<Locale> locales = symbolToLocales.get(symbol);
            if (null == locales) {
                locales = Sets.sorted(CurrencyDataTool::compareLocaleLanguageTag);
                symbolToLocales.put(symbol, locales);
            }
            locales.add(locale);
        }

        this.print();
        symbolToLocales.forEach((k, v) -> this.print("// " + k + "=" + v));

        String most = null;
        int mostCount = 0;
        for (final Entry<String, Set<Locale>> symbolAndLocales : symbolToLocales.entrySet()) {
            final int count = symbolAndLocales.getValue().size();
            if (count > mostCount) {
                mostCount = count;
                most = symbolAndLocales.getKey();
            }
        }

        //symbolToLocales.remove(most);

        // print the java definition...
        this.print("static {");
        this.indent();
        {
            this.print("new " + Currency.class.getSimpleName() + "(" + quote(currency.getCurrencyCode()) + ",");
            this.indent();
            {
                this.print(currency.getDefaultFractionDigits() + ",");
                this.print(currency.getNumericCode() + ",");
                this.print(quote(most) + (symbolToLocales.size() >= 1 ? "," : "")); // defaultSymbol

                final Set<Locale> locales = Sets.sorted(CurrencyDataTool::compareLocaleLanguageTag);

                for (final Locale possible : Locale.getAvailableLocales()) {
                    try {
                        final java.util.Currency possibleCurrency = java.util.Currency.getInstance(possible);
                        if (currency.getCurrencyCode().equals(possibleCurrency.getCurrencyCode())) {
                            locales.add(possible);
                        }
                    } catch (final Exception ignore) {

                    }
                }

                this.print("locales(" + quote(locales.stream()
                        .map(Locale::toLanguageTag)
                        .collect(Collectors.joining(","))) + "),");

                int i = 0;
                for (final Entry<String, Set<Locale>> symbolAndLocales : symbolToLocales.entrySet()) {
                    final String separator = (i < symbolToLocales.size() - 1) ?
                            "," :
                            "";
                    this.print("symbolToLocales(" + quote(symbolAndLocales.getValue()
                            .stream()
                            .map(Locale::toLanguageTag)
                            .collect(Collectors.joining(",", symbolAndLocales.getKey() + ",", ""))) + ")" + separator);

                    i++;
                }
            }
            this.outdent();
            this.print(");");
        }
        this.outdent();
        this.print("}");
    }

    private static int compareLocaleLanguageTag(final Locale left, final Locale right) {
        return left.toLanguageTag().compareTo(right.toLanguageTag());
    }

    private static CharSequence quote(final String text) {
        return CharSequences.quoteAndEscape(text);
    }

    private void indent() {
        this.printer.indent();
    }

    private void outdent() {
        this.printer.outdent();
    }

    private void print() {
        this.print("");
    }

    private void print(final String text) {
        this.printer.lineStart();
        this.printer.print(text);
    }

    private final IndentingPrinter printer;
}
