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

/**
 * Boostrap slider options enumeration type.<br>
 * <br>
 * Options can be passed either as a data (<code>data-bs-slider-foo</code>)
 * attribute, or as part of an object in the slider call. The only exception
 * here is the <strong>formatter</strong> option, that can not be passed as a
 * data attribute.
 *
 * @author Xiaodong SUN
 * @see <a href="https://github.com/seiyria/bootstrap-slider#options">...</a>
 */
enum RangeOption {

    ID("id"),
    MIN("min"),
    MAX("max"),
    STEP("step"),
    PRECISION("precision"),
    ORIENTATION("orientation"),
    VALUE("value"),
    RANGE("range"),
    SELECTION("selection"),
    TOOLTIP("tooltip"),
    TOOLTIP_SPLIT("tooltip_split"),
    TOOLTIP_POSITION("tooltip_position"),
    HANDLE("handle"),
    REVERSED("reversed"),
    ENABLED("enabled"),
    FORMATTER("formatter"),
    NATURAL_ARROW_KEYS("natural_arrow_keys"),
    TICKS("ticks"),
    TICKS_POSITIONS("ticks_positions"),
    TICKS_LABELS("ticks_labels"),
    TICKS_SNAP_BOUNDS("ticks_snap_bounds"),
    SCALE("scale"),
    FOCUS("focus");

    private final String name;
    private final String dataAttrName;
    private final static String DATA_ATTRIBUTE_PREFIX = "data-slider-";

    /**
     * @param name the option name
     */
    RangeOption(final String name) {
        this.name = name;
        this.dataAttrName = name.replaceAll("_", "-");
    }

    /**
     * Returns the option name.
     *
     * @return the option name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the data attribute name prefixed by
     * {@value #DATA_ATTRIBUTE_PREFIX}.
     *
     * @return the data attribute name
     */
    public String getDataAttribute() {
        return DATA_ATTRIBUTE_PREFIX + dataAttrName;
    }
}
