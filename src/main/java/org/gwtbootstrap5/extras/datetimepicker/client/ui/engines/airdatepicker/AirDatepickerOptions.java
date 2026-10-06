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

/** Options of Air Datepicker, as a plain JavaScript object; unset properties keep their defaults. */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class AirDatepickerOptions {

    /** Creates an empty options object; the properties left unset keep their defaults. */
    public AirDatepickerOptions() {
    }

    // --- General options ---
    /** Extra classes for the calendar element. */
    @JsProperty public String classes;
    /** Whether the calendar is always shown in the page, instead of opening from the input. */
    @JsProperty public boolean inline;
    /** The locale object, from {@code AirDatepickerLocales}. */
    @JsProperty public Object locale;
    /** The date the calendar opens on: a JS {@code Date}, a string or a timestamp. */
    @JsProperty public Object startDate;
    /** The day the week starts on, from 0 (Sunday) to 6. */
    @JsProperty public int firstDay;
    /** The days of the weekend, from 0 (Sunday) to 6. */
    @JsProperty public int[] weekends;
    /** Whether the calendar opens centered over the page, with a backdrop, as on phones. */
    @JsProperty public boolean isMobile;
    /** The date format, for example {@code "dd/MM/yyyy"}. */
    @JsProperty public String dateFormat;
    /** A second field that receives the date in {@link #altFieldDateFormat}: an element or a CSS selector. */
    @JsProperty public Object altField;
    /** The date format of {@link #altField}. */
    @JsProperty public String altFieldDateFormat;
    /** Whether clicking a selected date unselects it. */
    @JsProperty public boolean toggleSelected;
    /** Whether the arrow keys move through the calendar. */
    @JsProperty public boolean keyboardNav;

    // --- Position and view ---
    /** The element the calendar is added to, instead of the body. */
    @JsProperty public Element container;
    /** Where the calendar opens relative to the input, for example {@code "bottom left"}. */
    @JsProperty public String position;
    /** The initial view: {@code "days"}, {@code "months"} or {@code "years"}. */
    @JsProperty public String view;
    /** The most detailed view: {@code "days"}, {@code "months"} or {@code "years"}. */
    @JsProperty public String minView;
    /** Whether the days of the previous and next months are shown. */
    @JsProperty public boolean showOtherMonths;
    /** Whether the days of the previous and next months can be selected. */
    @JsProperty public boolean selectOtherMonths;
    /** Whether selecting a day of another month moves the calendar to it. */
    @JsProperty public boolean moveToOtherMonthsOnSelect;

    // --- Date limits ---
    /** The earliest date that can be selected, as a JS {@code Date}. */
    @JsProperty public Object minDate;
    /** The latest date that can be selected, as a JS {@code Date}. */
    @JsProperty public Object maxDate;
    /** Whether the navigation arrows are disabled beyond the date limits. */
    @JsProperty public boolean disableNavWhenOutOfRange;

    // --- Multiple dates and ranges ---
    /** {@code true} to select several dates, or the maximum number of dates as a number. */
    @JsProperty public Object multipleDates;
    /** What goes between the dates in the input when several are selected. */
    @JsProperty public String multipleDatesSeparator;
    /** Whether two dates are selected, as the start and end of a range. */
    @JsProperty public boolean range;
    /** Whether a selected range can be changed by dragging its ends. */
    @JsProperty public boolean dynamicRange;

    // --- Buttons ---
    /** The buttons under the calendar: a name such as {@code "clear"} or {@code "today"}, or an array of names or button objects. */
    @JsProperty public Object buttons;
    /** The field of the locale whose month names the months view shows. */
    @JsProperty public String monthsField;
    /** The event of the input that opens the calendar, {@code "focus"} by default. */
    @JsProperty public String showEvent;
    /** Whether the calendar closes after a date is selected. */
    @JsProperty public boolean autoClose;
    /** The content of the button that goes back. */
    @JsProperty public String prevHtml;
    /** The content of the button that goes forward. */
    @JsProperty public String nextHtml;
    /** Whether the days view always has six weeks, so its height doesn't change. */
    @JsProperty public boolean fixedHeight;

    // --- Timepicker ---
    /** Whether the time can be picked too. */
    @JsProperty public boolean timepicker;
    /** The time format, for example {@code "HH:mm"}. */
    @JsProperty public String timeFormat;
    /** Whether only the time is picked, without the calendar. */
    @JsProperty public boolean onlyTimepicker;
    /** What goes between the date and the time in the input. */
    @JsProperty public String dateTimeSeparator;
    /** The earliest hour that can be picked. */
    @JsProperty public int minHours;
    /** The latest hour that can be picked. */
    @JsProperty public int maxHours;
    /** The earliest minute that can be picked. */
    @JsProperty public int minMinutes;
    /** The latest minute that can be picked. */
    @JsProperty public int maxMinutes;
    /** The step of the hours slider. */
    @JsProperty public int hoursStep;
    /** The step of the minutes slider. */
    @JsProperty public int minutesStep;

    // --- Callbacks ---
    /** Called when a date is selected. */
    @JsProperty public AirDatepickerCallbacks.OnSelect onSelect;
    /** Called before a date is selected; returning {@code false} prevents it. */
    @JsProperty public AirDatepickerCallbacks.OnBeforeSelect onBeforeSelect;
    /** Called when the calendar moves to another month, year or decade. */
    @JsProperty public AirDatepickerCallbacks.OnChangeViewDate onChangeViewDate;
    /** Called when the view changes between days, months and years. */
    @JsProperty public AirDatepickerCallbacks.OnChangeView onChangeView;
    /** Called for each cell drawn, to change its content, classes or state. */
    @JsProperty public AirDatepickerCallbacks.OnRenderCell onRenderCell;
    /** Called when the calendar opens, and again when its animation ends. */
    @JsProperty public AirDatepickerCallbacks.OnShow onShow;
    /** Called when the calendar closes, and again when its animation ends. */
    @JsProperty public AirDatepickerCallbacks.OnHide onHide;
    /** Called when the name of a day is clicked. */
    @JsProperty public AirDatepickerCallbacks.OnClickDayName onClickDayName;
    /** Called when a date gets the keyboard focus. */
    @JsProperty public AirDatepickerCallbacks.OnFocus onFocus;
}
