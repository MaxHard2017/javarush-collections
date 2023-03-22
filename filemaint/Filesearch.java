package filemaint;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filesearch {
    public static void main(String[] args) {
        
        Map<String, Path> fileInfo = new HashMap<>();      // содержит список всех вайлов без дублей
        Map<String, List<Path>> fileDubles = new HashMap<>(); // содержит пути всех дублей

        String pathName;
        Path path;
        String key;
        Path dublesPath;    // путь к прудыдущему дублю файла
        
        for (int i = 0; i < args.length; i++) {
           
            pathName  = args[i];
            path = Paths.get(pathName);
            key = args[++i];

            // сохраняем файл в списке всех файлов (он без дублей)
            dublesPath = fileInfo.put(key, path);       // выдаст предущий задублированный путь если такой key уже есть. перезаписываем новый путь

            if (dublesPath != null){                       // dublesPath не null если ключ повторился
                if (fileDubles.containsKey(key)) {         // добавляем путь в список дублированных
                    fileDubles.get(key).add(path);
                } else {
                    fileDubles.put(key, Arrays.asList(path, dublesPath));   // добавляем оба пути если это первый дубль
                }    
            }
        } 


        for (Map.Entry<String, List<Path>> entry : fileDubles.entrySet()) {

            for (Path dublicate  : entry.getValue()) {
                System.out.println(dublicate.toString());
            }
        }
    }
}