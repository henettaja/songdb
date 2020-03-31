package hh.swd20.harjtyo.musicrepo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {

    private String genreName;

    //A genre can have multiple subgenres
    @OneToMany
    private List<Subgenre> subgenres;

    //Automatically generating iterating id values
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long genreId;

    public Genre (String genreName) {
        this.genreName = genreName;
    }

    public Genre () {

    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Subgenre> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<Subgenre> subgenres) {
        this.subgenres = subgenres;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return genreName;
    }
}
