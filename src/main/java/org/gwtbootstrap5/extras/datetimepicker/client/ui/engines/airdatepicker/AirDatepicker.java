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
    @JsMethod public native void update(AirDatepickerOptions options);
    @JsMethod public native void destroy();

    // --- API methods ---
    @JsMethod public native void show();
    @JsMethod public native void hide();
    @JsMethod public native void next();
    @JsMethod public native void prev();
    
    // date is a JS Date or an array of them
    @JsMethod public native void clear(ClearDateOptions opts);
    @JsMethod public native void selectDate(Object date);
    @JsMethod public native void selectDate(Object date, SelectDateOptions opts);
    @JsMethod public native void unselectDate(Object date);
    @JsMethod public native void setViewDate(Object date);
    @JsMethod public native void setFocusDate(Object date);
    @JsMethod public native void setFocusDate(Object date, FocusDateOptions opts);

    // Option objects of the methods above
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class ClearDateOptions {
        @JsProperty public boolean silent;
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class SelectDateOptions {
        @JsProperty public boolean updateTime;
        @JsProperty public boolean silent;
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class FocusDateOptions {
        @JsProperty public boolean viewDateTransition;
    }
}
