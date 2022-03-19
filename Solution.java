package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //создаем объект FileInputStream, привязанный к файлу
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        FileInputStream inputStream = new FileInputStream(filename);

        Map <Integer, Integer> map = new HashMap<>(); // мэп   "байт - количество повторений"

        while (inputStream.available() > 0) //пока остались непрочитанные байты
        {
            int data = inputStream.read(); //прочитать очередной байт
            if (map.containsKey(data)) { // если уже есть такой байт в мэп, то инкрементируем value (кол-во повторений)
                int newValue = map.get(data) + 1;
                map.put(data, newValue);
            } else {
                map.put(data, 1); // иначе вносим как первое повторение
            }
        }
        inputStream.close(); // закрываем поток

        // ************************************************************
        // нужно найти в мапе строчки с минимальным кол-вом повторов (то есть минимальное value)
        // и вывести key у этих строк
        List <Integer > list = new ArrayList<>();
        list.addAll(map.values()); // берем все значения
        Collections.sort(list); // и сортируем по возрастанию

        Integer min = list.get(0); // нулевой элемент - минимальный

        Set<Map.Entry<Integer, Integer>> entrySet=map.entrySet();
        for (Map.Entry<Integer, Integer> pair : entrySet) {
            if (min.equals(pair.getValue())) {
                System.out.print(pair.getKey() + " "); // нашли наше значение и выводим ключ
            }
        }
    }
}
