package org.gwtbootstrap5.extras.range.client.ui.base;

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

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Native binding for bootstrap-slider's vanilla API ({@code new Slider(element, options)}).
 *
 * @see <a href="https://github.com/seiyria/bootstrap-slider#functions">bootstrap-slider functions</a>
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Slider")
class BootstrapSlider {

    /**
     * Callback registered with {@link #on(String, EventCallback)}; receives the event value
     * ({@code {oldValue, newValue}} for {@code change}, nothing for enable/disable).
     */
    @JsFunction
    interface EventCallback {
        void onEvent(Object value);
    }

    /**
     * The {@code formatter} option: returns the tool-tip text for a value.
     */
    @JsFunction
    interface Formatter {
        String format(Object value);
    }

    /**
     * The resolved options (constructor options, data attributes and defaults).
     */
    public Object options;

    BootstrapSlider(HTMLElement element, Object options) {
    }

    public native Object getValue();

    public native void setValue(Object value);

    public native HTMLElement getElement();

    public native void destroy();

    public native void disable();

    public native void enable();

    public native void toggle();

    public native boolean isEnabled();

    public native void setAttribute(String attribute, Object value);

    public native Object getAttribute(String attribute);

    /**
     * Rebuilds the slider from its options. This also drops every callback registered with
     * {@link #on(String, EventCallback)}.
     */
    public native void refresh();

    public native void relayout();

    public native void on(String event, EventCallback callback);
}
