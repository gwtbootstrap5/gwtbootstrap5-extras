package org.gwtbootstrap5.extras.summernote.client.ui.base;

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
import java.util.List;

import elemental2.core.JsArray;

/**
 * Wrapper for the Summernote WYSIWYG Editor
 * <p/>
 * See: <a href="http://summernote.org/">...</a>
 *
 * @author Xiaodong Sun
 */
public class Toolbar {

    private static final String GROUP_PREFIX = "group_";
    private int groupIndex = 0;
    private final List<JsArray<Object>> groups = new ArrayList<>(0);

    /**
     * Add a new toolbar group with the specified buttons.
     *
     * @param buttons the toolbar buttons to be added
     * @return {@link Toolbar}
     */
    public Toolbar addGroup(ToolbarButton... buttons) {
        JsArray<String> ids = new JsArray<>();
        for (ToolbarButton button : buttons) {
            ids.push(button.getId());
        }
        groups.add(new JsArray<Object>(GROUP_PREFIX + groupIndex++, ids));
        return this;
    }

    JsArray<Object> build() {
        JsArray<Object> toolbar = new JsArray<>();
        for (JsArray<Object> group : groups) {
            toolbar.push(group);
        }
        return toolbar;
    }
}

