package com.sbz.code_piler

val C_KEYWORDS = listOf(
    "auto", "break", "case", "char",
    "const", "continue", "default", "do",
    "double", "else", "enum", "extern",
    "float", "for", "goto", "if",
    "int", "long", "register", "return",
    "short", "signed", "sizeof", "static",
    "struct", "switch", "typedef", "union",
    "unsigned", "void", "volatile", "while",
    "printf", "scanf"
)

val CPP_KEYWORDS = listOf(
    "asm", "double", "new", "switch",
    "auto", "else", "operator", "template",
    "break", "enum", "private", "this",
    "case", "extern", "protected", "throw",
    "catch", "float", "public", "try",
    "char", "for", "register", "typedef",
    "class", "friend", "return", "union",
    "const", "goto", "short", "unsigned",
    "continue", "if", "signed", "virtual",
    "default", "inline", "sizeof", "void",
    "delete", "int", "static", "volatile",
    "do", "long", "struct", "while", "cout",
    "cin"
)

val JAVA_KEYWORDS = listOf(
    "abstract", "assert", "boolean", "break", "byte",
    "case", "catch", "char", "class", "const",
    "continue", "default", "do", "double", "else",
    "enum", "extends", "final", "finally", "float",
    "for", "goto", "if", "implements", "import",
    "instanceof", "int", "interface", "long", "native",
    "new", "package", "private", "protected", "public",
    "return", "short", "static", "strictfp", "super",
    "switch", "synchronized", "this", "throw", "throws",
    "transient", "try", "void", "volatile", "while"
)

val PYTHON_KEYWORDS = listOf(
    "False", "await", "else", "import", "pass",
    "None", "break", "except", "in", "raise",
    "True", "class", "finally", "is", "return",
    "and", "continue", "for", "lambda", "try",
    "as", "def", "from", "nonlocal", "while",
    "assert", "del", "global", "not", "with",
    "async", "elif", "if", "or", "yield"
)