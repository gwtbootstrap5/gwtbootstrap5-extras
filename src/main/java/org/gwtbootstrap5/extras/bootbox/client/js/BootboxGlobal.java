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

    public static native void alert(String msg);

    public static native void alert(String msg, JsSimpleCallback callback);

    public static native void alert(Object options);

    public static native void confirm(String msg, JsConfirmCallback callback);

    public static native void confirm(Object options);

    public static native void prompt(String msg, JsPromptCallback callback);

    public static native void prompt(Object options);

    public static native void dialog(Object options);

    public static native void init(JsSimpleCallback callback);

    public static native void setDefaults(Object options);

    public static native void setLocale(String locale);

    public static native void hideAll();
}
