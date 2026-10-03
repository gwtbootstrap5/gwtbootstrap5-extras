package org.gwtbootstrap5.extras.summernote.client.event;

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

import com.google.gwt.event.shared.GwtEvent;

import elemental2.core.JsArray;
import elemental2.dom.File;

/**
 * The {@link SummernoteImageUploadEvent} is fired when inserting images into the
 * summernote editor.
 *
 * @author Xiaodong Sun
 */
public class SummernoteImageUploadEvent extends GwtEvent<SummernoteImageUploadHandler> {

    private static Type<SummernoteImageUploadHandler> TYPE;

    private final JsArray<File> images;

    /**
     * Fires a summernote image upload event on all registered handlers in the
     * handler manager. If no such handlers exist, this method will do nothing.
     *
     * @param source the source of the handlers
     */
    public static void fire(final HasSummernoteImageUploadHandlers source, JsArray<File> images) {
        if (TYPE != null) {
            SummernoteImageUploadEvent event = new SummernoteImageUploadEvent(images);
            source.fireEvent(event);
        }
    }

    /**
     * Gets the type associated with this event.
     *
     * @return returns the handler type
     */
    public static Type<SummernoteImageUploadHandler> getType() {
        if (TYPE == null) {
            TYPE = new Type<>();
        }
        return TYPE;
    }

    @Override
    public Type<SummernoteImageUploadHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final SummernoteImageUploadHandler handler) {
        handler.onSummernoteImageUpload(this);
    }

    /**
     * Creates a summernote image upload event.
     */
    protected SummernoteImageUploadEvent(JsArray<File> images) {
        this.images = images;
    }

    @Override
    public String toDebugString() {
        return super.toDebugString() + " with " + images.length + " images";
    }

    /**
     * Returns the JavaScript array of the {@link File}s to be
     * inserted.
     *
     * @return the JavaScript array of the {@link File}s to be
     *         inserted.
     */
    public JsArray<File> getImages() {
        return images;
    }
}
