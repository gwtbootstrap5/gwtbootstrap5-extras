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
import org.gwtbootstrap5.client.ui.util.StyleInjector;

import jsinterop.base.Js;

/**
 * Loads bootstrap-slider from a CDN instead of the bundled files, unless the page already has it.
 * It is the entry point of the {@code RangeURL} GWT module: inherit the module
 * rather than calling it.
 *
 * @author Xiaodong SUN
 */
public class RangeURLEntryPoint implements EntryPoint {

    /** Creates the entry point; GWT calls it when the module loads. */
    public RangeURLEntryPoint() {
    }

    @Override
    public void onModuleLoad() {
        if (!isSliderLoaded()) {
            ScriptInjector.fromUrl("https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/11.0.2/bootstrap-slider.min.js")
                    .setWindow(ScriptInjector.TOP_WINDOW).inject();

            StyleInjector.injectCSS("https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/11.0.2/css/bootstrap-slider.min.css");
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
