package labratorywork4.services;

import labratorywork4.dao.MusicShopDao;
import labratorywork4.models.Album;
import labratorywork4.models.Composition;
import labratorywork4.models.Musician;

/** Service — слой данных в приложении, отвечающий за выполнение бизнес-логики.
 Если программа должна выполнить какую-то бизнес-логику — она делает это через сервисы. */

public class MusicShopService {

    private MusicShopDao musicShopDao = new MusicShopDao();

    public MusicShopService() {
    }

    public void saveMusicians(Musician... musicians) {
        for (int i = 0; i < musicians.length; i++) {
            musicShopDao.saveMusician(musicians[i]);
        }
    }

    public void saveAlbums(Album... albums) {
        for (int i = 0; i < albums.length; i++) {
            musicShopDao.saveAlbum(albums[i]);
        }
    }

    public void saveCompositions(Composition... compositions) {
        for (int i = 0; i < compositions.length; i++) {
            musicShopDao.saveComposition(compositions[i]);
        }
    }

    public void showMusiciansById(int... id) {
        for (int i = 0; i < id.length; i++) {
            System.out.println(musicShopDao.findMusicianById(id[i]));
        }
    }

    public void updateComposition(Composition composition) {
        musicShopDao.updateComposition(composition);
    }

    public void deleteComposition(Composition composition) {
        musicShopDao.deleteComposition(composition);
    }

    public void showCompositionById(int... id) {
        for (int i = 0; i < id.length; i++) {
            System.out.println(musicShopDao.findCompositionById(id[i]));
        }
    }
}
