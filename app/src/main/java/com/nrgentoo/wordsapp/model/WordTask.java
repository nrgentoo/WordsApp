package com.nrgentoo.wordsapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Word task
 */
public class WordTask implements Serializable {

    public final long meaningId;
    public final String text;
    public final String translation;
    public final String definition;
    public final String example;
    public final String soundUrl;
    public final List<String> images;
    public final List<WordAlternative> alternatives;

    public WordTask(long meaningId, String text, String translation, String definition,
                    String example, String soundUrl, List<String> images,
                    List<WordAlternative> alternatives) {
        this.meaningId = meaningId;
        this.text = text;
        this.translation = translation;
        this.definition = definition;
        this.example = example;
        this.soundUrl = soundUrl;
        this.images = images;
        this.alternatives = alternatives;
    }
}
