package org.gwtbootstrap5.extras.select.client.ui.base;

/*-
 * ==========================LICENSE_START===============================
 * GwtBootstrap5
 * ======================================================================
 * Copyright (C) 2023 - 2026 GwtBootstrap5
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

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.impl.FocusImpl;
import org.gwtbootstrap5.client.ui.base.ComplexWidget;
import org.gwtbootstrap5.client.ui.base.HasPlaceholder;
import org.gwtbootstrap5.client.ui.base.mixin.BlankValidatorMixin;
import org.gwtbootstrap5.client.ui.base.mixin.EnabledMixin;
import org.gwtbootstrap5.client.ui.base.mixin.ErrorHandlerMixin;
import org.gwtbootstrap5.client.ui.constants.Styles;
import org.gwtbootstrap5.client.ui.form.error.ErrorHandler;
import org.gwtbootstrap5.client.ui.form.error.ErrorHandlerType;
import org.gwtbootstrap5.client.ui.form.error.HasErrorHandler;
import org.gwtbootstrap5.client.ui.form.validator.HasBlankValidator;
import org.gwtbootstrap5.client.ui.form.validator.HasValidators;
import org.gwtbootstrap5.client.ui.form.validator.ValidationChangedEvent;
import org.gwtbootstrap5.client.ui.form.validator.Validator;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectEngine;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectHandlers;
import org.gwtbootstrap5.extras.select.client.ui.base.engine.SelectProperties;
import org.gwtbootstrap5.extras.select.client.ui.base.events.*;
import org.gwtbootstrap5.extras.select.client.ui.base.interfaces.HasAllSelectHandlers;
import org.gwtbootstrap5.extras.select.client.ui.base.interfaces.HasOptions;
import org.gwtbootstrap5.extras.select.client.ui.base.interfaces.HasSearch;
import org.gwtbootstrap5.extras.select.client.ui.engines.SelectEngine;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class of {@code Select} and {@code MultipleSelect}: a Bootstrap {@code form-select} turned
 * into a searchable dropdown by a JavaScript library, the {@link ISelectEngine}. The options are
 * objects of type {@code T}; an {@link ItemProvider} gives their value and text.
 * <p>
 * The engine is given to the constructor or chosen with {@link #setEngine(SelectEngine)} before
 * the select is attached. Without one, the select uses the only engine whose module is inherited
 * (see {@link SelectEngine}).
 * </p>
 *
 * @param <T> select value type
 *
 * @see <a href="https://getbootstrap.com/docs/5.3/forms/select/">Bootstrap 5 documentation</a>
 * @author Xiaodong Sun
 */
