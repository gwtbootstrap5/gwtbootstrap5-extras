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

import com.google.gwt.uibinder.client.UiConstructor;
import org.gwtbootstrap5.extras.select.client.ui.base.SelectBase;
import org.gwtbootstrap5.extras.select.client.ui.engines.SelectEngine;

import java.util.List;

/**
 * A searchable single select: a Bootstrap {@code form-select} turned into a dropdown by
 * <a href="https://tom-select.js.org/">Tom Select</a>. Inherit
 * {@code org.gwtbootstrap5.extras.select.client.TomSelectResources} (or {@code TomSelectURL}) to
 * load the library. The JavaScript select is created when the widget is attached and destroyed when
 * it is detached; the value is kept in between.
 *
 * <pre>{@code
 * <s:Select ui:field="size" engine="TOMSELECT" placeholder="Pick a size"/>
 * }</pre>
 *
 *
 * @param <T> the type of the options
 * @see <a href="https://getbootstrap.com/docs/5.3/forms/select/">Bootstrap 5 documentation</a>
 */
public class Select<T> extends SelectBase<T> {

    /** The value set before the widget was attached, or kept while it is detached. */
    protected T valueSelectedBeforeInit;

    /**
     * Creates a select.
     *
     * @param engine the JavaScript library, the {@code engine} attribute in UiBinder
     */
    @UiConstructor
    public Select(SelectEngine engine) {
        super(SelectEngine.getEngine(engine));
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        if (valueSelectedBeforeInit != null) {
            setValue(valueSelectedBeforeInit, false);
        }
    }

    @Override
    protected void onUnload() {
        // Kept while the JavaScript select is destroyed, and set again by onLoad
        valueSelectedBeforeInit = getValue();

        super.onUnload();
    }

    @Override
    public boolean isMultiple() {
        return false;
    }

    @Override
    protected void asyncDataLoad(String query, AsyncDataLoadCallback<T> callback) {
        callback.onResult(List.of());
    }

    @Override
    public void clear() {
        if (engine != null) {
            engine.clear(true);
        }
    }

    @Override
    public void clearAll() {
        clear();

        clearOptions();
    }

    @Override
    public void setValue(final T value) {
        setValue(value, false);
    }

    @Override
    public void setValue(final T value, final boolean fireEvents) {
        if (value == null) {
            if (isEngineStarted()) {
                engine.clear(!fireEvents);
            } else {
                valueSelectedBeforeInit = null;
            }
            return;
        }

        if (!engine.haveOption(itemProvider.getValue(value))) {
            addOption(value);
        }

        if (isEngineStarted()) {
            engine.setValue(itemProvider.getValue(value), !fireEvents);
        } else {
            this.valueSelectedBeforeInit = value;
        }
    }

    @Override
    public T getValue() {
        if (isEngineStarted()) {
            return optionList.get(engine.getValue());
        }

        return valueSelectedBeforeInit;
    }
}
