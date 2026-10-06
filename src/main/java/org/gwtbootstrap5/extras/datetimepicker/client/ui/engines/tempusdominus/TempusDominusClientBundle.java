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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * The scripts of Tempus Dominus 6 and its translations, bundled with the module.
 *
 * @author Sven Jacobs
 */
public interface TempusDominusClientBundle extends ClientBundle {

    /** The bundle. */
    TempusDominusClientBundle INSTANCE = GWT.create(TempusDominusClientBundle.class);

    /** The folder of the library's files, with its version. */
    String TEMPUS_DOMINUS = "tempus-dominus-6.10.4";

    /**
     * The script of Tempus Dominus.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/tempus-dominus.min.cache.js")
    TextResource tempusDominus();

    /**
     * The Tempus Dominus translation into Arabic.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/ar.js")
    TextResource tdlocales_ar();

    /**
     * The Tempus Dominus translation into Arabic (Saudi Arabia).
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/ar-SA.js")
    TextResource tdlocales_ar_SA();

    /**
     * The Tempus Dominus translation into Catalan.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/ca.js")
    TextResource tdlocales_ca();

    /**
     * The Tempus Dominus translation into Czech.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/cs.js")
    TextResource tdlocales_cs();

    /**
     * The Tempus Dominus translation into German.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/de.js")
    TextResource tdlocales_de();

    /**
     * The Tempus Dominus translation into Spanish.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/es.js")
    TextResource tdlocales_es();

    /**
     * The Tempus Dominus translation into Finnish.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/fi.js")
    TextResource tdlocales_fi();

    /**
     * The Tempus Dominus translation into French.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/fr.js")
    TextResource tdlocales_fr();

    /**
     * The Tempus Dominus translation into Croatian.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/hr.js")
    TextResource tdlocales_hr();

    /**
     * The Tempus Dominus translation into Armenian.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/hy.js")
    TextResource tdlocales_hy();

    /**
     * The Tempus Dominus translation into Italian.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/it.js")
    TextResource tdlocales_it();

    /**
     * The Tempus Dominus translation into Dutch.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/nl.js")
    TextResource tdlocales_nl();

    /**
     * The Tempus Dominus translation into Polish.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/pl.js")
    TextResource tdlocales_pl();

    /**
     * The Tempus Dominus translation into Portuguese (Portugal).
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/pt-PT.js")
    TextResource tdlocales_pt_PT();

    /**
     * The Tempus Dominus translation into Romanian.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/ro.js")
    TextResource tdlocales_ro();

    /**
     * The Tempus Dominus translation into Russian.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/ru.js")
    TextResource tdlocales_ru();

    /**
     * The Tempus Dominus translation into Slovak.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/sk.js")
    TextResource tdlocales_sk();

    /**
     * The Tempus Dominus translation into Slovenian.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/sl.js")
    TextResource tdlocales_sl();

    /**
     * The Tempus Dominus translation into Serbian.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/sr.js")
    TextResource tdlocales_sr();

    /**
     * The Tempus Dominus translation into Serbian (Latin script).
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/sr-Latn.js")
    TextResource tdlocales_sr_LATN();

    /**
     * The Tempus Dominus translation into Turkish.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/tr.js")
    TextResource tdlocales_tr();

    /**
     * The Tempus Dominus translation into Ukrainian.
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/uk.js")
    TextResource tdlocales_uk();

    /**
     * The Tempus Dominus translation into Chinese (China).
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/zh-CN.js")
    TextResource tdlocales_zh_CN();

    /**
     * The Tempus Dominus translation into Chinese (Hong Kong).
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/zh-HK.js")
    TextResource tdlocales_zh_HK();

    /**
     * The Tempus Dominus translation into Chinese (Macao).
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/zh-MO.js")
    TextResource tdlocales_zh_MO();

    /**
     * The Tempus Dominus translation into Chinese (Taiwan).
     *
     * @return the script
     */
    @Source("../../../resource/" + TEMPUS_DOMINUS + "/js/locales.cache/zh-TW.js")
    TextResource tdlocales_zh_TW();

}
