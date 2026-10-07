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

import org.gwtbootstrap5.client.ui.html.Div;
import org.gwtbootstrap5.extras.summernote.client.event.HasAllSummernoteHandlers;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteBlurEvent;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteBlurHandler;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteChangeEvent;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteChangeHandler;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteEnterEvent;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteEnterHandler;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteFocusEvent;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteFocusHandler;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteImageUploadEvent;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteImageUploadHandler;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteInitEvent;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteInitHandler;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteKeyDownEvent;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteKeyDownHandler;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteKeyUpEvent;
import org.gwtbootstrap5.extras.summernote.client.event.SummernoteKeyUpHandler;
import org.gwtbootstrap5.extras.summernote.client.event.SummernotePasteEvent;
import org.gwtbootstrap5.extras.summernote.client.event.SummernotePasteHandler;
import org.gwtbootstrap5.extras.shared.js.JQuery;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.UIObject;

import elemental2.core.JsArray;
import elemental2.core.JsRegExp;
import elemental2.dom.File;
import jsinterop.base.Js;
import jsinterop.base.JsArrayLike;
import jsinterop.base.JsPropertyMap;

/**
 * Base class of {@code Summernote}, a {@code <div>} turned into a
 * <a href="https://summernote.org/">Summernote</a> editor when it is attached. The options set
 * before take effect then; after changing them on an attached editor, call {@link #reconfigure()}.
 *
 * @author Xiaodong Sun
 */
public class SummernoteBase extends Div implements HasAllSummernoteHandlers, HasEnabled {

    /**
     * Language; defaults to {@link SummernoteLanguage#EN_US}
     */
    private SummernoteLanguage language = SummernoteLanguage.EN_US;

    /**
     * Initialize options
     */
    private final SummernoteOptions options = new SummernoteOptions();

    /**
     * Enabled/Disabled state
     */
    private boolean enabled = true;

    private boolean hasInitHandler = false;
    private boolean hasEnterHandler = false;
    private boolean hasFocusHandler = false;
    private boolean hasBlurHandler = false;
    private boolean hasKeyUpHandler = false;
    private boolean hasKeyDownHandler = false;
    private boolean hasPasteHandler = false;
    private boolean hasUploadImageHandler = false;
    private boolean hasChangeHandler = false;

    /** Creates an editor with the default options; Summernote starts when it is attached. */
    public SummernoteBase() {}

    /**
     * Sets the default height of the editor (in pixel).<br>
     * <br>
     * <b>Note</b>: DO NOT renamed this method to <em>setHeight</em>
     * to avoid UiBinder name clash with {@link UIObject#setHeight(String)}.
     *
     * @param height the height in pixels
     */
    public void setDefaultHeight(final int height) {
        options.height = height;
    }

    /**
     * Sets the maximum height of the editor (in pixel).
     *
     * @param maxHeight the maximum height in pixels
     */
    public void setMaxHeight(final int maxHeight) {
        options.maxHeight = maxHeight;
    }

    /**
     * Sets the minimum height of the editor (in pixel).
     *
     * @param minHeight the minimum height in pixels
     */
    public void setMinHeight(final int minHeight) {
        options.minHeight = minHeight;
    }

    /**
     * If <code>false</code> the toolbar will be hidden.<br>
     * <br>
     * Defaults to <code>true</code>.
     *
     * @param showToolbar {@code true} to show the toolbar
     */
    public void setShowToolbar(final boolean showToolbar) {
        if (!showToolbar) {
            options.toolbar = false;
        } else if (Js.isTruthy(options.toolbar)) {
            Js.asPropertyMap(options).delete("toolbar");
        }
    }

    /**
     * Customizes the toolbar.<br>
     * <br>
     * Example:
     * <pre>
     * summernote.setToolbar(new Toolbar()
     *     .addGroup(ToolbarButton.OL, ToolbarButton.BOLD)
     *     .addGroup(ToolbarButton.HELP));
     * </pre>
     *
     * @param toolbar the toolbar
     */
    public void setToolbar(final Toolbar toolbar) {
        options.toolbar = toolbar.build();
    }

    /**
     * Set the focus of the editor.
     *
     * @param focus if <code>true</code>, focus on the editor
     */
    public void setHasFocus(final boolean focus) {
        options.focus = focus;
    }

    /**
     * Set placeholder of the editor.
     *
     * @param placeholder placeholder of the editor
     */
    public void setPlaceholder(final String placeholder) {
        options.placeholder = placeholder;
    }

    /**
     * Set customized font names.
     *
     * @param fontNames customized font names
     * @see SummernoteFontName
     */
    public void setFontNames(final SummernoteFontName... fontNames) {
        JsArray<String> array = new JsArray<>();
        for (SummernoteFontName fontName : fontNames) {
            array.push(fontName.getName());
        }
        options.fontNames = array;
    }

