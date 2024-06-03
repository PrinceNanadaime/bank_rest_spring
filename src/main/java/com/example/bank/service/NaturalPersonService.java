package com.example.bank.service;

import com.example.bank.model.entities.ClientStatus;
import com.example.bank.model.entities.NaturalPerson;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface NaturalPersonService {
    /**
     * Создает нового клиента
     * @param naturalPerson - физлицо для создания
     */
    void create(NaturalPerson naturalPerson);

    /**
     * Возвращает список всех имеющихся физлиц, которые могут быть у клиентов
     * @return список физлиц
     */
    List<NaturalPerson> readAll();

    /**
     * Возвращает физлицо по его ID
     * @param id - ID физлица
     * @return - объект физлица с заданным ID
     */
    NaturalPerson readById(long id);

    /**
     * Обновляет статус с заданным ID,
     * в соответствии с переданным статус
     * @param naturalPerson - физлицо в соответсвии с которым нужно обновить данные
     * @param id - id физлицо которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(NaturalPerson naturalPerson, long id);

    /**
     * @param id
     * @param patch - json patch в соответсвии с которым нужно обновить данные
     * @return - обновлённый NaturalPerson
     */
    NaturalPerson patch(Long id, JsonPatch patch);

    /**
     * Удаляет физлицо с заданным ID
     * @param id - id физлицо, которого нужно удалить
     * @return - true если физлицо было удалено, иначе false
     */
    void delete(long id);
}
