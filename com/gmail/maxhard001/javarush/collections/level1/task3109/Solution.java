package com.gmail.maxhard001.javarush.collections.level1.task3109;
/* 
 * Реализовать метод getProperties, который должен считывать свойства из переданного файла fileName.
fileName может иметь любое расширение - как xml, так и любое другое, или вообще не иметь.
Нужно обеспечить корректное чтение свойств.
При возникновении ошибок должен возвращаться пустой объект.
Метод main не участвует в тестировании.

Подсказка: возможно тебе понадобится File.separator.

Требования:
•	Класс Solution должен содержать метод Properties getProperties(String fileName).
•	Метод getProperties должен корректно считывать свойства из xml-файла.
•	Метод getProperties должен корректно считывать свойства из любого другого файла с любым расширением.
•	Метод getProperties должен возвращать пустой объект, если во время чтения свойств возникла ошибка.
*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        // Properties properties = solution.getProperties(
        //     "C:\\Users\\Kot\\Projects\\Java\\Javarush\\javarush-collections" + 
        //     "\\src\\com\\gmail\\maxhard001\\javarush\\collections\\level1\\task3109\\properties1");

        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties p = new Properties();
        try {
            // p.load(new FileReader(fileName));
            p.loadFromXML(new FileInputStream(fileName));

        } catch (IOException exc) {
            try ( Reader fr = new FileReader(fileName) )
            {
                p.load(fr);
            } catch (Exception e) {
                p.put("empty", e.getMessage());
                // TODO: handle exception
            }
        }
        return p;
    }
}
