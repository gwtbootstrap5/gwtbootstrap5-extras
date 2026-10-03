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
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

/**
 * Bootbox dialog options.
 *
 * @author Xiaodong Sun
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class DialogOptions {

    @JsOverlay
    private static final String BUTTON_PREFIX = "bootbox_btn_";

    protected DialogOptions() {
    }

    /**
     * Creates a new {@link DialogOptions}.
     *
     * @param message e
     * @return e
     */
    @JsOverlay
    public static DialogOptions newOptions(final String message) {
        DialogOptions options = new DialogOptions();
        options.setMessage(message);
        return options;
    }

    @JsOverlay
    final void setMessage(final String message) {
        set("message", message);
    }

    /**
     * Adds a header to the dialog and places this text in an H4.
     *
     * @param title e
     */
    @JsOverlay
    public final void setTitle(final String title) {
        set("title", title);
    }

    /**
     * The locale settings used to translate the three standard button
     * labels: <b>OK</b>, <b>CONFIRM</b>, <b>CANCEL</b>.
     *
     * @param locale e
     */
    @JsOverlay
    public final void setLocale(final BootboxLocale locale) {
        BootboxLocale l = (locale != null) ? locale : BootboxLocale.getDefault();
        set("locale", l.getLocale());
    }

    /**
     * Allows the user to dismiss the dialog by hitting
     * <code>ESC</code>, which will invoke this function.<br>
     * <br>
     * Defaults to <code>null</code> for custom dialogs.
     *
     * @param callback e
     */
    @JsOverlay
    public final void setOnEscape(final SimpleCallback callback) {
        if (callback != null) {
            set("onEscape", (JsSimpleCallback) callback::callback);
        } else {
            remove("onEscape");
        }
    }

    /**
     * Whether the dialog should be shown immediately.<br>
     * <br>
     * Defaults to <code>true</code>.
     *
     * @param show e
     */
    @JsOverlay
    public final void setShow(final boolean show) {
        set("show", show);
    }

    /**
     * Whether the dialog should have a backdrop or not.
     * Also determines whether clicking on the backdrop dismisses the modal.
     * <ul>
     * <li><code>null</code>: The backdrop is displayed, but clicking on it has no effect.</li>
     * <li><code>true</code>: The backdrop is displayed, and clicking on it dismisses the dialog.</li>
     * <li><code>false</code>: The backdrop is not displayed.</li>
     * </ul>
     * Defaults to <code>null</code>.
     *
     * @param backdrop e
     */
    @JsOverlay
    public final void setBackdrop(final Boolean backdrop) {
        if (backdrop == null) {
            remove("backdrop");
        } else {
            set("backdrop", backdrop);
        }
    }

    /**
     * Whether the dialog should have a close button or not.<br>
     * <br>
     * Defaults to <code>true</code>.
     *
     * @param closeButton e
     */
    @JsOverlay
    public final void setCloseButton(final boolean closeButton) {
        set("closeButton", closeButton);
    }

    /**
     * Animate the dialog in and out.<br>
     * <br>
     * Defaults to <code>true</code>.
     *
     * @param animate e
     */
    @JsOverlay
    public final void setAnimate(final boolean animate) {
        set("animate", animate);
    }

    /**
     * An additional class to apply to the dialog wrapper.<br>
     * <br>
     * Defaults to <code>true</code>.
     *
     * @param className e
     */
    @JsOverlay
    public final void setClassName(final String className) {
        set("className", className);
    }

    /**
     * Adds the relevant Bootstrap modal size class to the dialog wrapper.<br>
     * <br>
     * Defaults to <code>null</code>.
     *
     * @param size e
     */
    @JsOverlay
    public final void setSize(final BootboxSize size) {
        if (size != null) {
            set("size", size.getSize());
        } else {
            remove("size");
        }
    }

    /**
     * Adds a custom button.
     *
     * @param label e
     */
    @JsOverlay
    public final void addButton(String label) {
        addButton(label, (String) null);
    }

    /**
     * Adds a custom button with a class name.
     *
     * @param label e
     * @param className e
     */
    @JsOverlay
    public final void addButton(String label, String className) {
        addButton(label, className, SimpleCallback.DEFAULT_SIMPLE_CALLBACK);
    }

    /**
     * Adds a custom button with a callback.
     *
     * @param label e
     * @param callback e
     */
    @JsOverlay
    public final void addButton(String label, SimpleCallback callback) {
        addButton(label, null, callback);
    }

    /**
     * Adds a custom button with a class name and a callback.
     *
     * @param label e
     * @param className e
     * @param callback e
     */
    @JsOverlay
    public final void addButton(String label, String className, SimpleCallback callback) {
        addButton(BUTTON_PREFIX + ButtonIndex.next(), label, className,
            callback != null ? callback : SimpleCallback.DEFAULT_SIMPLE_CALLBACK);
    }

    @JsOverlay
    private void addButton(String name, String label, String className, SimpleCallback callback) {
        JsPropertyMap<Object> buttons = Js.uncheckedCast(get("buttons"));
        if (buttons == null) {
            buttons = JsPropertyMap.of();
            set("buttons", buttons);
        }
        JsPropertyMap<Object> button = JsPropertyMap.of(
            "label", label,
            "callback", (JsSimpleCallback) callback::callback);
        if (className != null && !className.isEmpty()) {
            button.set("className", className);
        }
        buttons.set(name, button);
    }

    @JsOverlay
    final Object get(final String key) {
        return Js.asPropertyMap(this).get(key);
    }

    @JsOverlay
    final void set(final String key, final Object value) {
        Js.asPropertyMap(this).set(key, value);
    }

    @JsOverlay
    final void remove(final String key) {
        Js.asPropertyMap(this).delete(key);
    }
}
