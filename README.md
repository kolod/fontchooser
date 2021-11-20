# Java Swing Font Chooser Dialog and Panel

[![pipeline status](https://gitlab.com/dheid/fontchooser/badges/master/pipeline.svg)](https://gitlab.com/dheid/fontchooser/commits/master)
[![coverage report](https://gitlab.com/dheid/fontchooser/badges/master/coverage.svg)](https://gitlab.com/dheid/fontchooser/commits/master)
[![Windows Build Status](https://img.shields.io/appveyor/ci/dheid/fontchooser/master.svg?label=windows)](https://ci.appveyor.com/project/dheid/fontchooser/branch/master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.drjekyll/fontchooser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.drjekyll/fontchooser/)

Java does not provide a lightweight font chooser out of the box. This easy to use Java Swing font chooser component
allows users to select a font by selecting a font family name and the installed font variants. Users are able to search
for a font and insert their own preview text.

This font chooser is fully Java 18.9 compatible.

## Cross Platform
![Font Chooser (Cross Platform)](images/fontchooser-metallookandfeel.png "Font Chooser (Cross Platform)")

## GTK / Linux
![Font Chooser (GTK)](images/fontchooser-gtklookandfeel.png "Font Chooser (GTK)")

## Aqua / Mac
![Font Chooser (Aqua)](images/fontchooser-aqualookandfeel.png "Font Chooser (Aqua)")

## Windows
![Font Chooser (Windows)](images/fontchooser-windowslookandfeel.png "Font Chooser (Windows)")

There are translations for the following languages:

* English
* German
* Greek
* Hungarian
* Spanish
* Finnish
* French
* Brazilian Portuguese
* Russian

Font Chooser is an open source project and completely free. I appreciate improvements or extensions. Please contact
me if you have questions.

## Building

Please use the included Maven wrapper to build the JAR.

    ./mvnw package
    
After that you can include the file `fontchooser/target/fontchooser-VERSION.jar` into your project (replace VERSION with
the current project version).

## Install to local Maven repository

To test a version locally, please execute

    ./mvnw install
    
This will install the version to your local repository. You can now include it using Maven (see below).

## Usage

Include the following dependency to your project:
```xml 
<dependency>
    <groupId>org.drjekyll</groupId>
    <artifactId>fontchooser</artifactId>
    <version>2.4</version>
 </dependency>
```

If you want to apply a font to a specific component, e.g. a text area, simply use

```java
JTextArea textArea = new JTextArea();
FontDialog.showDialog(textArea);
```

This will display the modal font dialog and apply the font to the component, if the user clicked OK.

Here is another simple example on how to use the font chooser dialog in your application:

```java
FontDialog dialog = new FontDialog((Frame) null, "Font Dialog Example", true);
dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
dialog.setVisible(true);
if (!dialog.isCancelSelected()) {
  System.out.printf("Selected font is: %s%n", dialog.getSelectedFont());
}                                                               
```

You'll find more examples in the module fontchooser-example:

* [DialogExample](fontchooser-example/src/main/java/DialogExample.java)
* [PanelExample](fontchooser-example/src/main/java/PanelExample.java)
* [ShowDialogExample](fontchooser-example/src/main/java/ShowDialogExample.java)

## Contributing

Please see here: [Contributing](CONTRIBUTING.md)

## Credits

This is a major rewrite of the JFontChooser component, originally written by Dr Christos Bohoris (Copyright 2009).

## License

GNU LESSER GENERAL PUBLIC LICENSE
Version 3, 29 June 2007
https://www.gnu.org/licenses/lgpl-3.0.en.html
