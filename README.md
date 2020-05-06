
# Flutter Localizer IntelliJ Plugin

IntelliJ Plugin to ease the process of localizing hard coded strings in Flutter projects

// TODO insert graphic gif


## Installation

The plugin is still under development and therefore not available on the IntelliJ Plugin repository.

Either download [flutter_localizer.zip](LINK TODO) or build the project yourself.

Then follow this guide on how to install plugins from a zip file:

https://ballerina.io/learn/intellij-plugin/#installing-the-zip-file-via-the-ide


## Usage

### Setup

![build_text](https://raw.githubusercontent.com/Norbert515/flutter_localizer/master/github_res/toolbar_setting.png?token=ADKSIVHICCROINMROIVJDXC6XPQMA)

You can supply up two json file paths which will be filled with the localized values.

Next, you have to specify the code which should be generated. "$$" will be replaced by the key
in camelCase

Example:
```
S.of(context).$$
```

![dialog](https://raw.githubusercontent.com/Norbert515/flutter_localizer/master/github_res/dialog.png?token=ADKSIVFMP4NDGIIY267GYU26XPSI6)

### Localization

All you have to do is hover over a string, and press Alt+Enter (invoke the quick action command).

![localize](https://raw.githubusercontent.com/Norbert515/flutter_localizer/master/github_res/localize.png?token=ADKSIVE73XAJUBYHJUFNG6S6XPSHC)





