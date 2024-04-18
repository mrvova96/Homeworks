package org.example.service;

import org.example.model.Audit;
import org.example.model.User;
import org.example.model.Workout;
import org.example.model.WorkoutType;
import org.example.output.AuditRepository;
import org.example.output.AuditRepositoryImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс отвечает за взаимодействие с репозиторием аудитов, а также за обработку логики, связанной с
 * аудитами пользователей
 */
public class AuditService {

    /**
     * Ссылка на репозиторий аудитов
     */
    private final AuditRepository auditRepository;

    public AuditService() {
        auditRepository = new AuditRepositoryImpl();
    }

    /**
     * Возвращает текущую дату с учетом времени, используется для сохранения записей аудита пользователей
     *
     * @return Текущая дата
     */
    private String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    /**
     * Сохраняет информацию о регистрации/авторизации в аудит
     *
     * @param user         Пользователь
     * @param isRegistered True, если пользователь впервые зарегистрировался, и False, если он авторизовался,
     *                     используется для сохранения записи об этом в аудит
     */
    public void addLoginInfoInAudit(User user, boolean isRegistered) {
        StringBuilder message = new StringBuilder();
        message.append(getCurrentDate()).append(" - ");
        if (isRegistered) {
            message.append("Регистрация в приложении");
        } else {
            message.append("Авторизация в приложении");
        }
        auditRepository.addMessageInAudit(user, message.toString());
    }

    /**
     * Сохраняет информацию о добавлении новой тренировки в аудит
     *
     * @param user    Пользователь
     * @param workout Тренировка
     */
    public void addAddingWorkoutInAudit(User user, Workout workout) {
        String message = getCurrentDate() + " - " + "Добавление новой тренировки типа \"" +
                workout.workoutType() + "\" на " + workout.date() + " число";
        auditRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию об удалении тренировки в аудит
     *
     * @param user    Пользователь
     * @param workout Тренировка
     */
    public void addRemovingWorkoutInAudit(User user, Workout workout) {
        String message = getCurrentDate() + " - " + "Удаление тренировки типа \"" +
                workout.workoutType() + "\" от " + workout.date() + " числа";
        auditRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о редактировании тренировки в аудит
     *
     * @param user    Пользователь
     * @param workout Тренировка
     */
    public void addUpdatingWorkoutInAudit(User user, Workout workout) {
        String message = getCurrentDate() + " - " + "Редактирование тренировки типа \"" +
                workout.workoutType() + "\" от " + workout.date() + " числа";
        auditRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о просмотре пользователем списка тренировок в аудит
     *
     * @param user Пользователь
     */
    public void addViewingWorkoutListInAudit(User user) {
        auditRepository.addMessageInAudit(user, getCurrentDate() + " - " + "Просмотр списка тренировок");
    }

    /**
     * Сохраняет информацию о просмотре пользователем списка типов тренировок в аудит
     *
     * @param user Пользователь
     */
    public void addViewingWorkoutTypeListInAudit(User user) {
        auditRepository.addMessageInAudit(user, getCurrentDate() + " - " + "Просмотр списка типов тренировок");
    }

    /**
     * Сохраняет информацию о добавлении нового типа тренировки в аудит
     *
     * @param user        Пользователь
     * @param workoutType Тип тренировки
     */
    public void addAddingWorkoutTypeInAudit(User user, WorkoutType workoutType) {
        String message = getCurrentDate() + " - " + "Добавление нового типа тренировки \"" +
                workoutType + "\"";
        auditRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о просмотре статистики по общему количеству тренировок указанного типа в аудит
     *
     * @param user        Пользователь
     * @param workoutType Тип тренировки
     */
    public void addViewingCountOfWorkoutsByTypeInAudit(User user, WorkoutType workoutType) {
        String message = getCurrentDate() + " - " +
                "Просмотр статистики по общему количеству тренировок типа \"" +
                workoutType + "\"";
        auditRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о просмотре статистики по среднему времени выполнения всех тренировок в аудит
     *
     * @param user Пользователь
     */
    public void addViewingAverageWorkoutsTimeInAudit(User user) {
        String message = getCurrentDate() + " - " +
                "Просмотр статистики по среднему времени выполнения всех тренировок";
        auditRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о просмотре статистики по общему количеству потраченных килокалорий в аудит
     *
     * @param user Пользователь
     */
    public void addViewingCountOfCaloriesInAudit(User user) {
        String message = getCurrentDate() + " - " +
                "Просмотр статистики по общему количеству потраченных килокалорий";
        auditRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о просмотре статистики по среднему количеству потраченных килокалорий в аудит
     *
     * @param user Пользователь
     */
    public void addViewingAverageCountOfCaloriesInAudit(User user) {
        String message = getCurrentDate() + " - " +
                "Просмотр статистики по количеству потраченных килокалорий за тренировку в среднем";
        auditRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о выходе пользователем из профиля в аудит
     *
     * @param user Пользователь
     */
    public void addLogoutInfoInAudit(User user) {
        auditRepository.addMessageInAudit(user, getCurrentDate() + " - " + "Выход из профиля");
    }

    /**
     * Сохраняет информацию о выходе пользователем из приложения в аудит
     *
     * @param user Пользователь
     */
    public void addExitInfoInAudit(User user) {
        auditRepository.addMessageInAudit(user, getCurrentDate() + " - " + "Выход из приложения");
    }

    /**
     * Возвращает аудит по указанному пользователю
     *
     * @param user Пользователь
     * @return Аудит пользователя
     */
    public Audit getAuditByUser(User user) {
        return auditRepository.getAuditByUser(user);
    }
}
