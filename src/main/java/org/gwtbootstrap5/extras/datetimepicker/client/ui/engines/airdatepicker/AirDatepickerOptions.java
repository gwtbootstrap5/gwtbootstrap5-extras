package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.airdatepicker;

/*-
 * ==========================LICENSE_START===============================
 * GwtBootstrap5
 * ======================================================================
 * Copyright (C) 2026 GwtBootstrap5
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

import elemental2.dom.Element;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class AirDatepickerOptions {

    // --- General options ---
    @JsProperty public String classes;
    @JsProperty public boolean inline;
    /** The locale object, from {@code AirDatepickerLocales}. */
    @JsProperty public Object locale;
    /** The date the calendar opens on: a JS {@code Date}, a string or a timestamp. */
    @JsProperty public Object startDate;
    @JsProperty public int firstDay;
    @JsProperty public int[] weekends;
    @JsProperty public boolean isMobile;
    /** The date format, for example {@code "dd/MM/yyyy"}. */
    @JsProperty public String dateFormat;
    /** A second field that receives the date in {@link #altFieldDateFormat}: an element or a CSS selector. */
    @JsProperty public Object altField;
    @JsProperty public String altFieldDateFormat;
    @JsProperty public boolean toggleSelected;
    @JsProperty public boolean keyboardNav;

    // --- Position and view ---
    @JsProperty public Element container;
    /** Where the calendar opens relative to the input, for example {@code "bottom left"}. */
    @JsProperty public String position;
    /** The initial view: {@code "days"}, {@code "months"} or {@code "years"}. */
    @JsProperty public String view;
    @JsProperty public String minView;
    @JsProperty public boolean showOtherMonths;
    @JsProperty public boolean selectOtherMonths;
    @JsProperty public boolean moveToOtherMonthsOnSelect;

    // --- Date limits ---
    /** The earliest date that can be selected, as a JS {@code Date}. */
    @JsProperty public Object minDate;
    /** The latest date that can be selected, as a JS {@code Date}. */
    @JsProperty public Object maxDate;
    @JsProperty public boolean disableNavWhenOutOfRange;

    // --- Multiple dates and ranges ---
    /** {@code true} to select several dates, or the maximum number of dates as a number. */
    @JsProperty public Object multipleDates;
    @JsProperty public String multipleDatesSeparator;
    @JsProperty public boolean range;
    @JsProperty public boolean dynamicRange;

    // --- Buttons ---
    /** The buttons under the calendar: a name such as {@code "clear"} or {@code "today"}, or an array of names or button objects. */
    @JsProperty public Object buttons;
    @JsProperty public String monthsField;
    @JsProperty public String showEvent;
    @JsProperty public boolean autoClose;
    @JsProperty public String prevHtml;
    @JsProperty public String nextHtml;
    @JsProperty public boolean fixedHeight;

    // --- Timepicker ---
    @JsProperty public boolean timepicker;
    @JsProperty public String timeFormat;
    @JsProperty public boolean onlyTimepicker;
    @JsProperty public String dateTimeSeparator;
    @JsProperty public int minHours;
    @JsProperty public int maxHours;
    @JsProperty public int minMinutes;
    @JsProperty public int maxMinutes;
    @JsProperty public int hoursStep;
    @JsProperty public int minutesStep;

    // --- Callbacks ---
    @JsProperty public AirDatepickerCallbacks.OnSelect onSelect;
    @JsProperty public AirDatepickerCallbacks.OnBeforeSelect onBeforeSelect;
    @JsProperty public AirDatepickerCallbacks.OnChangeViewDate onChangeViewDate;
    @JsProperty public AirDatepickerCallbacks.OnChangeView onChangeView;
    @JsProperty public AirDatepickerCallbacks.OnRenderCell onRenderCell;
    @JsProperty public AirDatepickerCallbacks.OnShow onShow;
    @JsProperty public AirDatepickerCallbacks.OnHide onHide;
    @JsProperty public AirDatepickerCallbacks.OnClickDayName onClickDayName;
    @JsProperty public AirDatepickerCallbacks.OnFocus onFocus;
}
