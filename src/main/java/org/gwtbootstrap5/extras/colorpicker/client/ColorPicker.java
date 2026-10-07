package org.gwtbootstrap5.extras.colorpicker.client;

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

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap5.client.ui.html.Div;
import org.gwtbootstrap5.extras.shared.js.JQuery;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

/**
 * An inline color picker, by
 * <a href="https://itsjavi.com/bootstrap-colorpicker/">bootstrap-colorpicker</a> 3. Its value is the color in hex, such as {@code #ff0000}. Inherit
 * {@code org.gwtbootstrap5.extras.colorpicker.ColorPicker} (or {@code ColorPickerURL}) to load
 * the library.
 *
 * <pre>{@code
 * <c:ColorPicker ui:field="picker"/>
 * }</pre>
 */
public class ColorPicker extends Widget implements HasValue<String>, HasChangeHandlers {

    private final Div colorPickerDiv;

    private boolean valueChangeHandlerInitialized = false;

    /** Creates an inline color picker, with no color selected. */
    public ColorPicker() {
        colorPickerDiv = new Div();

        setElement((Element) colorPickerDiv.getElement());
        initColorPicker(colorPickerDiv.getElement());
    }

    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return addDomHandler(handler, ChangeEvent.getType());
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        // Initialization code
        if (!valueChangeHandlerInitialized) {
            valueChangeHandlerInitialized = true;
            addChangeHandler(event -> ValueChangeEvent.fire(ColorPicker.this, getValue()));
        }
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public String getValue() {
        return getValueColorPicker(colorPickerDiv.getElement());
    }

    @Override
    public void setValue(String value) {
        setValueColorPicker(colorPickerDiv.getElement(), value);
    }

    @Override
    public void setValue(String value, boolean fireEvent) {
        setValue(value);

        if (fireEvent) {
            fireChangeEvent(value);
        }
    }

    private void fireChangeEvent(String value) {
        ValueChangeEvent.fire(ColorPicker.this, value);
    }

    private void initColorPicker(Element e) {
        final JsPropertyMap<Object> options = JsPropertyMap.of();
        options.set("customClass", "colorpicker-responsive");
        options.set("format", "hex");
        options.set("container", true);
        options.set("inline", true);
        options.set("sliders", JsPropertyMap.of(
                "saturation", JsPropertyMap.of("maxLeft", 300, "maxTop", 300),
                "hue", JsPropertyMap.of("maxTop", 300),
                "alpha", JsPropertyMap.of("maxTop", 300)));

        JQuery.jQuery(e).colorpicker(options).on("colorpickerChange", (event, args) -> {
            // bootstrap-colorpicker adds the selected color to the jQuery event
            final ColorpickerColor color = Js.uncheckedCast(Js.asPropertyMap(event).get("color"));
            fireChangeEvent(color.toHexString());
        });
    }

    private void setValueColorPicker(Element e, String value) {
        JQuery.jQuery(e).colorpickerCommand("setValue", value);
    }

    private String getValueColorPicker(Element e) {
        return JQuery.jQuery(e).colorpickerCommand("getValue");
    }

    /**
     * The color object bootstrap-colorpicker passes with its events.
     */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    private static class ColorpickerColor {
        native String toHexString();
    }

}
