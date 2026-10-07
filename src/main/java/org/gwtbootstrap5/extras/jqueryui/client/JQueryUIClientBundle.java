package org.gwtbootstrap5.extras.jqueryui.client;

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
 * The script of jQuery UI, bundled with the module.
 *
 * @author Sven Jacobs
 */
public interface JQueryUIClientBundle extends ClientBundle {

    /** The bundle. */
    JQueryUIClientBundle INSTANCE = GWT.create(JQueryUIClientBundle.class);

    /** The version of the library. */
    String VERSION = "1.14.2";

    /**
     * The script of jQuery UI.
     *
     * @return the script
     */
    @Source("resource/js/jquery-ui-" + VERSION + ".min.cache.js")
    TextResource jQueryUI();

}
