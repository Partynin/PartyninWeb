package labratorywork4.models;

import javax.persistence.*;

@Entity
@Table(name = "compositions")
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int compositionId;
    private String titleOfComposition;
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_album_id")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musician_musician_id")
    private Musician musician;

    public Composition() {

    }

    public Composition(String titleOfComposition, int duration) {
        this.titleOfComposition = titleOfComposition;
        this.duration = duration;
    }

    public int getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(int compositionId) {
        this.compositionId = compositionId;
    }

    public String getTitleOfComposition() {
        return titleOfComposition;
    }

    public void setTitleOfComposition(String titleOfComposition) {
        this.titleOfComposition = titleOfComposition;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    @Override
    public String toString() {
        return "Composition title: " + titleOfComposition + "\nDuration: " + duration + "\nComposition id: " + compositionId;
    }
}
