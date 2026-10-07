package org.gwtbootstrap5.extras.summernote.client.ui;

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

import org.gwtbootstrap5.extras.summernote.client.ui.base.SummernoteBase;

/**
 * A WYSIWYG HTML editor, by <a href="https://summernote.org/">Summernote</a> 0.9 for Bootstrap 5.
 * Inherit {@code org.gwtbootstrap5.extras.summernote.Summernote} (or {@code SummernoteURL}) to load
 * the library, which needs jQuery; the module loads it too. Read the HTML with {@code getCode()}.
 *
 * <pre>{@code
 * <sn:Summernote ui:field="editor" defaultHeight="160" placeholder="Write something..."/>
 * }</pre>
 *
 * @author godi
 */
public class Summernote extends SummernoteBase {

    /** Creates an editor with the default options. */
    public Summernote() {
        super();
    }

    /**
     * Creates an editor.
     *
     * @param height the height of the editing area, in pixels
     */
    public Summernote(final int height) {
        super();
        setDefaultHeight(height);
    }

    /**
     * Creates an editor.
     *
     * @param height the height of the editing area, in pixels
     * @param hasFocus {@code true} to give the focus to the editor when it is created
     */
    public Summernote(final int height, final boolean hasFocus) {
        this(height);
        setHasFocus(hasFocus);
    }
}
