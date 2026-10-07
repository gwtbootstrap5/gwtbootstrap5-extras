package org.gwtbootstrap5.extras.select.client.ui.base;

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

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.HasEditorDelegate;
import com.google.gwt.editor.client.adapters.TakesValueEditor;

/**
 * The editor of a {@link SelectBase} for the GWT Editor framework, which reads and writes the
 * widget's value.
 *
 *
 * @param <T> the type of the options
 */
public class SelectEditor<T> extends TakesValueEditor<T> implements HasEditorDelegate<T> {
    /**
     * Returns a new TakesValueEditor that adapts a {@link SelectBase} instance.
     *
     * @param <T> the type of the options
     * @param valueBox a {@link SelectBase} instance to adapt
     * @return a SelectEditor instance of the same type as the adapted {@link SelectBase}
     * instance
     */
    public static <T> SelectEditor<T> of(SelectBase<T> valueBox) {
        return new SelectEditor<>(valueBox);
    }

    private EditorDelegate<T> delegate;
    private final SelectBase<T> peer;

    /**
     * Constructs a new SelectEditor that adapts a {@link SelectBase} peer instance.
     *
     * @param peer a {@link SelectBase} instance of type T
     */
    protected SelectEditor(SelectBase<T> peer) {
        super(peer);
        this.peer = peer;
    }

    /**
     * Returns the {@link EditorDelegate} for this instance.
     *
     * @return an {@link EditorDelegate}, or {@code null}
     * @see #setDelegate(EditorDelegate)
     */
    public EditorDelegate<T> getDelegate() {
        return delegate;
    }

    /**
     * Calls {@link SelectBase#getValue()}. If a ParseException is thrown, it will be available
     * through {@link com.google.gwt.editor.client.EditorError#getUserData() EditorError.getUserData()}.
     *
     * @return a value of type T
     * @see #setValue(Object)
     */
    @Override
    public T getValue() {
        return peer.getValue();
    }

    /**
     * Sets the {@link EditorDelegate} for this instance. This method is only called by the driver.
     *
     * @param delegate an {@link EditorDelegate}, or {@code null}
     * @see #getDelegate()
     */
    public void setDelegate(EditorDelegate<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setValue(T value) {
        peer.setValue(value);
    }

}
