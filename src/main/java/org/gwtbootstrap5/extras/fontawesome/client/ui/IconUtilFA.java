package org.gwtbootstrap5.extras.fontawesome.client.ui;

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

import com.google.gwt.user.client.ui.UIObject;
import org.gwtbootstrap5.client.ui.base.helper.EnumHelper;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.IconType;
import org.gwtbootstrap5.client.ui.constants.IconTypeBI;
import org.gwtbootstrap5.client.ui.util.IIconUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@link IIconUtil} of the {@code FontAwesome} modules, which replace core's with it: icon names
 * are looked up in {@link IconTypeFASolid}, then {@link IconTypeFARegular}, {@link IconTypeFABrands}
 * and core's {@link IconTypeBI}, so the Bootstrap Icons keep working.
 */
public class IconUtilFA implements IIconUtil {

    /** Creates the icon util; GWT creates it through deferred binding. */
    public IconUtilFA() {
    }

    @Override
    public List<IconType> getValues() {
        List<IconType> iconTypeList = new ArrayList<>();
        iconTypeList.addAll(Arrays.asList(IconTypeFASolid.values()));
        iconTypeList.addAll(Arrays.asList(IconTypeFARegular.values()));
        iconTypeList.addAll(Arrays.asList(IconTypeFABrands.values()));
        iconTypeList.addAll(Arrays.asList(IconTypeBI.values()));
        return iconTypeList;
    }

    @Override
    public IconType fromIconType(final String enumName) {
        if (enumName == null) return null;

        IconType type = EnumHelper.fromEnumName(enumName, IconTypeFASolid.class, null);
        if (type == null) type = EnumHelper.fromEnumName(enumName, IconTypeFARegular.class, null);
        if (type == null) type = EnumHelper.fromEnumName(enumName, IconTypeFABrands.class, null);
        if (type == null) type = EnumHelper.fromEnumName(enumName, IconTypeBI.class, null);

        return type;
    }

    @Override
    public IconType fromStyleName(final String styleName) {
        if (styleName == null) return null;

        IconType type = EnumHelper.fromStyleName(styleName, IconTypeFASolid.class, null);
        if (type == null) type = EnumHelper.fromStyleName(styleName, IconTypeFARegular.class, null);
        if (type == null) type = EnumHelper.fromStyleName(styleName, IconTypeFABrands.class, null);
        if (type == null) type = EnumHelper.fromStyleName(styleName, IconTypeBI.class, null);

        return type;
    }

    @Override
    public void setType(final UIObject uiObject, final IconType type) {
        if (type instanceof IconTypeFABrands) {
            StyleHelper.addUniqueEnumStyleName(uiObject, IconTypeFABrands.class, type);
        } else if (type instanceof IconTypeFASolid) {
            StyleHelper.addUniqueEnumStyleName(uiObject, IconTypeFASolid.class, type);
        } else if (type instanceof IconTypeFARegular) {
            StyleHelper.addUniqueEnumStyleName(uiObject, IconTypeFARegular.class, type);
        } else if (type instanceof IconTypeBI) {
            StyleHelper.addUniqueEnumStyleName(uiObject, IconTypeBI.class, type);
        }
    }

}
