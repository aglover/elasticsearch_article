package com.b50.usat.load;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import io.searchbox.core.Index;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 */
public class MusicReviewLoad {

    public static void main(String[] args) throws Exception {

        ClientConfig clientConfig = new ClientConfig();
        Set<String> servers = new LinkedHashSet<String>();
        servers.add("http://localhost:9200");
        clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers);

        JestClientFactory factory = new JestClientFactory();
        factory.setClientConfig(clientConfig);
        JestClient client = factory.getObject();


        URL url = new URL("http://api.usatoday.com/open/reviews/music/recent?count=300&api_key=a9efy36q545zhsckjpu8mmzy");
        InputStream urlInputStream = url.openConnection().getInputStream();
        String jsonTxt = IOUtils.toString(urlInputStream);
        JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
        JSONArray reviews = (JSONArray) json.getJSONArray("MusicReviews");
        for (Object jsonReview : reviews) {
            MusicReview review = MusicReview.fromJSON((JSONObject) jsonReview);
            Index index = new Index.Builder(review).index("music_reviews").type("review").build();
            System.out.println("loading review: " + review);
            client.execute(index);
        }
    }
}
