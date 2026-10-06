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

import elemental2.core.JsObject;
import elemental2.dom.Element;
import elemental2.dom.EventListener;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.DateTimePickerOptions;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerEngine;
import org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine.IDateTimePickerHandlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TempusDominusEngine implements IDateTimePickerEngine {

    private TempusDominus instance;
    private TempusDominusOptions options;

    // Kept to remove them in destroy(), so a new init doesn't add them twice
    private Element input;
    private EventListener changeListener;
    private EventListener showListener;
    private EventListener hideListener;

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
        if (input != null) {
            input.removeEventListener(TempusDominusEvents.CHANGE, changeListener);
            input.removeEventListener(TempusDominusEvents.SHOW, showListener);
            input.removeEventListener(TempusDominusEvents.HIDE, hideListener);
            input = null;
        }
        if (instance != null) {
            instance.dispose();
            instance = null;
        }
    }

    // --- API methods exposed to Java ---

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

    // --- Private methods ---

    /**
     * Builds the Tempus Dominus options. A native options object starts empty, so every nested
     * object is created here, and only options that have a value are set; Tempus Dominus keeps
     * its defaults for the rest.
     */
    private TempusDominusOptions translateOptions(DateTimePickerOptions options) {
        TempusDominusOptions.ButtonsOptions buttons = new TempusDominusOptions.ButtonsOptions();
        buttons.today = options.isShowTodayButton();
        buttons.clear = options.isShowClearButton();

        TempusDominusOptions.DisplayOptions display = new TempusDominusOptions.DisplayOptions();
        display.keepOpen = options.isKeepOpen();
        display.buttons = buttons;
        if (options.isOnlyCalendar() || options.isOnlyTime()) {
            TempusDominusOptions.ComponentsOptions components = new TempusDominusOptions.ComponentsOptions();
            components.calendar = !options.isOnlyTime();
            components.clock = !options.isOnlyCalendar();
            display.components = components;
        }

        TempusDominusOptions.RestrictionsOptions restrictions = new TempusDominusOptions.RestrictionsOptions();
        if (options.getMinDate() != null) {
            restrictions.minDate = toTempusDominusDateTime(options.getMinDate());
        }
        if (options.getMaxDate() != null) {
            restrictions.maxDate = toTempusDominusDateTime(options.getMaxDate());
        }

        TempusDominusOptions.LocalizationOptions localization = localization(options.getLocale());
        localization.format = options.getDateTimeFormat();

        TempusDominusOptions tempusDominusOptions = new TempusDominusOptions();
        tempusDominusOptions.display = display;
        tempusDominusOptions.restrictions = restrictions;
        tempusDominusOptions.localization = localization;
        if (options.getMinuteStep() > 0) {
            tempusDominusOptions.stepping = options.getMinuteStep();
        }
        return tempusDominusOptions;
    }

    /**
     * Returns a copy of the full localization for the given locale, so each picker gets its own
     * language instead of whatever the global Tempus Dominus default is at creation time.
     */
    private static TempusDominusOptions.LocalizationOptions localization(String locale) {
        Object loaded = TempusDominusLocales.getLocaleAndLoadItIfNotLoaded(locale);
        if (loaded != null) {
            return Js.uncheckedCast(JsObject.assign(JsPropertyMap.of(), Js.asPropertyMap(loaded).get("localization")));
        }
        // English is built in; its "default" locale would format month names in the browser's language
        TempusDominusOptions.LocalizationOptions english =
                Js.uncheckedCast(JsObject.assign(JsPropertyMap.of(), TempusDominusGlobal.DefaultEnLocalization));
        english.locale = locale;
        return english;
    }

    private void appendEvents(Element input, IDateTimePickerHandlers handlers) {
        this.input = input;
        // Strongly typed event listener for the 'change.td' event
        changeListener = evt -> {
            if (instance != null) {
                handlers.onChangeValue(getMultipleDates());
            }
        };
        showListener = evt -> {
            if (instance != null) {
                handlers.onShow();
            }
        };
        hideListener = evt -> {
            if (instance != null) {
                handlers.onHide();
            }
        };
        input.addEventListener(TempusDominusEvents.CHANGE, changeListener);
        input.addEventListener(TempusDominusEvents.SHOW, showListener);
        input.addEventListener(TempusDominusEvents.HIDE, hideListener);
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

}
