package org.gwtbootstrap5.extras.select.client.ui.engines.choicesjs;


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
import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Native binding of <a href="https://choices-js.github.io/Choices/">Choices.js</a> 11, the library
 * behind {@link ChoicesEngine}, with the methods the engine uses. They map one to one to the
 * <a href="https://github.com/Choices-js/Choices#methods">Choices.js methods</a>.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Choices")
public class Choices {

    /**
     * Creates a Choices.js select on a {@code <select>}.
     *
     * @param element the {@code <select>}
     * @param options the options
     */
    public Choices(Element element, ChoicesOptions options) {}

    /** The element that wraps the select, the dropdown and the search input ({@code containerOuter}). */
    @JsProperty
    public Component containerOuter;

    /** The dropdown ({@code dropdown}). */
    @JsProperty
    public Component dropdown;

    /** The search input ({@code input}). */
    @JsProperty
    public Component input;

    /** Removes Choices.js and restores the original {@code <select>}. */
    public native void destroy();

    /** Enables the select. */
    public native void enable();

    /** Disables the select. */
    public native void disable();

    /** Opens the dropdown. */
    public native void showDropdown();

    /** Closes the dropdown. */
    public native void hideDropdown();

    /**
     * Adds options, or replaces them.
     *
     * @param choices the options, objects with the fields named by {@code value} and {@code label}
     * @param value the field that holds the value of an option
     * @param label the field that holds the text of an option
     * @param replaceChoices {@code true} to remove the options that aren't selected first
     */
    public native void setChoices(JsArray<Object> choices, String value, String label, boolean replaceChoices);

    /** Removes the options that aren't selected. */
    public native void clearChoices();

    /**
     * Returns the selection.
     *
     * @param valueOnly {@code true} for the values instead of the option objects
     * @return the selected value, or an array of them in a multiple select; {@code undefined} if
     *     nothing is selected in a single select
     */
    public native Object getValue(boolean valueOnly);

    /**
     * Selects options. It doesn't fire {@code change}.
     *
     * @param values the values of the options
     */
    public native void setChoiceByValue(String[] values);

    /** Deselects everything. It doesn't fire {@code change}. */
    public native void removeActiveItems();

    /** A part of the Choices.js select. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Component {

        /** Don't call it: Choices.js creates its parts. */
        protected Component() {
        }

        /** The element of the part. */
        @JsProperty
        public HTMLElement element;

        /** Whether the part is shown; for the dropdown, whether it is open. */
        @JsProperty
        public boolean isActive;
    }
}
