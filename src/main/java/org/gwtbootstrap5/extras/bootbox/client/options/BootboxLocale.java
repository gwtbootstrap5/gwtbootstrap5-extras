package org.gwtbootstrap5.extras.bootbox.client.options;

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

/**
 * The languages of the Bootbox buttons, for {@code Bootbox.setLocale}.
 *
 * @author Xiaodong Sun
 */
public enum BootboxLocale {

    /** Bulgarian ({@code bg_BG}). */
    BG_BG("bg_BG"),
    /** Portuguese (Brazil) ({@code br}). */
    BR("br"),
    /** Czech ({@code cs}). */
    CS("cs"),
    /** Danish ({@code da}). */
    DA("da"),
    /** German ({@code de}). */
    DE("de"),
    /** Greek ({@code el}). */
    EL("el"),
    /** English ({@code en}). */
    EN("en"),
    /** Spanish ({@code es}). */
    ES("es"),
    /** Estonian ({@code et}). */
    ET("et"),
    /** Persian ({@code fa}). */
    FA("fa"),
    /** Finnish ({@code fi}). */
    FI("fi"),
    /** French ({@code fr}). */
    FR("fr"),
    /** Hebrew ({@code he}). */
    HE("he"),
    /** Hungarian ({@code hu}). */
    HU("hu"),
    /** Croatian ({@code hr}). */
    HR("hr"),
    /** Indonesian ({@code id}). */
    ID("id"),
    /** Italian ({@code it}). */
    IT("it"),
    /** Japanese ({@code ja}). */
    JA("ja"),
    /** Lithuanian ({@code lt}). */
    LT("lt"),
    /** Latvian ({@code lv}). */
    LV("lv"),
    /** Dutch ({@code nl}). */
    NL("nl"),
    /** Norwegian ({@code no}). */
    NO("no"),
    /** Polish ({@code pl}). */
    PL("pl"),
    /** Portuguese ({@code pt}). */
    PT("pt"),
    /** Russian ({@code ru}). */
    RU("ru"),
    /** Albanian ({@code sq}). */
    SQ("sq"),
    /** Swedish ({@code sv}). */
    SV("sv"),
    /** Thai ({@code th}). */
    TH("th"),
    /** Turkish ({@code tr}). */
    TR("tr"),
    /** Chinese (Simplified) ({@code zh_CN}). */
    ZH_CN("zh_CN"),
    /** Chinese (Traditional) ({@code zh_TW}). */
    ZH_TW("zh_TW");

    private final String locale;

    BootboxLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * Returns the locale.
     *
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }
    
    /**
     * Returns the default locale: {@link #EN}.
     * 
     * @return the default locale.
     */
    public static BootboxLocale getDefault() {
        return EN;
    }

}
