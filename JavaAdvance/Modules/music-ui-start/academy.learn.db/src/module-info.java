module academy.learn.db {
    requires java.sql;
    requires sqlite.jdbc;
    requires transitive academy.intro.common;

    exports academy.learn.db to academy.learn.ui;
}