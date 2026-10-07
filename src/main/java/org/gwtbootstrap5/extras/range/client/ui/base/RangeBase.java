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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gwtbootstrap5.client.ui.base.HasId;
import org.gwtbootstrap5.client.ui.base.HasResponsiveness;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.base.mixin.AttributeMixin;
import org.gwtbootstrap5.client.ui.constants.DeviceSize;
import org.gwtbootstrap5.extras.range.client.ui.base.constants.HandleType;
import org.gwtbootstrap5.extras.range.client.ui.base.constants.OrientationType;
import org.gwtbootstrap5.extras.range.client.ui.base.constants.ScaleType;
import org.gwtbootstrap5.extras.range.client.ui.base.constants.SelectionType;
import org.gwtbootstrap5.extras.range.client.ui.base.constants.TooltipPosition;
import org.gwtbootstrap5.extras.range.client.ui.base.constants.TooltipType;
import org.gwtbootstrap5.extras.range.client.ui.base.event.HasAllSlideHandlers;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideDisabledEvent;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideDisabledHandler;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideEnabledEvent;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideEnabledHandler;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideEvent;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideHandler;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideStartEvent;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideStartHandler;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideStopEvent;
import org.gwtbootstrap5.extras.range.client.ui.base.event.SlideStopHandler;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

import elemental2.core.Global;
import elemental2.core.JsArray;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

/**
 * Base class of the sliders, a text input turned into a
 * <a href="https://github.com/seiyria/bootstrap-slider">bootstrap-slider</a>. The options are
 * stored as {@code data-slider-*} attributes, so they can be set before the widget is attached;
 * setting one on an attached slider rebuilds it.
 *
 * @param <T> slider value type
 * @see <a href="https://github.com/seiyria/bootstrap-slider">...</a>
 * @author Xiaodong Sun
 */
