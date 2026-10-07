package org.gwtbootstrap5.extras.bootbox.client.options;

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
 * The size of a Bootbox dialog, its {@code size} option; the default size is Bootstrap's medium
 * modal.
 *
 * @author Tercio Gaudencio Filho (terciofilho [at] gmail.com)
 */
public enum BootboxSize {
    
    /** A large dialog, {@code modal-lg}. */
    LARGE("large"),
    /** A small dialog, {@code modal-sm}. */
    SMALL("small");
    
    private final String size;

    BootboxSize(String size) {
        this.size=size;
    }
    
    /**
     * Returns the value of Bootbox's {@code size} option.
     *
     * @return {@code "large"} or {@code "small"}
     */
    public String getSize() {
        return size;
    }
    
}
