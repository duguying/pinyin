package net.duguying.pinyin;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rex on 2015/4/27.
 */
public class Pinyin {
    private String charsContent = "";
    private String wordsContent = "";
    private Map<String,String> wordsDict = new HashMap<String,String>();

    private int WORD_MAX_LEN = 10;
    private char SEP = ',';
    private char REP = 7;

    public Pinyin(){
        try {
            this.loadContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.parseChars();
        this.parseWords();
    }

    private void loadContent() throws IOException {
        InputStream isChars = this.getClass().getResourceAsStream("chars.csv");
        this.charsContent = this.inputStream2String(isChars).trim();

        InputStream isWords = this.getClass().getResourceAsStream("words.csv");
        this.wordsContent = this.inputStream2String(isWords).trim();
    }

    private String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for(int n; (n = in.read(b)) != -1;){
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    private void parseChars(){
        String[] charsArr = this.charsContent.split("\n");
        for (String charStr:charsArr){
            String[] charEle = charStr.split(",");
            this.wordsDict.put(charEle[0].trim(), SEP + charEle[1].trim());
        }
    }

    private void parseWords(){
        String[] wordsArr = this.wordsContent.split("\n");
        for (String wordStr:wordsArr){
            String[] wordEle = wordStr.split(",");
            String pinyin = "";

            for (int i = 1; i < wordEle.length; i++){
                pinyin = pinyin + SEP + wordEle[i];
            }

            this.wordsDict.put(wordEle[0].trim(), pinyin.trim());
        }
    }

    private String translate(String content){
        content = content.replaceAll(this.SEP + "", this.REP + "");

        String result = "";

        int len = 0;
        int tailStartIdx = 0;
        int tailEndIdx = 0;
        int leftEndIdx = 0;
        String tail = "";
        String left = content;

        for (;left.length() > 0;) {
            // outer
            if(tail.length() == 0){
                len = left.length();
                int cutLen = (len > this.WORD_MAX_LEN) ? this.WORD_MAX_LEN : len;
                tailStartIdx = len - cutLen;
                tailEndIdx = len;
                leftEndIdx = tailStartIdx;
                tail = content.substring(tailStartIdx, tailEndIdx);
                left = content.substring(0, leftEndIdx);
            }

            for (; tail.length() > 1; ) {
                String value = this.wordsDict.get(tail);
                if (value != null && !value.equals("")) {
                    result = value + result;
                    tail = "";
                    break;
                } else {
                    left = left + tail.charAt(0);
                    tail = tail.substring(1, tail.length());
                }
            }
            if (tail.length() > 0){
                String value = this.wordsDict.get(tail);
                value = (value != null) ? value : SEP + tail;
                result = value + result;
                tail = "";
            }
        }

        if (result.charAt(0) == this.SEP){
            result = result.substring(1, result.length());
        }



        return result;
    }

    public String translateWithSep(String content){
        String result = this.translate(content);
        result = result.replaceAll(this.REP + "", this.SEP + "");
        return result;
    }

    public String[] translateInArray(String content){
        String result = this.translate(content);
        String[] resultArray = result.split(this.SEP + "");
        for (int idx = 0; idx < resultArray.length; idx++){
            String element = resultArray[idx];
            resultArray[idx] = element.replace(this.REP, this.SEP);
        }
        return resultArray;
    }

    public String translateIntoPinyin(String content){
        String result = this.translate(content);
        result = result.replaceAll(SEP + "","");
        result = result.replaceAll(this.REP + "", this.SEP + "");
        return result;
    }
}
