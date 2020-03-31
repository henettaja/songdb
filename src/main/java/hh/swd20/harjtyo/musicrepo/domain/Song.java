package hh.swd20.harjtyo.musicrepo.domain;

import javax.persistence.*;

@Entity
public class Song {

    private String name, artist, album;

    //A subgenre can have multiple songs
    @ManyToOne
    private Subgenre subgenre;

    //Automatically generating iterating id values
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long songId;

    public Song (String name, String artist, String album, Subgenre subgenre) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.subgenre = subgenre;
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

    public Long getId() {
        return songId;
    }

    public void setId(Long songId) {
        this.songId = songId;
    }
}
