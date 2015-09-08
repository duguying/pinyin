package net.duguying.pinyin;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rex on 2015/4/27.
 */
public class Pinyin {
    private String charsContent = "";
    private String wordsContent = "";

    public Pinyin(){
        try {
            this.loadContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContent() throws IOException {
        InputStream isChars = this.getClass().getResourceAsStream("chars.csv");
        this.charsContent = this.inputStream2String(isChars);

        InputStream isWords = this.getClass().getResourceAsStream("words.csv");
        this.wordsContent = this.inputStream2String(isWords);
    }

    private String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for(int n; (n = in.read(b)) != -1;){
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
