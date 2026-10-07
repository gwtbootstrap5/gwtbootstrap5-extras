package org.gwtbootstrap5.extras.select.client.ui.base.engine;

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
 * The callbacks a {@link ISelectEngine} calls on the library's events; the widget fires its GWT
 * events from them.
 */
public interface ISelectHandlers {

    /** Called when the JavaScript select is ready. */
    void onLoaded();

    /**
     * Called when the library needs the options for a search.
     *
     * @param query the search text
     * @param callback to call with the options found
     */
    void onAsyncLoad(String query, OnAsyncLoadCallback callback);

    /** Called when the dropdown starts opening. */
    void onShow();

    /** Called when the dropdown is open. */
    void onShown();

    /** Called when the dropdown starts closing. */
    void onHide();

    /** Called when the dropdown is closed. */
    void onHidden();

    /** Called when the selection changes. */
    void onChange();

    /** Called when the select gets the focus. */
    void onFocus();

    /** Called when the select loses the focus. */
    void onBlur();

    /** Receives the options found for a search. */
    interface OnAsyncLoadCallback {

        /**
         * Gives the options found.
         *
         * @param options the options, empty if none was found
         */
        void callback(List<ISelectEngine.SelectOption> options);
    }
}
