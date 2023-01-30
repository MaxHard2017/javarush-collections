
# Требования к 2028
## Часть 1
Сегодня реализуем свое дерево немного нестандартным способом(на базе AbstractList).
Вводную информацию можешь получить используя свой любимый поисковик и текст ниже.
Элементы дерева должны следовать так как показано на картинке:
Для начала сделаем наше дерево потомком класса AbstractList с параметром типа String, а также реализуем интерфейсы `Cloneable` и `Serializable`.
Реализацию методов `get(int index)` и `size()` пока оставь стандартной.
*Требования:*
- Класс CustomTree должен поддерживать интерфейс Cloneable.
- Класс CustomTree должен поддерживать интерфейс Serializable.
- Класс CustomTree должен быть потомком класса `AbstractList<String>`.

## Часть 2
Несмотря на то что наше дерево является потомком класса AbstractList, это не список в привычном понимании.
В частности нам недоступны принимающие в качестве параметра индекс элемента.
Такие методы необходимо переопределить и бросить новое исключение типа `UnsupportedOperationException`.
Вот их список:
`public String get(int index)`
`public String set(int index, String element)`
`public void add(int index, String element)`
`public String remove(int index)`
`public List<String> subList(int fromIndex, int toIndex)`
`protected void removeRange(int fromIndex, int toIndex)`
`public boolean addAll(int index, Collection<? extends String> c)`

*Требования:*
- При попытке вызвать метод `set(int index, String element)` должно возникать исключение типа UnsupportedOperationException.
- При попытке вызвать метод `get(int index)` должно возникать исключение типа UnsupportedOperationException.
- При попытке вызвать метод `add(int index, String element)` должно возникать исключение типа UnsupportedOperationException.
- При попытке вызвать метод `String remove(int index)` должно возникать исключение типа UnsupportedOperationException.
- При попытке вызвать метод `subList(int fromIndex, int toIndex)` должно возникать исключение типа UnsupportedOperationException.
- При попытке вызвать метод `removeRange(int fromIndex, int toIndex)` должно возникать исключение типа UnsupportedOperationException.
- При попытке вызвать метод `addAll(int index, Collection<? extends String> c)` должно возникать исключение типа UnsupportedOperationException.

## Часть 3
Класс описывающий дерево мы создали, теперь нужен класс описывающий тип элементов дерева:
1.  В классе `CustomTree` создай вложенный статический параметризированный класс `Entry<T>` с модификатором доступа по умолчанию.
2. Обеспечь поддержку этим классом интерфейса Serializable.
3. Создай такие поля (модификатор доступа по умолчанию):
- `String elementName`;
- boolean `availableToAddLeftChildren`, `availableToAddRightChildren`;
- `Entry<T> parent`, `leftChild`, `rightChild`;
- при необходимости, можешь создавать и другие поля;
4. Реализуй публичный конструктор с одним параметром типа String:
- установи поле `elementName` равным полученному параметру;
- установи поле `availableToAddLeftChildren` равным true;
- установи поле `availableToAddRightChildren` равным true;
5. Реализуй публичный метод boolean `isAvailableToAddChildren`, возвращающий дизъюнкцию полей `availableToAddLeftChildren` и `availableToAddRightChildren`.

*Требования:*
- Класс `CustomTree.Entry` должен быть объявлен с модификатором доступа по умолчанию.
- Класс `CustomTree.Entry` должен поддерживать интерфейс `Serializable`.
- В классе `CustomTree.Entry` должно существовать поле `elementName` типа `String`.
- В классе `CustomTree.Entry` должно существовать поле `availableToAddLeftChildren` типа `boolean`.
- В классе `CustomTree.Entry` должно существовать поле `availableToAddRightChildren` типа `boolean`.
- В классе `CustomTree.Entry` должно существовать поле `parent` типа `Entry`.
- В классе `CustomTree.Entry` должно существовать поле `leftChild` типа `Entry`.
- В классе `CustomTree.Entry` должно существовать поле `rightChild` типа `Entry`.
- В классе `CustomTree.Entry` должен быть корректно реализован конструктор с одним параметром типа `String` (смотри условие).
- В классе `CustomTree.Entry` должен корректно реализован метод `isAvailableToAddChildren` (смотри условие).

## Часть 4
Любое дерево начинается с корня, поэтому не забудь в класс `CustomTree` добавить поле root типа `Entry<String>` c модификатором доступа по умолчанию.
Инициируй его в конструкторе `CustomTree`, имя (elementName) не важно.
Итак, основа дерева создана, пора тебе поработать немного самому.
Вспомним как должно выглядеть наше дерево.
Элементы дерева должны следовать так как показано на картинке:[tree](tree.jpeg)
Необходимо написать методы, которые бы позволили создать такую структуру дерева и проводить операции над ней.

Необходимо:
1. переопределить метод `add(String s)` - добавляет элементы дерева, в качестве параметра принимает имя элемента (`elementName`), искать место для вставки начинаем слева направо.
1. переопределить метод `size()` - возвращает текущее количество элементов в дереве.
1. реализовать метод `getParent(String s)` - возвращает имя родителя элемента дерева, имя которого было полученного в качестве параметра.

Если необходимо, можешь вводить дополнительные методы и поля, не указанные в задании.
*Требования:*
- В классе `CustomTree` должно существовать поле root типа `Entry`.
- В классе `CustomTree` должны быть переопределены методы `add(String s)` и `size()`.
- После добавления N элементов в дерево с помощью метода add, метод size должен возвращать `N`.
- Метод getParent должен возвращать имя родителя для любого элемента дерева.