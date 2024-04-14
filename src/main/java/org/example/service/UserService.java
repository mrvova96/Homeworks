package org.example.service;

import org.example.model.User;
import org.example.model.Workout;
import org.example.model.WorkoutType;
import org.example.output.UserRepository;
import org.example.output.UserRepositoryImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Класс отвечает за взаимодействие с репозиторием пользователей, а также за обработку логики, связанной с
 * пользователями
 */
public class UserService {

    /**
     * Ссылка на репозиторий пользователей
     */
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepositoryImpl();
    }

    /**
     * Проверяет корректность введенных данных при авторизации пользователя
     *
     * @param login    Логин пользователя
     * @param password Пароль пользователя
     * @return True, если пользователь находится в базе, иначе False
     */
    public boolean isAuthorizationDataCorrect(String login, String password) {
        User user = getUser(login);
        if (user == null) {
            return false;
        } else {
            return user.getPassword().equals(password);
        }
    }

    /**
     * Проверяет доступность логина по базе пользователей
     *
     * @param login Логин пользователя
     * @return True, если выбранный логин свободен, иначе False
     */
    public boolean isLoginAvailable(String login) {
        return getUser(login) == null;
    }

    /**
     * Добавляет нового пользователя через репозиторий
     *
     * @param user Пользователь
     */
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    /**
     * Возвращает пользователя по его логину через репозиторий
     *
     * @param login Логин пользователя
     * @return Пользователь
     */
    public User getUser(String login) {
        return userRepository.getUser(login);
    }

    /**
     * Возвращает список пользователей через репозиторий
     *
     * @return Список пользователей
     */
    public Map<String, User> getUserMap() {
        return userRepository.getUserMap();
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
        userRepository.addMessageInAudit(user, message.toString());
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
        userRepository.addMessageInAudit(user, message);
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
        userRepository.addMessageInAudit(user, message);
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
        userRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о просмотре пользователем списка тренировок в аудит
     *
     * @param user Пользователь
     */
    public void addViewingWorkoutListInAudit(User user) {
        userRepository.addMessageInAudit(user, getCurrentDate() + " - " + "Просмотр списка тренировок");
    }

    /**
     * Сохраняет информацию о просмотре пользователем списка типов тренировок в аудит
     *
     * @param user Пользователь
     */
    public void addViewingWorkoutTypeListInAudit(User user) {
        userRepository.addMessageInAudit(user, getCurrentDate() + " - " + "Просмотр списка типов тренировок");
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
        userRepository.addMessageInAudit(user, message);
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
        userRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о просмотре статистики по среднему времени выполнения всех тренировок в аудит
     *
     * @param user Пользователь
     */
    public void addViewingAverageWorkoutsTimeInAudit(User user) {
        String message = getCurrentDate() + " - " +
                "Просмотр статистики по среднему времени выполнения всех тренировок";
        userRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о просмотре статистики по общему количеству потраченных килокалорий в аудит
     *
     * @param user Пользователь
     */
    public void addViewingCountOfCaloriesInAudit(User user) {
        String message = getCurrentDate() + " - " +
                "Просмотр статистики по общему количеству потраченных килокалорий";
        userRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о просмотре статистики по среднему количеству потраченных килокалорий в аудит
     *
     * @param user Пользователь
     */
    public void addViewingAverageCountOfCaloriesInAudit(User user) {
        String message = getCurrentDate() + " - " +
                "Просмотр статистики по количеству потраченных килокалорий за тренировку в среднем";
        userRepository.addMessageInAudit(user, message);
    }

    /**
     * Сохраняет информацию о выходе пользователем из профиля в аудит
     *
     * @param user Пользователь
     */
    public void addLogoutInfoInAudit(User user) {
        userRepository.addMessageInAudit(user, getCurrentDate() + " - " + "Выход из профиля");
    }

    /**
     * Сохраняет информацию о выходе пользователем из приложения в аудит
     *
     * @param user Пользователь
     */
    public void addExitInfoInAudit(User user) {
        userRepository.addMessageInAudit(user, getCurrentDate() + " - " + "Выход из приложения");
    }

    public List<String> getAuditListByUser(User user) {
        return userRepository.getAuditListByUser(user);
    }
}
