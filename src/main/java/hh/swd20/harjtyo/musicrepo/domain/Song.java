package hh.swd20.harjtyo.musicrepo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Song {

    private String name, artist, album;

    //A subgenre can have multiple songs
    @ManyToOne
    @JsonManagedReference
    @JoinColumn
    private Subgenre subgenre;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn
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
