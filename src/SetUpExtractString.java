import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SetUpExtractString extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(CommonDataKeys.PROJECT);


        assert project != null;
        String path = Settings.getPath(project, 0);
        String path2 = Settings.getPath(project, 1);

        String replacement = Settings.getReplacementString(project);

        DialogBuilder builder = new DialogBuilder(project);

        JComponent content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentX(Component.LEFT_ALIGNMENT);

        /// 1
        JLabel selectFileLocationPath = new JLabel();
        selectFileLocationPath.setText("Select the file location of the default json localization file");
        content.add(selectFileLocationPath);

        TextFieldWithBrowseButton jsonLocationTextField = new TextFieldWithBrowseButton();
        jsonLocationTextField.setText(path);
        jsonLocationTextField.addBrowseFolderListener("Choose json file", "Choose a localization file, it has to be in JSON format", project, FileChooserDescriptorFactory.createSingleFileDescriptor());
        jsonLocationTextField.setMaximumSize(new Dimension(Short.MAX_VALUE,
                56));
        content.add(jsonLocationTextField);

        ///


        /// Two
        JLabel selectFileLocationPath2 = new JLabel();
        selectFileLocationPath2.setText("Select the file location of another json localization file");
        content.add(selectFileLocationPath2);

        TextFieldWithBrowseButton jsonLocationTextField2 = new TextFieldWithBrowseButton();
        jsonLocationTextField2.setText(path2);
        jsonLocationTextField2.addBrowseFolderListener("Choose json file", "Choose a localization file, it has to be in JSON format", project, FileChooserDescriptorFactory.createSingleFileDescriptor());
        jsonLocationTextField2.setMaximumSize(new Dimension(Short.MAX_VALUE,
                56));
        content.add(jsonLocationTextField2);

        ///

        JLabel replacementLabel = new JLabel();
        replacementLabel.setText("The code that should replace the Text. \n '$$' will be replaced by the key in CamelCase");
        content.add(replacementLabel);

        JTextField replacementTextField = new JTextField();
        replacementTextField.setText(replacement);
        replacementTextField.setMaximumSize(new Dimension(Short.MAX_VALUE,
                56));
        content.add(replacementTextField);

        builder.setDimensionServiceKey("GrepConsoleTailFileDialog");
        builder.setTitle("Choose your json path");
        builder.centerPanel(content);
        builder.removeAllActions();
        builder.addOkAction();
        builder.addCancelAction();

        builder.setOkOperation(() -> {
            String location = jsonLocationTextField.getText();
            Settings.savePath(project, location, 0);
            Settings.savePath(project, jsonLocationTextField2.getText(), 1);


            String replacementText = replacementTextField.getText();
            Settings.saveReplacement(project, replacementText);

            builder.getDialogWrapper().close(1);
        });

        builder.show();
    }
}
