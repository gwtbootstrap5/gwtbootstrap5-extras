package org.gwtbootstrap5.extras.datetimepicker.client.ui.engines.tempusdominus;

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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import elemental2.dom.Element;
import jsinterop.base.Js;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.DateTimePickerOptions;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerEngine;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerHandlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TempusDominusEngine implements IDateTimePickerEngine {

    private TempusDominus instance;
    private TempusDominusOptions options;

    public void init(com.google.gwt.dom.client.Element element, DateTimePickerOptions options, IDateTimePickerHandlers handlers) {
        Element input = Js.cast(element);

        // Translate from DateTimePickerOptions to TempusDominusOptions
        this.options = translateOptions(options);

        // Initialize
        instance = new TempusDominus(input, this.options);

        // Append events to input
        appendEvents(input, handlers);
    }

    @Override
    public void updateProperties(DateTimePickerOptions options) {
        if (instance != null) {
            this.options = translateOptions(options);
            instance.updateOptions(this.options);
        }
    }

    @Override
    public void destroy() {
        if (instance != null) {
            instance.dispose();
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
        if (instance != null) {
            instance.hide();
        }
    }

    @Override
    public void toggle() {
        if (instance != null) {
            instance.toggle();
        }
    }

    @Override
    public void clear(boolean silent) {
        if (instance != null) {
            instance.dates.clear();
        }
    }

    @Override
    public void setViewDate(Date date) {
        if (instance != null) {
            instance.viewDate = toTempusDominusDateTime(date);
        }
    }

    @Override
    public void setDate(Date date, boolean silent) {
        if (instance != null) {
            instance.dates.setValue(toTempusDominusDateTime(date));
        }
    }

    @Override
    public Date getDate() {
        if (instance != null && instance.dates.picked().length > 0) {
            return toJavaDate(instance.dates.picked()[0]);
        }

        return null;
    }

    @Override
    public void setMultipleDates(List<Date> dates, boolean silent) {
        if (instance != null) {
            for (Date date : dates) {
                instance.dates.add(toTempusDominusDateTime(date));
            }
        }
    }

    @Override
    public List<Date> getMultipleDates() {
        if (instance != null) {
            List<Date> dates = new ArrayList<>();
            for (TempusDominusDateTime tddt : instance.dates.picked()) {
                dates.add(toJavaDate(tddt));
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

    private TempusDominusOptions translateOptions(DateTimePickerOptions options) {
        TempusDominusOptions tempusDominusOptions = new TempusDominusOptions();
        tempusDominusOptions.display.keepOpen = options.isKeepOpen();
        tempusDominusOptions.restrictions.minDate = options.getMinDate();
        tempusDominusOptions.restrictions.maxDate = options.getMaxDate();
        tempusDominusOptions.stepping = options.getMinuteStep();
        tempusDominusOptions.display.buttons.today = options.isShowTodayButton();
        tempusDominusOptions.display.buttons.clear = options.isShowClearButton();
        if (options.isOnlyCalendar()) {
            tempusDominusOptions.display.components.calendar = true;
            tempusDominusOptions.display.components.clock = false;
        }
        if (options.isOnlyTime()) {
            tempusDominusOptions.display.components.calendar = false;
            tempusDominusOptions.display.components.clock = true;
        }

        loadLocale(TempusDominusLocales.getLocaleAndLoadItIfNotLoaded(options.getLocale()));

        // Wait for it to init
        Scheduler.get().scheduleDeferred(() -> {
            if (instance != null) {
                instance.setLocale(options.getLocale());

                tempusDominusOptions.localization.format = options.getDateTimeFormat();
            }
        });

        return tempusDominusOptions;
    }

    private void appendEvents(Element input, IDateTimePickerHandlers handlers) {
        // Strongly typed event listener for the 'change.td' event
        input.addEventListener(TempusDominusEvents.CHANGE, evt -> {
            if (instance != null) {
                handlers.onChangeValue(getMultipleDates());
            }
        });
        input.addEventListener(TempusDominusEvents.SHOW, evt -> {
            if (instance != null) {
                handlers.onShow();
            }
        });
        input.addEventListener(TempusDominusEvents.HIDE, evt -> {
            if (instance != null) {
                handlers.onHide();
            }
        });
    }

    private java.util.Date toJavaDate(TempusDominusDateTime tdDateTime) {
        if (tdDateTime == null) {
            return null;
        }

        // 1. java.util.Date expects the year as (Actual Year - 1900)
        // 2. JS/TempusDominus month is 0-indexed (0=Jan, 11=Dec),
        //    which perfectly matches java.util.Date's 0-indexed month!
        return new java.util.Date(
                tdDateTime.year - 1900,
                tdDateTime.month,
                tdDateTime.date,
                tdDateTime.hours,
                tdDateTime.minutes,
                tdDateTime.seconds
        );
    }

    private TempusDominusDateTime toTempusDominusDateTime(java.util.Date javaDate) {
        if (javaDate == null) {
            return null;
        }

        // javaDate.getTime() returns a long (epoch milliseconds)
        // We cast to double for safe JSInterop translation
        return new TempusDominusDateTime(javaDate.getTime());
    }

    private native void loadLocale(JavaScriptObject locale) /*-{
        $wnd.tempusDominus.loadLocale(locale);
        $wnd.tempusDominus.locale(locale.name);
    }-*/;

}
