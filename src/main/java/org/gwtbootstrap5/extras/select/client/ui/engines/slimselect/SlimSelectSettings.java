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
import elemental2.dom.Element;
import elemental2.promise.Promise;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The {@code settings} object passed to {@link SlimSelect}'s constructor, with the settings the
 * engine uses; the properties left unset keep the Slim Select defaults. See the
 * <a href="https://slimselectjs.com/settings">Slim Select settings</a>.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class SlimSelectSettings {

    /** Whether the dropdown has a search box. */
    @JsProperty public boolean showSearch;
    /** The placeholder of the search box. */
    @JsProperty public String searchPlaceholder;
    /** The text shown when a search finds no option. */
    @JsProperty public String searchText;
    /** The text shown when nothing is selected; it needs an option with {@code placeholder}. */
    @JsProperty public String placeholderText;
    /** Whether the selection can be cleared. */
    @JsProperty public boolean allowDeselect;
    /** The maximum number of options a multiple select can select. */
    @JsProperty public int maxSelected;
    /** Whether the dropdown closes when an option is selected. */
    @JsProperty public boolean closeOnSelect;

    /** Creates an empty settings object; the properties left unset keep their defaults. */
    public SlimSelectSettings() {
    }

    /** The object passed to {@link SlimSelect}'s constructor. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Config {

        /** The {@code <select>}. */
        @JsProperty public Element select;
        /** The options. */
        @JsProperty public JsArray<Object> data;
        /** The settings. */
        @JsProperty public SlimSelectSettings settings;
        /** The callbacks. */
        @JsProperty public Events events;

        /** Creates an empty object. */
        public Config() {
        }
    }

    /** The {@code events} object: the callbacks of Slim Select. */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Events {

        /** Loads the options for a search; without it, the search filters the options. */
        @JsProperty public Search search;
        /** Called after the selection changes. */
        @JsProperty public Callback afterChange;
        /** Called before the dropdown opens. */
        @JsProperty public Callback beforeOpen;
        /** Called after the dropdown opens. */
        @JsProperty public Callback afterOpen;
        /** Called before the dropdown closes. */
        @JsProperty public Callback beforeClose;
        /** Called after the dropdown closes. */
        @JsProperty public Callback afterClose;

        /** Creates an empty object. */
        public Events() {
        }
    }

    /** A callback without a result. */
    @JsFunction
    public interface Callback {

        /** Called by Slim Select. */
        void call();
    }

    /** The {@code search} callback. */
    @JsFunction
    public interface Search {

        /**
         * Loads the options for a search.
         *
         * @param searchValue the search text
         * @return the options found
         */
        Promise<JsArray<Object>> search(String searchValue);
    }
}
