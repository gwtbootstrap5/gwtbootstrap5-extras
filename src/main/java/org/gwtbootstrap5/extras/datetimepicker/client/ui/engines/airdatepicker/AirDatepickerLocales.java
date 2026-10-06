package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.airdatepicker;

/*-
 * ==========================LICENSE_START===============================
 * GwtBootstrap5
 * ======================================================================
 * Copyright (C) 2023 - 2026 GwtBootstrap5
 * ======================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==========================LICENSE_END=================================
 */

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.TextResource;

import jsinterop.base.Js;

/**
 * The languages of Air Datepicker bundled with the module, by code. A picker loads the translation
 * it needs.
 *
 * @author Joshua Godi
 */
public enum AirDatepickerLocales {
    /** Arabic ({@code ar}). */
    AR("ar", AirDatepickerClientBundle.INSTANCE.adlocales_ar()),
    /** Bulgarian ({@code bg}). */
    BG("bg", AirDatepickerClientBundle.INSTANCE.adlocales_bg()),
    /** Brazilian Portuguese ({@code br}). */
    BR("br", AirDatepickerClientBundle.INSTANCE.adlocales_br()),
    /** Catalan ({@code ca}). */
    CA("ca", AirDatepickerClientBundle.INSTANCE.adlocales_ca()),
    /** Czech ({@code cs}). */
    CS("cs", AirDatepickerClientBundle.INSTANCE.adlocales_cs()),
    /** Danish ({@code da}). */
    DA("da", AirDatepickerClientBundle.INSTANCE.adlocales_da()),
    /** German ({@code de}). */
    DE("de", AirDatepickerClientBundle.INSTANCE.adlocales_de()),
    /** Greek ({@code el}). */
    EL("el", AirDatepickerClientBundle.INSTANCE.adlocales_el()),
    /** English ({@code en}). */
    EN("en", AirDatepickerClientBundle.INSTANCE.adlocales_en()),
    /** Spanish ({@code es}). */
    ES("es", AirDatepickerClientBundle.INSTANCE.adlocales_es()),
    /** Basque ({@code eu}). */
    EU("eu", AirDatepickerClientBundle.INSTANCE.adlocales_eu()),
    /** Finnish ({@code fi}). */
    FI("fi", AirDatepickerClientBundle.INSTANCE.adlocales_fi()),
    /** French ({@code fr}). */
    FR("fr", AirDatepickerClientBundle.INSTANCE.adlocales_fr()),
    /** Croatian ({@code hr}). */
    HR("hr", AirDatepickerClientBundle.INSTANCE.adlocales_hr()),
    /** Hungarian ({@code hu}). */
    HU("hu", AirDatepickerClientBundle.INSTANCE.adlocales_hu()),
    /** Indonesian ({@code id}). */
    ID("id", AirDatepickerClientBundle.INSTANCE.adlocales_id()),
    /** Italian ({@code it}). */
    IT("it", AirDatepickerClientBundle.INSTANCE.adlocales_it()),
    /** Japanese ({@code ja}). */
    JA("ja", AirDatepickerClientBundle.INSTANCE.adlocales_ja()),
    /** Korean ({@code ko}). */
    KO("ko", AirDatepickerClientBundle.INSTANCE.adlocales_ko()),
    /** Norwegian Bokmål ({@code nb}). */
    NB("nb", AirDatepickerClientBundle.INSTANCE.adlocales_nb()),
    /** Dutch ({@code nl}). */
    NL("nl", AirDatepickerClientBundle.INSTANCE.adlocales_nl()),
    /** Polish ({@code pl}). */
    PL("pl", AirDatepickerClientBundle.INSTANCE.adlocales_pl()),
    /** Portuguese ({@code pt}). */
    PT("pt", AirDatepickerClientBundle.INSTANCE.adlocales_pt()),
    /** Romanian ({@code ro}). */
    RO("ro", AirDatepickerClientBundle.INSTANCE.adlocales_ro()),
    /** Russian ({@code ru}). */
    RU("ru", AirDatepickerClientBundle.INSTANCE.adlocales_ru()),
    /** Sinhala ({@code si}). */
    SI("si", AirDatepickerClientBundle.INSTANCE.adlocales_si()),
    /** Slovak ({@code sk}). */
    SK("sk", AirDatepickerClientBundle.INSTANCE.adlocales_sk()),
    /** Slovenian ({@code sl}). */
    SL("sl", AirDatepickerClientBundle.INSTANCE.adlocales_sl()),
    /** Swedish ({@code sv}). Despite its name, it is Swedish. */
    SR("sv", AirDatepickerClientBundle.INSTANCE.adlocales_sv()),
    /** Thai ({@code th}). */
    TH("th", AirDatepickerClientBundle.INSTANCE.adlocales_th()),
    /** Turkish ({@code tr}). */
    TR("tr", AirDatepickerClientBundle.INSTANCE.adlocales_tr()),
    /** Ukrainian ({@code uk}). */
    UK("uk", AirDatepickerClientBundle.INSTANCE.adlocales_uk()),
    /** Chinese ({@code zh}). */
    ZH("zh", AirDatepickerClientBundle.INSTANCE.adlocales_zh());

    // Base language, don't need another file

    private final String code;
    private final TextResource js;

    AirDatepickerLocales(final String code, final TextResource js) {
        this.js = js;
        this.code = code;
    }

    /**
     * Returns the code of the language.
     *
     * @return the code, such as {@code "es"}
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the script of the translation.
     *
     * @return the script, or {@code null} for English, which is built in
     */
    public TextResource getJs() {
        return js;
    }

    /**
     * Returns the language of a code.
     *
     * @param code the code, such as {@code "es"}
     * @return the language, or {@code null} if it isn't bundled
     */
    public static AirDatepickerLocales fromCode(final String code) {
        for (final AirDatepickerLocales locale : values()) {
            if (locale.getCode().equals(code)) {
                return locale;
            }
        }

        return null;
    }

    /**
     * Returns the locale object of a language, loading its translation into the page first if needed.
     *
     * @param lang the code of the language
     * @return the locale object, or {@code null} if the language isn't bundled
     */
    public static Object getLocaleAndLoadItIfNotLoaded(String lang) {
        AirDatepickerLocales locale = fromCode(lang);
        if (locale != null) {
            Object localeObj = getLocale(lang);
            if (localeObj == null) {
                ScriptInjector.fromString(locale.getJs().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
            }

            return getLocale(lang);
        }

        return null;
    }

    private static Object getLocale(String lang) {
        return Js.global().get("ad_locale_" + lang);
    }

}
