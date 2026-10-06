package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.tempusdominus;

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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/** Options of Tempus Dominus, as a plain JavaScript object; unset properties keep their defaults. */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class TempusDominusOptions {

    /** Creates an empty options object; the properties left unset keep their defaults. */
    public TempusDominusOptions() {
    }

    /** How the picker looks. */
    @JsProperty public DisplayOptions display;
    /** The language and formats. */
    @JsProperty public LocalizationOptions localization;
    /** Which dates and times can be picked. */
    @JsProperty public RestrictionsOptions restrictions;
    /** Whether clicking the input opens the picker. */
    @JsProperty public boolean allowInputToggle;
    /** Whether two dates are picked, as a range. */
    @JsProperty public boolean dateRange;
    /** Whether the picker stays open, for styling it. */
    @JsProperty public boolean debug;
    /** The date selected when the picker opens: a {@code DateTime}, a JS {@code Date} or a string. */
    @JsProperty public Object defaultDate;
    /** Whether a typed date that isn't valid stays in the input. */
    @JsProperty public boolean keepInvalid;
    /** Whether several dates can be picked. */
    @JsProperty public boolean multipleDates;
    /** What goes between the dates in the input when several are picked. */
    @JsProperty public String multipleDatesSeparator;
    /** Whether the clock opens after a date is picked. */
    @JsProperty public boolean promptTimeOnDateChange;
    /** The delay before the clock opens, in milliseconds. */
    @JsProperty public int promptTimeOnDateChangeTransitionDelay;
    /** The step of the minutes. */
    @JsProperty public int stepping;
    /** Whether the current date and time are selected when the picker opens empty. */
    @JsProperty public boolean useCurrent;

    /** Which dates and times can be picked. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class RestrictionsOptions {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public RestrictionsOptions() {
        }

        /** The earliest date: a {@code DateTime}, a JS {@code Date} or a string. */
        @JsProperty public Object minDate;
        /** The latest date: a {@code DateTime}, a JS {@code Date} or a string. */
        @JsProperty public Object maxDate;
        /** Dates that can't be picked. */
        @JsProperty public Object[] disabledDates;
        /** The only dates that can be picked. */
        @JsProperty public Object[] enabledDates;
        /** Days of the week that can't be picked, from 0 (Sunday) to 6. */
        @JsProperty public int[] daysOfWeekDisabled;
        /** Time intervals that can't be picked. */
        @JsProperty public Object[] disabledTimeIntervals;
        /** Hours that can't be picked. */
        @JsProperty public Object[] disabledHours;
        /** The only hours that can be picked. */
        @JsProperty public Object[] enabledHours;
    }

    /** How the picker looks. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class DisplayOptions {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public DisplayOptions() {
        }

        /**
         * The view the picker opens on: {@code "clock"}, {@code "calendar"}, {@code "months"},
         * {@code "years"} or {@code "decades"}.
         */
        @JsProperty public String viewMode;
        /** Which parts of the picker are shown. */
        @JsProperty public ComponentsOptions components;
        /** Which buttons the picker shows. */
        @JsProperty public ButtonsOptions buttons;
        /** The icon classes of the picker. */
        @JsProperty public IconsOptions icons;
        /** Whether the picker is always shown in the page. */
        @JsProperty public boolean inline;
        /** Whether the picker stays open after a date is picked. */
        @JsProperty public boolean keepOpen;
        /** The color mode: {@code "light"}, {@code "dark"} or {@code "auto"}. */
        @JsProperty public String theme;
    }

    /** Which parts of the picker are shown. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class ComponentsOptions {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public ComponentsOptions() {
        }

        /** Whether the calendar is shown. */
        @JsProperty public boolean calendar;
        /** Whether days can be picked. */
        @JsProperty public boolean date;
        /** Whether months can be picked. */
        @JsProperty public boolean month;
        /** Whether years can be picked. */
        @JsProperty public boolean year;
        /** Whether decades can be picked. */
        @JsProperty public boolean decades;
        /** Whether the clock is shown. */
        @JsProperty public boolean clock;
        /** Whether hours can be picked. */
        @JsProperty public boolean hours;
        /** Whether minutes can be picked. */
        @JsProperty public boolean minutes;
        /** Whether seconds can be picked. */
        @JsProperty public boolean seconds;
        /** Whether the clock uses 24 hours. */
        @JsProperty public boolean useTwentyfourHour;
    }

    /** Which buttons the picker shows. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class ButtonsOptions {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public ButtonsOptions() {
        }

        /** Whether the button that picks today is shown. */
        @JsProperty public boolean today;
        /** Whether the button that clears the date is shown. */
        @JsProperty public boolean clear;
        /** Whether the button that closes the picker is shown. */
        @JsProperty public boolean close;
    }

    /** The icon classes of the picker. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class IconsOptions {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public IconsOptions() {
        }

        /** The icon class of the clock. */
        @JsProperty public String time;
        /** The icon class of the calendar. */
        @JsProperty public String date;
        /** The icon class of the up arrows. */
        @JsProperty public String up;
        /** The icon class of the down arrows. */
        @JsProperty public String down;
        /** The icon class of the previous arrow. */
        @JsProperty public String previous;
        /** The icon class of the next arrow. */
        @JsProperty public String next;
        /** The icon class of the today button. */
        @JsProperty public String today;
        /** The icon class of the clear button. */
        @JsProperty public String clear;
        /** The icon class of the close button. */
        @JsProperty public String close;
    }

    /** The language and the format of the date. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class LocalizationOptions {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public LocalizationOptions() {
        }

        /** The locale, such as {@code "es"}, for the names of months and days. */
        @JsProperty public String locale;
        /** The day the week starts on, from 0 (Sunday) to 6. */
        @JsProperty public String startOfTheWeek;
        /** The format of the date in the input, in Tempus Dominus tokens. */
        @JsProperty public String format;
    }
}
