package org.gwtbootstrap5.extras.select.client.ui.engines.slimselect;


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

import org.gwtbootstrap5.client.ui.util.StyleInjector;
import org.gwtbootstrap5.extras.select.client.ui.engines.SelectEngine;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;

/**
 * Loads Slim Select from a CDN instead of the bundled files and registers
 * {@code SelectEngine.SLIMSELECT}. The style sheet that applies the Bootstrap theme to it comes
 * from the module. It is the entry point of the {@code SlimSelectURL} GWT module: inherit the module
 * rather than calling it.
 */
public class SlimSelectURLEntryPoint implements EntryPoint {

    /** Creates the entry point; GWT calls it when the module loads. */
    public SlimSelectURLEntryPoint() {
    }

    @Override
    public void onModuleLoad() {
        SelectEngine.register(SelectEngine.SLIMSELECT, SlimSelectEngine::new);

        ScriptInjector.fromUrl("https://cdn.jsdelivr.net/npm/slim-select@4.5.0/dist/slimselect.js")
                .setWindow(ScriptInjector.TOP_WINDOW).inject();

        StyleInjector.injectCSS("https://cdn.jsdelivr.net/npm/slim-select@4.5.0/dist/slimselect.css");
    }
}
