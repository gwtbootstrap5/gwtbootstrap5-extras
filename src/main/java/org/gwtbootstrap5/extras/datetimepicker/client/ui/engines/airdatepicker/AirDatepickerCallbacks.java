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

import elemental2.core.JsDate;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

public class AirDatepickerCallbacks {

    // --- OnSelect ---
    @JsFunction
    public interface OnSelect {
        void execute(OnSelectProps props);
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnSelectProps {
        /** The selected date as a JS {@code Date}, or an array of them. */
        @JsProperty
        public Object date;
        /** The selected date formatted as a string, or an array of them. */
        @JsProperty
        public String formattedDate;
        @JsProperty
        public AirDatepicker datepicker;
    }

    // --- OnBeforeSelect ---
    @JsFunction
    public interface OnBeforeSelect {
        void execute(OnSelectProps props);
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnBeforeSelectProps {
        /** The date about to be selected as a JS {@code Date}, or an array of them. */
        @JsProperty
        public Object date;
        @JsProperty
        public AirDatepicker datepicker;
    }

    // --- OnChangeViewDate ---
    @JsFunction
    public interface OnChangeViewDate {
        void execute(OnChangeViewDateProps props);
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnChangeViewDateProps {
        @JsProperty
        public int month;
        @JsProperty
        public int year;
        @JsProperty
        public int decade;
    }

    // --- OnChangeView ---
    @JsFunction
    public interface OnChangeView {
        void execute(String view);
    }

    // --- OnRenderCell ---
    @JsFunction
    public interface OnRenderCell {
        RenderCellResult execute(OnRenderCellProps props);
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnRenderCellProps {
        /** The date of the cell, as a JS {@code Date}. */
        @JsProperty
        public Object date;
        /** The type of the cell: {@code "day"}, {@code "month"} or {@code "year"}. */
        @JsProperty
        public String cellType;
        @JsProperty
        public AirDatepicker datepicker;
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class RenderCellResult {
        @JsProperty
        public String html;
        @JsProperty
        public String classes;
        @JsProperty
        public boolean disabled;
    }

    // --- OnShow / OnHide ---
    @JsFunction
    public interface OnShow {
        void execute(boolean isFinished);
    }

    @JsFunction
    public interface OnHide {
        void execute(boolean isFinished);
    }

    // --- OnClickDayName ---
    @JsFunction
    public interface OnClickDayName {
        void execute(OnClickDayNameProps props);
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnClickDayNameProps {
        /** The index of the day name that was clicked, from 0. */
        @JsProperty
        public int index;
        @JsProperty
        public AirDatepicker datepicker;
    }

    // --- OnFocus ---
    @JsFunction
    public interface OnFocus {
        void execute(OnFocusProps props);
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnFocusProps {
        /** The focused date. */
        @JsProperty
        public JsDate date;
        @JsProperty
        public AirDatepicker datepicker;
    }

}
