package net.duguying.pinyin;

import junit.framework.TestCase;

/**
 * Created by duguying on 2015/9/10.
 */
public class PinyinTest extends TestCase {
    public void testPinyin(){
        Pinyin py = new Pinyin();

        String[] result1 = py.translateInArray("我是中国人，我爱我的祖国。i am a chinese, i love china.");
        String[] expect1 = {"wǒ","shì","zhōng","guó","rén","，","wǒ","ài","wǒ","de","zǔ","guó","。","i"," ","a","m"," ","a"," ","c","h","i","n","e","s","e",","," ","i"," ","l","o","v","e"," ","c","h","i","n","a","."};
        for (int i = 0; i < result1.length; i++){
            assertEquals(result1[i], expect1[i]);
        }

        String result2 = py.translate("わたしわ阿飞, and my English name is Rex Lee. 网名是独孤影！ ^_^。下面是一段多音分词歧义测试，这个人无伤无臭味。");
        assertEquals(result2, "わたしわāfēi, and my English name is Rex Lee. wǎngmíngshìdúgūyǐng！ ^_^。xiàmiànshìyīduànduōyīnfēncíqíyìcèshì，zhègèrénwúshāngwúchòuwèi。");

        String result3 = py.translateWithSep("世界你好, hello world", ",");
        assertEquals(result3, "shì,jiè,nǐ,hǎo,,, ,h,e,l,l,o, ,w,o,r,l,d");

        String result3_1 = py.translateWithSep("世界你好, hello world");
        assertEquals(result3_1, "shì,jiè,nǐ,hǎo,,, ,h,e,l,l,o, ,w,o,r,l,d");

        String[] result4 = py.translateInArrayNoMark("わたしわ阿飞, and my English name is Rex Lee. 网名是独孤影！ ^_^。下面是一段多音分词歧义测试，这个人无伤无臭味。");
        this.dumpStringArray(result4);

        String result5 = py.translateFirstChar("わたしわ阿飞, and my English name is Rex Lee. 网名是独孤影！ ^_^。下面是一段多音分词歧义测试，这个人无伤无臭味。");
        System.out.println(result5);
    }

    private void dumpStringArray(String[] array){
        System.out.print("[");
        for (String element : array){
            System.out.print(element + ",");
        }
        System.out.print("]\n");
    }
}
