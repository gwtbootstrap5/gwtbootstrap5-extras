package org.gwtbootstrap5.extras.select.client.ui.base.engine;

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

import java.util.List;

/**
 * The JavaScript library behind a {@code Select} or {@code MultipleSelect}. The widget talks to
 * the library only through this interface, with options as value/text pairs; the methods do
 * nothing until {@link #init} has run.
 */
public interface ISelectEngine {

    /**
     * Creates the JavaScript select on the element.
     *
     * @param element the {@code <select>} element of the widget
     * @param options the widget's settings
     * @param handlers the widget's callbacks, which the engine calls on the library's events
     */
    void init(com.google.gwt.dom.client.SelectElement element, SelectProperties options, ISelectHandlers handlers);

    /**
     * Applies changed settings, keeping the options and the selection.
     *
     * @param options the widget's settings
     */
    void updateProperties(SelectProperties options);

    /** Destroys the JavaScript select; {@link #isStarted()} returns {@code false} after it. */
    void destroy();

    /** Updates the JavaScript select from the options of the {@code <select>} element. */
    void refresh();

    /** Opens the dropdown. */
    void show();

    /** Closes the dropdown. */
    void hide();

    /** Opens the dropdown if it is closed, closes it otherwise. */
    void toggle();

    /** Gives the focus to the select. */
    void focus();

    /** Takes the focus away from the select. */
    void blur();

    /**
     * Loads the options for a search, through {@link ISelectHandlers#onAsyncLoad}.
     *
     * @param search the search text
     */
    void triggerAsyncLoad(String search);

    /**
     * Deselects everything.
     *
     * @param silent {@code true} not to fire the change event
     */
    void clear(boolean silent);

    /** Removes every option. */
    void clearOptions();

    /**
     * Replaces the options.
     *
     * @param options the new options
     */
    void setOptions(List<SelectOption> options);

    /**
     * Adds an option.
     *
     * @param option the option
     */
    void addOption(SelectOption option);

    /**
     * Adds options.
     *
     * @param options the options
     */
    void addOptions(List<SelectOption> options);

    /**
     * Returns the options.
     *
     * @return the options, empty if the engine hasn't started
     */
    List<SelectOption> getOptions();

    /**
     * Returns whether there is an option with a value.
     *
     * @param value the value
     * @return {@code true} if there is one
     */
    boolean haveOption(String value);

    /**
     * Selects one option.
     *
     * @param value the value of the option
     * @param silent {@code true} not to fire the change event
     */
    void setValue(String value, boolean silent);

    /**
     * Returns the selected value.
     *
     * @return the value, or {@code null} if the engine hasn't started
     */
    String getValue();

    /**
     * Selects several options.
     *
     * @param value the values of the options
     * @param silent {@code true} not to fire the change event
     */
    void setValues(List<String> value, boolean silent);

    /**
     * Returns the selected values.
     *
     * @return the values, empty if nothing is selected
     */
    List<String> getValues();

    /**
     * Enables or disables the select.
     *
     * @param enabled {@code true} to enable it
     */
    void setEnabled(boolean enabled);

    /**
     * Returns whether {@link #init} has run and the select hasn't been destroyed since.
     *
     * @return {@code true} if the JavaScript select exists
     */
    boolean isStarted();

    /** An option as the engine sees it: a value and the text shown. */
    class SelectOption {
        private String value;
        private String text;

        /** Creates an option with no value and no text. */
        public SelectOption() {
        }

        /**
         * Returns the value, unique among the options.
         *
         * @return the value
         */
        public final String getValue() { return value; }

        /**
         * Sets the value, unique among the options.
         *
         * @param value the value
         */
        public final void setValue(String value) { this.value = value; }

        /**
         * Returns the text shown.
         *
         * @return the text
         */
        public final String getText() { return text; }

        /**
         * Sets the text shown.
         *
         * @param text the text
         */
        public final void setText(String text) { this.text = text; }
    }
}
