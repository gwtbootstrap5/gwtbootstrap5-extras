GwtBootstrap5-Extras is a collection of wrappers based on the [GwtBootstrap5](https://github.com/gwtbootstrap5/gwtbootstrap5) for 3rd party extensions of [Bootstrap](https://getbootstrap.com/), which helps you develop responsive, mobile first HTML, CSS, and JS projects on the web using Java and Google Web Toolkit (GWT). 

### Add GwtBootstrap5-Extras to your project
You can easily add GwtBootstrap5-Extras to your project by including the library as a Maven dependency.

```xml
<dependency>
    <groupId>io.github.gwtbootstrap5</groupId>
    <artifactId>gwtbootstrap5-extras</artifactId>
    <version>0.2.0</version>
    <scope>provided</scope>
</dependency>
```

Then inherit one GWT module per extra you use. `Select` and the date pickers also need their engine's module, for example `org.gwtbootstrap5.extras.select.client.TomSelectResources`: each [Extras page of the demo](https://gwtbootstrap5.github.io/#extras/animate) lists the modules it needs.

### Final Release
* 0.2.0 - Released on 5 October 2026.
  * Based on GwtBootstrap5 v0.2.0. JsInterop instead of JSNI, new `io.github.gwtbootstrap5` groupId. See [UPGRADING.md](https://github.com/gwtbootstrap5/gwtbootstrap5-parent/blob/master/UPGRADING.md).
* 0.1.12 - Released on 16 March 2026. 
  * Based on GwtBootstrap5 v0.1.12

### Links
* [Demo](https://gwtbootstrap5.github.io/) - Every widget of GwtBootstrap5 and its extras running, next to the UiBinder code that creates it.
* [Getting started](https://gwtbootstrap5.github.io/#setup) - Dependencies, the GWT module to inherit and the host page.
* [Upgrading to 0.2.0](https://github.com/gwtbootstrap5/gwtbootstrap5-parent/blob/master/UPGRADING.md) - Breaking changes from 0.1.x and how to update your code.
* [API Docs](https://javadoc.io/doc/io.github.gwtbootstrap5/gwtbootstrap5-extras) - The GwtBootstrap5 Extras Javadoc.
* [GwtBootstrap5 API Docs](https://javadoc.io/doc/io.github.gwtbootstrap5/gwtbootstrap5) - The core Javadoc.
* [Maven Central](https://central.sonatype.com/namespace/io.github.gwtbootstrap5) - The published artifacts.
* [Issues](https://github.com/gwtbootstrap5/gwtbootstrap5-extras/issues) - Questions and bug reports for the extras.
* [GwtBootstrap5 Issues](https://github.com/gwtbootstrap5/gwtbootstrap5/issues) - Questions and bug reports for the core module.
