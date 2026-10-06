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

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * JsInterop binding of {@code tempusDominus.DateTime}, the {@code Date} subclass of Tempus
 * Dominus.
 */
@JsType(isNative = true, namespace = "tempusDominus", name = "DateTime")
public class TempusDominusDateTime {

    /** Creates the current date and time. */
    @JsConstructor
    public TempusDominusDateTime() {}

    /**
     * Creates a date.
     *
     * @param epochMilliseconds the time, in milliseconds since 1970
     */
    @JsConstructor
    public TempusDominusDateTime(double epochMilliseconds) {}

    /**
     * Creates a date.
     *
     * @param date a JS {@code Date} or a string
     */
    @JsConstructor
    public TempusDominusDateTime(Object date) {} // Can pass a JS Date or String

    /** The year. */
    @JsProperty public int year;
    /** The month, from 0 (January). */
    @JsProperty public int month;
    /** The day of the month. */
    @JsProperty public int date;
    /** The hours. */
    @JsProperty public int hours;
    /** The minutes. */
    @JsProperty public int minutes;
    /** The seconds. */
    @JsProperty public int seconds;

    @JsMethod public native TempusDominusDateTime clone();
    /**
     * Formats the date.
     *
     * @param formatString the format, such as {@code "dd/MM/yyyy"}
     * @return the formatted date
     */
    @JsMethod public native String format(String formatString);
    /**
     * Returns whether the date is before another.
     *
     * @param other the other date
     * @return {@code true} if it is
     */
    @JsMethod public native boolean isBefore(TempusDominusDateTime other);
    /**
     * Returns whether the date is after another.
     *
     * @param other the other date
     * @return {@code true} if it is
     */
    @JsMethod public native boolean isAfter(TempusDominusDateTime other);
    /**
     * Returns whether the date is the same as another.
     *
     * @param other the other date
     * @return {@code true} if it is
     */
    @JsMethod public native boolean isSame(TempusDominusDateTime other);
}
