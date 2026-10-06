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

/** The callbacks of {@link AirDatepickerOptions}, and the objects they receive. */
public class AirDatepickerCallbacks {

    /** Creates an instance. It only holds nested types and constants, so there is no need to. */
    public AirDatepickerCallbacks() {
    }

    // --- OnSelect ---
    /** Called when a date is selected. */
    @JsFunction
    public interface OnSelect {
        /**
         * Called by Air Datepicker.
         *
         * @param props what the callback receives
         */
        void execute(OnSelectProps props);
    }

    /** What {@link OnSelect} receives. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnSelectProps {
        /** Creates an empty object; the library creates them. */
        public OnSelectProps() {
        }

        /** The selected date as a JS {@code Date}, or an array of them. */
        @JsProperty
        public Object date;
        /** The selected date formatted as a string, or an array of them. */
        @JsProperty
        public String formattedDate;
        /** The datepicker. */
        @JsProperty
        public AirDatepicker datepicker;
    }

    // --- OnBeforeSelect ---
    /** Called before a date is selected. */
    @JsFunction
    public interface OnBeforeSelect {
        /**
         * Called by Air Datepicker.
         *
         * @param props what the callback receives
         */
        void execute(OnSelectProps props);
    }

    /** What {@link OnBeforeSelect} receives. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnBeforeSelectProps {
        /** Creates an empty object; the library creates them. */
        public OnBeforeSelectProps() {
        }

        /** The date about to be selected as a JS {@code Date}, or an array of them. */
        @JsProperty
        public Object date;
        /** The datepicker. */
        @JsProperty
        public AirDatepicker datepicker;
    }

    // --- OnChangeViewDate ---
    /** Called when the calendar moves to another month, year or decade. */
    @JsFunction
    public interface OnChangeViewDate {
        /**
         * Called by Air Datepicker.
         *
         * @param props what the callback receives
         */
        void execute(OnChangeViewDateProps props);
    }

    /** What {@link OnChangeViewDate} receives. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnChangeViewDateProps {
        /** Creates an empty object; the library creates them. */
        public OnChangeViewDateProps() {
        }

        /** The month, from 0. */
        @JsProperty
        public int month;
        /** The year. */
        @JsProperty
        public int year;
        /** The first and last year of the decade. */
        @JsProperty
        public int decade;
    }

    // --- OnChangeView ---
    /** Called when the view changes. */
    @JsFunction
    public interface OnChangeView {
        /**
         * Called by Air Datepicker.
         *
         * @param view the new view
         */
        void execute(String view);
    }

    // --- OnRenderCell ---
    /** Called for each cell drawn. */
    @JsFunction
    public interface OnRenderCell {
        /**
         * Called by Air Datepicker for each cell.
         *
         * @param props the cell
         * @return the changes to the cell, or {@code null} for none
         */
        RenderCellResult execute(OnRenderCellProps props);
    }

    /** What {@link OnRenderCell} receives. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnRenderCellProps {
        /** Creates an empty object; the library creates them. */
        public OnRenderCellProps() {
        }

        /** The date of the cell, as a JS {@code Date}. */
        @JsProperty
        public Object date;
        /** The type of the cell: {@code "day"}, {@code "month"} or {@code "year"}. */
        @JsProperty
        public String cellType;
        /** The datepicker. */
        @JsProperty
        public AirDatepicker datepicker;
    }

    /** What {@link OnRenderCell} returns to change a cell. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class RenderCellResult {
        /** Creates an empty options object; the properties left unset keep their defaults. */
        public RenderCellResult() {
        }

        /** The content of the cell, as HTML. */
        @JsProperty
        public String html;
        /** Extra classes of the cell. */
        @JsProperty
        public String classes;
        /** Whether the cell can't be selected. */
        @JsProperty
        public boolean disabled;
    }

    // --- OnShow / OnHide ---
    /** Called when the calendar opens. */
    @JsFunction
    public interface OnShow {
        /**
         * Called by Air Datepicker.
         *
         * @param isFinished {@code true} once the animation has ended
         */
        void execute(boolean isFinished);
    }

    /** Called when the calendar closes. */
    @JsFunction
    public interface OnHide {
        /**
         * Called by Air Datepicker.
         *
         * @param isFinished {@code true} once the animation has ended
         */
        void execute(boolean isFinished);
    }

    // --- OnClickDayName ---
    /** Called when the name of a day is clicked. */
    @JsFunction
    public interface OnClickDayName {
        /**
         * Called by Air Datepicker.
         *
         * @param props what the callback receives
         */
        void execute(OnClickDayNameProps props);
    }

    /** What {@link OnClickDayName} receives. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnClickDayNameProps {
        /** Creates an empty object; the library creates them. */
        public OnClickDayNameProps() {
        }

        /** The index of the day name that was clicked, from 0. */
        @JsProperty
        public int index;
        /** The datepicker. */
        @JsProperty
        public AirDatepicker datepicker;
    }

    // --- OnFocus ---
    /** Called when a date gets the keyboard focus. */
    @JsFunction
    public interface OnFocus {
        /**
         * Called by Air Datepicker.
         *
         * @param props what the callback receives
         */
        void execute(OnFocusProps props);
    }

    /** What {@link OnFocus} receives. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class OnFocusProps {
        /** Creates an empty object; the library creates them. */
        public OnFocusProps() {
        }

        /** The focused date. */
        @JsProperty
        public JsDate date;
        /** The datepicker. */
        @JsProperty
        public AirDatepicker datepicker;
    }

}
