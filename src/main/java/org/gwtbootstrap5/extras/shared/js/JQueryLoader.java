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

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

import elemental2.dom.DomGlobal;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

/**
 * Loads jQuery for the extras whose library still requires it (Bootbox, Summernote,
 * bootstrap-colorpicker, jQuery UI). Core gwtbootstrap5 no longer loads jQuery.
 */
public final class JQueryLoader {

    interface Resources extends ClientBundle {
        Resources INSTANCE = GWT.create(Resources.class);

        @Source("jquery-3.7.1.min.cache.js")
        TextResource jQuery();

        @Source("jquery-migrate-3.5.0.min.cache.js")
        TextResource jQueryMigrate();
    }

    private static final String JQUERY_URL = "https://code.jquery.com/jquery-3.7.1.min.js";
    private static final String JQUERY_MIGRATE_URL = "https://code.jquery.com/jquery-migrate-3.5.0.min.js";

    private static final String[] BOOTSTRAP_PLUGINS = {
        "Alert", "Button", "Carousel", "Collapse", "Dropdown", "Modal",
        "Offcanvas", "Popover", "ScrollSpy", "Tab", "Toast", "Tooltip"
    };

    private JQueryLoader() {
    }

    /**
     * @return true if jQuery is already loaded in the top window
     */
    public static boolean isLoaded() {
        return !"undefined".equals(Js.typeof(Js.global().get("jQuery")));
    }

    /**
     * Injects the bundled jQuery and jQuery Migrate unless jQuery is already loaded.
     * Injection is synchronous, so scripts injected afterwards can use jQuery.
     */
    public static void ensureLoaded() {
        if (!isLoaded()) {
            ScriptInjector.fromString(Resources.INSTANCE.jQuery().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .inject();
            ScriptInjector.fromString(Resources.INSTANCE.jQueryMigrate().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .inject();
        }
        registerBootstrapPlugins();
    }

    /**
     * Loads jQuery and jQuery Migrate from the CDN unless jQuery is already loaded, then runs
     * the given callback. Inject scripts that depend on jQuery from the callback.
     *
     * @param onLoaded runs once jQuery is available
     */
    public static void ensureLoadedFromUrl(final Runnable onLoaded) {
        Runnable ready = () -> {
            registerBootstrapPlugins();
            onLoaded.run();
        };
        if (isLoaded()) {
            ready.run();
            return;
        }
        injectUrl(JQUERY_URL, () -> injectUrl(JQUERY_MIGRATE_URL, ready));
    }

    /**
     * Registers Bootstrap's jQuery plugins ({@code $.fn.modal}, {@code $.fn.tooltip}, ...) when
     * Bootstrap was loaded before jQuery. Bootstrap only registers them if jQuery is present when
     * Bootstrap itself loads, but core injects Bootstrap before the extras inject jQuery, and
     * jQuery-based libraries such as Bootbox call {@code $(el).modal(...)}.
     */
    private static void registerBootstrapPlugins() {
        Object bootstrap = Js.global().get("bootstrap");
        Object jQuery = Js.global().get("jQuery");
        if (bootstrap == null || jQuery == null
                || (DomGlobal.document.body != null && DomGlobal.document.body.hasAttribute("data-bs-no-jquery"))) {
            return;
        }
        JsPropertyMap<Object> fn = Js.asPropertyMap(Js.asPropertyMap(jQuery).get("fn"));
        for (String name : BOOTSTRAP_PLUGINS) {
            Object plugin = Js.asPropertyMap(bootstrap).get(name);
            String key = name.toLowerCase();
            if (plugin == null || fn.has(key) || !Js.asPropertyMap(plugin).has("jQueryInterface")) {
                continue;
            }
            Object jQueryInterface = Js.asPropertyMap(plugin).get("jQueryInterface");
            Js.asPropertyMap(jQueryInterface).set("Constructor", plugin);
            fn.set(key, jQueryInterface);
        }
    }

    private static void injectUrl(final String url, final Runnable onSuccess) {
        ScriptInjector.fromUrl(url)
                .setWindow(ScriptInjector.TOP_WINDOW)
                .setCallback(new Callback<Void, Exception>() {
                    @Override
                    public void onFailure(final Exception reason) {
                        GWT.log("Failed to load " + url + ": " + reason.getMessage());
                    }

                    @Override
                    public void onSuccess(final Void result) {
                        onSuccess.run();
                    }
                })
                .inject();
    }
}
