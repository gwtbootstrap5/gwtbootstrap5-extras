package org.gwtbootstrap5.extras.select.client.ui.engines.slimselect;


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
import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Native binding of <a href="https://slimselectjs.com/">Slim Select</a> 4, the library behind
 * {@link SlimSelectEngine}, with the methods the engine uses. They map one to one to the
 * <a href="https://slimselectjs.com/methods">Slim Select methods</a>.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "SlimSelect")
public class SlimSelect {

    /**
     * Creates a Slim Select.
     *
     * @param config the {@code <select>}, the options, the settings and the events
     */
    public SlimSelect(SlimSelectSettings.Config config) {}

    /** The elements Slim Select draws ({@code render}). */
    @JsProperty
    public Render render;

    /** The settings in force ({@code settings}), which tell whether the dropdown is open. */
    @JsProperty
    public State settings;

    /** Enables the select. */
    public native void enable();

    /** Disables the select. */
    public native void disable();

    /**
     * Replaces the options.
     *
     * @param data the options, objects with {@code value}, {@code text} and {@code selected}
     */
    public native void setData(JsArray<Object> data);

    /**
     * Returns the values of the selected options.
     *
     * @return the values
     */
    public native JsArray<String> getSelected();

    /**
     * Selects options, deselecting the others.
     *
     * @param values the values of the options
     * @param runAfterChange {@code true} to fire the {@code afterChange} event
     */
    public native void setSelected(String[] values, boolean runAfterChange);

    /** Opens the dropdown. */
    public native void open();

    /** Closes the dropdown. */
    public native void close();

    /**
     * Searches, as if the user had typed in the search box.
     *
     * @param value the search text
     */
    public native void search(String value);

    /** Removes Slim Select and restores the original {@code <select>}. */
    public native void destroy();

    /** The elements Slim Select draws. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Render {

        /** Don't call it: Slim Select creates it. */
        protected Render() {
        }

        /** The control that replaces the {@code <select>}. */
        @JsProperty
        public Part main;

        /** The dropdown, with the search box and the options. */
        @JsProperty
        public Part content;
    }

    /** A part of the drawn select. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Part {

        /** Don't call it: Slim Select creates it. */
        protected Part() {
        }

        /** The element of the part. */
        @JsProperty
        public HTMLElement main;
    }

    /** The state Slim Select keeps in its settings. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class State {

        /** Don't call it: Slim Select creates it. */
        protected State() {
        }

        /** Whether the dropdown is open. */
        @JsProperty
        public boolean isOpen;
    }
}
