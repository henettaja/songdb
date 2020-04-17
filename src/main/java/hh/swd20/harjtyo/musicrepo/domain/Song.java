package hh.swd20.harjtyo.musicrepo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Song {

    @NotNull(message = "Song title cannot be empty")
    @Size(min = 1, max = 50, message = "A song title must be between 1 and 50 characters long")
    private String name;

    @NotNull(message = "Song's artist field cannot be empty")
    @Size(min = 1, max = 30, message = "Artists name must be between 1 and 30 characters long")
    private String artist;

    @NotNull(message = "Album's name cannot be empty")
    @Size(min = 1, max = 50, message = "Album's name must be between 1 and 50 characters long")
    private String album;

    //A subgenre can have multiple songs
    @ManyToOne
    @JsonManagedReference
    @JoinColumn
    private Subgenre subgenre;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn
    @NotNull (message = "Song must have a genre")
    private Genre genre;

    //Automatically generating iterating id values
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long songId;

    public Song (String name, String artist, String album, Subgenre subgenre) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.subgenre = subgenre;
        this.genre = subgenre.getMainGenre();
    }

    public Song (String name, String artist, String album, Genre genre) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Song () {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Subgenre getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(Subgenre subgenre) {
        this.subgenre = subgenre;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }
}
