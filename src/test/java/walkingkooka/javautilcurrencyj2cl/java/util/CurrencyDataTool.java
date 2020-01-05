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

import walkingkooka.text.CharSequences;
import walkingkooka.text.Indentation;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printers;

import java.util.Currency;

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
        // new Currency("currencyCode", 1, 2, "Fr", "De", "Be");
        this.printer.lineStart();
        this.printer.print("new " + Currency.class.getSimpleName() + "(" +
                quote(currency.getCurrencyCode()) + "," +
                currency.getDefaultFractionDigits() + "," +
                currency.getNumericCode() +
                ");");
    }

    private static CharSequence quote(final String text) {
        return CharSequences.quoteAndEscape(text);
    }

    private final IndentingPrinter printer;
}
