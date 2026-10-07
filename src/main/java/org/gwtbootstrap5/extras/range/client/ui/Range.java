package org.gwtbootstrap5.extras.range.client.ui;

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

import elemental2.core.Global;
import elemental2.core.JsArray;
import jsinterop.base.Js;

/**
 * Slider range with a min value and a max value.
 */
public class Range {

    private double minValue;
    private double maxValue;

    /** Creates a range from 0 to 0, for subclasses. */
    protected Range() {
    }

    /**
     * Create a slider range with a min value and a max value.
     *
     * @param minValue min Value
     * @param maxValue max Value
     */
    public Range(final double minValue, final double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Creates a slider range with a JavaScript number array. <br>
     * <br>
     * This constructor is useful in JSNI calls.
     *
     * @param array array
     */
    public Range(final JsArray<Double> array) {
        this(array.getAt(0), array.getAt(1));
    }

    /**
     * Returns the min value.
     *
     * @return the min value
     */
    public double getMinValue() {
        return minValue;
    }

    /**
     * Returns the max value.
     *
     * @return the max value
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * Converts the range to a JavaScript number array.
     *
     * @return a JavaScript number array
     */
    public JsArray<Double> toJsArray() {
        JsArray<Double> array = new JsArray<>();
        array.push(minValue, maxValue);
        return array;
    }

    /**
     * Converts the given string to a range instance.<br>
     * <br>
     * Useful when using UiBinder.
     *
     * @param value value
     * @return Range
     */
    public static Range fromString(String value) {
        if (value == null || value.isEmpty())
            return null;
        JsArray<Double> array = Js.uncheckedCast(Global.JSON.parse(value));
        return new Range(array);
    }

    @Override
    public String toString() {
        return "[" +
                getMinValue() + ", " +
                getMaxValue() +
                "]";
    }

}
