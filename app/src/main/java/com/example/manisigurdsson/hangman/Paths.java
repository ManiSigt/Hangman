package com.example.manisigurdsson.hangman;



public class Paths {
    private Paths(){};
    private static String tr_url; /*= "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20171205T141245Z.709b144b45763084.686d7bb53df800a4fd509d8268db5eb1e5b48594&text=house%20cat%20television&lang=en-is";
*/
    private static String transl_url1 = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20171205T141245Z.709b144b45763084.686d7bb53df800a4fd509d8268db5eb1e5b48594&text=";
    private static String transl_url2 = "&lang=en-is";
    private static String random_url = "http://api.wordnik.com:80/v4/words.json/randomWords?hasDictionaryDef=true&minCorpusCount=0&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=-1&minLength=5&maxLength=10&sortBy=count&sortOrder=asc&limit=20&api_key=6e495aa4345325749497a17c9fb0b55b6a52a540c4a31422f";

    public static void setTransl_url(String t) {
        tr_url = t;
    }

    public static String getTr_url() {
        return tr_url;
    }

    public static String getTransl_url1() {
        return transl_url1;
    }

    public static String getTransl_url2() {
        return transl_url2;
    }

    public static String getRandom_url() {
        return random_url;
    }
}
