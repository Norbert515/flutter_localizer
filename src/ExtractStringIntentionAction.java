// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import com.google.common.base.CaseFormat;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.newEditor.SettingsDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.lang.dart.DartElementType;
import com.jetbrains.lang.dart.ide.application.options.DartCodeGenerationPanel;
import com.jetbrains.lang.dart.ide.application.options.DartCodeStyleSettings;
import com.jetbrains.lang.dart.ide.application.options.DartfmtCodeStylePanel;
import com.jetbrains.lang.dart.ide.formatter.settings.DartLanguageCodeStyleSettingsProvider;
import com.jetbrains.lang.dart.ide.imports.DartImportOptimizer;
import com.jetbrains.lang.dart.psi.DartImportOrExportStatement;
import com.jetbrains.lang.dart.psi.DartStringLiteralExpression;
import com.jetbrains.lang.dart.psi.impl.DartStringLiteralExpressionImpl;
import com.jetbrains.lang.dart.util.DartElementGenerator;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

class ExtractStringIntentionAction implements IntentionAction {

    /*
    private final DartStringLiteralExpression myExpression;

    public ExtractStringIntentionAction(DartStringLiteralExpression expression) {
        this.myExpression = expression;
    }*/

    @NotNull
    @Override
    public String getText() {
        return "Localize String";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return "Localizers";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());
        if(element == null) {
            return false;
        }
        PsiElement parent = element.getParent();
        if(parent == null) {
            return false;
        }
        return parent instanceof DartStringLiteralExpression;
    }

    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws
            IncorrectOperationException {
        DialogBuilder builder = new DialogBuilder(project);
        PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());

        DartStringLiteralExpression parent = (DartStringLiteralExpression)element.getParent();

        String text = parent.getText().substring(1, parent.getTextLength() - 1);

        JComponent content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField keyTextField = new JTextField();
        content.add(keyTextField);
        keyTextField.setMaximumSize(new Dimension(Short.MAX_VALUE,
                56));
        keyTextField.requestFocusInWindow();

        JTextField valueTextField = new JTextField();
        valueTextField.setText(text);
        content.add(valueTextField);
        valueTextField.setMaximumSize(new Dimension(Short.MAX_VALUE,
                56));

        builder.setDimensionServiceKey("GrepConsoleTailFileDialog");
        builder.setTitle("Localize \"" + text + "\"");
        builder.centerPanel(content);
        builder.removeAllActions();
        builder.addOkAction();
        builder.addCancelAction();

        builder.setOkOperation(() -> {
            JSONModifier.addTranslation(project, keyTextField.getText(), valueTextField.getText(), 0);
            JSONModifier.addTranslation(project, keyTextField.getText(), valueTextField.getText(), 1);
            String camelCaseKey = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, keyTextField.getText());
            String toSave = Settings.getReplacementString(project).replace("$$", camelCaseKey);

            WriteCommandAction.runWriteCommandAction(project, () -> {
                parent.replace(DartElementGenerator.createExpressionFromText(project, toSave));
            });

            builder.getDialogWrapper().close(1);

        });


        builder.setPreferredFocusComponent(keyTextField);
        builder.show();
        builder.dispose();
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }

}