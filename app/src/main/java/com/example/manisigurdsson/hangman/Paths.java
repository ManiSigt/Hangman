package com.example.manisigurdsson.hangman;



public class Paths {
    private String words_english;
    private String transl_url;
    private String random_url = "http://api.wordnik.com:80/v4/words.json/randomWords?hasDictionaryDef=true&minCorpusCount=0&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=-1&minLength=5&maxLength=10&sortBy=count&sortOrder=asc&limit=20&api_key=6e495aa4345325749497a17c9fb0b55b6a52a540c4a31422f";

    public String getWords_english() {
        return words_english;
    }

    public  String setTransl_url(String words_english) {
        transl_url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20171205T141245Z.709b144b45763084.686d7bb53df800a4fd509d8268db5eb1e5b48594&text="+ words_english +"&lang=en-is";
        return transl_url;
    }

    public String getTransl_url() {
        return transl_url;
    }


    public String getRandom_url() {
        return random_url;
    }
}
