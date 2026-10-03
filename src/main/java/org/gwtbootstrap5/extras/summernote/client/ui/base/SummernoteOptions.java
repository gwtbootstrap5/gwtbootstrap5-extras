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

import elemental2.core.JsArray;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;

/**
 * This class represents Summernote options, that you can use to
 * customize the editor. Only the fields that are set are passed to Summernote.
 *
 * @author Xiaodong SUN
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
class SummernoteOptions {

    /**
     * A Summernote callback (<code>onInit</code>, <code>onKeyup</code>, <code>onImageUpload</code>, ...).
     */
    @JsFunction
    interface Callback {
        void call(Object argument);
    }

    /**
     * The <code>hint.search</code> function.
     */
    @JsFunction
    interface HintSearch {
        void search(String keyword, HintResult callback);
    }

    /**
     * The callback that receives the hint search results.
     */
    @JsFunction
    interface HintResult {
        void accept(JsArray<String> items);
    }

    /**
     * The <code>hint.template</code> and <code>hint.content</code> functions.
     */
    @JsFunction
    interface HintRenderer {
        Object render(String item);
    }

    public String placeholder;
    public JsArray<String> fontNames;
    public JsArray<String> fontNamesIgnoreCheck;
    public boolean dialogsInBody;
    public boolean dialogsFade;
    public boolean disableDragAndDrop;
    public boolean shortcuts;
    /**
     * <code>false</code> hides the toolbar; otherwise an array of toolbar groups.
     */
    public Object toolbar;
    public int height;
    public int maxHeight;
    public int minHeight;
    public boolean focus;
    public String lang;
    public boolean airMode;
    public JsPropertyMap<Object> hint;
    public JsPropertyMap<Callback> callbacks;
}
