package org.gwtbootstrap5.extras.select.client.ui.engines;

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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.gwtbootstrap5.extras.select.client.ui.base.engine.ISelectEngine;
import org.junit.Test;

/**
 * Checks that each select engine is compiled only when its module is inherited: the registry
 * that replaces the static references to the engines, and the modules that bring each engine.
 * Runs on the plain JVM (no GWT), in every build.
 */
public class SelectEngineTest {

    private static final Path EXTRAS = Paths.get("src/main/java/org/gwtbootstrap5/extras");
    private static final String MODULES = "/org/gwtbootstrap5/extras/select/";

    private static ISelectEngine fakeEngine() {
        return (ISelectEngine) Proxy.newProxyInstance(ISelectEngine.class.getClassLoader(),
                new Class<?>[] {ISelectEngine.class}, (proxy, method, args) -> null);
    }

    private static String expectError(Runnable call) {
        try {
            call.run();
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
        fail("Expected an IllegalStateException");
        return null;
    }

    private static String read(String resource) throws IOException {
        try (InputStream in = SelectEngineTest.class.getResourceAsStream(resource)) {
            assertNotNull("Missing " + resource, in);
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    // The registry is static, so the steps go in one test, in order
    @Test
    public void registryNamesTheModuleToInheritAndPicksTheOnlyEngine() {
        assertTrue(SelectEngine.getRegisteredEngines().isEmpty());

        String none = expectError(SelectEngine::getDefaultEngine);
        for (SelectEngine engine : SelectEngine.values()) {
            assertTrue(none, none.contains(engine.getModule()));
        }

        String missing = expectError(() -> SelectEngine.getEngine(SelectEngine.CHOICESJS));
        assertTrue(missing, missing.contains("org.gwtbootstrap5.extras.select.client.ChoicesResources"));
        assertTrue(missing, missing.contains("ChoicesURL"));

        SelectEngine.register(SelectEngine.CHOICESJS, SelectEngineTest::fakeEngine);
        assertTrue(SelectEngine.isRegistered(SelectEngine.CHOICESJS));
        assertFalse(SelectEngine.isRegistered(SelectEngine.TOMSELECT));
        // A new engine for each select
        assertNotSame(SelectEngine.getDefaultEngine(), SelectEngine.getDefaultEngine());
        expectError(() -> SelectEngine.getEngine(SelectEngine.SLIMSELECT));

        SelectEngine.register(SelectEngine.SLIMSELECT, SelectEngineTest::fakeEngine);
        assertEquals(2, SelectEngine.getRegisteredEngines().size());
        assertNotNull(SelectEngine.getEngine(SelectEngine.SLIMSELECT));
        String several = expectError(SelectEngine::getDefaultEngine);
        assertTrue(several, several.contains("More than one"));
    }

    @Test
    public void eachEngineComesOnlyWithItsModules() throws IOException {
        String base = read(MODULES + "Select.gwt.xml");
        for (SelectEngine engine : SelectEngine.values()) {
            String pkg = engine.name().toLowerCase(Locale.ROOT);
            assertTrue("Select.gwt.xml must leave out " + pkg,
                    base.contains("<exclude name=\"ui/engines/" + pkg + "/**\"/>"));

            String module = engine.getModule();
            String simpleName = module.substring(module.lastIndexOf('.') + 1);
            for (String variant : new String[] {simpleName, simpleName.replace("Resources", "URL")}) {
                String xml = read(MODULES + "client/" + variant + ".gwt.xml");
                assertTrue(variant + " must bring the engine", xml.contains("<source path=\"ui/engines/" + pkg + "\"/>"));
                assertTrue(variant + " must inherit Select",
                        xml.contains("<inherits name=\"org.gwtbootstrap5.extras.select.Select\"/>"));
            }
        }
    }

    @Test
    public void nothingOutsideAnEngineReferencesIt() throws IOException {
        List<String> offenders = new ArrayList<>();
        List<Path> sources;
        try (Stream<Path> files = Files.walk(EXTRAS)) {
            sources = files.filter(p -> p.toString().endsWith(".java")).collect(Collectors.toList());
        }
        for (SelectEngine engine : SelectEngine.values()) {
            String pkg = "select.client.ui.engines." + engine.name().toLowerCase(Locale.ROOT);
            Path own = EXTRAS.resolve(pkg.replace('.', '/'));
            for (Path source : sources) {
                if (!source.startsWith(own)
                        && new String(Files.readAllBytes(source), StandardCharsets.UTF_8).contains(pkg + ".")) {
                    offenders.add(source + " references " + pkg);
                }
            }
        }
        assertTrue("Only the module of an engine may reference it, or every app compiles it: " + offenders,
                offenders.isEmpty());
    }
}
