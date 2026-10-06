package org.gwtbootstrap5.extras.datetimepicker.client.ui.base.engine;

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

import java.util.Date;

/** Options of a date and time picker, which each engine translates into its own. */
public class DateTimePickerOptions {
    /** Creates the default options. */
    public DateTimePickerOptions() {
    }

    private boolean keepOpen = false;
    private String locale = "en";
    private Date minDate = null;
    private Date maxDate = null;
    private int hourStep = 0;
    private int minuteStep = 0;
    private boolean showTodayButton = false;
    private boolean showClearButton = false;
    private String dateFormat = "dd/MM/yyyy";
    private String dateTimeConcatenator = " ";
    private String timeFormat = "HH:mm";
    private boolean onlyCalendar = false;
    private boolean onlyTime = false;
    private boolean focusDateOnWrite = true;
    private boolean selectDateOnWrite = false;
    private int typingDelay = 1000; // In ms

    /**
     * Returns whether the picker stays open after a date is picked.
     *
     * @return {@code true} if it stays open
     */
    public boolean isKeepOpen() {
        return keepOpen;
    }

    /**
     * Sets whether the picker stays open after a date is picked.
     *
     * @param keepOpen {@code true} to keep it open
     */
    public void setKeepOpen(boolean keepOpen) {
        this.keepOpen = keepOpen;
    }

    /**
     * Returns the language of the picker.
     *
     * @return the locale, such as {@code "es"}
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the language of the picker; the engine loads its translation.
     *
     * @param locale the locale, such as {@code "es"}; {@code "en"} by default
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Returns the earliest date that can be picked.
     *
     * @return the date, or {@code null} if there is no limit
     */
    public Date getMinDate() {
        return minDate;
    }

    /**
     * Sets the earliest date that can be picked.
     *
     * @param minDate the date, or {@code null} for no limit
     */
    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    /**
     * Returns the latest date that can be picked.
     *
     * @return the date, or {@code null} if there is no limit
     */
    public Date getMaxDate() {
        return maxDate;
    }

    /**
     * Sets the latest date that can be picked.
     *
     * @param maxDate the date, or {@code null} for no limit
     */
    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    /**
     * Returns the step of the hours in the clock.
     *
     * @return the step, or 0 for the engine's default
     */
    public int getHourStep() {
        return hourStep;
    }

    /**
     * Sets the step of the hours in the clock.
     *
     * @param hourStep the step, or 0 for the engine's default
     */
    public void setHourStep(int hourStep) {
        this.hourStep = hourStep;
    }

    /**
     * Returns the step of the minutes in the clock.
     *
     * @return the step, or 0 for the engine's default
     */
    public int getMinuteStep() {
        return minuteStep;
    }

    /**
     * Sets the step of the minutes in the clock.
     *
     * @param minuteStep the step, or 0 for the engine's default
     */
    public void setMinuteStep(int minuteStep) {
        this.minuteStep = minuteStep;
    }

    /**
     * Returns whether the picker shows a button that picks today.
     *
     * @return {@code true} if it shows one
     */
    public boolean isShowTodayButton() {
        return showTodayButton;
    }

    /**
     * Sets whether the picker shows a button that picks today.
     *
     * @param showTodayButton {@code true} to show it
     */
    public void setShowTodayButton(boolean showTodayButton) {
        this.showTodayButton = showTodayButton;
    }

    /**
     * Returns whether the picker shows a button that clears the date.
     *
     * @return {@code true} if it shows one
     */
    public boolean isShowClearButton() {
        return showClearButton;
    }

    /**
     * Sets whether the picker shows a button that clears the date.
     *
     * @param showClearButton {@code true} to show it
     */
    public void setShowClearButton(boolean showClearButton) {
        this.showClearButton = showClearButton;
    }

    /**
     * Returns the format of the date.
     *
     * @return the format, {@code "dd/MM/yyyy"} by default
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * Sets the format of the date, in the tokens of the engine.
     *
     * @param dateFormat the format, such as {@code "dd/MM/yyyy"}
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Returns what goes between the date and the time.
     *
     * @return the separator, a space by default
     */
    public String getDateTimeConcatenator() {
        return dateTimeConcatenator;
    }

    /**
     * Sets what goes between the date and the time.
     *
     * @param dateTimeConcatenator the separator
     */
    public void setDateTimeConcatenator(String dateTimeConcatenator) {
        this.dateTimeConcatenator = dateTimeConcatenator;
    }

    /**
     * Returns the format of the time.
     *
     * @return the format, {@code "HH:mm"} by default
     */
    public String getTimeFormat() {
        return timeFormat;
    }

    /**
     * Sets the format of the time, in the tokens of the engine.
     *
     * @param timeFormat the format, such as {@code "HH:mm"}
     */
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    /**
     * Returns the format of the whole value: the date format, the separator and the time format,
     * leaving out the date or the time when the picker shows only the other.
     *
     * @return the format
     */
    public String getDateTimeFormat() {
        String fullFormat = "";
        if (!onlyTime && dateFormat != null) {
            fullFormat += dateFormat;
        }

        if ((!onlyTime && dateFormat != null) && (!onlyCalendar && timeFormat != null)) {
            fullFormat += dateTimeConcatenator;
        }

        if (!onlyCalendar && timeFormat != null) {
            fullFormat += timeFormat;
        }

        return fullFormat;
    }

    /**
     * Returns whether the picker shows only the calendar.
     *
     * @return {@code true} for a date picker
     */
    public boolean isOnlyCalendar() {
        return onlyCalendar;
    }

    /**
     * Sets whether the picker shows only the calendar.
     *
     * @param onlyCalendar {@code true} for a date picker
     */
    public void setOnlyCalendar(boolean onlyCalendar) {
        this.onlyCalendar = onlyCalendar;
    }

    /**
     * Returns whether the picker shows only the clock.
     *
     * @return {@code true} for a time picker
     */
    public boolean isOnlyTime() {
        return onlyTime;
    }

    /**
     * Sets whether the picker shows only the clock.
     *
     * @param onlyTime {@code true} for a time picker
     */
    public void setOnlyTime(boolean onlyTime) {
        this.onlyTime = onlyTime;
    }

    /**
     * Returns whether typing a date in the text box moves the calendar to it.
     *
     * @return {@code true} if it does, the default
     */
    public boolean isFocusDateOnWrite() {
        return focusDateOnWrite;
    }

    /**
     * Sets whether typing a date in the text box moves the calendar to it.
     *
     * @param focusDateOnWrite {@code true} to move the calendar
     */
    public void setFocusDateOnWrite(boolean focusDateOnWrite) {
        this.focusDateOnWrite = focusDateOnWrite;
    }

    /**
     * Returns whether typing a date in the text box selects it.
     *
     * @return {@code true} if it does
     */
    public boolean isSelectDateOnWrite() {
        return selectDateOnWrite;
    }

    /**
     * Sets whether typing a date in the text box selects it.
     *
     * @param selectDateOnWrite {@code true} to select typed dates
     */
    public void setSelectDateOnWrite(boolean selectDateOnWrite) {
        this.selectDateOnWrite = selectDateOnWrite;
    }

    /**
     * Returns how long the picker waits after a key press before reading a typed date.
     *
     * @return the delay in milliseconds
     */
    public int getTypingDelay() {
        return typingDelay;
    }

    /**
     * Sets how long the picker waits after a key press before reading a typed date.
     *
     * @param typingDelay the delay in milliseconds, 1000 by default
     */
    public void setTypingDelay(int typingDelay) {
        this.typingDelay = typingDelay;
    }
}
