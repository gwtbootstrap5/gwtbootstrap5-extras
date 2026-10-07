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
import elemental2.core.JsObject;
import elemental2.dom.HTMLSelectElement;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectEngine;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectHandlers;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.SelectProperties;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@link org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectEngine} of
 * {@code SelectEngine.TOMSELECT}, backed by {@link TomSelect}.
 */
public class TomSelectEngine implements ISelectEngine {

    /** Creates an engine; {@code SelectEngine.getEngine} creates them for the selects. */
    public TomSelectEngine() {
    }

    private HTMLSelectElement element;
    private TomSelect instance;
    private TomSelectOptions properties;
    private ISelectHandlers handlers;

    @Override
    public void init(com.google.gwt.dom.client.SelectElement element, SelectProperties properties, ISelectHandlers handlers) {
        // 1. Select
        this.element = Js.cast(element);

        // 2. Translate properties
        this.properties = translateProperties(properties);

        // 3. Setup event listeners
        this.handlers = handlers;
        addHandlers(this.properties, this.handlers);

        // 4. Initialize Tom Select on a DOM element
        this.instance = new TomSelect(this.element, this.properties);
    }

    @Override
    public void updateProperties(SelectProperties properties) {
        if (instance != null) {
            List<String> values = getValues();
            List<SelectOption> options = getOptions();

            instance.destroy();
            this.properties = translateProperties(properties);
            addHandlers(this.properties, this.handlers);
            instance = new TomSelect(this.element, this.properties);

            setOptions(options);
            setValues(values, false);
        }
    }

    @Override
    public void destroy() {
        if (instance != null) {
            instance.destroy();
            instance = null;
        }
    }

    @Override
    public void refresh() {
        if (instance != null) {
            instance.sync();
        }
    }

    @Override
    public void show() {
        if (instance != null) {
            instance.open();
        }
    }

    @Override
    public void hide() {
        if (instance != null) {
            instance.close();
        }
    }

    @Override
    public void toggle() {
        if (instance != null) {
            if (instance.isOpen()) {
                instance.close();
            } else {
                instance.open();
            }
        }
    }

    @Override
    public void focus() {
        if (instance != null) {
            instance.focus();
        }
    }

    @Override
    public void blur() {
        if (instance != null) {
            instance.blur();
        }
    }

    @Override
    public void triggerAsyncLoad(String search) {
        if (instance != null) {
            instance.load(search);
        }
    }

    @Override
    public void clear(boolean silent) {
        if (instance != null) {
            instance.clear(silent);
        }
    }

    @Override
    public void clearOptions() {
        if (instance != null) {
            instance.clearOptions();
        }
    }

    @Override
    public void setOptions(List<SelectOption> options) {
        if (instance != null) {
            this.instance.clearOptions();

            addOptions(options);
        }
    }

    @Override
    public void addOption(SelectOption option) {
        if (instance != null) {
            this.instance.addOption(getObjectFromSelectOptions(option));

            this.instance.refreshOptions(false);
        }
    }

    @Override
    public void addOptions(List<SelectOption> options) {
        if (instance != null) {
            this.instance.addOptions(getObjectJsArrayFromSelectOptions(options));

            this.instance.refreshOptions(false);
        }
    }

    @Override
    public List<SelectOption> getOptions() {
        List<SelectOption> options = new ArrayList<>();

        if (instance != null) {
            // 1. Get the internal dictionary map
            JsPropertyMap<Object> optionsMap = instance.getOptionsMap();

            // 2. Use Elemental2's JsObject.values() to extract just the data into an array
            // (We cast the map to an Object to satisfy the method signature)
            JsArray<Object> allOptionsList = JsObject.values(optionsMap);

            // 3. Now you have a standard JsArray you can loop through!
            for (int i = 0; i < allOptionsList.length; i++) {
                Object optionData = allOptionsList.getAt(i);

                // Cast it back to a property map to read its fields
                JsPropertyMap<String> itemMap = jsinterop.base.Js.cast(optionData);

                SelectOption option = new SelectOption();
                option.setValue(Js.cast(itemMap.get("value")));
                option.setText(Js.cast(itemMap.get("text")));
                options.add(option);
            }
        }

        return options;
    }

