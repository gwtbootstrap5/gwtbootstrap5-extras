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

import org.gwtbootstrap5.extras.bootbox.client.callback.SimpleCallback;
import org.gwtbootstrap5.extras.bootbox.client.js.JsSimpleCallback;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Alert options.
 *
 * @author Xiaodong Sun
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class AlertOptions extends DialogOptions {

    /** Use {@link #newOptions} instead; the constructor exists for JsInterop. */
    protected AlertOptions() {}
    
    /**
     * Creates a new {@link AlertOptions}.
     *
     * @param message the message of the dialog
     * @return the options, to set the rest of them
     */
    @JsOverlay
    public static AlertOptions newOptions(final String message) {
        AlertOptions options = new AlertOptions();
        options.setMessage(message);
        return options;
    }

    /**
     * Sets the function Bootbox calls when the dialog is dismissed ({@code callback}).
     *
     * @param callback the callback
     */
    @JsOverlay
    public final void setCallback(SimpleCallback callback) {
        set("callback", (JsSimpleCallback) callback::callback);
    }
}
