import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;

public class Settings {

    static String getPath(Project project, int index) {
        return PropertiesComponent.getInstance(project).getValue("flutter_localizer_json_path_" + index);
    }

    static void savePath(Project project, String path, int index) {
        PropertiesComponent.getInstance(project).setValue("flutter_localizer_json_path_" + index, path);
    }

    static String getReplacementString(Project project) {
        return PropertiesComponent.getInstance(project).getValue("flutter_localizer_replacement_string");
    }

    static void saveReplacement(Project project, String string) {
        PropertiesComponent.getInstance(project).setValue("flutter_localizer_replacement_string", string);
    }
}
