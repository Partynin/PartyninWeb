package labratorywork4;

import labratorywork4.models.Musician;
import labratorywork4.services.MusicShopService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        MusicShopService musicShopService = new MusicShopService();
        Musician musician = new Musician("Sis");
        musicShopService.saveMusician(musician);
    }
}