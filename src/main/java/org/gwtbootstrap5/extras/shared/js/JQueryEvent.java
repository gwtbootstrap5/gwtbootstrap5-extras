package org.gwtbootstrap5.extras.shared.js;

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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import elemental2.dom.Event;
import elemental2.dom.EventTarget;

/**
 * Structural view of a jQuery event object. jQuery passes its own event wrapper to handlers,
 * not a DOM {@link Event}, so this type is bound to {@code Object} to avoid instanceof checks.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class JQueryEvent {

    /** The event type, e.g. {@code "click"} or {@code "summernote.change"}. */
    @JsProperty public String type;

    /** The element that initiated the event. */
    @JsProperty public EventTarget target;

    /** The element whose handler is currently running. */
    @JsProperty public EventTarget currentTarget;

    /** The browser event that jQuery wrapped, if any. */
    @JsProperty public Event originalEvent;

    /** Optional data passed when the handler was bound. */
    @JsProperty public Object data;

    /** Prevents the browser's default action. */
    public native void preventDefault();

    /** Stops the event from bubbling. */
    public native void stopPropagation();

}
