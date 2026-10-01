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
import org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.airdatepicker.AirDatepickerClientBundle;

import jsinterop.base.Js;

/**
 * @author Joshua Godi
 */
public enum TempusDominusLocales {
    AR("ar", TempusDominusClientBundle.INSTANCE.tdlocales_ar()),
    AR_SA("ar-SA", TempusDominusClientBundle.INSTANCE.tdlocales_ar_SA()),
    CA("ca", TempusDominusClientBundle.INSTANCE.tdlocales_ca()),
    CS("cs", TempusDominusClientBundle.INSTANCE.tdlocales_cs()),
    DE("de", TempusDominusClientBundle.INSTANCE.tdlocales_de()),
    ES("es", TempusDominusClientBundle.INSTANCE.tdlocales_es()),
    FI("fi", TempusDominusClientBundle.INSTANCE.tdlocales_fi()),
    FR("fr", TempusDominusClientBundle.INSTANCE.tdlocales_fr()),
    HR("hr", TempusDominusClientBundle.INSTANCE.tdlocales_hr()),
    HU("hy", TempusDominusClientBundle.INSTANCE.tdlocales_hy()),
    IT("it", TempusDominusClientBundle.INSTANCE.tdlocales_it()),
    NL("nl", TempusDominusClientBundle.INSTANCE.tdlocales_nl()),
    PL("pl", TempusDominusClientBundle.INSTANCE.tdlocales_pl()),
    PT_PT("pt-PT", TempusDominusClientBundle.INSTANCE.tdlocales_pt_PT()),
    RO("ro", TempusDominusClientBundle.INSTANCE.tdlocales_ro()),
    RU("ru", TempusDominusClientBundle.INSTANCE.tdlocales_ru()),
    SK("sk", TempusDominusClientBundle.INSTANCE.tdlocales_sk()),
    SL("sl", TempusDominusClientBundle.INSTANCE.tdlocales_sl()),
    SR("sr", TempusDominusClientBundle.INSTANCE.tdlocales_sr()),
    SR_LATIN("sr-latin", TempusDominusClientBundle.INSTANCE.tdlocales_sr_LATN()),
    TR("tr", TempusDominusClientBundle.INSTANCE.tdlocales_tr()),
    UK("uk", TempusDominusClientBundle.INSTANCE.tdlocales_uk()),
    ZH_CN("zh-CN", TempusDominusClientBundle.INSTANCE.tdlocales_zh_CN()),
    ZH_HK("zh-HK", TempusDominusClientBundle.INSTANCE.tdlocales_zh_HK()),
    ZH_MO("zh-MO", TempusDominusClientBundle.INSTANCE.tdlocales_zh_MO()),
    ZH_TW("zh-TW", TempusDominusClientBundle.INSTANCE.tdlocales_zh_TW()),
    EN("en", null); // Base language, don't need another file

    private final String code;
    private final TextResource js;

    TempusDominusLocales(final String code, final TextResource js) {
        this.js = js;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public TextResource getJs() {
        return js;
    }

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
