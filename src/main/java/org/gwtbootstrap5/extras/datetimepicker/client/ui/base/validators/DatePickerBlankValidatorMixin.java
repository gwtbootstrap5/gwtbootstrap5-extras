package org.gwtbootstrap5.extras.datetimepicker.client.ui.base.validators;

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

import com.google.gwt.event.dom.client.BlurEvent;
import org.gwtbootstrap5.client.ui.base.mixin.BlankValidatorMixin;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.DateTimePickerBase;

import java.util.Date;

/**
 * Blank validator of a date picker, which doesn't validate on blur while the picker is open: the
 * text box loses the focus to the calendar.
 */
public class DatePickerBlankValidatorMixin extends BlankValidatorMixin<DateTimePickerBase, Date> {
    private boolean showing = false;

    /**
     * Tells the mixin whether the picker is open.
     *
     * @param showing {@code true} while it is open
     */
    public void setShowing(boolean showing) {
        this.showing = showing;
    }

    /**
     * Creates the mixin of a picker.
     *
     * @param inputWidget the picker
     * @param errorHandler the error handler that shows the errors
     */
    public DatePickerBlankValidatorMixin(DateTimePickerBase inputWidget, org.gwtbootstrap5.client.ui.form.error.ErrorHandler errorHandler) {
        super(inputWidget, errorHandler);
    }

    @Override
    protected com.google.web.bindery.event.shared.HandlerRegistration setupBlurValidation() {
        return getInputWidget().addDomHandler(event -> getInputWidget().validate(!showing && getValidateOnBlur()), BlurEvent.getType());
    }
}
