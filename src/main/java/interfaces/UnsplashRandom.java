package interfaces;

import annotations.Author;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.http.HttpRequest;

/**
 * This interface provides all the methods that has to be implemented in
 * order to function and connect to the API server of unsplash. Note that,
 * the methods that are defined here are the bare minimum you need to make
 * a functional API class. Without implementing this interface, your class
 * won't be allowed to connect to the API link.
 *
 * @author ConfusedRobo
 */
@Author(
        author = "ConfusedRobo",
        creation = "Monday, 19 July, 2021, 05:34:35 PM",
        profile = "https://github.com/heretickeymaker"
)
public interface UnsplashRandom extends Serializable {

    /**
     * The serial version number field that will assist the JVM to correctly
     * cast/parse the object
     */
    long serialVersionUID = 1L;

    /**
     * Prepared link for entering the API key, which is saved in an {@code .env}
     * file, which should be loaded on runtime and be replaced with the
     * {@code API_KEY} substring ath the end of the link.<br/>
     * <b>Example</b>: {@code API_LINK.replace("API_KEY", "29837523bn532jk")}
     */
    String API_LINK = "https://api.unsplash.com/photos/random/?client_id=API_KEY";

    /**
     * It is an extension of the {@code API LINK} prepared link field. Essentially,
     * this is also a prepared link that must be replaced first with {@code CATEGORY}
     * substring then should be appended to the {@code API_LINK} prepared string.<br/>
     * <b>Example</b>: {@code API_LINK + LINK_SUBPART_QUERY.replace("CATEGORY", "dogs")}
     */
    String LINK_SUBPART_QUERY = "&query=CATEGORY";

    /**
     * Should load the image to the JSON or, parse it to other format, preferably, XML.
     * Also note that this project uses {@link org.json} library, so it would be optimal
     * for you to use JSON and also have a field variable that stores all the parsed
     * JSON code.<br/>
     * <b>
     * Important: It is recommended for you to call this method first to avoid
     * unnecessary side effects. As, if the environment variable and image JSON
     * isn't loaded then the other methods will malfunction. And, most of the
     * methods rely on calling {@code init()} method first
     * </b>
     *
     * @return a boolean
     * @see JSONObject
     */
    boolean init();

    /**
     * This method should connect to the download link via {@link HttpRequest} and fetch all the bytes
     * and write it to a {@code JSON} file. And, if it's already cached in a field beforehand by the
     * {@link #init()} method then you just need to write that {@link JSONObject} to the file.
     * (recommended) then, write them to a json file.
     *
     * @return a boolean
     * @see JSONObject
     * @see HttpRequest
     */
    boolean toJSON();

    /**
     * A utility method that is not necessary, but we strongly recommend using it,
     * and so we made implementing this compulsory. This method should only fetch
     * the URL string from the JSON file and return it. Which in turn shortens the
     * code and makes it more readable.
     *
     * @return a string
     */
    String downloadLink();

    /**
     * This method fetches the download link from the {@code JSON} file via the
     * {@link UnsplashRandom#downloadLink()} method then applies it to {@link HttpRequest} then
     * parses the response from the server in bytes. and writes to a {@code jpg}
     * image file with a random name.<br/><br/>
     * <b>
     * Important: Choose the random element carefully or they might conflict and
     * be overwritten, also it is recommended to add the {@code id} from the image
     * JSON to the name of the image file.
     * </b>
     * Note: The download link should be available in the JSON that was fetched during
     * initialization.<br/>
     * Tip: pass the name to the {@link UnsplashRandom#toJPG(String filename)} as there's
     * no use of doing the same thing twice.
     *
     * @return a boolean
     * @see HttpRequest
     * @see JSONObject
     */
    boolean toJPG();

    /**
     * Same as the {@link UnsplashRandom#toJPG()}, just that now this method should be able to
     * assign a name to the {@code jpg} image file.<br/>
     * <b>
     * Important: Use an escaping mechanism that removes a custom path as, the save
     * paths are carefully mapped to a fixed file location
     * </b>
     *
     * @param filename the name of the file
     * @return a boolean
     */
    boolean toJPG(String filename);

    /**
     * Clears all the stored caches and makes the implementation reusable i.e., prime-able
     * for another image fetch.
     * <b>
     * Important: Don't forget to re-invoke the {@link UnsplashRandom#init()} method.
     * </b>
     */
    void reset();
}
