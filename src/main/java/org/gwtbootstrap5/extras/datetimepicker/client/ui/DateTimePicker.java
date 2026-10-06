package org.gwtbootstrap5.extras.datetimepicker.client.ui;

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

import com.google.gwt.uibinder.client.UiConstructor;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.DateTimePickerBase;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.DateTimePickerEngines;

/**
 * Date and time picker: a text box that opens a calendar and a clock. The engine is
 * {@code TEMPUSDOMINUS} (Tempus Dominus 6) or {@code AIRDATEPICKER} (Air Datepicker 3).
 * <h2>UiBinder example</h2>
 * <pre>{@code
 *     <dtp:DateTimePicker engine="AIRDATEPICKER" minuteStep="15" placeholder="Pick a date and
 * time"/>
 * }</pre>
 * with {@code xmlns:dtp="urn:import:org.gwtbootstrap5.extras.datetimepicker.client.ui"}.
 *
 * @author themarioga
 */
public class DateTimePicker extends DateTimePickerBase {

    /**
     * Creates a date and time picker.
     *
     * @param engine the JavaScript library that draws the calendar
     */
    @UiConstructor
    public DateTimePicker(DateTimePickerEngines engine) {
        super(DateTimePickerEngines.getEngine(engine));
    }

}
