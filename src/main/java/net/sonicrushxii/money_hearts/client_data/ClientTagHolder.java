package net.sonicrushxii.money_hearts.client_data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClientTagHolder {

    // A set to hold the tags for the client player
    private static final Set<String> CLIENT_TAGS = new HashSet<>();

    /**
     * Updates the tags on the client side.
     *
     * @param newTags The new set of tags received from the server.
     */
    public static void updateTags(Set<String> newTags) {
        synchronized (CLIENT_TAGS) {
            CLIENT_TAGS.clear();
            CLIENT_TAGS.addAll(newTags);
        }
    }

    /**
     * Checks if the client player has a specific tag.
     *
     * @param tag The tag to check for.
     * @return True if the tag exists, false otherwise.
     */
    public static boolean hasTag(String tag) {
        synchronized (CLIENT_TAGS) {
            return CLIENT_TAGS.contains(tag);
        }
    }

    /**
     * Gets all the tags for the client player.
     *
     * @return An unmodifiable view of the client's tags.
     */
    public static Set<String> getTags() {
        synchronized (CLIENT_TAGS) {
            return Collections.unmodifiableSet(new HashSet<>(CLIENT_TAGS));
        }
    }

    /**
     * Adds a tag to the client's tags. (For local testing or temporary usage)
     *
     * @param tag The tag to add.
     */
    public static void addTag(String tag) {
        synchronized (CLIENT_TAGS) {
            CLIENT_TAGS.add(tag);
        }
    }

    /**
     * Removes a tag from the client's tags. (For local testing or temporary usage)
     *
     * @param tag The tag to remove.
     */
    public static void removeTag(String tag) {
        synchronized (CLIENT_TAGS) {
            CLIENT_TAGS.remove(tag);
        }
    }
}
