[linux]
run:
    #!/usr/bin/env bash
    set -euo pipefail
    javac src/main/java/xyz/whinyaan/*.java
    java -Dawt.useSystemAAFontSettings=lcd_hrgb -Dswing.aatext=true -cp src/main/java xyz.whinyaan.App

[macos]
run:
    #!/usr/bin/env bash
    set -euo pipefail
    javac src/main/java/xyz/whinyaan/*.java
    java -cp src/main/java xyz.whinyaan.App

[windows]
run:
    #!powershell.exe
    javac src/main/java/xyz/whinyaan/*.java
    java -cp src/main/java xyz.whinyaan.App