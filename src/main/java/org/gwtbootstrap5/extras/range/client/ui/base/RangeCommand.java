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

/**
 * The names of the bootstrap-slider methods.
 *
 * @author Xiaodong SUN
 * @see <a href="https://github.com/seiyria/bootstrap-slider#functions">...</a>
 */
public interface RangeCommand {

    /** Listens to an event. */
    String ON = "on";
    /** Returns the value. */
    String GET_VALUE = "getValue";
    /** Sets the value. */
    String SET_VALUE = "setValue";
    /** Returns the slider element. */
    String GET_ELEMENT = "getElement";
    /** Removes the slider and restores the input. */
    String DESTROY = "destroy";
    /** Disables the slider. */
    String DISABLE = "disable";
    /** Enables the slider. */
    String ENABLE = "enable";
    /** Enables the slider if it is disabled, disables it otherwise. */
    String TOGGLE = "toggle";
    /** Returns whether the slider is enabled. */
    String IS_ENABLED = "isEnabled";
    /** Sets an option. */
    String SET_ATTRIBUTE = "setAttribute";
    /** Returns an option. */
    String GET_ATTRIBUTE = "getAttribute";
    /** Rebuilds the slider with its current options. */
    String REFRESH = "refresh";
    /** Redraws the slider, after it was hidden. */
    String RELAYOUT = "relayout";

}
