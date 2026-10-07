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

/**
 * The settings of a select, kept by the widget and translated by the {@link ISelectEngine} into the
 * library's options.
 */
public class SelectProperties {
    private boolean loadOnOpen = false;
    private boolean allowClear = true;
    private boolean searchEnabled = true;
    private String searchPlaceholder = null;
    private String noResultsText = null;
    private boolean multiple = false;
    private int multipleLimit = 0;
    private String placeholder = "";

    /** Creates the default settings. */
    public SelectProperties() {
    }

    /**
     * Returns whether the options are loaded when the select gets the focus, through the async
     * load.
     *
     * @return {@code true} to load them
     */
    public boolean isLoadOnOpen() {
        return loadOnOpen;
    }

    /**
     * Sets whether the options are loaded when the select gets the focus, through the async load.
     * Defaults to false.
     *
     * @param loadOnOpen {@code true} to load them
     */
    public void setLoadOnOpen(boolean loadOnOpen) {
        this.loadOnOpen = loadOnOpen;
    }

    /**
     * Returns whether the select has a button that clears the selection.
     *
     * @return {@code true} to show it
     */
    public boolean isAllowClear() {
        return allowClear;
    }

    /**
     * Sets whether the select has a button that clears the selection. Defaults to true.
     *
     * @param allowClear {@code true} to show it
     */
    public void setAllowClear(boolean allowClear) {
        this.allowClear = allowClear;
    }

    /**
     * Returns whether the select has a search box.
     *
     * @return {@code true} to show it
     */
    public boolean isSearchEnabled() {
        return searchEnabled;
    }

    /**
     * Sets whether the select has a search box. Defaults to true.
     *
     * @param searchEnabled {@code true} to show it
     */
    public void setSearchEnabled(boolean searchEnabled) {
        this.searchEnabled = searchEnabled;
    }

    /**
     * Returns the placeholder of the search box.
     *
     * @return the placeholder
     */
    public String getSearchPlaceholder() {
        return searchPlaceholder;
    }

    /**
     * Sets the placeholder of the search box. Defaults to {@code null}.
     * The Tom Select engine doesn't use it: Tom Select searches in the select itself.
     *
     * @param searchPlaceholder the placeholder
     */
    public void setSearchPlaceholder(String searchPlaceholder) {
        this.searchPlaceholder = searchPlaceholder;
    }

    /**
     * Returns the text shown when a search finds no option.
     *
     * @return the text, or {@code null} for the library's
     */
    public String getNoResultsText() {
        return noResultsText;
    }

    /**
     * Sets the text shown when a search finds no option. Defaults to {@code null}.
     *
     * @param noResultsText the text, or {@code null} for the library's
     */
    public void setNoResultsText(String noResultsText) {
        this.noResultsText = noResultsText;
    }

    /**
     * Returns the maximum number of options a multiple select can select.
     *
     * @return the maximum, or 0 for no limit
     */
    public int getMultipleLimit() {
        return multipleLimit;
    }

    /**
     * Sets the maximum number of options a multiple select can select. Defaults to 0.
     *
     * @param multipleLimit the maximum, or 0 for no limit
     */
    public void setMultipleLimit(int multipleLimit) {
        this.multipleLimit = multipleLimit;
    }

    /**
     * Returns whether several options can be selected.
     *
     * @return {@code true} for a multiple select
     */
    public boolean isMultiple() {
        return multiple;
    }

    /**
     * Sets whether several options can be selected. Defaults to false.
     *
     * @param multiple {@code true} for a multiple select
     */
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    /**
     * Returns the text shown when nothing is selected.
     *
     * @return the placeholder
     */
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * Sets the text shown when nothing is selected. Defaults to empty.
     *
     * @param placeholder the placeholder
     */
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
