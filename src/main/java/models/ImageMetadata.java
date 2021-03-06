package models;

import annotations.Author;
import models.model_utils.Dimension;
import org.json.JSONObject;

import java.io.Serial;
import java.io.Serializable;


/**
 * This class parses all the metadata from the fetched JSON file. Note that, metadata in this
 * context means all the tertiary information related to an image. Like, blur hash, width, height,
 * color profiles, creation date, modification date, etc.
 * Also, this class's {@link ImageMetadata#toJSON()} should be called first and other JSON
 * objects added on top of it.
 *
 * @see Serializable
 *
 * @author ConfusedRobo
 */
@Author(
        author = "ConfusedRobo",
        creation = "Monday, 19 July, 2021, 05:34:35 PM",
        profile = "https://github.com/heretickeymaker"
)
public class ImageMetadata implements Serializable {
    /**
     * The serial version number field that will assist the JVM to correctly
     * serialize and deserialize the object
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The {@code blur_hash} JSON field
     */
    public String blurhash;
    /**
     * The {@code color} JSON field
     */
    public String color;

    /**
     * The {@code created_at} JSON field
     */
    public ImageDateTime createdAt;
    /**
     * The {@code updated_at} JSON field
     */
    public ImageDateTime updatedAt;

    /**
     * The {@code width} and {@code height} fields which are packed into one class {@link Dimension}
     */
    public Dimension dimension;

    /**
     * This method packs all the parsed or, manually assigned data to unsplash.com API compliant
     * JSON fragment that can be used build up the whole JSON.
     *
     * @return a {@link JSONObject} object
     */
    public JSONObject toJSON() {
        var jsonBuilder = new JSONObject();
        jsonBuilder.put("blur_hash", this.blurhash);
        jsonBuilder.put("color", this.color);
        jsonBuilder.put("created_at", this.createdAt);
        jsonBuilder.put("updated_at", this.updatedAt);
        jsonBuilder.put("width", dimension.width);
        jsonBuilder.put("height", dimension.height);
        jsonBuilder.put("created_at", this.createdAt.date());
        jsonBuilder.put("updated_at", this.updatedAt.date());
        return jsonBuilder;
    }
}
