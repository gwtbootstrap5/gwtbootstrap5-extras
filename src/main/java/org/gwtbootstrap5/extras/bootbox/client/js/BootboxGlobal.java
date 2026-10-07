package org.gwtbootstrap5.extras.bootbox.client.js;

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
import jsinterop.annotations.JsType;

/**
 * Native binding for the global {@code bootbox} object.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "bootbox")
public class BootboxGlobal {

    /** Don't call it: the methods are static and map to the global {@code bootbox} object. */
    public BootboxGlobal() {
    }

    /**
     * Calls {@code bootbox.alert(message)}.
     *
     * @param msg the message
     */
    public static native void alert(String msg);

    /**
     * Calls {@code bootbox.alert(message, callback)}.
     *
     * @param msg the message
     * @param callback called when the dialog is dismissed
     */
    public static native void alert(String msg, JsSimpleCallback callback);

    /**
     * Calls {@code bootbox.alert(options)}.
     *
     * @param options the dialog options, such as {@code AlertOptions}
     */
    public static native void alert(Object options);

    /**
     * Calls {@code bootbox.confirm(message, callback)}.
     *
     * @param msg the message
     * @param callback called with the user's answer
     */
    public static native void confirm(String msg, JsConfirmCallback callback);

    /**
     * Calls {@code bootbox.confirm(options)}.
     *
     * @param options the dialog options, such as {@code ConfirmOptions}
     */
    public static native void confirm(Object options);

    /**
     * Calls {@code bootbox.prompt(title, callback)}.
     *
     * @param msg the title of the dialog
     * @param callback called with the text the user entered, or {@code null} if they cancelled
     */
    public static native void prompt(String msg, JsPromptCallback callback);

    /**
     * Calls {@code bootbox.prompt(options)}.
     *
     * @param options the dialog options, such as {@code PromptOptions}
     */
    public static native void prompt(Object options);

    /**
     * Calls {@code bootbox.dialog(options)}, a dialog with custom buttons.
     *
     * @param options the dialog options, such as {@code DialogOptions}
     */
    public static native void dialog(Object options);

    /**
     * Calls {@code bootbox.init(argument)}. In Bootbox 6 it creates a new Bootbox bound to the
     * jQuery given as argument and returns it, so a callback passed here is never called.
     *
     * @param callback ignored by Bootbox as a callback
     * @deprecated it never calls the callback. Use {@code Bootbox.init(SimpleCallback)}, which runs
     *             it every time a dialog is shown, or {@code DialogOptions.setOnShown(SimpleCallback)}.
     */
    @Deprecated
    public static native void init(JsSimpleCallback callback);

    /**
     * Calls {@code bootbox.setDefaults(options)}: the options apply to the dialogs created after.
     *
     * @param options the default options
     */
    public static native void setDefaults(Object options);

    /**
     * Calls {@code bootbox.setLocale(name)}: the language of the buttons of the dialogs.
     *
     * @param locale the locale name, such as {@code "es"}
     */
    public static native void setLocale(String locale);

    /** Calls {@code bootbox.hideAll()}, which closes every open dialog. */
    public static native void hideAll();
}
