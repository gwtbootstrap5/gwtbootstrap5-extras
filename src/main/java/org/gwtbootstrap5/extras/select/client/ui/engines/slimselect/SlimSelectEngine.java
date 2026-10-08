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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectEngine;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectHandlers;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.SelectProperties;

import elemental2.core.JsArray;
import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;
import elemental2.dom.FocusEvent;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLSelectElement;
import elemental2.dom.Node;
import elemental2.promise.Promise;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

/**
 * The {@link ISelectEngine} of {@code SelectEngine.SLIMSELECT}, backed by {@link SlimSelect}.
 * <p>
 * Every setting of {@link SelectProperties} has a Slim Select equivalent. A single select starts
 * with nothing selected, showing its placeholder: the engine adds the empty option Slim Select
 * needs for it. A search loads the options with {@code asyncLoad} on, through the {@code search}
 * event of Slim Select.
 * </p>
 */
public class SlimSelectEngine implements ISelectEngine {

    private static final String VALUE = "value";
    private static final String TEXT = "text";
    private static final String FORM_SELECT = "form-select";

    private HTMLSelectElement element;
    private SlimSelect instance;
    private ISelectHandlers handlers;
    private SelectProperties properties;
    private boolean loadedOnOpen;
    // Set while the engine changes the options: Slim Select reports a change when the placeholder
    // of a single select gets selected, which isn't a change of the user's selection
    private boolean settingData;

    // The options, by value
    private final Map<String, String> options = new LinkedHashMap<>();
    private final List<Object[]> listeners = new ArrayList<>();

    /** Creates an engine; {@code SelectEngine.getEngine} creates them for the selects. */
    public SlimSelectEngine() {
    }

    @Override
    public void init(com.google.gwt.dom.client.SelectElement element, SelectProperties properties,
            ISelectHandlers handlers) {
        this.element = Js.cast(element);
        this.handlers = handlers;
        // Slim Select tells a single select from a multiple one by the attribute
        this.element.multiple = properties.isMultiple();
        createInstance(properties, new ArrayList<>());
    }

    @Override
    public void updateProperties(SelectProperties properties) {
        if (instance != null) {
            List<String> values = getValues();

            removeInstance();
            createInstance(properties, values);
        }
    }

    @Override
    public void destroy() {
        removeInstance();
    }

    @Override
    public void refresh() {
        if (instance != null) {
            setData(toData(getValues()));
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
            if (instance.settings.isOpen) {
                instance.close();
            } else {
                instance.open();
            }
        }
    }

    @Override
    public void focus() {
        if (instance != null) {
            instance.render.main.main.focus();
        }
    }

    @Override
    public void blur() {
        if (instance != null) {
            instance.render.main.main.blur();
        }
    }

    @Override
    public void triggerAsyncLoad(String search) {
        if (instance != null) {
            instance.search(search);
        }
    }

    @Override
    public void clear(boolean silent) {
        if (instance != null) {
            setValues(new ArrayList<>(), silent);
        }
    }

    @Override
    public void clearOptions() {
        if (instance != null) {
            // Like the other engines, the selected options stay
            List<String> selected = getValues();
            options.keySet().retainAll(selected);
            setData(toData(selected));
        }
    }

    @Override
    public void setOptions(List<SelectOption> options) {
        if (instance != null) {
            List<String> selected = getValues();
            this.options.keySet().retainAll(selected);
            for (SelectOption option : options) {
                this.options.put(option.getValue(), option.getText());
            }
            setData(toData(selected));
        }
    }

    @Override
    public void addOption(SelectOption option) {
        List<SelectOption> list = new ArrayList<>();
        list.add(option);
        addOptions(list);
    }

    @Override
    public void addOptions(List<SelectOption> options) {
        if (instance != null) {
            for (SelectOption option : options) {
                this.options.put(option.getValue(), option.getText());
            }
            setData(toData(getValues()));
        }
    }

