GwtBootstrap5-Extras is a collection of wrappers based on the [GwtBootstrap5](https://github.com/gwtbootstrap5/gwtbootstrap5) for 3rd party extensions of [Bootstrap](https://getbootstrap.com/), which helps you develop responsive, mobile first HTML, CSS, and JS projects on the web using Java and Google Web Toolkit (GWT). 

### Add GwtBootstrap5-Extras to your project
You can easily add GwtBootstrap5-Extras to your project by including the library as a Maven dependency.

```xml
<dependency>
    <groupId>io.github.gwtbootstrap5</groupId>
    <artifactId>gwtbootstrap5-extras</artifactId>
    <version>0.4.0</version>
    <scope>provided</scope>
</dependency>
```

Then inherit one GWT module per extra you use. `Select` and the date pickers also need their engine's module, for example `org.gwtbootstrap5.extras.select.client.TomSelectResources`: each [Extras page of the demo](https://gwtbootstrap5.github.io/#extras/animate) lists the modules it needs.

### Final Release
* 0.4.0 - Released on 8 October 2026.
  * Based on GwtBootstrap5 v0.4.0. Choices.js 11 and Slim Select 4 as select engines, each date picker and select engine in its own GWT module, jQuery 4 with jQuery Migrate only for Summernote and ColorPicker. See [UPGRADING.md](https://github.com/gwtbootstrap5/gwtbootstrap5-parent/blob/master/UPGRADING.md#upgrading-to-040).
* 0.3.1 - Released on 7 October 2026.
  * Based on GwtBootstrap5 v0.3.1. `Bootbox.init` fixed and show/hide callbacks for Bootbox dialogs; Tom Select 2.6.2, Font Awesome 7.3.1, Air Datepicker 3.6.0 and jQuery UI 1.14.2.
* 0.3.0 - Released on 7 October 2026.
  * Based on GwtBootstrap5 v0.3.0. Select and the date pickers destroy their JavaScript widget on unload, Javadoc for the whole public API.
* 0.2.0 - Released on 5 October 2026.
  * Based on GwtBootstrap5 v0.2.0. JsInterop instead of JSNI, new `io.github.gwtbootstrap5` groupId. See [UPGRADING.md](https://github.com/gwtbootstrap5/gwtbootstrap5-parent/blob/master/UPGRADING.md).
* 0.1.12 - Released on 16 March 2026. 
  * Based on GwtBootstrap5 v0.1.12

### Links
* [Demo](https://gwtbootstrap5.github.io/) - Every widget of GwtBootstrap5 and its extras running, next to the UiBinder code that creates it.
* [Getting started](https://gwtbootstrap5.github.io/#setup) - Dependencies, the GWT module to inherit and the host page.
* [Upgrading](https://github.com/gwtbootstrap5/gwtbootstrap5-parent/blob/master/UPGRADING.md) - The breaking changes of each version and how to update your code.
* [API Docs](https://javadoc.io/doc/io.github.gwtbootstrap5/gwtbootstrap5-extras) - The GwtBootstrap5 Extras Javadoc.
* [GwtBootstrap5 API Docs](https://javadoc.io/doc/io.github.gwtbootstrap5/gwtbootstrap5) - The core Javadoc.
* [Maven Central](https://central.sonatype.com/namespace/io.github.gwtbootstrap5) - The published artifacts.
* [Issues](https://github.com/gwtbootstrap5/gwtbootstrap5-extras/issues) - Questions and bug reports for the extras.
* [GwtBootstrap5 Issues](https://github.com/gwtbootstrap5/gwtbootstrap5/issues) - Questions and bug reports for the core module.
