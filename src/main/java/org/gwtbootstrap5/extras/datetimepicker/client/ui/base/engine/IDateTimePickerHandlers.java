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
import java.util.List;

/** What an engine calls when its picker opens, closes or changes. */
public interface IDateTimePickerHandlers {

    /** Called when the picker opens. */
    void onShow();
    /** Called when the picker closes. */
    void onHide();
    /**
     * Called when the selected dates change.
     *
     * @param dates the selected dates
     */
    void onChangeValue(List<Date> dates);

}
