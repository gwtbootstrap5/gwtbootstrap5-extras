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

@JsType(isNative = true, namespace = "tempusDominus", name = "TempusDominus")
public class TempusDominus {

    @JsConstructor
    public TempusDominus(Element element, TempusDominusOptions options) {}

    @JsConstructor
    public TempusDominus(Element element) {}

    // --- Core Display Functions ---
    @JsMethod public native void show();
    @JsMethod public native void hide();
    @JsMethod public native void toggle();
    @JsMethod public native void dispose();
    @JsMethod public native void enable();
    @JsMethod public native void disable();

    // --- Options Functions ---
    @JsMethod public native void updateOptions(TempusDominusOptions options);
    /**
     * Switches this picker to a locale registered with {@code tempusDominus.loadLocale()}.
     */
    @JsMethod public native void locale(String name);

    // --- Date Management API ---
    @JsProperty public Object viewDate; // DateTime, JsDate, or String
    @JsProperty public DatesApi dates;

    @JsType(isNative = true, namespace = "tempusDominus.TempusDominus", name = "DatesApi")
    public static class DatesApi {
        @JsProperty(name = "picked") public native TempusDominusDateTime[] picked(); // getter
        @JsProperty(name = "lastPicked") public native TempusDominusDateTime lastPicked(); // getter
        @JsMethod public native void add(Object date); // DateTime, Date, or String
        @JsMethod public native void setValue(Object date); 
        @JsMethod public native void clear();
        @JsMethod public native String formatInput(TempusDominusDateTime date);
    }
}
