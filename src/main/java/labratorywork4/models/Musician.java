package labratorywork4.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "musicians")
public class Musician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int musicianId;
    private String nameMusician;

    @OneToMany(mappedBy = "musician", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Composition> compositions;

    public Musician() {

    }

    public Musician(String name_musician) {
        this.nameMusician = name_musician;
    }

    public int getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(int musicianId) {
        this.musicianId = musicianId;
    }

    public String getNameMusician() {
        return nameMusician;
    }

    public void setNameMusician(String nameMusician) {
        this.nameMusician = nameMusician;
    }

    @Override
    public String toString() {
        return "Musician name: " + nameMusician + "\nMusician id: " + musicianId;
    }
}
