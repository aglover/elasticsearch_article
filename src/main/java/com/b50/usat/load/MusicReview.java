package com.b50.usat.load;

import io.searchbox.annotations.JestId;
import net.sf.json.JSONObject;

/**
 *
 */
public class MusicReview {
    private String albumName;
    private String artistName;
    private String rating;
    private String brief;
    private String reviewDate;
    private String url;

    @JestId
    private Long id;

    public static MusicReview fromJSON(JSONObject json) {
        return new MusicReview(
                json.getString("Id"),
                json.getString("AlbumName"),
                json.getString("ArtistName"),
                json.getString("Rating"),
                json.getString("Brief"),
                json.getString("ReviewDate"),
                json.getString("WebUrl")
        );
    }

    public MusicReview(String id, String albumName, String artistName, String rating, String brief, String reviewDate, String url) {
        this.id = Long.valueOf(id);
        this.albumName = albumName;
        this.artistName = artistName;
        this.rating = rating;
        this.brief = brief;
        this.reviewDate = reviewDate;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Long.valueOf(id);
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MusicReview is " +
                "albumName='" + albumName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", rating='" + rating + '\'' +
                ", brief='" + brief + '\'' +
                ", reviewDate='" + reviewDate + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id;
    }
}
