package org.gwtbootstrap5.extras.datepicker.client.ui;

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

import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.DateTimePickerBase;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.DateTimePickerEngines;

/**
 * Date picker: a text box that opens a calendar, without the time. The engine is
 * {@code TEMPUSDOMINUS} (Tempus Dominus 6) or {@code AIRDATEPICKER} (Air Datepicker 3), and its
 * module must be inherited; without {@code engine}, the picker uses the only one inherited.
 * <h2>UiBinder example</h2>
 * <pre>{@code
 *     <dp:DatePicker engine="TEMPUSDOMINUS" placeholder="Pick a date"/>
 * }</pre>
 * with {@code xmlns:dp="urn:import:org.gwtbootstrap5.extras.datepicker.client.ui"}.
 *
 * @author themarioga
 */
public class DatePicker extends DateTimePickerBase {

    /**
     * Creates a date picker drawn by the only engine whose module is inherited. If there are several,
     * {@link #setEngine} chooses one.
     */
    public DatePicker() {
        super();

        options.setOnlyCalendar(true);
    }

    /**
     * Creates a date picker.
     *
     * @param engine the JavaScript library that draws the calendar, whose module must be inherited
     */
    public DatePicker(DateTimePickerEngines engine) {
        super(DateTimePickerEngines.getEngine(engine));

        options.setOnlyCalendar(true);
    }

}
