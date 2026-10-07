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

import elemental2.core.JsArray;
import elemental2.dom.Element;
import jsinterop.annotations.*;
import jsinterop.base.JsPropertyMap;

/**
 * Native binding of <a href="https://tom-select.js.org/">Tom Select</a> 2, the library behind
 * {@link TomSelectEngine}. The methods map one to one to the
 * <a href="https://tom-select.js.org/docs/api/">Tom Select API</a>.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "TomSelect")
public class TomSelect {

    // --- Constructors ---

    /**
     * Creates a Tom Select with the default options.
     *
     * @param cssSelector a CSS selector of the {@code <select>} or {@code <input>}
     */
    public TomSelect(String cssSelector) {}

    /**
     * Creates a Tom Select.
     *
     * @param cssSelector a CSS selector of the {@code <select>} or {@code <input>}
     * @param options the options
     */
    public TomSelect(String cssSelector, TomSelectOptions options) {}

    /**
     * Creates a Tom Select with the default options.
     *
     * @param element the {@code <select>} or {@code <input>}
     */
    public TomSelect(Element element) {}

    /**
     * Creates a Tom Select.
     *
     * @param element the {@code <select>} or {@code <input>}
     * @param options the options
     */
    public TomSelect(Element element, TomSelectOptions options) {}

    // --- State Properties ---

    /**
     * Returns whether the dropdown is open ({@code isOpen}).
     *
     * @return {@code true} if it is open
     */
    @JsProperty(name = "isOpen")
    public native boolean isOpen();

    // --- API Methods ---

    /**
     * Adds an option.
     *
     * @param option the option, an object with the value and label fields
     */
    @JsMethod
    public native void addOption(Object option);

    /**
     * Adds options.
     *
     * @param option the options
     */
    @JsMethod
    public native void addOptions(JsArray<Object> option);

    /**
     * Replaces an option.
     *
     * @param value the value of the option to replace
     * @param option the new option
     */
    @JsMethod
    public native void updateOption(String value, Object option);

    /**
     * Removes an option.
     *
     * @param value the value of the option
     */
    @JsMethod
    public native void removeOption(String value);

    /** Removes the options that aren't selected. */
    @JsMethod
    public native void clearOptions();

    /**
     * Removes the options that aren't selected and that the filter doesn't keep.
     *
     * @param filter returns {@code true} for the options to keep
     */
    @JsMethod
    public native void clearOptions(OptionFilterCallback filter);

    /**
     * Redraws the list of options.
     *
     * @param triggerDropdown {@code true} to open the dropdown too
     */
    @JsMethod
    public native void refreshOptions(boolean triggerDropdown);

    /**
     * Selects an option.
     *
     * @param value the value of the option
     */
    @JsMethod
    public native void addItem(String value);

    /**
     * Selects an option.
     *
     * @param value the value of the option
     * @param silent {@code true} not to fire the change event
     */
    @JsMethod
    public native void addItem(String value, boolean silent);

    /**
     * Deselects an option.
     *
     * @param value the value of the option
     */
    @JsMethod
    public native void removeItem(String value);

    /**
     * Deselects an option.
     *
     * @param value the value of the option
     * @param silent {@code true} not to fire the change event
     */
    @JsMethod
    public native void removeItem(String value, boolean silent);

    /** Deselects everything. */
    @JsMethod
    public native void clear();

    /**
     * Deselects everything.
     *
     * @param silent {@code true} not to fire the change event
     */
    @JsMethod
    public native void clear(boolean silent);

    /**
     * Returns the selection.
     *
     * @return the value of the selected option, or an array of values when several can be selected
     */
    @JsMethod
    public native Object getValue();

    /**
     * Selects one option.
     *
     * @param value the value of the option
     */
    @JsMethod
    public native void setValue(String value);

    /**
     * Selects several options.
     *
     * @param values the values of the options
     */
    @JsMethod
    public native void setValue(String[] values);

    /**
     * Selects one option.
     *
     * @param value the value of the option
     * @param silent {@code true} not to fire the change event
     */
    @JsMethod
    public native void setValue(String value, boolean silent);

    /**
     * Loads the options for a search with the {@code load} option.
     *
     * @param query the search text
     */
    @JsMethod
    public native void load(String query);

    /** Stops the user from changing the selection; the control can still get the focus. */
    @JsMethod
    public native void lock();

    /** Undoes {@link #lock()}. */
    @JsMethod
    public native void unlock();

    /** Gives the focus to the control. */
    @JsMethod
    public native void focus();

    /** Takes the focus away from the control. */
    @JsMethod
    public native void blur();

    /** Enables the control. */
    @JsMethod
    public native void enable();

    /** Disables the control. */
    @JsMethod
    public native void disable();

    /** Removes Tom Select and restores the original element. */
    @JsMethod
    public native void destroy();

    /** Updates Tom Select from the options of the original element. */
    @JsMethod
    public native void sync();

    /** Opens the dropdown. */
    @JsMethod
    public native void open();

    /** Closes the dropdown. */
    @JsMethod
    public native void close();

    /** Moves the dropdown under the control, after the control moved. */
    @JsMethod
    public native void positionDropdown();

    /**
     * A callback function used to filter which options to keep or remove.
     * Return true to keep the option, false to clear it.
     */
    @JsFunction
    public interface OptionFilterCallback {
        /**
         * Decides whether to keep an option.
         *
         * @param option the option
         * @return {@code true} to keep it
         */
        boolean filter(Object option);
    }

    /**
     * Returns the options by value ({@code options}).
     *
     * @return the options
     */
    @JsProperty(name = "options")
    public native JsPropertyMap<Object> getOptionsMap();

}
