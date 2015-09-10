package net.duguying.pinyin;

import junit.framework.TestCase;

/**
 * Created by duguying on 2015/9/10.
 */
public class PinyinTest extends TestCase {
    public void testPinyin(){
        Pinyin py = new Pinyin();

        String result1 = py.translate("我是中国人，我爱我的祖国。i am a chinese, i love china.");
        assertEquals(result1, "wǒshìzhōngguórén，wǒàiwǒdezǔguó。i am a chinese, i love china.");

        String result2 = py.translate("わたしわ阿飞, and my English name is Rex Lee. 网名是独孤影！ ^_^。下面是一段多音分词歧义测试，这个人无伤无臭味。");
        assertEquals(result2, "わたしわāfēi, and my English name is Rex Lee. wǎngmíngshìdúgūyǐng！ ^_^。xiàmiànshìyīduànduōyīnfēncíqíyìcèshì，zhègèrénwúshāngwúchòuwèi。");

        System.out.println(result1);
        System.out.println(result2);
    }
}
