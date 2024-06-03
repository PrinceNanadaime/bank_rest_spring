package com.example.bank.service;

import com.example.bank.model.entities.ClientStatus;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface ClientStatusService {
    /**
     * Создает нового статуса
     * @param clientStatus - статус для создания
     */
    void create(ClientStatus clientStatus);

    /**
     * Возвращает список всех имеющихся статусов, которые могут быть у клиентов
     * @return список статусов
     */
    List<ClientStatus> readAll();

    /**
     * Возвращает статус по его ID
     * @param id - ID статуса
     * @return - объект статуса с заданным ID
     */
    ClientStatus readById(long id);

    /**
     * Обновляет статус с заданным ID,
     * в соответствии с переданным статус
     * @param clientStatus - статус в соответсвии с которым нужно обновить данные
     * @param id - id статуса которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(ClientStatus clientStatus, long id);

    /**
     * Обновляет статус с заданным ID,
     * в соответствии с переданным статус
     * @param id - id статуса, которого нужно удалить
     * @param patch - json patch в соответсвии с которым нужно обновить данные
     * @return - true если данные были обновлены, иначе false
     */
    ClientStatus patch(Long id, JsonPatch patch);

    /**
     * Удаляет статус с заданным ID
     * @param id - id статуса, которого нужно удалить
     * @return - true если статус был удален, иначе false
     */
    void delete(long id);
}