public abstract class RangeBase<T> extends Widget implements
        HasValue<T>, IsEditor<LeafValueEditor<T>>, HasEnabled, HasId,
        HasResponsiveness, HasAllSlideHandlers<T> {

    private FormatterCallback<T> formatterCallback;
    private LeafValueEditor<T> editor;
    private BootstrapSlider slider;

    private final AttributeMixin<RangeBase<T>> attributeMixin = new AttributeMixin<>(this);

    /** Creates the slider on a new text input. */
    protected RangeBase() {
        setElement(Document.get().createTextInputElement());
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        final JsPropertyMap<Object> options = JsPropertyMap.of();
        if (formatterCallback != null) {
            options.set(RangeOption.FORMATTER.getName(), formatter());
        }
        slider = new BootstrapSlider(Js.uncheckedCast(getElement()), options);
        bindSliderEvents();
    }

    @Override
    protected void onUnload() {
        super.onUnload();
        slider.destroy();
        slider = null;
    }

    /**
     * Sets the id of the slider element when it's created.
     */
    @Override
    public void setId(final String id) {
        updateSlider(RangeOption.ID, id);
    }

    @Override
    public String getId() {
        return getStringAttribute(RangeOption.ID);
    }

    /**
     * Returns the minimum possible value ({@code data-slider-min}).
     *
     * @return the minimum, 0 by default
     */
    public double getMin() {
        return getDoubleAttribute(RangeOption.MIN, 0);
    }

    /**
     * Sets the minimum possible value.
     *
     * @param min the minimum, 0 by default
     */
    public void setMin(final double min) {
        updateSlider(RangeOption.MIN, min);
    }

    /**
     * Returns the maximum possible value ({@code data-slider-max}).
     *
     * @return the maximum, 10 by default
     */
    public double getMax() {
        return getDoubleAttribute(RangeOption.MAX, 10);
    }

    /**
     * Sets the maximum possible value.
     *
     * @param max the maximum, 10 by default
     */
    public void setMax(final double max) {
        updateSlider(RangeOption.MAX, max);
    }

    /**
     * Returns the increment step ({@code data-slider-step}).
     *
     * @return the step, 1 by default
     */
    public double getStep() {
        return getDoubleAttribute(RangeOption.STEP, 1);
    }

    /**
     * Sets the increment step.
     *
     * @param step the step, 1 by default
     */
    public void setStep(final double step) {
        updateSlider(RangeOption.STEP, step);
    }

    /**
     * Returns the number of digits shown after the decimal ({@code data-slider-precision}).
     *
     * @return the number of digits, or 0 if it is not set (the slider then uses the digits of the
     *   step)
     */
    public double getPrecision() {
        return getDoubleAttribute(RangeOption.PRECISION, 0);
    }

    /**
     * Sets the number of digits shown after the decimal.<br>
     * <br>
     * Defaults to the number of digits after the decimal of step value.
     *
     * @param precision the number of digits
     */
    public void setPrecision(final double precision) {
        updateSlider(RangeOption.PRECISION, precision);
    }

    /**
     * Returns the orientation ({@code data-slider-orientation}).
     *
     * @return the orientation, horizontal by default
     */
    public OrientationType getOrientation() {
        return getEnumAttribute(RangeOption.ORIENTATION, OrientationType.class, OrientationType.HORIZONTAL);
    }

    /**
     * Sets the orientation.
     *
     * @param orientation horizontal (the default) or vertical
     * @see OrientationType
     */
    public void setOrientation(final OrientationType orientation) {
        updateSlider(RangeOption.ORIENTATION, orientation.getType());
    }

    /**
     * Returns whether this is a range slider, with two handles ({@code data-slider-range}).
     *
     * @return {@code true} for a range slider
     */
    protected boolean isRange() {
        return getBooleanAttribute(RangeOption.RANGE, false);
    }

    /**
     * Make range slider if set to <code>true</code>. If initial value is scalar,
     * max will be used for second value.
     *
     * @param range {@code true} for a range slider
     */
    protected void setRange(final boolean range) {
        updateSlider(RangeOption.RANGE, range);
    }

    /**
     * Returns where the selection is drawn ({@code data-slider-selection}).
     *
     * @return the selection type, before the handle by default
     */
    public SelectionType getSelection() {
        return getEnumAttribute(RangeOption.SELECTION, SelectionType.class, SelectionType.BEFORE);
    }

    /**
     * Sets the selection type.
     *
     * @param selection where the selection is drawn, before the handle by default
     * @see SelectionType
     */
    public void setSelection(final SelectionType selection) {
        updateSlider(RangeOption.SELECTION, selection.getType());
    }

    /**
     * Returns when the tool-tip shows ({@code data-slider-tooltip}).
     *
     * @return the tool-tip type, {@code SHOW} by default
     */
    public TooltipType getTooltip() {
        return getEnumAttribute(RangeOption.TOOLTIP, TooltipType.class, TooltipType.SHOW);
    }

    /**
     * Sets the tool-tip type.
     *
     * @param tooltip when the tool-tip shows, on hover by default ({@code SHOW})
     * @see TooltipType
     */
    public void setTooltip(final TooltipType tooltip) {
        updateSlider(RangeOption.TOOLTIP, tooltip.getType());
    }

    /**
     * Returns whether each handle of a range slider has its own tool-tip
     * ({@code data-slider-tooltip-split}).
     *
     * @return {@code true} for one tool-tip per handle, {@code false} (the default) for one
     *   tool-tip
     */
    public boolean isTooltipSplit() {
        return getBooleanAttribute(RangeOption.TOOLTIP_SPLIT, false);
    }

    /**
     * Show one too-tip if set to <code>false</code>, otherwise
     * show two tool-tips one for each handler.
     *
     * @param tooltipSplit {@code true} for one tool-tip per handle
     */
    public void setTooltipSplit(final boolean tooltipSplit) {
        updateSlider(RangeOption.TOOLTIP_SPLIT, tooltipSplit);
    }

    /**
     * Returns the tool-tip position ({@code data-slider-tooltip-position}).
     *
     * @return the position; by default top for horizontal sliders and right for vertical ones
     */
    public TooltipPosition getTooltipPosition() {
        TooltipPosition defaultPosition = getOrientation() == OrientationType.HORIZONTAL ?
                TooltipPosition.TOP : TooltipPosition.RIGHT;
        return getEnumAttribute(RangeOption.TOOLTIP_POSITION, TooltipPosition.class, defaultPosition);
    }

    /**
     * Sets the tool-tip position.
     *
     * @param position the position, top or right by default
     * @see TooltipPosition
     */
    public void setTooltipPosition(final TooltipPosition position) {
        updateSlider(RangeOption.TOOLTIP_POSITION, position.getPosition());
    }

    /**
     * Returns the handle shape ({@code data-slider-handle}).
     *
     * @return the shape, round by default
     */
    public HandleType getHandle() {
        return getEnumAttribute(RangeOption.HANDLE, HandleType.class, HandleType.ROUND);
    }

    /**
     * Sets the handle shape.
     *
     * @param handle the shape, round by default
     * @see HandleType
     */
    public void setHandle(final HandleType handle) {
        updateSlider(RangeOption.HANDLE, handle.getType());
    }

    /**
     * Returns whether the slider is reversed ({@code data-slider-reversed}).
     *
     * @return {@code true} if it is reversed, {@code false} by default
     */
    public boolean isReversed() {
        return getBooleanAttribute(RangeOption.REVERSED, false);
    }

    /**
     * Sets whether or not the slider should be reversed.
     *
     * @param reversed {@code true} to reverse the slider
     */
    public void setReversed(final boolean reversed) {
        updateSlider(RangeOption.REVERSED, reversed);
    }

    @Override
    public boolean isEnabled() {
        if (slider != null) {
            return slider.isEnabled();
        }
        return getBooleanAttribute(RangeOption.ENABLED, true);
    }

    @Override
    public void setEnabled(final boolean enabled) {
        if (slider != null) {
            if (enabled) {
                slider.enable();
            } else {
                slider.disable();
            }
        } else {
            updateSlider(RangeOption.ENABLED, enabled);
        }
    }

    /**
     * Sets the formatter callback.
     *
     * @param formatterCallback the formatter of the tool-tip
     */
    public void setFormatter(final FormatterCallback<T> formatterCallback) {
        this.formatterCallback = formatterCallback;
        if (slider != null) {
            slider.setAttribute(RangeOption.FORMATTER.getName(), formatter());
            rebuild();
        }
    }

    /**
     * Applies changed options by recreating the slider. bootstrap-slider's refresh() keeps the
     * existing DOM, so options such as ticks added after creation would break its layout.
     * The current value is kept.
     */
    private void rebuild() {
        Object options = slider.options;
        Js.asPropertyMap(options).set(RangeOption.VALUE.getName(), slider.getValue());
        slider.destroy();
        slider = new BootstrapSlider(Js.uncheckedCast(getElement()), options);
        bindSliderEvents();
    }

    private BootstrapSlider.Formatter formatter() {
        return value -> formatTooltip(toValue(value));
    }

    /**
     * Returns the text of the tool-tip for a value: the {@link FormatterCallback}'s if one is set,
     * {@link #format(Object)}'s otherwise.
     *
     * @param value the value
     * @return the text of the tool-tip
     */
    protected String formatTooltip(final T value) {
        if (formatterCallback != null)
            return formatterCallback.formatTooltip(value);
        return format(value);
    }

    /**
     * Formats the slider value to string value to be displayed
     * as tool-tip text.
     *
     * @param value the value
     * @return the text of the tool-tip
     */
    protected abstract String format(final T value);

    /**
     * Returns whether the arrow keys follow the natural order
     * ({@code data-slider-natural-arrow-keys}).
     *
     * @return {@code true} for the natural order, {@code false} by default
     */
    public boolean isNaturalArrowKeys() {
        return getBooleanAttribute(RangeOption.NATURAL_ARROW_KEYS, false);
    }

    /**
     * The natural order is used for the arrow keys. Arrow up select the
     * upper slider value for vertical sliders, arrow right the righter
     * slider value for a horizontal slider ; no matter if the slider
     * was reversed or not.<br>
     * <br>
     * By default the arrow keys are oriented by arrow up/right to the
     * higher slider value, arrow down/left to the lower slider value.
     *
     * @param naturalArrowKeys {@code true} for the natural order
     */
    public void setNaturalArrowKeys(final boolean naturalArrowKeys) {
        updateSlider(RangeOption.NATURAL_ARROW_KEYS, naturalArrowKeys);
    }

    /**
     * Returns the values of the tick marks ({@code data-slider-ticks}).
     *
     * @return the values, empty by default
     */
    public List<Double> getTicks() {
        return getNumberArrayAttribute(RangeOption.TICKS, Collections.<Double>emptyList());
    }

    /**
     * Sets the values of ticks. Tick marks are indicators to denote
     * special values in the range.<br>
     * <br>
     * This option overwrites min and max options.
     *
     * @param ticks the values of the ticks
     */
    public void setTicks(final List<Double> ticks) {
        updateSliderForNumberArray(RangeOption.TICKS, ticks);
    }

    /**
     * Returns the positions of the tick marks, in percentages
     * ({@code data-slider-ticks-positions}).
     *
     * @return the positions, empty by default
     */
    public List<Double> getTicksPositions() {
        return getNumberArrayAttribute(RangeOption.TICKS_POSITIONS, Collections.<Double>emptyList());
    }

    /**
     * Defines the positions of the tick values in percentages.<br>
     * The first value should always be 0, the last value should always be 100 percent.
     *
     * @param ticksPositions the positions, in percent
     */
    public void setTicksPositions(final List<Double> ticksPositions) {
        updateSliderForNumberArray(RangeOption.TICKS_POSITIONS, ticksPositions);
    }

    /**
     * Returns the labels below the tick marks ({@code data-slider-ticks-labels}).
     *
     * @return the labels, empty by default
     */
    public List<String> getTicksLabels() {
        return getStringArrayAttribute(RangeOption.TICKS_LABELS, Collections.<String>emptyList());
    }

    /**
     * Sets the labels below the tick marks.<br>
     * <br>
     * Accepts HTML input.
     *
     * @param ticksLabels the labels, as HTML
     */
    public void setTicksLabels(final List<String> ticksLabels) {
        updateSliderForStringArray(RangeOption.TICKS_LABELS, ticksLabels);
    }

    /**
     * Returns the distance within which the handle snaps to a tick
     * ({@code data-slider-ticks-snap-bounds}).
     *
     * @return the distance, 0 by default
     */
    public double getTicksSnapBounds() {
        return getDoubleAttribute(RangeOption.TICKS_SNAP_BOUNDS, 0);
    }

    /**
     * Sets the snap bounds of a tick. Snaps to the tick if value
     * is within these bounds.
     *
     * @param ticksSnapBounds the distance from a tick within which the handle snaps to it
     */
    public void setTicksSnapBounds(final double ticksSnapBounds) {
        updateSlider(RangeOption.TICKS_SNAP_BOUNDS, ticksSnapBounds);
    }

    /**
     * Returns the scale ({@code data-slider-scale}).
     *
     * @return the scale, linear by default
     */
    public ScaleType getScale() {
        return getEnumAttribute(RangeOption.SCALE, ScaleType.class, ScaleType.LINEAR);
    }

    /**
     * Focus the appropriate slider handle after a value change.
     * Defaults to false.
     *
     * @param focus {@code true} to focus the handle
     */
    public void setFocusHandle(final boolean focus) {
        updateSlider(RangeOption.FOCUS, focus);
    }

    /**
     * Returns whether the handle gets the focus after a value change ({@code data-slider-focus}).
     *
     * @return {@code true} if it does, {@code false} by default
     */
    public boolean getFocusHandle() {
        return getBooleanAttribute(RangeOption.FOCUS, false);
    }

    /**
     * Sets the slider scale type.
     *
     * @param scale linear (the default) or logarithmic
     * @see ScaleType
     */
    public void setScale(final ScaleType scale) {
        updateSlider(RangeOption.SCALE, scale.getType());
    }

    @Override
    public void setVisible(final boolean visible) {
        if (slider != null) {
            setVisible(getSliderElement(), visible);
        } else {
            super.setVisible(visible);
        }
    }

    @Override
    public boolean isVisible() {
        if (slider != null) {
            return isVisible(getSliderElement());
        }
        return super.isVisible();
    }

    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

    @Override
    public void setValue(final T value) {
        setValue(value, false);
    }

    @Override
    public void setValue(final T value, final boolean fireEvents) {

        T oldValue = fireEvents ? getValue() : null;

        if (slider != null) {
            slider.setValue(toJsValue(value));
        } else {
            String attrVal = (value == null) ? null : value.toString();
            attributeMixin.setAttribute(RangeOption.VALUE.getDataAttribute(), attrVal);
        }

        if (fireEvents) {
            T newValue = getValue();
            ValueChangeEvent.fireIfNotEqual(this, oldValue, newValue);
        }
    }

    /**
     * Converts a value to the form bootstrap-slider expects: a number,
     * or a two-number array for range sliders.
     *
     * @param value the slider value
     * @return the JS value
     */
    protected abstract Object toJsValue(T value);

    @Override
    public T getValue() {
        if (slider != null) {
            return toValue(slider.getValue());
        }
        String attrVal = attributeMixin.getAttribute(RangeOption.VALUE.getDataAttribute());
        return convertValue(attrVal);
    }

    /**
     * Converts a value received from bootstrap-slider (a number, or a two-number
     * array for range sliders) to the slider value.
     *
     * @param value the JS value, may be <code>null</code>
     * @return the slider value, or <code>null</code>
     */
    protected abstract T toValue(Object value);

    /**
     * Converts the value of the {@link RangeOption#VALUE} attribute to the
     * slider value.
     *
     * @param value the value of the attribute
     * @return the slider value
     */
    protected abstract T convertValue(String value);

    @SuppressWarnings("deprecation")
    @Override
    public com.google.gwt.user.client.Element getStyleElement() {
        if (slider != null) {
            return (com.google.gwt.user.client.Element) getSliderElement();
        }
        return super.getStyleElement();
    }

    /**
     * Toggles the slider between enabled and disabled.
     */
    public void toggle() {
        if (slider != null) {
            slider.toggle();
        } else {
            setEnabled(!isEnabled());
        }
    }

    /**
     * Refreshes the current slider. This method does nothing if the slider has
     * not been initialized.
     */
    public void refresh() {
        if (slider != null) {
            slider.refresh();
            // refresh() rebuilds the slider and drops its event callbacks
            bindSliderEvents();
        }
    }

    /**
     * Renders the tool-tip again, after initialization. Useful in situations
     * when the slider and tool-tip are initially hidden.
     */
    public void relayout() {
        if (slider != null) {
            slider.relayout();
        }
    }

    @Override
    public LeafValueEditor<T> asEditor() {
        if (editor == null) {
            editor = TakesValueEditor.of(this);
        }
        return editor;
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public HandlerRegistration addSlideHandler(final SlideHandler<T> handler) {
        return addHandler(handler, SlideEvent.getType());
    }

    @Override
    public HandlerRegistration addSlideStartHandler(final SlideStartHandler<T> handler) {
        return addHandler(handler, SlideStartEvent.getType());
    }

    @Override
    public HandlerRegistration addSlideStopHandler(final SlideStopHandler<T> handler) {
        return addHandler(handler, SlideStopEvent.getType());
    }

    @Override
    public HandlerRegistration addSlideEnabledHandler(final SlideEnabledHandler handler) {
        return addHandler(handler, SlideEnabledEvent.getType());
    }

    @Override
    public HandlerRegistration addSlideDisabledHandler(final SlideDisabledHandler handler) {
        return addHandler(handler, SlideDisabledEvent.getType());
    }

    private void updateSlider(RangeOption option, String value) {
        if (slider != null) {
            slider.setAttribute(option.getName(), value);
            rebuild();
        } else {
            attributeMixin.setAttribute(option.getDataAttribute(), value);
        }
    }

    private void updateSlider(RangeOption option, boolean value) {
        if (slider != null) {
            slider.setAttribute(option.getName(), value);
            rebuild();
        } else {
            attributeMixin.setAttribute(option.getDataAttribute(), Boolean.toString(value));
        }
    }

    private void updateSlider(RangeOption option, double value) {
        if (slider != null) {
            slider.setAttribute(option.getName(), value);
            rebuild();
        } else {
            attributeMixin.setAttribute(option.getDataAttribute(), Double.toString(value));
        }
    }

    private void updateSliderForNumberArray(RangeOption option, List<Double> value) {
        JsArray<Double> array = new JsArray<>();
        for (Double val : value) {
            array.push(val);
        }
        if (slider != null) {
            slider.setAttribute(option.getName(), array);
            rebuild();
        } else {
            String arrayStr = Global.JSON.stringify(array);
            attributeMixin.setAttribute(option.getDataAttribute(), arrayStr);
        }
    }

    private void updateSliderForStringArray(RangeOption option, List<String> value) {
        JsArray<String> array = new JsArray<>();
        for (String val : value) {
            array.push(val);
        }
        if (slider != null) {
            slider.setAttribute(option.getName(), array);
            rebuild();
        } else {
            String arrayStr = Global.JSON.stringify(array);
            attributeMixin.setAttribute(option.getDataAttribute(), arrayStr);
        }
    }

    private String getStringAttribute(RangeOption option) {
        if (slider != null) {
            Object value = slider.getAttribute(option.getName());
            return value == null ? null : String.valueOf(value);
        }
        return attributeMixin.getAttribute(option.getDataAttribute());
    }

    private boolean getBooleanAttribute(RangeOption option, boolean defaultValue) {
        if (slider != null) {
            return Js.isTruthy(slider.getAttribute(option.getName()));
        }
        String value = attributeMixin.getAttribute(option.getDataAttribute());
        if (value != null && !value.isEmpty()) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    private double getDoubleAttribute(RangeOption option, double defaultValue) {
        if (slider != null) {
            Object value = slider.getAttribute(option.getName());
            return value == null ? defaultValue : Js.asDouble(value);
        }
        String value = attributeMixin.getAttribute(option.getDataAttribute());
        if (value != null && !value.isEmpty()) {
            return Double.parseDouble(value);
        }
        return defaultValue;
    }

    private <E extends Enum<E>> E getEnumAttribute(RangeOption option, Class<E> clazz, E defaultValue) {
        String value = getStringAttribute(option);
        try {
            return Enum.valueOf(clazz, value.toUpperCase());
        } catch (Throwable e) {
            return defaultValue;
        }
    }

    private List<Double> getNumberArrayAttribute(RangeOption option, List<Double> defaultValue) {

        // Get array attribute
        JsArray<Double> array = null;
        if (slider != null) {
            array = Js.uncheckedCast(slider.getAttribute(option.getName()));
        } else {
            String value = attributeMixin.getAttribute(option.getDataAttribute());
            if (value != null && !value.isEmpty()) {
                array = Js.uncheckedCast(Global.JSON.parse(value));
            }
        }

        // Attribute not set
        if (array == null) {
            return defaultValue;
        }

        // Put array to list
        List<Double> list = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            list.add(array.getAt(i));
        }
        return list;
    }

    private List<String> getStringArrayAttribute(RangeOption option, List<String> defaultValue) {
        // Get array attribute
        JsArray<String> array = null;
        if (slider != null) {
            array = Js.uncheckedCast(slider.getAttribute(option.getName()));
        } else {
            String value = attributeMixin.getAttribute(option.getDataAttribute());
            if (value != null && !value.isEmpty()) {
                array = Js.uncheckedCast(Global.JSON.parse(value));
            }
        }

        // Attribute not set
        if (array == null) {
            return defaultValue;
        }

        // Put array to list
        List<String> list = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            list.add(array.getAt(i));
        }
        return list;
    }

    /**
     * Fires a {@link SlideEvent} event.
     *
     * @param value the new slide value
     */
    protected void fireSlideEvent(final T value) {
        SlideEvent.fire(this, value);
    }

    /**
     * Fires a {@link SlideStartEvent} event.
     *
     * @param value the new slide value
     */
    protected void fireSlideStartEvent(final T value) {
        SlideStartEvent.fire(this, value);
    }

    /**
     * Fires a {@link SlideStopEvent} event.
     *
     * @param value the new slide value
     */
    protected void fireSlideStopEvent(final T value) {
        SlideStopEvent.fire(this, value);
    }

    /**
     * Fires a {@link ValueChangeEvent} event.
     *
     * @param value the new slide value
     */
    protected void fireChangeEvent(final T value) {
        ValueChangeEvent.fire(this, value);
    }

    private void bindSliderEvents() {
        slider.on(SLIDE_EVENT, value -> fireSlideEvent(toValue(value)));
        slider.on(SLIDE_START_EVENT, value -> fireSlideStartEvent(toValue(value)));
        slider.on(SLIDE_STOP_EVENT, value -> fireSlideStopEvent(toValue(value)));
        slider.on(SLIDE_CHANGE_EVENT, value -> fireChangeEvent(toValue(Js.asPropertyMap(value).get("newValue"))));
        slider.on(SLIDE_ENABLED_EVENT, value -> SlideEnabledEvent.fire(this));
        slider.on(SLIDE_DISABLED_EVENT, value -> SlideDisabledEvent.fire(this));
    }

    private Element getSliderElement() {
        return Js.uncheckedCast(slider.getElement());
    }
}
