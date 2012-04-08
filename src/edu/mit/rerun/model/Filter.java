package edu.mit.rerun.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Model for a Filter.
 */
public class Filter {
    private Set<String> keywords;
    private String filterName;
    
    public Filter(String filterName) {
        this.filterName = filterName;
        keywords = new HashSet<String>();
    }
    
    public void addKeyWord(String keyword) {
        keywords.add(keyword);
    }
    
    public void removeKeyWord(String keyword) {
        if (keywords.contains(keyword)) {
            keywords.remove(keyword);
        }
    }
    
    //getters
    public String getFiltername() {
        return new String(filterName);
    }
    
    public List<String> getKeyWords() {
        List<String> words = new ArrayList<String>();
        for (String keyword: keywords) {
            words.add(keyword);
        }
        return words;
    }
}
