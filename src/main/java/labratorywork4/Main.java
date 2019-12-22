package labratorywork4;

import labratorywork4.models.Album;
import labratorywork4.models.Composition;
import labratorywork4.models.Musician;
import labratorywork4.services.MusicShopService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        /* Задане 1 Используя Hibernate, создать и заполнить таблицы заново. Для этого нужно будет описать три
         вспомогательных класса и файл hibernate.cfg.xml. Маппинг объекта на таблицу реализовать с использованием
         аннотаций.*/

        MusicShopService musicShopService = new MusicShopService();
        Musician musician1 = new Musician("Kirkorov");
        Musician musician2 = new Musician("Sidorov");
        Musician musician3 = new Musician("Petrov");
        musicShopService.saveMusicians(musician1, musician2, musician3);

        Album album1 = new Album("rock", "Best Of The Best");
        Album album2 = new Album("pop", "First Album");
        Album album3 = new Album("reggae", "Second Album");
        musicShopService.saveAlbums(album1, album2, album3);

        Composition composition1 = new Composition("My song", 5, album1, musician1);
        Composition composition2 = new Composition("Luna", 6, album2, musician2);
        Composition composition3 = new Composition("Yesterday", 7, album3, musician3);
        musicShopService.saveCompositions(composition1, composition2, composition3);

        musicShopService.showMusiciansById(1, 2, 3);

        /*Задание 2 Продемонстрировать вывод данных, а также удаление и редактирование данных в любой из таблиц.*/
        composition2.setTitleOfComposition("New title");
        musicShopService.updateComposition(composition2);
        musicShopService.showCompositionById(1, 2, 3);

        musicShopService.deleteComposition(composition3);
    }
}