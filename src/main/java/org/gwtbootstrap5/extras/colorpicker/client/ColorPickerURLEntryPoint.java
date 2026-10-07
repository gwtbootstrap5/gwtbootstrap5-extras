package org.gwtbootstrap5.extras.colorpicker.client;

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

import org.gwtbootstrap5.extras.shared.js.JQueryLoader;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import org.gwtbootstrap5.client.ui.util.StyleInjector;

/**
 * Loads bootstrap-colorpicker from a CDN instead of the bundled files, after jQuery.
 * It is the entry point of the {@code ColorPickerURL} GWT module: inherit the module
 * rather than calling it.
 *
 * @author Sven Jacobs
 */
public class ColorPickerURLEntryPoint implements EntryPoint {

    /** Creates the entry point; GWT calls it when the module loads. */
    public ColorPickerURLEntryPoint() {
    }

    @Override
    public void onModuleLoad() {
        JQueryLoader.ensureLoadedFromUrl(() -> ScriptInjector
                .fromUrl("https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/3.4.0/js/bootstrap-colorpicker.min.js")
                .setWindow(ScriptInjector.TOP_WINDOW).inject());

        StyleInjector.injectCSS("https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/3.4.0/css/bootstrap-colorpicker.min.css");
    }

}
