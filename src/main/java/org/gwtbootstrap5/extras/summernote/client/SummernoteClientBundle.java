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
 * @author godi
 */
public interface SummernoteClientBundle extends ClientBundle {

    SummernoteClientBundle INSTANCE = GWT.create(SummernoteClientBundle.class);
    String VERSION = "0.9.1";
    String LOCALE_DIR = "resource/js/locale.cache." + VERSION + "/";

    @Source("resource/js/summernote-bs5-" + VERSION + ".min.cache.js")
    TextResource summernote_BS5();

    @Source(LOCALE_DIR + "summernote-ar-AR.min.js")
    TextResource ar_AR();

    @Source(LOCALE_DIR + "summernote-az-AZ.min.js")
    TextResource az_AZ();

    @Source(LOCALE_DIR + "summernote-bg-BG.min.js")
    TextResource bg_BG();

    @Source(LOCALE_DIR + "summernote-bn-BD.min.js")
    TextResource bn_BD();

    @Source(LOCALE_DIR + "summernote-ca-ES.min.js")
    TextResource ca_ES();

    @Source(LOCALE_DIR + "summernote-cs-CZ.min.js")
    TextResource cs_CZ();

    @Source(LOCALE_DIR + "summernote-da-DK.min.js")
    TextResource da_DK();

    @Source(LOCALE_DIR + "summernote-de-CH.min.js")
    TextResource de_CH();

    @Source(LOCALE_DIR + "summernote-de-DE.min.js")
    TextResource de_DE();

    @Source(LOCALE_DIR + "summernote-el-GR.min.js")
    TextResource el_GR();

    @Source(LOCALE_DIR + "summernote-en-US.min.js")
    TextResource en_US();

    @Source(LOCALE_DIR + "summernote-es-ES.min.js")
    TextResource es_ES();

    @Source(LOCALE_DIR + "summernote-es-EU.min.js")
    TextResource es_EU();

    @Source(LOCALE_DIR + "summernote-fa-IR.min.js")
    TextResource fa_IR();

    @Source(LOCALE_DIR + "summernote-fi-FI.min.js")
    TextResource fi_FI();

    @Source(LOCALE_DIR + "summernote-fr-FR.min.js")
    TextResource fr_FR();

    @Source(LOCALE_DIR + "summernote-gl-ES.min.js")
    TextResource gl_ES();

    @Source(LOCALE_DIR + "summernote-he-IL.min.js")
    TextResource he_IL();

    @Source(LOCALE_DIR + "summernote-hr-HR.min.js")
    TextResource hr_HR();

    @Source(LOCALE_DIR + "summernote-hu-HU.min.js")
    TextResource hu_HU();

    @Source(LOCALE_DIR + "summernote-id-ID.min.js")
    TextResource id_ID();

    @Source(LOCALE_DIR + "summernote-it-IT.min.js")
    TextResource it_IT();

    @Source(LOCALE_DIR + "summernote-ja-JP.min.js")
    TextResource ja_JP();

    @Source(LOCALE_DIR + "summernote-ko-KR.min.js")
    TextResource ko_KR();

    @Source(LOCALE_DIR + "summernote-lt-LT.min.js")
    TextResource lt_LT();

    @Source(LOCALE_DIR + "summernote-lt-LV.min.js")
    TextResource lt_LV();

    @Source(LOCALE_DIR + "summernote-mn-MN.min.js")
    TextResource mn_MN();

    @Source(LOCALE_DIR + "summernote-nb-NO.min.js")
    TextResource nb_NO();

    @Source(LOCALE_DIR + "summernote-nl-NL.min.js")
    TextResource nl_NL();

    @Source(LOCALE_DIR + "summernote-pl-PL.min.js")
    TextResource pl_PL();

    @Source(LOCALE_DIR + "summernote-pt-BR.min.js")
    TextResource pt_BR();

    @Source(LOCALE_DIR + "summernote-pt-PT.min.js")
    TextResource pt_PT();

    @Source(LOCALE_DIR + "summernote-ro-RO.min.js")
    TextResource ro_RO();

    @Source(LOCALE_DIR + "summernote-ru-RU.min.js")
    TextResource ru_RU();

    @Source(LOCALE_DIR + "summernote-sk-SK.min.js")
    TextResource sk_SK();

    @Source(LOCALE_DIR + "summernote-sl-SI.min.js")
    TextResource sl_SI();

    @Source(LOCALE_DIR + "summernote-sr-RS.min.js")
    TextResource sr_RS();

    @Source(LOCALE_DIR + "summernote-sr-RS-Latin.min.js")
    TextResource sr_RS_Latin();

    @Source(LOCALE_DIR + "summernote-sv-SE.min.js")
    TextResource sv_SE();

    @Source(LOCALE_DIR + "summernote-th-TH.min.js")
    TextResource th_TH();

    @Source(LOCALE_DIR + "summernote-tr-TR.min.js")
    TextResource tr_TR();

    @Source(LOCALE_DIR + "summernote-uk-UA.min.js")
    TextResource uk_UA();

    @Source(LOCALE_DIR + "summernote-vi-VN.min.js")
    TextResource vi_VN();

    @Source(LOCALE_DIR + "summernote-zh-CN.min.js")
    TextResource zh_CN();

    @Source(LOCALE_DIR + "summernote-zh-TW.min.js")
    TextResource zh_TW();
}
