package com.timyarkov.peek.model.system;

import com.timyarkov.peek.model.items.Post;
import com.timyarkov.peek.model.items.PostDataType;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy facade with stripped down functionality to facilitate test data.
 */
public class DummyPeekSystem implements PeekSystem {
    // System State
    /**
     * Gets the system's error state. Null if no current error.
     * @return Current system error state.
     */
    public String getCurrentError() {
        return null;
    }

    // System Observation
    /**
     * Adds a system observer. Cannot be null.
     * @param o Observer to add.
     * @return Whether successful or not.
     */
    public boolean addObserver(PeekSystemObserver o) {
        return true;
    }

    /**
     * Removes a system observer.
     * @param o Observer to add.
     * @return Whether successful or not (i.e. whether an actual observer or not).
     */
    public boolean removeObserver(PeekSystemObserver o) {
        return true;
    }

    // System Functionality
    /**
     * Gets the amount of posts remaining for the day.
     * @return Amount of posts remaining for the day.
     */
    @Override
    public int getRemainingPosts() {
        return 99;
    }

    /**
     * Gets a random assortment of however many posts are available for the day
     * based on the set preferences.
     * @return Posts to display.
     */
    @Override
    public List<Post> getPosts() {
        List<Post> dummyData = new ArrayList<>();

        String dummyLongString = "To bigbig test long strings, I will now " +
                "copy over some text about koalas. I hope you enjoy reading it so" +
                "much that you forget that this is for testing...\n\n" +
                "[https://en.wikipedia.org/wiki/Koala]\n" +
                "The koala or, inaccurately, koala bear (Phascolarctos cinereus) is " +
                "an arboreal herbivorous marsupial native to Australia. It is the only " +
                "extant representative of the family Phascolarctidae and its closest " +
                "living relatives are the wombats. The koala is found in coastal areas " +
                "of the mainland's eastern and southern regions, inhabiting Queensland, " +
                "New South Wales, Victoria, and South Australia. It is easily recognisable " +
                "by its stout, tailless body and large head with round, fluffy ears and " +
                "large, spoon-shaped nose. The koala has a body length of 60–85 cm " +
                "(24–33 in) and weighs 4–15 kg (9–33 lb). Fur colour ranges from silver grey " +
                "to chocolate brown. Koalas from the northern populations are typically smaller " +
                "and lighter in colour than their counterparts further south. These populations " +
                "possibly are separate subspecies, but this is disputed.\n\n" +
                "Koalas typically inhabit open Eucalyptus woodland, as the leaves of these " +
                "trees make up most of their diet. Because this eucalypt diet has limited " +
                "nutritional and caloric content, koalas are largely sedentary and sleep up " +
                "to twenty hours a day. They are asocial animals, and bonding exists only " +
                "between mothers and dependent offspring. Adult males communicate with loud " +
                "bellows that intimidate rivals and attract mates. Males mark their presence " +
                "with secretions from scent glands located on their chests. Being marsupials, " +
                "koalas give birth to underdeveloped young that crawl into their mothers' " +
                "pouches, where they stay for the first six to seven months of their lives. " +
                "These young koalas, known as joeys, are fully weaned around a year old. " +
                "Koalas have few natural predators and parasites, but are threatened by " +
                "various pathogens, such as Chlamydiaceae bacteria and the koala retrovirus.\n\n" +
                "Because of its distinctive appearance, the koala is recognised worldwide as a " +
                "symbol of Australia. They were hunted by Indigenous Australians " +
                "and depicted in myths and cave art for millennia. The first recorded " +
                "encounter between a European and a koala was in 1798, and an image of " +
                "the animal was published in 1810 by naturalist George Perry. Botanist " +
                "Robert Brown wrote the first detailed scientific description of the koala " +
                "in 1814, although his work remained unpublished for 180 years. Popular " +
                "artist John Gould illustrated and described the koala, introducing the " +
                "species to the general British public. Further details about the " +
                "animal's biology were revealed in the 19th century by several English " +
                "scientists. Koalas are listed as a vulnerable species by the International " +
                "Union for Conservation of Nature. Among the many threats to their existence " +
                "are habitat destruction caused by agriculture, urbanisation, droughts and " +
                "associated bushfires, some related to climate change. In February of 2022, " +
                "the koala was officially listed as endangered in the Australian Capital " +
                "Territory, New South Wales and Queensland.";

        dummyData.add(new Post("Sample Text Post",
                                PostDataType.TEXT,
                                "Mega test text. Veryvery short!"));
        dummyData.add(new Post("Sample Super Long Text Post with a Very Long Title Too...",
                                PostDataType.TEXT,
                                dummyLongString));
        dummyData.add(new Post("Sample Image Post",
                                PostDataType.IMAGE,
                                "https://www.letuspublish.com/wp-content/uploads/2017/04/kitten-wallpaper-for-android.jpg"));
        dummyData.add(new Post("Sample GIF Post",
                                PostDataType.GIF,
                                "https://media4.giphy.com/media/9uwnYUDw342pq/giphy.gif"));

        return dummyData;
    }
}
