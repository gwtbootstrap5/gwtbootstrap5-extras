package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.airdatepicker;

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

import com.google.gwt.i18n.client.DateTimeFormat;
import elemental2.core.JsArray;
import elemental2.core.JsDate;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLInputElement;
import jsinterop.base.Js;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.DateTimePickerOptions;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerEngine;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerHandlers;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AirDatepickerEngine implements IDateTimePickerEngine {

    private AirDatepicker instance;
    private AirDatepickerOptions options;

    private double typingTimerId = 0;

    @Override
    public void init(com.google.gwt.dom.client.Element element, DateTimePickerOptions options, IDateTimePickerHandlers handlers) {
        Element input = Js.cast(element);

        // Inicializamos las opciones
        this.options = translateOptions(options);

        // Añadimos los eventos a las opciones
        appendEvents(handlers);

        // Añadimos evento de keyup como hack para que se refresque en vivo
        if (options.isFocusDateOnWrite() || options.isSelectDateOnWrite()) {
            input.addEventListener("keyup", event -> {
                DomGlobal.clearTimeout(typingTimerId);

                typingTimerId = DomGlobal.setTimeout(p0 -> manageTypingEvent(options, ((HTMLInputElement) input).value), options.getTypingDelay());
            });
            input.addEventListener("blur", event -> {
                if (typingTimerId != 0) {
                    DomGlobal.clearTimeout(typingTimerId);

                    manageTypingEvent(options, ((HTMLInputElement) input).value);
                }
            });
        }

        // Inicializamos el datepicker nativo
        this.instance = new AirDatepicker(input, this.options);
    }

    @Override
    public void updateProperties(DateTimePickerOptions options) {
        if (instance != null) {
            this.options = translateOptions(options);
            instance.update(this.options);
        }
    }

    @Override
    public void destroy() {
        if (instance != null) {
            instance.destroy();
        }
    }

    // --- Métodos de la API expuestos en Java puro ---

    @Override
    public void show() {
        if (instance != null) {
            instance.show();
        }
    }

    @Override
    public void hide() {
        // Air Datepicker throws when hiding a picker that is already hidden (e.g. after autoClose)
        if (instance != null && instance.visible) {
            instance.hide();
        }
    }

    @Override
    public void toggle() {
        if (instance == null) {
            return;
        }
        if (instance.visible) {
            instance.hide();
        } else {
            instance.show();
        }
    }

    @Override
    public void clear(boolean silent) {
        if (instance != null) {
            AirDatepicker.ClearDateOptions opt = new AirDatepicker.ClearDateOptions();
            opt.silent = silent;
            instance.clear(opt);
        }
    }

    @Override
    public void setViewDate(Date date) {
        if (instance != null) {
            JsDate jsDate = toJsDate(date);
            instance.setViewDate(jsDate);
            instance.setFocusDate(jsDate);
        }
    }

    @Override
    public void setDate(Date date, boolean silent) {
        if (instance != null && date != null) {
            AirDatepicker.SelectDateOptions opt = new AirDatepicker.SelectDateOptions();
            opt.silent = silent;
            instance.selectDate(toJsDate(date), opt);
        }
    }

    @Override
    public Date getDate() {
        if (instance != null) {
            return instance.selectedDates.length > 0 ? toJavaDate((JsDate) instance.selectedDates[0]) : null;
        }

        return null;
    }

    @Override
    public void setMultipleDates(List<Date> dates, boolean silent) {
        if (instance != null && !dates.isEmpty()) {
            for (Date date : dates) {
                AirDatepicker.SelectDateOptions opt = new AirDatepicker.SelectDateOptions();
                opt.silent = silent;
                instance.selectDate(toJsDate(date), opt);
            }
        }
    }

    @Override
    public List<Date> getMultipleDates() {
        if (instance != null) {
            List<Date> dates = new ArrayList<>();
            for (Object jsDate : instance.selectedDates) {
                dates.add(toJavaDate((JsDate) jsDate));
            }

            return dates;
        }

        return List.of();
    }

    @Override
    public boolean isStarted() {
        return instance != null;
    }

    // --- Métodos privados ---

    private AirDatepickerOptions translateOptions(DateTimePickerOptions options) {
        AirDatepickerOptions airDatepickerOptions = new AirDatepickerOptions();
        airDatepickerOptions.autoClose = !options.isKeepOpen();
        // Pass JS dates: a java.util.Date only works where Date.parse accepts GWT's toString() format
        airDatepickerOptions.minDate = options.getMinDate() != null ? toJsDate(options.getMinDate()) : "";
        airDatepickerOptions.maxDate = options.getMaxDate() != null ? toJsDate(options.getMaxDate()) : "";
        // 0 means "not set"; keep Air Datepicker's default step of 1
        if (options.getHourStep() > 0) {
            airDatepickerOptions.hoursStep = options.getHourStep();
        }
        if (options.getMinuteStep() > 0) {
            airDatepickerOptions.minutesStep = options.getMinuteStep();
        }

        List<String> buttons = new ArrayList<>();
        if (options.isShowClearButton()) {
            buttons.add("clear");
        }
        if (options.isShowTodayButton()) {
            buttons.add("today");
        }
        airDatepickerOptions.buttons = buttons.toArray(new String[0]);

        airDatepickerOptions.dateFormat = options.getDateFormat();
        airDatepickerOptions.timeFormat = options.getTimeFormat();

        airDatepickerOptions.locale = AirDatepickerLocales.getLocaleAndLoadItIfNotLoaded(options.getLocale());

        airDatepickerOptions.onlyTimepicker = options.isOnlyTime();
        airDatepickerOptions.timepicker = !options.isOnlyCalendar();

        return airDatepickerOptions;
    }

    private void appendEvents(IDateTimePickerHandlers handlers) {
        // Interceptamos el callback nativo para convertir los tipos de JS a Java
        if (handlers != null) {
            options.onSelect = props -> {
                List<Date> javaDates = new ArrayList<>();

                if (props.date != null) {
                    // Comprobamos si JS devolvió un Array (múltiples fechas) o un solo Date
                    if (JsArray.isArray(props.date)) {
                        JsArray<JsDate> jsDates = Js.cast(props.date);

                        for (int i = 0; i < jsDates.length; i++) {
                            javaDates.add(toJavaDate(jsDates.getAt(i)));
                        }
                    } else {
                        // Es una sola fecha
                        JsDate jsDate = Js.cast(props.date);
                        javaDates.add(toJavaDate(jsDate));
                    }
                }

                // Ejecutamos el handler de Java
                handlers.onChangeValue(javaDates);
            };
            options.onShow = props -> handlers.onShow();
            options.onHide = props -> handlers.onHide();
        }
    }

    private JsDate toJsDate(Date javaDate) {
        if (javaDate == null) return null;
        // JsDate requiere los milisegundos en formato double
        return new JsDate((double) javaDate.getTime());
    }

    private Date toJavaDate(JsDate jsDate) {
        if (jsDate == null) return null;
        // Convertimos los milisegundos de JS de vuelta a long para java.util.Date
        return new Date((long) jsDate.getTime());
    }

    private void manageTypingEvent(DateTimePickerOptions options, String value) {
        typingTimerId = 0;

        if (value == null || value.isBlank()) {
            clear(false);
        } else {
            Date inputDate = getDateFromInput(options, value);
            if (inputDate != null) {
                if (options.isFocusDateOnWrite()) {
                    setViewDate(inputDate);
                }
                if (options.isSelectDateOnWrite()) {
                    setDate(inputDate, false);
                }
            }
        }
    }

    private static @Nullable Date getDateFromInput(DateTimePickerOptions options, String value) {
        Date formattedDate;
        try {
            formattedDate = DateTimeFormat.getFormat(options.getDateTimeFormat()).parse(value);
        } catch (IllegalArgumentException e) {
            formattedDate = null;
        }
        return formattedDate;
    }

}
