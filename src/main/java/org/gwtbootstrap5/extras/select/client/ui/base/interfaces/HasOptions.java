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

import java.util.List;

/**
 * A widget whose options can be changed.
 *
 * @param <T> the type of the options
 */
public interface HasOptions<T> {

    /** Deselects everything and removes every option. */
    void clearAll();

    /** Removes every option. */
    void clearOptions();

    /**
     * Replaces the options.
     *
     * @param options the new options
     */
    void setOptions(List<T> options);

    /**
     * Adds an option.
     *
     * @param option the option
     */
    void addOption(T option);

    /**
     * Adds options.
     *
     * @param option the options
     */
    void addOptions(List<T> option);

    /**
     * Returns the options.
     *
     * @return the options
     */
    List<T> getOptions();
}
