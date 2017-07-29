package example;

import java.util.TreeMap;

public class TrieNode {
    private TreeMap<Character, TrieNode> children;
    private boolean isWord;
    private int numberOfWords;

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public void setChildren(TreeMap<Character, TrieNode> children) {
        this.children = children;
    }

    public void setWord(boolean word) {
        isWord = word;
    }



    /* если нужно учитывать дубликаты слов, то вместо
    boolean isWord использовать int countWord, со значениями
     0 - не конец слова, >0 - количество слов
    */
    public TrieNode() {
        children = new TreeMap();
        isWord = false;
        numberOfWords = 0;
    }

    public TreeMap<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setIsWord(boolean word) {
        isWord = word;
    }

}