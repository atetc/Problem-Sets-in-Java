package atetc.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import static atetc.helpers.Printer.println;

public class Trie {
    public static class TrieNode {
        public char character;
        public boolean isWord;
        public HashMap<Character, TrieNode> children;

        public TrieNode(char ch) {
            character = ch;
            isWord = false;
            children = new HashMap<Character, TrieNode>();
        }
        public TrieNode() {
            this('\0');
        }
    }

    public static void addWord(TrieNode n, String word) {
        if (word == null || word.isEmpty()) return;
        char[] characters = word.toCharArray();
        for (char ch: characters) {
            if (!n.children.containsKey(ch)) {
                n.children.put(ch, new TrieNode(ch));
            }
            n = n.children.get(ch);
        }
        n.isWord = true;
    }

    public static ArrayList<String> getAllWords(TrieNode n) {
        return getWords(n, "");
    }

    public static ArrayList<String> getWords(TrieNode n, String prefix) {
        if (prefix == null) return null;
        ArrayList<String> result = new ArrayList<String>();
        char[] characters = prefix.toCharArray();
        for (char ch: characters) {
            if (!n.children.containsKey(ch)) return result;
            n = n.children.get(ch);
        }
        getWords(n, prefix, result);
        return result;
    }

    private static void getWords(TrieNode n, String word, ArrayList<String> result) {
        if (n.isWord) result.add(word);
        for (char ch : n.children.keySet()) {
            getWords(n.children.get(ch), word + ch, result);
        }
    }

    //TEST----------------------------------
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        addWord(root, "dog");
        addWord(root, "dig");
        addWord(root, "");
        addWord(root, null);
        addWord(root, "dogman");
        addWord(root, "do");
        addWord(root, "apple");
        addWord(root, "application");
        addWord(root, "algorithm");
        println(getWords(root, "ap"));
        println(getWords(root, "d"));
        println(getWords(root, "none"));
        println(getAllWords(root));
    }
}
