package org.gwtbootstrap5.extras.datetimepicker.client.ui.base;

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

import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap5.client.shared.event.HideEvent;
import org.gwtbootstrap5.client.shared.event.HideHandler;
import org.gwtbootstrap5.client.shared.event.ShowEvent;
import org.gwtbootstrap5.client.shared.event.ShowHandler;
import org.gwtbootstrap5.client.ui.TextBox;
import org.gwtbootstrap5.client.ui.base.*;
import org.gwtbootstrap5.client.ui.base.ValueBoxBase;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.base.mixin.ErrorHandlerMixin;
import org.gwtbootstrap5.client.ui.constants.DeviceSize;
import org.gwtbootstrap5.client.ui.form.error.ErrorHandlerType;
import org.gwtbootstrap5.client.ui.form.error.HasErrorHandler;
import org.gwtbootstrap5.client.ui.form.validator.HasBlankValidator;
import org.gwtbootstrap5.client.ui.form.validator.HasValidators;
import org.gwtbootstrap5.client.ui.form.validator.ValidationChangedEvent.ValidationChangedHandler;
import org.gwtbootstrap5.client.ui.form.validator.Validator;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.DateTimePickerOptions;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerEngine;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerHandlers;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.interfaces.*;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.validators.DatePickerBlankValidatorMixin;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.DateTimePickerEngines;

import java.util.Date;
import java.util.List;

/**
 * Base class of the date and time pickers: a Bootstrap text box whose JavaScript picker, drawn
 * by an {@link IDateTimePickerEngine}, is created when the widget is attached and destroyed when
 * it
 * is detached, keeping its value. Its value is a {@code java.util.Date}; it validates like the
 * other form controls.
 * <p>
 * The engine is given to the constructor or chosen with {@link #setEngine(DateTimePickerEngines)}
 * before the picker is attached. Without one, the picker uses the only engine whose module is
 * inherited (see {@link DateTimePickerEngines}).
 * </p>
 *
 * @author Joshua Godi
 * @author Steven Jardine
 * @author themarioga
 */
