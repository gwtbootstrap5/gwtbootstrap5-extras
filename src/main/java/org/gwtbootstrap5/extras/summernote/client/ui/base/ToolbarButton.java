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

/**
 * This enum represents Summernote toolbar buttons.
 *
 * @author Xiaodong SUN
 */
public enum ToolbarButton {

    /* Insert */
    /** Inserts a picture ({@code picture}). */
    PICTURE("picture"),
    /** Inserts a link ({@code link}). */
    LINK("link"),
    /** Inserts a video ({@code video}). */
    VIDEO("video"),
    /** Inserts a table ({@code table}). */
    TABLE("table"),
    /** Inserts a horizontal rule ({@code hr}). */
    HR("hr"),

    /* Font Style */
    /** Picks the font ({@code fontname}). */
    FONT_NAME("fontname"),
    /** Picks the font size ({@code fontsize}). */
    FONT_SIZE("fontsize"),
    /** Picks the text and background colors ({@code color}). */
    COLOR("color"),
    /** Bold ({@code bold}). */
    BOLD("bold"),
    /** Italic ({@code italic}). */
    ITALIC("italic"),
    /** Underline ({@code underline}). */
    UNDERLINE("underline"),
    /** Strikethrough ({@code strikethrough}). */
    STRIKETHROUGH("strikethrough"),
    /** Superscript ({@code superscript}). */
    SUPER_SCRIPT("superscript"),
    /** Subscript ({@code subscript}). */
    SUB_SCRIPT("subscript"),
    /** Removes the font style ({@code clear}). */
    CLEAR("clear"),

    /* Paragraph Style */
    /** Picks the block style: paragraph, quote, code or a heading ({@code style}). */
    STYLE("style"),
    /** Ordered list ({@code ol}). */
    OL("ol"),
    /** Unordered list ({@code ul}). */
    UL("ul"),
    /** Picks the alignment and the indentation ({@code paragraph}). */
    PARAGRAPH("paragraph"),
    /** Picks the line height ({@code height}). */
    HEIGHT("height"),

    /* Misc */
    /** Toggles full screen ({@code fullscreen}). */
    FULL_SCREEN("fullscreen"),
    /** Toggles the HTML source view ({@code codeview}). */
    CODE_VIEW("codeview"),
    /** Undo ({@code undo}). */
    UNDO("undo"),
    /** Redo ({@code redo}). */
    REDO("redo"),
    /** Shows the keyboard shortcuts ({@code help}). */
    HELP("help");

    private final String id;

    ToolbarButton(String id) {
        this.id = id;
    }

    /**
     * Returns the name Summernote knows the button by.
     *
     * @return the id of the button
     */
    public String getId() {
        return id;
    }

}
