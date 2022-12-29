### 依赖
<!-- 拼音 -->
<dependency>
    <groupId>com.belerweb</groupId>
    <artifactId>pinyin4j</artifactId>
    <version>2.5.1</version>
</dependency>

###
```text
setToneType 设置音标的显示方式：
HanyuPinyinToneType.WITH_TONE_MARK：在拼音字母上显示音标，如“zhòng”
HanyuPinyinToneType.WITH_TONE_NUMBER：在拼音字符串后面通过数字显示，如“zhong4”
HanyuPinyinToneType.WITHOUT_TONE：不显示音标

setCaseType 设置拼音大小写：
HanyuPinyinCaseType.LOWERCASE：返回的拼音为小写字母
HanyuPinyinCaseType.UPPERCASE：返回的拼音为大写字母

setVCharType 设置拼音字母“ü”的显示方式
汉语拼音中的“ü”不能简单的通过英文来表示，所以需要单独定义“ü”的显示格式
HanyuPinyinVCharType.WITH_U_UNICODE：默认的显示方式，输出“ü”
HanyuPinyinVCharType.WITH_V：输出“v”
HanyuPinyinVCharType.WITH_U_AND_COLON：输出“u:”
```
