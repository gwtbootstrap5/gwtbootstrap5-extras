package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.airdatepicker;

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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import org.gwtbootstrap5.client.ui.util.StyleInjector;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.DateTimePickerEngines;

/**
 * Registers the Air Datepicker engine of the date pickers and loads its script
 * from a CDN instead of the bundled files.
 *
 * @author Sven Jacobs
 */
public class AirDatepickerURLEntryPoint implements EntryPoint {

    /** Creates the entry point; GWT calls it when the module loads. */
    public AirDatepickerURLEntryPoint() {
    }

    @Override
    public void onModuleLoad() {
        DateTimePickerEngines.register(DateTimePickerEngines.AIRDATEPICKER, AirDatepickerEngine::new);

        ScriptInjector.fromUrl("https://cdn.jsdelivr.net/npm/air-datepicker@3.6.0/air-datepicker.min.js")
                .setWindow(ScriptInjector.TOP_WINDOW).inject();

        StyleInjector.injectCSS("https://cdn.jsdelivr.net/npm/air-datepicker@3.6.0/air-datepicker.min.css");
    }
}
