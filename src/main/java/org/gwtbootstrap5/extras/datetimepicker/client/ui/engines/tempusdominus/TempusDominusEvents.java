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

/** Names and details of the events that Tempus Dominus fires on its input. */
public class TempusDominusEvents {
    /** Creates an instance. It only holds nested types and constants, so there is no need to. */
    public TempusDominusEvents() {
    }

    // Event Names
    /** Fired when the selected date changes. */
    public static final String CHANGE = "change.td";
    /** Fired when the view changes. */
    public static final String UPDATE = "update.td";
    /** Fired when a date can't be selected. */
    public static final String ERROR = "error.td";
    /** Fired when the picker opens. */
    public static final String SHOW = "show.td";
    /** Fired when the picker closes. */
    public static final String HIDE = "hide.td";

    // Detail object emitted in the 'change.td' CustomEvent
    /** Detail of the change event. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class ChangeEventDetail {
        /** Creates an empty object; the library creates them. */
        public ChangeEventDetail() {
        }

        /** The newly selected date. */
        @JsProperty public TempusDominusDateTime date;
        /** The previously selected date. */
        @JsProperty public TempusDominusDateTime oldDate;
        /** Whether the new date is valid. */
        @JsProperty public boolean isValid;
    }
    
    // Detail object emitted in the 'error.td' CustomEvent
    /** Detail of the error event. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class ErrorEventDetail {
        /** Creates an empty object; the library creates them. */
        public ErrorEventDetail() {
        }

        /** The kind of error. */
        @JsProperty public String type;
        /** The error message. */
        @JsProperty public String message;
        /** The date that caused the error. */
        @JsProperty public TempusDominusDateTime date;
    }
}
