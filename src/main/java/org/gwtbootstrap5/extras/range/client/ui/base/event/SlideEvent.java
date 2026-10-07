package org.gwtbootstrap5.extras.range.client.ui.base.event;

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

/**
 * The slide event is fired when the slider is dragged.
 *
 * @param <T> slider value type
 */
public class SlideEvent<T> extends GwtEvent<SlideHandler<T>> {

    private static Type<SlideHandler<?>> TYPE;

    private final T value;

    /**
     * Fires a slide event on all registered handlers in the handler manager. If
     * no such handlers exist, this method will do nothing.
     *
     * @param <T> slider value type
     * @param source the source of the handlers
     * @param newValue the new slider value
     */
    public static <T> void fire(final HasSlideHandlers<T> source, T newValue) {
        if (TYPE != null) {
            SlideEvent<T> event = new SlideEvent<>(newValue);
            source.fireEvent(event);
        }
    }

    /**
     * Gets the type associated with this event.
     *
     * @return returns the handler type
     */
    public static Type<SlideHandler<?>> getType() {
        if (TYPE == null) {
            TYPE = new Type<>();
        }
        return TYPE;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Type<SlideHandler<T>> getAssociatedType() {
        return (Type) TYPE;
    }

    @Override
    protected void dispatch(final SlideHandler<T> handler) {
        handler.onSlide(this);
    }

    /**
     * Creates a slide event with slider value
     *
     * @param newValue the new slider value
     */
    protected SlideEvent(final T newValue) {
        this.value = newValue;
    }

    /**
     * Returns the value of the slider while it is dragged.
     *
     * @return the new slider value
     */
    public T getValue() {
        return value;
    }

    @Override
    public String toDebugString() {
        return super.toDebugString() + getValue();
    }
}
