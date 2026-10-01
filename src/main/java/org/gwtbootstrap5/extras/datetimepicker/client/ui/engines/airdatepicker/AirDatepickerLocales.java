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
 * @author Joshua Godi
 */
public enum AirDatepickerLocales {
    AR("ar", AirDatepickerClientBundle.INSTANCE.adlocales_ar()),
    BG("bg", AirDatepickerClientBundle.INSTANCE.adlocales_bg()),
    BR("br", AirDatepickerClientBundle.INSTANCE.adlocales_br()),
    CA("ca", AirDatepickerClientBundle.INSTANCE.adlocales_ca()),
    CS("cs", AirDatepickerClientBundle.INSTANCE.adlocales_cs()),
    DA("da", AirDatepickerClientBundle.INSTANCE.adlocales_da()),
    DE("de", AirDatepickerClientBundle.INSTANCE.adlocales_de()),
    EL("el", AirDatepickerClientBundle.INSTANCE.adlocales_el()),
    EN("en", AirDatepickerClientBundle.INSTANCE.adlocales_en()),
    ES("es", AirDatepickerClientBundle.INSTANCE.adlocales_es()),
    EU("eu", AirDatepickerClientBundle.INSTANCE.adlocales_eu()),
    FI("fi", AirDatepickerClientBundle.INSTANCE.adlocales_fi()),
    FR("fr", AirDatepickerClientBundle.INSTANCE.adlocales_fr()),
    HR("hr", AirDatepickerClientBundle.INSTANCE.adlocales_hr()),
    HU("hu", AirDatepickerClientBundle.INSTANCE.adlocales_hu()),
    ID("id", AirDatepickerClientBundle.INSTANCE.adlocales_id()),
    IT("it", AirDatepickerClientBundle.INSTANCE.adlocales_it()),
    JA("ja", AirDatepickerClientBundle.INSTANCE.adlocales_ja()),
    KO("ko", AirDatepickerClientBundle.INSTANCE.adlocales_ko()),
    NB("nb", AirDatepickerClientBundle.INSTANCE.adlocales_nb()),
    NL("nl", AirDatepickerClientBundle.INSTANCE.adlocales_nl()),
    PL("pl", AirDatepickerClientBundle.INSTANCE.adlocales_pl()),
    PT("pt", AirDatepickerClientBundle.INSTANCE.adlocales_pt()),
    RO("ro", AirDatepickerClientBundle.INSTANCE.adlocales_ro()),
    RU("ru", AirDatepickerClientBundle.INSTANCE.adlocales_ru()),
    SI("si", AirDatepickerClientBundle.INSTANCE.adlocales_si()),
    SK("sk", AirDatepickerClientBundle.INSTANCE.adlocales_sk()),
    SL("sl", AirDatepickerClientBundle.INSTANCE.adlocales_sl()),
    SR("sv", AirDatepickerClientBundle.INSTANCE.adlocales_sv()),
    TH("th", AirDatepickerClientBundle.INSTANCE.adlocales_th()),
    TR("tr", AirDatepickerClientBundle.INSTANCE.adlocales_tr()),
    UK("uk", AirDatepickerClientBundle.INSTANCE.adlocales_uk()),
    ZH("zh", AirDatepickerClientBundle.INSTANCE.adlocales_zh());

    // Base language, don't need another file

    private final String code;
    private final TextResource js;

    AirDatepickerLocales(final String code, final TextResource js) {
        this.js = js;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public TextResource getJs() {
        return js;
    }

    public static AirDatepickerLocales fromCode(final String code) {
        for (final AirDatepickerLocales locale : values()) {
            if (locale.getCode().equals(code)) {
                return locale;
            }
        }

        return null;
    }

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
