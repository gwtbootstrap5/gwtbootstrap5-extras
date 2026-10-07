package org.gwtbootstrap5.extras.summernote;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Checks that the code of each {@code SummernoteLanguage} is the one its translation file
 * registers in {@code $.summernote.lang}: with any other code Summernote silently falls back to
 * English. The enum can't be loaded on the JVM (its constants call {@code GWT.create}), so the test
 * reads the sources. Runs on the plain JVM, in every build.
 */
public class SummernoteLanguageTest {

    private static final Path CLIENT = Paths.get("src/main/java/org/gwtbootstrap5/extras/summernote/client");
    private static final String RESOURCES = "/org/gwtbootstrap5/extras/summernote/client/";

    private static final Pattern CONSTANT = Pattern.compile(
            "(\\w+)\\(\"([^\"]+)\", SummernoteClientBundle\\.INSTANCE\\.(\\w+)\\(\\)\\)");
    private static final Pattern SOURCE = Pattern.compile("@Source\\(LOCALE_DIR \\+ \"([^\"]+)\"\\)\\s+TextResource (\\w+)\\(\\);");
    private static final Pattern REGISTERED = Pattern.compile("\"([a-z]{2}-[A-Za-z-]+)\":\\{font");

    @Test
    public void codesMatchTheTranslationFiles() throws Exception {
        String bundle = read(CLIENT.resolve("SummernoteClientBundle.java"));
        Matcher dir = Pattern.compile("LOCALE_DIR = \"([^\"]+)\" \\+ VERSION \\+ \"/\"").matcher(bundle);
        Matcher version = Pattern.compile("VERSION = \"([^\"]+)\"").matcher(bundle);
        assertTrue(dir.find() && version.find());
        String localeDir = RESOURCES + dir.group(1) + version.group(1) + "/";

        Map<String, String> files = new HashMap<>();
        Matcher source = SOURCE.matcher(bundle);
        while (source.find()) {
            files.put(source.group(2), source.group(1));
        }

        List<String> wrong = new ArrayList<>();
        int checked = 0;
        Matcher constant = CONSTANT.matcher(read(CLIENT.resolve("ui/base/SummernoteLanguage.java")));
        while (constant.find()) {
            String file = files.get(constant.group(3));
            assertNotNull("No @Source for " + constant.group(3), file);
            try (InputStream in = getClass().getResourceAsStream(localeDir + file)) {
                assertNotNull("Missing " + localeDir + file, in);
                Matcher registered = REGISTERED.matcher(new String(in.readAllBytes(), StandardCharsets.UTF_8));
                assertTrue("No language registered in " + file, registered.find());
                if (!registered.group(1).equals(constant.group(2))) {
                    wrong.add(constant.group(1) + " is \"" + constant.group(2) + "\" but " + file + " registers \""
                            + registered.group(1) + "\"");
                }
            }
            checked++;
        }
        assertTrue("No languages found", checked > 40);
        assertEquals("Codes that Summernote doesn't know", new ArrayList<String>(), wrong);
    }

    private static String read(final Path path) throws Exception {
        return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }
}
