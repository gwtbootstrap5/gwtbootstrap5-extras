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

import org.gwtbootstrap5.extras.range.client.ui.base.RangeBase;

import com.google.gwt.uibinder.client.UiConstructor;

import elemental2.core.JsArray;
import jsinterop.base.Js;

/**
 * This slider takes as value a range with a min value and a max value.
 *
 * @author Xiaodong SUN
 */
public class RangeSlider extends RangeBase<Range> {

    /**
     * Creates a range slider.
     */
    public RangeSlider() {
        setRange(true);
    }

    /**
     * Creates a range slider with min, max, and range value.
     *
     * @param min min
     * @param max max
     * @param range range
     */
    public RangeSlider(final double min, final double max, final Range range) {
        this();
        setMin(min);
        setMax(max);
        setValue(range);
    }

    /**
     * Creates a range slider with min, max, and range value.<br>
     * <br>
     * Useful for UiBinder.
     *
     * @param min min
     * @param max max
     * @param value value
     */
    @UiConstructor
    public RangeSlider(final double min, final double max, final String value) {
        this(min, max, Range.fromString(value));
    }

    @Override
    protected String format(Range value) {
        return value.getMinValue() + " : " + value.getMaxValue();
    }

    @Override
    protected Range convertValue(String value) {
        return Range.fromString(value);
    }

    @Override
    protected String formatTooltip(Range value) {
        if (value == null) return null;

        return super.formatTooltip(value);
    }

    @Override
    protected void fireSlideEvent(Range value) {
        if (value == null) return;

        super.fireSlideEvent(value);
    }

    @Override
    protected void fireSlideStartEvent(Range value) {
        if (value == null) return;

        super.fireSlideStartEvent(value);
    }

    @Override
    protected void fireSlideStopEvent(Range value) {
        if (value == null) return;

        super.fireSlideStopEvent(value);
    }

    @Override
    protected void fireChangeEvent(Range value) {
        if (value == null) return;

        super.fireChangeEvent(value);
    }

    @Override
    protected Object toJsValue(Range value) {
        return value == null ? null : value.toJsArray();
    }

    /**
     * Besides the two-number array, bootstrap-slider passes a single number to the formatter
     * for the separate min / max tool-tips; it becomes a range with equal bounds.
     */
    @Override
    protected Range toValue(Object value) {
        if (value == null) {
            return null;
        }
        if ("number".equals(Js.typeof(value))) {
            return new Range(Js.asDouble(value), Js.asDouble(value));
        }
        return new Range(Js.<JsArray<Double>>uncheckedCast(value));
    }
}
