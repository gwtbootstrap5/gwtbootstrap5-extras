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

import elemental2.dom.Element;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * JsInterop binding of a Tempus Dominus instance:
 * {@code new tempusDominus.TempusDominus(element, options)}.
 */
@JsType(isNative = true, namespace = "tempusDominus", name = "TempusDominus")
public class TempusDominus {

    /**
     * Creates a picker on an element.
     *
     * @param element the input or its container
     * @param options the options
     */
    @JsConstructor
    public TempusDominus(Element element, TempusDominusOptions options) {}

    /**
     * Creates a picker on an element.
     *
     * @param element the input or its container
     */
    @JsConstructor
    public TempusDominus(Element element) {}

    // --- Core Display Functions ---
    /** Opens the picker. */
    @JsMethod public native void show();
    /** Closes the picker. */
    @JsMethod public native void hide();
    /** Opens the picker if it is closed, closes it otherwise. */
    @JsMethod public native void toggle();
    /** Destroys the picker and removes its widget from the page. */
    @JsMethod public native void dispose();
    /** Enables the picker and its input. */
    @JsMethod public native void enable();
    /** Disables the picker and its input. */
    @JsMethod public native void disable();

    // --- Options Functions ---
    /**
     * Applies new options.
     *
     * @param options the options to change
     */
    @JsMethod public native void updateOptions(TempusDominusOptions options);
    /**
     * Switches this picker to a locale registered with {@code tempusDominus.loadLocale()}.
     *
     * @param name the name of the locale, such as {@code "es"}
     */
    @JsMethod public native void locale(String name);

    // --- Date Management API ---
    /** The date the view shows: a {@code DateTime}, a JS {@code Date} or a string. */
    @JsProperty public Object viewDate;
    /** The selected dates. */
    @JsProperty public DatesApi dates;

    /** The selected dates of a Tempus Dominus picker. */
    @JsType(isNative = true, namespace = "tempusDominus.TempusDominus", name = "DatesApi")
    public static class DatesApi {
        /** Don't call it: the picker has one, {@code dates}. */
        public DatesApi() {
        }

        /**
         * Returns the picked dates.
         *
         * @return the dates
         */
        @JsProperty(name = "picked") public native TempusDominusDateTime[] picked();
        /**
         * Returns the date picked last.
         *
         * @return the date, or {@code null}
         */
        @JsProperty(name = "lastPicked") public native TempusDominusDateTime lastPicked();
        /**
         * Adds a date to the picked dates.
         *
         * @param date a {@code DateTime}, a JS {@code Date} or a string
         */
        @JsMethod public native void add(Object date);
        /**
         * Replaces the picked date.
         *
         * @param date a {@code DateTime}, a JS {@code Date} or a string, or {@code null} to clear it
         */
        @JsMethod public native void setValue(Object date); 
        /** Clears the picked dates. */
        @JsMethod public native void clear();
        /**
         * Formats a date the way the input shows it.
         *
         * @param date the date
         * @return the text
         */
        @JsMethod public native String formatInput(TempusDominusDateTime date);
    }
}