    @Override
    public List<SelectOption> getOptions() {
        List<SelectOption> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : options.entrySet()) {
            SelectOption option = new SelectOption();
            option.setValue(entry.getKey());
            option.setText(entry.getValue());
            list.add(option);
        }
        return list;
    }

    @Override
    public boolean haveOption(String value) {
        return instance != null && options.containsKey(value);
    }

    @Override
    public void setValue(String value, boolean silent) {
        List<String> values = new ArrayList<>();
        values.add(value);
        setValues(values, silent);
    }

    @Override
    public String getValue() {
        List<String> values = getValues();
        return values.isEmpty() ? null : values.get(0);
    }

    @Override
    public void setValues(List<String> values, boolean silent) {
        if (instance != null) {
            instance.setSelected(values.toArray(new String[0]), !silent);
        }
    }

    @Override
    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        if (instance != null) {
            JsArray<String> selected = instance.getSelected();
            for (int i = 0; i < selected.length; i++) {
                // The placeholder of a single select is an option with an empty value
                if (!selected.getAt(i).isEmpty()) {
                    values.add(selected.getAt(i));
                }
            }
        }
        return values;
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (instance != null) {
            if (enabled) {
                instance.enable();
            } else {
                instance.disable();
            }
        }
    }

    @Override
    public com.google.gwt.dom.client.Element getControlElement() {
        return instance == null ? null : Js.cast(instance.render.main.main);
    }

    @Override
    public boolean isStarted() {
        return instance != null;
    }

    private void createInstance(SelectProperties properties, List<String> selected) {
        this.properties = properties;
        loadedOnOpen = false;

        SlimSelectSettings settings = new SlimSelectSettings();
        settings.showSearch = properties.isSearchEnabled();
        settings.allowDeselect = properties.isAllowClear();
        settings.placeholderText = properties.getPlaceholder() == null ? "" : properties.getPlaceholder();
        settings.maxSelected = properties.isMultiple() && properties.getMultipleLimit() > 0
                ? properties.getMultipleLimit() : 1000;
        settings.closeOnSelect = !properties.isMultiple();
        if (properties.getSearchPlaceholder() != null) {
            settings.searchPlaceholder = properties.getSearchPlaceholder();
        }
        if (properties.getNoResultsText() != null) {
            settings.searchText = properties.getNoResultsText();
        }

        SlimSelectSettings.Events events = new SlimSelectSettings.Events();
        events.afterChange = () -> {
            if (!settingData) {
                handlers.onChange();
            }
        };
        events.beforeOpen = handlers::onShow;
        events.afterOpen = () -> {
            handlers.onShown();

            // search("") empties the search box: not when the user has started typing
            if (properties.isAsyncLoad() && properties.isLoadOnOpen() && !loadedOnOpen && isSearchEmpty()) {
                loadedOnOpen = true;
                instance.search("");
            }
        };
        events.beforeClose = handlers::onHide;
        events.afterClose = handlers::onHidden;
        if (properties.isAsyncLoad()) {
            events.search = this::load;
        }

        SlimSelectSettings.Config config = new SlimSelectSettings.Config();
        config.select = element;
        config.data = toData(selected);
        config.settings = settings;
        config.events = events;

        // Slim Select copies the classes of the <select> to its control and its dropdown, and
        // form-select would add Bootstrap's arrow and padding to both
        element.classList.remove(FORM_SELECT);
        instance = new SlimSelect(config);
        addListeners();
        handlers.onLoaded();
    }

    private void removeInstance() {
        if (instance != null) {
            for (Object[] listener : listeners) {
                ((EventTarget) listener[0]).removeEventListener((String) listener[1], (EventListener) listener[2]);
            }
            listeners.clear();

            instance.destroy();
            instance = null;
            element.classList.add(FORM_SELECT);
        }
    }

    private void addListeners() {
        // The dropdown is a separate element in <body>: moving the focus between it and the
        // control is neither a focus nor a blur of the select
        EventListener focusIn = evt -> {
            if (!isInside(((FocusEvent) evt).relatedTarget)) {
                handlers.onFocus();
            }
        };
        EventListener focusOut = evt -> {
            if (!isInside(((FocusEvent) evt).relatedTarget)) {
                handlers.onBlur();
            }
        };
        for (EventTarget part : new EventTarget[] {instance.render.main.main, instance.render.content.main}) {
            listen(part, "focusin", focusIn);
            listen(part, "focusout", focusOut);
        }
    }

    private boolean isSearchEmpty() {
        HTMLInputElement input = Js.uncheckedCast(instance.render.content.main.querySelector(".ss-search input"));
        return input == null || input.value.isEmpty();
    }

    private boolean isInside(EventTarget target) {
        return target != null && instance != null
                && (instance.render.main.main.contains((Node) target)
                        || instance.render.content.main.contains((Node) target));
    }

    private void listen(EventTarget target, String type, EventListener listener) {
        target.addEventListener(type, listener);
        listeners.add(new Object[] {target, type, listener});
    }

    private Promise<JsArray<Object>> load(String query) {
        return new Promise<>((resolve, reject) -> handlers.onAsyncLoad(query, found -> {
            if (instance == null) {
                return;
            }

            List<String> selected = getValues();
            options.keySet().retainAll(selected);
            for (SelectOption option : found) {
                options.put(option.getValue(), option.getText());
            }

            // The options found become the options of the select, so they are still there when
            // the search is cleared; the promise shows them as the result of this search
            JsArray<Object> data = toData(selected);
            setData(data);
            resolve.onInvoke(data);
        }));
    }

    private void setData(JsArray<Object> data) {
        settingData = true;
        try {
            instance.setData(data);
        } finally {
            settingData = false;
        }
    }

    private JsArray<Object> toData(List<String> selected) {
        JsArray<Object> data = new JsArray<>();
        if (!properties.isMultiple()) {
            data.push(JsPropertyMap.of(VALUE, "", TEXT, "", "placeholder", true));
        }
        for (Map.Entry<String, String> entry : options.entrySet()) {
            data.push(JsPropertyMap.of(VALUE, entry.getKey(), TEXT, entry.getValue(), "selected",
                    selected.contains(entry.getKey())));
        }
        return data;
    }
}
