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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * The scripts of Air Datepicker 3 and its translations, bundled with the module.
 *
 * @author Sven Jacobs
 */
public interface AirDatepickerClientBundle extends ClientBundle {

    /** The bundle. */
    AirDatepickerClientBundle INSTANCE = GWT.create(AirDatepickerClientBundle.class);

    /** The folder of the library's files, with its version. */
    String AIR_DATEPICKER = "air-datepicker-3.5.3";

    /**
     * The script of Air Datepicker.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/air-datepicker.min.cache.js")
    TextResource airDatepicker();

    /**
     * The Air Datepicker translation into Arabic.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/ar.js")
    TextResource adlocales_ar();

    /**
     * The Air Datepicker translation into Bulgarian.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/bg.js")
    TextResource adlocales_bg();

    /**
     * The Air Datepicker translation into Brazilian Portuguese.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/br.js")
    TextResource adlocales_br();

    /**
     * The Air Datepicker translation into Catalan.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/ca.js")
    TextResource adlocales_ca();

    /**
     * The Air Datepicker translation into Czech.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/cs.js")
    TextResource adlocales_cs();

    /**
     * The Air Datepicker translation into Danish.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/da.js")
    TextResource adlocales_da();

    /**
     * The Air Datepicker translation into German.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/de.js")
    TextResource adlocales_de();

    /**
     * The Air Datepicker translation into Greek.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/el.js")
    TextResource adlocales_el();

    /**
     * The Air Datepicker translation into English.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/en.js")
    TextResource adlocales_en();

    /**
     * The Air Datepicker translation into Spanish.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/es.js")
    TextResource adlocales_es();

    /**
     * The Air Datepicker translation into Basque.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/eu.js")
    TextResource adlocales_eu();

    /**
     * The Air Datepicker translation into Finnish.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/fi.js")
    TextResource adlocales_fi();

    /**
     * The Air Datepicker translation into French.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/fr.js")
    TextResource adlocales_fr();

    /**
     * The Air Datepicker translation into Croatian.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/hr.js")
    TextResource adlocales_hr();

    /**
     * The Air Datepicker translation into Hungarian.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/hu.js")
    TextResource adlocales_hu();

    /**
     * The Air Datepicker translation into Indonesian.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/id.js")
    TextResource adlocales_id();

    /**
     * The Air Datepicker translation into Italian.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/it.js")
    TextResource adlocales_it();

    /**
     * The Air Datepicker translation into Japanese.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/ja.js")
    TextResource adlocales_ja();

    /**
     * The Air Datepicker translation into Korean.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/ko.js")
    TextResource adlocales_ko();

    /**
     * The Air Datepicker translation into Norwegian Bokmål.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/nb.js")
    TextResource adlocales_nb();

    /**
     * The Air Datepicker translation into Dutch.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/nl.js")
    TextResource adlocales_nl();

    /**
     * The Air Datepicker translation into Polish.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/pl.js")
    TextResource adlocales_pl();

    /**
     * The Air Datepicker translation into Portuguese.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/pt.js")
    TextResource adlocales_pt();

    /**
     * The Air Datepicker translation into Romanian.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/ro.js")
    TextResource adlocales_ro();

    /**
     * The Air Datepicker translation into Russian.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/ru.js")
    TextResource adlocales_ru();

    /**
     * The Air Datepicker translation into Sinhala.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/si.js")
    TextResource adlocales_si();

    /**
     * The Air Datepicker translation into Slovak.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/sk.js")
    TextResource adlocales_sk();

    /**
     * The Air Datepicker translation into Slovenian.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/sl.js")
    TextResource adlocales_sl();

    /**
     * The Air Datepicker translation into Swedish.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/sv.js")
    TextResource adlocales_sv();

    /**
     * The Air Datepicker translation into Thai.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/th.js")
    TextResource adlocales_th();

    /**
     * The Air Datepicker translation into Turkish.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/tr.js")
    TextResource adlocales_tr();

    /**
     * The Air Datepicker translation into Ukrainian.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/uk.js")
    TextResource adlocales_uk();

    /**
     * The Air Datepicker translation into Chinese.
     *
     * @return the script
     */
    @Source("../../../resource/" + AIR_DATEPICKER + "/js/locales.cache/zh.js")
    TextResource adlocales_zh();

}
