package org.gwtbootstrap5.extras;

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

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gwt.dom.client.Style;

/**
 * Checks that every CSS class written by an extras enum implementing {@link Style.HasCssName} exists
 * in the bundled CSS: Bootstrap 5 and Bootstrap Icons (from core) plus the CSS of each library
 * bundled in extras. Runs on the plain JVM (no GWT), in every build.
 */
public class ExtrasClassesTest {

    private static final String[] CORE_CSS = {
        "/org/gwtbootstrap5/client/resource/css/bootstrap-5.3.8.min.cache.css",
        "/org/gwtbootstrap5/client/resource/css/bootstrap-icons-1.13.1.min.cache.css"
    };

    private static final Pattern CSS_CLASS = Pattern.compile("\\.(-?[_a-zA-Z][\\w-]*)");

    private static Path classesDir;
    private static Set<String> cssClasses;

    @BeforeClass
    public static void readCss() throws Exception {
        classesDir = Paths.get(ExtrasClassesTest.class.getClassLoader()
                .getResource("org/gwtbootstrap5/extras").toURI()).getParent().getParent().getParent();
        // getResource may resolve to test-classes; the main classes hold the extras CSS and enums
        if (classesDir.endsWith("test-classes")) {
            classesDir = classesDir.resolveSibling("classes");
        }

        cssClasses = new HashSet<>();
        for (String css : CORE_CSS) {
            try (InputStream in = ExtrasClassesTest.class.getResourceAsStream(css)) {
                assertTrue("Missing " + css, in != null);
                addClasses(new String(in.readAllBytes(), StandardCharsets.UTF_8));
            }
        }
        try (Stream<Path> files = Files.walk(classesDir)) {
            for (Path css : files.filter(p -> p.toString().endsWith(".css")).collect(Collectors.toList())) {
                addClasses(new String(Files.readAllBytes(css), StandardCharsets.UTF_8));
            }
        }
    }

    private static void addClasses(final String css) {
        Matcher m = CSS_CLASS.matcher(css);
        while (m.find()) {
            cssClasses.add(m.group(1));
        }
    }

    @Test
    public void everyClassExists() throws Exception {
        List<String> missing = new ArrayList<>();
        int checked = 0;
        for (Class<?> type : enumsWithCssNames()) {
            for (Object constant : type.getEnumConstants()) {
                String value = ((Style.HasCssName) constant).getCssName();
                if (value == null) {
                    continue;
                }
                for (String cssClass : value.trim().split("\\s+")) {
                    if (!cssClass.isEmpty() && !cssClasses.contains(cssClass)) {
                        missing.add(type.getSimpleName() + "." + ((Enum<?>) constant).name() + " = \"" + cssClass + "\"");
                    }
                }
                checked++;
            }
        }
        assertTrue("No enum constants found under " + classesDir, checked > 0);
        assertTrue("Classes not in the bundled CSS: " + missing, missing.isEmpty());
    }

    private static List<Class<?>> enumsWithCssNames() throws IOException, ClassNotFoundException {
        List<Class<?>> types = new ArrayList<>();
        try (Stream<Path> files = Files.walk(classesDir)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".class") && !p.getFileName().toString().contains("$"))
                    .collect(Collectors.toList())) {
                String name = classesDir.relativize(file).toString().replace(".class", "").replace(file.getFileSystem().getSeparator(), ".");
                Class<?> type = Class.forName(name, false, ExtrasClassesTest.class.getClassLoader());
                if (type.isEnum() && Style.HasCssName.class.isAssignableFrom(type)) {
                    types.add(type);
                }
            }
        }
        return types;
    }
}
