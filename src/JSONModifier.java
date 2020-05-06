import com.intellij.openapi.project.Project;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONModifier {


    static String getfileContent(Project project, int index) {
        File file = new File(Settings.getPath(project, index));
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            return null;
        }
    }

    static void saveFile(Project project, String data, int index) {
        File file = new File(Settings.getPath(project, index));
        try {
            FileUtils.write(file, data, "UTF-8");
        } catch (IOException ignored) {
        }
    }

    static void addTranslation(Project project, String key, String value, int index) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String fileContent = getfileContent(project, index);
        if(fileContent == null) {
            return;
        }

        Map jsonMap = gson.fromJson(fileContent, Map.class);

        if(jsonMap == null) {
            jsonMap = new HashMap();
        }

        jsonMap.put(key, value);



        String json = gson.toJson(jsonMap);

        saveFile(project, json, index);


    }
}
