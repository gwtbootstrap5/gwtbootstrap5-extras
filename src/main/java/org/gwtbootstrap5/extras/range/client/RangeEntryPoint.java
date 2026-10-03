package org.gwtbootstrap5.extras.range.client;

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

import jsinterop.base.Js;

/**
 * @author Xiaodong SUN
 */
public class RangeEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        if (!isSliderLoaded()) {
            ScriptInjector.fromString(RangeClientBundle.INSTANCE.slider().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
        }
    }

    /**
     * Check if slider is already loaded.
     *
     * @return <code>true</code> if slider is loaded, <code>false</code> otherwise
     */
    private boolean isSliderLoaded() {
        return !"undefined".equals(Js.typeof(Js.global().get("Slider")));
    }
}
