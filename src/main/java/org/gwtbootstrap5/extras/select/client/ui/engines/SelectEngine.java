package org.gwtbootstrap5.extras.select.client.ui.engines;

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

import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectEngine;
import org.gwtbootstrap5.extras.select.client.ui.engines.tomselect.TomSelectEngine;

/**
 * The JavaScript libraries a {@code Select} can use; the {@code engine} attribute in UiBinder.
 * {@code TOMSELECT} is <a href="https://tom-select.js.org/">Tom Select</a> 2, which needs the
 * {@code TomSelectResources} or {@code TomSelectURL} module.
 */
public enum SelectEngine {
    /** <a href="https://tom-select.js.org/">Tom Select</a> 2. */
    TOMSELECT;

    /**
     * Creates the engine of a library.
     *
     * @param engine the library
     * @return a new engine
     */
    public static ISelectEngine getEngine(SelectEngine engine) {
        ISelectEngine selectEngine = new TomSelectEngine();
        switch (engine) {
            case TOMSELECT -> selectEngine = new TomSelectEngine();
//            case CHOICESJS -> selectEngine = new ChoicesEngine();
//            case SLIMSELECT -> selectEngine = new SlimSelectEngine();
        }

        return selectEngine;
    }
}
