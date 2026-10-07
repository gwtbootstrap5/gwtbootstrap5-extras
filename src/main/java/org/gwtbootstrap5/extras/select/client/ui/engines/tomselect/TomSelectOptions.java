package org.gwtbootstrap5.extras.select.client.ui.engines.tomselect;

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

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import elemental2.core.JsArray;
import elemental2.core.JsObject;

/**
 * The settings object passed to {@link TomSelect}'s constructor; the properties left unset keep
 * Tom Select's defaults. See the <a href="https://tom-select.js.org/docs/">Tom Select
 * documentation</a> for each one.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class TomSelectOptions {

    /** The initial options, objects with the value and label fields. */
    @JsProperty public JsArray<JsObject> options;
    /** The values of the initially selected options. */
    @JsProperty public JsArray<String> items;

    /** The text shown when nothing is selected. */
    @JsProperty public String placeholder;
    /** The field of the options that holds their value; {@code "value"} by default. */
    @JsProperty public String valueField;
    /** The field of the options that holds their text; {@code "text"} by default. */
    @JsProperty public String labelField;
    /** The fields the search looks in: a field name or an array of them. */
    @JsProperty public Object searchField;
    /**
     * The search input; {@code null} for none. It is an {@code Object} so it can be set to
     * {@code null}.
     */
    @JsProperty public Object controlInput;
    /** {@code "single"} or {@code "multi"}. */
    @JsProperty public String mode;
    /** The maximum number of selected options, or {@code null} for no limit. */
    @JsProperty public Object maxItems;
    /** The maximum number of options shown in the dropdown, or {@code null} for no limit. */
    @JsProperty public Object maxOptions;

    /** Whether the user can create options that aren't in the list. */
    @JsProperty public boolean create;
    /** Whether the text typed becomes an option when the control loses the focus. */
    @JsProperty public boolean createOnBlur;
    /** Whether the selected options are left out of the dropdown. */
    @JsProperty public boolean hideSelected;
    /** Whether the dropdown closes after an option is selected. */
    @JsProperty public boolean closeAfterSelect;

    /**
     * The plugins to load, for example an array with {@code "remove_button"} or
     * {@code "clear_button"}.
     */
    @JsProperty public Object plugins;

    // --- Remote Data Loading ---

    /**
     * {@code true} to call {@link #load} on creation, or {@code "focus"} to call it on the first
     * focus.
     */
    @JsProperty public Object preload;
    /** Loads the options for a search. */
    @JsProperty public LoadFunction load;

    /**
     * The HTML templates, by name, such as {@code no_results}; each is a {@link RenderFunction}.
     */
    @JsProperty public Object render;

    /**
     * How long to wait, in milliseconds, after the user stops typing before calling {@link #load}.
     */
    @JsProperty public int loadThrottle;

    // --- Callbacks / Events ---

    /** Called when the control is ready. */
    @JsProperty public OnInitializeCallback onInitialize;
    /** Called when the selection changes. */
    @JsProperty public OnChangeCallback onChange;
    /** Called when an option is selected. */
    @JsProperty public OnItemAddCallback onItemAdd;
    /** Called when an option is deselected. */
    @JsProperty public OnItemRemoveCallback onItemRemove;
    /** Called when the dropdown opens. */
    @JsProperty public OnDropdownStateCallback onDropdownOpen;
    /** Called when the dropdown closes. */
    @JsProperty public OnDropdownStateCallback onDropdownClose;
    /** Called when the control gets the focus. */
    @JsProperty public OnFocusCallback onFocus;
    /** Called when the control loses the focus. */
    @JsProperty public OnBlurCallback onBlur;

    /** Creates an empty options object; the properties left unset keep their defaults. */
    public TomSelectOptions() {
    }

    // --- JsFunction Interfaces for Callbacks ---

    /** The {@link #onChange} callback. */
    @JsFunction
    public interface OnChangeCallback {

        /**
         * Called when the selection changes.
         *
         * @param value the selected value, or an array of them when several can be selected
         */
        void onChange(Object value);
    }

    /** The {@link #onItemAdd} callback. */
    @JsFunction
    public interface OnItemAddCallback {

        /**
         * Called when an option is selected.
         *
         * @param value the value of the option
         * @param item the element of the selected option
         */
        void onItemAdd(String value, JsObject item);
    }

    /** The {@link #onItemRemove} callback. */
    @JsFunction
    public interface OnItemRemoveCallback {

        /**
         * Called when an option is deselected.
         *
         * @param value the value of the option
         * @param item the element of the option
         */
        void onItemRemove(String value, JsObject item);
    }

    /** The {@link #onInitialize} callback. */
    @JsFunction
    public interface OnInitializeCallback {

        /** Called when the control is ready. */
        void onInitialize();
    }

    /** The {@link #onFocus} callback. */
    @JsFunction
    public interface OnFocusCallback {

        /** Called when the control gets the focus. */
        void onFocus();
    }

    /** The {@link #onBlur} callback. */
    @JsFunction
    public interface OnBlurCallback {

        /** Called when the control loses the focus. */
        void onBlur();
    }

    // --- JsFunction Interfaces for Loading ---

    /** The {@link #load} function. */
    @JsFunction
    public interface LoadFunction {

        /**
         * Loads the options for a search.
         *
         * @param query the search text
         * @param callback must be called with the options found, or an empty array on error
         */
        void load(String query, LoadResultCallback callback);
    }

    /** The callback Tom Select passes to {@link LoadFunction}. */
    @JsFunction
    public interface LoadResultCallback {

        /**
         * Gives the options found.
         *
         * @param results the options, or an empty array on error
         */
        void onResult(JsArray<Object> results);
    }

    /** The {@link #onDropdownOpen} and {@link #onDropdownClose} callback. */
    @JsFunction
    public interface OnDropdownStateCallback {

        /**
         * Called when the dropdown opens or closes.
         *
         * @param dropdownElement the dropdown element
         */
        void onStateChange(Object dropdownElement);
    }

    // --- JsFunction Interface for Render Templates ---

    /** A template of {@link #render}. */
    @JsFunction
    public interface RenderFunction {

        /**
         * Returns the HTML of the template.
         *
         * @param data the context, such as the search input
         * @param escape a function that escapes HTML, for the text taken from {@code data}
         * @return the HTML
         */
        String render(jsinterop.base.JsPropertyMap<Object> data, Object escape);
    }
}
