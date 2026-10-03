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

import jsinterop.base.Js;

/**
 * This slider simply takes a numeric value.
 *
 * @author Xiaodong SUN
 */
public class Slider extends RangeBase<Double> {

    /**
     * Creates a numerical slider.
     */
    public Slider() {
        setRange(false);
    }

    /**
     * Creates a numerical slider with min, max, and value.
     *
     * @param min min
     * @param max max
     * @param value value
     */
    @UiConstructor
    public Slider(final double min, final double max, final double value) {
        this();
        setMin(min);
        setMax(max);
        setValue(value);
    }

    @Override
    protected String format(Double value) {
        return value.toString();
    }

    @Override
    protected Double convertValue(String value) {
        if (value == null || value.isEmpty())
            return null;
        return Double.valueOf(value);
    }

    @Override
    protected String formatTooltip(Double value) {
        if (value == null) return null;

        return super.formatTooltip(value);
    }

    @Override
    protected void fireSlideEvent(Double value) {
        if (value == null) return;

        super.fireSlideEvent(value);
    }

    @Override
    protected void fireSlideStartEvent(Double value) {
        if (value == null) return;

        super.fireSlideStartEvent(value);
    }

    @Override
    protected void fireSlideStopEvent(Double value) {
        if (value == null) return;

        super.fireSlideStopEvent(value);
    }

    @Override
    protected void fireChangeEvent(Double value) {
        if (value == null) return;

        super.fireChangeEvent(value);
    }

    @Override
    protected Object toJsValue(Double value) {
        return value;
    }

    @Override
    protected Double toValue(Object value) {
        return value == null ? null : Js.asDouble(value);
    }
}
