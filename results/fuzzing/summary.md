## Тестовое покрытие

Тестовое покрытие проводилось средствами IntelliJ Idea, затем сохранялось в виде `html`-страницы  и открывалось в браузере.

## Обычные тесты

Перед тем, как посмотреть на покрытие fuzzing-тестов, посмотрим на результаты покрытия обычных тестов

#### lesson5

![](D:\Programming\Kotlin\Idea Projects\KotlinAsFirst2021\results\fuzzing\coverageWithoutFuzzing\lesson5\results.png)

#### lesson6

![](D:\Programming\Kotlin\Idea Projects\KotlinAsFirst2021\results\fuzzing\coverageWithoutFuzzing\lesson6\results.png)

#### lesson7

![image-20220424110029526](C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20220424110029526.png)

## Fuzzing

Теперь посмотрим на результаты fuzzing-тестов

#### lesson5

![image-20220424110207056](C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20220424110207056.png)

#### lesson6

![image-20220424110238125](C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20220424110238125.png)

#### lesson7

![image-20220424110300945](C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20220424110300945.png)

## Сравнение и выводы

Видим, что покрытие` lesson5` и `lesson7` совпадает. Посмотрим на покрытие строк кода.

#### lesson5

![](D:\Programming\Kotlin\Idea Projects\KotlinAsFirst2021\results\fuzzing\lines_lesson5.png)

#### lesson7

![](D:\Programming\Kotlin\Idea Projects\KotlinAsFirst2021\results\fuzzing\lines_lesson7.png)

Видим, что и при использовании обычных тестов и при использовании fuzzing-тестов удалось покрыть весь код тестируемых методов

Теперь рассмотри результаты` lesson6`. Видно, что при использовании fuzzing-тестов удалось покрыть на 2 строки кода меньше. Рассмотрим результаты детальней.

#### lesson6

![](D:\Programming\Kotlin\Idea Projects\KotlinAsFirst2021\results\fuzzing\lines_lesson6.png)

Видим, что в результате Fuzzing-тестов не удалось получить `NumberFormatException` в результате выполнения `bestLongJump()`. Скорее всего, это произошло потому, что в самом fuzzing-тесте при генерации строки не сгенерировались определенные краевые случаи, которые могли бы выкинуть `NumberFormatException`.

