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

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The settings object passed to {@link Choices}'s constructor, with the options the engine uses;
 * the properties left unset keep the Choices.js defaults. See the
 * <a href="https://github.com/Choices-js/Choices#configuration-options">Choices.js options</a>.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class ChoicesOptions {

    /** Whether the select has a search box. */
    @JsProperty public boolean searchEnabled;
    /** Whether the search filters the options; off when a search loads them instead. */
    @JsProperty public boolean searchChoices;
    /** Whether each selected option has a button that deselects it. */
    @JsProperty public boolean removeItemButton;
    /** The maximum number of options a multiple select can select, or -1 for no limit. */
    @JsProperty public int maxItemCount;
    /** Whether the select shows a placeholder. */
    @JsProperty public boolean placeholder;
    /** The text shown when nothing is selected. */
    @JsProperty public String placeholderValue;
    /** The placeholder of the search box. */
    @JsProperty public String searchPlaceholderValue;
    /** The text shown when a search finds no option. */
    @JsProperty public String noResultsText;
    /** The text shown when there is no option left to choose. */
    @JsProperty public String noChoicesText;
    /** The text next to the highlighted option; empty for none. */
    @JsProperty public String itemSelectText;
    /** Whether the options are sorted; off, so they keep the order they were given in. */
    @JsProperty public boolean shouldSort;
    /** Whether the text of the options is HTML; off, so it is escaped. */
    @JsProperty public boolean allowHTML;
    /** Called when Choices.js is ready. */
    @JsProperty public CallbackOnInit callbackOnInit;

    /** Creates an empty options object; the properties left unset keep their defaults. */
    public ChoicesOptions() {
    }

    /** The {@link #callbackOnInit} callback. */
    @JsFunction
    public interface CallbackOnInit {

        /** Called when Choices.js is ready. */
        void onInit();
    }
}
