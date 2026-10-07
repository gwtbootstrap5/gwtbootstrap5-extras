package org.gwtbootstrap5.extras.summernote.client.ui.base;

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

import com.google.gwt.resources.client.TextResource;
import org.gwtbootstrap5.extras.summernote.client.SummernoteClientBundle;

/**
 * The languages of the Summernote editor, for {@code SummernoteBase.setLanguage}. Each one bundles
 * the translation file that the editor loads when it is created.
 *
 * @author Michał Rybicki (based on DatePickerLanguage by Joshua Godi)
 */
public enum SummernoteLanguage {
    /** Arabic ({@code ar-AR}). */
    AR_AR("ar-AR", SummernoteClientBundle.INSTANCE.ar_AR()),
    /** Azerbaijani ({@code az-AZ}). */
    AZ_AZ("az-AZ", SummernoteClientBundle.INSTANCE.az_AZ()),
    /** Bulgarian ({@code bg-BG}). */
    BG_BG("bg-BG", SummernoteClientBundle.INSTANCE.bg_BG()),
    /** Bengali (Bangladesh) ({@code bn-BD}). */
    BN_BD("bn-BD", SummernoteClientBundle.INSTANCE.bn_BD()),
    /** Catalan ({@code ca-ES}). */
    CA_ES("ca-ES", SummernoteClientBundle.INSTANCE.ca_ES()),
    /** Czech ({@code cs-CZ}). */
    CS_CZ("cs-CZ", SummernoteClientBundle.INSTANCE.cs_CZ()),
    /** Danish ({@code da-DK}). */
    DA_DK("da-DK", SummernoteClientBundle.INSTANCE.da_DK()),
    /** German (Switzerland) ({@code de-CH}). */
    DE_CH("de-CH", SummernoteClientBundle.INSTANCE.de_CH()),
    /** German (Germany) ({@code de-DE}). */
    DE_DE("de-DE", SummernoteClientBundle.INSTANCE.de_DE()),
    /** Greek ({@code el-GR}). */
    EL_GR("el-GR", SummernoteClientBundle.INSTANCE.el_GR()),
    /** English (US) ({@code en-US}). */
    EN_US("en-US", SummernoteClientBundle.INSTANCE.en_US()),
    /** Spanish (Spain) ({@code es-ES}). */
    ES_ES("es-ES", SummernoteClientBundle.INSTANCE.es_ES()),
    /** Basque ({@code es-EU}). Despite its name, it is Basque, not a variant of Spanish. */
    ES_EU("es-EU", SummernoteClientBundle.INSTANCE.es_EU()),
    /** Persian ({@code fa-IR}). */
    FA_IR("fa-IR", SummernoteClientBundle.INSTANCE.fa_IR()),
    /** Finnish ({@code fi-FI}). */
    FI_FI("fi-FI", SummernoteClientBundle.INSTANCE.fi_FI()),
    /** French ({@code fr-FR}). */
    FR_FR("fr-FR", SummernoteClientBundle.INSTANCE.fr_FR()),
    /** Galician ({@code gl-ES}). */
    GL_ES("gl-ES", SummernoteClientBundle.INSTANCE.gl_ES()),
    /** Hebrew ({@code he-IL}). */
    HE_IL("he-IL", SummernoteClientBundle.INSTANCE.he_IL()),
    /** Croatian ({@code hr-HR}). */
    HR_HR("hr-HR", SummernoteClientBundle.INSTANCE.hr_HR()),
    /** Hungarian ({@code hu-HU}). */
    HU_HU("hu-HU", SummernoteClientBundle.INSTANCE.hu_HU()),
    /** Indonesian ({@code id-ID}). */
    ID_ID("id-ID", SummernoteClientBundle.INSTANCE.id_ID()),
    /** Italian ({@code it-IT}). */
    IT_IT("it-IT", SummernoteClientBundle.INSTANCE.it_IT()),
    /** Japanese ({@code ja-JP}). */
    JA_JP("ja-JP", SummernoteClientBundle.INSTANCE.ja_JP()),
    /** Korean ({@code ko-KR}). */
    KO_KR("ko-KR", SummernoteClientBundle.INSTANCE.ko_KR()),
    /** Mongolian ({@code mn-MN}). */
    MN_MN("mn-MN", SummernoteClientBundle.INSTANCE.mn_MN()),
    /** Norwegian Bokmål ({@code nb-NO}). */
    NB_NO("nb-NO", SummernoteClientBundle.INSTANCE.nb_NO()),
    /** Dutch ({@code nl-NL}). */
    NL_NL("nl-NL", SummernoteClientBundle.INSTANCE.nl_NL()),
    /** Lithuanian ({@code lt-LT}). */
    LT_LT("lt-LT", SummernoteClientBundle.INSTANCE.lt_LT()),
    /** Latvian ({@code lv-LV}). Despite its name, it is Latvian. */
    LT_LV("lv-LV", SummernoteClientBundle.INSTANCE.lt_LV()),
    /** Polish ({@code pl-PL}). */
    PL_PL("pl-PL", SummernoteClientBundle.INSTANCE.pl_PL()),
    /** Portuguese (Brazil) ({@code pt-BR}). */
    PT_BR("pt-BR", SummernoteClientBundle.INSTANCE.pt_BR()),
    /** Portuguese (Portugal) ({@code pt-PT}). */
    PT_PT("pt-PT", SummernoteClientBundle.INSTANCE.pt_PT()),
    /** Romanian ({@code ro-RO}). */
    RO_RO("ro-RO", SummernoteClientBundle.INSTANCE.ro_RO()),
    /** Russian ({@code ru-RU}). */
    RU_RU("ru-RU", SummernoteClientBundle.INSTANCE.ru_RU()),
    /** Slovak ({@code sk-SK}). */
    SK_SK("sk-SK", SummernoteClientBundle.INSTANCE.sk_SK()),
    /** Slovenian ({@code sl-SI}). Despite its name, its code is {@code sl-SI}. */
    SL_SL("sl-SI", SummernoteClientBundle.INSTANCE.sl_SI()),
    /** Serbian in the Cyrillic script ({@code sr-RS}). */
    SR_RS("sr-RS", SummernoteClientBundle.INSTANCE.sr_RS()),
    /**
     * Serbian in the Latin script ({@code sr-RS}). It registers itself as {@code sr-RS}, like
     * {@link #SR_RS}, so a page can use only one of them.
     */
    SR_RS_LATIN("sr-RS", SummernoteClientBundle.INSTANCE.sr_RS_Latin()),
    /** Swedish ({@code sv-SE}). */
    SV_SE("sv-SE", SummernoteClientBundle.INSTANCE.sv_SE()),
    /** Thai ({@code th-TH}). */
    TH_TH("th-TH", SummernoteClientBundle.INSTANCE.th_TH()),
    /** Turkish ({@code tr-TR}). */
    TR_TR("tr-TR", SummernoteClientBundle.INSTANCE.tr_TR()),
    /** Ukrainian ({@code uk-UA}). */
    UK_UA("uk-UA", SummernoteClientBundle.INSTANCE.uk_UA()),
    /** Vietnamese ({@code vi-VN}). */
    VI_VN("vi-VN", SummernoteClientBundle.INSTANCE.vi_VN()),
    /** Chinese (Simplified) ({@code zh-CN}). */
    ZH_CN("zh-CN", SummernoteClientBundle.INSTANCE.zh_CN()),
    /** Chinese (Traditional) ({@code zh-TW}). */
    ZH_TW("zh-TW", SummernoteClientBundle.INSTANCE.zh_TW());
  
    private final String code;
    private final TextResource js;
  
    SummernoteLanguage(final String code, final TextResource js) {
        this.code = code;
        this.js = js;
    }
  
    /**
     * Returns the code Summernote knows the language by, its {@code lang} option.
     *
     * @return the code, such as {@code "es-ES"}
     */
    public String getCode() {
        return code;
    }
  
    /**
     * Returns the translation file.
     *
     * @return the script that registers the language in Summernote
     */
    public TextResource getJs() {
        return js;
    }
}
