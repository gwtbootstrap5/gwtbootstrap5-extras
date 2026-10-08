package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.tempusdominus;

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
import org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.DateTimePickerEngines;

/**
 * Registers the Tempus Dominus engine of the date pickers and loads the
 * bundled Tempus Dominus script and style sheet, unless the page loads them already.
 *
 * @author Sven Jacobs
 */
public class TempusDominusEntryPoint implements EntryPoint {

    /** Creates the entry point; GWT calls it when the module loads. */
    public TempusDominusEntryPoint() {
    }

    @Override
    public void onModuleLoad() {
        DateTimePickerEngines.register(DateTimePickerEngines.TEMPUSDOMINUS, TempusDominusEngine::new);

        ScriptInjector.fromString(TempusDominusClientBundle.INSTANCE.tempusDominus().getText())
                .setWindow(ScriptInjector.TOP_WINDOW).inject();
    }
}
