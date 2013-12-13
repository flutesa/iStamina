import org.json.simple.*;
import java.util.ArrayList;
import java.io.*;


public class LessonsJSON {

    public static String[] getLessonsNames() {
        String json = read("src/lessons_en.json");

        Object obj = JSONValue.parse(json);
        JSONObject jsonObj = (JSONObject) obj;

        ArrayList ln = (ArrayList)jsonObj.get("lessons names");


        return (String[])ln.toArray(new String[ln.size()]);
    }


    public static String getLesson(String lName) {
        String json = read("src/lessons_en.json");

        Object obj = JSONValue.parse(json);
        JSONObject jsonObj = (JSONObject) obj;

        return (String)jsonObj.get(lName);
    }


    public static String read(String fileName) { // читаем JSON/TXT данные из файла
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(
                    fileName).getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }



}