public abstract class SelectBase<T> extends ComplexWidget implements HasEnabled, Focusable, HasValue<T>, HasValidators<T>, IsEditor<SelectEditor<T>>,
        HasEditorErrors<T>, HasBlankValidator<T>, HasAllSelectHandlers<T>, HasErrorHandler, HasPlaceholder, HasOptions<T>, HasSearch {

    /** The editor, created by {@link #asEditor()}. */
    protected SelectEditor<T> editor;
    /** Gives the value and the text of the options. */
    protected ItemProvider<T> itemProvider;

    private final FocusImpl focusImpl = FocusImpl.getFocusImplForWidget();
    private final EnabledMixin<SelectBase<T>> enabledMixin = new EnabledMixin<>(this);
    private final ErrorHandlerMixin<T> errorHandlerMixin = new ErrorHandlerMixin<>(this);
    private final BlankValidatorMixin<SelectBase<T>, T> validatorMixin = new BlankValidatorMixin<>(this, errorHandlerMixin.getErrorHandler());

    /**
     * The JavaScript library; {@code null} until it is chosen, at the latest when the select is
     * attached.
     */
    protected ISelectEngine engine;
    /** The settings, passed to the engine when it starts and when they change. */
    protected SelectProperties properties;

    // Shown or hidden by setVisible: the library's control, once it is drawn, or the <select>
    private boolean visible = true;

    // Object List
    /** The options, by value. */
    protected BiMap<String, T> optionList = HashBiMap.create();

    /**
     * Creates the select on a new {@code <select class="form-select">}.
     *
     * @param engine the JavaScript library, or {@code null} to choose it later
     */
    protected SelectBase(ISelectEngine engine) {
        setElement(Document.get().createSelectElement());

        getElement().setClassName(Styles.FORM_SELECT);

        this.engine = engine;
        this.properties = new SelectProperties();

        this.properties.setMultiple(isMultiple());

        // Strings, numbers and enums work as they are; other options need setItemProvider
        this.itemProvider = new ItemProvider<T>() {
            @Override
            public String getValue(final T item) {
                return String.valueOf(item);
            }

            @Override
            public String getText(final T item) {
                return String.valueOf(item);
            }
        };
    }

    /**
     * Creates the select drawn by the only engine whose module is inherited. If there are several,
     * {@link #setEngine(SelectEngine)} chooses one before the select is attached.
     */
    protected SelectBase() {
        this(SelectEngine.getRegisteredEngines().size() == 1 ? SelectEngine.getDefaultEngine() : null);
    }

    /**
     * Chooses the JavaScript library that draws the select. In UiBinder, {@code engine="CHOICESJS"}.
     *
     * @param engine the library, whose module must be inherited
     * @throws IllegalStateException if the select is attached already, or the module of the engine
     *     isn't inherited
     */
    public void setEngine(SelectEngine engine) {
        if (isEngineStarted()) {
            throw new IllegalStateException("The engine of a select can't change once it is attached");
        }

        this.engine = SelectEngine.getEngine(engine);
    }

    /**
     * Returns whether several options can be selected.
     *
     * @return <code>true</code> if multiple selection is allowed
     */
    public abstract boolean isMultiple();

    /**
     * Loads the options for a search: with {@link #setAsyncLoad(boolean)}, the engine calls it
     * when the user types in the search box, and on the first focus with
     * {@link #setLoadOnOpen(boolean)}. {@code Select} and {@code MultipleSelect} find nothing;
     * override it to search a server.
     *
     * @param query the search text
     * @param callback to call with the options found
     */
    protected abstract void asyncDataLoad(String query, AsyncDataLoadCallback<T> callback);

    @Override
    protected void onLoad() {
        super.onLoad();

        if (engine == null) {
            engine = SelectEngine.getDefaultEngine();
        }

        if (!engine.isStarted()) {
            engine.init(SelectElement.as(getElement()), this.properties, createHandlers());

            if (!visible && engine.getControlElement() != null) {
                setVisible(engine.getControlElement(), false);
            }

            if (!optionList.isEmpty()) {
                setOptions(new ArrayList<>(optionList.values()));
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Destroys the JavaScript select, so a widget that is removed and added again doesn't leave
     * its old dropdown in the page. {@link #onLoad()} creates it again with the same options;
     * subclasses keep the selected values.
     * </p>
     */
    @Override
    protected void onUnload() {
        if (isEngineStarted()) {
            engine.destroy();
        }

        super.onUnload();
    }

    /** Opens the dropdown. */
    public void show() {
        if (isEngineStarted()) {
            engine.show();
        }
    }

    /** Closes the dropdown. */
    public void hide() {
        if (isEngineStarted()) {
            engine.hide();
        }
    }

    /** Updates the JavaScript select from the options of the {@code <select>} element. */
    public void refresh() {
        if (isEngineStarted()) {
            engine.refresh();
        }
    }

    /**
     * Sets whether the select has a button that clears the selection. Shown by default.
     *
     * @param allowClear {@code true} to show it
     */
    public void setAllowClear(boolean allowClear) {
        this.properties.setAllowClear(allowClear);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    /**
     * Makes a search load the options with {@link #asyncDataLoad}, for example from a server,
     * instead of filtering the options of the select. Off by default.
     *
     * @param asyncLoad {@code true} to load the options of each search
     */
    public void setAsyncLoad(boolean asyncLoad) {
        this.properties.setAsyncLoad(asyncLoad);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    /**
     * Loads the options with {@link #asyncDataLoad}, with an empty search, the first time the
     * select gets the focus. Only with {@link #setAsyncLoad(boolean)}. Off by default.
     *
     * @param loadOnOpen {@code true} to load the options on the first focus
     */
    public void setLoadOnOpen(boolean loadOnOpen) {
        this.properties.setLoadOnOpen(loadOnOpen);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    @Override
    public void reset() {
        validatorMixin.reset();
    }

    /**
     * Get error handler
     *
     * @return the error handler
     */
    @Override
    public ErrorHandler getErrorHandler() {
        return errorHandlerMixin.getErrorHandler();
    }

    /**
     * Set error handler
     *
     * @param errorHandler the new error handler
     */
    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        errorHandlerMixin.setErrorHandler(errorHandler);
        validatorMixin.setErrorHandler(errorHandler);
    }

    /**
     * Get error handler type
     *
     * @return the type of the error handler
     */
    @Override
    public ErrorHandlerType getErrorHandlerType() {
        return errorHandlerMixin.getErrorHandlerType();
    }

    /**
     * Set error handler type
     *
     * @param errorHandlerType the new error handler type
     */
    @Override
    public void setErrorHandlerType(ErrorHandlerType errorHandlerType) {
        errorHandlerMixin.setErrorHandlerType(errorHandlerType);
    }

    /**
     * Shows or hides the search box. Shown by default.
     *
     * @param enabled {@code true} to show it
     */
    @Override
    public void setSearchEnabled(boolean enabled) {
        this.properties.setSearchEnabled(enabled);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    /**
     * Sets the placeholder of the search box.
     *
     * @param placeholder the placeholder
     */
    @Override
    public void setSearchPlaceholder(String placeholder) {
        this.properties.setSearchPlaceholder(placeholder);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    /**
     * Sets the text shown when a search finds no option. Without it, each library shows its own.
     *
     * @param noResultsText the text
     */
    public void setNoResultsText(String noResultsText) {
        this.properties.setNoResultsText(noResultsText);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    /**
     * Get allow blank
     *
     * @return {@code true} if the select may be left blank
     */
    @Override
    public boolean getAllowBlank() {
        return validatorMixin.getAllowBlank();
    }

    /**
     * Set allow blank
     *
     * @param allowBlank the new allow blank
     */
    @Override
    public void setAllowBlank(boolean allowBlank) {
        validatorMixin.setAllowBlank(allowBlank);
    }

    /**
     * Set validate on blur
     *
     * @param validateOnBlur the new validate on blur
     */
    @Override
    public void setValidateOnBlur(boolean validateOnBlur) {
        validatorMixin.setValidateOnBlur(validateOnBlur);
    }

    /**
     * Get validate on blur
     *
     * @return {@code true} if it validates on blur
     */
    @Override
    public boolean getValidateOnBlur() {
        return validatorMixin.getValidateOnBlur();
    }

    /**
     * Add validator
     *
     * @param validator the validator
     */
    @Override
    public void addValidator(Validator<T> validator) {
        validatorMixin.addValidator(validator);
    }

    /**
     * Remove validator
     *
     * @param validator the validator
     * @return {@code true} if the validator was there
     */
    @Override
    public boolean removeValidator(Validator<T> validator) {
        return validatorMixin.removeValidator(validator);
    }

    /**
     * Set validators
     *
     * @param validators the new validators
     */
    @Override
    public void setValidators(Validator<T>... validators) {
        validatorMixin.setValidators(validators);
    }

    /**
     * Set the default placeholder text when nothing is selected.
     * This works for both multiple and standard select boxes.<br>
     * <br>
     * Defaults to <code>null</code>.
     *
     * @param placeholder placeholder
     * @see #setTitle(String)
     */
    @Override
    public void setPlaceholder(final String placeholder) {
        properties.setPlaceholder(placeholder);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    @Override
    public String getPlaceholder() {
        return properties.getPlaceholder();
    }

    @Override
    public SelectEditor<T> asEditor() {
        if (editor == null) {
            editor = SelectEditor.of(this);
        }
        return editor;
    }

    @Override
    public void showErrors(List<EditorError> errors) {
        errorHandlerMixin.showErrors(errors);
    }

    @Override
    public boolean validate() {
        return validatorMixin.validate();
    }

    @Override
    public boolean validate(boolean show) {
        return validatorMixin.validate(show);
    }

    @Override
    public void setEnabled(boolean enabled) {
        enabledMixin.setEnabled(enabled);

        if (isEngineStarted()) {
            engine.setEnabled(enabled);
        }
    }

    @Override
    public boolean isEnabled() {
        return enabledMixin.isEnabled();
    }

    @Override
    public int getTabIndex() {
        return focusImpl.getTabIndex(getFocusElement());
    }

    @Override
    public void setAccessKey(char key) {
        focusImpl.setAccessKey(getFocusElement(), key);
    }

    @Override
    public void setFocus(boolean focused) {
        if (focused) {
            focusImpl.focus(getFocusElement());
            if (engine != null) {
                engine.focus();
            }
        } else {
            focusImpl.blur(getFocusElement());
            if (engine != null) {
                engine.blur();
            }
        }
    }

    @Override
    public void setTabIndex(int index) {
        focusImpl.setTabIndex(getFocusElement(), index);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public HandlerRegistration addLoadedHandler(LoadedHandler handler) {
        return addHandler(handler, LoadedEvent.getType());
    }

    @Override
    public HandlerRegistration addShowHandler(ShowHandler handler) {
        return addHandler(handler, ShowEvent.getType());
    }

    @Override
    public HandlerRegistration addShownHandler(ShownHandler handler) {
        return addHandler(handler, ShownEvent.getType());
    }

    @Override
    public HandlerRegistration addHideHandler(HideHandler handler) {
        return addHandler(handler, HideEvent.getType());
    }

    @Override
    public HandlerRegistration addHiddenHandler(HiddenHandler handler) {
        return addHandler(handler, HiddenEvent.getType());
    }

    @Override
    public HandlerRegistration addFocusHandler(FocusHandler handler) {
        return addHandler(handler, FocusEvent.getType());
    }

    @Override
    public HandlerRegistration addBlurHandler(BlurHandler handler) {
        return addHandler(handler, BlurEvent.getType());
    }

    @Override
    public com.google.web.bindery.event.shared.HandlerRegistration addValidationChangedHandler(ValidationChangedEvent.ValidationChangedHandler handler) {
        return validatorMixin.addValidationChangedHandler(handler);
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;

        // The library hides the <select> and shows its own control instead
        if (isEngineStarted() && engine.getControlElement() != null) {
            setVisible(engine.getControlElement(), visible);
        } else {
            super.setVisible(visible);
        }
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void clearOptions() {
        optionList.clear();

        if (isEngineStarted()) {
            engine.clearOptions();
        }
    }

    @Override
    public void setOptions(List<T> options) {
        clearOptions();

        List<ISelectEngine.SelectOption> selectOptions = new ArrayList<>();
        for (T option : options) {
            ISelectEngine.SelectOption selectOption = transformOptionToSelectOption(option);

            optionList.put(selectOption.getValue(), option);
            selectOptions.add(selectOption);
        }

        if (isEngineStarted()) {
            engine.setOptions(selectOptions);
        }
    }

    @Override
    public void addOption(T option) {
        ISelectEngine.SelectOption selectOption = transformOptionToSelectOption(option);

        optionList.put(selectOption.getValue(), option);

        if (isEngineStarted()) {
            engine.addOption(selectOption);
        }
    }

    @Override
    public void addOptions(List<T> options) {
        List<ISelectEngine.SelectOption> selectOptions = new ArrayList<>();
        for (T option : options) {
            ISelectEngine.SelectOption selectOption = transformOptionToSelectOption(option);

            optionList.put(selectOption.getValue(), option);
            selectOptions.add(selectOption);
        }

        if (isEngineStarted()) {
            engine.addOptions(selectOptions);
        }
    }

    @Override
    public List<T> getOptions() {
        return new ArrayList<>(optionList.values());
    }

    @Override
    public void triggerSearch(String search) {
        if (isEngineStarted()) {
            engine.triggerAsyncLoad(search);
        }
    }

    /**
     * Sets how an option becomes the value and the text of its {@code <option>}. By default both
     * are {@code String.valueOf(option)}.
     *
     * @param itemProvider gives the value and the text of an option
     */
    public void setItemProvider(ItemProvider<T> itemProvider) {
        this.itemProvider = itemProvider;
    }

    /**
     * Returns whether the JavaScript select exists: the widget is attached.
     *
     * @return {@code true} if the engine has started
     */
    protected boolean isEngineStarted() {
        return engine != null && engine.isStarted();
    }

    private ISelectEngine.@NonNull SelectOption transformOptionToSelectOption(T option) {
        ISelectEngine.SelectOption selectOption = new ISelectEngine.SelectOption();
        selectOption.setValue(itemProvider.getValue(option));
        selectOption.setText(itemProvider.getText(option));
        return selectOption;
    }

    private Element getFocusElement() {
        if (isEngineStarted() && engine.getControlElement() != null) {
            return engine.getControlElement();
        }
        return getElement();
    }

    private @NonNull ISelectHandlers createHandlers() {
        SelectBase<T> that = this;
        return new ISelectHandlers() {
            @Override
            public void onLoaded() {
                LoadedEvent.fire(that);
            }

            @Override
            public void onAsyncLoad(String query, OnAsyncLoadCallback callback) {
                that.asyncDataLoad(query, result -> {
                    // Resolved before wiping the list, while the options they came from are still there.
                    List<T> selected = getSelectedOptions();

                    clearOptions();

                    List<ISelectEngine.SelectOption> selectOptions = new ArrayList<>();
                    for (T option : result) {
                        ISelectEngine.SelectOption selectOption = transformOptionToSelectOption(option);

                        optionList.put(selectOption.getValue(), option);
                        selectOptions.add(selectOption);
                    }

                    // Whatever is selected stays an option even if this search does not return it:
                    // otherwise its value has nothing left to resolve against and the selection is
                    // silently lost the next time the values are read.
                    for (T option : selected) {
                        ISelectEngine.SelectOption selectOption = transformOptionToSelectOption(option);

                        if (!optionList.containsKey(selectOption.getValue())) {
                            optionList.put(selectOption.getValue(), option);
                            selectOptions.add(selectOption);
                        }
                    }

                    callback.callback(selectOptions);
                });
            }

            @Override
            public void onShow() {
                ShowEvent.fire(that);
            }

            @Override
            public void onShown() {
                ShownEvent.fire(that);
            }

            @Override
            public void onHide() {
                HideEvent.fire(that);
            }

            @Override
            public void onHidden() {
                HiddenEvent.fire(that);
            }

            @Override
            public void onChange() {
                that.fireValueChangeEvent();
            }

            @Override
            public void onFocus() {
                FocusEvent.fire(that);
            }

            @Override
            public void onBlur() {
                BlurEvent.fire(that);
            }
        };
    }

    /**
     * Fires the value change event carrying this widget's current value. Multiple selects override
     * it to carry the whole list of selected values, which is what their handlers are registered for.
     */
    protected void fireValueChangeEvent() {
        ValueChangeEvent.fire(this, getValue());
    }

    /**
     * Returns the selected options.
     *
     * @return the options currently selected, resolved against the option list in force
     */
    protected List<T> getSelectedOptions() {
        List<T> selected = new ArrayList<>();

        if (isEngineStarted()) {
            for (String value : engine.getValues()) {
                T option = optionList.get(value);

                if (option != null) {
                    selected.add(option);
                }
            }
        }

        return selected;
    }

    /**
     * Gives the value (unique per option) and the text shown for an option.
     *
     * @param <T> the type of the options
     */
    public interface ItemProvider<T> {
        /**
         * Returns the value of an option, unique among the options; it is the {@code value} of its
         * {@code <option>}.
         *
         * @param item the option
         * @return the value
         */
        String getValue(T item);

        /**
         * Returns the text shown for an option.
         *
         * @param item the option
         * @return the text
         */
        String getText(T item);
    }

    /**
     * Receives the options found by {@link #asyncDataLoad}.
     *
     * @param <T> the type of the options
     */
    protected interface AsyncDataLoadCallback<T> {
        /**
         * Gives the options found.
         *
         * @param result the options, empty if none was found
         */
        void onResult(@NonNull List<T> result);
    }

}
