package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.tempusdominus;

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
 * The languages of Tempus Dominus bundled with the module, by code. A picker loads the translation
 * it needs.
 *
 * @author Joshua Godi
 */
public enum TempusDominusLocales {
    /** Arabic ({@code ar}). */
    AR("ar", TempusDominusClientBundle.INSTANCE.tdlocales_ar()),
    /** Arabic (Saudi Arabia) ({@code ar-SA}). */
    AR_SA("ar-SA", TempusDominusClientBundle.INSTANCE.tdlocales_ar_SA()),
    /** Catalan ({@code ca}). */
    CA("ca", TempusDominusClientBundle.INSTANCE.tdlocales_ca()),
    /** Czech ({@code cs}). */
    CS("cs", TempusDominusClientBundle.INSTANCE.tdlocales_cs()),
    /** German ({@code de}). */
    DE("de", TempusDominusClientBundle.INSTANCE.tdlocales_de()),
    /** Spanish ({@code es}). */
    ES("es", TempusDominusClientBundle.INSTANCE.tdlocales_es()),
    /** Finnish ({@code fi}). */
    FI("fi", TempusDominusClientBundle.INSTANCE.tdlocales_fi()),
    /** French ({@code fr}). */
    FR("fr", TempusDominusClientBundle.INSTANCE.tdlocales_fr()),
    /** Croatian ({@code hr}). */
    HR("hr", TempusDominusClientBundle.INSTANCE.tdlocales_hr()),
    /** Armenian ({@code hy}). Despite its name, it is Armenian. */
    HU("hy", TempusDominusClientBundle.INSTANCE.tdlocales_hy()),
    /** Italian ({@code it}). */
    IT("it", TempusDominusClientBundle.INSTANCE.tdlocales_it()),
    /** Dutch ({@code nl}). */
    NL("nl", TempusDominusClientBundle.INSTANCE.tdlocales_nl()),
    /** Polish ({@code pl}). */
    PL("pl", TempusDominusClientBundle.INSTANCE.tdlocales_pl()),
    /** Portuguese (Portugal) ({@code pt-PT}). */
    PT_PT("pt-PT", TempusDominusClientBundle.INSTANCE.tdlocales_pt_PT()),
    /** Romanian ({@code ro}). */
    RO("ro", TempusDominusClientBundle.INSTANCE.tdlocales_ro()),
    /** Russian ({@code ru}). */
    RU("ru", TempusDominusClientBundle.INSTANCE.tdlocales_ru()),
    /** Slovak ({@code sk}). */
    SK("sk", TempusDominusClientBundle.INSTANCE.tdlocales_sk()),
    /** Slovenian ({@code sl}). */
    SL("sl", TempusDominusClientBundle.INSTANCE.tdlocales_sl()),
    /** Serbian ({@code sr}). */
    SR("sr", TempusDominusClientBundle.INSTANCE.tdlocales_sr()),
    /** Serbian (Latin script) ({@code sr-latin}). */
    SR_LATIN("sr-latin", TempusDominusClientBundle.INSTANCE.tdlocales_sr_LATN()),
    /** Turkish ({@code tr}). */
    TR("tr", TempusDominusClientBundle.INSTANCE.tdlocales_tr()),
    /** Ukrainian ({@code uk}). */
    UK("uk", TempusDominusClientBundle.INSTANCE.tdlocales_uk()),
    /** Chinese (China) ({@code zh-CN}). */
    ZH_CN("zh-CN", TempusDominusClientBundle.INSTANCE.tdlocales_zh_CN()),
    /** Chinese (Hong Kong) ({@code zh-HK}). */
    ZH_HK("zh-HK", TempusDominusClientBundle.INSTANCE.tdlocales_zh_HK()),
    /** Chinese (Macao) ({@code zh-MO}). */
    ZH_MO("zh-MO", TempusDominusClientBundle.INSTANCE.tdlocales_zh_MO()),
    /** Chinese (Taiwan) ({@code zh-TW}). */
    ZH_TW("zh-TW", TempusDominusClientBundle.INSTANCE.tdlocales_zh_TW()),
    /** English ({@code en}). */
    EN("en", null); // Base language, don't need another file

    private final String code;
    private final TextResource js;

    TempusDominusLocales(final String code, final TextResource js) {
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
    public static TempusDominusLocales fromCode(final String code) {
        for (final TempusDominusLocales locale : values()) {
            if (locale.getCode().equals(code)) {
                return locale;
            }
        }

        return null;
    }

    /**
     * Returns the Tempus Dominus locale object for the language, injecting its script first if needed.
     *
     * @param lang the locale code, e.g. {@code "de"} or {@code "zh-CN"}
     * @return the locale object, or {@code null} for unknown codes and the built-in English locale
     */
    public static Object getLocaleAndLoadItIfNotLoaded(String lang) {
        TempusDominusLocales locale = fromCode(lang);
        if (locale != null && locale.getJs() != null) {
            Object localeObj = getLocale(lang);
            if (localeObj == null) {
                ScriptInjector.fromString(locale.getJs().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
            }

            return getLocale(lang);
        }

        return null;
    }

    /**
     * Returns the already loaded Tempus Dominus locale object for the language.
     *
     * @param lang the locale code, e.g. {@code "de"} or {@code "zh-CN"}
     * @return the locale object, or {@code null} if it is not loaded
     */
    public static Object getLocale(String lang) {
        final Object tempusDominus = Js.global().get("tempusDominus");
        final Object locales = tempusDominus == null ? null : Js.asPropertyMap(tempusDominus).get("locales");
        return locales == null ? null : Js.asPropertyMap(locales).get(toGlobalName(lang));
    }

    /**
     * Maps a locale code to the property name under {@code tempusDominus.locales}.
     */
    private static String toGlobalName(String lang) {
        return "sr-latin".equals(lang) ? "sr_Latn" : lang.replace('-', '_');
    }

}
