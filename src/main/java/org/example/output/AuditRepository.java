package org.example.output;

import org.example.model.Audit;
import org.example.model.User;

/**
 * Шаблон для репозитория аудитов
 */
public interface AuditRepository {

    /**
     * Добавляет новое сообщение в аудит для указанного пользователя
     *
     * @param user    Пользователь
     * @param message Сообщение о некоторой активности
     */
    void addMessageInAudit(User user, String message);

    /**
     * Возвращает аудит по указанному пользователю
     *
     * @param user Пользователь
     * @return Аудит действий пользователя
     */
    Audit getAuditByUser(User user);
}
