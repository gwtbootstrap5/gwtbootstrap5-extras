package org.gwtbootstrap5.extras.select.client.ui;

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

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import org.gwtbootstrap5.extras.select.client.ui.base.SelectBase;
import org.gwtbootstrap5.extras.select.client.ui.base.interfaces.HasValues;
import org.gwtbootstrap5.extras.select.client.ui.engines.SelectEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * A searchable select where several options can be selected, shown as removable tags. Inherit
 * the module of a select engine (see {@link SelectEngine}), for example
 * {@code org.gwtbootstrap5.extras.select.client.TomSelectResources}. Read the selection with
 * {@link #getValues()} and listen to it with {@link #addValuesChangeHandler}.
 *
 * <pre>{@code
 * <s:MultipleSelect ui:field="toppings" placeholder="Pick toppings"/>
 * }</pre>
 * <p>
 * {@code engine="…"} chooses the library when the application inherits several.
 * </p>
 *
 * @param <T> the type of the options
 * @see <a href="https://getbootstrap.com/docs/5.3/forms/select/">Bootstrap 5 documentation</a>
 */
public class MultipleSelect<T> extends SelectBase<T> implements HasValues<T> {

    /** The values set before the widget was attached, or kept while it is detached. */
    protected List<T> valuesSelectedBeforeInit = new ArrayList<>();

    /**
     * Creates a multiple select drawn by the only engine whose module is inherited. If there are
     * several, choose one with {@link #setEngine(SelectEngine)} ({@code engine="…"} in UiBinder).
     */
    public MultipleSelect() {
        super();
    }

    /**
     * Creates a multiple select.
     *
     * @param engine the JavaScript library, whose module must be inherited
     */
    public MultipleSelect(SelectEngine engine) {
        super(SelectEngine.getEngine(engine));
    }

    /**
     * Sets the maximum number of options that can be selected: {@code maxItems} in Tom Select,
     * {@code maxItemCount} in Choices.js and {@code maxSelected} in Slim Select.
     *
     * @param limit the maximum, or 0 (the default) for no limit
     */
    public void setMultipleLimit(int limit) {
        this.properties.setMultipleLimit(limit);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        if (valuesSelectedBeforeInit != null && !valuesSelectedBeforeInit.isEmpty()) {
            setValues(valuesSelectedBeforeInit, false);
        }
    }

    @Override
    protected void onUnload() {
        // Kept while the JavaScript select is destroyed, and set again by onLoad
        if (isEngineStarted()) {
            valuesSelectedBeforeInit = new ArrayList<>(getSelectedOptions());
        }

        super.onUnload();
    }

    @Override
    public boolean isMultiple() {
        return true;
    }

    @Override
    protected void asyncDataLoad(String query, AsyncDataLoadCallback<T> callback) {
        callback.onResult(List.of());
    }

    @Override
    public void clear() {
        if (isEngineStarted()) {
            engine.clear(true);
        }
    }

    @Override
    public void clearAll() {
        clear();

        clearOptions();
    }

    @Override
    public void setValue(T value) {
        setValue(value, false);
    }

    @Override
    public void setValue(T value, boolean fireEvents) {
        if (value == null) {
            if (isEngineStarted()) {
                engine.clear(!fireEvents);
            } else {
                valuesSelectedBeforeInit.clear();
            }

            return;
        }

        if (!optionList.containsKey(itemProvider.getValue(value))) {
            addOption(value);
        }

        if (isEngineStarted()) {
            engine.setValue(itemProvider.getValue(value), !fireEvents);
        } else {
            this.valuesSelectedBeforeInit.add(value);
        }
    }

    @Override
    public T getValue() {
        return valuesSelectedBeforeInit.isEmpty() ? null : valuesSelectedBeforeInit.get(valuesSelectedBeforeInit.size() - 1);
    }

    @Override
    public void setValues(List<T> values) {
        setValues(values, false);
    }

    @Override
    public void setValues(List<T> values, boolean fireEvents) {
        if (values == null || values.isEmpty()) {
            if (isEngineStarted()) {
                engine.clear(!fireEvents);
            } else {
                valuesSelectedBeforeInit.clear();
            }
            return;
        }

        setOptions(mergeValueListWithCurrentOptions(values));

        if (isEngineStarted()) {
            List<String> valueList = new ArrayList<>(values.size());
            for (T value : values) {
                valueList.add(itemProvider.getValue(value));
            }

            engine.setValues(valueList, !fireEvents);
        } else {
            this.valuesSelectedBeforeInit.addAll(values);
        }
    }

    private List<T> mergeValueListWithCurrentOptions(List<T> values) {
        List<T> oldValues = getOptions();
        for (T value : values) {
            if (!oldValues.contains(value)) {
                oldValues.add(value);
            }
        }
        return oldValues;
    }

    @Override
    public List<T> getValues() {
        if (isEngineStarted()) {
            return getSelectedOptions();
        }

        return new ArrayList<>(valuesSelectedBeforeInit);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Multiple selects carry the whole list of selected values: {@link #addValuesChangeHandler} is
     * registered on this same event type, and {@link #getValue()} only ever holds what was selected
     * before the engine started.
     * </p>
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void fireValueChangeEvent() {
        ValueChangeEvent.fire((HasValueChangeHandlers) this, getValues());
    }

    @Override
    public HandlerRegistration addValuesChangeHandler(ValueChangeHandler<List<T>> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }
}
