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

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Native jQuery binding for the extras whose third-party library still requires jQuery
 * (Bootbox, Summernote and bootstrap-colorpicker). Core gwtbootstrap5 does not use jQuery;
 * remove the plugin methods here together with the library that needs them.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "jQuery")
public class JQuery {

    /**
     * Wraps a GWT DOM element in a jQuery object.
     *
     * @param element the element to wrap
     * @return jQuery object of the element
     */
    @JsMethod(namespace = JsPackage.GLOBAL, name = "jQuery")
    public static native JQuery jQuery(com.google.gwt.dom.client.Element element);

    /**
     * Wraps an elemental2 DOM element in a jQuery object.
     *
     * @param element the element to wrap
     * @return jQuery object of the element
     */
    @JsMethod(namespace = JsPackage.GLOBAL, name = "jQuery")
    public static native JQuery jQuery(elemental2.dom.Element element);

    /**
     * jQuery on() method.
     *
     * @param events space-separated event types and optional namespaces
     * @param handler handler to execute when the event is triggered
     * @return this jQuery object for chaining
     */
    public native JQuery on(String events, JQueryEventHandler handler);

    /**
     * jQuery one() method: the handler runs at most once per element and event type.
     *
     * @param events space-separated event types and optional namespaces
     * @param handler handler to execute when the event is triggered
     * @return this jQuery object for chaining
     */
    public native JQuery one(String events, JQueryEventHandler handler);

    /**
     * jQuery off() method.
     *
     * @param events space-separated event types and optional namespaces, or just namespaces
     * @return this jQuery object for chaining
     */
    public native JQuery off(String events);

    /**
     * Initializes Summernote with the given options object.
     *
     * @param options Summernote options
     * @return this jQuery object for chaining
     */
    public native JQuery summernote(Object options);

    /**
     * Runs a Summernote command, e.g. {@code summernoteCommand("code")} or {@code summernoteCommand("destroy")}.
     *
     * @param command the command name
     * @param args the command arguments
     * @param <T> the command's return type
     * @return the command's result
     */
    @JsMethod(name = "summernote")
    public native <T> T summernoteCommand(String command, Object... args);

    /**
     * Initializes bootstrap-colorpicker with the given options object.
     *
     * @param options colorpicker options
     * @return this jQuery object for chaining
     */
    public native JQuery colorpicker(Object options);

    /**
     * Runs a bootstrap-colorpicker command, e.g. {@code colorpickerCommand("setValue", "#fff")}.
     *
     * @param command the command name
     * @param args the command arguments
     * @param <T> the command's return type
     * @return the command's result
     */
    @JsMethod(name = "colorpicker")
    public native <T> T colorpickerCommand(String command, Object... args);

}
