## VKFriends
### Description
VKFriends - GUI приложение, написанное на Java, позволяющее пользователя визуализировать связи между разными аккаунтами вконтакте. Программа строит граф, вершины которого - разные ВК аккаунты, рёбро - наличие/отсутствие общих друзей, вес этого ребра - количество общих друзей. 

### Data structure specification
В основе графа - список смежности. Также, есть возможность построить максимальное остовное дерево из этого графа с помощью алгоритма Краскала, если граф связный и взвешенный. 

### I/O data
 * Input data - пользователь вводит id/url страницы ВК в специальную строку ввода, добавляя вершину.

 * Output data - пользователь получает на экране граф, в котором вершинами являются страницы в ВК, а ребра - наличие общих друзей. А так же пользователь может получить минимальную информацию о пользователе в графе. Полученный граф - взвешенный (количество общих друзей), возможен несвязный граф, или остов, в зависимости от запроса пользователя.

### Features

- [ ] Графический интерфейс</br>
Примерный вид GUI представлен ниже (возможны изменения).

Базовый gui состоит из кнопок добавления/удаления вершины, удаления всего графа, графического поля для графа и поля/окна для вывода информации об аккаунте.
![](https://github.com/DarkFlink/VKFriends/blob/0f2055c5655ee06465b7ad3bb0e8414f61357718/docs/Images/SimpleDemoGUI.png?raw=true)

- [ ] Строит граф, у которого вершины - ВК аккаунты, ребра - общие друзья, есть они или нет.</br>
*Будет создан демо скриншот*

- [ ] Удаление и добавление вершины в графе.Пользователь может ввести айди/ссылку на аккаунт и добавить/удалить его из графа.</br>
*Будет создан демо скриншот*

- [ ] Минимальная информация о пользователе с помощью нажатия на вершину графа.</br>
*Будет создан демо скриншот* 

- [ ] Алгоритм, создающий минимальное остовное дерево из этого графа.</br>
*Будет создан демо скриншот* 

### Tests
Инструкции по тестированию

### Usage
Здесь будут приведены инструкции для запуска и использования VKFriends.

### Development plan
 1) Создать базовый gui с кпопками добавления/удаления вершины, удаления всего графа, графическое поле для графа и поле/окно для вывода информации об аккаунте.
 
 2) Добавить возможность строить граф, где вершина - аккаунт, а ребро - кол-во общ. Друзей.
 
 3) Добавить возможность добавлять/удалять новые вершины.
 
 4) Реализовать базовый набор unit тестов.
 
 5) Свести все наработки в один проект (реализовать взаимодействие между частями проекта).
 
 6) Получить новую версию приложения, которое умеет:

    * Выводить некоторую информацию об аккаунте по нажатию на вершину.
    
    * Строить минимальное/максимальное остовное дерево на основе базового графа.
    
    
### For contributors
* Разработка программы на разных ветках
* Новая ветка должна быть создана как: <_issue_id_>_имя ветки.
* создание пул реквеста с наименованием РВП: (Работа в прогрессе) если работа не выполнена.
* Не заливать в мастер.
* Одна проблема - один пул реквест - одна ветка.
* Пул реквест: <_issue_id_>_имя пул реквеста.
* Все пул реквесты должны иметь комментарии.

### Developers
1. [Yaroslav Gosudarkin](https://github.com/DarkFlink) - ответственный за алгоритмы.
2. [Gavrilov Andrew](https://github.com/AndrewGavril) - ответственный за графический интерфейс.
3. [Gizzatov Amir](https://github.com/Gizzatovamir) - ответственный за юнит тестирование и отчёт.