    /**
     * Set a list for Web fonts to be ignored. <br>
     * <br>
     * Summernote tests font in fontNames before adding them to drop-down.
     * This is problem while using Web fonts. It’s not easy picking up
     * nice time to check availabilities of Web fonts.
     *
     * @param fontNames the fonts
     */
    public void setFontNamesIgnoreCheck(final SummernoteFontName... fontNames) {
        JsArray<String> array = new JsArray<>();
        for (SummernoteFontName fontName : fontNames) {
            array.push(fontName.getName());
        }
        options.fontNamesIgnoreCheck = array;
    }

    /**
     * Set the air mode of the editor. Air-mode gives clearer interface with
     * hidden toolbar. To reveal toolbar, select a text where you want to
     * shape up.<br>
     * <br>
     * Defaults to <code>false</code>.
     *
     * @param airMode if <code>true</code>, the air mode is turn on
    */
    public void setAirMode(final boolean airMode) {
        options.airMode = airMode;
    }

    /**
     * Set <code>false</code> to disable custom shortcuts.<br>
     * <br>
     * Defaults to <code>true</code>.
     *
     * @param shortcuts if <code>false</code>, disable custom shortcuts
     */
    public void setShortcuts(final boolean shortcuts) {
        options.shortcuts = shortcuts;
    }

    /**
     * Set <code>true</code> to place dialogs in &lt;body&gt;
     * rather than in the editor.<br>
     * <br>
     * Defaults to <code>false</code>.
     *
     * @param dialogsInBody if <code>true</code>, place dialogs in &lt;body&gt;
     */
    public void setDialogsInBody(final boolean dialogsInBody) {
        options.dialogsInBody = dialogsInBody;
    }

    /**
     * Set <code>true</code> to turn on dialogs fading effect
     * when showing or hiding.<br>
     * <br>
     * Defaults to <code>false</code>.
     *
     * @param dialogsFade if <code>true</code>, turn on dialogs fading effect
     */
    public void setDialogsFade(final boolean dialogsFade) {
        options.dialogsFade = dialogsFade;
    }

    /**
     * Set <code>true</code> to disable drag and drop.<br>
     * <br>
     * Defaults to <code>false</code>.
     *
     * @param disableDragAndDrop if <code>true</code>, disable drag and drop
     */
    public void setDisableDragAndDrop(final boolean disableDragAndDrop) {
        options.disableDragAndDrop = disableDragAndDrop;
    }

    /**
     * Summernote support hint (autocomplete) feature. You can define custom hint
     * with options.
     *
     * @param matchRegexp the regular expression of the words that get hints
     * @param hintHandler gives the hints of a word
     */
    public void setHint(String matchRegexp, HintHandler hintHandler) {
        JsPropertyMap<Object> hint = JsPropertyMap.of();
        hint.set("match", new JsRegExp(matchRegexp));
        hint.set("search", (SummernoteOptions.HintSearch) (keyword, callback) -> {
            JsArray<String> result = new JsArray<>();
            for (String item : hintHandler.onSearch(keyword)) {
                result.push(item);
            }
            callback.accept(result);
        });
        hint.set("template", (SummernoteOptions.HintRenderer) hintHandler::getTemplate);
        hint.set("content", (SummernoteOptions.HintRenderer) hintHandler::getContent);
        options.hint = hint;
    }

    /**
     * Set the editor language.
     *
     * @param language supported editor language
     */
    public void setLanguage(final SummernoteLanguage language) {
        options.lang = language.getCode();
        this.language = language;
    }

    /**
     * Returns the editor language.
     *
     * @return the language
     */
    public SummernoteLanguage getLanguage() {
        return language;
    }

    @Override
    public HandlerRegistration addSummernoteInitHandler(final SummernoteInitHandler handler) {
        hasInitHandler = true;
        return addHandler(handler, SummernoteInitEvent.getType());
    }

    @Override
    public HandlerRegistration addSummernoteEnterHandler(final SummernoteEnterHandler handler) {
        hasEnterHandler = true;
        return addHandler(handler, SummernoteEnterEvent.getType());
    }

    @Override
    public HandlerRegistration addSummernoteFocusHandler(final SummernoteFocusHandler handler) {
        hasFocusHandler = true;
        return addHandler(handler, SummernoteFocusEvent.getType());
    }

    @Override
    public HandlerRegistration addSummernoteBlurHandler(final SummernoteBlurHandler handler) {
        hasBlurHandler = true;
        return addHandler(handler, SummernoteBlurEvent.getType());
    }

    @Override
    public HandlerRegistration addSummernoteKeyUpHandler(final SummernoteKeyUpHandler handler) {
        hasKeyUpHandler = true;
        return addHandler(handler, SummernoteKeyUpEvent.getType());
    }

    @Override
    public HandlerRegistration addSummernoteKeyDownHandler(final SummernoteKeyDownHandler handler) {
        hasKeyDownHandler = true;
        return addHandler(handler, SummernoteKeyDownEvent.getType());
    }

    @Override
    public HandlerRegistration addSummernotePasteHandler(final SummernotePasteHandler handler) {
        hasPasteHandler = true;
        return addHandler(handler, SummernotePasteEvent.getType());
    }

