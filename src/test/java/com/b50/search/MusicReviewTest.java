package com.b50.search;

import com.b50.usat.load.MusicReview;
import junit.framework.TestCase;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 */
public class MusicReviewTest extends TestCase {

    public void testFromJSON() throws Exception {
        String jsonTxt = "{\"AlbumName\":\"The Ringmaster General\",\"ArtistName\":\"Dave Stewart\",\"ReleaseDate\":\"\",\"Rating\":\"3.5\",\"DownloadSongs\":\"I Got Love, A Different Man Now\",\"ConsiderSongs\":\"\",\"Reviewer\":\"Jerry Shriver\",\"ReviewDate\":\"9/4/2012 11:33:00 AM\",\"Brief\":\"<p>So what if the former Eurythmics mastermind is a bit vocally challenged? He's a terrific songwriter, guitarist and producer and surrounds himself with great players and vocalists such as Alison Krauss, Joss Stone, Jessie Baylin and Diane Birch. Stewart reveals just enough of the love-perplexed man behind the curtain to keep us entranced.<\\/p>\",\"WebUrl\":\"http://apidata.usatoday.com/life/music/reviews/story/2012-09-03/matchbox-twenty-mark-knopfler-dave-stewart-chick-corea-gary-burton/57558456/1?kjnd=%2BtDc2cXKl6TiKKJHBVNAxGuDzejiscR7uv2OppCu5lLIGmUAo7h%2BpNaFCXYHi7bq-8618ed05-6978-479f-9240-a949ea27ae94_7qhNKQMrjFKL4D3pOsCtxuZPJiENLLtNBLqAcPZlXj21lmejvloSaycUmJyG1tt4\",\"Id\":\"840141\"}";
        JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
        assertNotNull(json);

        MusicReview review = MusicReview.fromJSON(json);
        assertNotNull(review);
        assertEquals("The Ringmaster General", review.getAlbumName());
        assertEquals("Dave Stewart", review.getArtistName());
        assertNotNull(review.getBrief());
        assertNotNull(review.getId());
    }
}
