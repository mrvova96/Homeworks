package org.example.output;

import org.example.model.Audit;
import org.example.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс-репозиторий, имитирующий запросы в таблицу "Аудиты"
 */
public class AuditRepositoryImpl implements AuditRepository {

    /**
     * Коллекция для хранения аудита пользователей
     */
    private final Map<User, Audit> auditMap;

    public AuditRepositoryImpl() {
        auditMap = new HashMap<>();
    }

    /**
     * Добавляет новое сообщение в аудит для указанного пользователя
     *
     * @param user    Пользователь
     * @param message Сообщение о некоторой активности
     */
    @Override
    public void addMessageInAudit(User user, String message) {
        if (!auditMap.containsKey(user)) {
            auditMap.put(user, new Audit(new ArrayList<>()));
        }
        auditMap.get(user).auditList().add(message);
    }

    /**
     * Возвращает аудит по указанному пользователю
     *
     * @param user Пользователь
     * @return Аудит действий пользователя
     */
    @Override
    public Audit getAuditByUser(User user) {
        return auditMap.get(user);
    }
}
