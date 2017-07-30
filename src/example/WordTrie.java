package example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class WordTrie {

    private TrieNode root;
    StringBuilder words;

    public WordTrie() {
        root = new TrieNode();
        words = new StringBuilder();
    }

    public void add(String word) {
        TrieNode node = root;
        if (node == null || word == null) return;

        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            Set<Character> childs = node.getChildren().keySet();
            if (!childs.contains(chars[i])) insertChar(node, chars[i]);
            node.setNumberOfWords(node.getNumberOfWords()+1);
            node = getChild(node, chars[i]);
            if (i == chars.length - 1) {
                node.setIsWord(true); //для дубликатов - countWord++
            }
        }
        //node.setNumberOfWords(node.getNumberOfWords()+1);
    }

    public boolean addWord(TrieNode node, String word) {
        boolean wordAlreadyInTrie = true;
        Character c = word.charAt(0);
        if (!node.getChildren().containsKey(c)) {
            node.getChildren().put(c, new TrieNode());
        }
        TrieNode next = node.getChildren().get(c);
        if (word.length() == 1) {
            if (!next.isWord()) wordAlreadyInTrie = false;
            next.setIsWord(true);
            return wordAlreadyInTrie;
        }
        else {
            return addWord(next, word.substring(1));
        }
    }


    public TrieNode getChild(TrieNode node, Character c) {
        return node.getChildren().get(c);
    }

    private void insertChar(TrieNode node, Character c) {
        if (node.getChildren().containsKey(c)) return;
        TrieNode next = new TrieNode();
        node.getChildren().put(c, next);
    }

    public void printTrie(TrieNode node, String text) {
        if (node.isWord()) printWord(text);
        if (!node.getChildren().isEmpty()) {
            Set<Character> children = node.getChildren().keySet();
            for (Character c : children) {
                printTrie(node.getChildren().get(c), text + c);
            }
        }

    }

    private void printWord(String toAdd) {
        words.append("[");
        words.append(toAdd);
        words.append("],");
    }


    public static void main(String[] args) throws FileNotFoundException {


        WordTrie example = new WordTrie();
        String inString = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
        String[] inArr = inString.split("\\p{Blank}+");
        for (String s : inArr) {
            if (!example.addWord(example.root, s))
                        example.getChild(example.root,s.charAt(0)).incrNumberOfWords();
        }

        Set<Character> letters = example.root.getChildren().keySet();

        for (Character c : letters) {

            example.words.append(c);
            example.words.append(" = ");

            example.words.append(example.getChild(example.root, c).getNumberOfWords());

            example.printTrie(example.getChild(example.root, c), c.toString());

            System.out.println(example.words.toString());
            example.words.setLength(0);
        }
    }
}