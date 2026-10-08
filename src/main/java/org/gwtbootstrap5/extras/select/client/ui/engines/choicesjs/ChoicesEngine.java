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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectEngine;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectHandlers;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.SelectProperties;

import elemental2.core.JsArray;
import elemental2.dom.EventListener;
import elemental2.dom.FocusEvent;
import elemental2.dom.HTMLSelectElement;
import elemental2.dom.Node;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

/**
 * The {@link ISelectEngine} of {@code SelectEngine.CHOICESJS}, backed by {@link Choices}.
 * <p>
 * Every setting of {@link SelectProperties} has a Choices.js equivalent. Choices.js has no event
 * before its dropdown opens or closes, so the show and shown events, and the hide and hidden
 * events, fire together. A search loads the options with {@code asyncLoad} on: the engine listens
 * to the {@code search} event of Choices.js and replaces the options with the ones found.
 * </p>
 */
public class ChoicesEngine implements ISelectEngine {

    private static final String VALUE = "value";
    private static final String LABEL = "label";

    private HTMLSelectElement element;
    private Choices instance;
    private ISelectHandlers handlers;
    private SelectProperties properties;
    private boolean loadedOnOpen;

    // The options, by value: Choices.js keeps them in an internal store
    private final Map<String, String> options = new LinkedHashMap<>();
    private final List<Object[]> listeners = new ArrayList<>();

    /** Creates an engine; {@code SelectEngine.getEngine} creates them for the selects. */
    public ChoicesEngine() {
    }

    @Override
    public void init(com.google.gwt.dom.client.SelectElement element, SelectProperties properties,
            ISelectHandlers handlers) {
        this.element = Js.cast(element);
        this.handlers = handlers;
        // Choices.js tells a single select from a multiple one by the attribute
        this.element.multiple = properties.isMultiple();
        createInstance(properties);
    }

    @Override
    public void updateProperties(SelectProperties properties) {
        if (instance != null) {
            List<String> values = getValues();

            removeInstance();
            createInstance(properties);

            setValues(values, true);
        }
    }

    @Override
    public void destroy() {
        removeInstance();
    }

    @Override
    public void refresh() {
        if (instance != null) {
            // Choices.js keeps the selected options when it clears the others
            List<String> selected = getValues();
            instance.clearChoices();
            List<SelectOption> unselected = new ArrayList<>();
            for (SelectOption option : toSelectOptions()) {
                if (!selected.contains(option.getValue())) {
                    unselected.add(option);
                }
            }
            addChoices(unselected);
        }
    }

    @Override
    public void show() {
        if (instance != null) {
            instance.showDropdown();
        }
    }

    @Override
    public void hide() {
        if (instance != null) {
            instance.hideDropdown();
        }
    }

    @Override
    public void toggle() {
        if (instance != null) {
            if (instance.dropdown.isActive) {
                instance.hideDropdown();
            } else {
                instance.showDropdown();
            }
        }
    }

    @Override
    public void focus() {
        if (instance != null) {
            instance.containerOuter.element.focus();
        }
    }

    @Override
    public void blur() {
        if (instance != null) {
            instance.containerOuter.element.blur();
            instance.input.element.blur();
        }
    }

    @Override
    public void triggerAsyncLoad(String search) {
        if (instance != null) {
            load(search);
        }
    }

    @Override
    public void clear(boolean silent) {
        if (instance != null) {
            boolean hadValue = !getValues().isEmpty();
            instance.removeActiveItems();

            if (!silent && hadValue) {
                handlers.onChange();
            }
        }
    }

    @Override
    public void clearOptions() {
        if (instance != null) {
            // Choices.js keeps the selected options
            List<String> selected = getValues();
            options.keySet().retainAll(selected);
            instance.clearChoices();
        }
    }

    @Override
    public void setOptions(List<SelectOption> options) {
        if (instance != null) {
            clearOptions();
            addOptions(options);
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
            List<SelectOption> added = new ArrayList<>();
            for (SelectOption option : options) {
                if (!this.options.containsKey(option.getValue())) {
                    this.options.put(option.getValue(), option.getText());
                    added.add(option);
                }
            }
            addChoices(added);
        }
    }

    @Override
    public List<SelectOption> getOptions() {
        return toSelectOptions();
    }

    @Override
    public boolean haveOption(String value) {
        return instance != null && options.containsKey(value);
    }

    @Override
    public void setValue(String value, boolean silent) {
        if (instance != null) {
            List<String> values = new ArrayList<>();
            values.add(value);
            setValues(values, silent);
        }
    }

    @Override
    public String getValue() {
        List<String> values = getValues();
        return values.isEmpty() ? null : values.get(0);
    }

    @Override
    public void setValues(List<String> values, boolean silent) {
        if (instance != null) {
            List<String> before = getValues();

            instance.removeActiveItems();
            instance.setChoiceByValue(values.toArray(new String[0]));

            if (!silent && !before.equals(getValues())) {
                handlers.onChange();
            }
        }
    }

    @Override
    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        if (instance == null) {
            return values;
        }

