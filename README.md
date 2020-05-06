[![stars](https://img.shields.io/github/stars/Norbert515/flutter_localizer)](https://github.com/Norbert515/flutter_localizer/stargazers)
[![GitHub followers](https://img.shields.io/github/followers/norbert515.svg?style=social&label=Follow)](https://github.com/Norbert515)
[![Twitter Follow](https://img.shields.io/twitter/follow/norbertkozsir.svg?style=social&label=Follow)](https://twitter.com/norbertkozsir)

<a href="https://www.buymeacoffee.com/norbertkozsir" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" style="height: 51px !important;width: 217px !important;" ></a>

# Flutter Localizer IntelliJ Plugin

IntelliJ Plugin to ease the process of localizing hard coded strings in Flutter projects

![showcase](https://raw.githubusercontent.com/Norbert515/flutter_localizer/master/github_res/showcase.gif?token=ADKSIVCVALSIN3RV365QC7S6XPYQO)


## Installation

The plugin is still under development and therefore not available on the IntelliJ Plugin repository.

Either download [flutter_localizer.zip](https://raw.githubusercontent.com/Norbert515/flutter_localizer/master/github_res/showcase.gif?token=ADKSIVCVALSIN3RV365QC7S6XPYQO) or build the project yourself.

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

In the following dialog you have to specify the key for that given string. This should be in camel_case.

![localize_dialog](https://raw.githubusercontent.com/Norbert515/flutter_localizer/master/github_res/localize_dialog.png?token=ADKSIVEO5LVP7YQNZQH2GRC6XPUXE)

Press "Ok" and you are done!


## Contribution

This is very early, but contributions, ideas and feedback is appreciated!