    @Override
    public boolean haveOption(String value) {
        if (instance != null) {
            return instance.getOptionsMap().has(value);
        }

        return false;
    }

    @Override
    public void setValue(String value, boolean silent) {
        if (instance != null) {
            instance.setValue(value, silent);
        }
    }

    @Override
    public String getValue() {
        if (instance != null) {
            return instance.getValue().toString();
        }

        return null;
    }

    @Override
    public void setValues(List<String> values, boolean silent) {
        if (instance != null) {
            instance.setValue(values.toArray(new String[0]));
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (instance !=  null) {
            if (enabled) {
                instance.enable();
            } else {
                instance.disable();
            }
        }
    }

    @Override
    public List<String> getValues() {
        if (instance != null && instance.getValue() != null) {
            if (instance.getValue() instanceof JsArray) {
                JsArray<Object> data = (JsArray<Object>) instance.getValue();
                List<String> values = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    values.add(data.getAt(i).toString());
                }

                return values;
            } else {
                return Collections.singletonList(instance.getValue().toString());
            }
        }

        return List.of();
    }

    @Override
    public boolean isStarted() {
        return instance != null;
    }

    private TomSelectOptions translateProperties(SelectProperties properties) {
        TomSelectOptions opt = new TomSelectOptions();

        if (!properties.isSearchEnabled()) {
            opt.controlInput = null;
        }

        if (properties.isAllowClear()) {
            if (properties.isMultiple()) {
                opt.plugins = new String[]{"remove_button"};
            } else {
                opt.plugins = new String[]{"clear_button"};
            }
        }

        if (!properties.isMultiple()) {
            opt.mode = "single";
            opt.maxItems = 1;
        } else {
            if (properties.getMultipleLimit() <= 0) {
                opt.maxItems = null;
            } else {
                opt.maxItems = properties.getMultipleLimit();
            }
        }

        if (properties.getNoResultsText() != null) {
            // 1. Create a map to hold your render functions
            JsPropertyMap<Object> renderTemplates = JsPropertyMap.of();

            // 2. Define the 'no_results' template
            renderTemplates.set("no_results", (TomSelectOptions.RenderFunction) (data, escape) -> "<div class=\"no-results\" style=\"padding: 10px; color: gray;\">" + properties.getNoResultsText() + "</div>");

            // 3. Assign the templates to your options
            opt.render = renderTemplates;
        }

        opt.placeholder = properties.getPlaceholder();

        opt.load = (query, callback) -> handlers.onAsyncLoad(query, cb -> callback.onResult(getObjectJsArrayFromSelectOptions(cb)));

        if (properties.isLoadOnOpen()) {
            opt.preload = "focus";
        }

        return opt;
    }

    private static void addHandlers(TomSelectOptions opt, ISelectHandlers handlers) {
        opt.onInitialize = handlers::onLoaded;
        opt.onChange = value -> handlers.onChange();
        opt.onDropdownOpen = value -> {
            handlers.onShow();
            handlers.onShown();
        };
        opt.onDropdownClose = value ->  {
            handlers.onHide();
            handlers.onHidden();
        };
        opt.onFocus = handlers::onFocus;
        opt.onBlur = handlers::onBlur;
    }

    private static @NonNull Object getObjectFromSelectOptions(SelectOption option) {
        // 1. Create a pure, guaranteed JavaScript object
        JsPropertyMap<Object> jsMap = JsPropertyMap.of();

        // 2. Safely map the properties so they survive production obfuscation
        jsMap.set("value", option.getValue());
        jsMap.set("text", option.getText());

        return Js.cast(jsMap);
    }

    private static @NonNull JsArray<Object> getObjectJsArrayFromSelectOptions(List<SelectOption> options) {
        JsArray<Object> jsArray = new JsArray<>();
        for (SelectOption item : options) {
            jsArray.push(getObjectFromSelectOptions(item));
        }
        return jsArray;
    }

}
