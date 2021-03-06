package edu.mit.rerun.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Model for a Filter.
 */
public class Filter {
    public static final String KEYWORD_DELIMITER = ",";
    private Set<String> keywords;
    private String filterName;
    private boolean used;

    public Filter(String filterName, boolean used) {
        this.filterName = filterName;
        keywords = new HashSet<String>();
        this.used = used;
    }

    public Filter(String filterName, boolean used, Set<String> keywords) {
        this.filterName = filterName;
        this.keywords = keywords;
        this.used = used;
    }

    public void addKeyWord(String keyword) {
        keywords.add(keyword);
    }

    public void removeKeyWord(String keyword) {
        if (keywords.contains(keyword)) {
            keywords.remove(keyword);
        }
    }

    // getters
    public String getFiltername() {
        return new String(filterName);
    }

    public List<String> getKeyWords() {
        List<String> words = new ArrayList<String>();
        for (String keyword : keywords) {
            words.add(keyword);
        }
        return words;
    }

    /**
     * 
     * @return keywords separated by comma
     */
    public String getKeyWordsString() {
        StringBuilder builder = new StringBuilder();
        List<String> keywords = getKeyWords();
        if (keywords.size() != 0) {
            for (int i = 0; i < keywords.size() - 1; i++) {
                builder.append(keywords.get(i) + KEYWORD_DELIMITER);
            }
            builder.append(keywords.get(keywords.size() - 1));
        }
        return builder.toString();
    }

    public int getUsedStatus() {
        return used ? 1 : 0;
    }

    // setters
    public void setUsed(boolean used) {
        this.used = used;
    }
}
