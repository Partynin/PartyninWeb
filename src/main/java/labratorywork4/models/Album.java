package labratorywork4.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int albumId;
    private String albumStyle;
    private String titleOfAlbum;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Composition> compositions;

    public Album() {

    }

    public Album(String album_style, String titleOfAlbum) {
        this.albumStyle = album_style;
        this.titleOfAlbum = titleOfAlbum;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumStyle() {
        return albumStyle;
    }

    public void setAlbumStyle(String albumStyle) {
        this.albumStyle = albumStyle;
    }

    public String getTitleOfAlbum() {
        return titleOfAlbum;
    }

    public void setTitleOfAlbum(String titleOfAlbum) {
        this.titleOfAlbum = titleOfAlbum;
    }

    @Override
    public String toString() {
        return "Album title: " + titleOfAlbum + "\nAlbum Id: " + albumId + "\nAlbum style: " + albumStyle;
    }
}