public abstract class DateTimePickerBase extends Widget implements HasEnabled, HasReadOnly, HasId, HasResponsiveness, HasVisibility,
        HasPlaceholder, HasAutoClose, HasMaximumDate, HasTimeStepping, HasShowTodayButton,
        HasShowClearButton, HasMinimumDate, HasDateTimePickerHandlers, HasLocalization, HasFormat, HasName,
        HasValue<Date>, HasMultipleValues<Date>, LeafValueEditor<Date>, HasEditorErrors<Date>, HasErrorHandler, HasValidators<Date>,
        HasBlankValidator<Date> {

    private final TextBox textBox;

    private final ErrorHandlerMixin<Date> errorHandlerMixin = new ErrorHandlerMixin<>(this);
    private final DatePickerBlankValidatorMixin validatorMixin = new DatePickerBlankValidatorMixin(this, errorHandlerMixin.getErrorHandler());

    /**
     * The engine that draws the picker; {@code null} until it is chosen, at the latest when the
     * picker is attached.
     */
    protected IDateTimePickerEngine dateTimePickerEngine;
    /** The options of the picker, passed to the engine. */
    protected final DateTimePickerOptions options;

    private Date valueSetBeforeInit;

    /**
     * Creates a picker drawn by the given engine.
     *
     * @param dateTimePickerEngine the engine, or {@code null} to choose it later
     */
    protected DateTimePickerBase(IDateTimePickerEngine dateTimePickerEngine) {
        textBox = new TextBox();
        setElement((Element) textBox.getElement());

        this.options = new DateTimePickerOptions();
        this.dateTimePickerEngine = dateTimePickerEngine;
    }

    /**
     * Creates a picker drawn by the only engine whose module is inherited. If there are several,
     * {@link #setEngine(DateTimePickerEngines)} chooses one before the picker is attached.
     */
    protected DateTimePickerBase() {
        this(DateTimePickerEngines.getRegisteredEngines().size() == 1
                ? DateTimePickerEngines.getDefaultEngine() : null);
    }

    /**
     * Chooses the JavaScript library that draws the picker. In UiBinder, {@code engine="AIRDATEPICKER"}.
     *
     * @param engine the library, whose module must be inherited
     * @throws IllegalStateException if the picker is attached already, or the module of the engine
     *                               isn't inherited
     */
    public void setEngine(DateTimePickerEngines engine) {
        if (dateTimePickerEngine != null && dateTimePickerEngine.isStarted()) {
            throw new IllegalStateException("The engine of a date picker can't change once it is attached");
        }

        dateTimePickerEngine = DateTimePickerEngines.getEngine(engine);
    }

    /** {@inheritDoc} */
    @Override
    protected void onLoad() {
        super.onLoad();

        if (dateTimePickerEngine == null) {
            dateTimePickerEngine = DateTimePickerEngines.getDefaultEngine();
        }

        if (!dateTimePickerEngine.isStarted()) {
            dateTimePickerEngine.init(getElement(), options, getHandlers());

            if (valueSetBeforeInit != null) setValue(valueSetBeforeInit, false);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Destroys the JavaScript date picker, so a widget that is removed and added again doesn't
     * leave its old calendar in the page. The value is kept and set again by {@link #onLoad()}.
     * </p>
     */
    @Override
    protected void onUnload() {
        if (dateTimePickerEngine != null && dateTimePickerEngine.isStarted()) {
            valueSetBeforeInit = dateTimePickerEngine.getDate();
            dateTimePickerEngine.destroy();
        }

        super.onUnload();
    }

    /**
     * Call this whenever changing any settings: minView, startView, format, etc. If you are changing
     * format and date value, the updates must take in such order:
     * <p>
     * 1. DateTimePicker.reload()
     * 2. DateTimePicker.setValue(newDate); // Date newDate.
     * <p>
     * Otherwise date value is not updated.
     *
     * @param options the options to apply
     */
    public void reload(DateTimePickerOptions options) {
        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** Opens the picker. */
    public void show() {
        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.show();
        }
    }

    /** Closes the picker. */
    public void hide() {
        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.hide();
        }
    }

    /**
     * Sets whether typing a date in the text box selects it in the picker, after
     * {@link #setTypingDelay(int) the typing delay}.
     *
     * @param value {@code true} to select typed dates
     */
    public void setSelectDateOnWrite(boolean value) {
        options.setSelectDateOnWrite(value);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /**
     * Sets how long the picker waits after a key press before reading a typed date.
     *
     * @param delay the delay in milliseconds, 1000 by default
     */
    public void setTypingDelay(int delay) {
        options.setTypingDelay(delay);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /**
     * Aligns the text in the text box.
     *
     * @param align the alignment
     */
    public void setAlignment(final ValueBoxBase.TextAlignment align) {
        textBox.setAlignment(align);
    }

    /**
     * Returns the text typed in the text box.
     *
     * @return the text
     */
    public String getBaseValue() {
        return textBox.getValue();
    }

    /**
     * Returns the text box of the picker.
     *
     * @return the text box
     */
    public TextBox getTextBox() {
        return textBox;
    }

    /**
     * Returns the options of the picker. After changing them, call {@link #reload} to apply them.
     *
     * @return the options
     */
    public DateTimePickerOptions getOptions() {
        return options;
    }

    @Override
    public void setPlaceholder(final String placeHolder) {
        textBox.setPlaceholder(placeHolder);
    }

    /** {@inheritDoc} */
    @Override
    public String getPlaceholder() {
        return textBox.getPlaceholder();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEnabled() {
        return textBox.isEnabled();
    }

    /** {@inheritDoc} */
    @Override
    public void setEnabled(final boolean enabled) {
        textBox.setEnabled(enabled);
    }

    /** {@inheritDoc} */
    @Override
    public void setId(final String id) {
        textBox.setId(id);
    }

    /** {@inheritDoc} */
    @Override
    public String getId() {
        return textBox.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setName(final String name) {
        textBox.setName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return textBox.getName();
    }

    @Override
    public void setReadOnly(final boolean readOnly) {
        textBox.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() {
        return textBox.isReadOnly();
    }

    /** {@inheritDoc} */
    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    /** {@inheritDoc} */
    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

    /** {@inheritDoc} */
    @Override
    public HandlerRegistration addShowHandler(final ShowHandler showHandler) {
        return addHandler(showHandler, ShowEvent.getType());
    }

    /** {@inheritDoc} */
    @Override
    public HandlerRegistration addHideHandler(final HideHandler hideHandler) {
        return addHandler(hideHandler, HideEvent.getType());
    }

    /** {@inheritDoc} */
    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Date> dateValueChangeHandler) {
        textBox.addValueChangeHandler(event -> ValueChangeEvent.fire(DateTimePickerBase.this, getValue()));
        return addHandler(dateValueChangeHandler, ValueChangeEvent.getType());
    }

    /** {@inheritDoc} */
    @Override
    public com.google.web.bindery.event.shared.HandlerRegistration addValidationChangedHandler(ValidationChangedHandler handler) {
        return validatorMixin.addValidationChangedHandler(handler);
    }

    /** {@inheritDoc} */
    @Override
    public void setErrorHandler(org.gwtbootstrap5.client.ui.form.error.ErrorHandler errorHandler) {
        errorHandlerMixin.setErrorHandler(errorHandler);
        validatorMixin.setErrorHandler(errorHandler);
    }

    /** {@inheritDoc} */
    @Override
    public void reset() {
        validatorMixin.reset();
    }

    /** {@inheritDoc} */
    @Override
    public boolean getAllowBlank() {
        return validatorMixin.getAllowBlank();
    }

    /** {@inheritDoc} */
    @Override
    public void setAllowBlank(boolean allowBlank) {
        validatorMixin.setAllowBlank(allowBlank);
    }

    /** {@inheritDoc} */
    @Override
    public void addValidator(Validator<Date> validator) {
        validatorMixin.addValidator(validator);
    }

    /** {@inheritDoc} */
    @Override
    public boolean getValidateOnBlur() {
        return validatorMixin.getValidateOnBlur();
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeValidator(Validator<Date> validator) {
        return validatorMixin.removeValidator(validator);
    }

    /** {@inheritDoc} */
    @Override
    public void setValidateOnBlur(boolean validateOnBlur) {
        validatorMixin.setValidateOnBlur(validateOnBlur);
    }

    /** {@inheritDoc} */
    @Override
    public void setValidators(@SuppressWarnings("unchecked") Validator<Date>... validators) {
        validatorMixin.setValidators(validators);
    }

    /** {@inheritDoc} */
    @Override
    public boolean validate() {
        return validatorMixin.validate();
    }

    /** {@inheritDoc} */
    @Override
    public boolean validate(boolean show) {
        return validatorMixin.validate(show);
    }

    /** {@inheritDoc} */
    @Override
    public org.gwtbootstrap5.client.ui.form.error.ErrorHandler getErrorHandler() {
        return errorHandlerMixin.getErrorHandler();
    }

    /** {@inheritDoc} */
    @Override
    public ErrorHandlerType getErrorHandlerType() {
        return errorHandlerMixin.getErrorHandlerType();
    }

    /** {@inheritDoc} */
    @Override
    public void setErrorHandlerType(ErrorHandlerType errorHandlerType) {
        errorHandlerMixin.setErrorHandlerType(errorHandlerType);
    }

    /** {@inheritDoc} */
    @Override
    public void showErrors(List<EditorError> errors) {
        errorHandlerMixin.showErrors(errors);
    }

    /** {@inheritDoc} */
    @Override
    public void setKeepOpen(boolean keepOpen) {
        options.setKeepOpen(keepOpen);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean isKeepOpen() {
        return options.isKeepOpen();
    }

    /** {@inheritDoc} */
    @Override
    public void setLocale(final String locale) {
        options.setLocale(locale);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getLocale() {
        return options.getLocale();
    }

    /** {@inheritDoc} */
    @Override
    public void setMinDate(final Date minDate) {
        options.setMinDate(minDate);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clearMinDate() {
        options.setMinDate(null);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setMaxDate(final Date maxDate) {
        options.setMaxDate(maxDate);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clearMaxDate() {
        options.setMaxDate(null);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setHourStep(final int hourStep) {
        options.setHourStep(hourStep);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setMinuteStep(final int minuteStep) {
        options.setMinuteStep(minuteStep);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setShowTodayButton(final boolean showTodayButton) {
        options.setShowTodayButton(showTodayButton);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setShowClearButton(final boolean showClearButton) {
        options.setShowClearButton(showClearButton);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setDateFormat(String format) {
        options.setDateFormat(format);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setTimeFormat(String format) {
        options.setTimeFormat(format);

        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.updateProperties(options);
        }
    }

    /** Clears the selected date, firing the change events. */
    public void clear() {
        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.clear(false);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Date getValue() {
        if (dateTimePickerEngine != null && dateTimePickerEngine.isStarted()) {
            return dateTimePickerEngine.getDate();
        }

        return valueSetBeforeInit;
    }

    /** {@inheritDoc} */
    @Override
    public void setValue(final Date value) {
        setValue(value, false);
    }

    /** {@inheritDoc} */
    @Override
    public void setValue(final Date value, final boolean fireEvents) {
        this.valueSetBeforeInit = value;

        if (dateTimePickerEngine != null) {
            errorHandlerMixin.clearErrors();

            if (value == null) {
                dateTimePickerEngine.clear(!fireEvents);
            } else {
                dateTimePickerEngine.setDate(value, !fireEvents);
            }

            if (fireEvents) {
                ValueChangeEvent.fire(DateTimePickerBase.this, value);
            }
        }
    }

    @Override
    public List<Date> getMultipleValues() {
        if (dateTimePickerEngine != null) {
            return dateTimePickerEngine.getMultipleDates();
        }

        return List.of();
    }

    @Override
    public void setMultipleValues(List<Date> values) {
        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.setMultipleDates(values, false);
        }
    }

    @Override
    public void setMultipleValues(List<Date> values, boolean fireEvents) {
        if (dateTimePickerEngine != null) {
            dateTimePickerEngine.setMultipleDates(values, fireEvents);
        }
    }

    // --- Private methods ---

    private IDateTimePickerHandlers getHandlers() {
        return new IDateTimePickerHandlers() {
            @Override
            public void onShow() {
                validatorMixin.setShowing(true);
                fireEvent(new ShowEvent());
            }

            @Override
            public void onHide() {
                validatorMixin.setShowing(false);
                validate(getValidateOnBlur());
                fireEvent(new HideEvent());
            }

            @Override
            public void onChangeValue(List<Date> dates) {
                ValueChangeEvent.fire(DateTimePickerBase.this, !dates.isEmpty() ? dates.get(0) : null);
            }
        };
    }
}
