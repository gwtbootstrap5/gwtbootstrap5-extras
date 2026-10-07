package org.gwtbootstrap5.extras.select.client.ui.engines.tomselect;

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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * The script of Tom Select 2, bundled with the module.
 *
 * @author godi
 */
public interface TomSelectClientBundle extends ClientBundle {

    /** The bundle. */
    TomSelectClientBundle INSTANCE = GWT.create(TomSelectClientBundle.class);

    /** The version of the library. */
    String VERSION = "2.6.2";

    /**
     * The script of Tom Select.
     *
     * @return the script
     */
    @Source("../../../resource/tom-select-" + VERSION + "/js/tom-select.min.cache.js")
    TextResource tomSelect();

}