    @Override
    public HandlerRegistration addSummernoteImageUploadHandler(final SummernoteImageUploadHandler handler) {
        hasUploadImageHandler = true;
        return addHandler(handler, SummernoteImageUploadEvent.getType());
    }

    @Override
    public HandlerRegistration addSummernoteChangeHandler(final SummernoteChangeHandler handler) {
        hasChangeHandler = true;
        return addHandler(handler, SummernoteChangeEvent.getType());
    }

    /**
     * Gets the HTML code generated from the editor
     *
     * @return generated code
     */
    public String getCode() {
        if (isAttached()) {
            return jQuery().summernoteCommand("code");
        }
        return getElement().getInnerHTML();
    }

    /**
     * Sets the given HTML code to the editor.
     *
     * @param code the content, as HTML
     */
    public void setCode(final String code) {
        if (isAttached()) {
            jQuery().summernoteCommand("code", code);
        } else {
            getElement().setInnerHTML(code);
        }
    }

    /**
     * Returns <code>true</code> if the content is empty.<br>
     * <br>
     * Editing area needs <code>&lt;p&gt;&lt;br&gt;&lt;/p&gt;</code>
     * for focus, even if contents is empty. So summernote supports this method
     * for helping to check contents is empty.
     *
     * @return <code>true</code> if the editor is empty
     */
    public boolean isEmpty() {
        if (isAttached()) {
            return Js.isTruthy(jQuery().summernoteCommand("isEmpty"));
        }
        return getElement().getInnerHTML().isEmpty();
    }

    /**
     * Removes all contents and restores the editable instance
     * to an <code>_emptyPara_</code>: &lt;p&gt;&lt;br&gt;&lt;/p&gt;
     */
    @Override
    public void clear() {
        if (isAttached()) {
            jQuery().summernoteCommand("empty");
        } else {
            super.clear();
            getElement().removeAllChildren();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (isAttached()) {
            jQuery().summernoteCommand(enabled ? "enable" : "disable");
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Clear editor contents and remove all stored history.
     */
    public void reset() {
        if (isAttached()) {
            jQuery().summernoteCommand("reset");
        } else {
            clear();
        }
    }

    /**
     * Recreates the editor so the options changed since it was attached take effect.
     */
    public void reconfigure() {
        destroy();
        initialize();
    }

    private void initialize() {
        // Inject the language JS is necessary
        if (language.getJs() != null) {
            ScriptInjector.fromString(language.getJs().getText())
                .setWindow(ScriptInjector.TOP_WINDOW).inject();
        }
        // Initialize
        initialize(options);
        // Enable/Disable editor
        setEnabled(enabled);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        // Initialize
        initialize();
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        // Destroy
        destroy();
    }

    /**
     * Inserts the given images to the editor as data URLs.<br>
     * <br>
     * This method should be used only when you customize
     * the image upload handler.
     *
     * @param images the image files
     */
    public void insertImages(JsArray<File> images) {
        jQuery().summernoteCommand("insertImagesAsDataURL", images);
    }

    private JQuery jQuery() {
        return JQuery.jQuery(getElement());
    }

    private void initialize(SummernoteOptions options) {
        options.callbacks = JsPropertyMap.of();
        if (hasInitHandler) {
            options.callbacks.set("onInit", arg -> SummernoteInitEvent.fire(this));
        }
        if (hasEnterHandler) {
            options.callbacks.set("onEnter", arg -> SummernoteEnterEvent.fire(this));
        }
        if (hasFocusHandler) {
            options.callbacks.set("onFocus", arg -> SummernoteFocusEvent.fire(this));
        }
        if (hasBlurHandler) {
            options.callbacks.set("onBlur", arg -> SummernoteBlurEvent.fire(this));
        }
        if (hasKeyUpHandler) {
            options.callbacks.set("onKeyup", event -> SummernoteKeyUpEvent.fire(this, originalEvent(event)));
        }
        if (hasKeyDownHandler) {
            options.callbacks.set("onKeydown", event -> SummernoteKeyDownEvent.fire(this, originalEvent(event)));
        }
        if (hasUploadImageHandler) {
            // Summernote passes a FileList; copy it into a real array
            options.callbacks.set("onImageUpload", files -> SummernoteImageUploadEvent.fire(this,
                JsArray.from(Js.<JsArrayLike<File>>uncheckedCast(files))));
        }
        if (hasPasteHandler) {
            options.callbacks.set("onPaste", arg -> SummernotePasteEvent.fire(this));
        }
        if (hasChangeHandler) {
            options.callbacks.set("onChange", arg -> SummernoteChangeEvent.fire(this));
        }
        jQuery().summernote(options);
    }

    /**
     * Summernote passes the jQuery event to its key callbacks.
     */
    private static NativeEvent originalEvent(Object jQueryEvent) {
        return Js.uncheckedCast(Js.asPropertyMap(jQueryEvent).get("originalEvent"));
    }

    private void destroy() {
        jQuery().summernoteCommand("destroy");
    }
}