        Object value = instance.getValue(true);
        if (value == null || Js.isTripleEqual(value, Js.undefined())) {
            return values;
        }

        if (JsArray.isArray(value)) {
            JsArray<Object> array = Js.uncheckedCast(value);
            for (int i = 0; i < array.length; i++) {
                values.add(Js.asString(array.getAt(i)));
            }
        } else if (!Js.asString(value).isEmpty()) {
            // A single select shows its placeholder as an item with an empty value
            values.add(Js.asString(value));
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
        return instance == null ? null : Js.cast(instance.containerOuter.element);
    }

    @Override
    public boolean isStarted() {
        return instance != null;
    }

    private void createInstance(SelectProperties properties) {
        this.properties = properties;
        loadedOnOpen = false;

        ChoicesOptions opt = new ChoicesOptions();
        opt.searchEnabled = properties.isSearchEnabled();
        // With the async load the options found are shown as they are, not filtered again
        opt.searchChoices = !properties.isAsyncLoad();
        opt.removeItemButton = properties.isAllowClear();
        opt.maxItemCount = properties.isMultiple() && properties.getMultipleLimit() > 0
                ? properties.getMultipleLimit() : -1;
        opt.shouldSort = false;
        opt.allowHTML = false;
        opt.itemSelectText = "";

        String placeholder = properties.getPlaceholder();
        opt.placeholder = placeholder != null && !placeholder.isEmpty();
        if (opt.placeholder) {
            opt.placeholderValue = placeholder;
        }
        if (properties.getSearchPlaceholder() != null) {
            opt.searchPlaceholderValue = properties.getSearchPlaceholder();
        }
        if (properties.getNoResultsText() != null) {
            opt.noResultsText = properties.getNoResultsText();
        }

        opt.callbackOnInit = handlers::onLoaded;

        instance = new Choices(element, opt);
        addChoices(toSelectOptions());
        addListeners();
    }

    private void removeInstance() {
        if (instance != null) {
            for (Object[] listener : listeners) {
                ((elemental2.dom.EventTarget) listener[0]).removeEventListener((String) listener[1],
                        (EventListener) listener[2]);
            }
            listeners.clear();

            instance.destroy();
            instance = null;
        }
    }

    private void addListeners() {
        listen(element, "change", evt -> handlers.onChange());
        listen(element, "showDropdown", evt -> {
            handlers.onShow();
            handlers.onShown();

            if (properties.isAsyncLoad() && properties.isLoadOnOpen() && !loadedOnOpen) {
                loadedOnOpen = true;
                load("");
            }
        });
        listen(element, "hideDropdown", evt -> {
            handlers.onHide();
            handlers.onHidden();
        });
        if (properties.isAsyncLoad()) {
            listen(element, "search", evt -> {
                JsPropertyMap<Object> detail = Js.asPropertyMap(Js.asPropertyMap(evt).get("detail"));
                load(Js.asString(detail.get(VALUE)));
            });
        }

        // The focus moves between the container and the search input: only entering and leaving
        // the container count
        elemental2.dom.HTMLElement container = instance.containerOuter.element;
        listen(container, "focusin", evt -> {
            if (!isInside(((FocusEvent) evt).relatedTarget)) {
                handlers.onFocus();
            }
        });
        listen(container, "focusout", evt -> {
            if (!isInside(((FocusEvent) evt).relatedTarget)) {
                handlers.onBlur();
            }
        });
    }

    private boolean isInside(elemental2.dom.EventTarget target) {
        return target != null && instance != null && instance.containerOuter.element.contains((Node) target);
    }

    private void listen(elemental2.dom.EventTarget target, String type, EventListener listener) {
        target.addEventListener(type, listener);
        listeners.add(new Object[] {target, type, listener});
    }

    private void load(String query) {
        handlers.onAsyncLoad(query, found -> {
            if (instance == null) {
                return;
            }

            List<String> selected = getValues();
            options.keySet().retainAll(selected);
            for (SelectOption option : found) {
                options.put(option.getValue(), option.getText());
            }

            // Replaces the options that aren't selected; the selected ones stay as they are
            List<SelectOption> unselected = new ArrayList<>();
            for (SelectOption option : found) {
                if (!selected.contains(option.getValue())) {
                    unselected.add(option);
                }
            }
            instance.setChoices(toChoices(unselected), VALUE, LABEL, true);
        });
    }

    private void addChoices(List<SelectOption> added) {
        if (!added.isEmpty()) {
            instance.setChoices(toChoices(added), VALUE, LABEL, false);
        }
    }

    private List<SelectOption> toSelectOptions() {
        List<SelectOption> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : options.entrySet()) {
            SelectOption option = new SelectOption();
            option.setValue(entry.getKey());
            option.setText(entry.getValue());
            list.add(option);
        }
        return list;
    }

    private static JsArray<Object> toChoices(List<SelectOption> options) {
        JsArray<Object> choices = new JsArray<>();
        for (SelectOption option : options) {
            choices.push(JsPropertyMap.of(VALUE, option.getValue(), LABEL, option.getText()));
        }
        return choices;
    }
}
