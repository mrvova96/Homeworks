package org.example.model;

import java.util.List;

/**
 * Класс-модель "Аудит пользователя"
 *
 * @param auditList Аудит в виде списка записей
 */
public record Audit(List<String> auditList) {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        auditList.forEach(message -> builder.append(message).append("\n"));
        return builder.toString();
    }
}
