package org.gwtbootstrap5.extras.select.client.ui.base.interfaces;

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

/**
 * A widget with a search box that filters its options.
 */
public interface HasSearch {

    /**
     * Shows or hides the search box.
     *
     * @param enabled {@code true} to show it
     */
    void setSearchEnabled(boolean enabled);

    /**
     * Sets the placeholder of the search box.
     *
     * @param placeholder the placeholder
     */
    void setSearchPlaceholder(String placeholder);

    /**
     * Loads the options for a search, as if the user had typed it.
     *
     * @param search the search text
     */
    void triggerSearch(String search);
}
