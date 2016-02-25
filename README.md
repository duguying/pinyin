# pinyin #

----------

[![travis](https://travis-ci.org/duguying/pinyin.svg)](https://travis-ci.org/duguying/pinyin)

***pinyin*** is java library which could translate Chinese character into Chinese PinYin.

## maven build ##
```
mvn package
```

## maven

```xml
<dependency>
    <groupId>net.duguying.pinyin</groupId>
    <artifactId>pinyin</artifactId>
    <version>0.0.0</version>
</dependency>
```

## usage

```java
Pinyin py = new Pinyin();
py.translate("汉");
py.translate("わたしわ阿飞, and my English name is Rex Lee. 网名是独孤影！ ^_^。下面是一段多音分词歧义测试，这个人无伤无臭味。");
```

## License ##

MIT License
