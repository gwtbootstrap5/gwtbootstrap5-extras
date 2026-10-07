package org.gwtbootstrap5.extras.bootbox.client;

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
import org.gwtbootstrap5.extras.bootbox.client.callback.PromptCallback;
import org.gwtbootstrap5.extras.bootbox.client.callback.SimpleCallback;
import org.gwtbootstrap5.extras.bootbox.client.js.BootboxGlobal;
import org.gwtbootstrap5.extras.bootbox.client.options.AlertOptions;
import org.gwtbootstrap5.extras.bootbox.client.options.BootboxLocale;
import org.gwtbootstrap5.extras.bootbox.client.options.ConfirmOptions;
import org.gwtbootstrap5.extras.bootbox.client.options.DialogOptions;
import org.gwtbootstrap5.extras.bootbox.client.options.PromptOptions;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.EventListener;

/**
 * Bootbox.js is a small JavaScript library which allows you
 * to create programmatic dialog boxes using Bootstrap modals.
 *
 * @author Xiaodong Sun
 * @see <a href="https://bootboxjs.com/">...</a>
 */
public class Bootbox {

    private static final String SHOWN_EVENT = "shown.bs.modal";
    private static final String BOOTBOX_CLASS = "bootbox";

    // The listener of init(SimpleCallback), kept to remove it when the callback changes
    private static EventListener shownListener;

    /** Creates an instance. It only has static methods, so there is no need to. */
    public Bootbox() {
    }

    /**
     * Displays a message in a modal dialog box.
     *
     * @param msg the message to be displayed.
     */
    public static void alert(String msg) {
        BootboxGlobal.alert(msg);
    }

    /**
     * Displays a message in a modal dialog box.
     * With callback handler.
     *
     * @param msg      the message to be displayed.
     * @param callback the callback handler.
     */
    public static void alert(String msg, SimpleCallback callback) {
        BootboxGlobal.alert(msg, callback::callback);
    }

    /**
     * Displays a customized alert with the given {@link AlertOptions}.
     *
     * @param options the options of the alert
     */
    public static void alert(AlertOptions options) {
        BootboxGlobal.alert(options);
    }

    /**
     * Displays a message in a modal dialog box, along with the standard 'OK' and
     * 'Cancel' buttons.
     *
     * @param msg      the message to be displayed.
     * @param callback the callback handler.
     */
    public static void confirm(String msg, ConfirmCallback callback) {
        BootboxGlobal.confirm(msg, callback::callback);
    }

    /**
     * Displays a customized confirm with the given {@link ConfirmOptions}.
     *
     * @param options the options of the confirm
     */
    public static void confirm(ConfirmOptions options) {
        BootboxGlobal.confirm(options);
    }

    /**
     * Displays a request for information in a modal dialog box, along with the
     * standard 'OK' and 'Cancel' buttons.
     *
     * @param msg      the message to be displayed.
     * @param callback the callback handler.
     */
    public static void prompt(String msg, PromptCallback callback) {
        BootboxGlobal.prompt(msg, callback::callback);
    }

    /**
     * Displays a customized prompt with the given {@link PromptOptions}.
     *
     * @param options the options of the prompt
     */
    public static void prompt(PromptOptions options) {
        BootboxGlobal.prompt(options);
    }

    /**
     * Displays a completely customizable dialog in a modal dialog box.
     *
     * @param options the dialog options.
     */
    public static void dialog(final DialogOptions options) {
        BootboxGlobal.dialog(options);
    }

    /**
     * Sets a callback that runs every time a Bootbox dialog is shown, whether it comes from
     * {@code alert}, {@code confirm}, {@code prompt} or {@code dialog}. It runs once the dialog
     * is visible, on Bootstrap's {@code shown.bs.modal} event.
     * <p>
     * There is one such callback: calling this again replaces the previous one, and
     * {@code null} removes it. To run code for a single dialog, use
     * {@link DialogOptions#setOnShown(SimpleCallback)} instead.
     *
     * @param callback called each time a dialog is shown, or {@code null} to remove it
     */
    public static void init(SimpleCallback callback) {
        if (shownListener != null) {
            DomGlobal.document.removeEventListener(SHOWN_EVENT, shownListener);
            shownListener = null;
        }
        if (callback != null) {
            shownListener = event -> {
                if (event.target instanceof Element && ((Element) event.target).classList.contains(BOOTBOX_CLASS)) {
                    callback.callback();
                }
            };
            DomGlobal.document.addEventListener(SHOWN_EVENT, shownListener);
        }
    }

    /**
     * Set many of the default options shown in the dialog example.<br>
     * <br>
     * Many of these options are also applied to the basic wrapper methods
     * and can be overridden whenever the wrapper methods are invoked
     * with a single options argument.
     *
     * @param options the default options
     */
    public static void setDefaults(DialogOptions options) {
        BootboxGlobal.setDefaults(options);
    }

    /**
     * Sets a locale.
     *
     * @param locale if <code>null</code>, defaults to {@link BootboxLocale#EN}.
     */
    public static void setLocale(final BootboxLocale locale) {
        BootboxLocale l = (locale != null) ? locale : BootboxLocale.getDefault();
        BootboxGlobal.setLocale(l.getLocale());
    }


    /**
     * Hide all currently active bootbox dialogs.
     * <p>Individual dialogs can be closed as per normal Bootstrap dialogs: dialog.modal('hide').
     */
    public static void hideAll() {
        BootboxGlobal.hideAll();
    }
}
