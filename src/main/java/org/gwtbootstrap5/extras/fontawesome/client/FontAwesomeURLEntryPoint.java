package org.gwtbootstrap5.extras.fontawesome.client;

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
import org.gwtbootstrap5.client.ui.util.StyleInjector;

/**
 * Loads the Font Awesome style sheet from a CDN instead of the bundled files.
 * It is the entry point of the {@code FontAwesomeURL} GWT module: inherit the module
 * rather than calling it.
 *
 * @author Sven Jacobs
 */
public class FontAwesomeURLEntryPoint implements EntryPoint {

    /** Creates the entry point; GWT calls it when the module loads. */
    public FontAwesomeURLEntryPoint() {
    }

    @Override
    public void onModuleLoad() {
        StyleInjector.injectCSS("https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@7.3.1/css/all.min.css");
    }

}
