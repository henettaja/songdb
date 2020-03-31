package hh.swd20.harjtyo.musicrepo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Subgenre {

    private String subgenreName;

    //A genre can have multiple subgenres
    @ManyToOne
    private Genre mainGenre;

    //One subgenre can have multiple songs
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subgenre")
    private List<Song> subgenreSongs;

    //Automatically generating iterating id values
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subgenreId;

    public Subgenre (String subgenreName, Genre mainGenre) {
        this.subgenreName = subgenreName;
        this.mainGenre = mainGenre;
    }

    public Subgenre () {

    }

    public String getSubgenreName() {
        return subgenreName;
    }

    public void setSubgenreName(String subgenreName) {
        this.subgenreName = subgenreName;
    }

    public List<Song> getSubgenreSongs() {
        return subgenreSongs;
    }

    public void setSubgenreSongs(List<Song> subgenreSongs) {
        this.subgenreSongs = subgenreSongs;
    }

    public Long getSubgenreId() {
        return subgenreId;
    }

    public void setSubgenreId(Long subgenreId) {
        this.subgenreId = subgenreId;
    }

    public Genre getMainGenre() {
        return mainGenre;
    }

    public void setMainGenre(Genre mainGenre) {
        this.mainGenre = mainGenre;
    }

    @Override
    public String toString() {
        return subgenreName;
    }
}
