package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines;

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

import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerEngine;

/**
 * The JavaScript libraries that can draw a date picker.
 * <p>
 * The engines are not part of the {@code DateTimePicker} module: each one comes with the module
 * that loads its script, and registers itself when that module loads. An application only
 * compiles the engines whose module it inherits:
 * </p>
 * <ul>
 * <li>{@link #TEMPUSDOMINUS}: {@code org.gwtbootstrap5.extras.datetimepicker.client.TempusDominusResources},
 * or {@code TempusDominusURL} to load it from a CDN.</li>
 * <li>{@link #AIRDATEPICKER}: {@code org.gwtbootstrap5.extras.datetimepicker.client.AirDatepickerResources},
 * or {@code AirDatepickerURL}.</li>
 * </ul>
 * <p>
 * The modules register their engine from their entry point, which GWT runs before the entry point
 * of the application when the application's {@code <entry-point>} comes after the
 * {@code <inherits>}.
 * </p>
 */
public enum DateTimePickerEngines {
    /** Tempus Dominus 6. */
    TEMPUSDOMINUS("TempusDominus"),
    /** Air Datepicker 3. */
    AIRDATEPICKER("AirDatepicker");

    private static final Map<DateTimePickerEngines, Supplier<IDateTimePickerEngine>> REGISTERED =
            new EnumMap<>(DateTimePickerEngines.class);

    private final String moduleName;

    DateTimePickerEngines(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Returns the module that brings this engine, with its bundled script. The module ending in
     * {@code URL} instead of {@code Resources} brings it too, loading the script from a CDN.
     *
     * @return the fully qualified name of the module
     */
    public String getModule() {
        return "org.gwtbootstrap5.extras.datetimepicker.client." + moduleName + "Resources";
    }

    /**
     * Registers the code of an engine. The module of each engine calls it when it loads; an
     * application doesn't need to.
     *
     * @param engine  the library
     * @param factory creates a new engine of that library for each picker
     */
    public static void register(DateTimePickerEngines engine, Supplier<IDateTimePickerEngine> factory) {
        if (engine == null || factory == null) {
            throw new NullPointerException("The engine and its factory can't be null");
        }

        REGISTERED.put(engine, factory);
    }

    /**
     * Returns whether the module of an engine is inherited, so pickers can use it.
     *
     * @param engine the library
     * @return {@code true} if it is registered
     */
    public static boolean isRegistered(DateTimePickerEngines engine) {
        return REGISTERED.containsKey(engine);
    }

    /**
     * Returns the engines whose module is inherited.
     *
     * @return the registered engines, which can't be changed
     */
    public static Set<DateTimePickerEngines> getRegisteredEngines() {
        return Collections.unmodifiableSet(REGISTERED.keySet());
    }

    /**
     * Creates a new engine of a library.
     *
     * @param engine the library
     * @return the engine
     * @throws IllegalStateException if the module of that engine isn't inherited
     */
    public static IDateTimePickerEngine getEngine(DateTimePickerEngines engine) {
        Supplier<IDateTimePickerEngine> factory = REGISTERED.get(engine);
        if (factory == null) {
            throw new IllegalStateException("The " + engine + " date picker engine isn't loaded: inherit "
                    + engine.getModule() + " (or " + engine.moduleName + "URL) in your .gwt.xml");
        }

        return factory.get();
    }

    /**
     * Creates a new engine of the only library whose module is inherited. Pickers created without
     * an engine use it.
     *
     * @return the engine
     * @throws IllegalStateException if no engine, or more than one, is registered
     */
    public static IDateTimePickerEngine getDefaultEngine() {
        if (REGISTERED.size() != 1) {
            throw new IllegalStateException(REGISTERED.isEmpty()
                    ? "No date picker engine is loaded: inherit " + TEMPUSDOMINUS.getModule() + " or "
                            + AIRDATEPICKER.getModule() + " in your .gwt.xml"
                    : "More than one date picker engine is loaded (" + REGISTERED.keySet()
                            + "): choose one with the engine of the picker");
        }

        return getEngine(REGISTERED.keySet().iterator().next());
    }
}
