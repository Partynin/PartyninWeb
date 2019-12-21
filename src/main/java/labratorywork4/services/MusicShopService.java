package labratorywork4.services;

import labratorywork4.dao.MusicShopDao;
import labratorywork4.models.Musician;

/** Service — слой данных в приложении, отвечающий за выполнение бизнес-логики.
 Если программа должна выполнить какую-то бизнес-логику — она делает это через сервисы. */

public class MusicShopService {

    private MusicShopDao musicShopDao = new MusicShopDao();

    public MusicShopService() {
    }

    public void saveMusician(Musician musician) {
        musicShopDao.saveMusician(musician);
    }
}
