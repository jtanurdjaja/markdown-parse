MarkdownParse.class: MarkdownParse.java
	javac MarkdownParse.java

MarkdownTest.class: MarkdownTest.java MarkdownParse.class
	javac MarkdownTest.java