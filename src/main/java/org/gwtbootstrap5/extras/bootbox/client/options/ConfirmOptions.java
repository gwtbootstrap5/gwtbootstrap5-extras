package org.gwtbootstrap5.extras.bootbox.client.options;

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

import org.gwtbootstrap5.extras.bootbox.client.callback.ConfirmCallback;
import org.gwtbootstrap5.extras.bootbox.client.js.JsConfirmCallback;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Confirm options.
 *
 * @author Xiaodong Sun
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class ConfirmOptions extends DialogOptions {

    /** Use {@link #newOptions} instead; the constructor exists for JsInterop. */
    protected ConfirmOptions() {}
    
    /**
     * Creates a new {@link ConfirmOptions}.
     *
     * @param message the message of the dialog
     * @return the options, to set the rest of them
     */
    @JsOverlay
    public static ConfirmOptions newOptions(final String message) {
        ConfirmOptions options = new ConfirmOptions();
        options.setMessage(message);
        options.setCallback(ConfirmCallback.DEFAULT_CONFIRM_CALLBACK);
        return options;
    }

    /**
     * Sets the function Bootbox calls with the user's answer ({@code callback}).
     *
     * @param callback the callback
     */
    @JsOverlay
    public final void setCallback(ConfirmCallback callback) {
        set("callback", (JsConfirmCallback) callback::callback);
    }
}
