set shell := ["powershell.exe", "-c"]

run:
    @ javac src/main/java/xyz/whinyaan/*.java
    @ java -cp src/main/java xyz.whinyaan.App