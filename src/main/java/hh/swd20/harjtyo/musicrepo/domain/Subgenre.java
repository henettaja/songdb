package hh.swd20.harjtyo.musicrepo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Subgenre {

    @NotNull (message = "Subgenre name cannot be empty")
    @Size(min = 1, max = 30, message = "Subgenre's name must be between 1 and 30 characters long")
    private String subgenreName;

    //A genre can have multiple subgenres
    @ManyToOne
    @JsonManagedReference
    @JoinColumn
    private Genre mainGenre;

    //One subgenre can have multiple songs
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subgenre")
    @JsonBackReference
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
