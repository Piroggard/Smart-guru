package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Course {
    UUID id;
    String name; // Название курса
    String url; // не обязательное поле
    UUID type; //Тип курса
    Long number_seats; // количество мест
    Long price; //Цена курса
    String photo_profile; // фото профиля
    String description; // Описание курса
    UUID direction_id; // Направление
    String duration; // продолжительность
    Long organizer_id; // ID организации
    Boolean certificate; //Сертификат
    String about_technology; //Технологии
    LocalDateTime date_create; //Дата создания курса
    LocalDateTime date_start_course; //Дата начала курса
    LocalDateTime date_finish_course; //Дата окончания курса
    Boolean delete; //Признак удаленности
    LocalDateTime date_delete;// Время удаления
    LocalDateTime date_update;// Время обновления
    UUID status_id;
    UUID adress_id;
}
