package org.gwtbootstrap5.extras.summernote.client;

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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * The script of Summernote 0.9 for Bootstrap 5 and its translations, bundled with the module.
 *
 * @author godi
 */
public interface SummernoteClientBundle extends ClientBundle {

    /** The bundle. */
    SummernoteClientBundle INSTANCE = GWT.create(SummernoteClientBundle.class);
    /** The version of Summernote. */
    String VERSION = "0.9.1";
    /** The folder of the translations. */
    String LOCALE_DIR = "resource/js/locale.cache." + VERSION + "/";

    /**
     * The script of Summernote for Bootstrap 5.
     *
     * @return the script
     */
    @Source("resource/js/summernote-bs5-" + VERSION + ".min.cache.js")
    TextResource summernote_BS5();

    /**
     * The Summernote translation into Arabic.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-ar-AR.min.js")
    TextResource ar_AR();

    /**
     * The Summernote translation into Azerbaijani.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-az-AZ.min.js")
    TextResource az_AZ();

    /**
     * The Summernote translation into Bulgarian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-bg-BG.min.js")
    TextResource bg_BG();

    /**
     * The Summernote translation into Bengali (Bangladesh).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-bn-BD.min.js")
    TextResource bn_BD();

    /**
     * The Summernote translation into Catalan.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-ca-ES.min.js")
    TextResource ca_ES();

    /**
     * The Summernote translation into Czech.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-cs-CZ.min.js")
    TextResource cs_CZ();

    /**
     * The Summernote translation into Danish.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-da-DK.min.js")
    TextResource da_DK();

    /**
     * The Summernote translation into German (Switzerland).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-de-CH.min.js")
    TextResource de_CH();

    /**
     * The Summernote translation into German (Germany).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-de-DE.min.js")
    TextResource de_DE();

    /**
     * The Summernote translation into Greek.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-el-GR.min.js")
    TextResource el_GR();

    /**
     * The Summernote translation into English (US).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-en-US.min.js")
    TextResource en_US();

    /**
     * The Summernote translation into Spanish (Spain).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-es-ES.min.js")
    TextResource es_ES();

    /**
     * The Summernote translation into Basque.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-es-EU.min.js")
    TextResource es_EU();

    /**
     * The Summernote translation into Persian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-fa-IR.min.js")
    TextResource fa_IR();

    /**
     * The Summernote translation into Finnish.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-fi-FI.min.js")
    TextResource fi_FI();

    /**
     * The Summernote translation into French.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-fr-FR.min.js")
    TextResource fr_FR();

    /**
     * The Summernote translation into Galician.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-gl-ES.min.js")
    TextResource gl_ES();

    /**
     * The Summernote translation into Hebrew.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-he-IL.min.js")
    TextResource he_IL();

    /**
     * The Summernote translation into Croatian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-hr-HR.min.js")
    TextResource hr_HR();

    /**
     * The Summernote translation into Hungarian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-hu-HU.min.js")
    TextResource hu_HU();

    /**
     * The Summernote translation into Indonesian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-id-ID.min.js")
    TextResource id_ID();

    /**
     * The Summernote translation into Italian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-it-IT.min.js")
    TextResource it_IT();

    /**
     * The Summernote translation into Japanese.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-ja-JP.min.js")
    TextResource ja_JP();

    /**
     * The Summernote translation into Korean.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-ko-KR.min.js")
    TextResource ko_KR();

    /**
     * The Summernote translation into Lithuanian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-lt-LT.min.js")
    TextResource lt_LT();

    /**
     * The Summernote translation into Latvian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-lt-LV.min.js")
    TextResource lt_LV();

    /**
     * The Summernote translation into Mongolian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-mn-MN.min.js")
    TextResource mn_MN();

    /**
     * The Summernote translation into Norwegian Bokmål.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-nb-NO.min.js")
    TextResource nb_NO();

    /**
     * The Summernote translation into Dutch.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-nl-NL.min.js")
    TextResource nl_NL();

    /**
     * The Summernote translation into Polish.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-pl-PL.min.js")
    TextResource pl_PL();

    /**
     * The Summernote translation into Portuguese (Brazil).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-pt-BR.min.js")
    TextResource pt_BR();

    /**
     * The Summernote translation into Portuguese (Portugal).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-pt-PT.min.js")
    TextResource pt_PT();

    /**
     * The Summernote translation into Romanian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-ro-RO.min.js")
    TextResource ro_RO();

    /**
     * The Summernote translation into Russian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-ru-RU.min.js")
    TextResource ru_RU();

    /**
     * The Summernote translation into Slovak.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-sk-SK.min.js")
    TextResource sk_SK();

    /**
     * The Summernote translation into Slovenian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-sl-SI.min.js")
    TextResource sl_SI();

    /**
     * The Summernote translation into Serbian (Cyrillic).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-sr-RS.min.js")
    TextResource sr_RS();

    /**
     * The Summernote translation into Serbian (Latin).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-sr-RS-Latin.min.js")
    TextResource sr_RS_Latin();

    /**
     * The Summernote translation into Swedish.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-sv-SE.min.js")
    TextResource sv_SE();

    /**
     * The Summernote translation into Thai.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-th-TH.min.js")
    TextResource th_TH();

    /**
     * The Summernote translation into Turkish.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-tr-TR.min.js")
    TextResource tr_TR();

    /**
     * The Summernote translation into Ukrainian.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-uk-UA.min.js")
    TextResource uk_UA();

    /**
     * The Summernote translation into Vietnamese.
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-vi-VN.min.js")
    TextResource vi_VN();

    /**
     * The Summernote translation into Chinese (Simplified).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-zh-CN.min.js")
    TextResource zh_CN();

    /**
     * The Summernote translation into Chinese (Traditional).
     *
     * @return the script
     */
    @Source(LOCALE_DIR + "summernote-zh-TW.min.js")
    TextResource zh_TW();
}
