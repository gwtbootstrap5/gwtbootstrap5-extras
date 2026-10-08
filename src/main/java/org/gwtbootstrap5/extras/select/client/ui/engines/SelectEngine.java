package org.gwtbootstrap5.extras.select.client.ui.engines;

/*-
 * ==========================LICENSE_START===============================
 * GwtBootstrap5
 * ======================================================================
 * Copyright (C) 2026 GwtBootstrap5
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

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectEngine;

/**
 * The JavaScript libraries that can draw a {@code Select} or {@code MultipleSelect}; the
 * {@code engine} attribute in UiBinder.
 * <p>
 * The engines are not part of the {@code Select} module: each one comes with the module that loads
 * its script, and registers itself when that module loads. An application only compiles the
 * engines whose module it inherits:
 * </p>
 * <ul>
 * <li>{@link #TOMSELECT}: {@code org.gwtbootstrap5.extras.select.client.TomSelectResources}, or
 * {@code TomSelectURL} to load it from a CDN.</li>
 * <li>{@link #CHOICESJS}: {@code org.gwtbootstrap5.extras.select.client.ChoicesResources}, or
 * {@code ChoicesURL}.</li>
 * <li>{@link #SLIMSELECT}: {@code org.gwtbootstrap5.extras.select.client.SlimSelectResources}, or
 * {@code SlimSelectURL}.</li>
 * </ul>
 * <p>
 * The modules register their engine from their entry point, which GWT runs before the entry point
 * of the application when the application's {@code <entry-point>} comes after the
 * {@code <inherits>}.
 * </p>
 */
public enum SelectEngine {
    /** <a href="https://tom-select.js.org/">Tom Select</a> 2. */
    TOMSELECT("TomSelect"),
    /** <a href="https://choices-js.github.io/Choices/">Choices.js</a> 11. */
    CHOICESJS("Choices"),
    /** <a href="https://slimselectjs.com/">Slim Select</a> 4. */
    SLIMSELECT("SlimSelect");

    private static final Map<SelectEngine, Supplier<ISelectEngine>> REGISTERED = new EnumMap<>(SelectEngine.class);

    private final String moduleName;

    SelectEngine(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Returns the module that brings this engine, with its bundled script. The module ending in
     * {@code URL} instead of {@code Resources} brings it too, loading the script from a CDN.
     *
     * @return the fully qualified name of the module
     */
    public String getModule() {
        return "org.gwtbootstrap5.extras.select.client." + moduleName + "Resources";
    }

    /**
     * Registers the code of an engine. The module of each engine calls it when it loads; an
     * application doesn't need to.
     *
     * @param engine  the library
     * @param factory creates a new engine of that library for each select
     */
    public static void register(SelectEngine engine, Supplier<ISelectEngine> factory) {
        if (engine == null || factory == null) {
            throw new NullPointerException("The engine and its factory can't be null");
        }

        REGISTERED.put(engine, factory);
    }

    /**
     * Returns whether the module of an engine is inherited, so selects can use it.
     *
     * @param engine the library
     * @return {@code true} if it is registered
     */
    public static boolean isRegistered(SelectEngine engine) {
        return REGISTERED.containsKey(engine);
    }

    /**
     * Returns the engines whose module is inherited.
     *
     * @return the registered engines, which can't be changed
     */
    public static Set<SelectEngine> getRegisteredEngines() {
        return Collections.unmodifiableSet(REGISTERED.keySet());
    }

    /**
     * Creates a new engine of a library.
     *
     * @param engine the library
     * @return a new engine
     * @throws IllegalStateException if the module of that engine isn't inherited
     */
    public static ISelectEngine getEngine(SelectEngine engine) {
        Supplier<ISelectEngine> factory = REGISTERED.get(engine);
        if (factory == null) {
            throw new IllegalStateException("The " + engine + " select engine isn't loaded: inherit "
                    + engine.getModule() + " (or " + engine.moduleName + "URL) in your .gwt.xml");
        }

        return factory.get();
    }

    /**
     * Creates a new engine of the only library whose module is inherited. Selects created without
     * an engine use it.
     *
     * @return a new engine
     * @throws IllegalStateException if no engine, or more than one, is registered
     */
    public static ISelectEngine getDefaultEngine() {
        if (REGISTERED.size() != 1) {
            throw new IllegalStateException(REGISTERED.isEmpty()
                    ? "No select engine is loaded: inherit " + TOMSELECT.getModule() + ", "
                            + CHOICESJS.getModule() + " or " + SLIMSELECT.getModule() + " in your .gwt.xml"
                    : "More than one select engine is loaded (" + REGISTERED.keySet()
                            + "): choose one with the engine of the select");
        }

        return getEngine(REGISTERED.keySet().iterator().next());
    }
}
