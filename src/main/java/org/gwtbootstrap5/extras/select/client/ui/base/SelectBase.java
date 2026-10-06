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
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Bootstrap select widget base
 *
 * @param <T> select value type
 *
 * @see <a href="https://silviomoreto.github.io/bootstrap-select/">...</a>
 * @author Xiaodong Sun
 */
public abstract class SelectBase<T> extends ComplexWidget implements HasEnabled, Focusable, HasValue<T>, HasValidators<T>, IsEditor<SelectEditor<T>>,
        HasEditorErrors<T>, HasBlankValidator<T>, HasAllSelectHandlers<T>, HasErrorHandler, HasPlaceholder, HasOptions<T>, HasSearch {

    protected SelectEditor<T> editor;
    protected ItemProvider<T> itemProvider;

    private final FocusImpl focusImpl = FocusImpl.getFocusImplForWidget();
    private final EnabledMixin<SelectBase<T>> enabledMixin = new EnabledMixin<>(this);
    private final ErrorHandlerMixin<T> errorHandlerMixin = new ErrorHandlerMixin<>(this);
    private final BlankValidatorMixin<SelectBase<T>, T> validatorMixin = new BlankValidatorMixin<>(this, errorHandlerMixin.getErrorHandler());

    protected ISelectEngine engine;
    protected SelectProperties properties;

    // Object List
    protected BiMap<String, T> optionList = HashBiMap.create();

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
     * @return <code>true</code> if multiple selection is allowed
     */
    public abstract boolean isMultiple();

    /**
     * AsyncDataLoad
     * @param query search query
     */
    protected abstract void asyncDataLoad(String query, AsyncDataLoadCallback<T> callback);

    @Override
    protected void onLoad() {
        super.onLoad();

        if (engine != null && !engine.isStarted()) {
            engine.init(SelectElement.as(getElement()), this.properties, createHandlers());

            if (!optionList.isEmpty()) {
                setOptions(new ArrayList<>(optionList.values()));
            }
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        // ToDo: habría que hacer destroy?
    }

    public void show() {
        if (isEngineStarted()) {
            engine.show();
        }
    }

    public void hide() {
        if (isEngineStarted()) {
            engine.hide();
        }
    }

    public void refresh() {
        if (isEngineStarted()) {
            engine.refresh();
        }
    }

    public void setAllowClear(boolean allowClear) {
        this.properties.setAllowClear(allowClear);

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
     * @return e
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
     * @return e
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
     * Set the search bar
     *
     * @param enabled boolean
     */
    @Override
    public void setSearchEnabled(boolean enabled) {
        this.properties.setSearchEnabled(enabled);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    /**
     * Set the search placeholder
     *
     * @param placeholder is placeholder
     */
    @Override
    public void setSearchPlaceholder(String placeholder) {
        this.properties.setSearchPlaceholder(placeholder);

        if (isEngineStarted()) {
            engine.updateProperties(this.properties);
        }
    }

    /**
     * Get allow blank
     *
     * @return e
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
     * @return e
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
     * @return e
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
        if (isAttached()) {
            setVisible(getElement().getParentElement(), visible);

            return;
        }

        super.setVisible(visible);
    }

    @Override
    public boolean isVisible() {
        if (isAttached()) {
            return isVisible(getElement().getParentElement());
        }
        return super.isVisible();
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
     */
    public void setItemProvider(ItemProvider<T> itemProvider) {
        this.itemProvider = itemProvider;
    }

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
        if (!isAttached()) {
            return getElement();
        }
        return getElement().getParentElement().getFirstChildElement();
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
     */
    public interface ItemProvider<T> {
        String getValue(T item);

        String getText(T item);
    }

    protected interface AsyncDataLoadCallback<T> {
        void onResult(@NonNull List<T> result);
    }

}
