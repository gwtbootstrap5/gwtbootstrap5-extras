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

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/** JsInterop binding of an Air Datepicker instance: {@code new AirDatepicker(element, options)}. */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "AirDatepicker")
public class AirDatepicker {

    /**
     * Creates a datepicker with the default options.
     *
     * @param element a CSS selector or the DOM element to attach the datepicker to
     */
    public AirDatepicker(Object element) {}

    /**
     * Creates a datepicker.
     *
     * @param element a CSS selector or the DOM element to attach the datepicker to
     * @param options the Air Datepicker options
     */
    public AirDatepicker(Object element, AirDatepickerOptions options) {}

    // --- Propiedades ---
    /** The selected dates, as JS {@code Date} objects. */
    @JsProperty public Object[] selectedDates;
    /** The focused date, as a JS {@code Date}. */
    @JsProperty public Object focusDate;
    /** The current view: {@code "days"}, {@code "months"} or {@code "years"}. */
    @JsProperty public String currentView;
    /** The date the current view shows, as a JS {@code Date}. */
    @JsProperty public Object viewDate;
    /** Whether the datepicker is visible. */
    @JsProperty public boolean visible;
    /** The DOM element the datepicker is attached to. */
    @JsProperty public Object el;
    /** The element the datepicker is attached to, wrapped by jQuery when jQuery is on the page. */
    @JsProperty public Object $el;

    // --- AirDatePicker ---
    /**
     * Applies new options.
     *
     * @param options the options to change
     */
    @JsMethod public native void update(AirDatepickerOptions options);
    /** Destroys the datepicker and removes its calendar. */
    @JsMethod public native void destroy();

    // --- API methods ---
    /** Opens the calendar. */
    @JsMethod public native void show();
    /** Closes the calendar. */
    @JsMethod public native void hide();
    /** Goes to the next month, year or decade. */
    @JsMethod public native void next();
    /** Goes to the previous month, year or decade. */
    @JsMethod public native void prev();

    /**
     * Unselects every date.
     *
     * @param opts {@code silent} not to fire {@code onSelect}
     */
    @JsMethod public native void clear(ClearDateOptions opts);
    /**
     * Selects a date.
     *
     * @param date the date, a JS {@code Date}, or an array of them
     */
    @JsMethod public native void selectDate(Object date);
    /**
     * Selects a date.
     *
     * @param date the date, a JS {@code Date}, or an array of them
     * @param opts whether the time is updated and {@code onSelect} fired
     */
    @JsMethod public native void selectDate(Object date, SelectDateOptions opts);
    /**
     * Unselects a date.
     *
     * @param date the date, a JS {@code Date}
     */
    @JsMethod public native void unselectDate(Object date);
    /**
     * Moves the calendar to a date.
     *
     * @param date the date, a JS {@code Date}
     */
    @JsMethod public native void setViewDate(Object date);
    /**
     * Gives a date the keyboard focus.
     *
     * @param date the date, a JS {@code Date}
     */
    @JsMethod public native void setFocusDate(Object date);
    /**
     * Gives a date the keyboard focus.
     *
     * @param date the date, a JS {@code Date}
     * @param opts whether the calendar moves to the date
     */
    @JsMethod public native void setFocusDate(Object date, FocusDateOptions opts);

    // Option objects of the methods above
    /** Options of {@link AirDatepicker#clear}. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class ClearDateOptions {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public ClearDateOptions() {
        }

        /** {@code true} not to fire {@code onSelect}. */
        @JsProperty public boolean silent;
    }

    /** Options of {@link AirDatepicker#selectDate(Object, SelectDateOptions)}. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class SelectDateOptions {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public SelectDateOptions() {
        }

        /** Whether the time of the selected date is updated too. */
        @JsProperty public boolean updateTime;
        /** {@code true} not to fire {@code onSelect}. */
        @JsProperty public boolean silent;
    }

    /** Options of {@link AirDatepicker#setFocusDate(Object, FocusDateOptions)}. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class FocusDateOptions {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public FocusDateOptions() {
        }

        /** Whether the calendar moves to the focused date. */
        @JsProperty public boolean viewDateTransition;
    }
}
