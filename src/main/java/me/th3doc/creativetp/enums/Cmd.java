package me.th3doc.creativetp.enums;

public enum Cmd {

    _effectClear("effect clear "),
    _tpcCreate("createworld"),
    _tpcSet("setworld"),
    _tpcReload("reload"),
    _tpcVoid("void");

    public String cmd;

    Cmd(String cmd) {
        this.cmd = cmd;
    }
}
