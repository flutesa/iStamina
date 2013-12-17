import org.json.simple.*;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class LessonsJSON {

    public static String[] getLessonsNames() {
        String jsonText = read("src/lessons_en.json");
//        String jsonText = read("src/lessons_ru.json");

        JSONParser parser = new JSONParser();
        ContainerFactory containerFactory = new ContainerFactory(){
            public List creatArrayContainer() {
                return new LinkedList();
            }
            public Map createObjectContainer() {
                return new LinkedHashMap();
            }
        };

        ArrayList<String> ln = new ArrayList<String>();
        Map json = null;
        String key;
        try {
            json = (Map)parser.parse(jsonText, containerFactory);
        } catch (ParseException e) {
            e.printStackTrace();
        }
            Iterator iter = (json != null ? json.entrySet().iterator() : null);
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                key = (String)entry.getKey();
                if (key.equals("_comment")) {
                    continue;
                }
                ln.add(key);
            }

        return ln.toArray(new String[ln.size()]);
//        return (String[])(json != null ? json.keySet().toArray(new String[json.size()]) : new Object[0]);
    }


    public static String getLesson(String lName) {
        String jsonText = read("src/lessons_en.json");
//        String jsonText = read("src/lessons_ru.json");

        Object obj = JSONValue.parse(jsonText);
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