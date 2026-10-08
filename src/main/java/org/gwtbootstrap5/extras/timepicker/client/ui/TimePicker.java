package org.gwtbootstrap5.extras.timepicker.client.ui;

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
 * Time picker: a text box that opens a clock, without the calendar. The engine is
 * {@code TEMPUSDOMINUS} (Tempus Dominus 6) or {@code AIRDATEPICKER} (Air Datepicker 3), and its
 * module must be inherited; without {@code engine}, the picker uses the only one inherited.
 * <h2>UiBinder example</h2>
 * <pre>{@code
 *     <tp:TimePicker engine="TEMPUSDOMINUS" placeholder="Pick a time"/>
 * }</pre>
 * with {@code xmlns:tp="urn:import:org.gwtbootstrap5.extras.timepicker.client.ui"}.
 *
 * @author themarioga
 */
public class TimePicker extends DateTimePickerBase {

    /**
     * Creates a time picker drawn by the only engine whose module is inherited. If there are several,
     * {@link #setEngine} chooses one.
     */
    public TimePicker() {
        super();

        options.setOnlyTime(true);
    }

    /**
     * Creates a time picker.
     *
     * @param engine the JavaScript library that draws the clock, whose module must be inherited
     */
    public TimePicker(DateTimePickerEngines engine) {
        super(DateTimePickerEngines.getEngine(engine));

        options.setOnlyTime(true);
    }

}
