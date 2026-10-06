package org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine;

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

import java.util.Date;
import java.util.List;

/**
 * A JavaScript date picker library behind a
 * {@link org.gwtbootstrap5.extras.datetimepicker.client.ui.base.DateTimePickerBase}: Tempus Dominus or Air
 * Datepicker.
 */
public interface IDateTimePickerEngine {

    /**
     * Creates the picker on the input.
     *
     * @param element the input of the widget
     * @param options the options of the picker
     * @param handlers called when the picker opens, closes and changes
     */
    void init(com.google.gwt.dom.client.Element element, DateTimePickerOptions options, IDateTimePickerHandlers handlers);
    /**
     * Applies new options to the picker.
     *
     * @param options the options
     */
    void updateProperties(DateTimePickerOptions options);
    /** Destroys the picker and removes it from the page; {@link #isStarted()} is false afterwards. */
    void destroy();
    /** Opens the picker. */
    void show();
    /** Closes the picker. */
    void hide();
    /** Opens the picker if it is closed, closes it otherwise. */
    void toggle();
    /**
     * Clears the selected dates.
     *
     * @param silent {@code true} not to fire the change handler
     */
    void clear(boolean silent);
    /**
     * Moves the calendar to a date without selecting it.
     *
     * @param date the date
     */
    void setViewDate(Date date);
    /**
     * Selects a date.
     *
     * @param date the date
     * @param silent {@code true} not to fire the change handler
     */
    void setDate(Date date, boolean silent);
    /**
     * Returns the selected date.
     *
     * @return the first selected date, or {@code null} if none
     */
    Date getDate();
    /**
     * Selects several dates, when the engine allows it.
     *
     * @param dates the dates
     * @param silent {@code true} not to fire the change handler
     */
    void setMultipleDates(List<Date> dates, boolean silent);
    /**
     * Returns the selected dates.
     *
     * @return the dates, empty if none
     */
    List<Date> getMultipleDates();
    /**
     * Returns whether the picker has been created and not destroyed.
     *
     * @return {@code true} if it is started
     */
    boolean isStarted();

}
