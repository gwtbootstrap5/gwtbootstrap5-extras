package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines;

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

import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerEngine;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.airdatepicker.AirDatepickerEngine;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.tempusdominus.TempusDominusEngine;

/** The JavaScript libraries that can draw a date picker. */
public enum DateTimePickerEngines {
    /** Tempus Dominus 6. */
    TEMPUSDOMINUS,
    /** Air Datepicker 3. */
    AIRDATEPICKER;

    /**
     * Creates a new engine of a library.
     *
     * @param engine the library
     * @return the engine
     */
    public static IDateTimePickerEngine getEngine(DateTimePickerEngines engine) {
        IDateTimePickerEngine dateTimePickerEngine = new TempusDominusEngine();
        switch (engine) {
            case TEMPUSDOMINUS -> dateTimePickerEngine = new TempusDominusEngine();
            case AIRDATEPICKER -> dateTimePickerEngine = new AirDatepickerEngine();
        }

        return dateTimePickerEngine;
    }
}
