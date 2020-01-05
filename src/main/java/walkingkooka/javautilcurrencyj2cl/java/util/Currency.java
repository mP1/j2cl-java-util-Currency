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
import walkingkooka.javautillocalej2cl.java.util.Locale;
import walkingkooka.text.CharSequences;

import java.util.Map;
import java.util.Set;

// currencyCode = defaultFractionCode, defaultFractionDigits, numericCode, countries
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

    public static Currency getInstance(final String currencyCode) {
        final Currency currency = CODE_TO_CURRENCY.get(currencyCode);
        if (null == currency) {
            throw new IllegalArgumentException("Invalid currency code " + CharSequences.quote(currencyCode));
        }
        return currency;
    }

    public static Currency getInstance(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    /**
     * Pre-populated table of code to {@link Currency}.
     */
    private final static Map<String, Currency> CODE_TO_CURRENCY = Maps.sorted();

    static {
        new Currency("ADP", 0, 20);
        new Currency("AED", 2, 784);
        new Currency("AFA", 2, 4);
        new Currency("AFN", 2, 971);
        new Currency("ALL", 2, 8);
        new Currency("AMD", 2, 51);
        new Currency("ANG", 2, 532);
        new Currency("AOA", 2, 973);
        new Currency("ARS", 2, 32);
        new Currency("ATS", 2, 40);
        new Currency("AUD", 2, 36);
        new Currency("AWG", 2, 533);
        new Currency("AYM", 2, 945);
        new Currency("AZM", 2, 31);
        new Currency("AZN", 2, 944);
        new Currency("BAM", 2, 977);
        new Currency("BBD", 2, 52);
        new Currency("BDT", 2, 50);
        new Currency("BEF", 0, 56);
        new Currency("BGL", 2, 100);
        new Currency("BGN", 2, 975);
        new Currency("BHD", 3, 48);
        new Currency("BIF", 0, 108);
        new Currency("BMD", 2, 60);
        new Currency("BND", 2, 96);
        new Currency("BOB", 2, 68);
        new Currency("BOV", 2, 984);
        new Currency("BRL", 2, 986);
        new Currency("BSD", 2, 44);
        new Currency("BTN", 2, 64);
        new Currency("BWP", 2, 72);
        new Currency("BYB", 0, 112);
        new Currency("BYN", 2, 933);
        new Currency("BYR", 0, 974);
        new Currency("BZD", 2, 84);
        new Currency("CAD", 2, 124);
        new Currency("CDF", 2, 976);
        new Currency("CHE", 2, 947);
        new Currency("CHF", 2, 756);
        new Currency("CHW", 2, 948);
        new Currency("CLF", 4, 990);
        new Currency("CLP", 0, 152);
        new Currency("CNY", 2, 156);
        new Currency("COP", 2, 170);
        new Currency("COU", 2, 970);
        new Currency("CRC", 2, 188);
        new Currency("CSD", 2, 891);
        new Currency("CUC", 2, 931);
        new Currency("CUP", 2, 192);
        new Currency("CVE", 2, 132);
        new Currency("CYP", 2, 196);
        new Currency("CZK", 2, 203);
        new Currency("DEM", 2, 276);
        new Currency("DJF", 0, 262);
        new Currency("DKK", 2, 208);
        new Currency("DOP", 2, 214);
        new Currency("DZD", 2, 12);
        new Currency("EEK", 2, 233);
        new Currency("EGP", 2, 818);
        new Currency("ERN", 2, 232);
        new Currency("ESP", 0, 724);
        new Currency("ETB", 2, 230);
        new Currency("EUR", 2, 978);
        new Currency("FIM", 2, 246);
        new Currency("FJD", 2, 242);
        new Currency("FKP", 2, 238);
        new Currency("FRF", 2, 250);
        new Currency("GBP", 2, 826);
        new Currency("GEL", 2, 981);
        new Currency("GHC", 2, 288);
        new Currency("GHS", 2, 936);
        new Currency("GIP", 2, 292);
        new Currency("GMD", 2, 270);
        new Currency("GNF", 0, 324);
        new Currency("GRD", 0, 300);
        new Currency("GTQ", 2, 320);
        new Currency("GWP", 2, 624);
        new Currency("GYD", 2, 328);
        new Currency("HKD", 2, 344);
        new Currency("HNL", 2, 340);
        new Currency("HRK", 2, 191);
        new Currency("HTG", 2, 332);
        new Currency("HUF", 2, 348);
        new Currency("IDR", 2, 360);
        new Currency("IEP", 2, 372);
        new Currency("ILS", 2, 376);
        new Currency("INR", 2, 356);
        new Currency("IQD", 3, 368);
        new Currency("IRR", 2, 364);
        new Currency("ISK", 0, 352);
        new Currency("ITL", 0, 380);
        new Currency("JMD", 2, 388);
        new Currency("JOD", 3, 400);
        new Currency("JPY", 0, 392);
        new Currency("KES", 2, 404);
        new Currency("KGS", 2, 417);
        new Currency("KHR", 2, 116);
        new Currency("KMF", 0, 174);
        new Currency("KPW", 2, 408);
        new Currency("KRW", 0, 410);
        new Currency("KWD", 3, 414);
        new Currency("KYD", 2, 136);
        new Currency("KZT", 2, 398);
        new Currency("LAK", 2, 418);
        new Currency("LBP", 2, 422);
        new Currency("LKR", 2, 144);
        new Currency("LRD", 2, 430);
        new Currency("LSL", 2, 426);
        new Currency("LTL", 2, 440);
        new Currency("LUF", 0, 442);
        new Currency("LVL", 2, 428);
        new Currency("LYD", 3, 434);
        new Currency("MAD", 2, 504);
        new Currency("MDL", 2, 498);
        new Currency("MGA", 2, 969);
        new Currency("MGF", 0, 450);
        new Currency("MKD", 2, 807);
        new Currency("MMK", 2, 104);
        new Currency("MNT", 2, 496);
        new Currency("MOP", 2, 446);
        new Currency("MRO", 2, 478);
        new Currency("MTL", 2, 470);
        new Currency("MUR", 2, 480);
        new Currency("MVR", 2, 462);
        new Currency("MWK", 2, 454);
        new Currency("MXN", 2, 484);
        new Currency("MXV", 2, 979);
        new Currency("MYR", 2, 458);
        new Currency("MZM", 2, 508);
        new Currency("MZN", 2, 943);
        new Currency("NAD", 2, 516);
        new Currency("NGN", 2, 566);
        new Currency("NIO", 2, 558);
        new Currency("NLG", 2, 528);
        new Currency("NOK", 2, 578);
        new Currency("NPR", 2, 524);
        new Currency("NZD", 2, 554);
        new Currency("OMR", 3, 512);
        new Currency("PAB", 2, 590);
        new Currency("PEN", 2, 604);
        new Currency("PGK", 2, 598);
        new Currency("PHP", 2, 608);
        new Currency("PKR", 2, 586);
        new Currency("PLN", 2, 985);
        new Currency("PTE", 0, 620);
        new Currency("PYG", 0, 600);
        new Currency("QAR", 2, 634);
        new Currency("ROL", 2, 946);
        new Currency("RON", 2, 946);
        new Currency("RSD", 2, 941);
        new Currency("RUB", 2, 643);
        new Currency("RUR", 2, 810);
        new Currency("RWF", 0, 646);
        new Currency("SAR", 2, 682);
        new Currency("SBD", 2, 90);
        new Currency("SCR", 2, 690);
        new Currency("SDD", 2, 736);
        new Currency("SDG", 2, 938);
        new Currency("SEK", 2, 752);
        new Currency("SGD", 2, 702);
        new Currency("SHP", 2, 654);
        new Currency("SIT", 2, 705);
        new Currency("SKK", 2, 703);
        new Currency("SLL", 2, 694);
        new Currency("SOS", 2, 706);
        new Currency("SRD", 2, 968);
        new Currency("SRG", 2, 740);
        new Currency("SSP", 2, 728);
        new Currency("STD", 2, 678);
        new Currency("SVC", 2, 222);
        new Currency("SYP", 2, 760);
        new Currency("SZL", 2, 748);
        new Currency("THB", 2, 764);
        new Currency("TJS", 2, 972);
        new Currency("TMM", 2, 795);
        new Currency("TMT", 2, 934);
        new Currency("TND", 3, 788);
        new Currency("TOP", 2, 776);
        new Currency("TPE", 0, 626);
        new Currency("TRL", 0, 792);
        new Currency("TRY", 2, 949);
        new Currency("TTD", 2, 780);
        new Currency("TWD", 2, 901);
        new Currency("TZS", 2, 834);
        new Currency("UAH", 2, 980);
        new Currency("UGX", 0, 800);
        new Currency("USD", 2, 840);
        new Currency("USN", 2, 997);
        new Currency("USS", 2, 998);
        new Currency("UYI", 0, 940);
        new Currency("UYU", 2, 858);
        new Currency("UZS", 2, 860);
        new Currency("VEB", 2, 862);
        new Currency("VEF", 2, 937);
        new Currency("VND", 0, 704);
        new Currency("VUV", 0, 548);
        new Currency("WST", 2, 882);
        new Currency("XAF", 0, 950);
        new Currency("XAG", -1, 961);
        new Currency("XAU", -1, 959);
        new Currency("XBA", -1, 955);
        new Currency("XBB", -1, 956);
        new Currency("XBC", -1, 957);
        new Currency("XBD", -1, 958);
        new Currency("XCD", 2, 951);
        new Currency("XDR", -1, 960);
        new Currency("XFO", -1, 0);
        new Currency("XFU", -1, 0);
        new Currency("XOF", 0, 952);
        new Currency("XPD", -1, 964);
        new Currency("XPF", 0, 953);
        new Currency("XPT", -1, 962);
        new Currency("XSU", -1, 994);
        new Currency("XTS", -1, 963);
        new Currency("XUA", -1, 965);
        new Currency("XXX", -1, 999);
        new Currency("YER", 2, 886);
        new Currency("YUM", 2, 891);
        new Currency("ZAR", 2, 710);
        new Currency("ZMK", 2, 894);
        new Currency("ZMW", 2, 967);
        new Currency("ZWD", 2, 716);
        new Currency("ZWL", 2, 932);
        new Currency("ZWN", 2, 942);
        new Currency("ZWR", 2, 935);
    }

    /**
     * Private ctor, called only by static initializer.
     */
    private Currency(final String currencyCode,
                     final int defaultFractionDigits,
                     final int numericCode,
                     final String... countries) {
        super();
        this.currencyCode = currencyCode;
        this.defaultFractionDigits = defaultFractionDigits;
        this.numericCode = numericCode;
        this.countries = countries;

        CODE_TO_CURRENCY.put(currencyCode, this);
    }

    /**
     * Default the currency symbol for the default {@link Locale}.
     */
    public String getSymbol() {
        return getSymbol(Locale.getDefault());
    }

    public String getSymbol(final Locale locale) {
        final String country = locale.getCountry();
        if (CharSequences.isNullOrEmpty(country)) {
            throw new NullPointerException("Locale missing country");
        }

        if (country.length() != 2) {
            throw new IllegalArgumentException("Locale country code " + CharSequences.quote(country) + " must be 2 characters");
        }

        final String symbol = null;
        if (symbol != null) {
            return symbol;
        }

        // use currency code as symbol of last resort
        return currencyCode;
    }

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

    private final String[] countries;

    // Object...........................................................................................................

    @Override
    public String toString() {
        return this.currencyCode;
    }
}
